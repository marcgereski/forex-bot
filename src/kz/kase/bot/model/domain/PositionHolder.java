package kz.kase.bot.model.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ResourceBundle;

public class PositionHolder implements Holder {

    private final ObjectProperty<PosType> type = new SimpleObjectProperty<>();
    private boolean typeSet;
    //    private final LongProperty instrId = new SimpleLongProperty();
    private boolean instrIDSet;
    private final ObjectProperty<Long> accId = new SimpleObjectProperty<>();
    private boolean accIDSet;
    private final ObjectProperty<String> symbol = new SimpleObjectProperty<>();
    private boolean symbolSet;
    private final ObjectProperty<String> extCode = new SimpleObjectProperty<>();
    private boolean extCodeSet;
    private final ObjectProperty<String> account = new SimpleObjectProperty<>();
    private boolean accountSet;
    private final ObjectProperty<Long> initial = new SimpleObjectProperty<>();
    private boolean initialSet;
    private final ObjectProperty<Double> current = new SimpleObjectProperty<>();
    private boolean currentSet;
    private final ObjectProperty<Double> blocked = new SimpleObjectProperty<>();
    private boolean blockedSet;
    private final ObjectProperty<Double> planned = new SimpleObjectProperty<>();
    private boolean plannedSet;
    private final ObjectProperty<Integer> number = new SimpleObjectProperty<>();
    private boolean ratingSet;
    private final ObjectProperty<Double> rating = new SimpleObjectProperty<>();
    private boolean totalBothSet;
    private ObjectProperty<Double> totalBoth = new SimpleObjectProperty<>();
    private boolean totalSoldSet;
    private ObjectProperty<Double> totalSold = new SimpleObjectProperty<>();
    private boolean avgSellSet;
    private ObjectProperty<Double> avgSell = new SimpleObjectProperty<>();
    private boolean avgBuySet;
    private ObjectProperty<Double> avgBuy = new SimpleObjectProperty<>();
    private ObjectProperty<Integer> dealsQnt = new SimpleObjectProperty<>();
    private ObjectProperty<Long> net = new SimpleObjectProperty<>();
    private ObjectProperty<Double> netVol = new SimpleObjectProperty<>();
    private ObjectProperty<Double> guarantee = new SimpleObjectProperty<>();

    private long hash;

    public boolean hasGuarantee() {
        return guarantee.get() != null;
    }

    public void setGuarantee(double guarantee) {
        this.guarantee.set(guarantee);
    }

    public Double getGuarantee() {
        return guarantee.get();
    }

    public ObjectProperty<Double> guaranteeProperty() {
        return guarantee;
    }

    public void setNet(long net) {
        this.net.set(net);
    }

    public void setNetVol(double netVol) {
        this.netVol.set(netVol);
    }

    public boolean hasNet() {
        return net.get() != null;
    }

    public boolean hasNetVol() {
        return netVol.get() != null;
    }

    public Long getNet() {
        return net.get();
    }

    public ObjectProperty<Long> netProperty() {
        return net;
    }

    public Double getNetVol() {
        return netVol.get();
    }

    public ObjectProperty<Double> netVolProperty() {
        return netVol;
    }

    public boolean hasDealsQnt() {
        return dealsQnt.get() != null;
    }

    public void setDealsQnt(int dealsQnt) {
        this.dealsQnt.set(dealsQnt);
    }

    public Integer getDealsQnt() {
        return dealsQnt.get();
    }

    public ObjectProperty<Integer> dealsQntProperty() {
        return dealsQnt;
    }

    public void setTotalBoth(double totalBoth) {
        this.totalBoth.set(totalBoth);
        totalBothSet = true;
    }

    public void setTotalSold(double totalSold) {
        this.totalSold.set(totalSold);
        totalSoldSet = true;
    }

    public void clearStatistics() {
        totalBoth.set(0D);
        totalSold.set(0D);
        avgSell.set(0D);
        avgBuy.set(0D);
        dealsQnt.set(0);
        net.set(0L);
        netVol.set(0D);
    }

    public Double getTotalBoth() {
        return totalBoth.get();
    }

    public ObjectProperty<Double> totalBothProperty() {
        return totalBoth;
    }

    public Double getTotalSold() {
        return totalSold.get();
    }

    public ObjectProperty<Double> totalSoldProperty() {
        return totalSold;
    }

    public boolean hasTotalBoth() {
        return totalBothSet;
    }

    public boolean hasTotalSold() {
        return totalSoldSet;
    }

    public void setRating(double rating) {
        this.rating.set(rating);
        ratingSet = true;
    }

    public ObjectProperty<Double> ratingProperty() {
        return rating;
    }

    public Double getRating() {
        return rating.get();
    }

    public boolean hasRating() {
        return ratingSet;
    }


    public ObjectProperty<Double> evalProperty() {
        return rating;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public Integer getNumber() {
        return number.get();
    }

    public ObjectProperty<Integer> numberProperty() {
        return number;
    }

    public void setAccId(long accId) {
        this.accId.set(accId);
        accIDSet = true;
    }

    public Long getAccId() {
        return accId.get();
    }

    public ObjectProperty<Long> accIdProperty() {
        return accId;
    }

    public boolean hasAccIDSet() {
        return accIDSet;
    }

    public PosType getType() {
        return type.get();
    }

    public ObjectProperty<PosType> typeProperty() {
        return type;
    }

    public void setType(PosType type) {
        this.type.set(type);
        typeSet = true;
    }

/*
    public long getInstrId() {
        return instrId.get();
    }

    public LongProperty instrIdProperty() {
        return instrId;
    }

    public void setInstrId(long instrId) {
        this.instrId.set(instrId);
        instrIDSet = true;
    }
*/

    public String getSymbol() {
        return symbol.get();
    }

    public ObjectProperty<String> symbolProperty() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol.set(symbol);
        symbolSet = true;
    }

    public String getExtCode() {
        return extCode.get();
    }

    public ObjectProperty<String> extCodeProperty() {
        return extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode.set(extCode);
        extCodeSet = true;
        hash += extCode.hashCode();
    }

    public String getAccount() {
        return account.get();
    }

    public ObjectProperty<String> accountProperty() {
        return account;
    }

    public void setAccount(String account) {
        this.account.set(account);
        accountSet = true;
        hash = +account.hashCode();
    }

    public Long getInitial() {
        return initial.get();
    }

    public ObjectProperty<Long> initialProperty() {
        return initial;
    }

    public void setInitial(long initial) {
        this.initial.set(initial);
        initialSet = true;
    }

    public Double getCurrent() {
        return current.get();
    }

    public ObjectProperty<Double> currentProperty() {
        return current;
    }

    public void setCurrent(double current) {
        this.current.set(current);
        currentSet = true;
    }

    public Double getBlocked() {
        return blocked.get();
    }

    public ObjectProperty<Double> blockedProperty() {
        return blocked;
    }

    public void setBlocked(double blocked) {
        this.blocked.set(blocked);
        blockedSet = true;
    }

    public Double getPlanned() {
        return planned.get();
    }

    public ObjectProperty<Double> plannedProperty() {
        return planned;
    }

    public void setPlanned(double planned) {
        this.planned.set(planned);
        plannedSet = true;
    }

    public ObjectProperty<Double> avgSellProperty() {
        return avgSell;
    }

    public ObjectProperty<Double> avgBuyProperty() {
        return avgBuy;
    }

    public void setAvgBuy(double avgPrice) {
        avgBuy.set(avgPrice);
        avgBuySet = true;
//        avgBuy.set(qty > 0 ? cashQty / qty : 0);
    }

    public void setAvgSell(double avgPrice) {
        avgSell.set(avgPrice);
        avgSellSet = true;
//        avgSell.set(qty > 0 ? cashQty / qty : 0);
    }

    public Double getAvgSell() {
        return avgSell.get();
    }

    public Double getAvgBuy() {
        return avgBuy.get();
    }

    public boolean hasAvgBuy() {
        return avgBuySet;
    }

    public boolean hasAvgSell() {
        return avgSellSet;
    }

    public boolean hasType() {
        return typeSet;
    }

    public boolean hasInstrId() {
        return instrIDSet;
    }

    public boolean hasSymbol() {
        return symbolSet;
    }

    public boolean hasExtCode() {
        return extCodeSet;
    }

    public boolean hasAccount() {
        return accountSet;
    }

    public boolean hasInitial() {
        return initialSet;
    }

    public boolean hasCurrent() {
        return currentSet;
    }

    public boolean hasBlocked() {
        return blockedSet;
    }

    public boolean hasPlanned() {
        return plannedSet;
    }

//    public boolean hasAvgBuy() { return avgBuy; }

/*    public boolean hasAvgSell() {
        return avgSell != null;
    }*/

    public void update(PositionHolder newPos) {
        if (newPos.hasCurrent()) {
            setCurrent(newPos.getCurrent());
        }
        if (newPos.hasBlocked()) {
            setBlocked(newPos.getBlocked());
        }
        if (newPos.hasInitial()) {
            setInitial(newPos.getInitial());
        }
        if (newPos.hasPlanned()) {
            setPlanned(newPos.getPlanned());
        }
        if (newPos.hasRating()) {
            setRating(newPos.getRating());
        }
        if (newPos.hasTotalBoth()) {
            setTotalBoth(newPos.getTotalBoth());
        }
        if (newPos.hasTotalSold()) {
            setTotalSold(newPos.getTotalSold());
        }
        if (newPos.hasAvgBuy()) {
            setAvgBuy(newPos.getAvgBuy());
        }
        if (newPos.hasAvgSell()) {
            setAvgSell(newPos.getAvgSell());
        }
        if (newPos.hasDealsQnt()) {
            setDealsQnt(newPos.getDealsQnt());
        }
        if (newPos.hasNet()) {
            setNet(newPos.getNet());
        }
        if (newPos.hasNetVol()) {
            setNetVol(newPos.getNetVol());
        }
        if (newPos.hasGuarantee()) {
            setGuarantee(newPos.getGuarantee());
        }
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();

        if (getType() == PosType.INSTRUMENT) {
            buf.append("\tPosition (symbol: ").append(symbol).append("):").append("\n");
        } else {
            buf.append("\tMoney position:").append("\n");
        }

        if (current.getValue() != null) {
            buf.append("\t\tpos: ").append(current).append("\n");
        }
        if (blocked.getValue() != null) {
            buf.append("\t\tblocked: ").append(blocked).append("\n");
        }
        if (initial.getValue() != null) {
            buf.append("\t\tinitial: ").append(initial).append("\n");
        }
        if (planned.getValue() != null) {
            buf.append("\t\tplanned: ").append(planned).append("\n");
        }

        return buf.toString();
    }

    public String getString(ResourceBundle bundle) {
        StringBuilder buf = new StringBuilder();

        if (getType() == PosType.INSTRUMENT) {
            buf.append(symbol.getValue()).append(":");
        }
        if (account.getValue() != null) {
            buf.append(account.getValue()).append(":");
        }
        if (extCode.getValue() != null) {
            buf.append(extCode.getValue()).append(":");
        }
        if (current.getValue() != null) {
            buf.append(current.getValue()).append(":");
        }
        if (blocked.getValue() != null) {
            buf.append(blocked.getValue()).append(":");
        }
        if (initial.getValue() != null) {
            buf.append(initial.getValue()).append(":");
        }
        if (planned.getValue() != null) {
            buf.append(planned.getValue()).append(":");
        }
        if (type.getValue() != null) {
            buf.append(bundle.getString("position.type." + type.getValue().toString().toUpperCase()));
        }

        return buf.toString();
    }

    public static enum PosType {
        INSTRUMENT, PosType, MONEY
    }

    @Override
    public String getKey() {
        return getSymbol();
    }

/*    @Override
    public boolean equals(Object o) {
        if (o instanceof PositionHolder) {
            PositionHolder p = (PositionHolder) o;
            if (extCode.get() != null && account.get() != null &&
                    p.getAccount() != null && p.getExtCode() != null) {
                return extCode.get().equals(p.getExtCode()) && account.get().equals(p.getAccount());
            }
        }
        return false;
    }*/

    @Override
    public boolean equals(Object o) {
        if (o instanceof PositionHolder) {
            PositionHolder p = (PositionHolder) o;
            return p.hash == hash;
        }
        return false;
    }

}