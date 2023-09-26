/*
 * Name: Jinhyo Park
 * Student ID #: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public class CircularLinkedList<T> implements ICLL<T> {
	class Node {
		Node next;
		Node previous;
		// you can further implement your node class here.
		private final T element;

		public Node(T element, Node previous, Node next) {
			this.element = element;
			this.previous = previous;
			this.next = next;
		}
	}

	private Node head;
	// you may declare additional variables here.

	public CircularLinkedList() {
		// implement your constructor here.
		head = null;
	}

	@Override
	public int size() {
		/*
		 * Input:
		 *	- none
		 *
		 * Output:
		 *  - the number of elements in the linked list.
		 */
		if (head == null) {
			return 0;
		}
		else if (head.previous == head && head.next == head) {
			return 1;
		}
		else{
			int count = 1;
			Node position = head;
			while (position.next != head) {
				position = position.next;
				count++;
			}
			return count;
		}
	}

	@Override
	public boolean isEmpty() {
		/*
		 * Input:
		 *	- none
		 *
		 * Output:
		 *  - whether or not the list is empty.
		 */
		if (head == null) {
			return true;
		}
		return false;
	}

	@Override
	public T getHead() {
		/*
		 * Input:
		 *	- none
		 *
		 * Output: 
		 *  - the element stored in the node pointed by the head.
		 */
		if (head == null) {
			return null;
		}
		return head.element;
	}

	@Override
	public void rotate(Direction direction) {
		/*
		 * Input:
		 *	- the rotation orientation
		 *
		 * Output: 
		 *  - none
		 * 
		 * Does:
		 *  - rotates the linked list so that the head points to the Node in that direction.
		 */
		if (size() == 0) {
			return;
		}
		else {
			if (direction == Direction.TO_NEXT) {
				head = head.next;
			}
			else if (direction == Direction.TO_PREVIOUS) {
				head = head.previous;
			}
		}
	}

	@Override
	public void insert(T element) {
		/*
		 * Input:
		 *	- element to be inserted to the list
		 *
		 * Output: 
		 *  - none
		 * 
		 * Does:
		 *  - inserts the given element before the Node pointed by the head.
		 */
		if (head == null) {
			head = new Node(element, null, null);
			head.previous = head;
			head.next = head;
			return;
		}

		Node newNode = new Node(element, head.previous, head);
		head.previous.next = newNode;
		head.previous = newNode;
	}

	@Override
	public T delete() {
		/*
		 * Input:
		 *	- none
		 *
		 * Output:
		 *  - the element pointed to by the head at the start of the method call.
		 * 
		 * Does:
		 *  - deletes the element pointed to by the head and returns it.
		 *  - if the list is empty, return null.
		 * 
		 * Note:
		 *  - if nonempty after deletion,
		 *    the new head should be the next node of the original head.
		 */
		if (head != null) {
			T element = head.element;
			if (head.next == head) {
				head = null;
			}
			else {
				head.previous.next = head.next;
				head.next.previous = head.previous;
				head = head.next;
			}
			return element;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean find(T target) {
		/*
		 * Input:
		 *	- target element to find in the list.
		 *
		 * Output:
		 *  - whether or not the search succeeded.
		 * 
		 * Does:
		 *  - moves the head to the first instance of target in the list if the search succeeded.
		 *  - does not move the head if the search failed.
		 */
		Node position = head;
		T itemAtPosition;
		int count = 1;

		while (count <= size())
		{
			itemAtPosition = position.element;
			if (itemAtPosition.equals(target)) {
				head = position;
				return true;
			}
			position = position.next;
			count++;
		}
		return false;
	}
}
