
public class Card {
    private String suit;
    private int rank;
    private boolean faceDown;
    // private String status;
    private int color;

    
    public Card(String suit, int rank, boolean faceDown, int color){
        this.suit = suit;
        this.rank = rank;
        this.color = color;
        this.faceDown = faceDown;
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
}