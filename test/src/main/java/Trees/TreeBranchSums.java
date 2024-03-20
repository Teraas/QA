package Trees;

import java.util.ArrayList;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * createdOn 12/02/24
 */
public class TreeBranchSums {
    public static void main(String[] args) {
        /**
         *              10
         *              / \
         *             6   15
         *            / \  / \
         *           5  7  12  16
         */
        BinaryTree node = new BinaryTree(10);
        node.left = new BinaryTree(6);
        node.right = new BinaryTree(15);

        node.left.right = new BinaryTree(7);
        node.left.left = new BinaryTree(5);

        node.right.right = new BinaryTree(16);
        node.right.left = new BinaryTree(12);

        List<Integer> res = branchSums(node);
        System.out.println(res);
    }

    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static List<Integer> branchSums(BinaryTree root) {
        // Write your code here.
        List<Integer> sums = new ArrayList<Integer>();
        branchSums(root, sums, 0);
        return sums;
    }

    public static void branchSums(BinaryTree root, List<Integer> list, int parentSum) {
        // base condition
        if(root.left == null && root.right == null) {
            list.add(parentSum + root.value);
            return;
        }
        int currentSUm = parentSum + root.value;

        if(root.left !=null)
            branchSums(root.left, list, currentSUm);
        if(root.right !=null)
            branchSums(root.right, list, currentSUm);

    }

    class NodeMap {
        BinaryTree node;
        int sum;
    }
}
