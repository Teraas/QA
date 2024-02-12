package graphs;

import java.util.*;

/**
 * @author harish.kumar-mbp
 * createdOn 16/01/24
 */
public class DijkstraDirectedWeighted2 {

    public static int[]  dijkstra(Map<String, List<DijkstraDirectedWeighted.NeighborWeightPair>> edges, String source){
        int vertexes = edges.size();
        Set<String> visitedNode = new HashSet<String>();
        Map<String, Integer> distances = new HashMap<>();
        for(String vert :edges.keySet() ){
            distances.put(vert, Integer.MAX_VALUE);
        }
        distances.put(source, 0);
        while(visitedNode.size() <vertexes){
            // check everything
            // find the verteex with min distance currently.

            // then update the distances of that vertexes neighbours

            String newSource = getTheLeastDistanceNeighbour(distances, visitedNode);
            visitedNode.add(newSource);
            int currentDistance = distances.get(newSource);
            for(DijkstraDirectedWeighted.NeighborWeightPair node : edges.get(newSource)){
                // update the neighbours
                if(visitedNode.contains(node.neighbor))
                    continue;
                int currentMinDistance = currentDistance + node.weight;
                if(currentMinDistance < distances.get(node.neighbor)){
                    distances.put(node.neighbor, currentMinDistance);
                }
            }
        }
        System.out.println("Output is - " + distances);
        int[] res = new int[vertexes];
        int i =0;
        for(String s : distances.keySet()){
            res[i] = distances.get(s);
            i++;
        }
        return res;
    }

    private static String getTheLeastDistanceNeighbour(Map<String, Integer> distances, Set<String> visitedNodes) {
        Integer minDistance = Integer.MAX_VALUE;
        String node = "";
        for(String s : distances.keySet()){
            if(visitedNodes.contains(s))
                continue;
            if(distances.get(s) < minDistance) {
                minDistance = distances.get(s);
                node = s;
            }
        }
        return  node;
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

         dijkstra(graph, startUser);

//        if (shortestRoute != null) {
//            System.out.println("Shortest route from " + startUser + " to " + endUser + ": " + shortestRoute);
//        } else {
//            System.out.println("No route found from " + startUser + " to " + endUser);
//        }
        int[][][] edges = {{{1,7}},{{2,6},{3, 20},{4,3}},{{3,14}},{{4,2}},{},{}};
        System.out.println(edges.length);
        System.out.println(edges[0].length);
        System.out.println(edges[0][0].length);
        System.out.println(edges[1].length);
    }

}
