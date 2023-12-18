/*
 * Name: Jinhyo Park
 * Student ID #: 2018121022
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Iterator;

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class Graph implements IGraph {
    private int numNodes, numEdges;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private HashMap<Node, HashMap<Node, Edge>> adjacencyList;
    /*
     * you may declare variables here
     */
    int mstWeight;

    Graph() {
        /*
         * implement your constructor here.
         */
        numNodes = 0;
        numEdges = 0;
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        adjacencyList = new HashMap<>();
        mstWeight = 0;
    }

    @Override
    public Node addNode() {
        /*
         * Function input:
         *  - none
         *
         * Does:
         * adds a new node to the graph and returns it.
         * each node in the graph should have a label from 0 and |V|-1,
         * in the order of instantiation.
         */
        Node newNode = new Node(numNodes);
        nodes.add(newNode);
        adjacencyList.put(newNode, new HashMap<>());
        numNodes++;
        return newNode;
    }

    @Override
    public Edge addEdge(Node u, Node v, int weight) {
        /*
         * Function input:
         *  - none
         *
         * Does:
         * adds an edge with the given endpoints and weight to the graph and returns it.
         */
        Edge newEdge = new Edge(u, v, weight);
        if (adjacencyList.get(u).containsKey(v)) {
            return null;
        }
        edges.add(newEdge);
        adjacencyList.get(u).put(v, newEdge);
        adjacencyList.get(v).put(u, newEdge);
        numEdges++;
        return newEdge;
    }

    @Override
    public int getNumNodes() {
        /*
         * Function input:
         *  - none
         *
         * Does:
         * returns the number of nodes in the graph.
         */
        return numNodes;
    }

    @Override
    public int getNumEdges() {
        /*
         * Function input:
         *  - none
         *
         * Does:
         * returns the number of nodes in the graph.
         */
        return numEdges;
    }

    @Override
    public ArrayList<Edge> minSpanningTree() {
        /*
         * Function input:
         *  - none
         *
         * Does:
         * returns an ArrayList containing all the edges in a minimum spanning tree of the graph.
         * if there are multiple MSTs, returns one of them.
         */
        ArrayList<Edge> mstEdges = new ArrayList<>();
        edges.sort(Edge::compareTo);

        UnionFind uf = new UnionFind(numNodes);
        mstWeight = 0;
        for (Edge edge : edges) {
            Node u = edge.getU();
            Node v = edge.getV();

            if (uf.find(u.getLabel()) != uf.find(v.getLabel())) {
                mstEdges.add(edge);
                mstWeight += edge.getWeight();
                uf.union(u.getLabel(), v.getLabel());
            }
        }

        return mstEdges;
    }

    private static class UnionFind {
        private final int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (x < y) {
                parent[rootY] = rootX;
            }
            else {
                parent[rootX] = rootY;
            }
        }
    }


    @Override
    public int minSpanningTreeWeight() {
        /*
         * Function input:
         *  - none
         *
         * Does:
         * returns the weight of a minimum spanning tree of the graph.
         */
        minSpanningTree();
        return mstWeight;
    }

    @Override
    public boolean areUVConnected(Node u, Node v) {
        /*
         * Function input:
         *  - Nodes u,v
         *
         * Does:
         * returns true if u and v are connected. returns false otherwise.
         */
        HashSet<Node> visited = new HashSet<>();
        return isPathExists(u, v, visited);
    }

    private boolean isPathExists(Node u, Node v, HashSet<Node> visited) {
        if (u == v) {
            return true;
        }

        visited.add(u);
        HashMap<Node, Edge> neighbors = adjacencyList.get(u);
        if (neighbors != null) {
            for (Edge edge : neighbors.values()) {
                Node neighbor = (edge.getU() == u) ? edge.getV() : edge.getU();
                if (!visited.contains(neighbor) && isPathExists(neighbor, v, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean isConnected() {
        HashSet<Node> visited = new HashSet<>();
        dfs(nodes.get(0), visited);
        return visited.size() == numNodes;
    }

    private void dfs(Node node, HashSet<Node> visited) {
        visited.add(node);

        HashMap<Node, Edge> neighbors = adjacencyList.get(node);
        if (neighbors != null) {
            for (Edge edge : neighbors.values()) {
                Node neighbor = (edge.getU() == node) ? edge.getV() : edge.getU();
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, visited);
                }
            }
        }
    }

    @Override
    public int numConnnectedComponents() {
        /*
         * Function input:
         *  - none
         *
         * Does:
         * returns the number of connected components of the graph.
         */
        HashSet<Node> visited = new HashSet<>();
        int components = 0;
        for (Node node : nodes) {
            if (!visited.contains(node)) {
                dfs(node, visited);
                components++;
            }
        }
        return components;
    }

    @Override
    public HashMap<Node,Integer> dijkstra(Node source) {
        /*
         * Function input:
         *  - a source Node
         *
         * Does:
         * runs Dijkstra's algorithm on the graph for the given source node.
         * the HashMap should have each Node instance in the graph as the key
         * and the distance from source as the value.
         * unreachable nodes should have Integer.MAX_VALUE as the value.
         */
        HashMap<Node, Integer> distances = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> distances.get(a) - distances.get(b));
        if (!nodes.contains(source)) {
            return null;
        }

        for (Node node : nodes) {
            distances.put(node, Integer.MAX_VALUE);
        }

        distances.put(source, 0);
        pq.offer(source);

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int distanceToCurr = distances.get(curr);

            HashMap<Node, Edge> neighbors = adjacencyList.get(curr);
            if (neighbors != null) {
                for (Edge edge : neighbors.values()) {
                    Node neighbor = (edge.getU() == curr) ? edge.getV() : edge.getU();
                    int weight = edge.getWeight();
                    int distanceThroughCurr = distanceToCurr + weight;

                    if (distanceThroughCurr < distances.get(neighbor)) {
                        distances.put(neighbor, distanceThroughCurr);
                        pq.offer(neighbor);
                    }
                }
            }
        }

        return distances;
    }

    @Override
    public int shortestPathLength(Node source, Node target) {
        /*
         * Function input:
         *  - Nodes source, target
         *
         * Does:
         * returns the length of the shortest path from source to target.
         * returns Integer.MAX_VALUE if target is unreachable from source.
         */
        HashMap<Node, Integer> distances = dijkstra(source);
        if (!distances.containsKey(target) || distances.get(target) == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return distances.get(target);
    }
}
