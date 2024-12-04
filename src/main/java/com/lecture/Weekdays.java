package com.lecture;

public enum Weekdays {
    MONDAY ("workday", true),
    TUESDAY("workday", true),
    WEDNESDAY("workday", true),
    THURSDAY("workday", true),
    FRIDAY("workday", true),
    SATURDAY("weekend day", false),
    SUNDAY("weekend day",false);

    private final String value;
    private final boolean hasToWork;

    // constructor to add stuff
    Weekdays(String value, boolean hasToWork) {
        this.value = value;
        this.hasToWork = false;
    }
    // getter
    public String getValue() {
        return value;
    }
    public boolean hasToWork() {
        return hasToWork;
    }
}

