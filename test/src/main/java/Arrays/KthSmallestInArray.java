package Arrays;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author harish.kumar-mbp
 * createdOn 09/03/24
 */
public class KthSmallestInArray {

    public static int quickselectPQ(int[] array, int k) {
        // Write your code here.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        // keep onky k items in heap.
        for(int n : array){
            pq.add(n);

            if(pq.size() >k){
                pq.poll();
            }
        }
        return pq.poll();
    }

    public static int quickselect(int[] array, int k) {
        int l = array.length;
        int left = 0;
        int right = l-1;
        while(left <= right){
            // find pivot
            int pivot = getPivot(array, left, right);
            if(pivot == k-1){
                return array[pivot];
            } else if (pivot > k-1){
                right = pivot -1;
            } else {
                left = pivot +1;
            }

        }
        return -1;
    }

    private static int getPivot(int[] array, int left, int right) {
        int pivot = array[right];
        int l = left;

        for(int i =l;i<=right;i++){
            // keep putting elements smaller than pivot to left side
            if(pivot > array[i]){
                swap(array, l, i);
                l++;
            }
        }
        // put pivot in its correct place
        swap(array, l, right);
        return l;
    }

    private static void swap(int[] array, int l, int right) {
        int tmp = array[l];
        array[l] = array[right];
        array[right] = tmp;
    }
}
