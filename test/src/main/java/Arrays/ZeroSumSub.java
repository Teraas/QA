package Arrays;

/**
 * @author harish.kumar-mbp
 * createdOn 04/03/24
 */
public class ZeroSumSub {
    public boolean zeroSumSubarray(int[] nums) {
        // Write your code here.
        int l = 0;
        int r =0;
        int sum = nums[0];
        int[] res = new int[2];
        while( l <nums.length && r < nums.length){
            if(sum == 0){
                return true;
                //break;
            }
            else if( Math.abs(sum +(nums[r+1])) > Math.abs(sum)){
                    sum = sum - nums[l];
                    l++;
                }
                else{
                    sum = sum + nums[r+1];
                    r = r +1;
                }

        }

        return false;
    }
}
