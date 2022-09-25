package Trees;

/**
 * @author harish.kumar-mbp
 * @created 22/09/22
 */
public class SecondLargestInBST {
    public static void main(String[] args){
        BinaryTree root = new BinaryTree(10);
        root.left = new BinaryTree(5);
        root.right = new BinaryTree(15);
        root.left.left = new BinaryTree(2);
        root.left.right = new BinaryTree(6);
        root.right.right = new BinaryTree(16);
        root.right.left = new BinaryTree(14);
        root.right.right.right = new BinaryTree(18);
        root.right.right.right.left = new BinaryTree(17);
        root.left.left.right = new BinaryTree(1);

        System.out.println("maximum path sum is : " + getSecondLargestInBST(root));
    }

    private static int getSecondLargestInBST(BinaryTree root) {
        if (root == null || (root.left == null
                && root.right == null)) {
            throw new IllegalArgumentException("Tree must have at least 2 nodes");
        }
        // For BST, largest is the right most element.
        // Second largest could be either root of largest(if its a leaf node)
        // else could be left leaf when largest is node.
        if(root.right != null && root.right.right == null && root.right.left == null)
            return root.value;
        else if( root.right == null && root.left != null)
            return getLargestInBST(root.left);
        else
            return getSecondLargestInBST(root.right);
    }

    private static int getLargestInBST(BinaryTree root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree must have at least 1 node");
        }
        // For BST, largest is the right most element.
        // circuit break - if currentNode has no right leaf, return the current.
        if(root.right == null)
            return root.value;
        else
            return getLargestInBST(root.right);
    }

    public static int findLargestIteratively(BinaryTree rootNode) {
        BinaryTree current = rootNode;

        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public static int findSecondLargestIteratively(BinaryTree root) {
        if(root == null || (root.right == null && root.left == null ))
            throw new IllegalArgumentException("Tree must have at least 2 nodes");

        BinaryTree current = root;
        while(true){

            if(current.right != null && current.right.right==null && current.right.left==null)
                return current.value;
            else if(current.right == null && current.left !=null)
                return findLargestIteratively(current.left);
            current = current.right;
        }
    }
}
