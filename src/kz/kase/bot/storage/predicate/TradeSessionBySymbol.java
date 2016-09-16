package kz.kase.bot.storage.predicate;


import kz.kase.bot.model.domain.TradeSessionHolder;

public class TradeSessionBySymbol implements Predicate<TradeSessionHolder>{

    private String symbol;

    public TradeSessionBySymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean matches(TradeSessionHolder session) {
        return session.getInstrSymbol().equals(symbol);
    }
}
