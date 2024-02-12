package Trees;

import java.util.ArrayList;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * createdOn 16/01/24
 */
public class KthLargestInBST {
    public static void getKthLargetBruteForce(BinaryTree node, List<Integer> inorder){
        if(node == null)
            return ;
        getKthLargetBruteForce(node.left, inorder);
        int val = node.value;

        inorder.add(node.value);
        getKthLargetBruteForce(node.right, inorder);
    }

    // do reverse of inorder traverse -- right, root, left
    public static void getKthLarget(BinaryTree node, List<Integer> inorder, int k){
        if(node == null || inorder.size() >= k) {
            System.out.println("here - inorder.size() is " + inorder.size());
            return;

        }


        getKthLarget(node.right, inorder, k);

        int val = node.value;
        inorder.add(val);
        getKthLarget(node.left, inorder, k);


    }

    public static void main(String[] args){
        List<Integer> inOrder = new ArrayList<>();
        BinaryTree root = new BinaryTree(6);
        root.left = new BinaryTree(4);
        root.right = new BinaryTree(8);
        root.left.left = new BinaryTree(3);
        root.left.right = new BinaryTree(5);
        root.right.left = new BinaryTree(7);
        root.right.right = new BinaryTree(10);
        getKthLarget(root, inOrder, 3);
        System.out.println(inOrder.get(3-1));

        // Write your code here.

    }
    }

