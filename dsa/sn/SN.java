import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SN {

    public static void main(String[] args) {

    }

    public void rightView(TreeNode root) {
        int maxLevel = 0;
        Map<Integer, TreeNode> levelToNodeMap = new HashMap<>();
        rightViewHelper(node, 0, levelToNodeMap);
    }

    public void rightViewHelper(TreeNode node, int level, Map<Integer, TreeNode> levelToNodeMap) {
        if (root == null) {
            return;
        }

        if (!levelToNodeMap.containsKey(level)) {
            System.out.println(node.data);
            levelToNodeMap.put(level, node);
        }

        rightViewHelper(node.right, level + 1, levelToNodeMap);
        rightViewHelper(node.left, level + 1, levelToNodeMap);
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int data;
}