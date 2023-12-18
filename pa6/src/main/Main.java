import java.util.Scanner;
import java.util.Arrays;
import java.util.*;

// Main.java file is not for submission.
// It is here for you to give means of testing.
// You can use any packages in "this" file but be careful on the submission files.

public class prim_main {
	static List<Edge>[] graph;

	public static void prim(int start, int n) {
		boolean[] visit = new boolean[n + 1];

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));

		int total = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int v = edge.w;
			int cost = edge.cost;

			if(visit[v]) continue;

			visit[v] = true;
			total += cost;

			for(Edge e : graph[v]) {
				if(!visit[e.w]) {
					pq.add(e);
				}
			}
		}
		System.out.println(total);
	}


	public static void main(String[] args) {
		// 그래프 입력, 저장

		// 그래프 선언, 간선 리스트로 표현
		graph = new ArrayList[n + 1];
		for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();

		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[v].add(new Edge(w, cost));
			graph[v].add(new Edge(v, cost));
		}

		// 프림 알고리즘 수행
		prim(1, n);
	}
}
