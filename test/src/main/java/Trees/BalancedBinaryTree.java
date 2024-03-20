package Trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
public class BalancedBinaryTree {

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

        node.right.left.right = new BinaryTreeNode(22);
        node.right.right.right = new BinaryTreeNode(18);

        node.right.left.right.right = new BinaryTreeNode(33);

        boolean res = isBalanced(node);
        System.out.println(node);
    }

    public static boolean isBalanced(BinaryTreeNode treeRoot) {

        // a tree with no nodes is superbalanced, since there are no leaves!
        if (treeRoot == null) {
            return true;
        }

        // we short-circuit as soon as we find more than 2
        List<Integer> depths = new ArrayList<>(3);

        Deque<NodeDepthPair> nodes = new ArrayDeque<>();
        nodes.push(new NodeDepthPair(treeRoot, 0));

        while (!nodes.isEmpty()) {

            // pop a node and its depth from the top of our stack
            NodeDepthPair nodeDepthPair = nodes.pop();
            BinaryTreeNode node = nodeDepthPair.node;
            int depth = nodeDepthPair.depth;

            // case: we found a leaf
            if (node.left == null && node.right == null) {

                // we only care if it's a new depth
                if (!depths.contains(depth)) {
                    depths.add(depth);

                    // two ways we might now have an unbalanced tree:
                    //   1) more than 2 different leaf depths
                    //   2) 2 leaf depths that are more than 1 apart
                    if (depths.size() > 2 || (depths.size() == 2
                            && Math.abs(depths.get(0) - depths.get(1)) > 1)) {
                        return false;
                    }
                }

                // case: this isn't a leaf - keep stepping down
            }
            else {
                if (node.left != null) {
                    nodes.push(new NodeDepthPair(node.left, depth + 1));
                }
                if (node.right != null) {
                    nodes.push(new NodeDepthPair(node.right, depth + 1));
                }
            }
        }

        return true;
    }
    static class NodeDepthPair {

        BinaryTreeNode node;
        int depth;

        NodeDepthPair(BinaryTreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    static class InfoPair {

        boolean isBalanced ;
        int height;

        InfoPair(boolean bool, int height) {
            this.isBalanced = bool;
            this.height = height;
        }
    }

    public static InfoPair isBalanced1(BinaryTreeNode root) {
        if(root == null)
            return new InfoPair(true, -1);

        InfoPair pairL = isBalanced1(root.left );
        InfoPair pairR = isBalanced1(root.right );
        boolean isBalance = pairL.isBalanced && pairR.isBalanced && Math.abs(pairL.height- pairR.height) <=1 ;
        int maxH = Math.max(pairL.height, pairR.height);
        return  new InfoPair(isBalance, maxH+1);
    }
}




