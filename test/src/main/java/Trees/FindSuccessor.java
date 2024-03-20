package Trees;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author harish.kumar-mbp
 * createdOn 12/02/24
 */
public class FindSuccessor {

    public static void main(String[] args) {
        /**
         *              10
         *              / \
         *             6   15
         *            / \  / \
         *           5  7  12  16
         */
        Trees.BinaryTree node = new Trees.BinaryTree(10);
        node.left = new Trees.BinaryTree(6);
        node.right = new Trees.BinaryTree(15);

        node.left.right = new Trees.BinaryTree(7);
        node.left.left = new Trees.BinaryTree(5);

        node.right.right = new Trees.BinaryTree(16);
        node.right.left = new Trees.BinaryTree(12);

        //invert(node);
        System.out.println(node);
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;
        public BinaryTree parent = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
        // Write your code here.
        if(node.right != null)
            return minOfTree(node.right);

        // other wise move up and keep checking if node is found
        BinaryTree p = node.parent;
        while( p !=null & p.right == node){
            node = p;
            p = p.parent;
        }
        return p;
    }

    private BinaryTree minOfTree(BinaryTree right) {
        while(right.left !=null)
            right = right.left;
        return right;
    }


    public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node, Set<BinaryTree> visited) {
        // Write your code here.
        if(tree == null )
            return null;
        else if(tree == node){
            // if its left node then next to be the root/parent.
            // if it right, then
            BinaryTree parent = tree.parent;
            while(visited.contains(parent)){
                parent = parent.parent;
            }
            System.out.println("next ->" + parent.value);
            return parent;

        }
        else{
            findSuccessor(tree.left, node);
            System.out.println("root ->" + tree.value);
            visited.add(tree);
            findSuccessor(tree.right, node);
            return null;
        }


    }
}
