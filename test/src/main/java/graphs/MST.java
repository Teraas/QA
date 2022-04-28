package graphs;

public class MST {
    private static final int V = 5;
    private void primsMST(int graph[][]){
    int constructedMST[] = new int[V];
    int keysEdgeWeight[] = new int[V];
    boolean pickedMSTlist[] = new boolean[V]; // set to false



        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            keysEdgeWeight[i] = Integer.MAX_VALUE;
            pickedMSTlist[i] = false;
        }
        // start with first node and set weight
        keysEdgeWeight[0] = 0;
        constructedMST[0] = -1;
    }

    int minKey(int key[], Boolean mstSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }
    public static void main(String[] args){
        //GraphNode graph = new GraphNode("1");
        // Use Matrix as Graph representation - Adjacency matrix
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 } };

    }
}
