### Table of Contents:
- [Java Enums](#java-enums)
    - [Example: Basic Enum Usage](#example-basic-enum-usage)
    - [Example: Adding a Constructor to Enums](#example-adding-a-constructor-to-enums)
- [Java Optionals](#java-optionals)
    - [Why Use Optionals?](#why-use-optionals)
    - [Example: Basic Usage of `Optional`](#example-basic-usage-of-optional)
    - [Example: Using Optional with `orElse` and `orElseThrow`](#example-using-optional-with-orelse-and-orelsethrow)
    - [Example: Optional with Custom Objects](#example-optional-with-custom-objects)
    - [Key Optional Methods](#key-optional-methods)
    - [Key Takeaways](#key-takeaways)
- [EXTRA NOTES ON OPTIONALS (For Dummies)](#extra-notes-on-optionals-for-dummies)
    - [How to survive from null pointer exceptions:](#how-to-survive-from-null-pointer-exceptions)
        - [1. Don't assign a null to an optional variable](#1-dont-assign-a-null-to-an-optional-variable)
        - [2. Never call `get()` directly to get the value](#2-never-call-get-directly-to-get-the-value)
- [Streams](#streams)
    - [Loop vs. Stream](#loop-vs-stream)
        - [Loop Method](#loop-method)
        - [Stream Method](#stream-method)
    - [How Streams Work](#how-streams-work)
        - [Simplified Example](#simplified-example)
        - [Stream Terminators](#stream-terminators)
    - [Custom Predicate Implementation](#custom-predicate-implementation)
    - [Why Use Streams?](#why-use-streams)
    - [Summary](#summary)


# Java Enums

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
# Java Optionals

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

# EXTRA NOTES ON OPTIONALS (For Dummies)

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

# Streams

Java Streams, introduced in Java 8, provide a functional programming approach to processing collections (e.g., `List`, `Set`, etc.). Streams allow us to perform operations such as filtering, mapping, and reducing data in a clean and readable way.

---

## **Loop vs. Stream**

### **Loop Method**
```java
List<String> fruits = List.of("apple", "pear", "banana", "pineapple", "mango");

// Using a traditional loop to filter and process data
for (String fruit : fruits) {
    if (fruit.contains("n")) {              // Check if the fruit contains 'n'
        if (fruit.length() >= 6) {          // Check if the fruit length is >= 6
            System.out.println("Loop: " + fruit);
        }
    }
}
```
**Explanation:**

- This traditional approach is verbose and involves nested if statements.
- The logic for filtering and processing is tightly coupled, making the code harder to read.

### **Stream Method**
```java
import java.util.stream.Collectors;

List<String> fruits = List.of("apple", "pear", "banana", "pineapple", "mango");

// Using streams to filter and process data
List<String> filteredFruits = fruits.stream()       // 1. Start the stream
    .filter(fruit -> fruit.contains("n"))           // 2. Filter: Keep fruits containing 'n'
    .filter(fruit -> fruit.length() >= 6)           // 3. Filter: Keep fruits with length >= 6
    .collect(Collectors.toList());                  // 4. Terminate: Collect results into a list

System.out.println(filteredFruits);                 // Output: [banana, pineapple]
```

**Explanation:**

1. Start: fruits.stream() creates a stream from the list.
2. Filter Operations: .filter applies lambda expressions to filter elements based on conditions.
3. Terminate: .collect(Collectors.toList()) collects the results into a list.

## How Streams Work
**Streams have three main stages:**

**1. Start:**
- A stream is created from a data source (e.g., a collection like List).
```java
fruits.stream();
```

**2. Intermediate Operations:**

- These operations process or transform data in the stream. Examples include:
  - `.filter(Predicate)` to filter elements based on a condition.
  - `.map(Function)` to transform elements.
  - `.sorted()` to sort elements.

```java
.filter(fruit -> fruit.contains("n"))
.map(String::toUpperCase)
```

**3. Terminal Operations:**

- These operations produce a result or a side effect, such as:
    - `.collect(Collectors.toList())`: Collect elements into a list.
    - `.forEach(Consumer)`: Perform an action for each element.
    - `.reduce(BinaryOperator)`: Combine elements into a single result.
```java
.collect(Collectors.toList());
```

### Simplified Example
```java
List<String> fruits = List.of("apple", "pear", "banana", "pineapple", "mango");

// Filter fruits containing 'n' and convert them to uppercase
List<String> result = fruits.stream()
    .filter(fruit -> fruit.contains("n"))   // Filter fruits with 'n'
    .map(String::toUpperCase)               // Convert to uppercase
    .collect(Collectors.toList());          // Collect results into a list

System.out.println(result);                 // Output: [BANANA, PINEAPPLE]
```

### Stream Terminators
#### Streams must end with a terminal operation. Some commonly used terminators are:

#### 1. forEach
Executes an action for each element in the stream.
```java
fruits.stream()
    .filter(fruit -> fruit.contains("n"))
    .forEach(fruit -> System.out.println("Fruit: " + fruit));
```

#### 2. collect()
Collects stream elements into a data structure.
```java
List<String> result = fruits.stream()
    .filter(fruit -> fruit.length() >= 6)
    .collect(Collectors.toList());
System.out.println(result); // Output: [banana, pineapple]
```

#### 3. reduce()
Combines elements into a single result.
```java
int totalLength = fruits.stream()
    .map(String::length)                 // Get the length of each fruit
    .reduce(0, Integer::sum);            // Sum all lengths
System.out.println(totalLength);         // Output: 29
```
---
## Custom Predicate Implementation
Instead of lambdas, you can define custom Predicate classes to filter data.

### Using a Lambda:
```java
.filter(fruit -> fruit.contains("n"))
```
### Equivalent with a Custom Predicate:
```java
import java.util.function.Predicate;

class ContainsN implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return s.contains("n");
    }
}

// Usage
fruits.stream()
    .filter(new ContainsN())
        .filter(fruit -> fruit.length() >= 6)
        .collect(Collectors.toList());
```

## Why Use Streams?
1. Readability: Streams make code shorter and more expressive.
2. Functional Style: Focus on "what to do" rather than "how to do it."
3. Chaining Operations: Combine multiple operations in a single pipeline.
4. Parallel Processing: Easily process streams in parallel with `.parallelStream()`.

## Summary
- Streams make Java more expressive, reduce boilerplate, and encourage functional programming principles.
- Streams simplify data processing with three main steps:

1. Start: Create a stream with `.stream()`.
2. Intermediate Operations: Chain multiple operations like `.filter`, `.map`, and `.sorted`.
3. Terminate: Collect or process the results with `.collect`, `.forEach`, or `.reduce`.

**Example Workflow:**
```java
List<String> filteredFruits = fruits.stream()
    .filter(fruit -> fruit.contains("n"))    // Step 1: Filter fruits with 'n'
    .filter(fruit -> fruit.length() >= 6)    // Step 2: Filter fruits with length >= 6
    .collect(Collectors.toList());           // Step 3: Collect results into a list
    
    // Output: [banana, pineapple]
```