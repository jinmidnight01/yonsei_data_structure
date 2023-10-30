import java.util.Scanner;
import java.util.Arrays;

// Main.java file is not for submission.
// It is here for you to give means of testing.
// You can use any packages in "this" file but be careful on the submission files.

public class Main {
	public static void main(String[] args) {

		Heap<Integer> heap = new Heap<Integer>();
		heap.insert(4);
		System.out.println(heap.min());
		heap.insert(5);
		System.out.println(heap.min());
		heap.insert(9);
		System.out.println(heap.min());
		heap.insert(100);
		System.out.println(heap.min());
		heap.insert(20);
		System.out.println(heap.min());
		heap.insert(0);
		System.out.println(heap.min());
		heap.insert(4);
		System.out.println(heap.min());
		heap.insert(2);
		System.out.println(heap.min());
		heap.insert(19);
		System.out.println(heap.min());
		heap.insert(21);
		System.out.println(heap.min());

		Heap<Integer> heap2 = new Heap<>();
		heap2.insert(-6);
		System.out.println(heap2.min());
		heap2.insert(3);
		System.out.println(heap2.min());
		heap2.insert(15);
		System.out.println(heap2.min());
		heap2.insert(31);
		System.out.println(heap2.min());
		heap2.insert(67);
		System.out.println(heap2.min());
		heap2.insert(5);
		System.out.println(heap2.min());

		heap.merge(heap2);

		System.out.println("Removing min " + heap.getSize() + " " + heap.height);
		while (!heap.isEmpty()) {
			System.out.println(heap.removeMin() + " " + heap.getSize() + " " + heap.height);
		}
		System.out.println(heap.removeMin() + " " + heap.getSize() + " " + heap.height);
	}
}
