package com.exercises;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println(DaysOfWeek.SUNDAY.getdayOrWeekend()); // weekend
        PersonRepository pr = new PersonRepository();
        Optional<Person> person5 = pr.findPersonById(5);
        Optional<Person> person1 = pr.findPersonById(1);
        System.out.println(person5); // Optional.empty
        System.out.println(person1); // Optional[Person[id=1, name=Emre, favoriteDay=TUESDAY]]
        System.out.println("Gender count" + pr.countPersonByGender(Gender.MALE));
        Person person = person1.orElseThrow(RuntimeException::new);
        System.out.println(person.name()); // Emre
        System.out.println(person.favoriteDay()); // TUESDAY

        System.out.println(pr.findPersonByName("Chiara")); // Optional[Person[id=3, name=Chiara, favoriteDay=SUNDAY, gender=FEMALE]]
        System.out.println(pr.findPersonByName("Coralee")); // Optional.empty

        System.out.println(pr.peopleWithSameFavDays(DaysOfWeek.SUNDAY));
    }
}

