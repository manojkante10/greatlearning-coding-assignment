package game;


import models.Player;
import java.util.ArrayList;

public class DiceGameUtil {

    //prints the points, rank of each player
    public static void printGameStats(ArrayList<Player> players, int maxPoints) {
        System.out.println();
        System.out.printf("%-30s %-20s %-50s %-20s\n", "Player Name", "Player Points", "Minimum Points to Win", "Rank");
        players.forEach(p -> System.out.printf("%-30s %-20d %-50d %-20d\n", p.getName(), p.getPoints(), maxPoints, p.getRank()));
        System.out.println();
    }

//sets the rank of each player after a roll
    public static void setRanks(ArrayList<Player> players) {
        ArrayList<Player> playersCopy = (ArrayList<Player>) players.clone();
        playersCopy.sort((p1, p2) -> {
            if (! p1.hasFinished() && !p2.hasFinished())
                return p2.getPoints() - p1.getPoints();
            else if(!p1.hasFinished())
                return 1;
            else if(!p2.hasFinished())
                return -1;
            else
                return p1.getRank() - p2.getRank();
        });
        playersCopy.get(0).setRank(1);
        for (int i = 1; i < playersCopy.size(); i++) {
            if (playersCopy.get(i).getPoints() == playersCopy.get(i - 1).getPoints() && !playersCopy.get(i).hasFinished())
                playersCopy.get(i).setRank(playersCopy.get(i-1).getRank());
            else
                playersCopy.get(i).setRank(playersCopy.get(i-1).getRank()+1);
        }
    }
}
