import java.util.*;
import java.util.prefs.NodeChangeEvent;

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
     * Print the left view of a binary
     * tree(https://www.geeksforgeeks.org/print-left-view-binary-tree/).
     * 
     * Approach: Keep a global variable to track maxLevel reached till now. Do a
     * preorder traversal, if the current level is greater than the max level, print
     * the node. And update the max level. The idea is to print the first node at
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
     * Check if a tree is BST or
     * not(https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/).
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

    /**
     * Print the bottom view of a
     * tree(https://www.geeksforgeeks.org/bottom-view-binary-tree/).
     * 
     * Approach: 1. We put tree nodes in a queue for the level order traversal. 2.
     * Start with the horizontal distance(hd) 0 of the root node, keep on adding
     * left child to queue along with the horizontal distance as hd-1 and right
     * child as hd+1. 3. Also, use a TreeMap which stores key value pair sorted on
     * key. 4. Every time, we encounter a new horizontal distance or an existing
     * horizontal distance put the node data for the horizontal distance as key. For
     * the first time it will add to the map, next time it will replace the value.
     * This will make sure that the bottom most element for that horizontal distance
     * is present in the map and if you see the tree from beneath that you will see
     * that element.
     */
    public void bottomView(NodeWithHd root) {
        if (root == null)
            return;

        int hd = 0;

        Map<Integer, Integer> map = new TreeMap<>();
        Queue<NodeWithHd> queue = new LinkedList<NodeWithHd>();

        root.hd = hd;
        queue.add(root);

        // Loop until the queue is empty (standard level order loop)
        while (!queue.isEmpty()) {
            NodeWithHd temp = queue.remove();
            hd = temp.hd;
            map.put(hd, temp.data);

            if (temp.left != null) {
                temp.left.hd = hd - 1;
                queue.add(temp.left);
            }
            // If the dequeued node has a left child add it to the
            // queue with a horizontal distance hd+1.
            if (temp.right != null) {
                temp.right.hd = hd + 1;
                queue.add(temp.right);
            }
        }
        System.out.print(map.entrySet().toString());
    }

    /**
     * Find the Maximum Depth or Height of a
     * Tree(https://www.geeksforgeeks.org/write-a-c-program-to-find-the-maximum-depth-or-height-of-a-tree/).
     */
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
     * Print level order traversal in spiral
     * form(https://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/).
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
     * Connect nodes at same
     * level(https://www.geeksforgeeks.org/connect-nodes-at-same-level/).
     * 
     * Approach: set nextRight in Pre Order fashion to make sure that the nextRight
     * of parent is set before its children. When we are at node p, we set the
     * nextRight of its left and right children. Since the tree is complete tree,
     * nextRight of p’s left child (p->left->nextRight) will always be p’s right
     * child, and nextRight of p’s right child (p->right->nextRight) will always be
     * left child of p’s nextRight (if p is not the rightmost node at its level). If
     * p is the rightmost node, then nextRight of p’s right child will be NULL.
     * 
     * @param p
     */
    void connect(TreeNodeWithNextRight p) {

        // Set the nextRight for root
        p.nextRight = null;

        // Set the next right for rest of the nodes (other
        // than root)
        connectRecur(p);
    }

    /*
     * Set next right of all descendents of p. Assumption: p is a compete binary
     * tree
     */
    void connectRecur(TreeNodeWithNextRight p) {
        // Base case
        if (p == null)
            return;

        // Set the nextRight pointer for p's left child
        if (p.left != null)
            p.left.nextRight = p.right;

        // Set the nextRight pointer for p's right child
        // p->nextRight will be NULL if p is the right most child
        // at its level
        if (p.right != null)
            p.right.nextRight = (p.nextRight != null) ? p.nextRight.left : null;

        // Set nextRight for other nodes in pre order fashion
        connectRecur(p.left);
        connectRecur(p.right);
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

    /**
     * Lowest Common Ancestor in a
     * BST(https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/).
     * 
     * Approach: he main idea of the solution is, while traversing from top to
     * bottom, the first node n we encounter with value between n1 and n2, i.e., n1
     * < n < n2 or same as one of the n1 or n2, is LCA of n1 and n2 (assuming that
     * n1 < n2). So just recursively traverse the BST in, if node’s value is greater
     * than both n1 and n2 then our LCA lies in left side of the node, if it’s is
     * smaller than both n1 and n2, then LCA lies on right side. Otherwise root is
     * LCA (assuming that both n1 and n2 are present in BST)
     */
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

    /**
     * Convert a given Binary Tree to Doubly Linked
     * List(https://www.geeksforgeeks.org/convert-given-binary-tree-doubly-linked-list-set-3/).
     * 
     * Approach:The idea is to do inorder traversal of the binary tree. While doing
     * inorder traversal, keep track of the previously visited node in a variable
     * say prev. For every visited node, make it next of prev and previous of this
     * node as prev.
     * 
     * @param root
     */
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

    /**
     * Write Code to Determine if Two Trees are
     * Identical(https://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/).
     * 
     * Approach: Traverse both trees simultaneously, compare data and children of
     * the trees.
     * 
     * @param root1
     * @param root2
     * @return
     */
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

    /**
     * Symmetric
     * Tree(https://www.geeksforgeeks.org/symmetric-tree-tree-which-is-mirror-image-of-itself/).
     * 
     * Approach: For two trees to be mirror images, the following three conditions
     * must be true,
     * 
     * 1 - Their root node's key must be same
     * 
     * 2 - left subtree of left tree and right subtree of right tree have to be
     * mirror images
     * 
     * 3 - right subtree of left tree and left subtree of right tree have to be
     * mirror images
     * 
     * 
     * @param root1
     * @param root2
     * @return
     */
    public static boolean isMirror(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;

        if (root1 == null || root2 == null)
            return false;

        return root1.data == root2.data && isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }

    /**
     * Maximum Path Sum in a Binary
     * Tree(https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/).
     * 
     * Appraoch: For each node there can be four ways that the max path goes through
     * the node: 1. Node only 2. Max path through Left Child + Node 3. Max path
     * through Right Child + Node 4. Max path through Left Child + Node + Max path
     * through Right Child
     * 
     * The idea is to keep track of four paths and pick up the max one in the end.
     * 
     * @param node
     * @param res
     * @return
     */
    int findMaxUtil(TreeNode node, Res res) {

        // Base Case
        if (node == null)
            return 0;

        // l and r store maximum path sum going through left and
        // right child of root respectively
        int l = findMaxUtil(node.left, res);
        int r = findMaxUtil(node.right, res);

        // Max path for parent call of root. This path must
        // include at-most one child of root
        int max_single = Math.max(Math.max(l, r) + node.data, node.data);

        // Max Top represents the sum when the Node under
        // consideration is the root of the maxsum path and no
        // ancestors of root are there in max sum path
        int max_top = Math.max(max_single, l + r + node.data);

        // Store the Maximum Result.
        res.val = Math.max(res.val, max_top);

        return max_single;
    }

    /**
     * Find the diameter of a
     * tree(https://www.geeksforgeeks.org/diameter-of-a-binary-tree/).
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

    /**
     * How to determine if a binary tree is
     * height-balanced(https://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/)
     * 
     * @param node
     * @return
     */
    public boolean isBalanced(TreeNode node) {
        if (node == null) {
            return true;
        }

        int lh = heightHelper(root.left);
        int rh = heightHelper(root.right);

        return Math.abs(lh - rh) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

    // A wrapper class used to modify height across
    // recursive calls.
    class Height {
        int height = 0;
    }

    /* Returns true if binary tree with root as root is height-balanced */
    boolean isBalanced(TreeNode root, Height height) {
        /* If tree is empty then return true */
        if (root == null) {
            height.height = 0;
            return true;
        }

        /* Get heights of left and right sub trees */
        Height lheight = new Height(), rheight = new Height();
        boolean l = isBalanced(root.left, lheight);
        boolean r = isBalanced(root.right, rheight);
        int lh = lheight.height, rh = rheight.height;

        /*
         * Height of current node is max of heights of left and right subtrees plus 1
         */
        height.height = (lh > rh ? lh : rh) + 1;

        /*
         * If difference between heights of left and right subtrees is more than 2 then
         * this node is not balanced so return 0
         */
        if ((lh - rh >= 2) || (rh - lh >= 2))
            return false;

        /*
         * If this node is balanced and left and right subtrees are balanced then return
         * true
         */
        else
            return l && r;
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

class NodeWithHd {
    int data;
    int hd;
    NodeWithHd left;
    NodeWithHd right;

    public NodeWithHd(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

}

class TreeNodeWithNextRight {
    TreeNodeWithNextRight nextRight;
    int data;
    TreeNodeWithNextRight left;
    TreeNodeWithNextRight right;

    public TreeNodeWithNextRight(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.nextRight = null;
    }

}

class Res {
    public int val;
}