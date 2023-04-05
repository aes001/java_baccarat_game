public class Baccarat {
    public static void main(String[] args) {
        Shoe shoe = new Shoe(6);
        shoe.shuffle();
        BaccaratHand player = new BaccaratHand();
        BaccaratHand banker = new BaccaratHand();

        Boolean[][] tableau = 
        // Literally the Tableau from Wikipedia
        // Row number represent the banker total, columns are the player's third card
        {
            {true,true,true,true,true,true,true,true,true,true},
            {true,true,true,true,true,true,true,true,false,true},
            {false,false,true,true,true,true,true,true,false,false},
            {false,false,false,false,true,true,true,true,false,false},
            {false,false,false,false,false,false,true,true,false,false},
            {false,false,false,false,false,false,false,false,false,false},
        };

        for(int i = 0; i < 2; i++){
            player.add(shoe.deal());
            banker.add(shoe.deal());
        }

        System.out.println("Player: " + player.toString() + " = " + player.value());
        if(player.isNatural()){
            System.out.println("Player has a natural!");
        }
        System.out.println("Banker: " + banker.toString() + " = " + banker.value());
        if(banker.isNatural()){
            System.out.println("Banker has a natural!");
        }

        // Check Natural
        if(player.isNatural() && banker.isNatural()){
            System.out.println("Tie");
            return;
        }
        if(player.isNatural()){
            System.out.println("Player wins!");
            return;
        }
        if(banker.isNatural()){
            System.out.println("Banker wins!");
            return;
        }


        // Draw a third card if the player's hand is less than 6
        if (player.value() < 6){
            player.add(shoe.deal());
            System.out.println("Dealing third card to player...");
            System.out.println("Player: " + player.toString() + " = " + player.value());
        }

        // If the player did NOT draw a third card then:
        // If the banker has an initial total of 5 or less, they draw a third card. 
        // If the banker has an initial total of 6 or 7, they stand.
        if(player.size() == 2){
            if(banker.value() < 6){
                banker.add(shoe.deal());
                System.out.println("Dealing third card to banker...");
                System.out.println("Banker: " + banker.toString() + " = " + banker.value());
                // If the banker drew a third card,
                // Check win
                // checkWin function returns true if the game is over and false if it is not
                if(checkWin(player, banker)){
                    return;
                }
            } else {
                // Check win
                if(checkWin(player, banker)){
                    return;
                }
            }
        }

        // If the player drew a third card,
        // The Tableau is consulted
        int bankerTotal = banker.value() < 3 ? 0 : (banker.value() > 6 ? 5 : banker.value() - 2);
        int playerThirdCard = player.cards.get(2).value();
        if(tableau[bankerTotal][playerThirdCard]){
            banker.add(shoe.deal());
            System.out.println("Dealing third card to banker...");
            System.out.println("Banker: " + banker.toString() + " = " + banker.value());
        }

        // Check win
        checkWin(player, banker);
        return;
    }
    

        // This whole algorithm collapses if both hands are 6 or 7
        // I don't know how to handle that
        // Wikipedia doesn't say anything about that

    

    // Win condition check
    // Returns a string with the outcome
    // Returns null if the game is not over
    public static boolean checkWin(BaccaratHand player, BaccaratHand banker){
        if(player.value() > banker.value()){
            System.out.println("Player wins!");
            return true;
        }
        if(player.value() < banker.value()){
            System.out.println("Banker wins!");
            return true;
        }
        if(player.value() == banker.value()){ 
            System.out.println("Tie");
            return true;
        }
        return false;
    }

}
