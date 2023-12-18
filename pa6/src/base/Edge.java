class Edge implements Comparable<Edge>
{
    private Node u,v;
    private int weight;

    public Edge(Node u, Node v, int weight)
    {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public void setU(Node u)
    {
        this.u = u;
    }

    public void setV(Node v)
    {
        this.v = v;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public Node getU()
    {
        return u;
    }

    public Node getV()
    {
        return v;
    }

    public int getWeight()
    {
        return weight;
    }

    public int compareTo(Edge e)
    {
        return Integer.compare(this.weight, e.getWeight());
    }
}
