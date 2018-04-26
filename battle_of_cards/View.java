
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.List;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class View {

    private static final String RED_PREFIX = "\u001B[31m";
    private static final String BLACK_PREFIX = "\u001B[34m";
    private static final String YELLOW_PREFIX = "\u001B[33m";
    private static final String RESET_COLOR = "\u001B[0m";
    private static List<List<String>> battleCardLine;


    protected static void printHand(Player player) {
        List<Card> hand = player.getHand();
        for (Card card : hand) {
            try {
                String cardMiniatureDir = "cards-unicode/" + card.getRank() + "-" + card.getSuit() + ".txt";
                File loadCardMiniature = new File(cardMiniatureDir);
                Scanner cardMiniature = new Scanner(loadCardMiniature);
                String printableCardMiniature = cardMiniature.nextLine();
                if (card.getColor() == 1) {
                    System.out.print(" " + RED_PREFIX + printableCardMiniature + RESET_COLOR);
                } else {
                    System.out.print(" " + BLACK_PREFIX + printableCardMiniature + RESET_COLOR);
                }
                cardMiniature.close();
            } catch (FileNotFoundException error) {
                System.out.println("Card not found!");
            }
        }
    }


    private static List<String> cardToList(Card card) {
        String line;
        List<String> cardLineList = new ArrayList<String>();
        String cardLine;
        String file = "cards-fronts/" + card.getRank() + "-" + card.getSuit() + "-front.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {

                if (card.getColor() == 1) {
                    cardLine = " " + RED_PREFIX + line + RESET_COLOR;
                    cardLineList.add(cardLine);

                } else {
                    cardLine = " " + BLACK_PREFIX + line + RESET_COLOR;
                    cardLineList.add(cardLine);
                }
            }

        } catch (IOException error) {
            System.out.println("Card front not found!");
        }
        return cardLineList;
    }


    protected static List<List<String>> addCard(List<Card> battlefield) {
        battleCardLine = new ArrayList<>();
        if (!battlefield.isEmpty()) {
            Card firstPlayerCard = battlefield.get(battlefield.size() - 2);
            Card secondPlayerCard = battlefield.get(battlefield.size() - 1);
            List<String> firstPlayerCardLine = cardToList(firstPlayerCard);
            List<String> secondPlayerCardLine = cardToList(secondPlayerCard);

            battleCardLine.add(secondPlayerCardLine);
            battleCardLine.add(firstPlayerCardLine);
        }
        return battleCardLine;
    }


    protected static void printBattlefield(List<Card> battlefield) {
        for (List<String> cardLine : addCard(battlefield)) {
            for (String card : cardLine) {
                System.out.println(card);
            }
        }

    }


    protected static void printEnemyHand(Player player) {
        List<Card> hand = player.getHand();
        for (int i = 0; i < hand.size(); i++) {
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


    protected static void printGame(Player firstPlayer, Player secondPlayer, List<Card> battlefield) {
        printEnemyHand(secondPlayer);
        System.out.println("\n");
        printBattlefield(battlefield);
        System.out.println("\n");
        printHand(firstPlayer);
    }
}