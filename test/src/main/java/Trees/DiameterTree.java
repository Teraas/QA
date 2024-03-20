package Trees;

/**
 * @author harish.kumar-mbp
 * createdOn 17/01/24
 */
public class DiameterTree {
    // This is an input class. Do not edit.
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }

    public int binaryTreeDiameter(BinaryTree tree) {
        // Write your code here.
        // keep a diameter pair with DFS and calculate for every node.
        if(tree == null)
            return -1;
        DiameterNode node = getMaxDiameter(tree);
        return node.diameter;
    }

    private DiameterNode getMaxDiameter(BinaryTree tree) {
        if(tree == null)
            return new DiameterNode(0, 0);
        //if(tree.left == null && tree.right == null)
            //return new DiameterNode(1, 0);
        DiameterNode left =getMaxDiameter(tree.left);
        DiameterNode right = getMaxDiameter(tree.right);
        int rightH = right.height;
        int leftH = left.height;
        int currMaxpath = rightH + leftH;
        int currentMaxH = Math.max(leftH, rightH) +1;
        int currentMaxDiam = Math.max(left.diameter, right.diameter);
        int maxDiam = Math.max(currentMaxDiam, currMaxpath);



        return new DiameterNode(maxDiam, currentMaxH);
    }

    static class DiameterNode {
        public int diameter;
        public int height;


        public DiameterNode(int diameter, int height) {
            this.diameter = diameter;
            this.height = height;

        }
    }
}
