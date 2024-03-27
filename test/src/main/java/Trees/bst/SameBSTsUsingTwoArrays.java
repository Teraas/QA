package Trees.bst;

import java.util.ArrayList;

/**
 * @author harish.kumar-mbp
 * GIven 2 arrays. Check if we create BSTs using them, it would be same BST.
 * But verify this without creating BST data structures
 */
public class SameBSTsUsingTwoArrays {
    /***
     * Solution 1:
     * Check the root from left and match and then create new Arrays for Left & Right sub trees.
     * Left to have smaller than root elements and Right to have elements greater than root.
     * Do the same until only 1 element left.
     * DO this recursively.
     * Complexity:
     * Time - We will traverse every element and then also traverse entire everytime to get left/right tree arrays. So O(n^2)
     * Same - similarly O(n^2)
     */
    public boolean solution1(ArrayList<Integer> list1, ArrayList<Integer> list2){
        if(list1.size() != list2.size())  {
            return false;
        }
        if(list1.isEmpty() ){
            return true;
        }
        int root1 = list1.get(0);
        int root2 = list2.get(0);
        if(root1 == root2  && list2.size()== 1){
            return true;
        }
        else if(root1 == root2 ){
            // check left and right
            ArrayList<Integer> listLeft1 = new ArrayList<>();
            ArrayList<Integer> listLeft2 = new ArrayList<>();
            for(Integer el : list1.subList(1,list1.size())){
                if(el < root1){
                    listLeft1.add(el);
                }
            }
            for(Integer el : list2.subList(1,list2.size())){
                if(el < root1){
                    listLeft2.add(el);
                }
            }

            ArrayList<Integer> listRight1 = new ArrayList<>();
            ArrayList<Integer> listRight2 = new ArrayList<>();
            for(Integer el : list1.subList(1,list1.size())){
                if(el >= root1){
                    listRight1.add(el);
                }
            }
            for(Integer el : list2.subList(1,list2.size())){
                if(el >= root1){
                    listRight2.add(el);
                }
            }

            return solution1(listLeft1, listLeft2) && solution1(listRight1, listRight2);
        }
        else{
            return false;
        }
    }
}
