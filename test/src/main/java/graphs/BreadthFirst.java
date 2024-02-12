package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author harish.kumar-mbp
 * createdOn 14/01/24
 */
public class BreadthFirst {

    static class Node {
        String name;
        List<Node> children = new ArrayList<Node>();

        public Node(String name) {
            this.name = name;
        }

        public List<String> breadthFirstSearch(List<String> array) {
            // Write your code here.
            Queue<Node> queue = new ArrayDeque<Node>();
            queue.add(this);
            List<String> res = new ArrayList<>();
            while(!queue.isEmpty()){
                Node cur = queue.poll();
                res.add(cur.name);
                for(Node node : cur.children){
                    queue.add(node);

                }
            }
            return res;
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }
    }
}
