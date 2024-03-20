package Trees.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * createdOn 13/02/24
 */
public class ConstructUsingPreOrder {
    private static int currentIdx;
    public static void main(String[] args){
        Integer[] arr = {10, 4, 2, 1, 5, 17,  19, 18};
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Collections.addAll(arrayList, arr);
        BST bst = reconstructBst(arrayList);
        System.out.println(bst);

    }

    public static BST reconstructBst(ArrayList<Integer> preOrderTraversalValues) {
        // first node is root. then build furtherusing bst property
        // [ 10 4 2 1 5 17  19 18 ]
        //          10
        //         4    17
        //      2           19
        //    1     5     18

        //int index = 0;
        currentIdx = 0;
        return getTree(preOrderTraversalValues, Integer.MAX_VALUE);

        //return reconstructBst(preOrderTraversalValues, 0, Integer.MAX_VALUE);
    }
    private static BST getTree(ArrayList<Integer> preOrderTraversalValues, int max){
        if(currentIdx == preOrderTraversalValues.size() || preOrderTraversalValues.get(currentIdx) >=max)
            return null;
        BST n = new BST(preOrderTraversalValues.get(currentIdx));
        currentIdx++;
        n.left = getTree(preOrderTraversalValues, n.value);
        n.right = getTree(preOrderTraversalValues, max);
        return n;
    }

    public static BST reconstructBst1(List<Integer> preOrderTraversalValues) {

        if(preOrderTraversalValues.size() == 0)
            return null;
        int val = preOrderTraversalValues.get(0);
        BST node = new BST(val);
        int rdx = getMaxValueIndex(preOrderTraversalValues, 0, preOrderTraversalValues.size());

        node.left = reconstructBst1( preOrderTraversalValues.subList(1, rdx));
        node.right = reconstructBst1( preOrderTraversalValues.subList(rdx,preOrderTraversalValues.size()));

        return node;

    }

    private static int getMaxValueIndex(List<Integer> preOrderTraversalValues, int i, int max) {
        int root = preOrderTraversalValues.get(i);
        int cur = i;
        while(cur < preOrderTraversalValues.size() && root >= preOrderTraversalValues.get(cur) ){
            cur = cur+1;
        }
        return cur;
    }


    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }
}
