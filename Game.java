import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
    Scanner sc = new Scanner(System.in);
    private static final int RED = 1;
    private static final int BLACK = 2;
    private static final int SPADES = 1;
    private static final int HEARTS = 2;
    private static final int FIRST_CARD = 0;
    private static final int FIRST_PLAYER_CARD = 0;
    private static final int SECOND_PLAYER_CARD = 1;
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

    public Game() {
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
        for (Card card : Card.getCards()) {
            secondPlayer.getHand().add(card);

        }
        Card.getCards().clear();
    }

    private void isGameWon() {
        if (firstPlayer.isHandEmpty() && firstPlayer.getWinPot().isEmpty()
                || secondPlayer.isHandEmpty() && secondPlayer.getWinPot().isEmpty()) {
            isGameWon = false;
        } else {
            isGameWon = true;
        }
    }

    private void refillHandsIfEmpty() {
        if (firstPlayer.isHandEmpty() && firstPlayer.getWinPot().size() != 0) {
            for (Card card : firstPlayer.getWinPot()) {
                firstPlayer.getHand().add(card);
            }
            firstPlayer.getWinPot().clear();
        }
        if (secondPlayer.isHandEmpty() && secondPlayer.getWinPot().size() != 0) {
            for (Card card : secondPlayer.getWinPot()) {
                secondPlayer.getHand().add(card);
            }
            secondPlayer.getWinPot().clear();
        }
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
        takeFaceUpCards();
        addCardsToBattlefield();
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
    }
    private void isRoundGoOn() {
        if (isGameWon = true) {
            endRound = true;
        } else {
            endRound = false;
        }
    }

    private void evaluateRound() {
       
        isRoundGoOn();
        while (endRound) {
            int result = 0;
            if (battleField.size() == 2) {
                result = battleField.get(FIRST_PLAYER_CARD).compareTo(battleField.get(SECOND_PLAYER_CARD));
            } else {
                result = battleField.get(battleField.size() - 2).compareTo(battleField.get(battleField.size() - 1));
            }

            switch (result) {
            case 1:
                playerWinRound(firstPlayer);
                break;
            case 0:
                if (firstPlayer.getHand().size() + firstPlayer.getWinPot().size() == 2 
                || secondPlayer.getHand().size() + secondPlayer.getWinPot().size() == 2) {
                    isGameWon = false;
                    break;
                   
                } else {
                    handleDraw();
                    break;
                }
                
            case -1:
                playerWinRound(secondPlayer);
                break;
            }
            endRound = false;

        }

    }

    public void playGame() {
        while (isGameWon) {
            dragCardsFromPlayers();
            evaluateRound();
        }
    }

    private void createNewDeck() {
        for (int suit = 1; suit < 5; suit++) {
            for (int rank = 1; rank < 7; rank++) {
                if (suit == SPADES || suit == HEARTS)
                    card = new Card(suit, rank, RED);
                else
                    card = new Card(suit, rank, BLACK);
            }
        }

        Collections.shuffle(Card.getCards());
    }

}