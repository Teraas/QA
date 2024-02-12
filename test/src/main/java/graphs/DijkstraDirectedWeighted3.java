package graphs;

import java.util.*;

/**
 * @author harish.kumar-mbp
 * createdOn 16/01/24
 */
public class DijkstraDirectedWeighted3 {

    public static int[]  dijkstra(int[][][] edges, int source){
        int vertexes = edges.length;
        Set<Integer> visitedNode = new HashSet<>();
        Map<Integer, Integer> distances = new HashMap<>();
        for(int i =0;i<vertexes;i++){
            distances.put(i, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        while(visitedNode.size() <vertexes){
            // check everything
            // find the verteex with min distance currently.

            // then update the distances of that vertexes neighbours

            NeighborWeightPair newSource = getTheLeastDistanceNeighbour(distances, visitedNode);
            visitedNode.add(newSource.neighbor);
            int currentDistance = newSource.weight;

            if(newSource.weight == Integer.MAX_VALUE)
                break;
            for(int[] node: edges[newSource.neighbor]){
                // update the neighbours
                if(visitedNode.contains(node[0]))
                    continue;
                int currentMinDistance = currentDistance + node[1];
                if(currentMinDistance < distances.get(node[0])){
                    distances.put(node[0], currentMinDistance);
                }
            }

        }
        System.out.println("Output is - " + distances);
        int[] res = new int[vertexes];
        int i =0;
        for(int s : distances.keySet()){
            res[i] = distances.get(s);
            i++;
        }
        return res;
    }

    private static NeighborWeightPair getTheLeastDistanceNeighbour(Map<Integer, Integer> distances, Set<Integer> visitedNodes) {
        Integer minDistance = Integer.MAX_VALUE;
        int node = 0;
        for(int s : distances.keySet()){
            if(visitedNodes.contains(s))
                continue;
            if(distances.get(s) < minDistance) {
                minDistance = distances.get(s);
                node = s;
            }
        }
        return  new NeighborWeightPair(node,minDistance);
    }

    static class NeighborWeightPair {
        int neighbor;
        int weight;

        public NeighborWeightPair(int neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }


    public static void main(String[] args) {
        // Example graph
        Map<String, List<DijkstraDirectedWeighted.NeighborWeightPair>> graph = new HashMap<>();
        graph.put("Alice", Arrays.asList(new DijkstraDirectedWeighted.NeighborWeightPair("Bob", 1), new DijkstraDirectedWeighted.NeighborWeightPair("Charlie", 2)));
        graph.put("Bob", Arrays.asList(new DijkstraDirectedWeighted.NeighborWeightPair("Alice", 1), new DijkstraDirectedWeighted.NeighborWeightPair("David", 3)));
        graph.put("Charlie", Arrays.asList(new DijkstraDirectedWeighted.NeighborWeightPair("Alice", 2), new DijkstraDirectedWeighted.NeighborWeightPair("Eve", 2)));
        graph.put("David", Arrays.asList(new DijkstraDirectedWeighted.NeighborWeightPair("Bob", 3), new DijkstraDirectedWeighted.NeighborWeightPair("Frank", 3)));
        graph.put("Eve", Arrays.asList(new DijkstraDirectedWeighted.NeighborWeightPair("Charlie", 2), new DijkstraDirectedWeighted.NeighborWeightPair("Frank", 2)));
        graph.put("Frank", Arrays.asList(new DijkstraDirectedWeighted.NeighborWeightPair("David", 1), new DijkstraDirectedWeighted.NeighborWeightPair("Eve", 2)));

        String startUser = "Alice";
        String endUser = "Frank";

         //dijkstra(graph, startUser);

//        if (shortestRoute != null) {
//            System.out.println("Shortest route from " + startUser + " to " + endUser + ": " + shortestRoute);
//        } else {
//            System.out.println("No route found from " + startUser + " to " + endUser);
//        }
        int[][][] edges = {{{1,7}},{{2,6},{3, 20},{4,3}},{{3,14}},{{4,2}},{},{}};
        System.out.println(edges.length);
        int[][] tmp = edges[1];
        System.out.println(tmp);
        System.out.println(edges[0][0]);
        System.out.println(tmp[1]);
        dijkstra(edges, 0);
    }

}
