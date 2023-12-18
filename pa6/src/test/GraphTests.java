
import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class GraphTests {
    @Test
    @Score(1)
    public void sampleTest1() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph g = new Graph();
            Node[] n = new Node[5];
            assertThat(g.getNumNodes(), is(0));
            for(int i = 0; i < 5; i++)
            {
                n[i] = g.addNode();
                assertThat(g.getNumNodes(), is(i+1));
            }
            g.addEdge(n[0], n[1], 3);
            assertThat(g.getNumEdges(), is(1));
            g.addEdge(n[1], n[2], 5);
            assertThat(g.getNumEdges(), is(2));
            assertThat(g.minSpanningTreeWeight(), is(8));
            g.addEdge(n[3], n[4], 100);
            assertThat(g.getNumEdges(), is(3));

            assertThat(g.shortestPathLength(n[0], n[2]), is(8));
            assertThat(g.shortestPathLength(n[3], n[4]), is(100));
            assertThat(g.areUVConnected(n[0], n[2]), is(true));
            assertThat(g.numConnnectedComponents(), is(2));
            assertThat(g.dijkstra(n[0]).size(), is(5));
            assertThat(g.shortestPathLength(n[0], n[4]), is(Integer.MAX_VALUE));
            assertThat(g.isConnected(), is(false));
        });
    }

    @Test
    @Score(1)
    public void sampleTest2() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Graph g = new Graph();
            Node[] n = new Node[5];
            for(int i = 0; i < 5; i++)
            {
                n[i] = g.addNode();
            }
            assertThat(g.getNumEdges(), is(0));
            g.addEdge(n[0], n[1], 10);
            assertThat(g.getNumEdges(), is(1));
            g.addEdge(n[0], n[2], 3);
            assertThat(g.getNumEdges(), is(2));
            g.addEdge(n[0], n[3], 8);
            assertThat(g.getNumEdges(), is(3));
            g.addEdge(n[1], n[2], 13);
            assertThat(g.getNumEdges(), is(4));
            g.addEdge(n[1], n[4], 8);
            assertThat(g.getNumEdges(), is(5));
            g.addEdge(n[4], n[3], 9);
            assertThat(g.getNumEdges(), is(6));

            assertThat(g.shortestPathLength(n[0], n[4]), is(17));
            assertThat(g.shortestPathLength(n[2], n[4]), is(20));
            assertThat(g.areUVConnected(n[0], n[4]), is(true));
            assertThat(g.minSpanningTreeWeight(), is(28));
            assertThat(g.numConnnectedComponents(), is(1));
            assertThat(g.isConnected(), is(true));
            assertThat(g.addEdge(n[0], n[1], 1), is(nullValue()));
        });
    }

    @Test
    @Score(1)
    public void sampleTest3() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Graph g = new Graph();
            Node[] n = new Node[9];
            for(int i = 0; i < 7; i++)
            {
                n[i] = g.addNode();
                assertThat(g.getNumNodes(), is(i+1));
            }
            assertThat(g.getNumEdges(), is(0));
            g.addEdge(n[0], n[1], 7);
            assertThat(g.getNumEdges(), is(1));
            g.addEdge(n[0], n[4], 3);
            assertThat(g.getNumEdges(), is(2));
            g.addEdge(n[0], n[5], 10);
            assertThat(g.getNumEdges(), is(3));
            g.addEdge(n[1], n[2], 4);
            assertThat(g.getNumEdges(), is(4));
            g.addEdge(n[1], n[3], 10);
            assertThat(g.getNumEdges(), is(5));
            g.addEdge(n[1], n[4], 2);
            assertThat(g.getNumEdges(), is(6));
            g.addEdge(n[1], n[5], 6);
            assertThat(g.getNumEdges(), is(7));
            g.addEdge(n[2], n[3], 2);
            assertThat(g.getNumEdges(), is(8));
            g.addEdge(n[3], n[5], 9);
            assertThat(g.getNumEdges(), is(9));
            g.addEdge(n[3], n[6], 4);
            assertThat(g.getNumEdges(), is(10));
            g.addEdge(n[4], n[6], 5);
            assertThat(g.getNumEdges(), is(11));

            assertThat(g.minSpanningTree().size(), is(6));
            assertThat(g.minSpanningTreeWeight(), is(21));
            assertThat(g.areUVConnected(n[0], n[6]), is(true));
            assertThat(g.isConnected(), is(true));
            assertThat(g.numConnnectedComponents(), is(1));
            assertThat(g.dijkstra(n[7]), is(nullValue()));
            assertThat(g.shortestPathLength(n[0], n[3]), is(11));
            assertThat(g.shortestPathLength(n[5], n[6]), is(13));
            assertThat(g.shortestPathLength(n[0], n[7]), is(Integer.MAX_VALUE));
            n[7] = g.addNode();
            assertThat(g.areUVConnected(n[0], n[4]), is(true));
            assertThat(g.areUVConnected(n[0], n[7]), is(false));
            assertThat(g.isConnected(), is(false));
            assertThat(g.dijkstra(n[0]).size(), is(8));
            assertThat(g.shortestPathLength(n[0], n[3]), is(11));
            assertThat(g.shortestPathLength(n[5], n[6]), is(13));
            assertThat(g.shortestPathLength(n[0], n[7]), is(Integer.MAX_VALUE));
            assertThat(g.numConnnectedComponents(), is(2));
            n[8] = g.addNode();
            assertThat(g.numConnnectedComponents(), is(3));
            g.addEdge(n[0], n[7], 1);
            assertThat(g.shortestPathLength(n[4], n[7]), is(4));
            assertThat(g.numConnnectedComponents(), is(2));
            assertThat(g.addEdge(n[0], n[1], 1), is(nullValue()));
        });
    }

    @Test
    @Score(1)
    public void sampleTest4() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            IGraph g = new Graph();
            Node[] n = new Node[6];
            assertThat(g.getNumNodes(), is(0));
            for(int i = 0; i < 6; i++)
            {
                n[i] = g.addNode();
                assertThat(g.getNumNodes(), is(i+1));
            }
            g.addEdge(n[0], n[1], 3);
            assertThat(g.getNumEdges(), is(1));
            g.addEdge(n[1], n[2], 5);
            assertThat(g.getNumEdges(), is(2));
            assertThat(g.minSpanningTreeWeight(), is(8));
            g.addEdge(n[3], n[4], 4);
            assertThat(g.getNumEdges(), is(3));
            g.addEdge(n[5], n[4], 4);
            g.addEdge(n[0], n[5], 0);

            assertThat(g.shortestPathLength(n[0], n[2]), is(8));
            assertThat(g.shortestPathLength(n[3], n[5]), is(8));
            assertThat(g.areUVConnected(n[0], n[2]), is(true));
            assertThat(g.numConnnectedComponents(), is(1));
            assertThat(g.dijkstra(n[0]).size(), is(6));
            assertThat(g.shortestPathLength(n[0], n[5]), is(0));
            assertThat(g.isConnected(), is(true));
            assertThat(g.minSpanningTreeWeight(), is(16));
        });
    }

}
