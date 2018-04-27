import java.util.Scanner;
import java.util.List;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

public class View {

    public static final String RED_PREFIX = "\u001B[31m";
    public static final String BLACK_PREFIX = "\u001B[34m";
    public static final String YELLOW_PREFIX = "\u001B[33m";
    public static final String RESET_COLOR = "\u001B[0m";
    private static List<List<String>> battleCardLine;
    private static final String CLEAR = "\033[H\033[2J";

    public static void printHand(Player player) {
        List<Card> hand = player.getHand();
        System.out.println(player.getName() + "r hand: ");
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
        System.out.println("\n" + player.getName() + "r pot: ");
        List<Card> pot = player.getWinPot();
        for (int i = 0; i < pot.size(); i++) {
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
        }}


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

    public static List<List<String>> addCard(List<Card> battlefield) {
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

    public static void printBattlefield(List<Card> battlefield) {
        for (List<String> cardLine : addCard(battlefield)) {
            for (String card : cardLine) {
                System.out.println(card);
            }
        }

    }

    public static void printEnemyHand(Player player) {
        List<Card> hand = player.getHand();
        System.out.println(player.getName() + " hand: ");
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
        System.out.println("\n" + player.getName() + " pot: ");
        List<Card> pot = player.getWinPot();
        for (int i = 0; i < pot.size(); i++) {
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
        System.out.println("\n");
        printBattlefield(battlefield);
        System.out.println("\n");
        printHand(firstPlayer);
    }
}