import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class mincut {
	private int V;	
	private int E;
	private LinkedList<Integer> [] adj;
	private List<edge> edges;
	private List<Integer> vertices;
	
	@SuppressWarnings("unchecked")
	public mincut(int V) {
		if (V < 0) throw new IllegalArgumentException(" negative vertices !");
		this.V = V;	
		this.E = 0;
		adj = (LinkedList<Integer>[])new LinkedList[V];
		vertices = new ArrayList<Integer>();
		this.edges = new ArrayList<edge>();
		for (int v=0; v < V; v++)
			adj[v] = new LinkedList<Integer>();
	}
	
	public mincut(In in) {
		this(200);
		while(!in.isEmpty()) {
			String a = in.readLine();
			String [] line = a.split("\t");
			int v = Integer.parseInt(line[0]);
			vertices.add(v - 1);
			for (int i = 1; i < line.length; i++) {
				addEdge(v - 1, Integer.parseInt(line[i]) - 1);
			}		
		}
		assert edgecheck();
	}
	
	public int V() {return V;}
	public int E() {return E;}
	
	private boolean edgecheck() {
		StdOut.print("enable assertion");
		return edges.size() == E;
	}
	
	public void addEdge(int v, int w) {
		if(v < 0 || v >= V || w < 0 || w >= V)
			throw new IndexOutOfBoundsException();
		adj[v].add(w);
		if (v < w) {
			edges.add(new edge(v, w));
			E++;
		}
	}
	
	public int solvemincut() {
		
		if (vertices.size() == 2)
			return edges.size();
		
		if (vertices.size() < 2)
			return -1;
		
		Random gen = new Random();
		while (vertices.size() > 2) {
			int sel = gen.nextInt(edges.size());
			int v = edges.get(sel).V();
			int w = edges.get(sel).W();
			
			while (edges.contains(new edge(v, w)))
			{
				edges.remove(new edge(v, w));
				//StdOut.printf("remove (%d, %d)\n", v, w);
			}
			while (adj[v].contains(w))
				adj[v].remove(new Integer(w));
			
			for(int i = 0; i < adj[w].size(); i++) {
				int ww = adj[w].get(i);
				if (ww != v) 
				{
					
					if (true)//(!adj[v].contains(ww)) 
					{
						adj[v].add(ww);
						adj[ww].add(v);	
					}
					if (v < ww)
						edges.add(new edge(v, ww));
					else
						edges.add(new edge(ww, v));
					adj[ww].remove(new Integer(w));
					if (w < ww)
					{
						edges.remove(new edge(w, ww));
					}
					else
					{
						edges.remove(new edge(ww, w));
					}
				}
			}
			
			adj[w].clear();
			
			vertices.remove(new Integer(w));
			
		}
		return edges.size();
	}
	
	public static void main(String [] args) {
//		int min = Integer.MAX_VALUE;
//		int N = 10000;
//		mincut [] G = new mincut[N];
//		for(int i = 0; i < N; i++) 
//		{
//			In in = new In(args[0]);
//			G[i]= new mincut(in);
//			int res = G[i].solvemincut();
//			min = res < min ? res: min;
//			StdOut.printf("%d %d\n", i, min);
//		}
		In in = new In(args[0]);
		mincut G = new mincut(in);
		StdOut.println(G.solvemincut());
		
	}
}
