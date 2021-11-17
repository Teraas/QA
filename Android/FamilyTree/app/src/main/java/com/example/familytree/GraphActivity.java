package com.example.familytree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.blox.graphview.Graph;
import de.blox.graphview.GraphAdapter;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;
import de.blox.graphview.energy.FruchtermanReingoldAlgorithm;
import de.blox.graphview.tree.BuchheimWalkerAlgorithm;
import de.blox.graphview.tree.BuchheimWalkerConfiguration;



public class GraphActivity extends AppCompatActivity {
    private int nodeCount = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        GraphView graphView = findViewById(R.id.graph);

        // example tree
        final Graph graph = new Graph();
        final Node node1 = new Node("Parent");
        final Node node2 = new Node("Child 1");
        final Node node3 = new Node("Child 2");
        //final Node node4 = new Node(getNodeText());

        graph.addEdge(node1, node2);
        graph.addEdge(node1, node3);
        //graph.addEdge(node1, node4);

        // you can set the graph via the constructor or use the adapter.setGraph(Graph) method
        GraphAdapter adapter = new GraphAdapter<GraphView.ViewHolder>(graph) {

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @NonNull
            @Override
            public GraphView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.node, parent, false);
                return new SimpleViewHolder(view);
            }

            @Override
            public void onBindViewHolder(GraphView.ViewHolder viewHolder, Object data, int position) {
                ((SimpleViewHolder) viewHolder).textView.setText(data.toString());
            }
        };
        graphView.setAdapter(adapter);

        // you can set the graph via the constructor or use the adapter.setGraph(Graph) method
//        final BaseGraphAdapter<RecyclerView.ViewHolder> adapter = new BaseGraphAdapter<RecyclerView.ViewHolder>(graph) {
//
//            @NonNull
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.node, parent, false);
//                return new SimpleViewHolder(view);
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Object data, int position) {
//                ((SimpleViewHolder)viewHolder).textView.setText(data.toString());
//            }
//        };
//        graphView.setAdapter(adapter);

        // set the algorithm here
//        final BuchheimWalkerConfiguration configuration = new BuchheimWalkerConfiguration.Builder()
//                .setSiblingSeparation(100)
//                .setLevelSeparation(300)
//                .setSubtreeSeparation(300)
//                .setOrientation(BuchheimWalkerConfiguration.ORIENTATION_TOP_BOTTOM)
//                .build();
        //graphView.setLayout(new BuchheimWalkerAlgorithm(configuration));
        graphView.setLayout(new FruchtermanReingoldAlgorithm(2));
    }
    private String getNodeText() {
        return "Node " + nodeCount++;
    }
}

class SimpleViewHolder extends GraphView.ViewHolder {
    TextView textView;

    SimpleViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text);
    }
}