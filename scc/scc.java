import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Collections;

public class scc {
	private HashMap<Integer, ArrayList<Integer>> adj, adjr;
	private HashMap<Integer, Boolean> vertices;
	private boolean [] explored;
	private int [] rank;
	public int index;
	private List<Integer> pool;
	
	private int V;
	
	private int counter;
	
	@SuppressWarnings("unchecked")
	public scc(int V) {
		adj = new HashMap<Integer, ArrayList<Integer>>(); 
		adjr = new HashMap<Integer, ArrayList<Integer>>();
		
		explored = new boolean [V];
		rank = new int [V];
		vertices = new HashMap<Integer, Boolean>();
		index = 0;
		this.V = V;
		
		for (int i = 0; i < V; i++) {
			adj.put(i, new ArrayList<Integer>());
			adjr.put(i, new ArrayList<Integer>());
			vertices.put(i, true);
			rank[i] = -1;
		}
	}
	
	public scc() {
		this(875714);
//		this(8);
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextInt()) {
			int from = scan.nextInt() - 1;
			int to = scan.nextInt() - 1;
			adj.get(from).add(to);
			adjr.get(to).add(from);
		}
	} 
	
	public void refresh(){
		for (int i = 0; i < V; i++)
			explored[i] = false;
	}
	
	public void DFS0() {
		refresh();
		index = 0;
		int s = V - 1;
		while (s >= 0 ) {
			if (vertices.get(s) == true) {
				explored[s] = true;
				DFSr(s);
			}
			s--;
		}	
	}
	
	public void DFS1() {
		refresh();
		ArrayList<Integer> sccrank = new ArrayList<Integer>();
		for (int i = index - 1; i >= 0; i--) {
			int current = rank[i];
			if (!explored[current]) {
				counter = 0;
				explored[current] = true;
				DFS(current);
//				StdOut.println(counter);
				sccrank.add(counter);
			}
		}
		Collections.sort(sccrank);
		for(int i = 0; i < 5; i++)
			StdOut.println(sccrank.get(sccrank.size()-i-1));
	}
	
	public void DFSr(int s) {
		//StdOut.printf("at vertex %d\n", s+1);
		for (int i = 0; i < adjr.get(s).size(); i++) {
			int current = adjr.get(s).get(i);
			if (!explored[current]) {
				explored[current] = true;
				DFSr(current);
			}
		}
		rank[index] = s;
		index++;
		vertices.put(s, false);
//		StdOut.printf("pool removed %d\n", s+1);
	}
	
	public void DFS(int s) {
		for (int i = 0; i < adj.get(s).size(); i++) {
			int current = adj.get(s).get(i);
			if (!explored[current]) {
				explored[current] = true;
				DFS(current);
			}
		}
//		StdOut.println(s+1);
		counter++;
	}
	
	
	public static void main(String [] args) {
//		In in = new In(args[0]);
		scc source = new scc();
		StdOut.println("stage 1\n");
		source.DFS0();
//		StdOut.println(source.index);
		source.DFS1();
	}
	
}