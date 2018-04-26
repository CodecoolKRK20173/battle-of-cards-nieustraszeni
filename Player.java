import java.util.ArrayList;
import java.util.List;

public abstract class Player { 

    private List<Card> hand = new ArrayList<>();
    private List<Card> winPot = new ArrayList<>();

    abstract String getName();

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getWinPot() {
        return winPot;
    }
    public boolean isHandEmpty() {
        return hand.isEmpty();
    }
    public boolean isWinPotEmpty() {
        return winPot.isEmpty();
    }

}