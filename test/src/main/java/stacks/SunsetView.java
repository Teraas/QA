package stacks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author harish.kumar-mbp
 * createdOn 11/01/24
 */
public class SunsetView {
    public ArrayList<Integer> sunsetViews(int[] buildings, String direction) {
        // Write your code here.
        ArrayList<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<Integer>();
        int l = buildings.length;
        if(direction.equals("EAST")){
            stack.push(buildings[l-1]);
            res.add(buildings[l-1]);
            for(int i=l-2;i>=0;i--){
            int last = stack.peek();
            if(last < buildings[i]) {
                stack.push(buildings[i]);
                res.add(buildings[i]);
            }
            }
            Collections.reverse(res);

        }
        else {
            stack.push(buildings[0]);
            res.add(l-1);
            for(int i=1;i>l;i++){
                int last = stack.peek();
                if(last < buildings[i]) {
                    stack.push(buildings[i]);
                    res.add(buildings[i]);
                }
            }
        }
        return res;
    }

    public static void main(String[] agrs){
        int[] buildings = {3, 5, 4, 4, 3, 1, 3, 2};
    }
}
