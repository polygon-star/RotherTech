# Java Interview Script: Functional Interface, Stream API, CompletableFuture

## 1. What is a Functional Interface?

A **functional interface** is an interface that has exactly **one abstract method**.

It can have:
- one abstract method
- many default methods
- many static methods

It is commonly used with **lambda expressions** and **method references**.

```java
@FunctionalInterface
interface MyInterface {
    void sayHello();
}

Interview answer:

A functional interface is an interface with only one abstract method. It is the foundation of lambda expressions in Java. Examples include Predicate, Supplier, Consumer, and Function.

2. What is a Default Method?

A default method is a method inside an interface that has a method body.

It was introduced in Java 8.

interface Vehicle {
    default void start() {
        System.out.println("Vehicle is starting");
    }
}
```

A default method allows an interface to provide method implementation. Classes that implement the interface can use it directly or override it.

3. Predicate vs Supplier vs Consumer vs Function
Interface	Input	Output	Purpose
Predicate<T>	T	boolean	Check a condition
Supplier<T>	none	T	Provide a value
Consumer<T>	T	void	Consume/use a value
Function<T, R>	T	R	Transform input to output

```
import java.util.function.*;

public class FunctionalInterfaceExample {
    public static void main(String[] args) {

        Predicate<Integer> isAdult = age -> age >= 18;
        System.out.println(isAdult.test(20));

        Supplier<String> supplier = () -> "Hello Java";
        System.out.println(supplier.get());

        Consumer<String> printer = name -> System.out.println(name);
        printer.accept("Alice");

        Function<String, Integer> getLength = str -> str.length();
        System.out.println(getLength.apply("Java"));
    }
}
```


Predicate returns true or false. Supplier provides a value without input. Consumer takes an input but returns nothing. Function takes an input and returns a transformed output.

4. What is Method Reference?

A method reference is a shorter way to write a lambda expression when the lambda only calls an existing method.

`list.forEach(name -> System.out.println(name));`

Can be written as:

`list.forEach(System.out::println);`

Common types:

ClassName::staticMethod
object::instanceMethod
ClassName::instanceMethod
ClassName::new

Method reference is a shorthand syntax for a lambda expression that calls an existing method.

5. What is CompletableFuture?

CompletableFuture is used for asynchronous programming in Java.

It allows tasks to run in the background without blocking the main thread.

```
import java.util.concurrent.*;

public class CompletableFutureExample {
    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Data from async task";
        });

        future.thenApply(data -> data.toUpperCase())
              .thenAccept(System.out::println);

        Thread.sleep(1000);
    }
}
```

CompletableFuture is used to write asynchronous, non-blocking code. It can run tasks in another thread and chain operations using methods like thenApply, thenAccept, and thenCompose.

6. default Keyword vs Java Default Scope
default keyword

Used in an interface to define a default method.

```
interface Animal {
    default void eat() {
        System.out.println("Eating");
    }
}
```
Default scope

Default scope means package-private access.

If no access modifier is written, the class, method, or field is accessible only within the same package.

```
class Student {
    String name; // package-private
}
```

The default keyword is used in interfaces to provide method implementation. Default scope means package-private access when no access modifier is used.

Stream API Coding Questions
Student Class
```
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Student {
    private String name;
    private int age;
    private int score;
    private String gender;

    public Student(String name, int age, int score, String gender) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }

    public String getGender() {
        return gender;
    }
}
```

Create a List of Students

```
List<Student> list = new ArrayList<>();

list.add(new Student("Alice", 20, 95, "girl"));
list.add(new Student("Amy", 21, 88, "girl"));
list.add(new Student("Bob", 20, 55, "boy"));
list.add(new Student("Alex", 22, 70, "boy"));
list.add(new Student("Cindy", 21, 60, "girl"));
```

1. Find all students whose names start with A

```
List<Student> result = list.stream()
        .filter(student -> student.getName().startsWith("A"))
        .collect(Collectors.toList());
```

To get only names:

```
List<String> names = list.stream()
        .map(Student::getName)
        .filter(name -> name.startsWith("A"))
        .collect(Collectors.toList());
```

2. Get the sum of all students' scores

```
int totalScore = list.stream()
        .mapToInt(Student::getScore)
        .sum();
```

3. Find all students whose score is greater than or equal to 60

```
List<Student> passedStudents = list.stream()
        .filter(student -> student.getScore() >= 60)
        .collect(Collectors.toList());
```

4. Retrieve all students' names

```
List<String> studentNames = list.stream()
        .map(Student::getName)
        .collect(Collectors.toList());
```

5. Count the frequency of each age

```
Map<Integer, Long> ageFrequency = list.stream()
        .collect(Collectors.groupingBy(
                Student::getAge,
                Collectors.counting()
        ));
```

6. Count the number of boys and girls using groupingBy

```
Map<String, Long> genderCount = list.stream()
        .collect(Collectors.groupingBy(
                Student::getGender,
                Collectors.counting()
        ));
```

7. Count the number of boys and girls using Collectors.toMap

```
Map<String, Long> genderCountMap = list.stream()
        .collect(Collectors.toMap(
                Student::getGender,
                student -> 1L,
                Long::sum
        ));
```

groupingBy is more readable for classification problems. toMap is useful when we want to manually define the key, value, and merge logic.

Intermediate Operation vs Terminal Operation
Intermediate Operation

Intermediate operations return another stream.

They are lazy, meaning they do not execute until a terminal operation is called.

Examples:

filter()
map()
flatMap()
sorted()
distinct()
limit()
Terminal Operation

Terminal operations produce a final result.

Examples:

collect()
forEach()
count()
sum()
reduce()
anyMatch()

```
List<String> names = list.stream()
        .filter(student -> student.getScore() >= 60) // intermediate
        .map(Student::getName)                       // intermediate
        .collect(Collectors.toList());               // terminal

```

Intermediate operations transform a stream and return another stream. Terminal operations trigger execution and produce a final result.

Coding: Count Frequency of Each Character

Given a char array:

char[] chars = {'a', 'b', 'a', 'c', 'b', 'a'};

Solution:

```
Map<Character, Long> frequency = new String(chars)
        .chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));
```

Output:

```
{a=3, b=2, c=1}
Stream API: map() vs flatMap()
map()
```

map() transforms each element into another element.

```
List<String> names = Arrays.asList("Alice", "Bob");

List<Integer> lengths = names.stream()
        .map(String::length)
        .collect(Collectors.toList());
```

Result:

[5, 3]
flatMap()

flatMap() transforms each element into a stream, then flattens all streams into one stream.

```
List<List<String>> nestedList = Arrays.asList(
        Arrays.asList("Alice", "Bob"),
        Arrays.asList("Cindy", "David")
);

List<String> flatList = nestedList.stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
```

Result:

[Alice, Bob, Cindy, David]

map is one-to-one transformation. flatMap is one-to-many transformation and flattens nested structures.