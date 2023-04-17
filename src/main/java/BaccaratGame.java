import java.util.ArrayList;
import java.util.List;

/*
 * BaccaratGame.java
 * @author Andreas Sunardi
 * Represents a game of Baccarat
 * A game is made up of rounds of Baccarat
 * Hence, this object stores the rounds and the stats of the game
 */

public class BaccaratGame {

    // Fields for storing the stats of the game and the rounds
    private int playerWins = 0;
    private int bankerWins = 0;
    private int ties = 0;
    private List<BaccaratRound> rounds = new ArrayList<BaccaratRound>();
    // The shoe that will be used for every round of the game
    private Shoe shoe;

    // Constructor creates a new shoe and shuffles it
    public BaccaratGame(){
        this.shoe = new Shoe(6);
        shoe.shuffle();
    }

    // "Plays" a round of Baccarat. This is done by:
    // Creating a new BaccaratRound object and adding it to the list of rounds
    // And checking the winner of the round and incrementing the appropriate stat
    public void playRound(){
        BaccaratRound round = new BaccaratRound(shoe);
        rounds.add(round);
        if(round.getWinner() == 1){
            playerWins++;
        } else if(round.getWinner() == 2){
            bankerWins++;
        } else {
            ties++;
        }
    }

    // Returns the number of cards left in the shoe
    public int getShoeSize(){
        return shoe.size();
    }

    // Returns the number of rounds that have been played
    public int getRoundCount(){
        return rounds.size();
    }

    // Returns the specified BaccaratRound object
    public BaccaratRound getRound(int i){
        return rounds.get(i);
    }

    // Prints a summary of the game to the console
    public void printStats(){
        System.out.println(rounds.size() + " rounds played.");
        System.out.println(playerWins + " player wins.");
        System.out.println(bankerWins + " banker wins.");
        System.out.println(ties + " ties.");
    }


}
