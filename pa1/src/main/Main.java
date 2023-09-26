import java.util.Scanner;
import java.util.Arrays;

// Main.java file is not for submission.
// It is here for you to give means of testing.
// You can use any packages in "this" file but be careful on the submission files.

public class Main {
	public static void main(String[] args) {

	ICLL<Point> l = new CircularLinkedList<Point>();
	l.insert(new Point(10,-10));
	l.insert(new Point(11,2));
	l.insert(new Point(9,10));
	l.insert(new Point(-4,8));
	l.insert(new Point(-1,-1));
	l.insert(new Point(5,4));
	Polygon p = new Polygon(l);
	System.out.println(p.pointInPolygon(new Point(8,10)));

//		CircularLinkedList<Double> l = new CircularLinkedList<Double>();

//		for (int i = 0; i < 100; i++) {
//			l.insert(1.1);
//			l.rotate(Direction.TO_NEXT);
//			System.out.println(l.getHead());
//			System.out.println(l.size());
//			l.rotate(Direction.TO_PREVIOUS);
//		}
//
//		for (int j = 0; j < 150; j++) {
//			System.out.println(l.delete());
//			System.out.println(l.getHead());
//			System.out.println(l.size());
//			System.out.println(l.find(1.0));
//			System.out.println(l.isEmpty());
//			l.insert(1.0);
//		}

//		System.out.print("Length: ");
//		Sorter sorter = new Sorter();
//		Scanner scanner = new Scanner(System.in);
//
//		System.out.print("Length: ");
//		int length = Integer.parseInt(scanner.next());
//		int[] a = new int[length];
//		for (int i = 0; i < length; i++) {
//			System.out.print("a[" + Integer.toString(i) + "]: ");
//			a[i] = Integer.parseInt(scanner.next());
//		}
//
//		System.out.println("a: " + Arrays.toString(a));
//		int[] asc = sorter.ascending(a);
//		System.out.println("asc: " + Arrays.toString(a));
	}
}
