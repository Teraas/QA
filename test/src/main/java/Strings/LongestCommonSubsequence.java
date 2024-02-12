package Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * createdOn 28/01/24
 */
public class LongestCommonSubsequence {
    public static void main(String[] args){
        String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s2 = "CCCDDEGDHAGKGLWAJWKJAWGKGWJAKLGGWAFWLFFWAGJWKAG";
        int l1 = s1.length();
        int l2 = s2.length();
        int[][] dp = new int[l1+1][l2+1];
        dp[0][0] = 0;
        for(int i =1;i<l1+1;i++){
            dp[i][0] = -1;
        }
        for(int j =1;j<l2+1;j++){
            dp[0][j] = -1;
        }
        int res = getLCSlength( s1, s2, dp);
        System.out.println("outpur " + res);
        List<Character> lst = new ArrayList<>();
        while(l1>0 && l2>0){
            if(s1.charAt(l1-1) == s2.charAt(l2-1)){
                lst.add(s1.charAt(l1-1));
                l1--;l2--;
            }
            else if(dp[l1-1][l2] > dp[l1][l2-1] ){
                l1--;
            }
            else {
                l2--;
            }

        }
        Collections.reverse(lst);
        System.out.println("outpur " + lst);


    }

    private static int getLCSlength( String s1, String s2, int[][] dp) {

        for(int i =1;i<s1.length()+1;i++) {
            for(int j =1;j < s2.length() + 1;j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1] +1;
                    //return getLCSlength(i + 1, j + 1, s1, s2, dp);
                }
                else
                    dp[i][j] = (getPrevMax(i, j, s1, s2, dp));

            }
        }
        return dp[s1.length()][s2.length()];

    }

    private static int getPrevMax(int i, int j, String s1, String s2, int[][] dp) {
        int a1 = dp[i-1][j];
        int a2 = dp[i][j-1];

        return Math.max(a1, a2);
    }


}
