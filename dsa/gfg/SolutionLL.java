
public class SolutionLL {
    public static void main(String[] args) {
        LinkedL ll1 = new LinkedL();
        ll1.insertLast(1);
        ll1.insertLast(2);
        ll1.insertLast(3);
        ll1.insertLast(4);
        ll1.insertLast(5);
        ll1.printList();
        LinkedL ll2 = new LinkedL();
        ll2.insertAtHead(0);
        ll2.insertAtHead(-1);
        ll2.insertAtHead(-2);
        ll2.insertAtHead(-3);
        ll2.printList();
        SolutionLL s = new SolutionLL();
        // s.findMiddle(ll);
        // ll.reverse();
        // ll.printList();
        // ll.reverse();
        // ll.printList();

        // ll.reverseRecursive();
        // ll.printList();
        // ll.reverseRecursive();
        // ll.printList();

        s.sortedMerge(ll1, ll2);
    }

    /**
     * Find middle of a linked list
     * 
     * Approach: run two pointers, run one at twice the speed of first
     */
    public void findMiddle(LinkedL ll) {

        Node p1 = ll.head;
        Node p2 = ll.head;

        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        System.out.println(p1.data);

    }

    /**
     * Rotate a list by k
     * 
     * Approach: move to kth node, get the last node Point last node to head, point
     * head to (k+1)th node and make the next of kth node as null
     */
    public void rotateList(LinkedL l, int k) {

    }

    /**
     * Reverse in groups of k
     * 
     * Approach: Take groups of k using count, the reverse Point manage the pointer
     * of head accordingly
     */
    public void reverseInKGroups(LinkedL l, int k) {

    }

    /**
     * Write a function to get the intersection point of two Linked Lists
     * 
     * Approach: Find the difference of the node betweent the two lists
     * 
     * In the first list, move to the d the node. Then traverse both the list
     * together untill an intersecting node is note found.
     */
    public void intersectionYShapedLists(LinkedL l1, LinkedL l2) {
    }

    /**
     * Detect a loop in a loop
     * 
     * Approach: Run slow and fast pointer, when they intersect, thet is the loop If
     * not then not
     */
    public void detectLoop(LinkedL ll) {

    }

    /**
     * Detect and remove loop from linked list
     * 
     * Approach: detect loop first using the previoous method. Count the number of
     * nodes in the loop say k. Start one pointer from the head and another at k.
     * Move both pointer at the same pace, they will meet at the beginning of the
     * loop. Get the node before this node and make its next as null
     * 
     * @param ll
     */
    public void removeLoop(LinkedL ll) {
    }

    /**
     * Find nth node from end
     * 
     * Approach: Take two pointers and initialize them to head. Move one n times
     * from head. Then move both pointers at the same speed. When the second pointer
     * reaches the end The first will reach nth point from end
     * 
     * @param ll
     */
    public void nthNodeFromEnd(LinkedL ll) {

    }

    private Node sortedMergeRecursive(Node head1, Node head2) {

        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;

        if (head1.data < head2.data) {
            head1.next = sortedMergeRecursive(head1.next, head2);
            return head1;
        } else {
            head2.next = sortedMergeRecursive(head1, head2.next);
            return head2;
        }

    }

    /**
     * Merge two sorted lists
     * 
     * Approach: Try to compare the two nodes, if first smaller, merge the first
     * next with the rest else merge seconds next with the rest
     * 
     * @param l1
     * @param l2
     */
    public void sortedMerge(LinkedL l1, LinkedL l2) {

        Node head1 = l1.head;
        Node head2 = l2.head;

        LinkedL sortedL = new LinkedL();
        sortedL.head = sortedMergeRecursive(head1, head2);
        sortedL.printList();

    }

    /**
     * Pairwise swap elements of a given linked list
     * 
     * Aproach: swap the node with the next node and move two pointers ahead
     * 
     * @param l1
     */
    public void pairWiseSwap(LinkedL l1) {

    }

    /**
     * Add two numbers represented by linked lists
     * 
     * Approach: Interate the two lists while one list exists and put the result sum
     * in a temp node
     * 
     * If the sum > 10 , keep a carry number, put the data as sum%10 in the new node
     * After both lists are exhausted, add a carry node if it is greater than 0
     */
    public void addTwoNumbers(LinkedL l1, LinkedL l2) {

    }

    /**
     * Check if a linked list is a palindrome
     * 
     * Approach: Put the nodes in a stack, pop and check while iterating
     * 
     * @param l
     */
    public void isLLAPalindrome(LinkedL l) {

    }

    /**
     * Given a list of 0,1,2 : sort the numbers
     * 
     * Approach1: Count the 0,1,2 and iterate again and fill the nodes Approaac2:
     * Use the dutch flag sorting algorithm with three pointers
     * 
     * @param l
     */
    public void sort012InLL(LinkedL l) {

    }

    /**
     * Delete a node without the head pointer
     * 
     * Approach: Copt the data of the next pointer to the current pointer Make the
     * next pointer point to the next of next pointer
     * 
     * @param n
     */
    public void deleteNodeWithoutHeadPointer(Node n) {

    }

}

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

class LinkedL {
    Node head;

    public LinkedL() {
    }

    public void insertLast(int data) {
        Node node = new Node(data);
        Node p = head;

        if (head == null) {
            head = node;
            return;
        }

        while (p.next != null) {
            p = p.next;
        }
        p.next = node;
    }

    public void insertAtHead(int data) {
        Node node = new Node(data);

        if (head == null) {
            head = node;
            return;
        }

        node.next = head;
        head = node;

    }

    public void printList() {
        Node p = head;

        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public Node reverse() {

        Node current = head;
        Node prev = null;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;

            prev = current;
            current = next;

        }

        head = prev;
        return head;

    }

    private Node reverseRecursiveHelper(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node rest = reverseRecursiveHelper(head.next);
        head.next.next = head;

        head.next = null;

        return rest;
    }

    public void reverseRecursive() {
        this.head = reverseRecursiveHelper(head);
    }

}