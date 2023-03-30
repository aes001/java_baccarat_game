public class BaccaratHand extends CardCollection{
    public BaccaratHand() {
        super();
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < size(); i++) {
            output += cards.get(i).toString();
            // Add a space after each card except the last one
            if(i != size() - 1) {
                output += " ";
            }
        }
        return output;
    }

    public boolean isNatural() {
        if(size() != 2) {
            return false;
        }
        if(value() == 8 || value() == 9) {
            return true;
        }
        return false;
    }

    @Override
    public int value() {
        int sum = 0;
        for (int i = 0; i < size(); i++) {
            sum += cards.get(i).value();
        }
        return sum % 10;
    }

}