import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Human extends Player {
    public String name;

    Player(String name) {
        this.name = name;
        handDeck = new ArrayList<>();
        winPotDeck = new ArrayList<>();
    }


    public void play() {
        System.out.println(name + " is playing...");
    }
    

    public String getName() {
        return name;
    }
}

