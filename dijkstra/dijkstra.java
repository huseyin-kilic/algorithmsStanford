import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

public class dijkstra {
	public HashMap<Integer, ArrayList<edge>> adj;
	public ArrayList<Integer> vertices;
	private myheap h = new myheap();
	private boolean [] explored; 
	
	public dijkstra() {
		adj = new HashMap<Integer, ArrayList<edge>>();
		vertices = new ArrayList<Integer>();
		vertices.add(0, -1);
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()){
			
			int v = scan.nextInt();
			adj.put(v, new ArrayList<edge>());
			while(!scan.hasNext("")){
				scan.useDelimiter("[,\\s*]");
				int w = scan.nextInt();
				StdOut.printf("w= %d\n", w);
				int weight = scan.nextInt();
				StdOut.printf("weight= %d\n", weight);
				adj.get(v).add(new edge(v, w, weight));			
			}
			while (scan.hasNext(""))
				scan.next();
			StdOut.println(v);
			vertices.add(v, Integer.MAX_VALUE);
		}
		
		vertices.set(1, 0);
		
		int start = 1;
		
		explored = new boolean [adj.size()];
		for (int i=0; i<explored.length; i++)
			explored[i] = false;
		explored[0] = true;
		
		int num = 1;
		int minver = 1;
		int weight = 0;
		for (int i = 0; i < adj.get(start).size(); i++) {
			h.insert(adj.get(start).get(i).weight(), adj.get(start).get(i).W());
		}
		while (num < adj.size()) {	
			weight = h.extractMin();
			minver = h.minver();
			vertices.set(minver, weight);
			start = minver;
			explored[minver - 1] = true;
			StdOut.printf("%d-> ", minver);
			num++;
			for (int i = 0; i < adj.get(start).size(); i++) {
				if (!explored[adj.get(start).get(i).W() - 1])
					h.insert(adj.get(start).get(i).weight() + weight, adj.get(start).get(i).W());
			}
		}
		StdOut.printf("%d\n", num);
		
		
		
	}
	
	public static void main(String [] args){
		dijkstra source = new dijkstra();
		int [] a = {7,37,59,82,99,115,133,165,188,197};
		//int [] a = {1,2,3,4};
		
		StdOut.println("Results:");

		for (int i = 0; i< a.length; i++)
		{
			StdOut.printf("%d,",source.vertices.get(a[i]));
		}
		
		
	}
}