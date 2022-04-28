package Arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class NextPermutation {
    public static void main(String[] args){
        int[] nums = {1, 3,6,5,4}; // 1, 3, 5, 4,6
        nextPerm(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPerm(int[] nums){
        int len = nums.length;
        int left = len - 2;
        /**
         * from right to left, search for the first one which is smaller than the right digit.
         */
        while (left >= 0 && nums[left] >= nums[left + 1]) {
            left--;
        }
        System.out.println("first left " + nums[left]);

        if (left >= 0) {
            int right = nums.length - 1;
            while (right >= 0 && nums[left] >= nums[right]) {
                right--;
            }
            System.out.println("first right " + nums[right]);
            swap(nums, left, right);
            System.out.println(Arrays.toString(nums));
        }
        reverse(nums, left + 1);
    }
    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    public static void reverse(int[] nums, int low) {
        int left = low;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
