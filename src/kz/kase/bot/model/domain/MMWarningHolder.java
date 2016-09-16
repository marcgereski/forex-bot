package kz.kase.bot.model.domain;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Date;

public class MMWarningHolder implements Holder {

    //    private final ObjectProperty<Long> liabilityId = new SimpleObjectProperty<>();
    private final ObjectProperty<String> instrSymbol = new SimpleObjectProperty<>();
    private final ObjectProperty<String> sessionSerial = new SimpleObjectProperty<>();

//    private final ObjectProperty<Long> sessionId = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> startTime = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> endTime = new SimpleObjectProperty<>();
    private final ObjectProperty<String> type = new SimpleObjectProperty<>();
    private final ObjectProperty<Boolean> close = new SimpleObjectProperty<>();

/*
    public Long getLiabilityId() {
        return liabilityId.get();
    }

    public void setLiabilityId(long liabilityId) {
        this.liabilityId.set(liabilityId);
    }

    public ObjectProperty liabilityIdProperty() {
        return liabilityId;
    }
*/
/*
    public Long getSessionId() {
        return sessionId.get();
    }

    public void setSessionId(long sessionId) {
        this.sessionId.set(sessionId);
    }

    public ObjectProperty sessionIdProperty() {
        return sessionId;
    }*/

    public void setSessionSerial(String sessionSerial) {
        this.sessionSerial.set(sessionSerial);
    }

    public String getSessionSerial() {
        return sessionSerial.get();
    }

    public ObjectProperty<String> sessionSerialProperty() {
        return sessionSerial;
    }

    public Date getStartTime() {
        return startTime.get();
    }

    public void setStartTime(Date startTime) {
        this.startTime.set(startTime);
    }

    public ObjectProperty startTimeProperty() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime.get();
    }

    public void setEndTime(Date endTime) {
        this.endTime.set(endTime);
    }

    public ObjectProperty endTimeProperty() {
        return endTime;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public ObjectProperty typeProperty() {
        return type;
    }

    public boolean isClose() {
        if (close.get() == null) {
            return false;
        }
        return close.get();
    }

    public void setClose(boolean close) {
        this.close.set(close);
    }

    public ObjectProperty closeProperty() {
        return close;
    }

    public String getInstrSymbol() {
        return instrSymbol.get();
    }

    public ObjectProperty<String> instrSymbolProperty() {
        return instrSymbol;
    }

    public void setInstrSymbol(String symbol) {
        this.instrSymbol.set(symbol);
    }

    @Override
    public String getKey() {
        return getInstrSymbol();
    }
}
