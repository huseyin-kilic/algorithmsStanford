import java.util.ArrayList;
import java.util.HashMap;

public class myheap {
	private int size;
	private int minver;
	private ArrayList<Integer> hp; 
	private HashMap<Integer,Integer> ver;
	private HashMap<Integer,Integer> loc;

	public myheap(){
		size = 0;
		hp = new ArrayList<Integer>();
		hp.add(0, -1);
		ver = new HashMap<Integer,Integer>();
		loc = new HashMap<Integer,Integer>();
	}

	public void insert(int a, int v) {
		if (loc.containsKey(v)) {
			//StdOut.println("HIT\n");
			if(a < hp.get(loc.get(v))) {
				hp.set(loc.get(v), a);
				for (int i = loc.get(v); i > 1; i = i/2)
					if(hp.get(i) < hp.get(i/2))
						swap(hp, i, i/2);
			}
		}
		else
		{
			hp.add(++size, a);
			stable(v,size);
			
			for (int i = size; i > 1; i = i/2) {
				if(hp.get(i) < hp.get(i/2))
					swap(hp, i, i/2);
			}
		}
	}
	
	
	
	public int extractMin() {
		swap(hp, 1, size);
		int re = hp.remove(size);
		loc.remove(ver.get(size));
		minver = ver.remove(size);
		
		
		size--;
		int i = 1;
		while(i < size) {
			if (i*2 > size)
				break;
			if (i*2 == size) {
				if (hp.get(i) > hp.get(i*2))
					swap(hp, i, i*2);
				break;
			}
			int min;
			if (hp.get(i*2) < hp.get(i*2+1))
				min = i*2;
			else
				min = i*2+1;
			if (hp.get(i) > hp.get(min)) {
				swap(hp, i, min);
				i = min;
			}
			else
				break;
		}
		return re;
	}
	
	public void swap(ArrayList<Integer> a, int i, int j){
		int temp = a.get(i);
		a.set(i, a.get(j));
		a.set(j, temp);
		

		loc.put(ver.get(i), j);
		loc.put(ver.get(j), i);
		
		
		temp = ver.get(i);
		this.ver.put(i, this.ver.get(j));
		this.ver.put(j, temp);
	}
	
	private void stable(int ver, int loc) {
		this.ver.put(loc, ver);
		this.loc.put(ver, loc);
		
	}
	
	public int size() {return size;}
	
	public void testprint() {
//		for (int i = 0; i < 7; i++)
//			StdOut.printf("%d -> ", ver.get(i+1));
//		StdOut.println();
//		for (int i = 0; i < 7; i++)
//			StdOut.printf("%d -> ", loc.get(i+1));
//		StdOut.println();
//		
	}
	
	public int minver() {return minver;}
	
	public static void main(String args []) {
		//In in = new In(args[0]);
		myheap h = new myheap();
		int size = StdIn.readInt();
		//StdOut.println(size);
		for (int i = 0; i < size; i++)
			h.insert(StdIn.readInt(),i+1);
		h.insert(1004, 7);
		h.insert(-9999, 4);
		for (int i = 0; i < size; i++) {
			h.testprint();
			//StdOut.printf("%d: %d\n",i+1,h.extractMin());
		}
	}

}