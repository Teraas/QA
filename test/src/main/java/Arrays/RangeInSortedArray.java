package Arrays;

/**
 * @author harish.kumar-mbp
 * createdOn 09/03/24
 */
public class RangeInSortedArray {
    public static void main(String[] args){
        int[] arr = new int[]{0, 1, 21, 33, 45, 45, 45, 45, 45, 45, 61, 71, 73};
        int[] res = searchForRange(arr, 45);
        System.out.println(res);

    }

    public static int[] searchForRange(int[] array, int target) {
        // Write your code here.
        int l = searchForRangeL(array, target, 0, array.length);
        int r = searchForRangeR(array, target, 0, array.length);
        return new int[] {l, r};
    }
    public static int searchForRangeL(int[] array, int target, int l, int r) {
        // Write your code here.

        while(l <= r) {
            int mid = l + (r -l) /2;
            if (array[mid] < target) {
                l = mid + 1;
            } else if (array[mid] > target) {
                r = mid - 1;
            } else {
                if ( mid -1 >0 && array[mid-1] == target) {
                    r =  mid-1;
                }
                else{
                    return mid;
                }
            }
        }
        return -1;
    }

    public static int searchForRangeR(int[] array, int target, int l, int r) {
        // Write your code here.

        while(l <= r) {
            int mid = l + (r -l) /2;
            if (array[mid] < target) {
                l = mid + 1;
            } else if (array[mid] > target) {
                r = mid - 1;
            } else {
                if (mid +1 < array.length && array[mid+1] == target) {
                    l =  mid+1;
                }
                else{
                    return mid;
                }
            }
        }
        return -1;
    }
}
