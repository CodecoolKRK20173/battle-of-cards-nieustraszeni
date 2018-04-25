public abstract class Player { 

    private List<Card> handDeck;
    private List<Card> winPotDeck;

    abstract void play();

    
    public List<Card> getHandDeck() {
        return handDeck;
    }


    public List<Card> getWinPotDeck() {
        return winPotDeck;
    }

}