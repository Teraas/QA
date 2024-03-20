package Trees;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * createdOn 12/02/24
 */
public class TreeBranchDepthsSum {
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

        int res = nodeDepths(node);
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

    public static int nodeDepths(BinaryTree root) {
        // Write your code here.
        List<Integer> sums = new ArrayList<Integer>();

        int depth = branchDepthSums(root, new NodeMap(root, 0), 0);

        return depth;
    }

    public static int branchDepthSums(BinaryTree root, NodeMap parentSum, int depth) {
        // base condition
        if(root == null) {
            //list.add(parentSum );
            return 0;
        }
        //NodeMap neww = new NodeMap(root, parentSum.sum + 1);
        int d = depth + branchDepthSums(root.left, new NodeMap(root.left, parentSum.sum + 1), depth+1) + branchDepthSums(root.right, new NodeMap(root.right, parentSum.sum + 1), depth+1);

        return d;

    }

    static class NodeMap {
        BinaryTree node;
        int sum;

        public NodeMap(BinaryTree root, int i) {
            this.sum = i;
            this.node = root;
        }
    }
}
