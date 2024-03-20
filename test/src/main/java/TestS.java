/**
 * @author harish.kumar-mbp
 * createdOn 29/02/24
 */
public class TestS {

    public static void main(String[] agrs){
        int[] arr = {1, 7, 10, 23, 101, 9, 21};


    }

    public static int getMaxSteal(int[] arr){
        int[] steal = new int[arr.length];
        steal[0] = arr[0];
        int inclusingCurr = 0;
        int excludingCur = 0;
        for(int i =1;i<arr.length; i++){
            // either prevIncluding i-1 or i-1th expluding + current;
            int inclusingCurrTmp = steal[i-1];

        }
    return 0;
    }
}
