import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Deck {
    private List<Card> deck = new ArrayList<>();
    private List<Card> playerHandDeck = new ArrayList<>();
    private final int HALF_SIZE_OF_DECK = 12;
    private static final String RED = "red";
    private static final String BLACK = "black";


    public List<Card> createNewDeck(){
        for (int suit = 1; suit < 5; suit++){
            for (int rank = 1; rank < 7; rank++){
                if (suit == 1 || suit == 2)
                    deck.add(new Card(suit, rank, RED));
                else
                    deck.add(new Card(suit, rank, BLACK));
            }
        }

        Collection.shuffle(deck);
        return deck;
    }


    public List<Card> createDeckForPlayer(List<Card> deck){
        for(int i = HALF_SIZE_OF_DECK; i > 0; i--){
            playerHandDeck.add(deck(i));
            deck.remove(i);
        }

        return playerHandDeck;
    }
    
    public List<Card> getPlayerHandDeck(){
        return playerHandDeck;
    }
}