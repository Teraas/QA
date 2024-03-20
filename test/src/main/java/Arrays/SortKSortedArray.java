package Arrays;

import java.util.PriorityQueue;

/**
 * @author harish.kumar-mbp
 * createdOn 09/03/24
 */
public class SortKSortedArray {
    public static void main(String[] args){
        int[] arr = new int[]{-1, -3, -4, 2, 1, 3};
        int[] res = sortKSortedArray(arr, 2);
        System.out.println(res);
    }


    public static int[] sortKSortedArray(int[] array, int k) {
        // Write your code here.
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int l = array.length;
        int[] res = new int[l];
        for(int i =0;i< Math.min(k+1, l);i++){
            pq.add(array[i]);
        }
        int i =0;
        int j = k+1;
        while(i < array.length && j < array.length){
            res[i] = pq.poll();
            i++;

            pq.add(array[j]);
            j++;
        }
        while(!pq.isEmpty()){
            res[i] = pq.poll();
            i++;
        }
        return res;
    }
}
