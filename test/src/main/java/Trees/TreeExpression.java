package Trees;

/**
 * @author harish.kumar-mbp
 * createdOn 12/02/24
 */
public class TreeExpression {

    public static void main(String[] args) {
        /**
         *              10
         *              / \
         *             6   15
         *            / \  / \
         *           5  7  12  16
         */
        BinaryTree node = new BinaryTree(-1);
        node.left = new BinaryTree(-2);
        node.right = new BinaryTree(-3);

        node.left.right = new BinaryTree(2);
        node.left.left = new BinaryTree(-4);

        node.right.right = new BinaryTree(8);
        node.right.left = new BinaryTree(3);
        node.right.left.left = new BinaryTree(2);
        node.right.left.right = new BinaryTree(3);

        int res = evaluateExpressionTree(node);
        System.out.println(res);
    }


    public static int evaluateExpressionTree(BinaryTree tree) {
        // if we reach value nodes we calculate and return
        if(tree.value >=0 )
            return tree.value;
        return calculateExp(evaluateExpressionTree(tree.left), evaluateExpressionTree(tree.right), tree.value);

    }

    private static int calculateExp(int value, int value1, int value2) {
        switch (value2){
            case -1 : {
                return value + value1;
            }
            case -2 : {
                return value - value1;
            }
            case -3 : {
                return value / value1;
            }
            case -4 : {
                return value * value1;
            }
            default:{
                return 0;
            }
        }
    }
}
