package kz.kase.bot.model.domain;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class NextCalendar {


    public static final String KZT = "kzt";
    protected Map<LocalDate, Day> days = new HashMap<>();

    public int getSwapDaysLength(int tPlusN, String counterCurr, String crossCurr) {
        if (days.size() <= 0) return tPlusN;

        String counter = counterCurr.toLowerCase();
        String cross = crossCurr.toLowerCase();
        LocalDate incrementalDate = LocalDate.now();

        int addDays = 0;
        for (int i = 0; i < tPlusN; i++) {
            addDays++;
            try {
                while (true) {
                    DayType localDayType = days.get(incrementalDate.plus(addDays, ChronoUnit.DAYS))
                            .getDayType(counter);
                    DayType foreignDayType = days.get(incrementalDate.plus(addDays, ChronoUnit.DAYS))
                            .getDayType(cross);
                    if (foreignDayType == null) {
                        foreignDayType = DayType.WORKDAY;
                    }
                    if (localDayType != DayType.WORKDAY
                            || foreignDayType != DayType.WORKDAY) {
                        addDays++;
                    } else {
                        break;
                    }
                }
            } catch (Exception ne) {
                ne.printStackTrace();
            }
        }

        return addDays;
    }

    public int getRepoDaysLength(int tPlusN) {
        if (days.size() <= 0) return tPlusN;

        LocalDate incrementalDate = LocalDate.now();

        int addDays = tPlusN;
        for (int i = 0; i < tPlusN; i++) {
            while (days.get(incrementalDate.plus(addDays, ChronoUnit.DAYS))
                    .getDayType(KZT) != DayType.WORKDAY) {
                addDays++;
            }
        }

        return addDays;
    }

    public void addDay(LocalDate date, Day day) {
        days.put(date, day);
    }

    public void update(NextCalendar nextCalendar) {
        Map<LocalDate, Day> newCalDays = nextCalendar.days;
        Map<LocalDate, Day> foreignHolidayUpdateList = new HashMap<>();
        for (LocalDate ld : newCalDays.keySet()) {
            Day newDay = newCalDays.get(ld);
            boolean localDayUpdates = newDay.getCountryTypes().contains(KZT);
            Day oldDay = days.get(ld);
            if (!localDayUpdates) {
                foreignHolidayUpdateList.put(ld, newCalDays.get(ld));
            } else {

                if (oldDay != null) {
                    oldDay.update(newCalDays.get(ld));
                }
            }
        }
        if (foreignHolidayUpdateList.size() > 0) {
            for (Day d : days.values()) {
                Day newDay = foreignHolidayUpdateList.get(d.getDate());
                if (newDay == null) {
                    d.resetForeignHolidays();
                } else {
                    d.updateForeignCalDay(newDay);
                }
            }
        }

    }

    public int getTPlusN(String instrSymbol) {
        if (instrSymbol.equals("USDKZT_TOD")) return 0;
        if (instrSymbol.equals("USDKZT_TOM")) return 1;
        if (instrSymbol.equals("USDKZT_SPT")) return 2;

        if (instrSymbol.equals("EURKZT_TOD")) return 0;
        if (instrSymbol.equals("EURKZT_TOM")) return 1;
        if (instrSymbol.equals("EURKZT_SPT")) return 2;

        if (instrSymbol.equals("EURUSD_TOD")) return 0;
        if (instrSymbol.equals("EURUSD_TOM")) return 1;
        if (instrSymbol.equals("EURUSD_SPT")) return 2;
        return 0;
    }

    public static class Day {
        protected LocalDate date;
        protected Map<String, DayType> currDayStatus = new HashMap<>();

        public Day() {
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public DayType getDayType(String cur) {
            return currDayStatus.get(cur);
        }

        public void resetForeignHolidays() {
            for (String countryCode : currDayStatus.keySet()) {
                if (!countryCode.equals(KZT)) {
                    currDayStatus.replace(countryCode, DayType.WORKDAY);
                }
            }
        }

        public LocalDate getDate() {
            return date;
        }

        public Set<String> getCountryTypes() {
            return currDayStatus.keySet();
        }

        public void putDayType(String cName, DayType dayType) {
            currDayStatus.put(cName, dayType);
        }

        public void update(Day anotherDay) {
            Map<String, DayType> anotherDayTypes = anotherDay.currDayStatus;
            for (String countryCode : anotherDayTypes.keySet()) {

                if (currDayStatus.containsKey(countryCode)) {
                    currDayStatus.replace(countryCode, anotherDayTypes.get(countryCode));
                } else {
                    currDayStatus.put(countryCode, anotherDayTypes.get(countryCode));
                }
            }


        }

        public void updateForeignCalDay(Day anotherDay) {
            List<String> removeList = new ArrayList<>();
            List<String> addList = new ArrayList<>();
            for (String countryCode : currDayStatus.keySet()) {
                if (!countryCode.equals(KZT)) {
                    if (!anotherDay.currDayStatus.containsKey(countryCode)) {
                        removeList.add(countryCode);
                    } else if (anotherDay.currDayStatus.containsKey(countryCode)) {
                        addList.add(countryCode);
                    } else if (currDayStatus.containsKey(countryCode) &&
                            anotherDay.currDayStatus.containsKey(countryCode)) {
                        currDayStatus.replace(countryCode, anotherDay.currDayStatus.get(countryCode));
                    }
                }
            }

            for (String r : removeList) {
                currDayStatus.remove(r);
            }

            for (String a : addList) {
                currDayStatus.put(a, DayType.HOLIDAY);
            }


        }


    }


    public enum DayType {
        HOLIDAY, WORKDAY
    }

}


