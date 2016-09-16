package kz.kase.bot.model.domain;


import kz.kase.bot.fix.QuickFixClient.FixServerType;
import kz.kase.fix.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InstrHolder implements Holder {

    public static final double DELTA = 0.00000001;

    private Long qty;
    private Long leavesQty;
    private String symbol;
    private String buyOrderSerial;
    private String sellOrderSerial;
    private String serial;
    private String instr;
    private String accName;
    private String buyAcc;
    private String sellAcc;
    private String buyUserName;
    private String sellUserName;
    private String dealType;
    private String sessionSerial;
    private Integer number;
    private Double price;
    private Double cashQty;
    private Date creationTime;
    private Date lastUpdateTime;
    private Date settlementDate;
    private Side side;
    private TradeCondition tradeCondition;
    private Integer currSessionId;
    private Integer marketDepth;
    private Double openPrice;
    private Double averagePrcBeforeToday;
    private Double averagePrc;
    private String orderSides;
    private Long lot;
    private String nin;
    private Long minQty;
    private Double devLimitAvgPrc;
    private Double devLimitLastDealPrc;
    private Double priceStep;
    private Integer pricePrecision;
    private Product marketType;
    private final List<OrderType> orderTypes = new ArrayList<>();
    private String counterCurrency;
    private SecStatus status;
    private String securityGroup;
    private Long nominal;
    private Integer devLimitMarketPrc;
    private String crossCurrency;
    private String tags;
    private final List<TimeInForce> timeInForces = new ArrayList<>();
    private final CopyOnWriteArrayList<QuoteHolder> quotes = new CopyOnWriteArrayList<>();
    private Double averageWeightedPrice;
    private Double lastPx;
    private Double deltaPrice;
    private Double deltaPricePercent;
    private Double lastDealBeforeTodayPrice;
    private Double exchangeRatePerc;
    private Double exchangeRate;
    private Long bestSellVolume;
    private Long bestBuyVolume;
    private Double bestBuyPrice;
    private Double bestSellPrice;
    private Boolean swap;


    @Override
    public Object getKey() {
        return getSymbol();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setNumber(int id) {
        this.number = id;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Long getLeavesQty() {
        return leavesQty;
    }

    public void setLeavesQty(Long leavesQty) {
        this.leavesQty = leavesQty;
    }

    public String getBuyOrderSerial() {
        return buyOrderSerial;
    }

    public void setBuyOrderSerial(String buyOrderSerial) {
        this.buyOrderSerial = buyOrderSerial;
    }

    public String getSellOrderSerial() {
        return sellOrderSerial;
    }

    public void setSellOrderSerial(String sellOrderSerial) {
        this.sellOrderSerial = sellOrderSerial;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getInstr() {
        return instr;
    }

    public void setInstr(String instr) {
        this.instr = instr;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getBuyAcc() {
        return buyAcc;
    }

    public void setBuyAcc(String buyAcc) {
        this.buyAcc = buyAcc;
    }

    public String getSellAcc() {
        return sellAcc;
    }

    public void setSellAcc(String sellAcc) {
        this.sellAcc = sellAcc;
    }

    public String getBuyUserName() {
        return buyUserName;
    }

    public void setBuyUserName(String buyUserName) {
        this.buyUserName = buyUserName;
    }

    public String getSellUserName() {
        return sellUserName;
    }

    public void setSellUserName(String sellUserName) {
        this.sellUserName = sellUserName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSessionSerial() {
        return sessionSerial;
    }

    public void setSessionSerial(String sessionSerial) {
        this.sessionSerial = sessionSerial;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCashQty() {
        return cashQty;
    }

    public void setCashQty(Double cashQty) {
        this.cashQty = cashQty;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public TradeCondition getTradeCondition() {
        return tradeCondition;
    }

    public void setTradeCondition(TradeCondition tradeCondition) {
        this.tradeCondition = tradeCondition;
    }

    public Integer getCurrSessionId() {
        return currSessionId;
    }

    public void setCurrSessionId(Integer currSessionId) {
        this.currSessionId = currSessionId;
    }

    public Integer getMarketDepth() {
        return marketDepth;
    }

    public void setMarketDepth(Integer marketDepth) {
        this.marketDepth = marketDepth;
    }

    public String getOrderSides() {
        return orderSides;
    }

    public void setOrderSides(String orderSides) {
        this.orderSides = orderSides;
    }

    public Double getAveragePrc() {
        return averagePrc;
    }

    public void setAveragePrc(Double averagePrc) {
        this.averagePrc = averagePrc;
    }

    public Double getAveragePrcBeforeToday() {
        return averagePrcBeforeToday;
    }

    public void setAveragePrcBeforeToday(Double averagePrcBeforeToday) {
        this.averagePrcBeforeToday = averagePrcBeforeToday;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Long getLot() {
        return lot;
    }

    public void setLot(long lot) {
        this.lot = lot;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public Long getMinQty() {
        return minQty;
    }

    public void setMinQty(long minQty) {
        this.minQty = minQty;
    }

    public Double getDevLimitAvgPrc() {
        return devLimitAvgPrc;
    }

    public void setDevLimitAvgPrc(Double devLimitAvgPrc) {
        this.devLimitAvgPrc = devLimitAvgPrc;
    }

    public Double getDevLimitLastDealPrc() {
        return devLimitLastDealPrc;
    }

    public void setDevLimitLastDealPrc(Double devLimitLastDealPrc) {
        this.devLimitLastDealPrc = devLimitLastDealPrc;
    }

    public Double getPriceStep() {
        return priceStep;
    }

    public void setPriceStep(Double priceStep) {
        this.priceStep = priceStep;
    }

    public Integer getPricePrecision() {
        return pricePrecision;
    }

    public void setPricePrecision(Integer pricePrecision) {
        this.pricePrecision = pricePrecision;
    }

    public Product getMarketType() {
        return marketType;
    }

    public void setMarketType(Product marketType) {
        this.marketType = marketType;
    }

    public void setOrderTypes(List<OrderType> ots) {
        orderTypes.addAll(ots);
    }

    public void addOrderType(OrderType ot) {
        orderTypes.add(ot);
    }

    public List<OrderType> getOrderTypes() {
        return orderTypes;
    }

    public String getCounterCurrency() {
        return counterCurrency;
    }

    public void setCounterCurrency(String counterCurrency) {
        this.counterCurrency = counterCurrency;
    }

    public SecStatus getStatus() {
        return status;
    }

    public void setStatus(SecStatus status) {
        this.status = status;
    }

    public String getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(String securityGroup) {
        this.securityGroup = securityGroup;
    }

    public Long getNominal() {
        return nominal;
    }

    public void setNominal(Long nominal) {
        this.nominal = nominal;
    }

    public Integer getDevLimitMarketPrc() {
        return devLimitMarketPrc;
    }

    public void setDevLimitMarketPrc(Integer devLimitMarketPrc) {
        this.devLimitMarketPrc = devLimitMarketPrc;
    }

    public String getCrossCurrency() {
        return crossCurrency;
    }

    public void setCrossCurrency(String crossCurrency) {
        this.crossCurrency = crossCurrency;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setTimeInForces(List<TimeInForce> tifs) {
        timeInForces.addAll(tifs);
    }

    public void addTimeInForce(TimeInForce tif) {
        timeInForces.add(tif);
    }

    public List<TimeInForce> getTimeInForces() {
        return timeInForces;
    }

    public Double getDeltaPricePercent() {
        return deltaPricePercent;
    }

    public Double getDeltaPrice() {
        return deltaPrice;
    }

    public void setDeltaPrice(Double deltaPrice) {
        this.deltaPrice = deltaPrice;
    }

    public Double getLastPx() {
        return lastPx;
    }

    public void setLastPx(Double lastPx) {
        this.lastPx = lastPx;
    }

    public Double getAverageWeightedPrice() {
        return averageWeightedPrice;
    }

    public void setAverageWeightedPrice(Double averageWeightedPrice) {
        this.averageWeightedPrice = averageWeightedPrice;
    }

    public void setDeltaPricePercent(Double deltaPricePercent) {
        this.deltaPricePercent = deltaPricePercent;
    }

    public Double getLastDealBeforeTodayPrice() {
        return lastDealBeforeTodayPrice;
    }

    public void setLastDealBeforeTodayPrice(Double lastDealBeforeTodayPrice) {
        this.lastDealBeforeTodayPrice = lastDealBeforeTodayPrice;
    }

    public Double getExchangeRatePerc() {
        return exchangeRatePerc;
    }

    public void setExchangeRatePerc(Double exchangeRatePerc) {
        this.exchangeRatePerc = exchangeRatePerc;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public List<QuoteHolder> getQuotes() {
        return quotes;
    }

    public void addQoute(QuoteHolder quote) {
        quotes.add(quote);
    }

    public QuoteHolder getQuote(double price, QuoteHolder.Type type) {
        for (QuoteHolder e : quotes) {
            if (Math.abs(e.getPrice() - price) < DELTA && type == e.getType()) {
                return e;
            }
        }
        return null;
    }

    public Long getBestSellVolume() {
        return bestSellVolume;
    }

    public void setBestSellVolume(Long bestSellVolume) {
        this.bestSellVolume = bestSellVolume;
    }

    public Long getBestBuyVolume() {
        return bestBuyVolume;
    }

    public void setBestBuyVolume(Long bestBuyVolume) {
        this.bestBuyVolume = bestBuyVolume;
    }

    public Double getBestBuyPrice() {
        return bestBuyPrice;
    }

    public void setBestBuyPrice(Double bestBuyPrice) {
        this.bestBuyPrice = bestBuyPrice;
    }

    public Double getBestSellPrice() {
        return bestSellPrice;
    }

    public void setBestSellPrice(Double bestSellPrice) {
        this.bestSellPrice = bestSellPrice;
    }

    public Boolean isSwap() {
        return swap;
    }

    public void setSwap(Boolean swap) {
        this.swap = swap;
    }

    /***********************/

    public boolean hasNin() {
        return nin != null;
    }

    public boolean hasSymbol() {
        return symbol != null;
    }

    public boolean hasCurrSessionId() {
        return currSessionId != null;
    }

    public boolean hasOrderSides() {
        return orderSides != null;
    }

    public boolean hasLot() {
        return lot != null;
    }

    public boolean hasMinQty() {
        return minQty != null;
    }

    public boolean hasDevLimAvgPrc() {
        return devLimitAvgPrc != null;
    }

    public boolean hasDevLimLastDealPrc() {
        return devLimitLastDealPrc != null;
    }

    public boolean hasPriceStep() {
        return priceStep != null;
    }

    public boolean hasPricePrecision() {
        return pricePrecision != null;
    }

    public boolean hasMarketType() {
        return marketType != null;
    }

    public boolean hasOrderTypes() {
        return orderTypes.size() > 0;
    }

    public boolean hasCounterCurrency() {
        return counterCurrency != null;
    }

    public boolean hasStatus() {
        return status != null;
    }

    public boolean hasSecurityGroup() {
        return securityGroup != null;
    }

    public boolean hasNominal() {
        return nominal != null;
    }

    public boolean hasDevLimitMarketPrc() {
        return devLimitMarketPrc != null;
    }

    public boolean hasCrossCurrency() {
        return crossCurrency != null;
    }

    public boolean hasTags() {
        return tags != null;
    }

    public boolean hasTimeInForces() {
        return timeInForces.size() > 0;
    }

    public boolean hasTradeCondition() {
        return tradeCondition != null;
    }

    public boolean hasAveragePrc() {return averagePrc != null;}

    public boolean hasAveragePrcBeforeToday() {return averagePrcBeforeToday != null;}

    public boolean hasLastPrc() {return lastPx != null;}

    public boolean hasAverageWeightedPrice() {return averageWeightedPrice != null;}

    public boolean hasDeltaPriceSet() {return deltaPrice != null;}

    public boolean hasOpenPrice() {return openPrice != null;}

    public boolean hasLDBTPriceSet() {return lastDealBeforeTodayPrice != null;}

    /********************************/

    public void clearAll() {
        openPrice = null;
        clearBestPrcVols();
    }

    public void updateMarketData(InstrHolder newInstr, FixServerType serverType) {

        if ((serverType == FixServerType.MarketData) ||
                (newInstr.hasCurrSessionId() && hasCurrSessionId()
                        && newInstr.getCurrSessionId().equals(getCurrSessionId()))) {



            if (newInstr.hasAveragePrc()) {
                if (newInstr.getAveragePrc() != 0) {
                    setAveragePrc(newInstr.getAveragePrc());
                }
                if (hasAveragePrcBeforeToday()) {
                    if (newInstr.getAveragePrc() != 0) {
//                        double avgPrc =
                        double summ = newInstr.getAveragePrc() - getAveragePrcBeforeToday();
                        double perc = ((summ / getAveragePrcBeforeToday()) * 100);
                        setExchangeRate(summ);
                        setExchangeRatePerc(perc);
                    } else {
                        setExchangeRate(0D);
                        setExchangeRatePerc(0D);
                    }
                }

            }


            if (newInstr.hasAveragePrcBeforeToday()) {
                setAveragePrcBeforeToday(newInstr.getAveragePrcBeforeToday());
                if (hasAveragePrc()) {
                    double summ = getAveragePrc() != 0.0 ? getAveragePrc() - newInstr.getAveragePrcBeforeToday() : 0.0;
                    double perc = getAveragePrc() != 0.0 ? ((summ / newInstr.getAveragePrcBeforeToday()) * 100) : 0.0;
                    setExchangeRate(summ);
                    setExchangeRatePerc(perc);
                }
            }

            if (newInstr.hasLastPrc()) {
                setLastPx(newInstr.getLastPx());
            }

            if (newInstr.hasAverageWeightedPrice()) {
                setAverageWeightedPrice(newInstr.getAverageWeightedPrice());
            }

            if (newInstr.hasDeltaPriceSet()) {
                double delta = newInstr.getDeltaPrice();
                setDeltaPrice(delta);
                if (delta != 0 &&
                        hasLastPrc() &&
                        getLastPx() != 0) {
                    setDeltaPricePercent((delta / getLastPx()) * 100);
                }
                if (delta == 0) {
                    setDeltaPricePercent(0D);
                }
            }

            if (newInstr.hasOpenPrice()) {
                setOpenPrice(newInstr.getOpenPrice());
            }
        }

        if (newInstr.hasAveragePrcBeforeToday()) {
            setAveragePrcBeforeToday(newInstr.getAveragePrcBeforeToday());
            if (hasAveragePrc()) {
                double summ = getAveragePrc() != 0.0 ? getAveragePrc() - newInstr.getAveragePrcBeforeToday() : 0.0;
                double perc = getAveragePrc() != 0.0 ? ((summ / newInstr.getAveragePrcBeforeToday()) * 100) : 0.0;
                setExchangeRate(summ);
                setExchangeRatePerc(perc);
            }
        }

        if (newInstr.hasLDBTPriceSet()) {
            setLastDealBeforeTodayPrice(newInstr.getLastDealBeforeTodayPrice());
        }

        if (newInstr.getQuotes().size() == 0) return;

        List<QuoteHolder> newQuotesList = newInstr.getQuotes();

        for (QuoteHolder newQuote : newQuotesList) {
            QuoteHolder quote = getQuote(newQuote.getPrice(), newQuote.getType());
            if (quote != null) {
                if (newQuote.getQty() != 0) {
                    quote.setQty(newQuote.getQty());
                } else {
                    quotes.remove(quote);
                }
            } else if (newQuote.getQty() != 0) {
                quotes.add(newQuote);
            }
        }

        QuoteHolder newBestBuy = null;
        QuoteHolder newBestSell = null;

        if (quotes.size() == 0) {
            clearBestPrcVols();
            return;
        }

        for (QuoteHolder q : quotes) {
            if (q.getType() == QuoteHolder.Type.SELL) {
                if (isSwap()) {
                    if (newBestSell == null || q.getPrice() > newBestSell.getPrice()) {
                        newBestSell = q;
                    }
                } else {
                    if (newBestSell == null || q.getPrice() < newBestSell.getPrice()) {
                        newBestSell = q;
                    }
                }
            } else if (q.getType() == QuoteHolder.Type.BUY) {
                if (isSwap()) {
                    if (newBestBuy == null || q.getPrice() < newBestBuy.getPrice()) {
                        newBestBuy = q;
                    }
                } else {
                    if (newBestBuy == null || q.getPrice() > newBestBuy.getPrice()) {
                        newBestBuy = q;
                    }
                }
            }
        }

        if (newBestBuy != null &&
                newBestBuy.getQty() != 0 &&
                (newBestBuy.getPrice() != 0.0D || isSwap())) {

            setBestBuyPrice(newBestBuy.getPrice());
            setBestBuyVolume(newBestBuy.getQty());

            //also change newInstr's bestPrice
            newInstr.setBestBuyPrice(newBestBuy.getPrice());
            newInstr.setBestBuyVolume(newBestBuy.getQty());
        } else {
            clearBestBuyPrcVol();
        }

        if (newBestSell != null &&
                newBestSell.getQty() != 0 &&
                (newBestSell.getPrice() != 0.0D || isSwap())) {
            setBestSellPrice(newBestSell.getPrice());
            setBestSellVolume(newBestSell.getQty());

            //also change newInstr's bestPrice
            newInstr.setBestSellPrice(newBestSell.getPrice());
            newInstr.setBestSellVolume(newBestSell.getQty());
        } else {
            clearBestSellPrcVol();
        }
    }

    public void updateSecurity(InstrHolder newInstr) {

        if (newInstr.hasSymbol()) {
            setSymbol(newInstr.getSymbol());
        }
//        if (newInstr.hasTimeInForces()) {
//            setTimeInForces(newInstr.getTimeInForces());
//        }
//        if (newInstr.hasTags()) {
//            setTags(newInstr.getTags());
//        }
//        if (newInstr.hasFullName()) {
//            setTags(newInstr.getFullName());
//        }
//        if (newInstr.hasOrderTypes()) {
//            setOrderTypes(newInstr.getOrderTypes());
//        }
        if (newInstr.hasOrderSides()) {
            setOrderSides(newInstr.getOrderSides());
        }
//        if (newInstr.hasExpectDateAllowed()) {
//            setExpectDateAllowed(newInstr.getExpectDateAllowed());
//        }
//        if (newInstr.hasMaxExpectDateInDays()) {
//            setMaxExpectDateInDays(newInstr.getMaxExpectDateInDays());
//        }
//        if (newInstr.hasSwapSet()) {
//            setSwap(newInstr.isSwap());
//        }
//        if (newInstr.hasNearLegSymbol()) {
//            setNearLegSymbol(newInstr.getNearLegSymbol());
//        }
//        if (newInstr.hasFarLegSymbol()) {
//            setFarLegSymbol(newInstr.getFarLegSymbol());
//        }
        if (newInstr.hasLot()) {
            setLot(newInstr.getLot());
        }
        if (newInstr.hasNin()) {
            setNin(newInstr.getNin());
        }
//        if (newInstr.hasNominal()) {
//            setNominal(newInstr.getNominal());
//        }
//        if (newInstr.hasMaxQty()) {
//            setMaxQty(newInstr.getMaxQty());
//        }
        if (newInstr.hasMinQty()) {
            setMinQty(newInstr.getMinQty());
        }
        if (newInstr.hasDevLimAvgPrc()) {
            setDevLimitAvgPrc(newInstr.getDevLimitAvgPrc());
        }
        if (newInstr.hasDevLimLastDealPrc()) {
            setDevLimitLastDealPrc(newInstr.getDevLimitLastDealPrc());
        }
//        if (newInstr.hasDevLimMarketPrc()) {
//            setDevLimitMarketPrc(newInstr.getDevLimitMarketPrc());
//        }
        if (newInstr.hasPriceStep()) {
            setPriceStep(newInstr.getPriceStep());
        }
        if (newInstr.hasPricePrecision()) {
            setPricePrecision(newInstr.getPricePrecision());
        }
        if (newInstr.hasMarketType()) {
            setMarketType(newInstr.getMarketType());
        }
    }

    public void updateSecurityStatus(InstrHolder newInstr) {
        if (newInstr.hasCurrSessionId()) {
            if (hasCurrSessionId() && !newInstr.getCurrSessionId().equals(getCurrSessionId())) {
                clearAll();
            }
            setCurrSessionId(newInstr.getCurrSessionId());
        }
        if (newInstr.hasTradeCondition()) {
            setTradeCondition(newInstr.getTradeCondition());
        }
    }

    private void clearBestPrcVols() {
        clearBestSellPrcVol();
        clearBestBuyPrcVol();
    }

    private void clearBestSellPrcVol() {
        bestSellPrice = null;
        bestSellVolume = null;
    }

    private void clearBestBuyPrcVol() {
        bestBuyPrice = null;
        bestBuyVolume = null;
    }
}
