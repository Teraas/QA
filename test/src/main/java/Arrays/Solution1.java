package Arrays;

/**
 * @author harish.kumar-mbp
 * createdOn 17/01/24
 */
public class Solution1 {

    public int[] solution(int[] A, int F, int M) {
        // Implement your solution here
        int[] leftOnes = new int[F];
        int x = 12;
        int length = A.length;
        int sumOfRolls = 0;
        for(int idx =0;idx<length;idx++){
            sumOfRolls = sumOfRolls + A[idx];
        }
        x = M * (length+ F) - sumOfRolls;
        if(F * 1 > x || F * 6 < x ){
            int[] res = {0};
            return res;
            }
        for(int i =0;i<F;i++){
            leftOnes[i] = 1;
        }
        int currentSum = F * 1;
        for(int i =0;i<F;i++){
            if(currentSum < x){
                // update the element of an index until arr[i] =6
                currentSum = getUpdatedArray(leftOnes, i,  currentSum, x);
            }
            if(currentSum == x)
                break;

        }
        return leftOnes;
    }

    private int getUpdatedArray(int[] leftOnes, int i, int currentSum, int x) {
        int indexValue = leftOnes[i];
        for(int j =indexValue;j<6;j++){
            leftOnes[i] = leftOnes[i] +1;
            currentSum = currentSum+1;
            if(currentSum == x){
                break ;
            }
        }
        return currentSum;
    }

    public static void main(String[] args){
        int[] arr = {3,2,4,3};
        Solution1 solution1 = new Solution1();
        int[] arr2 =  solution1.solution(arr, 2, 5);
        System.out.println(arr2);
    }
}
