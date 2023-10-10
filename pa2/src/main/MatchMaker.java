/*
 * Name: Jinhyo Park
 * Student ID: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public class MatchMaker implements IMatchMaker {
	private final Queue<Player> ironQueue;
	private final Queue<Player> bronzeQueue;
	private final Queue<Player> silverQueue;
	private final Queue<Player> goldQueue;
	private final Queue<Player> platinumQueue;
	private final Queue<Player> emeraldQueue;
	private final Queue<Player> diamondQueue;

	public MatchMaker(){
		// constructor
		ironQueue = new Queue<Player>();
		bronzeQueue = new Queue<Player>();
		silverQueue = new Queue<Player>();
		goldQueue = new Queue<Player>();
		platinumQueue = new Queue<Player>();
		emeraldQueue = new Queue<Player>();
		diamondQueue = new Queue<Player>();
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
			case IRON:
				ironQueue.enqueue(player);
				break;
			case BRONZE:
				bronzeQueue.enqueue(player);
				break;
			case SILVER:
				silverQueue.enqueue(player);
				break;
			case GOLD:
				goldQueue.enqueue(player);
				break;
			case PLATINUM:
				platinumQueue.enqueue(player);
				break;
			case EMERALD:
				emeraldQueue.enqueue(player);
				break;
			case DIAMOND:
				diamondQueue.enqueue(player);
				break;
			default:
				break;
		}

		if (ironQueue.getSize() == 6) return setResult(ironQueue, new Queue<Player>(), bronzeQueue);
		if (bronzeQueue.getSize() == 6) return setResult(bronzeQueue, ironQueue, silverQueue);
		if (silverQueue.getSize() == 6) return setResult(silverQueue, bronzeQueue, goldQueue);
		if (goldQueue.getSize() == 6) return setResult(goldQueue, silverQueue, platinumQueue);
		if (platinumQueue.getSize() == 6) return setResult(platinumQueue, goldQueue, emeraldQueue);
		if (emeraldQueue.getSize() == 6) return setResult(emeraldQueue, platinumQueue, diamondQueue);
		if (diamondQueue.getSize() == 6) return setResult(diamondQueue, emeraldQueue, new Queue<Player>());
		return null;
	}

	private Player[] setResult(Queue<Player> queue, Queue<Player> leftQueue, Queue<Player> rightQueue) {
		boolean left = leftQueue.getSize() > 0 && leftQueue.peek().getPlayerID() < queue.peek().getPlayerID();
		boolean right = rightQueue.getSize() > 0 && rightQueue.peek().getPlayerID() < queue.peek().getPlayerID();
		Player[] result = new Player[6];

		if (left && right) {
			if (leftQueue.peek().getPlayerID() < rightQueue.peek().getPlayerID()) {
				result[0] = leftQueue.dequeue();
				result[1] = rightQueue.dequeue();
			} else {
				result[0] = rightQueue.dequeue();
				result[1] = leftQueue.dequeue();
			}
			for (int i=2; i<6; i++) {
				result[i] = queue.dequeue();
			}
		} else if (left) {
			result[0] = leftQueue.dequeue();
			for (int i=1; i<6; i++) {
				result[i] = queue.dequeue();
			}
		} else if (right) {
			result[0] = rightQueue.dequeue();
			for (int i=1; i<6; i++) {
				result[i] = queue.dequeue();
			}
		} else {
			for (int i = 0; i < 6; i++) {
				result[i] = queue.dequeue();
			}
		}

		return result;
	}
}
