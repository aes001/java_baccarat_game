/*
 * BaccaratRound.java
 * @author Andreas Sunardi
 * Represents a single round of Baccarat
 * A shoe is required to instantiate a round
 * When a round is played the winner is determined and the history is recorded
 * This history can then be printed to the console or anything else the user wants
 */

import java.util.ArrayList;
import java.util.List;

public class BaccaratRound {

    // The Tableau from Wikipedia
    // Row number represent the banker total, columns are the player's third card
    // Represents the decision making of the banker to draw a third card after the player has drawn their third card
    // (true = draw, false = stand)
    static final Boolean tableau [][] = {
        {true,true,true,true,true,true,true,true,true,true},
        {true,true,true,true,true,true,true,true,false,true},
        {false,false,true,true,true,true,true,true,false,false},
        {false,false,false,false,true,true,true,true,false,false},
        {false,false,false,false,false,false,true,true,false,false},
        {false,false,false,false,false,false,false,false,false,false},
    };

    /*
     * Winner is an integer representing the winner of the round
     * 0 = tie
     * 1 = player
     * 2 = banker
     */
    private int winner = 0;

    private Shoe shoe;
    private List<String> history = new ArrayList<String>();


    // Constructor assigns the given shoe to the round and plays the round
    public BaccaratRound(Shoe shoe){
        this.shoe = shoe;
        playRound();
    }

    // The logic for playing a round of Baccarat
    // Every event in the round is recorded in the history list
    // The winner is determined and stored in the winner field
    private void playRound(){
        BaccaratHand player = new BaccaratHand();
        BaccaratHand banker = new BaccaratHand();

        for(int i = 0; i < 2; i++){
            player.add(shoe.deal());
            banker.add(shoe.deal());
        }

        history.add("Player: " + player.toString() + " = " + player.value());
        if(player.isNatural()){
            history.add("Player has a natural!");
        }
        history.add("Banker: " + banker.toString() + " = " + banker.value());
        if(banker.isNatural()){
            history.add("Banker has a natural!");
        }

        // Check Natural
        if(player.isNatural() && banker.isNatural()){
            history.add("Tie");
            winner = 0;
            return;
        }
        if(player.isNatural()){
            history.add("Player wins!");
            winner = 1;
            return;
        }
        if(banker.isNatural()){
            history.add("Banker wins!");
            winner = 2;
            return;
        }

        // Draw a third card if the player's hand is less than 6
        if (player.value() < 6){
            player.add(shoe.deal());
            history.add("Dealing third card to player...");
            history.add("Player: " + player.toString() + " = " + player.value());
        }

        // If the player did NOT draw a third card then:
        // If the banker has an initial total of 5 or less, they draw a third card.
        // If the banker has an initial total of 6 or 7, they stand.
        if(player.size() == 2){
            if(banker.value() < 6){
                banker.add(shoe.deal());
                history.add("Dealing third card to banker...");
                history.add("Banker: " + banker.toString() + " = " + banker.value());

                if(checkWin(player, banker)){
                    return;
                }
            } else {
                if(checkWin(player, banker)){
                    return;
                }
            }
        }

        // If the player did draw a third card then:
        // The banker consults the tableau to determine whether to draw a third card.
        int bankerTotal = banker.value() < 3 ? 0 : (banker.value() > 6 ? 5 : banker.value() - 2);
        int playerThirdCard = player.cards.get(2).value();
        if(tableau[bankerTotal][playerThirdCard]){
            banker.add(shoe.deal());
            history.add("Dealing third card to banker...");
            history.add("Banker: " + banker.toString() + " = " + banker.value());
        }

        checkWin(player, banker);
        return;
    }

    // Checks the winner of the round
    // Assigns the winner to the winner field if the round is over
    // Returns true if the round is over
    private Boolean checkWin(BaccaratHand player, BaccaratHand banker){
        Boolean endGame = false;
        if(player.value() > banker.value()){
            winner = 1;
            history.add("Player wins!");
            endGame = true;
        } else if(player.value() < banker.value()){
            winner = 2;
            history.add("Banker wins!");
            endGame = true;
        } else if(player.value() == banker.value()){
            winner = 0;
            history.add("Tie");
            endGame = true;
        }
        return endGame;
    }

    // Prints the events of the round to the console
    public void printHistory(){
        for(String s : history){
            System.out.println(s);
        }
    }

    // Returns the integer representation of the winner of the round
    public int getWinner(){
        return winner;
    }
    
}
