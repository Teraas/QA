package Trees;

/**
 * @author harish.kumar-mbp
 * createdOn 12/02/24
 */
public class InvertBinaryTree {
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

        invert(node);
        System.out.println(node);
    }

    private static BinaryTree invert(BinaryTree node) {
        if(node == null)
            return null;
        BinaryTree left = invert(node.left);
        BinaryTree right = invert(node.right);
        node.left = right;
        node.right = left;
        return node;



    }

}
