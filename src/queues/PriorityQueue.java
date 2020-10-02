package queues;

import java.util.Arrays;

public class PriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T> {

    public static final double RESIZE_FACTOR = 1.5;
    private T[] heap;
    private int size;

    public PriorityQueue() {
        heap = (T[])new Comparable[10];
    }


    @Override
    public void insert(T element) {

        // do we have enough room in the array for the new element
        if (size + 1 >= heap.length) {
            resize();
        }

        size++;
        heap[size] = element;
        swim(size);
    }

    private void swim(int index) {
        while (index / 2 != 0) {
            int parentIndex = index / 2;

            // check if elements are out of order
            if (heap[index].compareTo(heap[parentIndex]) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            }
            else { // otherwise stop
                break;
            }
        }
    }

    private void swap(int index, int parentIndex) {
        T temp = heap[index];
        heap[index] = heap[parentIndex];
        heap[parentIndex] = temp;
    }

    private void resize() {
        // increase the size of the heap by 50%
        int newSize = (int)(heap.length * RESIZE_FACTOR);
        T[] newHeap = (T[])new Comparable[newSize];

        // copy over elements from the old heap to the new
        /*
        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }
         */
        System.arraycopy(heap, 0, newHeap, 0, heap.length);

        heap = newHeap;
    }

    @Override
    public T deleteMin() {
        /*
        if (size == 0) {
            return null;
        }
        T min = heap[1];


        return null;
        */
        // preconditions
        if (isEmpty()) {
            throw new IllegalStateException("The heap is empty");
        }

        // save my result
        T min = heap[1];
        heap[1] = heap[size];
        heap[size] = null;

        // reorder the heap
        size--;
        sink(1);
        return min;
    }

    private void sink(int index) {
        // loop as long as current element has children
        while (index <= size / 2) {
            int left = index * 2;
            int right = index * 2 + 1;

            // search for the smaller child
            int smallest = left;
            if (right <= size && heap[right].compareTo(heap[left]) < 0) {
                    smallest = right;
            }

            // is the current index index smaller than the smallest child
            if (heap[index].compareTo(heap[smallest]) > 0) {
                swap(index, smallest);
                index = smallest; // move current index to smallest child
            }
            else {
                break;
            }
        }
    }



    @Override
    public T peek() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(heap);
    }

}
