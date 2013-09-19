public class inversionCount {
	private int [] a;
	private int len;

	public inversionCount(String [] args) {
		a = new In(args[0]).readAllInts();
		len = a.length;
	}
	public long countInversion() {
		int [] aux = new int [len];
		return sortCount(a, aux, 0, a.length-1);
	}
	private long sortCount(int [] a, int [] aux, int start, int end) {
		if (start >= end)
			return 0;
		int mid = (start+end)/2;
		long res0 = sortCount(a, aux, start, mid);
		long res1 = sortCount(a, aux, mid+1, end);
		return res0+res1+merge(a, aux, start, mid, end);
	}
	
	private long merge(int [] a, int [] aux, int start, int mid, int end) {
		int i = start;
		int j = mid+1;
		int cur = start;
		long count = 0;
		while (cur <= end) {
			if (i > mid)
				aux[cur++] = a[j++];
			else if (j > end)
				aux[cur++] = a[i++];
			else if (a[i] <= a[j])
				aux[cur++] = a[i++];
	  		else if (a[i] > a[j]) {
				aux[cur++] = a[j++];
				count += mid-i+1;
			}
		}
		while (start <= end) {
			a[start] = aux[start];
			start++;
		}
		return count;
	}
	
	public static void main (String [] args) {
		inversionCount count = new inversionCount(args);
		long ans = count.countInversion();
		StdOut.println(ans);
	}
}
