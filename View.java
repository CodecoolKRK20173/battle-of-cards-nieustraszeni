import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.List;

public class View {

    private static final String RED_PREFIX = "\u001B[31m";
    private static final String BLACK_PREFIX = "\u001B[34m";
    private static final String YELLOW_PREFIX = "\u001B[33m";
    private static final String RESET_COLOR = "\u001B[0m";
    private static final String CLEAR = "\033[H\033[2J";
    Player player1 = new Human("pszemek polczag");
    Card card = new Card(0, 0, 0);

    public static void printHand(Player player) {
        List<Card> hand = player.getHand();
        System.out.println("Your hand: ");
        for (Card card : hand) {
            try {
                String cardMiniatureDir = "cards-unicode/" + card.getRank() + "-" + card.getSuit() + ".txt";
                File loadCardMiniature = new File(cardMiniatureDir);
                Scanner cardMiniature = new Scanner(loadCardMiniature);
                String cardImage = cardMiniature.nextLine();
                if (card.getColor() == 1) {
                    System.out.print(" " + RED_PREFIX + cardImage + RESET_COLOR);
                } else {
                    System.out.print(" " + BLACK_PREFIX + cardImage + RESET_COLOR);
                }
                cardMiniature.close();
            } catch (FileNotFoundException error) {
                System.out.println("Card not found!");
            }
        }
        System.out.println();
    }
    private static void printPot(Player player) {
        List<Card> pot = player.getWinPot();
        System.out.println("Your win pot: ");
        for (Card card : pot) {
            try {
                String cardMiniatureDir = "cards-unicode/CardBack.txt";
                File loadCardMiniature = new File(cardMiniatureDir);
                Scanner cardMiniature = new Scanner(loadCardMiniature);
                String reverse = cardMiniature.nextLine();
                System.out.print(" " + YELLOW_PREFIX + reverse + RESET_COLOR);
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

    private static void printEnemyHand(Player player){
        List<Card> hand = player.getHand();
        System.out.println("Enemy hand: ");
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
        } System.out.println();
    }
    private static void printEnemyPot(Player player) {
        List<Card> pot = player.getWinPot();
        System.out.println("Enemy win pot: ");
        for (int i = 0; i < pot.size(); i++){
            try {
                File loadCardMiniature = new File("cards-unicode/CardBack.txt");
                Scanner cardMiniature = new Scanner(loadCardMiniature);
                String reverse = cardMiniature.nextLine();
                    System.out.print(" " + YELLOW_PREFIX + reverse + RESET_COLOR);
                cardMiniature.close();
            } catch (FileNotFoundException error) {
                System.out.println("Card not found!");
            }
        }

    }


    public static void printGame(Player firstPlayer, Player secondPlayer, List<Card> battlefield) {
        System.out.println(CLEAR);
        printEnemyHand(secondPlayer);
        printEnemyPot(secondPlayer);
        System.out.println("\n");
        //printBattlefield(battlefield);
        printHand(firstPlayer);
        printPot(firstPlayer);
    }
}