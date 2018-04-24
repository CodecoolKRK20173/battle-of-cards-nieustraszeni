import java.util.ArrayList;

public class Game {
    private Player player1 = new Player();
    private Player player2 = new Player();
    private Deck deck = new Deck();
    private List<Card> startDeck = new ArrayList<>();
    private List<Card> player1Deck = new ArrayList<>();
    private List<Card> player2Deck = new ArrayList<>();


    public dealCards(Player player1, Player player2){
        startDeck = deck.createNewDeck();
        player1Deck = deck.createDeckForPlayer(startDeck);
        player2Deck = deck.createDeckForPlayer(startDeck);
    }
    
}