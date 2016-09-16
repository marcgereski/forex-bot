package kz.kase.bot.model.domain;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class TradeSessionHolder implements Holder {

    private final ObjectProperty<String> instrSymbol = new SimpleObjectProperty<>();
    private boolean hasInstrSymbol;
    private ObjectProperty<Long> number = new SimpleObjectProperty<>();
    private boolean hasNumber;
    private final ObjectProperty<Double> lowPrice = new SimpleObjectProperty<>();
    private boolean hasLowPrice;
    private final ObjectProperty<Long> lowVolume = new SimpleObjectProperty<>();
    private boolean hasLowVol;
    private final ObjectProperty<Double> highPrice = new SimpleObjectProperty<>();
    private boolean hasHighPrice;
    private final ObjectProperty<Long> highVolume = new SimpleObjectProperty<>();
    private boolean hasHighVol;
    private final ObjectProperty<Double> openPrice = new SimpleObjectProperty<>();
    private boolean hasOpenPrice;
    private final ObjectProperty<Long> openVolume = new SimpleObjectProperty<>();
    private boolean hasOpenVol;
    private final ObjectProperty<Double> closePrice = new SimpleObjectProperty<>();
    private boolean hasClosePrice;
    private final ObjectProperty<Long> closeVolume = new SimpleObjectProperty<>();
    private boolean hasCloseVol;
    private final ObjectProperty<Double> avgPriceWeight = new SimpleObjectProperty<>();
    private boolean hasAvgPriceWeight;
    private final ObjectProperty<Long> dealsQtyTotal = new SimpleObjectProperty<>();
    private boolean hasDealsQtyTotal;
    private final ObjectProperty<Integer> dealQty = new SimpleObjectProperty<>();
    private boolean hasDealQty;
    private final ObjectProperty<Double> dealsVolume = new SimpleObjectProperty<>();
    private boolean hasDealsVolume;
    private final ObjectProperty<Double> lastPrice = new SimpleObjectProperty<>();
    private boolean hasLastPrice;
    private final ObjectProperty<Long> lastVol = new SimpleObjectProperty<>();
    private boolean hasLastVol;
    private final ObjectProperty<Double> deltaPrice = new SimpleObjectProperty<>();
    private boolean hasDeltaPrice;

    public void setNumber(long number) {
        this.number.set(number);
        hasNumber = true;
    }

    public long getNumber() {
        return number.get();
    }

    public ObjectProperty<Long> numberProperty() {
        return number;
    }

    public double getAvgPriceWeight() {
        return avgPriceWeight.get();
    }

    public ObjectProperty<Double> avgPriceWeightProperty() {
        return avgPriceWeight;
    }

    public int getDealQty() {
        return dealQty.get();
    }

    public ObjectProperty<Integer> dealQtyProperty() {
        return dealQty;
    }

    public Long getDealsQtyTotal() {
        return dealsQtyTotal.get();
    }

    public ObjectProperty<Long> dealsQtyTotalProperty() {
        return dealsQtyTotal;
    }

    public double getDealsVolume() {
        return dealsVolume.get();
    }

    public ObjectProperty<Double> dealsVolumeProperty() {
        return dealsVolume;
    }

    public void setDealsVolume(double dealsVolume) {
        this.dealsVolume.set(dealsVolume);
        hasDealsVolume = true;
    }

    public void setAvgPriceWeight(double avgPriceWeight) {
        this.avgPriceWeight.set(avgPriceWeight);
        hasAvgPriceWeight = true;
    }

    public void setDealQty(int dealQty) {
        this.dealQty.set(dealQty);
        hasDealQty = true;
    }

    public void setDealsQtyTotal(long dealsQtyTotal) {
        this.dealsQtyTotal.set(dealsQtyTotal);
        hasDealsQtyTotal = true;
    }

    public boolean hasNumber() {
        return hasNumber;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice.set(lowPrice);
        hasLowPrice = true;
    }

    public void setLowVolume(long lowVolume) {
        this.lowVolume.set(lowVolume);
        hasLowVol = true;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice.set(highPrice);
        hasHighPrice = true;
    }

    public void setHighVolume(long highVolume) {
        this.highVolume.set(highVolume);
        hasHighVol = true;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice.set(openPrice);
        hasOpenPrice = true;
    }

    public void setOpenVolume(long openVolume) {
        this.openVolume.set(openVolume);
        hasOpenVol = true;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice.set(closePrice);
        hasClosePrice = true;
    }

    public void setCloseVolume(long closeVolume) {
        this.closeVolume.set(closeVolume);
        hasCloseVol = true;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice.set(lastPrice);
        hasLastPrice = true;
    }

    public void setLastVol(long lastVol) {
        this.lastVol.set(lastVol);
        hasLastVol = true;
    }

    public void setDeltaPrice(double deltaPrice) {
        this.deltaPrice.set(deltaPrice);
        hasDeltaPrice = true;
    }

    public double getLowPrice() {
        return lowPrice.get();
    }

    public ObjectProperty<Double> lowPriceProperty() {
        return lowPrice;
    }

    public long getLowVolume() {
        return lowVolume.get();
    }

    public ObjectProperty<Long> lowVolumeProperty() {
        return lowVolume;
    }

    public double getHighPrice() {
        return highPrice.get();
    }

    public ObjectProperty<Double> highPriceProperty() {
        return highPrice;
    }

    public long getHighVolume() {
        return highVolume.get();
    }

    public ObjectProperty<Long> highVolumeProperty() {
        return highVolume;
    }

    public double getOpenPrice() {
        return openPrice.get();
    }

    public ObjectProperty<Double> openPriceProperty() {
        return openPrice;
    }

    public long getOpenVolume() {
        return openVolume.get();
    }

    public ObjectProperty<Long> openVolumeProperty() {
        return openVolume;
    }

    public double getClosePrice() {
        return closePrice.get();
    }

    public ObjectProperty<Double> closePriceProperty() {
        return closePrice;
    }

    public long getCloseVolume() {
        return closeVolume.get();
    }

    public ObjectProperty<Long> closeVolumeProperty() {
        return closeVolume;
    }

    public double getLastPrice() {
        return lastPrice.get();
    }

    public ObjectProperty<Double> lastPriceProperty() {
        return lastPrice;
    }

    public Long getLastVol() {
        return lastVol.get();
    }

    public ObjectProperty lastVolProperty() {
        return lastVol;
    }

    public double getDeltaPrice() {
        return deltaPrice.get();
    }

    public ObjectProperty<Double> deltaPriceProperty() {
        return deltaPrice;
    }

    public boolean hasLowPrice() {
        return hasLowPrice;
    }

    public boolean hasLowVol() {
        return hasLowVol;
    }

    public boolean hasHighPrice() {
        return hasHighPrice;
    }

    public boolean hasHighVol() {
        return hasHighVol;
    }

    public boolean hasOpenPrice() {
        return hasOpenPrice;
    }

    public boolean hasOpenVol() {
        return hasOpenVol;
    }

    public boolean hasClosePrice() {
        return hasClosePrice;
    }

    public boolean hasCloseVol() {
        return hasCloseVol;
    }

    public boolean hasDealQty() {
        return hasDealQty;
    }

    public boolean hasDealsQtyTotal() {
        return hasDealsQtyTotal;
    }

    public boolean hasDealsVolume() {
        return hasDealsVolume;
    }

    public boolean hasLastPrice() {
        return hasLastPrice;
    }

    public boolean hasLastVol() {
        return hasLastVol;
    }

    public boolean hasDeltaPrice() {
        return hasDeltaPrice;
    }

    public boolean hasAvgPriceWeight() {
        return hasAvgPriceWeight;
    }

    public void update(TradeSessionHolder newSession) {
        if (newSession.hasClosePrice()) {
            setClosePrice(newSession.getClosePrice());
        }
        if (newSession.hasCloseVol()) {
            setCloseVolume(newSession.getCloseVolume());
        }
        if (newSession.hasOpenPrice()) {
            setOpenPrice(newSession.getOpenPrice());
        }
        if (newSession.hasOpenVol()) {
            setOpenVolume(newSession.getOpenVolume());
        }
        if (newSession.hasLowPrice()) {
            setLowPrice(newSession.getLowPrice());
        }
        if (newSession.hasLowVol()) {
            setLowVolume(newSession.getLowVolume());
        }
        if (newSession.hasHighPrice()) {
            setHighPrice(newSession.getHighPrice());
        }
        if (newSession.hasHighVol()) {
            setHighVolume(newSession.getHighVolume());
        }
        if (newSession.hasDealQty()) {
            setDealQty(newSession.getDealQty());
        }
        if (newSession.hasDealsQtyTotal()) {
            setDealsQtyTotal(newSession.getDealsQtyTotal());
        }
        if (newSession.hasDealsVolume()) {
            setDealsVolume(newSession.getDealsVolume());
        }
        if (newSession.hasLastPrice()) {
            setLastPrice(newSession.getLastPrice());
        }
        if (newSession.hasLastVol()) {
            setLastVol(newSession.getLastVol());
        }
        if (newSession.hasDeltaPrice()) {
            setDeltaPrice(newSession.getDeltaPrice());
        }
        if (newSession.hasAvgPriceWeight()) {
            setAvgPriceWeight(newSession.getAvgPriceWeight());
        }

    }

    public String getInstrSymbol() {
        return instrSymbol.get();
    }

    public ObjectProperty<String> instrSymbolProperty() {
        return instrSymbol;
    }

    public void setInstrSymbol(String instrSymbol) {
        this.instrSymbol.set(instrSymbol);
        hasInstrSymbol = true;
    }

    public boolean hasInstrSymbol() {
        return hasInstrSymbol;
    }

    @Override
    public String getKey() {
        return getInstrSymbol() + getNumber();
    }
}
