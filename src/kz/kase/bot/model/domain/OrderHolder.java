package kz.kase.bot.model.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import kz.kase.fix.OrderStatus;
import kz.kase.fix.OrderType;
import kz.kase.fix.Side;
import kz.kase.fix.TimeInForce;

import java.util.Date;
import java.util.ResourceBundle;

public class OrderHolder implements Holder {

    //    private final ObjectProperty<Long> id = new SimpleObjectProperty<>();
    private final ObjectProperty<String> serial = new SimpleObjectProperty<>();
    private final ObjectProperty<Integer> number = new SimpleObjectProperty<>();
    private final ObjectProperty<String> instr = new SimpleObjectProperty<>();
    private final ObjectProperty<Long> accId = new SimpleObjectProperty<>();
    //    private final ObjectProperty<Long> instrId = new SimpleObjectProperty<>();
    private final ObjectProperty<String> acc = new SimpleObjectProperty<>();
    private final ObjectProperty<OrderType> type = new SimpleObjectProperty<>();
    private final ObjectProperty<Double> price = new SimpleObjectProperty<>();
    private final ObjectProperty<Long> qty = new SimpleObjectProperty<>();
    private final ObjectProperty<Long> leavesQty = new SimpleObjectProperty<>();
    private final ObjectProperty<Double> cashQty = new SimpleObjectProperty<>();
    private final ObjectProperty<Side> side = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> creationTime = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> lastUpdateTime = new SimpleObjectProperty<>();
    private final ObjectProperty<OrderStatus> status = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> expireDate = new SimpleObjectProperty<>();
    private final ObjectProperty<String> comment = new SimpleObjectProperty<>();
    private final ObjectProperty<Boolean> isMMOrder = new SimpleObjectProperty<>();
    private final ObjectProperty<String> userName = new SimpleObjectProperty<>();
    private final ObjectProperty<TimeInForce> timeInForce = new SimpleObjectProperty<>();
    private final ObjectProperty<String> whoRemoved = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> whenRemoved = new SimpleObjectProperty<>();
    private final ObjectProperty<Integer> sessionId = new SimpleObjectProperty<>();

    public Integer getSessionId() {
        return sessionId.get();
    }

    public ObjectProperty<Integer> sessionIdProperty() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId.set(sessionId);
    }

    public void setWhenRemoved(Date whenRemoved) {
        this.whenRemoved.set(whenRemoved);
    }

    public Date getWhenRemoved() {
        return whenRemoved.get();
    }

    public ObjectProperty<Date> whenRemovedProperty() {
        return whenRemoved;
    }

    public String getWhoRemoved() {
        return whoRemoved.get();
    }

    public ObjectProperty<String> whoRemovedProperty() {
        return whoRemoved;
    }

    public void setWhoRemoved(String whoRemoved) {
        this.whoRemoved.set(whoRemoved);
    }

    public TimeInForce getTimeInForce() {
        return timeInForce.get();
    }

    public ObjectProperty<TimeInForce> timeInForceProperty() {
        return timeInForce;
    }

    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce.set(timeInForce);
    }

    public String getUserName() {
        return userName.get();
    }

    public ObjectProperty<String> userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getComment() {
        return comment.get();
    }

    public ObjectProperty<String> commentProperty() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public Integer getNumber() {
        return number.get();
    }

    public ObjectProperty numberProperty() {
        return number;
    }

    public void setNumber(int id) {
        this.number.set(id);
    }

    public String getInstr() {
        return instr.get();
    }

    public ObjectProperty instrProperty() {
        return instr;
    }

    public void setInstr(String instr) {
        this.instr.set(instr);
    }

    public Long getAccId() {
        return accId.get();
    }

    public ObjectProperty accIdProperty() {
        return accId;
    }

    public void setAccId(long accId) {
        this.accId.set(accId);
    }

    public String getAcc() {
        return acc.get();
    }

    public ObjectProperty accProperty() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc.set(acc);
    }

    public OrderType getType() {
        return type.get();
    }

    public ObjectProperty<OrderType> typeProperty() {
        return type;
    }

    public void setType(OrderType type) {
        this.type.set(type);
    }

    public Double getPrice() {
        return price.get();
    }

    public ObjectProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public Long getQty() {
        return qty.get();
    }

    public ObjectProperty qtyProperty() {
        return qty;
    }

    public void setQty(long qty) {
        this.qty.set(qty);
    }

    public Long getLeavesQty() {
        return leavesQty.get();
    }

    public ObjectProperty leavesQtyProperty() {
        return leavesQty;
    }

    public void setLeavesQty(long leavesQty) {
        this.leavesQty.set(leavesQty);
    }

    public Double getCashQty() {
        return cashQty.get();
    }

    public ObjectProperty cashQtyProperty() {
        return cashQty;
    }

    public void setCashQty(double cashQty) {
        this.cashQty.set(cashQty);
    }

    public Side getSide() {
        return side.get();
    }

    public ObjectProperty sideProperty() {
        return side;
    }

    public void setSide(Side side) {
        this.side.set(side);
    }

    public Date getCreationTime() {
        return creationTime.get();
    }

    public ObjectProperty creationTimeProperty() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime.set(creationTime);
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime.get();
    }

    public ObjectProperty lastUpdateTimeProperty() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime.set(lastUpdateTime);
    }

    public OrderStatus getStatus() {
        return status.get();
    }

    public ObjectProperty<OrderStatus> statusProperty() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status.set(status);
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate.set(expireDate);
    }

    public Date getExpireDate() {
        return expireDate.get();
    }

    public ObjectProperty expireDateProperty() {
        return expireDate;
    }

    public void setIsMMOrder(boolean isMMOrder) {
        this.isMMOrder.set(isMMOrder);
    }

    public Boolean isMMOrder() {
        return isMMOrder.get();
    }

    public ObjectProperty isMMOrderProperty() {
        return isMMOrder;
    }

    //    -----------------------------------------------------


    public void update(OrderHolder newOrder) {

        if (newOrder.whenRemoved.getValue() != null) {
            setWhenRemoved(newOrder.getWhenRemoved());
        }
        if (newOrder.whoRemoved.getValue() != null) {
            setWhoRemoved(newOrder.getWhoRemoved());
        }

        if (newOrder.leavesQty.getValue() != null) {
            setLeavesQty(newOrder.getLeavesQty());
        }

        if (newOrder.status.getValue() != null) {
            setStatus(newOrder.getStatus());
        }
    }

    public String getSerial() {
        return serial.get();
    }

    public ObjectProperty<String> serialProperty() {
        return serial;
    }


    public void setSerial(String serial) {
        this.serial.set(serial);
    }

    @Override
    public String toString() {
        return "\tsymbol: " + instr.get() + "\n" + "" +
                "\tserial: " + serial.get() + "\n" +
                "\tstatus: " + status.get() + "\n" +
                "\taccId: " + accId.get() + "\n" +
                "\tside: " + side.get() + "\n" +
                "\tprice: " + price.get() + "\n" +
                "\tqty: " + qty.get() + "\n" +
                "\tleavesQty: " + leavesQty.get() + "\n" +
                "\tcreation time: " + creationTime.get() + "\n";
    }

    @Override
    public String getKey() {
        return getSerial();
    }

    public String getString(ResourceBundle bundle) {
        StringBuilder s = new StringBuilder();
        s.append(instr.getValue()).append(":");
        s.append(acc.getValue()).append(":");
        s.append(serial.getValue()).append(":");
        s.append(bundle.getString("label.side." + side.getValue().toString().toLowerCase()));
        s.append(bundle.getString("label.order-status." + status.getValue().toString().toLowerCase()));
        s.append(price.getValue()).append(":");
        s.append(qty.getValue()).append(":");
        s.append(leavesQty.get()).append(":");
        s.append(bundle.getString("label.side." + side.getValue().toString().toLowerCase())).append(":");
        s.append(userName.getValue()).append(":");
        return s.toString();
    }
}
