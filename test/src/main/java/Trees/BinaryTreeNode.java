package Trees;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

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
        /**
         *              10
         *              / \
         *             6   15
         *            / \  / \
         *           5  7  12  16
         */
        BinaryTreeNode node = new BinaryTreeNode(10);
        node.left = new BinaryTreeNode(6);
        node.right = new BinaryTreeNode(15);

        node.left.right = new BinaryTreeNode(7);
        node.left.left = new BinaryTreeNode(5);

        node.right.right = new BinaryTreeNode(16);
        node.right.left = new BinaryTreeNode(12);

        //int res = findSecondLargestRecurse(node); //for BST
        int res2 = findSecondLargest(node); //for BST
        //System.out.println(" second largest node " + res + " asdsd  " +res2);
        dfsIteratively(node);
        dfdRecursively(node);
        bfs(node); // bfs can not be recursive as recursion is stack order not as what bfs expects
    }
    public static void bfs(BinaryTreeNode root){
        Queue<BinaryTreeNode> queue = new ArrayDeque<>();
        // add root to the queue
        if(root==null)
            return;
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryTreeNode currentNode = queue.poll();
            System.out.println("current value node bfs --> " + currentNode.value);
            // add left/right nodes in queue
            if(currentNode.left !=null)
                queue.add(currentNode.left);
            if(currentNode.right !=null)
                queue.add(currentNode.right);
        }

    }
    public static void dfdRecursively(BinaryTreeNode root){
        /**
         * base case for recursion
         */
        if(root==null)
            return;
        System.out.println("current value node --> " + root.value);
        if(root.left !=null)
            dfdRecursively(root.left);
        if(root.right !=null)
            dfdRecursively(root.right);

    }
    public static void dfsIteratively(BinaryTreeNode node){
        Stack<BinaryTreeNode> stack = new Stack();
        stack.add(node);

        while(!stack.isEmpty()){
            //System.out.print("current stack --> " );
            //printstack(stack);
            System.out.println("" );
            BinaryTreeNode currentNode = stack.pop();
            System.out.println("current value node --> " + currentNode.value);
            if(currentNode.right !=null)
                stack.push(currentNode.right);
            if(currentNode.left !=null)
                stack.push(currentNode.left);
        }

    }

    private static void printstack(Stack<BinaryTreeNode> stack) {
        for(BinaryTreeNode node:stack){
            System.out.print(node.value + " ");
        }
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
        // right most in BST would be the largest
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
