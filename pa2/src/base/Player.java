public class Player{
	private static int globalPlayerID = 0;
	private int playerID;
	private Tier myTier;

	public Player(Tier tier){
		playerID = globalPlayerID;
		globalPlayerID++;
		myTier = tier;
	}

	public Tier getTier(){
		return myTier;
	}

	public int getPlayerID(){
		return playerID;
	}

	public boolean equals(Object otherObject){
		if(otherObject == null) return false;
		if(otherObject instanceof Player){
			Player otherPlayer = (Player) otherObject;
			return playerID == otherPlayer.playerID;
		}
		return false;
	}
}
