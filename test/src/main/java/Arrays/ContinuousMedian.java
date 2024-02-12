package Arrays;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author harish.kumar-mbp
 * createdOn 05/02/24
 */
public class ContinuousMedian {

    static class ContinuousMedianHandler {
        PriorityQueue<Integer> firstHeap = new PriorityQueue<>(Comparator.reverseOrder()); //maxHeap
        PriorityQueue<Integer> secondHeap = new PriorityQueue<>(); // minHeap

        double median = 0;
        int size = 0;

        public void insert(int number) {
            // Write your code here.
            // if number > root & sizeDiff(first >second by max 1)of firstHeap then insert into second
            // if number > root & sizeDiff(first >second by max 1)of firstHeap then insert into second
            // 1, 10
            // 15,19,199
            int size1 = firstHeap.size();
            int size2 = secondHeap.size();
            if(size1 == 0 ){
                firstHeap.add(number);
            }
            else {
                int m1 = firstHeap.peek();
                //int m2 = secondHeap.peek();
                if(number < m1){
                    if(size1 <= size2){
                        firstHeap.add(number);
                    }
                    else {
                        firstHeap.add(number);
                        int tmp = firstHeap.poll(); // remove root
                        secondHeap.add(tmp);

                    }
                }
                else {
                    if(size1 >= size2){
                        secondHeap.add(number);
                    }

                    else {
                        secondHeap.add(number);
                        int tmp = secondHeap.poll(); // remove root
                        firstHeap.add(tmp);

                    }

                }
            }

            size +=1;
            updateMedian();
        }

        private void updateMedian() {
            if (secondHeap.size()<1){
                median = firstHeap.peek();
            }
            else if (firstHeap.size()<1){
                median = secondHeap.peek();
            }
            else {
                int m1 = (float)firstHeap.size() > 0 ? firstHeap.peek() : null;
                int m2 = (float) secondHeap.size() > 0 ? secondHeap.peek() : null;
                if(size % 2 ==0)
                    median = ((float)m1 + m2)/2;
                else {
                    median = firstHeap.size() > secondHeap.size() ? m1 : m2;
                }
            }



        }

        public double getMedian() {
            return median;
        }
    }

    public static void main(String[] args){
        ContinuousMedianHandler handler = new ContinuousMedianHandler();
        handler.insert(5);
        handler.insert(10);
//        handler.insert(15);
//        handler.insert(199);
//        handler.insert(19);
//        handler.insert(14);
        System.out.println("Median is -> " + handler.getMedian());
    }
}
