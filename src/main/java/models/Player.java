package models;

import java.util.Scanner;

public class Player {

    private final String name;
    private int points;
    private boolean hasPenalty;
    private boolean hasFinished;
    private int previousRoll;
    private int rank;



    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.hasPenalty = false;
        this.hasFinished = false;
        this.previousRoll = -1;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean hasPenalty() {
        return hasPenalty;
    }

    public void setHasPenalty(boolean hasPenalty) {
        this.hasPenalty = hasPenalty;
    }

    public boolean hasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public int getPreviousRoll() {
        return previousRoll;
    }

    public void setPrevMove(int previousRoll) {
        this.previousRoll = previousRoll;
    }

    public boolean canRoll() {
        return !this.hasFinished && !this.hasPenalty;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    //player plays his turn by entering 'r'
    public void playTurn() {
        while (true) {
            System.out.printf("%s: Enter 'r'(and click on Enter) to roll your dice: ", this.name);
            Scanner sc = new Scanner(System.in);
            char c = sc.next().charAt(0);
            if (c == 'r' || c == 'R')
                return;
            else
                System.out.println("Invalid Input. Please enter 'r' to roll your dice");
        }
    }

}
