import java.util.Scanner;

public class Baccarat {
    public static void main(String[] args) {
        BaccaratGame game = new BaccaratGame();
        if(args.length == 0){
            while(game.getShoeSize() >= 6){
                game.playRound();
                System.out.println("Round " + game.getRoundCount());
                game.getRound(game.getRoundCount() - 1).printHistory();
                System.out.println("");
            }
            game.printStats();
        } else if(args[0].equals("-i") || args[0].equals("--interactive")){
            Scanner sc = new Scanner(System.in);
            char playAgain = 'y';
            while((playAgain == 'y' || playAgain == 'Y') && game.getShoeSize() >= 6){
                game.playRound();
                System.out.println("Round " + game.getRoundCount());
                game.getRound(game.getRoundCount() - 1).printHistory();
                System.out.print("Another round? (y/n): ");
                playAgain = sc.next().charAt(0);
                System.out.println("");
            }
            game.printStats();
        } else {
            System.out.println("Invalid arguments");
        }
    }
}
