package kz.kase.bot.fix;

import kz.kase.bot.model.domain.*;
import kz.kase.bot.storage.Storage;
import kz.kase.fix.MDEntryType;
import kz.kase.fix.messages.*;

import java.util.Collection;
import java.util.List;


public interface FixParser {

    public AccountHolder parseAcc(PositionReport report, String symbol);

    public PositionHolder parsePositions(PositionReport report, String symbol);

    public Collection<InstrHolder> parse(SecurityList list);

    public Collection<InstrHolder> parse(MDIncRefresh md);

    public Collection<InstrHolder> parseInstrument(MDIncRefresh md, Storage storage);

    public Collection<TradeSessionHolder> parseTradeSession(MDIncRefresh md);

    public InstrHolder parse(MDFullSnapshotRefresh md);

    public InstrHolder parse(SecurityStatus ss);

    public OrderHolder parseOrder(ExecutionReport report);

    public QuoteHolder.Type convert(MDEntryType type);

    public List<MMLiabilityHolder> parse(MMLiabilityList mmList);

    public MMStatHolder parse(MMStat mmStat);

    public AccountHolder parseAcc(DayPositionReport report);

    public List<DayPositionHolder> parseDayPositions(DayPositionReport report);

    public List<IndicativeQuoteHolder> parse(IndicativeQuoteList mmList);

    public NextCalendar parse(CalendarResponse resp);
}