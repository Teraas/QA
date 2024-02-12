package dynamicprogramming;

import java.util.*;

public class PiNumbers {
    public static int numbersInPi(String pi, String[] numbers){
        Set<String> numberSet = new HashSet<>();
        for(String num : numbers)
            numberSet.add(num);
        int[] dp = new int[pi.length()+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for(int i =1;i<pi.length()+1;i++){
            String subString = pi.substring(0,i);

            if(numberSet.contains(subString)) {
                dp[i] = 1;
            } else {
                for(int j=1;j<i;j++){
                    if(numberSet.contains(pi.substring(j,i)) && dp[j] < Integer.MAX_VALUE)
                        dp[i] = Math.min(dp[i], dp[j] +1);

                }
            }
        }
        return dp[pi.length()] == Integer.MAX_VALUE ? -1 : dp[pi.length()]-1;

    }

    public static void main(String[] args){
        String s = "3141592653589793238462643383279";
        String[] num = {
                "314159265358979323846",
                "26433", "8",
                "3279",
                "314159265",
                "35897932384626433832",
                "79"};
        int res = numbersInPi(s, num);
        List<String> lst = new ArrayList<>();
        if(lst instanceof ArrayList)
        System.out.println("result  " + res);
    }

}
