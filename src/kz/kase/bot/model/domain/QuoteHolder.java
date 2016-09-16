package kz.kase.bot.model.domain;

public class QuoteHolder implements Comparable<QuoteHolder> {

    public static enum Type {BUY, SELL}

    private Double price;
    private Long qty;
    private Type type;
    private boolean isMyOrder = false;
    private boolean isMMOrder = false;
    private final String symbol;

    public QuoteHolder(double priceVal, long qtyVal, Type type, String instrSymbol) {
        setPrice(priceVal);
        setQty(qtyVal);
        setType(type);
        this.symbol = instrSymbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double val) {
        price = val;
    }

    public long getQty() {
        return qty;
    }

    public void setQty(long val) {
        qty = val;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isMyOrder() {
        return isMyOrder;
    }

    public String getInstrSymbol() {
        return symbol;
    }

    public void setMyOrder(boolean isMyOrder) {
        this.isMyOrder = isMyOrder;
    }

    public boolean isMMOrder() {
        return isMMOrder;
    }

    public void setMMOrder(boolean isMMOrder) {
        this.isMMOrder = isMMOrder;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(QuoteHolder o) {
        if (o == null) return 0;
        if (o.type != type) return 0;

        return Double.compare(price, o.price);
    }

    public String toString() {
        return "\nQuote :\n" +
                "symbol    :" + symbol + "\n" +
                "price     :" + price + "\n" +
                "qty       :" + qty + "\n" +
                "type      :" + type + "\n" +
                "my order  :" + isMMOrder + "\n" +
                "mm order  :" + isMMOrder + "\n";
    }
}
