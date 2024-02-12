package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author harish.kumar-mbp
 * createdOn 14/01/24
 */
public class RiverSizes {

    public static List<Integer> riverSizes(int[][] matrix) {
        // Write your code here.
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        System.out.println(matrix.length + " and " + matrix[0].length);
        List<Integer> rivers = new ArrayList<>();
        for(int i=0;i<matrix.length;i++){
            for(int j =0; j<matrix[0].length;j++){
                if(!visited[i][j]) {
                    int size = getRiverForNode(i, j, matrix, visited);
                    if(size>0)
                        rivers.add(size);
                }
            }
        }
        return rivers;
    }

    private static int getRiverForNode(int i, int j, int[][] matrix, boolean[][] visited) {
        int size = 0;
        Stack<List<Integer>> nodesToVisit = new Stack<>();
        nodesToVisit.add(new ArrayList<Integer>(){{add(i);add(j);}});
        while (nodesToVisit.size()>0){
            List<Integer> curr = nodesToVisit.pop();
            int idx = curr.get(0);
            int idy = curr.get(1);
            if(visited[idx][idy] )
                continue;
            visited[idx][idy] = true;
            if(matrix[idx][idy] == 1){
                List<List<Integer>> neighboursToVisit = getTheNeighbours(idx, idy, matrix, visited);
                size++;
                for(List<Integer> ng : neighboursToVisit)
                    nodesToVisit.add(ng);
            }
        }
        return size;
    }

    private static List<List<Integer>> getTheNeighbours(int idx, int idy, int[][] matrix, boolean[][] visited) {
        List<List<Integer>> neighboursToVisit = new ArrayList<>();
        if(idx >0 && !visited[idx-1][idy])
            neighboursToVisit.add(new ArrayList<Integer>(){{add(idx-1);add(idy);}});
        if(idx < matrix.length-1 && !visited[idx+1][idy])
            neighboursToVisit.add(new ArrayList<Integer>(){{add(idx+1);add(idy);}});

        if(idy >0 && !visited[idx][idy-1])
            neighboursToVisit.add(new ArrayList<Integer>(){{add(idx);add(idy-1);}});
        if(idy < matrix[0].length-1 && !visited[idx][idy+1])
            neighboursToVisit.add(new ArrayList<Integer>(){{add(idx);add(idy+1);}});

        return neighboursToVisit;
    }


}
