/*
 * Name: Jinhyo Park
 * Student ID: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public class temp2_MatchMaker implements IMatchMaker {
    private Queue<Player> matchMakingQueue;
    private int iron;
    private int bronze;
    private int silver;
    private int gold;
    private int platinum;
    private int emerald;
    private int diamond;

    public temp2_MatchMaker(){
        // constructor
        matchMakingQueue = new Queue<Player>();
        iron = 0;
        bronze = 0;
        silver = 0;
        gold = 0;
        platinum = 0;
        emerald = 0;
        diamond = 0;
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
        matchMakingQueue.enqueue(player);
        switch (player.getTier()) {
            case IRON: iron++; break;
            case BRONZE: bronze++; break;
            case SILVER: silver++; break;
            case GOLD: gold++; break;
            case PLATINUM: platinum++; break;
            case EMERALD: emerald++; break;
            case DIAMOND: diamond++; break;
            default: break;
        }

        if (iron == 6) return setPlayers(Tier.IRON, Tier.DIAMOND, Tier.BRONZE);
        if (bronze == 6) return setPlayers(Tier.BRONZE, Tier.IRON, Tier.SILVER);
        if (silver == 6) return setPlayers(Tier.SILVER, Tier.BRONZE, Tier.GOLD);
        if (gold == 6) return setPlayers(Tier.GOLD, Tier.SILVER, Tier.PLATINUM);
        if (platinum == 6) return setPlayers(Tier.PLATINUM, Tier.GOLD, Tier.EMERALD);
        if (emerald == 6) return setPlayers(Tier.EMERALD, Tier.PLATINUM, Tier.DIAMOND);
        if (diamond == 6) return setPlayers(Tier.DIAMOND, Tier.EMERALD, Tier.IRON);
        return null;
    }

    private Player[] setPlayers(Tier target, Tier left, Tier right) {
        Queue<Player> temp = new Queue<Player>();
        Player[] ret = new Player[6];

        boolean leftFlag = true;
        boolean rightFlag = true;
        if (left == Tier.DIAMOND) leftFlag = false;
        if (right == Tier.IRON) rightFlag = false;

        int i = 0;
        while (matchMakingQueue.getSize() > 0) {
            Player p = matchMakingQueue.dequeue();
            if (i < 6 && p.getTier() == target) {
                ret[i] = p;
                leftFlag = false;
                rightFlag = false;
                i++;
                controlNum(target);
                continue;
            }
            if (leftFlag && p.getTier() == left) {
                ret[i] = p;
                leftFlag = false;
                i++;
                controlNum(left);
                continue;
            }
            if (rightFlag && p.getTier() == right) {
                ret[i] = p;
                rightFlag = false;
                i++;
                controlNum(right);
                continue;
            }
            temp.enqueue(p);
        }
        matchMakingQueue = temp;
        return ret;
    }

    private void controlNum(Tier target) {
        switch (target) {
            case IRON: iron--; break;
            case BRONZE: bronze--; break;
            case SILVER: silver--; break;
            case GOLD: gold--; break;
            case PLATINUM: platinum--; break;
            case EMERALD: emerald--; break;
            case DIAMOND: diamond--; break;
            default: break;
        }
    }
}
