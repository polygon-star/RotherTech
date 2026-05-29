1. List vs Set

A List is an ordered collection that allows duplicate elements. We can access elements by index. Examples are ArrayList and LinkedList.

A Set is a collection that does not allow duplicates. Some sets preserve order, like LinkedHashSet, some sort elements, like TreeSet, and some do not guarantee order, like HashSet.

```
List<String> list = new ArrayList<>();
list.add("A");
list.add("A");
System.out.println(list); // [A, A]

Set<String> set = new HashSet<>();
set.add("A");
set.add("A");
System.out.println(set); // [A]
```

2. LinkedList vs ArrayList

ArrayList is backed by a dynamic array. It is fast for random access, because get(index) is O(1), but inserting or deleting in the middle can be O(n).

LinkedList is backed by nodes. It is better for frequent insertions and deletions at the beginning or middle if we already have the node, but random access is slower, O(n).

In most cases, I use ArrayList unless I specifically need queue/deque behavior.

```
List<Integer> arrayList = new ArrayList<>();
List<Integer> linkedList = new LinkedList<>();

arrayList.add(10);
arrayList.add(20);

linkedList.add(10);
linkedList.add(20);
```

3. What is Map Interface?

Map is an interface that stores key-value pairs. Each key must be unique, but values can be duplicated.

Common implementations are HashMap, LinkedHashMap, TreeMap, Hashtable, and ConcurrentHashMap.

```
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 90);
scores.put("Bob", 85);

System.out.println(scores.get("Alice")); // 90
```

4. How does HashMap work?

HashMap stores key-value pairs using hashing. When we put a key-value pair, Java calculates the key’s hashCode(), maps it to a bucket index, and stores the entry there.

If two keys go to the same bucket, that is a hash collision. Java handles it using a linked list or, when the bucket becomes too large, a balanced tree.

Average time complexity for put, get, and remove is O(1), but worst case can be O(n), or O(log n) after treeification.

```
Map<String, Integer> map = new HashMap<>();
map.put("apple", 3);
map.put("banana", 5);

System.out.println(map.get("apple"));
```

5. What is hash collision?

A hash collision happens when two different keys produce the same bucket index in a hash table.

For example, two different objects may have different values but their hash codes map to the same bucket. HashMap handles this by storing multiple entries in the same bucket and comparing keys using equals().

6. What is Collections used for?

Collections is a utility class in Java. It provides static helper methods for collections, such as sorting, reversing, shuffling, finding min/max, and creating immutable or synchronized collections.

```
List<Integer> nums = new ArrayList<>(Arrays.asList(3, 1, 2));

Collections.sort(nums);
System.out.println(nums); // [1, 2, 3]

Collections.reverse(nums);
System.out.println(nums); // [3, 2, 1]
```

7. What is immutable class?

An immutable class is a class whose object state cannot be changed after creation. A common example is String.

To create an immutable class, we usually make the class final, make fields private final, do not provide setters, and return defensive copies for mutable fields.

```
final class Student {
private final String name;
private final int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
```

8. Hashtable vs HashMap vs ConcurrentHashMap

HashMap is not thread-safe and allows one null key and multiple null values.

Hashtable is thread-safe because its methods are synchronized, but it is older and slower. It does not allow null keys or null values.

ConcurrentHashMap is thread-safe and more efficient than Hashtable because it allows concurrent reads and updates with finer-grained locking. It does not allow null keys or null values.

```
Map<String, Integer> hashMap = new HashMap<>();
Map<String, Integer> hashtable = new Hashtable<>();
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
```

9. String vs StringBuilder vs StringBuffer

String is immutable. Every modification creates a new object.

StringBuilder is mutable and not thread-safe. It is faster and commonly used for string concatenation in a single-threaded environment.

StringBuffer is mutable and thread-safe because its methods are synchronized, but it is slower than StringBuilder.

```
StringBuilder sb = new StringBuilder();
sb.append("Hello");
sb.append(" Java");
System.out.println(sb.toString());
```

10. Why override `hashCode()` and `equals()` at the same time?

Because collections like HashMap and HashSet use both methods.

`hashCode()` decides the bucket location. equals() checks whether two objects are actually equal.

If two objects are equal according to `equals()`, they must have the same `hashCode()`. Otherwise, HashMap or HashSet may behave incorrectly.

```
class Person {
private String name;
private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;

        Person other = (Person) obj;
        return age == other.age && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
```

Common Data Structure APIs
```
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CollectionPractice {
public static void main(String[] args) {

        // List
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("Java");

        System.out.println("List: " + list);
        System.out.println("First item: " + list.get(0));
        list.remove("Python");

        // Set
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Python");
        set.add("Java");

        System.out.println("Set: " + set);
        System.out.println("Contains Java? " + set.contains("Java"));

        // Map
        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 90);
        map.put("Bob", 85);
        map.put("Alice", 95);

        System.out.println("Map: " + map);
        System.out.println("Alice score: " + map.get("Alice"));

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Queue
        Queue<String> queue = new LinkedList<>();
        queue.offer("Task1");
        queue.offer("Task2");
        queue.offer("Task3");

        System.out.println("Queue peek: " + queue.peek());
        System.out.println("Queue poll: " + queue.poll());
        System.out.println("Queue after poll: " + queue);

        // Stack behavior using Deque
        Deque<String> stack = new ArrayDeque<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println("Stack pop: " + stack.pop());
        System.out.println("Stack after pop: " + stack);
    }
}
```

### The difference between List and Set:

A List is ordered and allows duplicates. A Set does not allow duplicates. If I need index-based access or duplicate values, I use List. If I need uniqueness, I use Set.

### ArrayList or LinkedList?
ArrayList is better for random access because it uses an array internally. LinkedList is better for frequent insertions and deletions, especially at the beginning. In practice, I usually choose ArrayList unless I need queue-like behavior.

### What is a Map?
A Map stores key-value pairs. Keys are unique, but values can be duplicated. HashMap is the most common implementation.

### How does HashMap work?
HashMap uses the key’s hashCode to find a bucket. Then it stores the key-value pair in that bucket. When retrieving, it uses hashCode again to find the bucket and equals to find the exact key.

### What is a hash collision?
A hash collision happens when two keys map to the same bucket. HashMap handles this by storing multiple entries in that bucket and comparing keys with equals.

### Why override equals and hashCode together?
Because HashMap and HashSet depend on both. If two objects are equal, they must have the same hashCode. Otherwise, the object may not be found correctly in hash-based collections.

### String vs StringBuilder vs StringBuffer?
String is immutable. StringBuilder is mutable and faster but not thread-safe. StringBuffer is mutable and thread-safe but slower.

### HashMap vs Hashtable vs ConcurrentHashMap?
HashMap is not thread-safe and allows null. Hashtable is synchronized but old and slower. ConcurrentHashMap is thread-safe and more efficient for concurrent applications.