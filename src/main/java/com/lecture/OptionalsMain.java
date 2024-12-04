package com.lecture;

import java.util.Optional; // Import the Optional class

public class OptionalsMain {
    public static void main(String[] args) {
        // Using the static getCatByName method
        Optional<Cat> databaseCatOrNot = getCatByName("Felix");

        // Using .orElse to provide a default cat if not found
        Cat cat = databaseCatOrNot.orElse(new Cat("UNKNOWN", 0));
        System.out.println(cat.age());

        // Old way for comparison
        if (databaseCatOrNot != null && databaseCatOrNot.isPresent()) {
            System.out.println(databaseCatOrNot.get().age());
            System.out.println("Hallo");
            System.out.println("Total wichtige Berechnung!");
        } else {
            System.out.println("Nicht gefunden!");
        }
    }

    // Mimicking the lecturer's getCatByName method
    public static Optional<Cat> getCatByName(String name) {
        Cat temp = new Cat(name, 3); // Simulating a cat with the given name
        return Optional.ofNullable(name.equals("Felix") ? temp : null); // Return Optional
    }
}
