/**
 * Take any natural number n. If n is even, divide it by 2 to get n / 2. If n is
 * odd, multiply it by 3 and add 1 to obtain 3n + 1. Repeat the process (which
 * has been called "Half Or Triple Plus One", or HOTPO) indefinitely. The
 * conjecture is that no matter what number you start with, you will always
 * eventually reach 1. The property has also been called oneness. --Wikipedia
 * 
 * @author Mike
 *
 */
public class Collatz {
	int num = 95959427;

	public int doWork(int n) {
		if (n == 1) {
			System.out.println("Reached 1!");
			return 1;
		}
		int next;
		if (isOdd(n) == 1) {
			next = 3 * n + 1;
			System.out.println("(odd) " + n + "*3+1=" + next);
			return doWork(next);
		}
		next = n / 2;
		System.out.println("(even) " + n + "/2=" + next);
		return doWork(n / 2);
	}

	public int isOdd(int num) {
		return num & 1;
	}

	public Collatz() {
		doWork(num);
	}

	public static void main(String[] args) {
		new Collatz();
	}
}
