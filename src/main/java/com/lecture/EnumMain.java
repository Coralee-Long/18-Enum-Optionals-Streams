package com.lecture;

public class EnumMain {
    public static void main(String[] args) {

        Weekdays day = Weekdays.MONDAY; // Don't use the "new" object method for enums

        if(day == Weekdays.THURSDAY) {
            System.out.println("Yay, it's almost Friday!");
        }
        System.out.println("Aww... it's only " + day);

        if(day.getValue().equals("weekend day")) {
            System.out.println("Thank god it's the weekend");
        }


    }
}