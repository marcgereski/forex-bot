package kz.kase.bot.model.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Date;

public class DayPositionHolder {

    private final ObjectProperty<String> acc = new SimpleObjectProperty<>();
    private boolean hasAcc;
    private final ObjectProperty<String> memberName = new SimpleObjectProperty<>();
    private boolean hasMemberName;
    private final ObjectProperty<String> currencyName = new SimpleObjectProperty<>();
    private boolean hasCurrencyName;
    private final ObjectProperty<Double> buyPos = new SimpleObjectProperty<>(0D);
    private boolean hasBuyPos;
    private final ObjectProperty<Double> sellPos = new SimpleObjectProperty<>(0D);
    private boolean hasSellPos;
    private final ObjectProperty<Double> netPos = new SimpleObjectProperty<>(0D);
    private boolean hasNetPos;
    private final ObjectProperty<Double> buyBlockedPos = new SimpleObjectProperty<>(0D);
    private boolean hasBuyBlockedPos;
    private final ObjectProperty<Double> sellBlockedPos = new SimpleObjectProperty<>(0D);
    private boolean hasSellBlockedPos;
    private final ObjectProperty<Date> settlementPosDate = new SimpleObjectProperty<>();
    private boolean hasSettlementPosDate;

    public String getAcc() {
        return acc.get();
    }

    public void setAcc(String acc) {
        this.acc.set(acc);
        hasAcc = true;
    }

    public ObjectProperty<String> accProperty() {
        return acc;
    }

    public String getMemberName() {
        return memberName.get();
    }

    public void setMemberName(String memberName) {
        this.memberName.set(memberName);
        hasMemberName = true;
    }


    public ObjectProperty<String> memberNameProperty() {
        return memberName;
    }

    public String getCurrencyName() {
        return currencyName.get();
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName.set(currencyName);
        hasCurrencyName = true;
    }

    public ObjectProperty<String> currencyNameProperty() {
        return currencyName;
    }

    public Double getBuyPos() {
        return buyPos.get();
    }

    public void setBuyPos(double buyPos) {
        this.buyPos.set(buyPos);
        hasBuyPos = true;
    }

    public ObjectProperty<Double> buyPosProperty() {
        return buyPos;
    }

    public Double getSellPos() {
        return sellPos.get();
    }

    public void setSellPos(double sellPos) {
        this.sellPos.set(sellPos);
        hasSellPos = true;
    }

    public ObjectProperty<Double> sellPosProperty() {
        return sellPos;
    }

    public Double getNetPos() {
        return netPos.get();
    }

    public void setNetPos(double netPos) {
        this.netPos.set(netPos);
        hasNetPos = true;
    }

    public ObjectProperty<Double> netPosProperty() {
        return netPos;
    }

    public Double getBuyBlockedPos() {
        return buyBlockedPos.get();
    }

    public void setBuyBlockedPos(double buyBlockedPos) {
        this.buyBlockedPos.set(buyBlockedPos);
        hasBuyBlockedPos = true;
    }

    public ObjectProperty<Double> buyBlockedPosProperty() {
        return buyBlockedPos;
    }

    public Double getSellBlockedPos() {
        return sellBlockedPos.get();
    }

    public void setSellBlockedPos(double sellBlockedPos) {
        this.sellBlockedPos.set(sellBlockedPos);
        hasSellBlockedPos = true;
    }

    public ObjectProperty<Double> sellBlockedPosProperty() {
        return sellBlockedPos;
    }

    public Date getSettlementPosDate() {
        return settlementPosDate.get();
    }

    public void setSettlementPosDate(Date settlementPosDate) {
        this.settlementPosDate.set(settlementPosDate);
        hasSettlementPosDate = true;
    }

    public ObjectProperty settlementPosDateProperty() {
        return settlementPosDate;
    }

    public boolean hasAcc() {
        return hasAcc;
    }

    public boolean hasMemberName() {
        return hasMemberName;
    }

    /*public boolean hasCurrencyId() {
        return hasCurrencyIdSet;
    }*/

    public boolean hasCurrencyName() {
        return hasCurrencyName;
    }

    public boolean hasBuyPos() {
        return hasBuyPos;
    }

    public boolean hasSellPos() {
        return hasSellPos;
    }

    public boolean hasNetPos() {
        return hasNetPos;
    }

    public boolean hasBuyBlockedPos() {
        return hasBuyBlockedPos;
    }

    public boolean hasSellBlockedPos() {
        return hasSellBlockedPos;
    }

    public boolean hasSettlementPosDate() {
        return hasSettlementPosDate;
    }

    public void update(DayPositionHolder newDayPos) {

        if (newDayPos.hasBuyPos()) {
            setBuyPos(newDayPos.getBuyPos());
        }

        if (newDayPos.hasSellPos()) {
            setSellPos(newDayPos.getSellPos());
        }

        if (newDayPos.hasNetPos()) {
            setNetPos(newDayPos.getNetPos());
        }

        if (newDayPos.hasBuyBlockedPos()) {
            setBuyBlockedPos(newDayPos.getBuyBlockedPos());
        }

        if (newDayPos.hasSellBlockedPos()) {
            setSellBlockedPos(newDayPos.getSellBlockedPos());
        }

//        if (newDayPos.hasSettlementPosDate()) {
//            setSettlementPosDate(newDayPos.getSettlementPosDate());
//        }
    }

    public boolean equals(DayPositionHolder dp) {
        return !(dp.getAcc() == null || dp.getMemberName() == null ||
                dp.getSettlementPosDate() == null || dp.getCurrencyName() == null ||
                acc.get() == null || memberName.get() == null || currencyName.get() == null ||
                settlementPosDate.get() == null) && dp.getAcc().equals(acc.get()) &&
                dp.getMemberName().equals(memberName.get()) &&
                dp.getCurrencyName().equals(currencyName.get()) &&
                dp.getSettlementPosDate().equals(settlementPosDate.get());
    }

}
