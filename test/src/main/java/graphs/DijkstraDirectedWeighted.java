package graphs;

import java.util.*;

/**
 * @author harish.kumar-mbp
 * createdOn 15/01/24
 */
public class DijkstraDirectedWeighted {

    public static List<String> dijkstra(Map<String, List<NeighborWeightPair>> graph, String startUser, String endUser) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> visited = new HashMap<>();

        for (String user : graph.keySet()) {
            distances.put(user, Integer.MAX_VALUE);
        }

        distances.put(startUser, 0);

        for (int i = 0; i < graph.size() - 1; i++) {
            String minDistanceUser = getMinDistanceUser(graph, distances, visited);
            visited.put(minDistanceUser, "visited");

            for (NeighborWeightPair neighbor : graph.get(minDistanceUser)) {
                int newDistance = distances.get(minDistanceUser) + neighbor.weight;
                if (newDistance < distances.get(neighbor.neighbor)) {
                    distances.put(neighbor.neighbor, newDistance);
                    visited.put(neighbor.neighbor, minDistanceUser);
                }
            }
        }

        // Reconstruct the path
        List<String> path = new ArrayList<>();
        String currentUser = endUser;
        while (currentUser != null) {
            path.add(0, currentUser);
            currentUser = visited.get(currentUser);
        }

        // If no path is found
        return path.size() > 1 ? path : null;
    }

    private static String getMinDistanceUser(Map<String, List<NeighborWeightPair>> graph, Map<String, Integer> distances, Map<String, String> visited) {
        int minDistance = Integer.MAX_VALUE;
        String minDistanceUser = null;

        for (String user : graph.keySet()) {
            if (!visited.containsKey(user) && distances.get(user) < minDistance) {
                minDistance = distances.get(user);
                minDistanceUser = user;
            }
        }

        return minDistanceUser;
    }

    public static void main(String[] args) {
        // Example graph
        Map<String, List<NeighborWeightPair>> graph = new HashMap<>();
        graph.put("Alice", Arrays.asList(new NeighborWeightPair("Bob", 1), new NeighborWeightPair("Charlie", 2)));
        graph.put("Bob", Arrays.asList(new NeighborWeightPair("Alice", 1), new NeighborWeightPair("David", 3)));
        graph.put("Charlie", Arrays.asList(new NeighborWeightPair("Alice", 2), new NeighborWeightPair("Eve", 2)));
        graph.put("David", Arrays.asList(new NeighborWeightPair("Bob", 3), new NeighborWeightPair("Frank", 1)));
        graph.put("Eve", Arrays.asList(new NeighborWeightPair("Charlie", 2), new NeighborWeightPair("Frank", 2)));
        graph.put("Frank", Arrays.asList(new NeighborWeightPair("David", 1), new NeighborWeightPair("Eve", 2)));

        String startUser = "Alice";
        String endUser = "Frank";

        List<String> shortestRoute = dijkstra(graph, startUser, endUser);

        if (shortestRoute != null) {
            System.out.println("Shortest route from " + startUser + " to " + endUser + ": " + shortestRoute);
        } else {
            System.out.println("No route found from " + startUser + " to " + endUser);
        }
        int[][][] edges = {{{1,7}},{{2,6},{3, 20},{4,3}},{{3,14}},{{4,2}},{},{}};
        System.out.println(edges.length);
        System.out.println(edges[0].length);
        System.out.println(edges[0][0].length);
        System.out.println(edges[1].length);
    }
    public int[] dijkstrasAlgorithm(int start, int[][][] edges) {
        // Write your code here.
        int d = edges.length;
        return new int[] {};
    }

    static class NeighborWeightPair {
        String neighbor;
        int weight;

        public NeighborWeightPair(String neighbor, int weight) {
            this.neighbor = neighbor;
            this.weight = weight;
        }
    }

}
