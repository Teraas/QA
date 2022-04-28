package Trees;

import java.util.Stack;

public class TreeTraversal {
    public static void main(String[] args){
        BinaryTree root = new BinaryTree(1);
        root.left = new BinaryTree(4);
        root.right = new BinaryTree(6);
        root.left.left = new BinaryTree(3);
        root.left.right = new BinaryTree(5);
        /***
         *          1
         *       4      6
         *    3     5
         */

        System.out.println("Print the inorder traversal of a tree");
        //printInorderTreeRecursive(root);
        System.out.println("Print the inorder traversal of a tree");
        printInorderTreeNonRecursive(root);
//        System.out.println("Print the Preorder traversal of a tree");
//        printPreorderTree(root);
//        System.out.println("Print the Postorder traversal of a tree");
//        printPostorderTree(root);
        
    }

    private static void printInorderTreeRecursive(BinaryTree root) {

        if(root ==null)
            return;
        printInorderTreeRecursive(root.left);
        System.out.println(root.value);
        printInorderTreeRecursive(root.right);
    }
    private static void printInorderTreeNonRecursive(BinaryTree root) {
        Stack<BinaryTree> stack = new Stack<>();
        if(root ==null)
            return;
        /**
         * Start from root and reach left most and start printing for that.
         *
         */
        BinaryTree curr = root;

        // traverse the tree
        while (curr != null || stack.size() > 0)
        {

            /* Reach the left most Node of the
            curr Node */
            while (curr !=  null)
            {
                /* place pointer to a tree node on
                   the stack before traversing
                  the node's left subtree */
                stack.push(curr);
                curr = curr.left;
            }

            /* Current must be NULL at this point */
            curr = stack.pop();

            System.out.print(curr.value + " ");

            /* we have visited the node and its
               left subtree.  Now, it's right
               subtree's turn */
            curr = curr.right;
        }
    }
    private static void printPreorderTree(BinaryTree root) {

        if(root ==null)
            return;

        System.out.println(root.value);
        printPreorderTree(root.left);
        printPreorderTree(root.right);
    }
    private static void printPostorderTree(BinaryTree root) {

        if(root ==null)
            return;
        printPostorderTree(root.left);
        printPostorderTree(root.right);
        System.out.println(root.value);
    }
}



