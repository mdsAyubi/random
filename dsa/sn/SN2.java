import java.util.*;

public class SN2 {
    public static void main(String[] args) {

    }
}

class ELinkedList<E> {
    String id;
    Node<E> root;

    ELinkedList(String id) {
        this.root = null;
        this.id = id;
    }

    /**
     * Creates the node for the element and adds the current id in the list of ids
     * of the node
     * 
     * @param elem
     * @return
     */
    public Node<E> createNode(E elem) {
        // traverse to the end
        // add to the next nodes list of the node with the right id
        Node<E> node = new Node();
        node.id = new ArrayList<>();
        node.id.add(id);
        node.elem = elem;
        node.next = new ArrayList<>();
        return node;
    }

    public void add(E elem) {
        Node<E> node = createNode(elem);
        Node<E> currentNode = root;

        while (currentNode.next != null && !currentNode.next.isEmpty()) {
            currentNode = next(currentNode);
        }
        currentNode.next.add(node);
    }

    /**
     * Navigate based on the id of the current linked list
     * 
     * @return
     */
    public List<Node<E>> navigate() {
        List<Node<E>> nodes = new ArrayList<>();
        Node<E> currentNode = root;

        while (currentNode != null) {
            nodes.add(currentNode);
            currentNode = next(currentNode);
        }
        return nodes;
    }

    /**
     * Iterates over all the next nodes of the current node and returns the node
     * which belongs to the current list
     */
    public Node<E> next(Node<E> node) {
        Optional<Node<E>> oNode = node.next.stream().filter(n -> n.id.contains(this.id)).findFirst();
        return oNode.isPresent() ? oNode.get() : null;
    }

}

class Node<E> {
    List<Node<E>> next;
    E elem;
    List<String> id;
}
