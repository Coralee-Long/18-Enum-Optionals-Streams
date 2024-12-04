package com.exercises;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonRepository {
    private final Map<Integer, Person> personMap = new HashMap<>();

    public PersonRepository() {
        personMap.put(1, new Person(1, "Emre", DaysOfWeek.TUESDAY, Gender.MALE));
        personMap.put(2, new Person(2, "Niels", DaysOfWeek.SATURDAY, Gender.MALE));
        personMap.put(3, new Person(3, "Chiara", DaysOfWeek.SUNDAY, Gender.FEMALE));
        personMap.put(4, new Person(4, "Jane", DaysOfWeek.SUNDAY, Gender.FEMALE));

    }

    public Optional<Person> findPersonById(int id) {
        return Optional.ofNullable(personMap.get(id));
    }

    public Optional<Person> findPersonByName(String name) {

        List<Person> people = (personMap.values().stream().filter(person -> person.name().equalsIgnoreCase(name))).toList();

        if (people.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(people.getFirst());
    }

    public long countPersonByGender(Gender gender)  {
        return personMap.values().stream().filter(person -> person.gender() == gender).count();
    }


    public List<Person> peopleWithSameFavDays(DaysOfWeek favoriteDay) {
        return (personMap.values().stream().filter(person -> person.favoriteDay().equals(favoriteDay))).toList();
    }
}
