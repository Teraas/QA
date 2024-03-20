package Trees;

public class SymmetricTree {

    public static void main(String[] args){
        //          10
        //     5          5
        //  2     1     1    2
        //    3             3
        BinaryTree root = new BinaryTree(10);
        root.left = new BinaryTree(5);
        root.right = new BinaryTree(5);
        root.left.left = new BinaryTree(2);
        root.left.right = new BinaryTree(1);
        root.right.right = new BinaryTree(2);
        root.right.left = new BinaryTree(1);
        root.right.right.left = new BinaryTree(3);
        root.left.left.right = new BinaryTree(3);

        System.out.println("maximum path sum is : " + verifySymmetric(root));
    }
    public static boolean verifySymmetric(BinaryTree root){
        // a tree is symettric is left is same as inverted right
        return verifySymmetric(root.left, root.right);



    }

    private static boolean verifySymmetric(BinaryTree left, BinaryTree right) {
        if(left == right && right == null)
            return true;
        else if ( left != null && right != null && left.value == right.value)
            return verifySymmetric(left.left, right.right) && verifySymmetric(left.right, right.left);
        //left = left.left;
        //right = right.right;
        //return
        return false;
    }
}
