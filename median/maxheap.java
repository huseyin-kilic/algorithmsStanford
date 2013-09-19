import java.util.ArrayList;

public class maxheap {
	
	private ArrayList<Integer> h;
	private int size;
	
	public maxheap() {
		h = new ArrayList<Integer>();
		size = 0;
		h.add(-1);
	}
	
	public void insert(int e) {
		h.add(e);
		size++;
		int p = size;
		while (p/2 > 0) {
			if (h.get(p/2) < h.get(p))
				swap(p, p/2);
			p /= 2;
		}
	}
	
	public int peakmax() {
		if (size == 0)
			return -1;
		return h.get(1);
	}
	
	public int extractMax() {
		swap(1, size);
		int r = h.remove(size--);
		int p = 1; 
		while (p*2 <= size) {
			if (p*2 == size || h.get(p*2) > h.get(p*2+1)) {
				if (h.get(p) < h.get(p*2)) {
					swap(p, p*2);
					p*=2;
				}
				else
					break;
			}
			else {
				if (h.get(p) < h.get(p*2+1)) {
					swap(p, p*2+1);
					p=p*2+1;
				}
				else
					break;
			}
		}
		return r;

	}
	
	private void swap(int i, int j) {
		int temp = h.get(i);
		h.set(i, h.get(j));
		h.set(j, temp);
	}
	
	public int size() {
		return size;
	}

	public static void main(String [] args) {
		maxheap maxh = new maxheap();
//		int num = StdIn.readInt();
		int num = 3;
		maxh.insert(6);
		maxh.extractMax();
		maxh.insert(2);
		maxh.insert(1);
		maxh.insert(-1);
		maxh.extractMax();
		maxh.insert(0);
//		for (int i = 0; i < num; i++) {
//			maxh.insert(StdIn.readInt());
//		}
		for (int i = 0; i < num; i++) {
			StdOut.println(maxh.extractMax());
		}
	}

}