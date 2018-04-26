public class Score {
    private String playerName;
    private int playerScore;

    Score(String playerName, int playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    /**
     * @return the playerName
     */
    protected String getPlayerName() {
        return playerName;
    }


    /**
     * @return the playerScore
     */
    protected int getPlayerScore() {
        return playerScore;
    }

    @Override
    public String toString() {
        return String.format("%s, %d", playerName,playerScore);
    }
}