import java.util.ArrayList;

public class Card {
    private int suit;
    private int rank;
    private boolean faceDown;
    private int color;
    private static ArrayList<Card> cards = new ArrayList<>();


    public Card(int suit, int rank, int color) {
        this.suit = suit;
        this.rank = rank;
        this.color = color;
        this.faceDown = true;
        cards.add(this);
    }


    public String getSuit(){
        return suit;
    }
    

    public int getRank(){
        return rank;
    }


    public boolean isFaceDown(){
        return faceDown;
    }


    public String getStatus(){
        return status;
    }


    public int getColor(){
        return color;
    }


    public String toString(){
        return "The " + "Rank" + rank + " of " + "Suit" + suit;
    }

    
    public void flipCard(){
        // check faceDown status and change ascii outfit from file  
    }


    public boolean compareTo(){
        // compare rank cards from two players and return boolean
        return true;
    }
    public static getCards() {
        return cards;
    }
}