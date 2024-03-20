package Trees.bst;

/**
 * @author harish.kumar-mbp
 * createdOn 13/02/24
 */
public class ClosestValue {
    public static void main(String[] args){

    }

    public static int findClosestValueInBst(BST tree, int target, int closest) {
        // Write your code here.
        if (tree == null)
            return closest;
        int val = tree.value;
        //if(value <= target )
        if (Math.abs(closest-target) > Math.abs(target-val))
            closest = val;
        if(val > target ) {
             return findClosestValueInBst(tree.left, target, closest);

        }
        else if(val < target ) {
            return findClosestValueInBst(tree.right, target, closest);

        }
        else return closest;

    }

    public static int findClosestValueInBst(BST tree, int target ) {
        // Write your code here.
        BST cur = tree;
        int closest = Integer.MAX_VALUE;
        while(cur != null){
            int val = cur.value;
            if (Math.abs(closest-target) > Math.abs(target-val))
                closest = val;
            if(val > target ) {
                cur = cur.left;
            }
            else if(val < target ) {
                cur = cur.right;
            }
            else{
                closest = val;
            }
        }
        return closest;

    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
