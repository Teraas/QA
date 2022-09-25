package graphs;

import java.util.*;

public class ShortestPathMeshNetwork {

    public static void main(String[] agrs){
        // This is graph representation as Adj List/map
        Map<String, String[]> network = new HashMap<String, String[]>() {{
            put("Min",     new String[] { "William", "Jayden", "Omar" });
            put("William", new String[] { "Min", "Noam" });
            put("Jayden",  new String[] { "Min", "Amelia", "Ren", "Noam" });
            put("Ren",     new String[] { "Jayden", "Omar" });
            put("Amelia",  new String[] { "Jayden", "Adam", "Miguel" });
            put("Adam",    new String[] { "Amelia", "Miguel", "Sofia", "Lucas" });
            put("Miguel",  new String[] { "Amelia", "Adam", "Liam", "Nathan" });
            put("Noam",    new String[] { "Nathan", "Jayden", "William" });
            put("Omar",    new String[] { "Ren", "Min", "Scott" });
        }};
        //do_BFS(network, "Min");
        System.out.println(get_distance_using_BFS(network, "Min", "Scott1"));
    }

    public static void do_BFS(Map<String, String[]> graph, String source){
        Queue<String> queue = new ArrayDeque();

        queue.add(source);
        while(!queue.isEmpty()){
            String current = queue.poll();

            System.out.println("Current node --> " + current);
            System.out.println("Current queue --> " + queue);
            if(graph.get(current)==null)
                return;
            for(String s:graph.get(current)){
                queue.add(s);
            }
        }

    }
    // This is hasPath solution, to calculate distance we need to check how many level BFS went
    public static boolean search_path_using_BFS(Map<String, String[]> graph, String source, String dest){
        Queue<String> queue = new ArrayDeque();
        Set<String> visitedNodes = new HashSet<>();
        queue.add(source);
        int dist = 0;
        while(!queue.isEmpty()){
            String current = queue.poll();

            System.out.println("Current node --> " + current);
            System.out.println("Current queue --> " + queue);
            if(current.equals(dest)) {
                System.out.println("The distance is ->  " +dist);
                return true;
            }
            if(graph.get(current)==null) // continue if leave node found
                continue;
            if(visitedNodes.contains(current)) // skip already checked
                continue;
            visitedNodes.add(current);
            for(String s:graph.get(current)){
                if(visitedNodes.contains(s))
                    continue;
                else
                    queue.add(s);
            }
            dist++;
        }
            return false;
    }

    // This is hasPath solution, to calculate distance we need to check how many level BFS went
    public static boolean get_distance_using_BFS(Map<String, String[]> graph, String source, String dest){
        Queue<Map<String, Integer>> queue = new ArrayDeque();
        Set<String> visitedNodes = new HashSet<>();
        Map<String, Integer> queueMap = new LinkedHashMap<>();
        queueMap.put(source,0);
        Map.Entry<String, Integer> entry = null;
        queue.add(queueMap);
        //int dist = 0;
        while(!queue.isEmpty()){
            Map current = queue.poll();
            entry = (Map.Entry<String, Integer>) current.entrySet().iterator().next();
            System.out.println("Current node --> " + entry.getKey());
            System.out.println("Current queue --> " + queue);
            System.out.println("The distance is ->  " +entry.getValue());
            if(entry.getKey().equals(dest)) {
                System.out.println("The distance is ->  " +entry.getValue());
                return true;
            }
            if(graph.get(entry.getKey())==null) // continue if leave node found
                continue;
            if(visitedNodes.contains(entry.getKey())) // skip already checked
                continue;
            visitedNodes.add(entry.getKey());
            for(String s:graph.get(entry.getKey())){
                if(visitedNodes.contains(s))
                    continue;
                else {
                    Map<String, Integer> queueMapNeighbor = new LinkedHashMap<>();
                    queueMapNeighbor.put(s, entry.getValue() + 1);
                    queue.add(queueMapNeighbor);
                }
            }
            //dist++;
        }
        return false;
    }
}
