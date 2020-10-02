package test;

import queues.IPriorityQueue;
import queues.PriorityQueue;

import java.util.Random;

public class InformalQueueTest {
    public static void main(String[] args) {
        // always program against an interface
        IPriorityQueue<Integer> numberHeap = new PriorityQueue<>();

        // add a few numbers
        numberHeap.insert(5);
        numberHeap.insert(3);
        numberHeap.insert(2);
        numberHeap.insert(7);
        numberHeap.insert(10);

        System.out.println(numberHeap);
        System.out.println("Num elements in heap: " + numberHeap.size());

        // test finding the minimum elements in our heap
        while (!numberHeap.isEmpty()) {
            int number = numberHeap.deleteMin();
            System.out.println("Pulled from heap: " + number);
        }
        System.out.println();

        // perform a stress test
        Random random = new Random();
        for (int i = 1; i < 100; i++) {
            numberHeap.insert(random.nextInt(1000));
        }

        while (!numberHeap.isEmpty()) {
            int number = numberHeap.deleteMin();
            System.out.println("Pulled from heap: " + number);
        }
    }
}
