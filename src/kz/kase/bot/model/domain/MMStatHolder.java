package kz.kase.bot.model.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

public class MMStatHolder implements Holder {

    //    private final ObjectProperty<Long> liabilityId = new SimpleObjectProperty<>();
//    private boolean hasLiabilityId;
    //    private final ObjectProperty<Long> sessionId = new SimpleObjectProperty<>();
//    private boolean hasSessionId;
    private final ObjectProperty<String> sessionSerial = new SimpleObjectProperty<>();
    private boolean hasSessionSerial;

    private final ObjectProperty<String> instrSymbol = new SimpleObjectProperty<>();
    private boolean hasInstrSymbol;

    //    private final ObjectProperty<Long> instrId = new SimpleObjectProperty<>();
//    private boolean hasInstrId;
    private final ObjectProperty<Double> buyPrice = new SimpleObjectProperty<>();
    private boolean isBuyPriceSet;
    private final ObjectProperty<Long> buyQty = new SimpleObjectProperty<>();
    private boolean isBuyQtySet;
    private final ObjectProperty<Double> buyVol = new SimpleObjectProperty<>();
    private boolean isBuyVolSet;
    private final ObjectProperty<Double> sellPrice = new SimpleObjectProperty<>();
    private boolean isSellPriceSet;
    private final ObjectProperty<Long> sellQty = new SimpleObjectProperty<>();
    private boolean isSellQtySet;
    private final ObjectProperty<Double> sellVol = new SimpleObjectProperty<>();
    private boolean isSellVolSet;
    private final ObjectProperty<Double> spread = new SimpleObjectProperty<>();
    private boolean isSpreadSet;
    private final ObjectProperty<Double> spreadPerc = new SimpleObjectProperty<>();
    private boolean isSpreadPercSet;
    private List<MMWarningHolder> mmWarnings = new ArrayList<>();
    private boolean hasMmWarnings;


    /* public Long getSessionId() {
         return sessionId.get();
     }

     public void setSessionId(long sessionId) {
         this.sessionId.set(sessionId);
         hasSessionId = true;
     }

     public ObjectProperty sessionIdProperty() {
         return sessionId;
     }

     public Long getInstrId() {
         return instrId.get();
     }

     public void setInstrId(long instrId) {
         this.instrId.set(instrId);
         hasInstrId = true;
     }

     public ObjectProperty instrIdProperty() {
         return instrId;
     }
 */
    public Double getBuyPrice() {
        return buyPrice.get();
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice.set(buyPrice);
        isBuyPriceSet = true;
    }

    public ObjectProperty buyPriceProperty() {
        return buyPrice;
    }

    public Long getBuyQty() {
        return buyQty.get();
    }

    public void setBuyQty(Long buyQty) {
        this.buyQty.set(buyQty);
        isBuyQtySet = true;
    }

    public ObjectProperty buyQtyProperty() {
        return buyQty;
    }

    public Double getBuyVol() {
        return buyVol.get();
    }

    public void setBuyVol(Double buyVol) {
        this.buyVol.set(buyVol);
        isBuyVolSet = true;
    }

    public ObjectProperty buyVolProperty() {
        return buyVol;
    }

    public Double getSellPrice() {
        return sellPrice.get();
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice.set(sellPrice);
        isSellPriceSet = true;
    }

    public ObjectProperty sellPriceProperty() {
        return sellPrice;
    }

    public Long getSellQty() {
        return sellQty.get();
    }

    public void setSellQty(Long sellQty) {
        this.sellQty.set(sellQty);
        isSellQtySet = true;
    }

    public ObjectProperty sellQtyProperty() {
        return sellQty;
    }

    public Double getSellVol() {
        return sellVol.get();
    }

    public void setSellVol(Double sellVol) {
        this.sellVol.set(sellVol);
        isSellVolSet = true;
    }

    public ObjectProperty sellVolProperty() {
        return sellVol;
    }

    public Double getSpread() {
        return spread.get();
    }

    public void setSpread(Double spread) {
        this.spread.set(spread);
        isSpreadSet = true;
    }

    public ObjectProperty spreadProperty() {
        return spread;
    }

    public Double getSpreadPerc() {
        return spreadPerc.get();
    }

    public void setSpreadPerc(Double spreadPerc) {
        this.spreadPerc.set(spreadPerc);
        isSpreadPercSet = true;
    }

    public ObjectProperty spreadPercProperty() {
        return spreadPerc;
    }

    public List<MMWarningHolder> getMmWarnings() {
        return mmWarnings;
    }

    public void setMmWarnings(List<MMWarningHolder> mmWarnings) {
//        this.mmWarnings.addAll(mmWarnings);
        this.mmWarnings = mmWarnings;
        hasMmWarnings = true;
    }


    public void update(MMStatHolder mmStatUpdate) {
        if (mmStatUpdate != null) {
            if (mmStatUpdate.hasBuyPrice()) {
                setBuyPrice(mmStatUpdate.getBuyPrice());
            }
            if (mmStatUpdate.hasBuyQty()) {
                setBuyQty(mmStatUpdate.getBuyQty());
            }
            if (mmStatUpdate.hasBuyVol()) {
                setBuyVol(mmStatUpdate.getBuyVol());
            }
            if (mmStatUpdate.hasSellPrice()) {
                setSellPrice(mmStatUpdate.getSellPrice());
            }
            if (mmStatUpdate.hasSellQty()) {
                setSellQty(mmStatUpdate.getSellQty());
            }
            if (mmStatUpdate.hasSellVol()) {
                setSellVol(mmStatUpdate.getSellVol());
            }
            if (mmStatUpdate.hasSpread()) {
                setSpread(mmStatUpdate.getSpread());
            }
            if (mmStatUpdate.hasSpreadPerc()) {
                setSpreadPerc(mmStatUpdate.getSpreadPerc());
            }
            if (mmStatUpdate.hasMmWarnings()) {
                setMmWarnings(mmStatUpdate.getMmWarnings());
            }
        }
    }

//    public boolean hasLiabilityId() {
//        return hasLiabilityId;
//    }

    /*   public boolean hasSessionId() {
           return hasSessionId;
       }

       public boolean hasInstrId() {
           return hasInstrId;
       }
   */
    public boolean hasBuyPrice() {
        return isBuyPriceSet;
    }

    public boolean hasBuyQty() {
        return isBuyQtySet;
    }

    public boolean hasBuyVol() {
        return isBuyVolSet;
    }

    public boolean hasSellPrice() {
        return isSellPriceSet;
    }

    public boolean hasSellQty() {
        return isSellQtySet;
    }

    public boolean hasSellVol() {
        return isSellVolSet;
    }

    public boolean hasSpread() {
        return isSpreadSet;
    }

    public boolean hasSpreadPerc() {
        return isSpreadPercSet;
    }

    public boolean hasMmWarnings() {
        return hasMmWarnings;
    }

    public String getInstrSymbol() {
        return instrSymbol.get();
    }

    public void setInstrSymbol(String instrSymbol) {
        this.instrSymbol.set(instrSymbol);
        hasInstrSymbol = true;
    }

    public String getSessionSerial() {
        return sessionSerial.get();
    }

    public ObjectProperty<String> sessionSerialProperty() {
        return sessionSerial;
    }

    public void setSessionSerial(String sessionSerial) {
        this.sessionSerial.set(sessionSerial);
        hasSessionSerial = true;
    }

    public boolean hasInstrSerial() {
        return hasInstrSymbol;
    }

    public boolean hasSessionSerial() {
        return hasSessionSerial;
    }


    public ObjectProperty<String> instrSymbolProperty() {
        return instrSymbol;
    }


    @Override
    public String getKey() {
        return getInstrSymbol();
    }
}


