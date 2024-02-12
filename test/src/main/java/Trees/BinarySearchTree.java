package Trees;

public class BinarySearchTree {
    public static void main(String[] args){
        BinaryTree root = new BinaryTree(6);
        root.left = new BinaryTree(4);
        root.right = new BinaryTree(8);
        root.left.left = new BinaryTree(3);
        root.left.right = new BinaryTree(5);
        root.right.left = new BinaryTree(7);
        root.right.right = new BinaryTree(10);

        boolean res = isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(res);

    }

    public static boolean isBST(BinaryTree root, long min, long max){
        // check the short-circuit condition/basecase for recursion. Return true if null or empty left/right
        if(root ==null || (root.left==null && root.right==null) )
            return true;
        else{
            // leftTree < root.value && rightTree >root.value
            return (root.value >min && root.value <max && isBST(root.left, min,root.value) && isBST(root.right, root.value, max));
        }
    }
}
