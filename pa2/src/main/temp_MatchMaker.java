/*
 * Name: Jinhyo Park
 * Student ID: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public class temp_MatchMaker implements IMatchMaker {
	private int index;
	private final Queue<Player> ironQueue;
	private final Queue<Integer> ironIndexQueue;
	private final Queue<Player> bronzeQueue;
	private final Queue<Integer> bronzeIndexQueue;
	private final Queue<Player> silverQueue;
	private final Queue<Integer> silverIndexQueue;
	private final Queue<Player> goldQueue;
	private final Queue<Integer> goldIndexQueue;
	private final Queue<Player> platinumQueue;
	private final Queue<Integer> platinumIndexQueue;
	private final Queue<Player> emeraldQueue;
	private final Queue<Integer> emeraldIndexQueue;
	private final Queue<Player> diamondQueue;
	private final Queue<Integer> diamondIndexQueue;

	public temp_MatchMaker(){
		// constructor
		index = 0;
		ironQueue = new Queue<Player>();
		ironIndexQueue = new Queue<Integer>();
		bronzeQueue = new Queue<Player>();
		bronzeIndexQueue = new Queue<Integer>();
		silverQueue = new Queue<Player>();
		silverIndexQueue = new Queue<Integer>();
		goldQueue = new Queue<Player>();
		goldIndexQueue = new Queue<Integer>();
		platinumQueue = new Queue<Player>();
		platinumIndexQueue = new Queue<Integer>();
		emeraldQueue = new Queue<Player>();
		emeraldIndexQueue = new Queue<Integer>();
		diamondQueue = new Queue<Player>();
		diamondIndexQueue = new Queue<Integer>();
	}

	@Override
	public Player[] newPlayer(Player player) {
		/*
		 * Input:
		 *	- Player player: Player object containing the tier information of a new player.
		 *
		 * Output:
		 *  - an array of 6 Player objects that meet the matchmaking criteria, if any.
		 *  - if none meets the criteria, returns null.
		 *  - the returned Player array should contain the Players exactly in the order of their arrival.
		 *  - refer to the assignment specification for the exact criteria.
		 * 
		 * Does:
		 *  - keeps track of the Players that are waiting for matchmaking and their order of arrival using the queue you implemented.
		 */
		switch (player.getTier()) {
			case IRON: ironQueue.enqueue(player); ironIndexQueue.enqueue(index); break;
			case BRONZE: bronzeQueue.enqueue(player); bronzeIndexQueue.enqueue(index); break;
			case SILVER: silverQueue.enqueue(player); silverIndexQueue.enqueue(index); break;
			case GOLD: goldQueue.enqueue(player); goldIndexQueue.enqueue(index); break;
			case PLATINUM: platinumQueue.enqueue(player); platinumIndexQueue.enqueue(index); break;
			case EMERALD: emeraldQueue.enqueue(player); emeraldIndexQueue.enqueue(index); break;
			case DIAMOND: diamondQueue.enqueue(player); diamondIndexQueue.enqueue(index);break;
			default: break;
		}
		index++;

		if (ironQueue.getSize() == 6) return setResult(ironQueue, new Queue<Player>(), bronzeQueue, ironIndexQueue, new Queue<Integer>(), bronzeIndexQueue);
		if (bronzeQueue.getSize() == 6) return setResult(bronzeQueue, ironQueue, silverQueue, bronzeIndexQueue, ironIndexQueue, silverIndexQueue);
		if (silverQueue.getSize() == 6) return setResult(silverQueue, bronzeQueue, goldQueue, silverIndexQueue, bronzeIndexQueue, goldIndexQueue);
		if (goldQueue.getSize() == 6) return setResult(goldQueue, silverQueue, platinumQueue, goldIndexQueue, silverIndexQueue, platinumIndexQueue);
		if (platinumQueue.getSize() == 6) return setResult(platinumQueue, goldQueue, emeraldQueue, platinumIndexQueue, goldIndexQueue, emeraldIndexQueue);
		if (emeraldQueue.getSize() == 6) return setResult(emeraldQueue, platinumQueue, diamondQueue, emeraldIndexQueue, platinumIndexQueue, diamondIndexQueue);
		if (diamondQueue.getSize() == 6) return setResult(diamondQueue, emeraldQueue, new Queue<Player>(), diamondIndexQueue, emeraldIndexQueue, new Queue<Integer>());
		return null;
	}

	private Player[] setResult(Queue<Player> queue, Queue<Player> leftQueue, Queue<Player> rightQueue, Queue<Integer> indexQueue, Queue<Integer> leftIndexQueue, Queue<Integer> rightIndexQueue) {
		boolean left = leftQueue.getSize() > 0 && leftIndexQueue.peek() < indexQueue.peek();
		boolean right = rightQueue.getSize() > 0 && rightIndexQueue.peek() < indexQueue.peek();
		Player[] result = new Player[6];

		if (left && right) {
			if (leftIndexQueue.peek() < rightIndexQueue.peek()) {
				result[0] = leftQueue.dequeue();
				result[1] = rightQueue.dequeue();
			} else {
				result[0] = rightQueue.dequeue();
				result[1] = leftQueue.dequeue();
			}
			leftIndexQueue.dequeue();
			rightIndexQueue.dequeue();
			for (int i=2; i<6; i++) {
				result[i] = queue.dequeue();
				indexQueue.dequeue();
			}
		} else if (left) {
			result[0] = leftQueue.dequeue();
			leftIndexQueue.dequeue();
			for (int i=1; i<6; i++) {
				result[i] = queue.dequeue();
				indexQueue.dequeue();
			}
		} else if (right) {
			result[0] = rightQueue.dequeue();
			rightIndexQueue.dequeue();
			for (int i=1; i<6; i++) {
				result[i] = queue.dequeue();
				indexQueue.dequeue();
			}
		} else {
			for (int i = 0; i < 6; i++) {
				result[i] = queue.dequeue();
				indexQueue.dequeue();
			}
		}

		return result;
	}
}
