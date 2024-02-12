package graphs;

import java.util.*;

/**
 * @author harish.kumar-mbp
 * createdOn 17/01/24
 */
public class Solution2 {

    public  boolean  solution(int N, int[] A, int[] B) {
        // Implement your solution here
        int edges = A.length;
        //build edgeList from edges;
        Map<Integer, List<Integer>> edgeList = new HashMap<>();
        for(int i = 1; i<=N;i++){
            edgeList.put(i, new ArrayList<>());
        }
        int emptyNodes = N;
        if(edges < N-1)
            return false;
        for(int i =0;i<edges;i++){
            int currentEdge = A[i];
            if(edgeList.containsKey(currentEdge)){
                edgeList.get(currentEdge).add(B[i]);
            }
            else{
                List<Integer> neighbours = new ArrayList<>();
                neighbours.add(B[i]);
                edgeList.put(A[i], neighbours);
            }
        }
        for(int j =0;j<edges;j++){
            int currentEdge = B[j];
            if(edgeList.containsKey(currentEdge)){
                edgeList.get(currentEdge).add(A[j]);
            }
            else{
                List<Integer> neighbours = new ArrayList<>();
                neighbours.add(A[j]);
                edgeList.put(B[j], neighbours);
            }
        }

        // check if the increasing path exists
        // use simple breadth search as its indirected.
        // for every node check the neighbour and compare with prev node value.
        boolean res = checkIfPathExist(edgeList, N);
        System.out.println(res);
        return res;
    }

    private static boolean checkIfPathExist(Map<Integer, List<Integer>> edgeList, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        int currentValOfSeq = 0;
        while(!queue.isEmpty() || currentValOfSeq <n){
            // get neighbours
            currentValOfSeq = queue.poll();
            List<Integer> neighbours = edgeList.get(currentValOfSeq);
            if(neighbours.contains(currentValOfSeq+1))
                queue.add(++currentValOfSeq);
            else
                break;
        }
        if(currentValOfSeq == n)
            return true;
        else
            return false;
    }

    public static void main(String[] args){
        int[] A = {2, 4, 5, 3};
        int[] B = {3, 5, 6, 4};
        Solution2 sol = new Solution2();
        sol.solution(6, A, B);
    }
}
