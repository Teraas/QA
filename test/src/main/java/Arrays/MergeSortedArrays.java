package Arrays;

import java.util.*;

/**
 * @author harish.kumar-mbp
 * @created 19/09/22
 */
public class MergeSortedArrays {
    // naive solution would be creatign the result array and adding all the elements of the arrays.
    // Then sort it. Time would be O(nlogn)
    public static void main(String[] args){
        List<List<Integer>> arrays = new ArrayList<>();

        Integer[] ll1 = new Integer[]{1, 5, 9, 21};
        List<Integer> l1 = new ArrayList<>();
        Collections.addAll(l1, ll1);

        Integer[] ll2 = new Integer[]{-1, 0};
        List<Integer> l2 = new ArrayList<>();
        Collections.addAll(l2, ll2);

        Integer[] ll3 = new Integer[]{-124, 81, 121};
        List<Integer> l3 = new ArrayList<>();
        Collections.addAll(l3, ll3);

        Integer[] ll4 = new Integer[]{3, 6, 12, 20, 150};
        List<Integer> l4 = new ArrayList<>();
        Collections.addAll(l4, ll4);

        arrays.add(l1);arrays.add(l2);arrays.add(l3);arrays.add(l4);
        mergeKSortedArrays(arrays);
    }

    public static List<Integer> mergeKSortedArrays(List<List<Integer>> arrays){
        // use a priority queue MinHeap and track first elments of the arrays.
        // keep adding the min/top elements for pq, and add next element from the array for which root was removed from pq.
        // do this until array is empty and pq is empty.
        PriorityQueue<ArrayNode> pq = new PriorityQueue<>(new Comparator<ArrayNode>() {
            @Override
            public int compare(ArrayNode o1, ArrayNode o2) {
                if(o2.num == o1.num)
                    return 0;
                return o2.num > o1.num ? -1 : 1;
            }
        });
        for(int i =0;i<arrays.size();i++){
            pq.add(new ArrayNode(arrays.get(i).get(0), i, 1));
        }
        List<Integer> res = new ArrayList<>();
        while(!pq.isEmpty()){
            ArrayNode node = pq.poll();
            res.add(node.num);


            if(node.nextIndex  < arrays.get(node.arraysIndex).size()){
                node.num = arrays.get(node.arraysIndex).get(node.nextIndex);
                node.nextIndex = node.nextIndex +1;
                pq.add(node);
            }
        }

    return res;
    }

    static class ArrayNode {
        int num;
        int arraysIndex;
        int nextIndex;

        public ArrayNode(int num, int arraysIndex, int nextIndex) {
            this.num = num;
            this.arraysIndex = arraysIndex;
            this.nextIndex = nextIndex;
        }
    }
}
