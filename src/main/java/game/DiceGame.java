package game;

import service.DiceService;
import models.Player;

import java.util.ArrayList;
import java.util.Collections;

public class DiceGame {

    private final int numOfPlayers;
    private final ArrayList<Player> players;
    private final int pointsToWin;
    private final DiceService diceService;
    private final ArrayList<Player> rollingOrder;

    public DiceGame(int numOfPlayers, int pointsToWin) {
        this.numOfPlayers = numOfPlayers;
        this.players = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++) {
            this.players.add(new Player("Player-" + (i + 1)));
        }
        this.diceService = new DiceService();
        this.pointsToWin = pointsToWin;
        this.rollingOrder = (ArrayList) players.clone();
        Collections.shuffle(rollingOrder);
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public int getPointsToWin() {
        return pointsToWin;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public DiceService getDice() {
        return diceService;
    }

    public boolean isGameOver() {
        boolean res = true;
        for (Player p : players)
            res = res && p.hasFinished();
        return res;
    }

    public void start() {
        System.out.println("---------STARTING GAME--------");

        while (!isGameOver()) { //while the game is not over
            for (Player p : this.rollingOrder) {
                if (p.canRoll()) { // if a player is eligible to roll
                    while (true) {
                        p.playTurn();
                        System.out.printf("%s: Rolling Dice..\n", p.getName());
                        int diceRoll = this.diceService.roll();
                        System.out.printf("%s: You rolled %d\n", p.getName(), diceRoll);

                        //if max points are reached, then the player finishes his game
                        if (diceRoll + p.getPoints() >= this.pointsToWin) {
                            p.setPoints(this.pointsToWin);
                            p.setHasFinished(true);
                        }
                        else
                            p.setPoints(p.getPoints() + diceRoll);
                        DiceGameUtil.setRanks(this.players);
                        DiceGameUtil.printGameStats(this.players, this.pointsToWin);
                        //if 2 consecutive 1s are rolled, penalty is being set
                        if (diceRoll == 1 && p.getPreviousRoll() == 1) {
                            p.setHasPenalty(true);
                            System.out.printf("%s has got a penalty and won't be eligible to play his next turn\n", p.getName());
                        }
                        p.setPrevMove(diceRoll); //previous move is set
                        if (p.hasFinished()) {
                            System.out.printf("----Wohoo!! %s just finished his game---\n", p.getName());
                            break;
                        }
                        if (diceRoll != 6)
                            break;
                        System.out.printf("Congrats!! %s has got an additional move as he rolled a 6\n", p.getName());
                    }
                } else if (!p.hasFinished() && p.hasPenalty()) {
                    System.out.printf("Skipping %s's turn as the player has a penalty\n", p.getName());
                    p.setHasPenalty(false);
                }
            }
        }
        System.out.println("xxxxxxxxxx GAME OVER xxxxxxxxxx");

    }


}
