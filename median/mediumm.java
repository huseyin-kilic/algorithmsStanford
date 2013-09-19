import java.util.ArrayList;

public class mediumm {
	minheap min;
	maxheap max;

	public mediumm () {
		min = new minheap();
		max = new maxheap();
	}

	public void insert(int e) {
		if (min.size() == 0) {
			if (max.size() == 0)
				max.insert(e);
			else {
				if (e > max.peakmax())
					min.insert(e);
				else {
					min.insert(max.extractMax());
					max.insert(e);
				}
			}
		}
		else if (min.size() == max.size()) {			
			if (e > min.peakmin()) {
				max.insert(min.extractMin());
				min.insert(e);
			}
			else
				max.insert(e);
		}
		else if (min.size() < max.size()) {
			if (e < max.peakmax()) { 
				min.insert(max.extractMax());
				max.insert(e);
			}
			else 
				min.insert(e);
		}
	}
	
	public int size() {
		return min.size()+max.size();
	}

	public int medium() {
		return max.peakmax();
	}

	public void testprint() {
		StdOut.printf("%d (%d) : %d (%d)\n", max.peakmax(), 
					  max.size(), min.peakmin(), min.size());
	}

	public static void main(String [] args) {
		mediumm m = new mediumm();
//		int num = StdIn.readInt();
		int num = 10000;
		long sum = 0;
		for (int i = 0; i < num; i++) {
			m.insert(StdIn.readInt());
			sum = sum + m.medium();
			//StdOut.printf("%d: %d\n", i+1, m.medium());
			sum = sum % 10000;
//			m.testprint();
		}

		StdOut.printf("num is %d\n", m.size());
		
		StdOut.printf("medium is %d\n", m.medium());

		StdOut.printf("sum is %d\n", sum);
		
	}


}
