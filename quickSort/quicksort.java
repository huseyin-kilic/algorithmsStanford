import java.lang.Math;
public class quicksort {
	public static long sort(double [] a) {
		return sort(a, 0, a.length - 1);
	}
	
	private static long sort(double [] a, int lo, int hi) {
		if (lo >= hi)
			return 0;
		int mid = (hi + lo) / 2;
		int head;
		if ((a[lo] - a[mid])*(a[mid] - a[hi]) > 0)
			head = mid;
		else if ((a[mid] - a[lo])*(a[lo] - a[hi]) > 0)
			head = lo;
		else
			head = hi;
		swap(a, lo, head);
		int j = lo+1;
		for (int i = lo+1; i <= hi; i++)
			if (a[i] < a[lo])
				swap(a, i, j++);			
		swap(a, lo, j-1);
		long count = hi - lo;
		return  sort(a, j, hi) + sort(a, lo, j-2) + count;
	}
	
	private static void swap (double [] a, int x, int y) {
		double temp;
		temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	public static boolean isSorted(double [] a) {
		int N = a.length;
		for (int i = 1; i < N; i++) 
			if (a[i] < a[i - 1])
				return false;
		return true;
	}
	
	public static void main(String [] args) {
		int N = 10000; //StdIn.readInt();
		double [] sample = new double[N]; 
		for (int i = 0; i < N; i++) {
			sample[i] = StdIn.readDouble();
		}
		StdOut.println(sort(sample));
		assert isSorted(sample);
	}
}
