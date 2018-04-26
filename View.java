import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.List;

public class View {

    public static final String RED_PREFIX = "\u001B[31m";
    public static final String BLACK_PREFIX = "\u001B[34m";
    public static final String YELLOW_PREFIX = "\u001B[33m";
    public static final String RESET_COLOR = "\u001B[0m";
    Player player1 = new Human("pszemek polczag");
    Card card = new Card(0, 0, 0);

    public static void printHand(Player player) {
        List<Card> hand = player.getHand();
        for (Card card : hand) {
            try {
                String cardMiniatureDir = "cards-unicode/" + card.getRank() + "-" + card.getSuit() + ".txt";
                File loadCardMiniature = new File(cardMiniatureDir);
                Scanner cardMiniature = new Scanner(loadCardMiniature);
                String twojastara = cardMiniature.nextLine();
                if (card.getColor() == 1) {
                    System.out.print(" " + RED_PREFIX + twojastara + RESET_COLOR);
                } else {
                    System.out.print(" " + BLACK_PREFIX + twojastara + RESET_COLOR);
                }
                cardMiniature.close();
            } catch (FileNotFoundException error) {
                System.out.println("Card not found!");
            }
        }
    }

    public void printCard() {
        try {
            File loadCardFile = new File("/cards-fronts" + card.getRank() + "-" + card.getSuit() + "-front.txt");
            Scanner cardFile = new Scanner(loadCardFile);

            while (cardFile.hasNextLine()) {
                System.out.println(cardFile);
            }
        } catch (FileNotFoundException error) {
            System.out.println("Card front not found!");
        }
    }

    public static void printEnemyHand(Player player){
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++){
            try {
                File loadCardMiniature = new File("cards-unicode/CardBack.txt");
                Scanner cardMiniature = new Scanner(loadCardMiniature);
                String reverse = cardMiniature.nextLine();
                    System.out.print(" " + YELLOW_PREFIX + reverse + RESET_COLOR);
                cardMiniature.close();
            } catch (FileNotFoundException error) {
                System.out.println("Card not found!");
            }
    }}

    public static void printGame(Player firstPlayer, Player secondPlayer, List<Card> battlefield) {
        printEnemyHand(secondPlayer);
        System.out.println("\n");
        //printBattlefield(battlefield);
        printHand(firstPlayer);
    }
}