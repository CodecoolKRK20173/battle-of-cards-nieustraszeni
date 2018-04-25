import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> playerHandDeck = new ArrayList<>();
    private final int HALF_SIZE_OF_DECK = 12;
    private static final int RED = 1;
    private static final int BLACK = 2;
    private static final int SPADES = 1;
    private static final int HEARTS = 2;
    private Card card;


    public void createNewDeck() {
        for (int suit = 1; suit < 5; suit++){
            for (int rank = 1; rank < 7; rank++){
                if (suit == SPADES || suit == HEARTS)
                    card = new Card(suit, rank, RED);
                else
                    card = new Card(suit, rank, BLACK);
            }
        }

        Collections.shuffle(Card.getCards());
    }


    public List<Card> createDeckForPlayer() {
        for(int i = HALF_SIZE_OF_DECK; i > 0; i--){
            playerHandDeck.add(Card.getCards().get(i));
            Card.getCards().remove(i);
        }

        return playerHandDeck;
    }
    
    public List<Card> getPlayerHandDeck(){
        return playerHandDeck;
    }
}