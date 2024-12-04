# ⭐️ Java Enums

Enums are a special Java type used to define collections of constants. They are useful when you have a fixed set of related values, such as days of the week, directions, or states.

### Example: Basic Enum Usage
```java
// Weekdays.java
public enum Weekdays {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Weekdays day = Weekdays.MONDAY; // Don't use the "new" object method for enums

        if (day == Weekdays.THURSDAY) {
            System.out.println("Yay, it's almost Friday!");
        }
        System.out.println("Aww... it's only " + day);
    }
}
```
### Example: Adding a Constructor to Enums
Enums can include additional fields, methods, and constructors to store related information.

```java
// Weekdays.java
public enum Weekdays {
    MONDAY("workday", true),
    TUESDAY("workday", true),
    WEDNESDAY("workday", true),
    THURSDAY("workday", true),
    FRIDAY("workday", true),
    SATURDAY("weekend day", false),
    SUNDAY("weekend day", false);

    private final String value;
    private final boolean hasToWork;

    // Constructor to initialize fields
    Weekdays(String value, boolean hasToWork) {
        this.value = value;
        this.hasToWork = hasToWork;
    }

    // Getter methods
    public String getValue() {
        return value;
    }

    public boolean hasToWork() {
        return hasToWork;
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Weekdays today = Weekdays.FRIDAY;

        // Accessing enum fields and methods
        System.out.println("Today is " + today + ", which is a " + today.getValue());
        if (today.hasToWork()) {
            System.out.println("Unfortunately, you have to work today!");
        } else {
            System.out.println("Enjoy your day off!");
        }
    }
}
```
---
# ⭐️ Java Optionals

`Optional` is a container object introduced in Java 8 to handle the presence or absence of a value without risking `NullPointerException`. It encourages writing cleaner and safer code by avoiding manual null checks.

### Why Use Optionals?

- To avoid null pointer exceptions.
- To make the absence of a value explicit.
- To reduce the need for defensive programming.

---

### Example: Basic Usage of `Optional`
```java
import java.util.Optional;

public class OptionalsMain {
    public static void main(String[] args) {
        // Creating an Optional with a value
        Optional<String> optionalValue = Optional.of("Hello, Optional!");

        // Accessing the value
        if (optionalValue.isPresent()) {
            System.out.println("Value is present: " + optionalValue.get());
        } else {
            System.out.println("Value is absent!");
        }

        // Using orElse
        String fallback = optionalValue.orElse("Default Value");
        System.out.println("Retrieved value: " + fallback);
    }
}
```

### Example: Using Optional with `orElse` and `orElseThrow`
```java
import java.util.Optional;

public class OptionalsMain {
    public static void main(String[] args) {
        Optional<String> optionalValue = getValue("Java");

        // Using .orElse() to provide a default value
        String value = optionalValue.orElse("Default Value");
        System.out.println("Retrieved value: " + value);

        // Using .orElseThrow() to throw an exception if value is absent
        try {
            String mandatoryValue = optionalValue.orElseThrow(() -> new RuntimeException("Value is missing!"));
            System.out.println("Mandatory value: " + mandatoryValue);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    // Simulated method returning Optional
    public static Optional<String> getValue(String input) {
        return input.equals("Java") ? Optional.of("Java is awesome!") : Optional.empty();
    }
}
```

### Example: Optional with Custom Objects
```java
import java.util.Optional;

public class OptionalsMain {
    public static void main(String[] args) {
        // Using Optional with a custom object
        Optional<Cat> optionalCat = getCatByName("Felix");

        // Using orElse to handle absence of a value
        Cat cat = optionalCat.orElse(new Cat("UNKNOWN", 0));
        System.out.println("Cat retrieved: Name = " + cat.name() + ", Age = " + cat.age());

        // Using orElseThrow to enforce mandatory presence of a value
        try {
            Cat mandatoryCat = optionalCat.orElseThrow(() -> new RuntimeException("Cat not found!"));
            System.out.println("Mandatory Cat: Name = " + mandatoryCat.name() + ", Age = " + mandatoryCat.age());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    // Simulated method returning Optional<Cat>
    public static Optional<Cat> getCatByName(String name) {
        Cat temp = new Cat(name, 3); // Simulate a Cat object
        return Optional.ofNullable(name.equals("Felix") ? temp : null); // Return Optional
    }
}

// Record to represent a Cat
public record Cat(String name, int age) {
}
```

## Key Optional Methods
1. **`isPresent()`**: Returns `true` if the Optional contains a value, `false` otherwise.
2. **`get()`**: Retrieves the value if present (use cautiously as it throws if the Optional is empty).
3. **`orElse()`**: Returns the value if present or provides a default value.
4. **`orElseThrow()`**: Throws a custom exception if the value is absent.
5. **`ifPresent()`**: Executes a lambda expression if the value is present.

---

## Key Takeaways
- Use `Optional` to handle nullable values in a clean and safe way.
- Avoid calling `get()` directly unless you've confirmed the value is present.
- Use methods like `.orElse()` or `.orElseThrow()` to handle absence of values gracefully.
---

# ⚠️ EXTRA NOTES ON OPTIONALS (For Dummies) ⚠️

## How to survive from null pointer exceptions:
### 1. Don't assign a null to an optional variable
```java
Optional<Person> person = null; // BAD: This defeats the purpose of Optional
```
**Solution: use `empty()`**
```java
Optional<Person> person = Optional.empty(); // GOOD: No null here
```
### 2. Never call `get()` directly to get the value
```java
Optional<Person> person = PersonService.getPerson();
Person myPerson = person.get(); // BAD: This throws an exception if the Optional is empty
```
**Solution 1: Using `isPresent()` and `.get()`**
```java
Optional<Person> person = PersonService.getPerson();
    if(person.isPresent()) {                // Check if the value exists
        Person myPerson = person.get();     // Safe to get the value here
    }
```
**Solution 2: Using `orElse()`**
```java
Optional<Person> person = PersonService.getPerson();
Person myPerson = person.orElse(new Person("Unknown Name")); // Provide a fallback value
```

