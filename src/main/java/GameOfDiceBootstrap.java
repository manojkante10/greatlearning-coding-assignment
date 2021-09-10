import game.DiceGame;

public class GameOfDiceBootstrap {

    public static void main(String[] args){
        if(args.length !=2 ){
            System.out.println("Expected 2 arguments: <N: number of players>, <M: Points to accumulate to finish the game>");
            return;
        }

        DiceGame diceGame = new DiceGame(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        diceGame.start();
    }
}
//main class to be executed
//2 arguments need to be passed