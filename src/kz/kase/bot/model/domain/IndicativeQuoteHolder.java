package kz.kase.bot.model.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Date;

public class IndicativeQuoteHolder implements Holder {

    private ObjectProperty<Integer> number = new SimpleObjectProperty<>();
    private ObjectProperty<Long> id = new SimpleObjectProperty<>();
    private boolean hasId;
    private ObjectProperty<String> instrSymbol = new SimpleObjectProperty<>();
    private ObjectProperty<String> memberName = new SimpleObjectProperty<>();
    private ObjectProperty<Double> buyPrice = new SimpleObjectProperty<>();
    private boolean hasBuyPrice;
    private ObjectProperty<Double> sellPrice = new SimpleObjectProperty<>();
    private boolean hasSellPrice;
    private ObjectProperty<Long> buyVolume = new SimpleObjectProperty<>();
    private boolean hasBuyVolume;
    private ObjectProperty<Long> sellVolume = new SimpleObjectProperty<>();
    private boolean hasSellVolume;
    private ObjectProperty<String> status = new SimpleObjectProperty<>();
    private boolean hasStatus;
    private ObjectProperty<Date> creationTime = new SimpleObjectProperty<>();
    private boolean hasCreationTime;
    private ObjectProperty<String> comment = new SimpleObjectProperty<>();
    private boolean hasComment;

    public void setNumber(int number) {
        this.number.set(number);
    }

    public int getNumber() {
        return number.get();
    }

    public ObjectProperty numberProperty() {
        return number;
    }


    public void setId(long id) {
        this.id.set(id);
        hasId = true;
    }

    public ObjectProperty<Long> idProperty() {
        return id;
    }

    public Long getId() {
        return id.get();
    }

    public void setInstrSymbol(String instrSymbol) {
        this.instrSymbol.set(instrSymbol);
    }

    public ObjectProperty<String> instrSymbolProperty() {
        return instrSymbol;
    }

    public String getInstrSymbol() {
        return instrSymbol.get();
    }

    public void setMemberName(String memberName) {
        this.memberName.set(memberName);
    }

    public ObjectProperty<String> memberNameProperty() {
        return memberName;
    }

    public String getMemberName() {
        return memberName.get();
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice.set(buyPrice);
        hasBuyPrice = true;
    }

    public ObjectProperty<Double> buyPriceProperty() {
        return buyPrice;
    }

    public double getBuyPrice() {
        return buyPrice.get();
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice.set(sellPrice);
        hasSellPrice = true;
    }

    public ObjectProperty<Double> sellPriceProperty() {
        return sellPrice;
    }

    public double getSellPrice() {
        return sellPrice.get();
    }

    public void setBuyVolume(long buyVolume) {
        this.buyVolume.set(buyVolume);
        hasBuyVolume = true;
    }

    public ObjectProperty<Long> buyVolumeProperty() {
        return buyVolume;
    }

    public Long getBuyVolume() {
        return buyVolume.get();
    }

    public void setSellVolume(long sellVolume) {
        this.sellVolume.set(sellVolume);
        hasSellVolume = true;
    }

    public ObjectProperty<Long> sellVolumeProperty() {
        return sellVolume;
    }

    public long getSellVolume() {
        return sellVolume.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
        hasStatus = true;
    }

    public ObjectProperty<String> statusProperty() {
        return status;
    }

    public String getStatus() {
        return status.get();
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime.set(creationTime);
        hasCreationTime = true;
    }

    public ObjectProperty<Date> creationTimeProperty() {
        return creationTime;
    }

    public Date getCreationTime() {
        return creationTime.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
        hasComment = true;
    }

    public ObjectProperty<String> commentProperty() {
        return comment;
    }

    public String getComment() {
        return comment.get();
    }

    public void update(IndicativeQuoteHolder newIndiQuote) {

        if (newIndiQuote != null) {

            if (newIndiQuote.hasId()) {
                setId(newIndiQuote.getId());
            }
            if (newIndiQuote.hasBuyPrice()) {
                setBuyPrice(newIndiQuote.getBuyPrice());
            }
            if (newIndiQuote.hasSellPrice()) {
                setSellPrice(newIndiQuote.getSellPrice());
            }
            if (newIndiQuote.hasBuyVolume()) {
                setBuyVolume(newIndiQuote.getBuyVolume());
            }
            if (newIndiQuote.hasSellVolume()) {
                setSellVolume(newIndiQuote.getSellVolume());
            }
            if (newIndiQuote.hasStatus()) {
                setStatus(newIndiQuote.getStatus());
            }

            if (newIndiQuote.hasCreationTime()) {
                setCreationTime(newIndiQuote.getCreationTime());
            }

            if (newIndiQuote.hasComment()) {
                setComment(newIndiQuote.getComment());
            }
        }
    }

    public boolean hasComment() {
        return hasComment;
    }

    public boolean hasCreationTime() {
        return hasCreationTime;
    }

    public boolean hasStatus() {
        return hasStatus;
    }

    public boolean hasSellVolume() {
        return hasSellVolume;
    }

    public boolean hasBuyVolume() {
        return hasBuyVolume;
    }

    public boolean hasSellPrice() {
        return hasSellPrice;
    }

    public boolean hasBuyPrice() {
        return hasBuyPrice;
    }

    public boolean hasId() {
        return hasId;
    }

    @Override
    public String getKey() {
        return getInstrSymbol() + ":" + getMemberName();
    }
}
