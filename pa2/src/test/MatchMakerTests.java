import java.time.Duration;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import testrunner.annotation.Score;

public class MatchMakerTests {
	@Test
	@Score(1)
	public void test1() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			IMatchMaker m = new MatchMaker();
			int numPlayers = 10;
			Player[] players = new Player[numPlayers];
			// gold, iron, silver, bronze, silver, silver, gold, silver, silver, silver
			for(int i = 0; i < numPlayers; i++){
				if(i == 0 || i == 6) players[i] = new Player(Tier.GOLD);
				else if(i == 1) players[i] = new Player(Tier.IRON);
				else if(i == 3) players[i] = new Player(Tier.BRONZE);
				else players[i] = new Player(Tier.SILVER);
			}

			for(int i = 0; i < numPlayers - 1; i++){
				assertThat(m.newPlayer(players[i]), is(nullValue()));
			}
			Player[] ret = m.newPlayer(players[numPlayers - 1]);
			assertThat(ret.length, is(6));
			assertThat(ret[0], is(players[0]));
			assertThat(ret[1], is(players[2]));
			assertThat(ret[2], is(players[4]));
			assertThat(ret[3], is(players[5]));
			assertThat(ret[4], is(players[7]));
			assertThat(ret[5], is(players[8]));
		});
	}

	@Test
	@Score(1)
	public void test2() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			MatchMaker m = new MatchMaker();
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
				assertThat(m.newPlayer(players[i]), is(nullValue()));
			}
			Player[] ret = m.newPlayer(players[9]);
			assertThat(ret.length, is(6));
			assertThat(ret[0], is(players[0]));
			assertThat(ret[1], is(players[2]));
			assertThat(ret[2], is(players[4]));
			assertThat(ret[3], is(players[5]));
			assertThat(ret[4], is(players[7]));
			assertThat(ret[5], is(players[8]));

			for(int i = 10; i < 14; i++){
				assertThat(m.newPlayer(players[i]), is(nullValue()));
			}
			Player[] ret2 = m.newPlayer(players[14]);
			assertThat(ret2.length, is(6));
			assertThat(ret2[0], is(players[6]));
			assertThat(ret2[1], is(players[10]));
			assertThat(ret2[2], is(players[11]));
			assertThat(ret2[3], is(players[12]));
			assertThat(ret2[4], is(players[13]));
			assertThat(ret2[5], is(players[14]));

			assertThat(m.ironQueue.getSize(), is(1));
			assertThat(m.bronzeQueue.getSize(), is(1));
			assertThat(m.silverQueue.getSize(), is(1));
			assertThat(m.goldQueue.getSize(), is(0));
			assertThat(m.platinumQueue.getSize(), is(0));
		});
	}
}
