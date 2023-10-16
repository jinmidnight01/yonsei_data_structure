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
			IMatchMaker m = new temp2_MatchMaker();
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

//			assertThat(m.ironQueue.getSize(), is(1));
//			assertThat(m.bronzeQueue.getSize(), is(1));
//			assertThat(m.silverQueue.getSize(), is(1));
//			assertThat(m.goldQueue.getSize(), is(0));
//			assertThat(m.platinumQueue.getSize(), is(0));
		});
	}

	@Test
	@Score(1)
	public void test3() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			IMatchMaker m = new MatchMaker();
			int numPlayers = 17;
			Player[] players = new Player[numPlayers];
			// diamond, platinum, emerald, bronze, iron, silver, iron, platinum, gold, iron, gold, diamond, iron, bronze, bronze, iron, iron
			for(int i = 0; i < numPlayers; i++){
				switch(i) {
					case 0: players[i] = new Player(Tier.DIAMOND); break;
					case 1: players[i] = new Player(Tier.PLATINUM); break;
					case 2: players[i] = new Player(Tier.EMERALD); break;
					case 3: players[i] = new Player(Tier.BRONZE); break;
					case 4: players[i] = new Player(Tier.IRON); break;
					case 5: players[i] = new Player(Tier.SILVER); break;
					case 6: players[i] = new Player(Tier.IRON); break;
					case 7: players[i] = new Player(Tier.PLATINUM); break;
					case 8: players[i] = new Player(Tier.GOLD); break;
					case 9: players[i] = new Player(Tier.IRON); break;
					case 10: players[i] = new Player(Tier.GOLD); break;
					case 11: players[i] = new Player(Tier.DIAMOND); break;
					case 12: players[i] = new Player(Tier.IRON); break;
					case 13: players[i] = new Player(Tier.BRONZE); break;
					case 14: players[i] = new Player(Tier.BRONZE); break;
					case 15: players[i] = new Player(Tier.IRON); break;
					case 16: players[i] = new Player(Tier.IRON); break;
				}
			}

			for(int i = 0; i < 16; i++){
				assertThat(m.newPlayer(players[i]), is(nullValue()));
			}
			Player[] ret = m.newPlayer(players[9]);
			assertThat(ret.length, is(6));
			assertThat(ret[0], is(players[3]));
			assertThat(ret[1], is(players[4]));
			assertThat(ret[2], is(players[6]));
			assertThat(ret[3], is(players[9]));
			assertThat(ret[4], is(players[12]));
			assertThat(ret[5], is(players[15]));
		});
	}

	@Test
	@Score(1)
	public void test4() {
		assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
			IMatchMaker m = new temp_MatchMaker();
			int numPlayers = 26;
			Player[] players = new Player[numPlayers];
			// silver, platinum, emerald, bronze, iron, silver, iron, platinum, gold, iron, gold, diamond, gold, bronze, bronze, iron, iron, gold, gold, diamond, gold
			for(int i = 0; i < numPlayers; i++){
				switch(i) {
					case 0: players[i] = new Player(Tier.SILVER); break;
					case 1: players[i] = new Player(Tier.PLATINUM); break;
					case 2: players[i] = new Player(Tier.EMERALD); break;
					case 3: players[i] = new Player(Tier.BRONZE); break;
					case 4: players[i] = new Player(Tier.IRON); break;
					case 5: players[i] = new Player(Tier.SILVER); break;
					case 6: players[i] = new Player(Tier.IRON); break;
					case 7: players[i] = new Player(Tier.PLATINUM); break;
					case 8: players[i] = new Player(Tier.GOLD); break;
					case 9: players[i] = new Player(Tier.IRON); break;
					case 10: players[i] = new Player(Tier.GOLD); break;
					case 11: players[i] = new Player(Tier.DIAMOND); break;
					case 12: players[i] = new Player(Tier.GOLD); break;
					case 13: players[i] = new Player(Tier.BRONZE); break;
					case 14: players[i] = new Player(Tier.BRONZE); break;
					case 15: players[i] = new Player(Tier.IRON); break;
					case 16: players[i] = new Player(Tier.IRON); break;
					case 17: players[i] = new Player(Tier.GOLD); break;
					case 18: players[i] = new Player(Tier.GOLD); break;
					case 19: players[i] = new Player(Tier.DIAMOND); break;
					case 20: players[i] = new Player(Tier.GOLD); break;
					case 21: players[i] = new Player(Tier.IRON); break;
					case 22: players[i] = new Player(Tier.DIAMOND); break;
					case 23: players[i] = new Player(Tier.DIAMOND); break;
					case 24: players[i] = new Player(Tier.DIAMOND); break;
					case 25: players[i] = new Player(Tier.DIAMOND); break;
				}
			}

			for(int i = 0; i < 20; i++){
				// silver, platinum, emerald, bronze, iron, silver, iron, platinum, gold, iron, gold, diamond, gold, bronze, bronze, iron, iron, gold, gold, diamond, gold, iron, diamond, diamond, diamond, diamond
				// emerald, silver, platinum, gold, gold, silver, platinum, bronze, iron, gold, diamond, bronze, iron, gold, gold, diamond, iron, iron, iron, bronze, gold, iron, diamond, diamond, diamond, diamond
				switch(i) {
					case 0: assertThat(m.newPlayer(players[2]), is(nullValue()));; break;
					case 1: assertThat(m.newPlayer(players[5]), is(nullValue())); break;
					case 2: assertThat(m.newPlayer(players[1]), is(nullValue())); break;
					case 3: assertThat(m.newPlayer(players[18]), is(nullValue())); break;
					case 4: assertThat(m.newPlayer(players[20]), is(nullValue())); break;
					case 5: assertThat(m.newPlayer(players[0]), is(nullValue())); break;
					case 6: assertThat(m.newPlayer(players[7]), is(nullValue())); break;
					case 7: assertThat(m.newPlayer(players[13]), is(nullValue())); break;
					case 8: assertThat(m.newPlayer(players[4]), is(nullValue())); break;
					case 9: assertThat(m.newPlayer(players[17]), is(nullValue())); break;
					case 10: assertThat(m.newPlayer(players[11]), is(nullValue())); break;
					case 11: assertThat(m.newPlayer(players[3]), is(nullValue())); break;
					case 12: assertThat(m.newPlayer(players[9]), is(nullValue())); break;
					case 13: assertThat(m.newPlayer(players[10]), is(nullValue())); break;
					case 14: assertThat(m.newPlayer(players[12]), is(nullValue())); break;
					case 15: assertThat(m.newPlayer(players[19]), is(nullValue())); break;
					case 16: assertThat(m.newPlayer(players[6]), is(nullValue())); break;
					case 17: assertThat(m.newPlayer(players[16]), is(nullValue())); break;
					case 18: assertThat(m.newPlayer(players[15]), is(nullValue())); break;
					case 19: assertThat(m.newPlayer(players[14]), is(nullValue())); break;
				};
			}
			Player[] ret = m.newPlayer(players[8]);
			assertThat(ret.length, is(6));
			assertThat(ret[0], is(players[5]));
			assertThat(ret[0].getTier(), is(Tier.SILVER));
			assertThat(ret[1], is(players[1]));
			assertThat(ret[1].getTier(), is(Tier.PLATINUM));
			assertThat(ret[2], is(players[18]));
			assertThat(ret[2].getTier(), is(Tier.GOLD));
			assertThat(ret[3], is(players[20]));
			assertThat(ret[3].getTier(), is(Tier.GOLD));
			assertThat(ret[4], is(players[17]));
			assertThat(ret[4].getTier(), is(Tier.GOLD));
			assertThat(ret[5], is(players[10]));
			assertThat(ret[5].getTier(), is(Tier.GOLD));

			Player[] ret2 = m.newPlayer(players[21]);
			assertThat(ret2.length, is(6));
			assertThat(ret2[0], is(players[13]));
			assertThat(ret2[0].getTier(), is(Tier.BRONZE));
			assertThat(ret2[1], is(players[4]));
			assertThat(ret2[1].getTier(), is(Tier.IRON));
			assertThat(ret2[2], is(players[9]));
			assertThat(ret2[2].getTier(), is(Tier.IRON));
			assertThat(ret2[3], is(players[6]));
			assertThat(ret2[3].getTier(), is(Tier.IRON));
			assertThat(ret2[4], is(players[16]));
			assertThat(ret2[4].getTier(), is(Tier.IRON));
			assertThat(ret2[5], is(players[15]));
			assertThat(ret2[5].getTier(), is(Tier.IRON));

			for (int i=22; i<25; i++) {
				assertThat(m.newPlayer(players[i]), is(nullValue()));
			}

			Player[] ret3 = m.newPlayer(players[25]);
			assertThat(ret3.length, is(6));
			assertThat(ret3[0], is(players[2]));
			assertThat(ret3[0].getTier(), is(Tier.EMERALD));
			assertThat(ret3[1], is(players[11]));
			assertThat(ret3[1].getTier(), is(Tier.DIAMOND));
			assertThat(ret3[2], is(players[19]));
			assertThat(ret3[2].getTier(), is(Tier.DIAMOND));
			assertThat(ret3[3], is(players[22]));
			assertThat(ret3[3].getTier(), is(Tier.DIAMOND));
			assertThat(ret3[4], is(players[23]));
			assertThat(ret3[4].getTier(), is(Tier.DIAMOND));
			assertThat(ret3[5], is(players[24]));
			assertThat(ret3[5].getTier(), is(Tier.DIAMOND));
		});
	}
}
