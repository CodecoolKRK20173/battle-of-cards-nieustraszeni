import java.util.ArrayList;
import java.util.List;

public abstract class Player { 

    private List<Card> hand = new ArrayList<>();
    private List<Card> winPot = new ArrayList<>();

    abstract String getName();

    protected List<Card> getHand() {
        return hand;
    }

    protected List<Card> getWinPot() {
        return winPot;
    }
    protected boolean isHandEmpty() {
        return hand.isEmpty();
    }
    protected boolean isWinPotEmpty() {
        return winPot.isEmpty();
    }

}