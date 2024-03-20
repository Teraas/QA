package Trees;

import java.util.Stack;

/**
 * @author harish.kumar-mbp
 * createdOn 10/03/24
 */
public class SplitBinaryTree {
    public static void main(String[] args) {
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
        int sum = splitBinaryTree(root);
        System.out.println("sum is " + sum);
    }

    public static int splitBinaryTree(BinaryTree tree) {
        // Write your code here.
        int sum = getSum(tree);
        if(sum % 2 != 0)
            return 0;
        int sum2 = sum / 2;
        Stack<BinaryTree> stack = new Stack<>();
        stack.add(tree);
        int target =0;
        while(!stack.isEmpty()){
            BinaryTree node = stack.pop();
            if(target == sum2){
                return sum2;
            }
            if(node.left == null && node.right == null) {
                target += node.value;
            }
            else if(node.left != null){
                stack.push(node.left);
            }
            else{
                stack.push(node.right);
            }

        }
        return 0;
    }

    public static boolean checkSum(BinaryTree tree, )

    public static int getSum(BinaryTree tree) {
        // Write your code here.
        if(tree == null){
            return 0;
        }
        int sum = tree.value + getSum(tree.left) + getSum( tree.right);
        return sum;
    }
}
