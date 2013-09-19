import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class hash1 {
	
	private HashMap<Long, Boolean> hashing;
	private ArrayList<Long> keys;
	
	
	public hash1() {
		int i = 0;
		keys = new ArrayList<Long>();
		Scanner scan = new Scanner(System.in);
		hashing = new HashMap<Long, Boolean>();
		while (scan.hasNextLong()) {
			//StdOut.println("Here\n");
			long a = scan.nextLong();
			if (!hashing.containsKey(a)) {
				hashing.put(a, true);
				keys.add(a);
			}
			StdOut.printf("# hash is %d\n", hashing.size());
		}
		
	}
	
	public int count() {
		int k = 0;
		for (long sum = -10000; sum <= 10000; sum++) {
			for (int i = 0; i < keys.size(); i++) {
				if (hashing.containsKey(sum - keys.get(i)))
				{
					k++;
					break;
				}
			}
			StdOut.println(sum);
		}
		return k;
	}
	
	public static void main(String [] args) {
		hash1 source = new hash1();
		StdOut.printf("Number is %d\n", source.count());
	}
}