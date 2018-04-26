import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;

public class Game {
    private Scanner sc = new Scanner(System.in);
    private static final int FIRST_CARD = 0;
    private static final int HALF_SIZE_OF_DECK = 12;
    private Card card;
    private List<Card> battleField;
    private Player firstPlayer;
    private Player secondPlayer;
    private Card firstPlayerCard;
    private Card secondPlayerCard;
    private boolean endRound;
    private int firstPlayerCardWhenDraw;
    private int secondPlayerCardWhenDraw;
    private String fileName = "Scores.txt";
    private Highscore highscore;

    public Game() {
        this.highscore = new Highscore();
        this.firstPlayer = new Human("Player 1");
        this.secondPlayer = new Human("Player 2");
        this.battleField = new ArrayList<>();
        createNewDeck();
        dealCards();
    }

    private void dealCards() {
        for (int i = HALF_SIZE_OF_DECK; i > 0; i--) {
            firstPlayer.getHand().add(Card.getCards().get(i));
            Card.getCards().remove(i);
        }

        for (Card card : Card.getCards())
            secondPlayer.getHand().add(card);

        Card.getCards().clear();
    }


    private boolean isGameWon() {
        if (firstPlayerCards() == 0 || secondPlayerCards() == 0)
            return false;
        else 
            return true;
    }


    private void refillFirstPlayerHand() {
        if (firstPlayer.isHandEmpty() && firstPlayer.getWinPot().size() != 0) {
            Collections.shuffle(firstPlayer.getWinPot());
            for (Card card : firstPlayer.getWinPot())
                firstPlayer.getHand().add(card);
            
            firstPlayer.getWinPot().clear();
        }
    }


    private void refillSecondPlayerHand() {
        if (secondPlayer.isHandEmpty() && secondPlayer.getWinPot().size() != 0) {
            Collections.shuffle(secondPlayer.getWinPot());

            for (Card card : secondPlayer.getWinPot())
                secondPlayer.getHand().add(card);

            secondPlayer.getWinPot().clear();
        }
    }


    private void refillHandsIfEmpty() {
        refillFirstPlayerHand();
        refillSecondPlayerHand();
    }


    private int firstPlayerCards() {
        return firstPlayer.getHand().size() + firstPlayer.getWinPot().size();
    }


    private int secondPlayerCards() {
        return secondPlayer.getHand().size() + secondPlayer.getWinPot().size();
    }


    private void takeFaceUpCards() {
        firstPlayerCard = firstPlayer.getHand().get(FIRST_CARD);
        firstPlayerCard.flipCard();
        secondPlayerCard = secondPlayer.getHand().get(FIRST_CARD);
        secondPlayerCard.flipCard();
    }


    private void takeFaceDownCards() {
        firstPlayerCard = firstPlayer.getHand().get(FIRST_CARD);
        secondPlayerCard = secondPlayer.getHand().get(FIRST_CARD);
    }


    private void addCardsToBattlefield() {
        battleField.add(firstPlayerCard);
        battleField.add(secondPlayerCard);
        firstPlayer.getHand().remove(FIRST_CARD);
        secondPlayer.getHand().remove(FIRST_CARD);
    }


    private void dragCardsFromPlayers() {
        try {
            takeFaceUpCards();
            addCardsToBattlefield();
        } catch (IndexOutOfBoundsException e) {
            whoWon();
        }
    }


    private void handleDraw() {
        refillHandsIfEmpty();
        takeFaceDownCards();
        addCardsToBattlefield();
        refillHandsIfEmpty();
        takeFaceUpCards();
        addCardsToBattlefield();
        refillHandsIfEmpty();
        evaluateRound();
    }


    private void playerWinRound(Player player) {
        for (Card card : battleField) {

            if (!card.isFaceDown()) {
                card.flipCard();
                player.getWinPot().add(card);
            }

            player.getWinPot().add(card);
        }
        battleField.clear();
        refillHandsIfEmpty();
    }


    private void whoWon() {
        if (firstPlayerCards() < secondPlayerCards()) {

            for (Card card : firstPlayer.getHand())
                secondPlayer.getHand().add(card);

            for (Card card : firstPlayer.getWinPot())
                secondPlayer.getWinPot().add(card);

            playerWinRound(secondPlayer);
            firstPlayer.getHand().clear();
            firstPlayer.getWinPot().clear();

        } else {

            for (Card card : secondPlayer.getHand())
                firstPlayer.getHand().add(card);

            for (Card card : secondPlayer.getWinPot())
                firstPlayer.getWinPot().add(card);

            playerWinRound(firstPlayer);
            secondPlayer.getHand().clear();
            secondPlayer.getWinPot().clear();

        }
    }


    private void evaluateRound() {
        endRound = true;

        while (endRound) {
            View.printGame(firstPlayer, secondPlayer, battleField);
            int result = 0;
            firstPlayerCardWhenDraw = battleField.size() - 2;
            secondPlayerCardWhenDraw = battleField.size() - 1;
            result = battleField.get(firstPlayerCardWhenDraw).compareTo(battleField.get(secondPlayerCardWhenDraw));
        
            switch (result) {
                case 1:
                    playerWinRound(firstPlayer);
                    break;
                case 0:
                    if (firstPlayerCards() > 2 && secondPlayerCards() > 2) {
                        handleDraw();
                        break;
                    } else {
                        whoWon();
                        break;
                    }

                case -1:
                    playerWinRound(secondPlayer);
                    break;
            }

            endRound = false;
        }
    }


    private void pressEnterToContinue() {
        sc.nextLine();
    }


    private void playerShuffle(){
        View.printHand(firstPlayer);
        System.out.println("Do you want shuffle your deck? (press 0)");

        switch (takeInput()) {
            case 0:
                Collections.shuffle(firstPlayer.getHand());
                break;
            default:
                break;
        }
    }


    private int takeInput(){
        Scanner sc = new Scanner(System.in);
        int option = 1;
        try {
            option = sc.nextInt();
        } catch (InputMismatchException e){
            takeInput();
        }

        return option;
    }


    public void playGame() {
        int score = 0;
        playerShuffle();
        while (isGameWon()) {
            dragCardsFromPlayers();
            evaluateRound();
            score++;
            pressEnterToContinue();
        }

        View.printGame(firstPlayer, secondPlayer, battleField);
        
        if(secondPlayerCards()  == 0)
            endGame(firstPlayer, score);
        else
            endGame(secondPlayer, score);
    }
    

    public void endGame(Player player, int score) {
        highscore.readHighscoreFromFile(fileName);
        System.out.printf(player.getName() +"! Your highscore is %d%n", score);
        highscore.addScoreToList(new Score(player.getName(), score));
        highscore.sortListByPoints();
        writeScoreToFile();
    }
    

    public void writeScoreToFile() {
        highscore.writeScoreListToFile(highscore.getScores(), false, "Scores.txt");
    }


    private void createNewDeck() {
        for (int suit = 1; suit < 5; suit++) {
            for (int rank = 9; rank < 15; rank++) {
                if (suit == Suit.DIAMONDS.type() || suit == Suit.HEARTS.type())
                    card = new Card(suit, rank, Color.RED.type());
                else if(suit == Suit.SPADES.type() || suit == Suit.CLUBS.type())
                    card = new Card(suit, rank, Color.BLACK.type());
            }
        }

        Collections.shuffle(Card.getCards());
    }
}