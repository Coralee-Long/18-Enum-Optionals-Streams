package com.exercises;

public enum DaysOfWeek {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Weekend"),
    SUNDAY("Weekend");

    private final String dayOrWeekend;

    DaysOfWeek(String dayOrWeekend) {
        this.dayOrWeekend = dayOrWeekend;
    }

    public String getdayOrWeekend() {
        return dayOrWeekend;
    }
}
