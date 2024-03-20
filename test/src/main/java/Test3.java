/**
 * @author harish.kumar-mbp
 * createdOn 17/02/24
 */
public class Test3 {

    //int price[] = { 20,10,20,30,40,50,80,10,90 };
    //purchase at day 1 and sell at day 1 and your profit is 0
    //purchase at day 2 and sell at day 7 and your profit is 70
    //purchase at day 8 and sell at day 9 and your profit is 150
    //
    //The goal is to find the best time to buy and sell a stock to maximize profit given an array of stock prices representing each day's closing price.
    //
    public static void main(String[] agrgs){
        int[] arr = { 20,10,20,30,40,50,80,10,90 };
        findMaxProfitDays(arr);

    }
    private static void findMaxProfitDays(int[] stocks){
        int maxProfit = 0;
        int currProf = 0;
        int minPrice =Integer.MAX_VALUE;
        int maxPrice = 0;

        for(int i = 0; i<stocks.length;i++){
            int prof = 0;
            int curMaxpr = 0;
            // check current price less than min
            int cur = stocks[i];
            if(cur < minPrice){
                minPrice = cur;
            }
            if(cur > minPrice){
                // cal profit
                maxPrice = cur;
                prof = maxProfit - minPrice;
                currProf = prof;
                if(prof > curMaxpr)
                    curMaxpr = prof;
            }
            if(cur < maxPrice){
                maxProfit +=prof;
                System.out.println(" and buy at " + minPrice + " sold at " + maxPrice);
                // restart buy sell
                minPrice = cur;
                maxPrice = cur;
            }

            // if current price more than min, then we chekc profit.
        }
        System.out.println(" and buy at " + minPrice + " sold at " + maxPrice);
        //System.out.println("Current total profite ->" + maxProfit);

    }



}
