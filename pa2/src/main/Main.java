import java.util.Scanner;
import java.util.Arrays;

// Main.java file is not for submission.
// It is here for you to give means of testing.
// You can use any packages in "this" file but be careful on the submission files.

public class Main {
	public static void main(String[] args) {
		// implement your main function here
		IMatchMaker m = new temp2_MatchMaker();
		int numPlayers = 15;
		Player[] players = new Player[numPlayers];

		// gold, iron, silver, bronze, silver, silver, gold, silver, silver, silver, gold, gold, gold, gold, gold
		for(int i = 0; i < numPlayers; i++){
			if(i == 0 || i == 6 ||i==10||i==11||i==12||i==13||i==14) players[i] = new Player(Tier.GOLD);
			else if(i == 1) players[i] = new Player(Tier.IRON);
			else if(i == 3) players[i] = new Player(Tier.BRONZE);
			else players[i] = new Player(Tier.SILVER);
		}

		for(int i = 0; i < 9; i++){
			m.newPlayer(players[i]);
		}
		Player[] ret = m.newPlayer(players[9]);

		for(int i = 10; i < 14; i++){
			m.newPlayer(players[i]);
		}

		Player[] ret2 = m.newPlayer(players[14]);
		for(int i = 0; i < ret2.length; i++){
			System.out.print(ret2[i].getPlayerID() + " ");
		}
//		IPostfixCalculator p = new PostfixCalculator();
//		System.out.println(p.evaluate("5 10 4 x + 9 + 5 11 x -"));
	}
}
