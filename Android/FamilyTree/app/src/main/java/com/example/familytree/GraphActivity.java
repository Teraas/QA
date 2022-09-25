package com.example.familytree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.xqhs.graphs.graph.Graph;
import net.xqhs.graphs.graph.Node;
import net.xqhs.graphs.graph.SimpleEdge;
import net.xqhs.graphs.graph.SimpleNode;

import ImageUtility.TouchImageView;
import giwi.org.networkgraph.GraphSurfaceView;
import giwi.org.networkgraph.beans.NetworkGraph;
import giwi.org.networkgraph.beans.Vertex;

//import de.blox.graphview.Graph;
//import de.blox.graphview.GraphAdapter;
//import de.blox.graphview.GraphView;
//import de.blox.graphview.Node;
//import de.blox.graphview.energy.FruchtermanReingoldAlgorithm;
//import de.blox.graphview.tree.BuchheimWalkerAlgorithm;
//import de.blox.graphview.tree.BuchheimWalkerConfiguration;



public class GraphActivity extends AppCompatActivity {
    private int nodeCount = 1;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_graph);

        //imageView=findViewById(R.id.imageView);
        //imageView.setOnTouchListener(this);
        TouchImageView img = new TouchImageView(this);
        img.setImageResource(R.drawable.graph1);
        img.setMaxZoom(4f);
        setContentView(img);
        //scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        // example tree

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }

    private String getNodeText() {
        return "Node " + nodeCount++;
    }
}
