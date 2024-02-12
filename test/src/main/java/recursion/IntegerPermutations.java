package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * createdOn 29/01/24
 */
public class IntegerPermutations {
    public static void main(String[] args){
        Integer[] arr = {1,2,3};
        List list = new ArrayList<>();
        List list2 = new ArrayList<>();
        list2.add(1);list2.add(2);list2.add(3);
        Collections.addAll(list, arr);

        String[] array1 = {"new", "String", "array"};

        List<String> list1 = new ArrayList<>(Arrays.asList(array1));

        getPermutations(list);
        System.out.println(list);


    }
    public static List<List<Integer>> getPermutations(List<Integer> array) {
        // Write your code here.
        List<List<Integer>> perms = new ArrayList<>();
        List<Integer> perm = new ArrayList<>();
        List<Integer> array1 = new ArrayList<>(array);
        getPermutations(array,array1,  perm, perms);
        return perms;
    }

    private static void getPermutations(List<Integer> array, List<Integer> array1, List<Integer> perm, List<List<Integer>> perms) {

        if(array.isEmpty() && !perm.isEmpty())
            perms.add(perm);
        else {
            for (int i =0;i<array.size() ; i++){
                System.out.println("here");
                //int i = array1.get(0);
                List<Integer> arrayN = new ArrayList<>(array.subList(0,i)) ;
                arrayN.addAll(array.subList(i+1,array.size()));
                //arrayN.remove(i);
                List<Integer> permN = new ArrayList<>(perm);
                permN.add(array.get(i));
                getPermutations(arrayN, array1, permN, perms);
        }

        }
    }

}
