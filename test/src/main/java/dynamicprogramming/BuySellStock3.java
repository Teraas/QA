package dynamicprogramming;

import java.util.Arrays;

/**
 * @author harish.kumar-mbp
 * createdOn 28/01/24
 */
public class BuySellStock3 {
    // simple recursive solution.


    public static void main(String[] args){
        //int[] arr = {7, 1, 5, 3, 6, 4};
        int[] arr = {3, 3, 5, 0, 0, 3, 1, 4};
        boolean buy = true;
        int index = 0;
        int noOfTxn = 2;
        int p = getMaxProfit2Trx(arr, index, buy, noOfTxn);

        int[][][] dp = new int[arr.length][2][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int[] arr1 = {1, 10};
        p = maxProfitWithKTransactions(arr1, 1);

        System.out.println("profit is " + p);
    }

    public static int maxProfitWithKTransactions(int[] prices, int k) {
        // Write your code here.
        int[][][] dp = new int[prices.length][2][k+1];
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return getMaxProfit2Trx(prices, 0, 0, k, dp);
    }

    private static int getMaxProfit2Trx(int[] arr, int index, boolean buy, int t) {
        if(index >=arr.length || t ==0)
            return 0;
        int profit = 0;
        if(buy){
            // if buy then profit is (-buy + profit at next index)
            int p1 = (-arr[index] + getMaxProfit2Trx(arr, index+1, false, t));
            int p2 = getMaxProfit2Trx(arr, index+1, true, t);
            profit= Math.max(p1, p2);;
        }
        if(!buy){
            int p1 = arr[index]  + getMaxProfit2Trx(arr, index+1, true, t-1);
            int p2 = getMaxProfit2Trx(arr, index+1, false, t);
            profit = Math.max(p1, p2);
        }
        return profit;
    }

    private static int getMaxProfit2Trx(int[] arr, int index, int buy, int t, int[][][] dp) {
        if(index >=arr.length || t ==0)
            return 0;
        if (dp[index][buy][t] != -1)
            return dp[index][buy][t];
        int profit = 0;
        if(buy == 0){
            // if buy then profit is (-buy + profit at next index)
            int p1 = (-arr[index] + getMaxProfit2Trx(arr, index+1, 1, t, dp));
            int p2 = getMaxProfit2Trx(arr, index+1, 0, t, dp);
            profit= Math.max(p1, p2);;
        }
        if(buy == 1){
            int p1 = arr[index]  + getMaxProfit2Trx(arr, index+1, 0, t-1, dp);
            int p2 = getMaxProfit2Trx(arr, index+1, 1, t, dp);
            profit = Math.max(p1, p2);
        }
        dp[index][buy][t] = profit;
        return profit;
    }
}
