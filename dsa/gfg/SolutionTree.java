import java.util.*;

class SolutionTree {
    public static void main(String[] args) {
        // Tree tree = new Tree();
        // tree.root = new TreeNode(12);
        // tree.root.left = new TreeNode(10);
        // tree.root.right = new TreeNode(30);
        // tree.root.right.left = new TreeNode(25);
        // tree.root.right.right = new TreeNode(40);

        // tree.leftView();
        // Tree tree = new Tree();
        // TreeNode root = new TreeNode(3);
        // root.left = new TreeNode(2);
        // root.right = new TreeNode(5);
        // root.left.left = new TreeNode(1);
        // root.left.right = new TreeNode(4);
        // tree.root = root;
        // tree.isBST();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.right = new TreeNode(9);
        System.out.println("Vertical Order traversal is");
        Tree tree = new Tree();
        tree.root = root;
        tree.printVerticalOrder();
    }
}

class Tree {

    TreeNode root;

    public Tree() {

    }

    public void printPreOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.println(head.data);
        printPreOrder(head.left);
        printPreOrder(head.right);
    }

    public void printPostOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        printPostOrder(head.left);
        printPostOrder(head.right);
        System.out.println(head.data);
    }

    public void printInOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        printInOrder(head.left);
        System.out.println(head.data);
        printInOrder(head.right);
    }

    public void insert(int d) {
        insertInorderHelper(root, d);
    }

    private void insertInorderHelper(TreeNode root, int d) {
        if (root == null) {
            TreeNode t = new TreeNode(d);
            root = t;
            return;
        }

        if (root.left == null) {
            TreeNode t = new TreeNode(d);
            root.left = t;
            return;
        }

        if (root.right == null) {
            TreeNode t = new TreeNode(d);
            root.right = t;
            return;
        }
        insertInorderHelper(root.left, d);
        insertInorderHelper(root.right, d);

    }

    /**
     * Print the left view of a binary tree
     * 
     * Approach: Keep a global variable to track maxLevel reached till now Do an
     * preorder traversal, if the current level is greater than the max level, print
     * the node And update the max level. The idea is to print the first node at
     * every level
     */
    public void leftView() {
        leftViewHelper(this.root, 1);
    }

    int maxLevel = 0;

    private void leftViewHelper(TreeNode node, int currentLevel) {
        if (node == null) {
            return;
        }

        // Next level shit
        if (currentLevel > maxLevel) {
            System.out.println(node.data);
            maxLevel = Integer.valueOf(currentLevel);
        }

        leftViewHelper(node.left, currentLevel + 1);
        leftViewHelper(node.right, currentLevel + 1);

    }

    /**
     * Check if a tree is BST or not
     * 
     * Approach: The inorder traversal of the tree should be in ascending order To
     * achieve this, keep track of the prev node, if the current node in traversal
     * is less than the previous node, it is not a BST
     */
    public void isBST() {
        System.out.println(isBSTHelper(root, null));
    }

    private boolean isBSTHelper(TreeNode node, TreeNode prevNode) {
        if (node == null) {
            return true;
        }

        if (!isBSTHelper(node.left, node))
            return false;

        if (prevNode != null && node.data < prevNode.data)
            return false;

        prevNode = node;
        return isBSTHelper(node.right, prevNode);
    }

    public void height() {
        System.out.println(heightHelper(root));
    }

    private int heightHelper(TreeNode root) {
        if (root == null)
            return 0;
        int leftH = heightHelper(root.left);
        int rightH = heightHelper(root.right);

        return Math.max(leftH, rightH) + 1;
    }

    public void printALevel(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.println(root.data);
        } else {
            printALevel(root.left, level - 1);
            printALevel(root.right, level - 1);
        }
    }

    public void printALevel(TreeNode root, int level, boolean ltr) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.println(root.data);
        } else {
            if (ltr) {
                printALevel(root.left, level - 1);
                printALevel(root.right, level - 1);
            } else {
                printALevel(root.right, level - 1);
                printALevel(root.left, level - 1);
            }
        }
    }

    /**
     * Print level order traversal in spiral form
     * 
     * Approach: Print every level from 1 to height of the tree At every level, keep
     * the ltr boolean and toggle it
     */
    public void printLevelOrderInSpiralForm() {
        int height = heightHelper(root);

        boolean ltr = false;
        for (int i = 1; i <= height; i++) {
            printALevel(root, i, ltr);
            ltr = !ltr;
        }
    }

    /**
     * Print the the nodes of the tree in vertical order
     * 
     * Approach: Keep a map of the horizontal distance from the root Print the list
     * in the end
     */
    public void printVerticalOrder() {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        verticalOrderHelper(root, map, 0);
        System.out.println(map.toString());
    }

    private void verticalOrderHelper(TreeNode node, TreeMap<Integer, List<Integer>> map, int hd) {

        if (node == null) {
            return;
        }

        List<Integer> nodesAtThisDistance = map.getOrDefault(hd, new ArrayList<Integer>());
        nodesAtThisDistance.add(node.data);
        map.put(hd, nodesAtThisDistance);

        verticalOrderHelper(node.left, map, hd - 1);
        verticalOrderHelper(node.right, map, hd + 1);

    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    public void levelOrderTraversalWithQueue() {
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);
        q.add(null);

        while (!q.isEmpty()) {

            TreeNode node = q.peek();
            q.remove();

            if (node != null) {

                // operate with node
                System.out.println(node.data);

                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);

            } else if (!q.isEmpty()) {
                q.offer(null);
            }

        }
    }

    public TreeNode lca(TreeNode root, int n1, int n2) {
        if (root == null) {
            return null;
        }

        if (root.data > n1 && root.data < n2) {
            return root;
        }

        else if (root.data > n1 && root.data > n2) {
            return lca(root.left, n1, n2);
        }

        else {
            return lca(root.right, n1, n2);
        }

    }

    TreeNode prevNode, head;

    public void btreeToDLL(TreeNode root) {

        if (root == null) {
            return;
        }

        btreeToDLL(root.left);

        if (root.left == null) {
            head = root;
        } else {
            root.left = prevNode;
            prevNode.right = root;
        }
        prevNode = root;

        btreeToDLL(root.right);

    }

    public static boolean isIdentical(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 != null && root2 != null && root1.data == root2.data && isIdentical(root1.left, root2.left)
                && isIdentical(root1.right, root2.right)) {
            return true;
        }

        return false;
    }

    public static boolean isMirror(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;

        if (root1 == null || root2 == null)
            return false;

        return root1.data == root2.data && isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }

    /**
     * Find the diameter of a tree
     * 
     * The diameter is either the max of diameter of left subtree or right subtree
     * Or the max possible path which goes through the root (i.e left heigh + right
     * height +1)
     * 
     * @param root
     * @return
     */
    public int diameter(TreeNode root) {

        if (root == null)
            return 0;

        int lH = heightHelper(root.left);
        int rH = heightHelper(root.right);

        int ld = diameter(root.left);
        int rd = diameter(root.right);

        return Math.max(Math.max(ld, rd), (lH + rH + 1));

    }

    public boolean isBalanced(TreeNode node) {
        if (node == null) {
            return true;
        }

        int lh = heightHelper(root.left);
        int rh = heightHelper(root.right);

        return Math.abs(lh - rh) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

    // void serialize(Node *root, FILE *fp)
    // {
    // // If current node is NULL, store marker
    // if (root == NULL)
    // {
    // fprintf(fp, "%d ", MARKER);
    // return;
    // }

    // // Else, store current node and recur for its children
    // fprintf(fp, "%d ", root->key);
    // serialize(root->left, fp);
    // serialize(root->right, fp);
    // }

    // // This function constructs a tree from a file pointed by 'fp'
    // void deSerialize(Node *&root, FILE *fp)
    // {
    // // Read next item from file. If theere are no more items or next
    // // item is marker, then return
    // int val;
    // if ( !fscanf(fp, "%d ", &val) || val == MARKER)
    // return;

    // // Else create node with this item and recur for children
    // root = newNode(val);
    // deSerialize(root->left, fp);
    // deSerialize(root->right, fp);
    // }

}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

}