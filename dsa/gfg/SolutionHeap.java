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

    /**
     * Heap sort(https://www.geeksforgeeks.org/heap-sort/)
     * 
     * Approach:
     * 
     * 1. Build a max heap from the input data.
     * 
     * 2. At this point, the largest item is stored at the root of the heap. Replace
     * it with the last item of the heap followed by reducing the size of heap by 1.
     * Finally, heapify the root of tree.
     * 
     * 3. Repeat above steps while size of heap is greater than 1.
     * 
     * @param arr
     */
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

    /**
     * Median in a stream of
     * integers(https://www.geeksforgeeks.org/median-of-stream-of-integers-running-integers/)
     * 
     * Approach: For every newly read element, insert it into either max heap or min
     * heap and calculate the median based on the following conditions:
     * 
     * If the size of max heap is greater than size of min heap and the element is
     * less than previous median then pop the top element from max heap and insert
     * into min heap and insert the new element to max heap else insert the new
     * element to min heap. Calculate the new median as average of top of elements
     * of both max and min heap.
     * 
     * If the size of max heap is less than size of min heap and the element is
     * greater than previous median then pop the top element from min heap and
     * insert into max heap and insert the new element to min heap else insert the
     * new element to max heap. Calculate the new median as average of top of
     * elements of both max and min heap.
     * 
     * If the size of both heaps are same. Then check if current is less than
     * previous median or not. If the current element is less than previous median
     * then insert it to max heap and new median will be equal to top element of max
     * heap. If the current element is greater than previous median then insert it
     * to min heap and new median will be equal to top element of min heap.
     * 
     * @param arr
     */
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

    /**
     * Rearrange characters in a string such that no two adjacent are
     * same(https://www.geeksforgeeks.org/rearrange-characters-string-no-two-adjacent/).
     * 
     * Approach:
     * 
     * 1. Build a Priority_queue or max_heap, pq that stores characters and their
     * frequencies. Priority_queue or max_heap is built on the bases of the
     * frequency of character.
     * 
     * 2. Create a temporary Key that will be used as the previously visited element
     * (the previous element in the resultant string. Initialize it { char = ‘#’ ,
     * freq = ‘-1’ }
     * 
     * 3. While pq is not empty. Pop an element and add it to the result. Decrease
     * frequency of the popped element by ‘1’. Push the previous element back into
     * the priority_queue if it’s frequency > 0. Make the current element as the
     * previous element for the next iteration.
     * 
     * @param str
     */
    public void rearrangeString(String str) {
        int n = str.length();

        // Store frequencies of all characters in string
        int[] count = new int[26];

        for (int i = 0; i < n; i++)
            count[str.charAt(i) - 'a']++;

        // Insert all characters with their frequencies
        // into a priority_queue
        PriorityQueue<Key> pq = new PriorityQueue<>(new KeyComparator());
        for (char c = 'a'; c <= 'z'; c++) {
            int val = c - 'a';
            if (count[val] > 0)
                pq.add(new Key(count[val], c));
        }

        // 'str' that will store resultant value
        str = "";

        // work as the previous visited element
        // initial previous element be. ( '#' and
        // it's frequency '-1' )
        Key prev = new Key(-1, '#');

        // traverse queue
        while (pq.size() != 0) {

            // pop top element from queue and add it
            // to string.
            Key k = pq.peek();
            pq.poll();
            str = str + k.ch;

            // If frequency of previous character is less
            // than zero that means it is useless, we
            // need not to push it
            if (prev.freq > 0)
                pq.add(prev);

            // make current character as the previous 'char'
            // decrease frequency by 'one'
            (k.freq)--;
            prev = k;
        }

        // If length of the resultant string and original
        // string is not same then string is not valid
        if (n != str.length())
            System.out.println(" Not valid String ");
        else
            System.out.println(str);
    }

    /**
     * Merge k sorted linked
     * lists(https://www.geeksforgeeks.org/merge-k-sorted-linked-lists-set-2-using-min-heap/).
     * 
     * Approach:
     * 
     * 1. Create an output array of size n*k.
     * 
     * 2. Create a min heap of size k and insert 1st element in all the arrays into
     * the heap
     * 
     * 3. Repeat following steps n*k times.
     * 
     * a) Get minimum element from heap (minimum is always at root) and store it in
     * output array.
     * 
     * b) Replace heap root with next element from the array from which the element
     * is extracted. If the array doesn’t have any more elements, then replace root
     * with infinite. After replacing the root, heapify the tree.
     * 
     * 
     * 
     * @param arr
     * @param k
     * @return
     */
    public static Node mergeKSortedLists(Node arr[], int k) {
        Node head = null, last = null;

        // priority_queue 'pq' implemeted as min heap with the
        // help of 'compare' function
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.data - b.data;
            }
        });

        // push the head nodes of all the k lists in 'pq'
        for (int i = 0; i < k; i++)
            if (arr[i] != null)
                pq.add(arr[i]);

        // loop till 'pq' is not empty
        while (!pq.isEmpty()) {
            // get the top element of 'pq'
            Node top = pq.peek();
            pq.remove();

            // check if there is a node next to the 'top' node
            // in the list of which 'top' node is a member
            if (top.next != null)
                // push the next node in 'pq'
                pq.add(top.next);

            // if final merged list is empty
            if (head == null) {
                head = top;
                // points to the last node so far of
                // the final merged list
                last = top;
            } else {
                // insert 'top' at the end of the merged list so far
                last.next = top;
                // update the 'last' pointer
                last = top;
            }
        }
        // head node of the required merged list
        return head;
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

class KeyComparator implements Comparator<Key> {

    // Overriding compare()method of Comparator
    public int compare(Key k1, Key k2) {
        if (k1.freq < k2.freq)
            return 1;
        else if (k1.freq > k2.freq)
            return -1;
        return 0;
    }
}

class Key {
    int freq; // store frequency of character
    char ch;

    Key(int val, char c) {
        freq = val;
        ch = c;
    }
}

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}
