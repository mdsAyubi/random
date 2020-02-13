import java.security.DrbgParameters.Capability;
import java.util.*;

public class SolutionSQ {

    private boolean isMatching(char character1, char character2) {
        // System.out.println("Comparing char1=" + character1 + ", char2=" +
        // character2);
        if (character1 == '(' && character2 == ')')
            return true;
        else if (character1 == '{' && character2 == '}')
            return true;
        else if (character1 == '[' && character2 == ']')
            return true;
        else
            return false;
    }

    public boolean matchParens(String exp) {

        char[] arr = exp.toCharArray();
        Stack s = new Stack(arr.length);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '{' || arr[i] == '(' || arr[i] == '[') {
                s.push(arr[i]);
            } else if (arr[i] == '}' || arr[i] == ')' || arr[i] == ']') {
                // Found a closing and stack is empty
                if (s.isEmpty()) {
                    // System.out.println("Found empty stack");
                    return false;
                }
                // Popped bracket does not match
                char ch = (char) s.pop();
                if (!isMatching(ch, arr[i])) {
                    // System.out.println("Found unmatched char" + "ch=" + ch + "arr[i]=" + arr[i]);
                    return false;
                }
            } else {
                continue;
            }
        }
        if (!s.isEmpty()) {
            // System.out.println("Non empty at the end...");
            return false;
        }

        return true;
    }

    /**
     * Given an array, print the Next Greater Element (NGE) for every element.
     * 
     * Aproach: create a stack, put the first element in the stack
     * 
     * Iterate from 2nd to last item, if the element in the stack is less than the
     * current element, keep popping. Then put the current element in the stack.
     * After exhausting all items, pop all the items and print -1 for them
     * 
     * @param arr
     */
    public void nextGreaterElement(int[] arr) {
        Stack st = new Stack(arr.length);

        st.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {

            int item = arr[i];

            while (!st.isEmpty() && st.peek() < item) {
                System.out.println("NGE for " + st.peek() + " is=" + item);
                st.pop();
            }

            st.push(item);

        }

        while (!st.isEmpty()) {
            System.out.println("NGE for " + st.pop() + " is=" + -1);
        }
    }

    /**
     * Implement a queue using two stack
     * 
     * Approach: Make the dequeue costly
     * 
     * Enqueue into stack 1. While dequeing if the stack2 is empty then only pop
     * everything from stack1 and push into stack2, otherwise onyl pop from stack2
     * and return.
     * 
     * Or use function call stack as the second stack for dequeue
     */
    public void queuUsingTwoStack() {

    }

    /**
     * Implement stack using two queueu
     * 
     * Approach: make push costly by enqueuing two second queue first then deeuque
     * everything from first queue and enqueue it to second queue. Then swap the
     * queue names. This is to ensure that the newly inserted item is at the front
     * of queue.
     * 
     * Pop from the first queue for dequeing.
     */
    public void stackUsingTwoQueue() {

    }

    /**
     * Implement getmin operation on a stack in contant time
     * 
     * Approach: Keep another stack, while pushing if the current element is smaller
     * than the min, push to aux stack as well. While popping, if the popped element
     * is the current min, pop from the aux stack too
     */
    public void stackWithGetMin() {

    }

    /**
     * Find the starting point of a circular tour of gas stattions
     * 
     * Approach: Keep track of surplus which petrol available and distance till next
     * pump If surplus is positive, make that node potential start, else try next
     * node and update deficit
     * 
     * At the end of the loop if surplus - deficit >=0, then tour is possible
     * 
     * @param p
     * @param d
     */
    public void circularTour(int[] p, int[] d) {

        int surplus = 0;
        int deficit = 0;
        int start = -1;
        for (int i = 0; i < p.length; i++) {
            if (p[i] - d[i] < 0) {
                deficit += p[i] - d[i];
                surplus = 0;
            } else {
                surplus += p[i] - d[i];
                start = i;
            }
        }

        if (surplus - deficit >= 0) {
            System.out.println("Start from: " + start);
        } else {
            System.out.println("No solution");
        }

    }

    /**
     * Given an array and an integer K, find the maximum for each and every
     * contiguous subarray of size k.
     * 
     * Approach: Use sliding window to keep track of the max index and min index of
     * the current window Drop useless elements outside of the window.
     * 
     * Or use a heap
     * 
     * @param arr
     * @param k
     */
    public void maxContiguousSubarraySumOfSizeK(int[] arr, int k) {

    }

    public static void main(String[] args) {
        SolutionSQ s = new SolutionSQ();

        // String str = "{()}[]";
        // System.out.println(s.matchParens(str));

        // int arr[] = { 11, 13, 21, 3 };
        // s.nextGreaterElement(arr);

        int[] arr1 = { 4, 6, 7, 4 };
        int[] arr2 = { 6, 5, 3, 5 };
        s.circularTour(arr1, arr2);
    }
}

class Stack {
    private int top;
    private int[] arr;

    private static final int DEFAULT_CAP = 16;

    public Stack(int capacity) {
        arr = new int[capacity];
        top = -1;
    }

    public Stack() {
        arr = new int[DEFAULT_CAP];
        top = -1;
    }

    public void push(int d) {
        if (top == arr.length - 1) {
            throw new RuntimeException("Stack overflow!");
        }
        arr[++top] = d;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow!");
        }
        return arr[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

}

class LRUCache<E extends Comparable<E>> {

    Deque<E> deque;
    Set<E> set;
    int capacity;

    public LRUCache(int capacity) {
        deque = new ArrayDeque<>(capacity);
        set = new HashSet<>(capacity);
        this.capacity = capacity;
    }

    /**
     * Keeps the elements added to the cache, the least resently used is always the
     * last
     * 
     * @param element
     * @return
     */
    public E get(E element) {
        // If the element is present, then move to front
        if (set.contains(element)) {
            deque.remove(element);
            deque.addFirst(element);
        } else {
            // If the element is not present and the capacity is full, remove the last
            // element
            if (deque.size() == capacity) {
                deque.removeLast();
                set.remove(element);
            }
        }
        deque.add(element);
        set.add(element);
        return element;
    }

}