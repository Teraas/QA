package Trees;

public class SymmetricTree {

    public static void main(String[] args){
        BinaryTree root = new BinaryTree(10);
        root.left = new BinaryTree(5);
        root.right = new BinaryTree(5);
        root.left.left = new BinaryTree(2);
        root.left.right = new BinaryTree(1);
        root.right.right = new BinaryTree(2);
        root.right.left = new BinaryTree(1);
        root.right.right.left = new BinaryTree(3);
        root.left.left.right = new BinaryTree(3);

        System.out.println("maximum path sum is : " + verifySymmetric());
    }
    public static boolean verifySymmetric(){

        return false;

    }
}
