import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DataStructurePractice {
    public static void main(String[] args) {

        // List practice
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Apple");

        System.out.println("List: " + fruits);
        System.out.println("First fruit: " + fruits.get(0));
        fruits.remove("Banana");
        System.out.println("After remove: " + fruits);

        // Set practice
        Set<String> uniqueFruits = new HashSet<>();
        uniqueFruits.add("Apple");
        uniqueFruits.add("Banana");
        uniqueFruits.add("Apple");

        System.out.println("Set: " + uniqueFruits);
        System.out.println("Contains Apple? " + uniqueFruits.contains("Apple"));

        // Map practice
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 90);
        scores.put("Bob", 85);
        scores.put("Alice", 95);

        System.out.println("Map: " + scores);
        System.out.println("Alice score: " + scores.get("Alice"));

        for (String key : scores.keySet()) {
            System.out.println(key + " = " + scores.get(key));
        }

        // Queue practice
        Queue<String> queue = new LinkedList<>();
        queue.offer("Task 1");
        queue.offer("Task 2");
        queue.offer("Task 3");

        System.out.println("Queue: " + queue);
        System.out.println("Peek: " + queue.peek());
        System.out.println("Poll: " + queue.poll());
        System.out.println("After poll: " + queue);

        // Stack practice using Deque
        Deque<String> stack = new ArrayDeque<>();
        stack.push("Page 1");
        stack.push("Page 2");
        stack.push("Page 3");

        System.out.println("Stack top: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("Stack after pop: " + stack);

        // Collections utility
        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 9, 1));
        Collections.sort(nums);
        System.out.println("Sorted nums: " + nums);

        // ConcurrentHashMap
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("A", 1);
        concurrentMap.put("B", 2);
        System.out.println("ConcurrentHashMap: " + concurrentMap);
    }
}
