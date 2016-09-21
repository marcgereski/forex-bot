package kz.kase.bot.fix;


import kz.kase.bot.model.domain.*;
import kz.kase.bot.storage.Storage;
import kz.kase.bot.storage.predicate.InstrByExtCode;
import kz.kase.bot.storage.predicate.TradeSessionBySymbol;
import kz.kase.fix.OrdRejReason;
import kz.kase.fix.OrderStatus;
import kz.kase.fix.SecurityRequestResult;
import kz.kase.fix.TradeCondition;
import kz.kase.fix.messages.*;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static kz.kase.fix.FixProtocol.FIELD_SECURITY_ID;

public class FixMessProcessor {
    private final static Logger log = Logger.getLogger(FixMessReceiver.class);
    private boolean debug;
    private Storage storage;

    public FixMessProcessor(boolean debug, Storage storage) {
        this.debug = debug;
        this.storage = storage;
    }

    public FixUpdate processAndStore(SecurityList data) {
        StringBuilder logBuf = new StringBuilder();
        Collection<InstrHolder> instrs = null;

        if (debug) {
            logBuf.append("\nGot security list:\n")
                    .append("\tref: ").append(data.getRef()).append("\n")
                    .append("\tstatus: ").append(data.getSecRequestResult()).append("\n");
        }

        if (data.getSecRequestResult() == SecurityRequestResult.VALID_REQUEST) {

            if (debug) {
                logBuf.append("\tentries (").append(data.getInstrumentsCount()).append("):\n");
            }

            int idx = 0;
            AtomicInteger lastNumber = new AtomicInteger(1);
            instrs = FixParserFactory.getInstance().parse(data);
            for (InstrHolder newInstr : instrs) {
                if (newInstr.getKey() != null) {
                    InstrHolder instr = storage.get(InstrHolder.class, newInstr.getKey());
                    if (instr != null) {
                        instr.updateSecurity(newInstr);
                        storage.put(instr);
                    } else {
                        newInstr.setNumber(lastNumber.getAndIncrement());
                        storage.put(newInstr, newInstr.getKey());
                    }

                    if (debug) {
                        logBuf.append("\t#").append(idx).append("\tid: ")
                                .append(newInstr.getKey()).append("\n");
                    }
                }

                idx++;
            }
        }

        if (debug) {
            log.info(logBuf);
        }
        return new FixUpdateImpl(data, instrs);
    }

    public FixUpdate processAndStore(SecurityStatus data) {

        StringBuilder logBuf = new StringBuilder();
        InstrHolder newInstr;
        if (debug) {
            logBuf.append("\nGot security status for instr:\n")
                    .append("\tref: ").append(data.getSymbol());
        }

        newInstr = FixParserFactory.getInstance().parse(data);
        if (newInstr.getKey() != null) {
            InstrHolder instr = storage.get(InstrHolder.class, newInstr.getKey());
            if (instr != null) {
                if (newInstr.getTradeCondition() == TradeCondition.Opened
                        && instr.hasCurrSessionId()
                        && newInstr.getCurrSessionId() <= instr.getCurrSessionId()) {
                    instr.clearAll();
                    List<TradeSessionHolder> oldSessions = storage.findAll(TradeSessionHolder.class,
                            new TradeSessionBySymbol(newInstr.getSymbol()));
                    if (oldSessions != null) {
                        for (TradeSessionHolder oldSess : oldSessions) {
                            storage.remove(TradeSessionHolder.class, oldSess.getKey());

                        }
                    }
                }
                instr.updateSecurityStatus(newInstr);
                storage.put(instr);
            }
        }

        if (newInstr.hasCurrSessionId()) {
            TradeSessionHolder newSes = new TradeSessionHolder();
            newSes.setInstrSymbol(newInstr.getSymbol());
            newSes.setNumber(newInstr.getCurrSessionId());
            TradeSessionHolder ses = storage.get(TradeSessionHolder.class, newSes.getKey());
            if (ses != null) {
                ses.update(newSes);
                storage.put(ses);
            } else {
                storage.put(newSes, newSes.getKey());
            }
        }

        if (debug) {
            log.info(logBuf);
        }
        return new FixUpdateImpl(data, newInstr);
    }

    public FixUpdate processAndStore(MDIncRefresh data) {
        StringBuilder logBuf = new StringBuilder();
        Collection<InstrHolder> newInstrs =
                FixParserFactory.getInstance().parseInstrument(data, storage);

        if (newInstrs != null) {
            logBuf.append("\nGot market data:\n");
            if (data.getRef() != null) {
                logBuf.append("\tref: ").append(data.getRef()).append("\n");
            }
            logBuf.append("\tentries (").
                    append(newInstrs.size()).
                    append("):").
                    append("\n");
//            newInstrs.forEach(i->i.getQuotes().forEach(logBuf::append));

            int idx = 0;
            for (InstrHolder newInstr : newInstrs) {
                if (newInstr.getKey() != null) {

                    logBuf.append("\t#").append(idx).append("\t").append(newInstr);
                    InstrHolder instr = storage.get(InstrHolder.class, newInstr.getKey());
                    if (instr != null) {
                        instr.updateMarketData(newInstr, QuickFixClient.FixServerType.Transactional);
                        storage.put(instr);
                    }
                }
                idx++;
            }

            Collection<TradeSessionHolder> newTradeSessions = FixParserFactory.getInstance().parseTradeSession(data);
            if (newTradeSessions != null) {
                for (TradeSessionHolder newTradeSes : newTradeSessions) {
                    if (newTradeSes.getKey() != null) {

                        TradeSessionHolder tradeSession = storage.get(TradeSessionHolder.class, newTradeSes.getKey());
                        if (tradeSession != null) {
                            tradeSession.update(newTradeSes);
                            storage.put(tradeSession);
                        } else {
                            storage.put(newTradeSes);
                        }
                    }
                }
            }
        }

        if (debug) {
            log.info(logBuf);
        }
        return new FixUpdateImpl(data, newInstrs);
    }

    public FixUpdate processAndStore(PositionReport report) {
        StringBuilder logBuf = new StringBuilder();
        if (debug) {
            logBuf.append("\nGot position report.").append("\n");
        }

        String extCode;
        if (report.isSetField(FIELD_SECURITY_ID)) {
            extCode = report.getSecurityId();
        } else {
            extCode = report.getCurrency();
        }
        InstrHolder instr = storage.findFirst(InstrHolder.class, new InstrByExtCode(extCode));
        String symbol = instr != null ?
                instr.getSymbol() : extCode;

        AccountHolder newAcc = FixParserFactory.getInstance().parseAcc(report, symbol);
        AccountHolder acc;
        if (newAcc != null) {
            if (newAcc.getName() != null) {
                acc = storage.get(AccountHolder.class, report.getAccount());
            } else {
                return null;
            }
            if (acc != null) {
                acc.update(newAcc);
                storage.put(acc);
            } else {
                storage.put(newAcc);
            }

            if (debug) {
                logBuf.append(newAcc);
                log.info(logBuf);
            }
        }
        return new FixUpdateImpl(report, newAcc);
    }

    public void logOrderStatus(ExecutionReport report, OrderHolder newOrder) {
        OrderStatus status = report.getOrderStatus();
        if (debug) {
            StringBuilder logBuf = new StringBuilder();

            if (status == OrderStatus.REJECTED) {
                logBuf.append("Got order report with status.").append(status).append("\n");
                OrdRejReason reason = report.getOrdRejReason();
                logBuf.append("\tstatus:").append(status).append("\n");
                logBuf.append("\treject reason: ").append(reason).append("\n");
            } else {
                if (newOrder != null) {
                    logBuf.append(newOrder);
                }
            }
            log.info("\n" + logBuf);
        }
    }
}
