package kz.kase.bot.fix;


import kz.kase.bot.model.domain.*;
import kz.kase.bot.storage.Storage;
import kz.kase.fix.*;
import kz.kase.fix.messages.*;
import quickfix.Group;

import java.time.format.DateTimeFormatter;
import java.util.*;

import static kz.kase.fix.FixProtocol.*;

public class FixParserTR implements FixParser {

    public static final String TYPE_MM = "5";
    public static final String TYPE_TRADER = "8";
    public static final int PRICE_PRECISION_TYPE = 27;
    public static final int DEFAULT_PRICE_PRECISION = 2;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public enum MdIncRefreshType {
        Instrument, Quote
    }

    @Override
    public AccountHolder parseAcc(PositionReport report, String symbol) {
        AccountHolder acc = new AccountHolder();
        acc.setName(report.getAccount());
        PositionHolder pos = parsePositions(report, symbol);
        if (pos.getType() == PositionHolder.PosType.MONEY) {
            PositionHolder moneyPos = acc.getMoneyPosition(pos.getSymbol());
            if (moneyPos != null) {
                moneyPos.update(pos);
                acc.addMoneyPosition(moneyPos);
            } else {
                acc.addMoneyPosition(pos);
            }
        } else if (pos.getType() == PositionHolder.PosType.INSTRUMENT) {
            PositionHolder instrPos = acc.getPosition(pos.getSymbol());
            if (instrPos != null) {
                instrPos.update(pos);
                acc.addPosition(instrPos);
            } else {
                acc.addPosition(pos);
            }
        }
        return acc;
    }

    @Override
    public PositionHolder parsePositions(PositionReport report, String symbol) {
        return null;
    }

    @Override
    public Collection<InstrHolder> parse(SecurityList list) {
        List<InstrHolder> result = new ArrayList<>();

        for (int i = 1; i <= list.getInstrumentsCount(); i++) {

            Group noRelatedSym = list.getGroup(i, FIELD_NO_RELATED_SYM);
            InstrHolder instr = new InstrHolder();
            instr.setSymbol(noRelatedSym.getString(FIELD_SYMBOL));

            List<Group> nestedAttr = noRelatedSym.getGroups(FIELD_NO_NESTED_INSTR_ATTRIB);
            for (Group nest : nestedAttr) {
                if (nest.isSetField(FIELD_NESTED_INSTR_ATTRIB_TYPE)) {
                    int attType = nest.getInt(FixProtocol.FIELD_NESTED_INSTR_ATTRIB_TYPE);
                    if (attType == PRICE_PRECISION_TYPE) {
                        String pricePrecStr = nest.getString(FixProtocol.FIELD_NESTED_INSTR_ATTRIB_VALUE);
                        int pricePrc = 0;
                        try {
                            pricePrc = Integer.parseInt(pricePrecStr);
                        } catch (NumberFormatException ne) {
                            //igonre
                        }
                        instr.setPricePrecision(pricePrc);
                    }
                } else {
                    instr.setPricePrecision(DEFAULT_PRICE_PRECISION);
                }
            }

            if (noRelatedSym.isSetField(FIELD_SESSION_PERIOD_VALUE)) {
                instr.setMarketDepth(noRelatedSym.getInt(FIELD_SESSION_PERIOD_VALUE));
            }

            List<Group> ordTypeRules = noRelatedSym.getGroups(FixProtocol.FIELD_NO_ORDER_TYPE_RULES);
            for (Group ot : ordTypeRules) {
                OrderType orderType = OrderType.valueOf(ot.
                        getChar(FixProtocol.FIELD_ORDER_TYPE));
                instr.addOrderType(orderType);
            }

            List<Group> tradeSessRules = noRelatedSym.getGroups(FixProtocol.FIELD_NO_TRADING_SESSION_RULES);
            for (Group ses : tradeSessRules) {

                if (ses.isSetField(FIELD_TRADE_SESSION_ID)) {
                    instr.setCurrSessionId(ses.getInt(FIELD_TRADE_SESSION_ID));
                }

                if (ses.isSetField(FIELD_TRADE_SESSION_SUB_ID)) {
                    instr.setTradeCondition(TradeCondition.valueOf(ses.getString(FIELD_TRADE_SESSION_SUB_ID)));
                }
            }

            List<Group> timeInForceRules = noRelatedSym.getGroups(FixProtocol.FIELD_NO_TIME_IN_FORCE_RULES);
            for (Group tifGr : timeInForceRules) {
                TimeInForce tif = TimeInForce.valueOf(tifGr.
                        getChar(FixProtocol.FIELD_TIME_IN_FORCE));
                instr.addTimeInForce(tif);
            }

            if (noRelatedSym.isSetField(FIELD_MIN_TRADE_VOL)) {
                instr.setMinQty(noRelatedSym.getInt(FIELD_MIN_TRADE_VOL));
            }

            if (noRelatedSym.isSetField(FIELD_MAX_PRICE_VARIATION)) {
                instr.setDevLimitLastDealPrc(noRelatedSym.getDouble(FIELD_MAX_PRICE_VARIATION));
                //todo make lastdeal deviation in instr to double
            }

            if (noRelatedSym.isSetField(FIELD_TRADING_CURRENCY)) {
                instr.setCounterCurrency(noRelatedSym.getString(FIELD_TRADING_CURRENCY));//todo cross cur in instr String
            }

            if (noRelatedSym.isSetField(FIELD_ROUND_LOT)) {
                instr.setLot(noRelatedSym.getInt(FIELD_ROUND_LOT));
            }

            if (noRelatedSym.isSetField(FIELD_SECURITY_ID)) {
                instr.setNin(noRelatedSym.getString(FIELD_SECURITY_ID));
            }

            if (noRelatedSym.isSetField(FIELD_SECURITY_STATUS)) {
                instr.setStatus(SecStatus.getByValue(noRelatedSym.getInt(FIELD_SECURITY_STATUS)));
            }

            if (noRelatedSym.isSetField(FIELD_SECURITY_GROUP)) {
                instr.setSecurityGroup(noRelatedSym.getString(FIELD_SECURITY_GROUP));
            }

            if (noRelatedSym.isSetField(FIELD_TEXT)) {
                instr.setOrderSides(noRelatedSym.getString(FIELD_TEXT));
            }

            if (noRelatedSym.isSetField(FIELD_NOMINAL_VALUE)) {
                instr.setNominal(noRelatedSym.getLong(FIELD_NOMINAL_VALUE));
            }

            if (noRelatedSym.isSetField(FIELD_PRODUCT)) {
                instr.setMarketType(Product.valueOf(noRelatedSym.getInt(FIELD_PRODUCT)));
            }

            if (noRelatedSym.isSetField(FIELD_DEV_LIMIT_AVG_PRICE_VALUE)) {
                instr.setDevLimitAvgPrc(noRelatedSym.getDouble(FIELD_DEV_LIMIT_AVG_PRICE_VALUE));
            }

            if (noRelatedSym.isSetField(FIELD_DEV_LIM_MARKET_PRC_VALUE)) {
                instr.setDevLimitMarketPrc(noRelatedSym.getInt(FIELD_DEV_LIM_MARKET_PRC_VALUE));
            }

            if (noRelatedSym.isSetField(FIELD_CROSS_CURRENCY)) {
                instr.setCrossCurrency(noRelatedSym.getString(FIELD_CROSS_CURRENCY));
            }
            if (noRelatedSym.isSetField(FIELD_COUNTER_CURRENCY)) {
                instr.setCounterCurrency(noRelatedSym.getString(FIELD_COUNTER_CURRENCY));
            }

            if (noRelatedSym.isSetField(FIELD_MIN_PRICE_INCREMENT)) {
                instr.setPriceStep(noRelatedSym.getDouble(FIELD_MIN_PRICE_INCREMENT));
            }

            if (noRelatedSym.isSetField(FIELD_TAGS)) {
                instr.setTags(noRelatedSym.getString(FIELD_TAGS));
            }

            if (noRelatedSym.isSetField(FIELD_NO_LEGS)) {
                instr.setSwap(true);
            }

            result.add(instr);
        }
        return result;
    }

    @Override
    public Collection<InstrHolder> parse(MDIncRefresh md) {
        return null;
    }

    @Override
    public Collection<InstrHolder> parseInstrument(MDIncRefresh md, Storage storage) {
        Map<String, InstrHolder> result = new HashMap<>();
        List<Group> groups = md.getGroups(FIELD_NO_MD_ENTRIES);
        if (groups != null) {

            for (Group g : groups) {
                if (!g.isSetField(FIELD_SYMBOL)) continue;

                String symbol = g.getString(FIELD_SYMBOL);

                InstrHolder instr = result.get(symbol);

                if (instr == null) {
                    instr = new InstrHolder();
                    instr.setSymbol(g.getString(FIELD_SYMBOL));
                    result.put(symbol, instr);
                }

                if (g.isSetField(FIELD_TRADE_SESSION_ID)) {
                    int sesId = g.getInt(FIELD_TRADE_SESSION_ID);
                    if (!instr.hasCurrSessionId()
                            || instr.getCurrSessionId() < sesId) {
                        instr.setCurrSessionId(sesId);
                        instr.clearAll();
                    }

                    if (instr.getCurrSessionId().equals(sesId)) {

                        if (g.isSetField(FIELD_AVERAGE_WEIGHTED_PRICE)) {
                            instr.setAverageWeightedPrice(g.getDouble(FIELD_AVERAGE_WEIGHTED_PRICE));
                        }

                        if (g.isSetField(FIELD_LAST_PX)) {
                            instr.setLastPx(g.getDouble(FIELD_LAST_PX));
                        }

                        if (g.isSetField(FIELD_PRICE_DELTA)) {
                            instr.setDeltaPrice(g.getDouble(FIELD_PRICE_DELTA));
                        }

                        if (g.isSetField(FIELD_PRICE_DELTA) &&
                                g.getDouble(FIELD_PRICE_DELTA) != 0 &&
                                g.isSetField(FIELD_LAST_PX) &&
                                g.getDouble(FIELD_LAST_PX) != 0) {
                            double delta = g.getDouble(FIELD_PRICE_DELTA);
                            double last = g.getDouble(FIELD_LAST_PX);
                            double deltaPriceProc = (delta / last) * 100;
                            instr.setDeltaPricePercent(deltaPriceProc);
                        } else {
                            instr.setDeltaPricePercent(0D);
                        }

                        if (g.isSetField(FIELD_AVERAGE_PRC)) {
                            instr.setAveragePrc(g.getDouble(FIELD_AVERAGE_PRC));
                        }
                        if (g.isSetField(FIELD_AVERAGE_PRC) &&
                                g.isSetField(FIELD_AVG_PRC_PREV)) {

                            double summ = g.getDouble(FIELD_AVERAGE_PRC) != 0.0 ?
                                    g.getDouble(FIELD_AVERAGE_PRC) - g.getDouble(FIELD_AVG_PRC_PREV) : 0.0;
                            double perc = g.getDouble(FIELD_AVERAGE_PRC) != 0.0 ?
                                    (summ / g.getDouble(FIELD_AVG_PRC_PREV) * 100) : 0.0;
                            instr.setExchangeRate(summ);
                            instr.setExchangeRatePerc(perc);
                        } else {
                            instr.setExchangeRate(0D);
                            instr.setExchangeRatePerc(0D);
                        }
                    }
                }

                if (g.isSetField(FIELD_AVG_PRC_PREV)) {
                    instr.setAveragePrcBeforeToday(g.getDouble(FIELD_AVG_PRC_PREV));
                }

                if (g.isSetField(FIELD_LAST_DEAL_BEFORE_TODAY_PRICE)) {
                    instr.setLastDealBeforeTodayPrice(g.getDouble(FIELD_LAST_DEAL_BEFORE_TODAY_PRICE));
                }
            }
        }
        return result.values();
    }

    @Override
    public Collection<TradeSessionHolder> parseTradeSession(MDIncRefresh md) {
        return null;
    }

    @Override
    public InstrHolder parse(MDFullSnapshotRefresh md) {
        return null;
    }

    @Override
    public InstrHolder parse(SecurityStatus ss) {
        return null;
    }

    @Override
    public OrderHolder parseOrder(ExecutionReport report) {
        return null;
    }

    @Override
    public QuoteHolder.Type convert(MDEntryType type) {
        return null;
    }

    @Override
    public List<MMLiabilityHolder> parse(MMLiabilityList mmList) {
        return null;
    }

    @Override
    public MMStatHolder parse(MMStat mmStat) {
        return null;
    }

    @Override
    public AccountHolder parseAcc(DayPositionReport report) {
        return null;
    }

    @Override
    public List<DayPositionHolder> parseDayPositions(DayPositionReport report) {
        return null;
    }

    @Override
    public List<IndicativeQuoteHolder> parse(IndicativeQuoteList mmList) {
        return null;
    }

    @Override
    public NextCalendar parse(CalendarResponse resp) {
        return null;
    }
}
