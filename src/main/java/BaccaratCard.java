public class BaccaratCard extends Card{

    BaccaratCard(Card.Rank r, Card.Suit s) {
        super(r, s);
    }

    @Override
    public int value() {
        if (getRank().ordinal() + 1 >= 10) {
            return 0;
        }
        return getRank().ordinal() + 1;
    }

}