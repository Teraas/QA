package Trees;

public class MaximumPathSum {
    public static void main(String[] args){
        BinaryTree root = new BinaryTree(10);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(10);
        root.left.left = new BinaryTree(20);
        root.left.right = new BinaryTree(1);
        root.right.right = new BinaryTree(-25);
        root.right.right.left = new BinaryTree(3);
        root.right.right.right = new BinaryTree(4);

        Result result = new Result();
        result.val = Integer.MIN_VALUE;
        getMaxPathSum(root, result);
        System.out.println("maximum path sum is : " + result.val);
    }

    public static int getMaxPathSum(BinaryTree node, Result sum){
        /**
         * Get left-tree maxPathSum. Get right-tree maxPathSum.
         * Max sum would be either of -- 1: root + left_sum,
         * 2: root + right_sum,
         * 3: root + left_sum + right_sum
         * 4: root
         */
        if(node ==null)
            return 0;
        int left_sum = getMaxPathSum(node.left, sum);
        int right_sum = getMaxPathSum(node.right, sum);

        int max_of_children = Math.max(left_sum, right_sum);
        int max_of_root_or_single_childerns = Math.max(max_of_children+node.value, node.value);

        int max_total = Math.max(max_of_root_or_single_childerns, left_sum+right_sum+node.value);

        sum.val = Math.max(max_total, sum.val);
        return max_of_root_or_single_childerns;
    }
}
class Result {
    public int val;
}
