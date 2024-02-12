package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * createdOn 29/01/24
 */
public class ListSums {
    public static int productSum(List<Object> array) {
        // Write your code here.
        int sum = productSum(array, 0, 1);
        System.out.println(sum);
        return sum;
    }

    public static int productSum(List<Object> array, int sum, int level) {



        for(Object ob : array){
            int cur = 0;
            if(ob instanceof ArrayList)
                cur = productSum(Collections.unmodifiableList((List) ob), 0, level+1);
            else
                cur = (int)ob;

            sum = sum + cur;
        }
        return level * sum;


    }

    public static void main(String[] args){
        List<Object> lst = new ArrayList<>();
        lst.add(5);
        lst.add(2);
        lst.add(3);
        List<Object> lst1 = new ArrayList<>();
        lst1.add(7);
        lst1.add(-1);
        lst.add(lst1);
        List<Object> lst2 = new ArrayList<>();
        List<Object> lst3 = new ArrayList<>();
        lst3.add(-13);
        lst3.add(8);
        lst2.add(lst3);
        lst2.add(6);lst2.add(4);
        lst.add(lst2);

        productSum(lst);

    }

}
