package kz.kase.bot.model.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MMLiabilityHolder implements Holder {

//    private final ObjectProperty<Long> instrId = new SimpleObjectProperty<>();
//    private boolean hasInstrId;

    private final ObjectProperty<String> instrSymbol = new SimpleObjectProperty<>();
    private final ObjectProperty<Double> maxSpread = new SimpleObjectProperty<>();
    private boolean hasMaxSpread;
    private final ObjectProperty<Double> maxSpreadPercent = new SimpleObjectProperty<>();
    private boolean hasMaxSpreadPercent;
    private final ObjectProperty<Integer> minQty = new SimpleObjectProperty<>();
    private boolean hasMinQty;
    private final ObjectProperty<Double> minVol = new SimpleObjectProperty<>();
    private boolean hasMinVol;
    private final ObjectProperty<Double> maxLastDayPricePercDev = new SimpleObjectProperty<>();
    private boolean hasMaxLastDayPricePercDev;

/*
    public Long getInstrId() {
        return instrId.get();
    }

    public void setInstrId(long id) {
        this.instrId.set(id);
        hasInstrId = true;
    }

    public ObjectProperty instrIdProperty() {
        return instrId;
    }
*/

    public String getInstrSymbol() {
        return instrSymbol.get();
    }

    public ObjectProperty<String> instrSymbolProperty() {
        return instrSymbol;
    }

    public void setInstrSymbol(String instrSymbol) {
        this.instrSymbol.set(instrSymbol);
    }

    public Double getMaxSpread() {
        return maxSpread.get();
    }

    public void setMaxSpread(Double maxSpread) {
        this.maxSpread.set(maxSpread);
        hasMaxSpread = true;
    }

    public ObjectProperty maxSpreadProperty() {
        return maxSpread;
    }

    public Double getMaxSpreadPercent() {
        return maxSpreadPercent.get();
    }

    public void setMaxSpreadPercent(Double maxSpreadPercent) {
        this.maxSpreadPercent.set(maxSpreadPercent);
        hasMaxSpreadPercent = true;
    }

    public ObjectProperty maxSpreadPercentProperty() {
        return maxSpreadPercent;
    }

    public Integer getMinQty() {
        return minQty.get();
    }

    public void setMinQty(Integer minQty) {
        this.minQty.set(minQty);
        hasMinQty = true;
    }

    public ObjectProperty minQtyProperty() {
        return minQty;
    }

    public Double getMinVol() {
        return minVol.get();
    }

    public void setMinVol(Double minVol) {
        this.minVol.set(minVol);
        hasMinVol = true;
    }

    public ObjectProperty minVolProperty() {
        return minVol;
    }

    public Double getMaxLastDayPricePercDev() {
        return maxLastDayPricePercDev.get();
    }

    public ObjectProperty maxLastDayPricePercDevProperty() {
        return maxLastDayPricePercDev;
    }

    public void setMaxLastDayPricePercDev(Double maxLastDayPricePercDev) {
        this.maxLastDayPricePercDev.set(maxLastDayPricePercDev);
        hasMaxSpreadPercent = true;
    }

    public void update(MMLiabilityHolder newMmLiability) {

        if (newMmLiability != null) {
            if (hasMaxSpread()) {
                setMaxSpread(newMmLiability.getMaxSpread());
            }
            if (hasMaxSpreadPercent()) {
                setMaxSpreadPercent(newMmLiability.getMaxSpreadPercent());
            }
            if (hasMinQty()) {
                setMinQty(newMmLiability.getMinQty());
            }
            if (hasMinVol()) {
                setMinVol(newMmLiability.getMinVol());
            }
            if (hasMaxLastDayPricePercDev()) {
                setMaxLastDayPricePercDev(newMmLiability.getMaxLastDayPricePercDev());
            }
        }
    }

//    public boolean hasInstrId() {
//        return hasInstrId;
//    }

    public boolean hasMaxSpread() {
        return hasMaxSpread;
    }

    public boolean hasMaxSpreadPercent() {
        return hasMaxSpreadPercent;
    }

    public boolean hasMinQty() {
        return hasMinQty;
    }

    public boolean hasMinVol() {
        return hasMinVol;
    }

    public boolean hasMaxLastDayPricePercDev() {
        return hasMaxLastDayPricePercDev;
    }

    @Override
    public String getKey() {
        return getInstrSymbol();
    }
}
