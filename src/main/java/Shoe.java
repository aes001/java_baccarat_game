import java.util.Collections;

public class Shoe extends CardCollection{
    public Shoe(int decks){
        super();
        if (!(decks == 6 || decks == 8)){
            throw new CardException("Invalid number of decks");
        }
        for (int i = 0; i < decks; i++){
            for (Card.Suit s: Card.Suit.values()){
                for (Card.Rank r: Card.Rank.values()){
                    cards.add(new BaccaratCard(r, s));
                }
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card deal(){
        if (cards.size() == 0){
            throw new CardException("Empty shoe");
        }
        return cards.remove(0);
    }

}
