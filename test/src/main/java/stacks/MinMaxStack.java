package stacks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * createdOn 09/03/24
 */
public class MinMaxStack {
    public static void main(String[] args){
        MinMaxStackk s = new MinMaxStackk();
        s.push(5);
        int test = s.peek();
        System.out.println(s.getMin());
        System.out.println(s.getMax());
        System.out.println(s.peek());
        s.push(7);
        System.out.println(s.getMin());
        System.out.println(s.getMax());
        System.out.println(s.peek());

        s.push(2);
        System.out.println(s.getMin());
        System.out.println(s.getMax());
        System.out.println(s.peek());

        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.peek());
        System.out.println(s.getMin());
        System.out.println(s.getMax());
        System.out.println(s.peek());
    }

    static class MinMaxStackk {
        List<Integer> stack = new ArrayList<>();
        List<int[]> minMax = new ArrayList<>();
        public int peek() {
            // Write your code here.
            return stack.get(stack.size() -1);
        }

        public int pop() {
            // Write your code here.
            int el = stack.get(stack.size() -1);
            stack.remove(stack.size() -1);
            minMax.remove(minMax.size() -1);

            return el;
        }

        public void push(Integer number) {
            // Write your code here.
            int[] minMaxTmp = new int[]{number, number};
            if(minMax.isEmpty()){
                minMax.add(minMaxTmp);

            }
            else {
                int[] minMaxCur = minMax.get(minMax.size()-1);
                minMaxTmp[0] = Math.min(number, minMaxCur[0]);
                minMaxTmp[1] = Math.max(number, minMaxCur[1]);
                minMax.add(minMaxTmp);
            }
            stack.add(number);
        }

        public int getMin() {
            // Write your code here.
            int[] minMaxCur = minMax.get(minMax.size()-1);
            return minMaxCur[0];
        }

        public int getMax() {
            // Write your code here.
            int[] minMaxCur = minMax.get(minMax.size()-1);
            return minMaxCur[1];
        }
    }
}
