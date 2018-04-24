
public class Card {
    private String suit;
    private int rank;
    private boolean faceDown;
    private String status;
    private int color;

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
    
}