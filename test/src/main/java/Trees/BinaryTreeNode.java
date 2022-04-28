package Trees;

public class BinaryTreeNode {

    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public BinaryTreeNode insertLeft(int leftValue) {
        this.left = new BinaryTreeNode(leftValue);
        return this.left;
    }

    public BinaryTreeNode insertRight(int rightValue) {
        this.right = new BinaryTreeNode(rightValue);
        return this.right;
    }
}

class TestBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode node = new BinaryTreeNode(10);
        node.left = new BinaryTreeNode(6);
        node.right = new BinaryTreeNode(15);

        node.left.right = new BinaryTreeNode(7);
        node.left.left = new BinaryTreeNode(5);

        node.right.right = new BinaryTreeNode(16);
        node.right.left = new BinaryTreeNode(12);

        int res = findSecondLargestRecurse(node);
        int res2 = findSecondLargest(node);
        System.out.println(" second largest node " + res + " asdsd  " +res2);
    }

    public static int findLargestRecurse(BinaryTreeNode rootNode) {
        if (rootNode == null) {
            throw new IllegalArgumentException("Tree must have at least 1 node");
        }

        if (rootNode.right != null) {
            return findLargestRecurse(rootNode.right);
        }
        return rootNode.value;
    }
    public static int findSecondLargestRecurse(BinaryTreeNode rootNode) {

        if (rootNode == null || (rootNode.left == null
                && rootNode.right == null)) {
            throw new IllegalArgumentException("Tree must have at least 2 nodes");
        }
        // case: we're currently at largest, and largest has a left subtree,
        // so 2nd largest is largest in said subtree
        if (rootNode.left != null && rootNode.right == null) {
            return findLargestRecurse(rootNode.left);
        }
        if (rootNode.right != null
                && rootNode.right.left == null
                && rootNode.right.right == null) {
            return rootNode.value;
        }

        // otherwise: step right
        return findSecondLargestRecurse(rootNode.right);
    }

    private static int findLargest(BinaryTreeNode rootNode) {
        BinaryTreeNode current = rootNode;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public static int findSecondLargest(BinaryTreeNode rootNode) {
        if (rootNode == null || (rootNode.left == null
                && rootNode.right == null)) {
            throw new IllegalArgumentException("Tree must have at least 2 nodes");
        }

        BinaryTreeNode current = rootNode;

        while (true) {

            // case: current is largest and has a left subtree
            // 2nd largest is the largest in that subtree
            if (current.left != null && current.right == null) {
                return findLargest(current.left);
            }

            // case: current is parent of largest, and largest has no children,
            // so current is 2nd largest
            if (current.right != null &&
                    current.right.left == null &&
                    current.right.right == null) {
                return current.value;
            }

            current = current.right;
        }
    }


}
