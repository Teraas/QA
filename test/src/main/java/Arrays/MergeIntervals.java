package Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author harish.kumar-mbp
 * createdOn 16/01/24
 */
public class MergeIntervals {

    public static int[][] mergeOverlappingIntervals(int[][] intervals) {
        // Write your code here.
        // get max as this could be fixed range.
        // create an array with min and max
        // traverse the intervals again and set the array using interval ranges.
        // traverse again the array and create the merged intervals
        // above would work if end of a interval = startOfNext-1

        // new approach - sort the intervals by start index.
        // sort
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        System.out.println(intervals);
        List<int[]> res = new ArrayList<>();
        int start = 0;
        res.add(intervals[0]);
        for(int i =1;i<intervals.length;i++){
            if(res.get(start)[1] >= intervals[i][0]){
                int a = res.get(start)[0];
                int b = intervals[i][1];
                if(res.get(start)[1] >= intervals[i][1])
                    b = res.get(start)[1];;


                res.remove(start);
                int[] arr = {a, b};
                res.add(arr);
            }
            else{
                res.add(intervals[i]);
                start++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args){
        int[][] intervals = {{3,5},{1,2},{4, 7},{6,8},{1,10}};
        int[][] rs = mergeOverlappingIntervals(intervals);
        System.out.println(rs);
    }
}
