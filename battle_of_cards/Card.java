import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Card implements Comparable<Card> {
    private int suit;
    private int rank;
    private boolean faceDown;
    private int color;
    private static List<Card> cards = new ArrayList<>();

    private static final int LESS_THAN = -1;
    private static final int EQUAL_TO = 0;
    private static final int GREATER_THAN = 1;


    protected Card(int suit, int rank, int color) {
        this.suit = suit;
        this.rank = rank;
        this.color = color;
        this.faceDown = true;
        cards.add(this);
    }


    public int compareTo(Card card) {
        if (this.getRank() == card.getRank())
            return EQUAL_TO;
            
        else if (this.getRank() > card.getRank())
            return GREATER_THAN;

        else 
            return LESS_THAN;
    }


    protected int getSuit() {
        return suit;
    }


    protected int getRank() {
        return rank;
    }


    protected boolean isFaceDown() {
        return faceDown;
    }


    protected int getColor() {
        return color;
    }


    protected void flipCard() {
        if (isFaceDown())
            faceDown = false;
        faceDown = true;
    }


    public String toString() {
        return "The " + "Rank" + rank + " of " + "Suit" + suit;
    }

    
    protected static List<Card> getCards() {
        return cards;
    }
}