public class edge {
	private int w;
	private int v;
	private int weight;
	
	public edge() {
		w = 0;
		v = 0;
		weight = 0;
	}
	
	public edge(int v, int w) {
		this.v = v;
		this.w = w;
		this.weight = 0;
	}
	
	public edge(int v, int w, int weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;		
	}
	
	@Override
	public boolean equals (Object other) {
		if (!(other instanceof edge))
			return false;
		edge otheredge = (edge) other;
		if(otheredge.V() == v && otheredge.W() == w && otheredge.weight() == weight)
			return true;
		else
			return false;
	}
	
	public int W() {return w;}
	public int V() {return v;}
	public int weight() {return weight;}
}