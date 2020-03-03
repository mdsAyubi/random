import java.util.*;

public class SolutionHeap {
    public static void main(String[] args) {
        SolutionHeap s = new SolutionHeap();
        // int[] A = { 4, 10, 3, 5, 1, 2 };
        // s.heapSort(A);
        // printArray(A);

        int[] arr = { 5, 15, 10, 20, 3 };
        s.medianOfRunningIntegers(arr);

    }

    public static void printArray(int[] a) {
        for (int i : a) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
    }

    public void heapSort(int[] arr) {
        Heap h = new Heap(arr);
        printArray(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            h.heapify(arr, i, 0);
        }
    }

    public void medianOfRunningIntegers(int[] arr) {

        PriorityQueue<Integer> smaller = new PriorityQueue<>(Collections.reverseOrder()); // max heap
        PriorityQueue<Integer> greater = new PriorityQueue<>(); // min heap

        int med = arr[0];
        for (int i = 1; i < arr.length; i++) { // simulate stream
            int elem = arr[i];
            // find the heap with fewer elements
            if (smaller.size() > greater.size()) {

                if (elem < med) {
                    // candidate to go in smaller pq
                    greater.add(smaller.remove());
                    smaller.add(elem);
                } else {
                    greater.add(elem);
                }
                med = (smaller.peek() + greater.peek()) / 2;
            }

            if (smaller.size() == greater.size()) {
                if (elem < med) {
                    smaller.add(elem);
                    med = smaller.peek();
                } else {
                    greater.add(elem);
                    med = greater.peek();
                }

            }

            if (greater.size() > smaller.size()) {
                if (elem > med) {
                    smaller.add(greater.remove());
                    greater.add(elem);
                } else {
                    smaller.add(elem);
                }
                med = (smaller.peek() + greater.peek()) / 2;
            }

            System.out.println(med);

        }

    }
}

class Heap {
    int[] arr;
    int capacity;

    public Heap(int[] a) {
        this.arr = a;
        buildHeap(arr);
    }

    private void buildHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }

        for (int i : arr) {
            // System.out.print(i + " ");
        }
    }

    public void heapify(int[] arr, int size, int index) {

        int largest = index;
        int left = left(index);
        int right = right(index);
        int N = size;

        if (left < N && right < N && arr[left] > arr[index] && arr[left] > arr[right]) {
            largest = left;
        }

        if (left < N && right < N && arr[right] > arr[index] && arr[right] > arr[left]) {
            largest = right;
        }

        if (largest != index) {
            int temp = arr[index];
            arr[index] = arr[largest];
            arr[largest] = temp;
            heapify(arr, size, largest);
        }

    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    public int extractMin() {
        int temp = arr[0];

        heapify(arr, arr.length, 0);
        return temp;
    }
}
