package Arrays;

import java.util.Arrays;

/**
 * Max Heap is CBT. All levels full except the leaf/last node level where filled from left. All leaves have same depth
 * Heap is stored in array. left node at 2k+1, right at 2k+2. array representation work becasue CBT is filled at last level from left to right.
 * MaxHeap properties - child node value lesser or equal to parent value. If after insertion a value, propery is voilated, we need to correct until root.
 * we can limit the size of Heap using MaxSize if needed keep only n no of minimum vals.
 */
public class HeapsMax {
    int maxSize = 10;
    int[] Heap = new int[this.maxSize + 1]; // 0th is to keep minimum int value
    int size = 0;
    int root = 1;

    public int[] getHeap() {
        return Heap;
    }

    public void setHeap(int[] heap) {
        Heap = heap;
    }

    public HeapsMax() {
        Heap[0] = Integer.MAX_VALUE;
    }
    private void swap(int fpos, int spos) {
        int tmp;
        tmp = Heap[fpos];

        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
        System.out.println("Current state of arr after swap");

        System.out.println(Arrays.toString(Heap));
    }
    public void insert(int val){
        // insert at the end always
        // then correct until root
        if (size >= maxSize) {
            return;
        }
        Heap[++size] = val;
        int current = size;
        while(Heap[current] > Heap[parent(current)]){
            swap(current, parent(current));
            current = parent(current);
        }
    System.out.println("Current state of arr");

        System.out.println(Arrays.toString(Heap));
    }

    private int parent(int current) {
        return current / 2;
    }

    public int getRoot(){
        return Heap[root];
    }
    public int delete(){
        // remove from root, then heapify
        System.out.println("Current state of arr before delete");
        int pop = Heap[root];
        Heap[root] = Heap[size--];
        System.out.println("Current state of arr before heapify");

        System.out.println(Arrays.toString(Heap));
        maxHeapify(root);
        System.out.println("Current state of arr after delete");

        System.out.println(Arrays.toString(Heap));
        return pop;

    }
    private boolean isLeaf(int pos)
    {
        if (pos >=  (size / 2)  &&  pos <= size)
        {
            return true;
        }
        return false;
    }

    private void maxHeapify(int pos) {
        // Choose the maximum of children(left or right), and swap with parent.

            if (!isLeaf(pos))
            {
                if ( Heap[pos] < Heap[leftChild(pos)]  || Heap[pos] < Heap[rightChild(pos)])

                {
                    if (Heap[leftChild(pos)] < Heap[rightChild(pos)])
                    {
                        swap(pos, rightChild(pos));

                        maxHeapify(rightChild(pos));
                    }else
                    {
                        swap(pos, leftChild(pos));

                        maxHeapify(leftChild(pos));
                    }
                }
            }
    }
    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }
    private int leftChild(int pos) {
        return (2 * pos);
    }

    public static void main(String[] agrs)
    {
        int[] arr = {1, 3, 5, 6, 9, 10, 14, 15, 32, 34};
        int[] arr1 = {4, 3, 5, 0, 6};
        int[] arr2 = {91, 83, 75, 66, 59, 40, 34, 25, 12, 4, 99, 101, 110};
        int N = 10;

        HeapsMax heaps = new HeapsMax();

        for(int i : arr1){
            heaps.insert(i);
        }

        //heaps.delete();

        //getTop20NumbersFromArray(arr2);
    }

    private static void getTop20NumbersFromArray(int[] arr2) {
        HeapsMax heaps = new HeapsMax();
        //int[] arr2 = {91, 83, 75, 66, 59, 40, 34, 25, 12, 4};
        int i =0;
        // insert first 5 elements
        while( i < 5){

            heaps.insert(arr2[i]);
            i++;
        }
        // insert at the top if element greater than root
        for( int j =i;j<arr2.length;j++){
            int top = heaps.getRoot();
            if(top < arr2[j]) {
                heaps.insert(arr2[j]);
                heaps.delete();
            }
        }
        for(int k =1;k<=5;k++){
            System.out.println(heaps.getHeap()[k]);
        }
    }


}
