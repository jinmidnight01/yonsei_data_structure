import java.util.Scanner;
import java.util.Arrays;

// Main.java file is not for submission.
// It is here for you to give means of testing.
// You can use any packages in "this" file but be careful on the submission files.

public class Main {
	public static void main(String[] args) {

		Sorter sorter = new Sorter();
		Scanner scanner = new Scanner(System.in);

		System.out.print("Length: ");
		int length = Integer.parseInt(scanner.next());
		int[] a = new int[length];
		for (int i = 0; i < length; i++) {
			System.out.print("a[" + Integer.toString(i) + "]: ");
			a[i] = Integer.parseInt(scanner.next());
		}

		System.out.println("a: " + Arrays.toString(a));
		int[] asc = sorter.descending(a);
		System.out.println("asc: " + Arrays.toString(a));
	}
}
