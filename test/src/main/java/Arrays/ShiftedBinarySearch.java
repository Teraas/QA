package Arrays;

/**
 * @author harish.kumar-mbp
 * createdOn 09/03/24
 */
public class ShiftedBinarySearch {
    public static void main(String[] args){
        int[] arr = new int[]{61, 71, 72, 73, 0, 1, 21, 33, 37, 45};
        int res = shiftedBinarySearch(arr, 33);
        System.out.println(res);

    }

    public static int shiftedBinarySearch(int[] array, int target) {
        // Write your code here.
        // fidn the pivot and search in sub arrays
        int pivot = 0;
        int el = array[0];
        for(int i =0;i<array.length;i++){
            if(el > array[i]){
                el = array[i];
                pivot = i;
            }
        }

        int resL = shiftedBinarySearch(array, target, 0, pivot-1);
        int resR = shiftedBinarySearch(array, target, pivot, array.length-1);
        if(resL != -1)
            return resL;
        return resR;
    }

    public static int shiftedBinarySearch(int[] array, int target, int left, int right) {
        // Write your code here.
        // fidn the pivot and search in sub arrays
        while(left <=right){
            int mid = (left + (right -left) / 2);
            if(array[mid] < target){
                left = mid +1;
            } else if(array[mid] > target){
                right = mid -1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
