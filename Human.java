import java.util.ArrayList;
import java.util.List;

public class Human extends Player {
    private List<Card> handDeck;
    private List<Card> winPotDeck;
    public String name;

    Player(String name) {
        this.name = name;
        handDeck = new ArrayList<>();
        winPotDeck = new ArrayList<>();
    }


    public void play() {
        System.out.println(name + " is playing...");
    }


    public List<Card> getHandDeck() {
        return handDeck;
    }


    public List<Card> getWinPotDeck() {
        return winPotDeck;
    }


    public String getName() {
        return name;
    }
}

