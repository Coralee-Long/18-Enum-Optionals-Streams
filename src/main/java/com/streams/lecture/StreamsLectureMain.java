package com.streams.lecture;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamsLectureMain {
    public static void main(String[] args) {

        List<String> fruits = List.of("apple", "pear","banana", "pineapple", "mango");

        //  Loop method:
        for(String fruit : fruits) {
            if(fruit.contains("n")) {
                if(fruit.length() >= 6) {
                    System.out.println("Loop: " + fruit);
                }
            }
        }

        // Stream method:

        // 1. Start = .stream()
        List<String> filteredFruits = fruits.stream()
        // 2. Any number of manipulating operations
                // Can't just use like : .filter(fruit.contains("n"))
                .filter(fruit -> fruit.contains("n")) // lambda function
                .filter(fruit -> fruit.length() >= 6) // lambda function
        // 3. Terminator => Ends Stream
               // .forEach(fruit -> System.out.println("Stream: " + fruit));
               // .forEach(System.out::println); // Simplified version of line above

                // other terminator methods like:
                // .collect(Collectors.joining(", "));
                //    System.out.println(filteredFruits); // banana, pineapple
                .collect(Collectors.toList());
                System.out.println(filteredFruits); // [banana, pineapple]

    }
}
// This class with interface method is basically the same as a lambda method:
// .filter(fruit -> fruit.contains("n"))
class ContainsN implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return s.contains("n");
    }
}
