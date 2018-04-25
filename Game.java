import java.util.List;
import java.util.ArrayList;

public class Game {
    private Player player1 = new Player();
    private Player player2 = new Player();
    private Deck deck = new Deck();
    private List<Card> startDeck = new ArrayList<>();
    private List<Card> player1HandDeck = new ArrayList<>();
    private List<Card> player2HandDeck = new ArrayList<>();
    private List<Card> player1WinPotDeck = new ArrayList<>();
    private List<Card> player2WinPotDeck = new ArrayList<>();
    private List<Card> battleField = new ArrayList<>();


    public void dealCards(Player player1, Player player2){
        startDeck = deck.createNewDeck();
        player1HandDeck = deck.createDeckForPlayer(startDeck);
        player2HandDeck = deck.createDeckForPlayer(startDeck);
    }
    
        
    public boolean isGameWon(){
        // create conditions to win game
        return true;
    }


    public void roundOfGame(){
        // create conditions to start round
        // check here method evaluableRound
    }

    public void dragCardToBattleField(){
        int firstElement = 0;

        battleField.add(player1HandDeck.get(firstElement));
        battleField.add(player2HandDeck.get(firstElement));

        player1HandDeck.remove(firstElement);
        player2HandDeck.remove(firstElement);
    }


    public void dragCardWhenDraw(){
        System.out.println("Round draw");
        for (int i = 0; i < 2; i++)
            dragCardToBattleField();
    }


    public void runFightMode(){
        while (battleField.size() != 0){
            int lastCardIndex = battleField.size() - 1;
            int beforeLastCardIndex = battleField.size() - 2;

            if (battleField.size() == 2){
                if (battleField.get(0).getRank() > battleField.get(1).getRank()){
                    playerWinRound(player1HandDeck);
                    
                } else if (battleField.get(0).getRank() < battleField.get(1).getRank()){
                    playerWinRound(player2HandDeck);

                } else {    
                    dragCardWhenDraw();

                }
            } else{
                if (battleField.get(beforeLastCardIndex).getRank() > battleField.get(lastCardIndex).getRank()){
                    playerWinRound(player1HandDeck);
                } else if (battleField.get(beforeLastCardIndex).getRank() < battleField.get(lastCardIndex).getRank()){
                    playerWinRound(player2HandDeck);
                } else {
                    dragCardWhenDraw();
                }
            }
        }
    }


    public void playerWinRound(List<Card> potDeck){

        for (int i = 0; i < battleField.size(); i++){
            potDeck.add(battleField.get(i));
        }
        battleField.clear();
    }


    public void evaluableRound(){
        // create conditions to win round or fight
    }


    public void playGame(){
        boolean gameRun = true;

        while (gameRun){
            if (player1HandDeck.size() == 0 || player2HandDeck.size() == 0){
                if (isGameWon()){
                    System.out.println("You win the game!");
                    gameRun = false;
                } else {
                    System.out.println("You lose the game!");
                    gameRun = false;
                }
            } else {
                roundOfGame();
            }

        }
    }
    
}