/*
 * Name: Jinhyo Park
 * Student ID #: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public class Sorter implements ISorter {
	public Sorter() { ; }

	@Override
	public int[] ascending(int[] a) {
		/*
		 * Input:
		 *	- an integer array A
		 *
		 * Output: a sorted array A in *ascending* order.
		 */
		for (int i = 0; i < a.length - 1; i++) {
			// find the lowest element in the unsorted part
			int lowest = i;
			for (int j = i + 1; j < a.length; j++) {
				// update lowest
				if (a[j] < a[lowest]) {
					lowest = j;
				}
			}
			// swap the lowest element with the target element
			int temp = a[lowest];
			a[lowest] = a[i];
			a[i] = temp;
		}

		return a;
	}

	@Override
	public int[] descending(int[] a) {
		/*
		 * Input:
		 *	- an integer array A
		 *
		 * Output: a sorted array A in *descending* order.
		 */
		for (int i = 0; i < a.length - 1; i++) {
			// find the highest element in the unsorted part
			int highest = i;
			for (int j = i + 1; j < a.length; j++) {
				// update highest
				if (a[j] > a[highest]) {
					highest = j;
				}
			}
			// swap the highest element with the target element
			int temp = a[highest];
			a[highest] = a[i];
			a[i] = temp;
		}

		return a;
	}
}
