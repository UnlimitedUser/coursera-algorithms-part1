import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rand = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			rand.enqueue(s);
		}
		while (k-- > 0) {
			StdOut.println(rand.dequeue());
		}
	}

}