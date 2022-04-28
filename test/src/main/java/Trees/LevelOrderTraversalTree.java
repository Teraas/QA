package Trees;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversalTree {

    public static void main(String[] args){
    BinaryTree root = new BinaryTree(1);
    root.left = new BinaryTree(4);
    root.right = new BinaryTree(6);
    root.left.left = new BinaryTree(3);
    root.left.right = new BinaryTree(5);
    levelOrderTranversal(root);

    printLevelOrderUsingQueue(root);
    }

    /***
     * Use queue to track nodes and impl with complexity o(n)
     */
    public static void printLevelOrderUsingQueue(BinaryTree root)
    {
        Queue<BinaryTree> queue = new LinkedList<BinaryTree>();
        queue.add(root);
        while (!queue.isEmpty()) {

            /***
             * deque the queue to get first node inserted
             */
            BinaryTree tempNode = queue.poll();
            System.out.print(tempNode.value + " ");

            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }

            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }
    public static void levelOrderTranversal(BinaryTree root){
        int h = getHeight(root);
        System.out.print(h + " is the height");
        int i;
        for (i = 1; i <= h; i++)
            levelOrderTranversal(root, i);
    }

    /***
     * if tree is NULL then return;
     * if level is 1, then
     *     print(tree->data);
     * else if level greater than 1, then
     *     printCurrentLevel(tree->left, level-1);
     *     printCurrentLevel(tree->right, level-1);
     * @param root
     * @param level
     */
    public static void levelOrderTranversal(BinaryTree root, int level){
        //System.out.print(h + " is the height");
        if(root == null)
            return;
        else if(level == 1) {
            System.out.print(root.value + " ");
            return;
        }
        else {
            levelOrderTranversal(root.left, level-1);
            levelOrderTranversal(root.right, level-1);
        }

    }
    public static int getHeight(BinaryTree root)
    {
        if (root == null)
            return 0;
        else {
            /* compute  height of each subtree */
            int lheight = getHeight(root.left);
            int rheight = getHeight(root.right);

            /* use the larger one */
            if (lheight > rheight)
                return (lheight + 1);
            else
                return (rheight + 1);
        }
    }
}

class BinaryTree{
    int value;
    BinaryTree left;
    BinaryTree right;
    public BinaryTree(int val){
        value = val;
    }
    public BinaryTree insertLeft(int leftNode){
        left = new BinaryTree(leftNode);
        return left;
    }
    public BinaryTree insertRight(int rightNode){
        right = new BinaryTree(rightNode);
        return right;
    }
}