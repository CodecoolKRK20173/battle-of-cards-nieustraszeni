import java.awt.List;
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


    public boolean isGameWon(){
        // create conditions to win game
        return true;
    }


    public roundOfGame(){
        // create conditions to start round
    }


    public evaluableRound(){
        // create conditions to win round or fight
    }


    public void playGame(){
        boolean gameRun = true;

        while (gameRun){
            if (player1Deck.size() == 0 || player2Deck.size() == 0){
                if (isGameWon() == true){
                    System.out.println("You win the game!");
                    gameRun = false;
                } else {
                    System.out.println("You lose the game!");
                    gameRun = false;
                }
            } else {
                roundOfGame();
            }

        }
    }
    
}