package service;

public class DiceService {

    //returns a value between 1 to 6
    public int roll() {
        return 1 + (int)(Math.random() * 6);
    }
}
