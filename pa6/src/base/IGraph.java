import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Iterator;

public interface IGraph {
    public Node addNode();
    public Edge addEdge(Node u, Node v, int weight);
    public int getNumNodes();
    public int getNumEdges();
    public ArrayList<Edge> minSpanningTree();
    public int minSpanningTreeWeight();
    public boolean areUVConnected(Node u, Node v);
    public boolean isConnected();
    public int numConnnectedComponents();
    public HashMap<Node,Integer> dijkstra(Node source);
    public int shortestPathLength(Node source, Node target);
}
