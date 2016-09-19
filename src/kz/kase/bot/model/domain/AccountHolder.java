package kz.kase.bot.model.domain;


import kz.kase.fix.Product;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AccountHolder implements Comparable, Holder {

    //    private final LongProperty id = new SimpleLongProperty();
//    private boolean idSet;
    private String name;
    private List<String> users = new ArrayList<>();
    private Product marketType;
    private boolean hasMarketType;
    private final List<PositionHolder> moneyPositions = new ArrayList<>();
    private final List<PositionHolder> positions = new ArrayList<>();
    private final List<DayPositionHolder> dayPositions = new ArrayList<>();

//    public long getId() {
//        return id.get();
//    }
//
//    public LongProperty idProperty() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id.set(id);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getMarketType() {
        return marketType;
    }

    public Product marketTypeProperty() {
        return marketType;
    }

    public void setMarketType(Product marketType) {
        this.marketType = marketType;
        hasMarketType = true;
    }

    public List<PositionHolder> getMoneyPositions() {
        return moneyPositions;
    }

    public void addMoneyPosition(PositionHolder pos) {
        if (pos != null) {
            moneyPositions.add(pos);
        }
    }

    public PositionHolder getMoneyPosition(String instrSymbol) {
        for (PositionHolder pos : moneyPositions) {
            if (pos.getSymbol().equals(instrSymbol))
                return pos;
        }
        return null;
    }

    public boolean hasMarketType() {
        return hasMarketType;
    }

    public PositionHolder getPosition(String instrSymbol) {
        for (PositionHolder pos : positions) {
            if (pos.getSymbol().equals(instrSymbol))
                return pos;
        }
        return null;
    }

    public List<PositionHolder> getPositions() {
        return positions;
    }

    public void addPosition(PositionHolder pos) {
        if (pos != null) {
            positions.add(pos);
        }
    }

    public List<DayPositionHolder> getDayPositions() {
        return dayPositions;
    }

    public void addDayPosition(DayPositionHolder dayPos) {
        if (dayPos != null) {
            dayPositions.add(dayPos);
        }
    }

    public DayPositionHolder getDayPosition(String acc, String currName, Date settlDate) {
        Calendar newCal = Calendar.getInstance();
        newCal.setTime(settlDate);
        CopyOnWriteArrayList<DayPositionHolder> dpList = new CopyOnWriteArrayList(dayPositions);

        for (DayPositionHolder dayPos : dpList) {
            Calendar oldCal = Calendar.getInstance();
            oldCal.setTime(dayPos.getSettlementPosDate());

            if (acc.equals(dayPos.getAcc())
                    && dayPos.getCurrencyName().equals(currName)
                    && newCal.get(Calendar.DAY_OF_YEAR) == oldCal.get(Calendar.DAY_OF_YEAR)) {
                return dayPos;
            }
        }
        return null;
    }

    public List<String> getOwnerUsers() {
        return users;
    }

    public void setOwnerUser(String user) {
        if (!users.contains(user)) users.add(user);
    }

    public void update(AccountHolder newAcc) {
        newAcc.getOwnerUsers().forEach(u->{
            if (!users.contains(u)) users.add(u);
        });

        if (newAcc.getPositions() != null) {
            for (PositionHolder newPos : newAcc.getPositions()) {
                PositionHolder pos = getPosition(newPos.getSymbol());
                if (pos != null) {
                    pos.update(newPos);
                } else {
                    addPosition(newPos);
                }
            }
        }

        if (newAcc.getMoneyPositions() != null) {
            for (PositionHolder newPos : newAcc.getMoneyPositions()) {
                PositionHolder pos = getMoneyPosition(newPos.getSymbol());
                if (pos != null) {
                    pos.update(newPos);
                } else {
                    addMoneyPosition(newPos);
                }
            }
        }

        if (newAcc.getDayPositions() != null) {
//            List<DayPositionHolder> removeList = new ArrayList<>();


            for (DayPositionHolder newDayPos : newAcc.getDayPositions()) {
                DayPositionHolder dayPos = getDayPosition(newDayPos.getAcc(), newDayPos.getCurrencyName(), newDayPos.getSettlementPosDate());
                if (dayPos != null) {
//                    if (newDayPos.hasTPlusN() && newDayPos.getTPlusN() < 0) {
//                        removeList.add(dayPos);
//                    } else {
                    dayPos.update(newDayPos);
//                    }
                } else {
                    addDayPosition(newDayPos);
                }
            }

//            for (DayPositionHolder dp : removeList) {
//                dayPositions.remove(dp);
//            }
        }

        if (getName() == null && newAcc.getName() != null) {
            setName(newAcc.getName());
        }
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("\tname: ").append(getName()).append("\n");
        CopyOnWriteArrayList<PositionHolder> instPos = new CopyOnWriteArrayList<>(positions);
        for (PositionHolder pos : instPos) {
            buf.append(pos);
        }

        CopyOnWriteArrayList<PositionHolder> moneyPos = new CopyOnWriteArrayList<>(moneyPositions);
        for (PositionHolder pos : moneyPos) {
            buf.append(pos);
        }
        return buf.toString();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(Object obj) {
        if (obj != null) {
            if (obj instanceof AccountHolder) {
                AccountHolder newAcc = (AccountHolder) obj;
                return name.compareTo(newAcc.getName());
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AccountHolder) {
            AccountHolder a2 = (AccountHolder) o;
            return name.equals(a2.name);
        }
        return false;
    }


    @Override
    public String getKey() {
        return getName();
    }
}

