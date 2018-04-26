import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Highscore {
    
    private List<Score> scores;


    protected Highscore() {
       this.scores = new ArrayList<>();
    }


    protected List<Score> getScores() {
        return scores;
    }

    
    protected void addScoreToList(Score score) {
        scores.add(score);
    }


    protected void sortListByPoints() {

        Collections.sort(scores, new Comparator<Score>() {
            @Override
            public int compare(Score s1, Score s2) {
                return s2.getPlayerScore() - s1.getPlayerScore();
            }
        });
    }


    protected void writeScoreListToFile(List<Score> scores, boolean value, String fileName) {
        File file = new File(fileName);
        try {
            FileWriter fw = new FileWriter(file, value);

            for(Score line : scores)
                fw.write(line + "\n");
            fw.close();
        } catch(IOException e) {
            System.out.println("File not found.");
        }
    }
    
    
    protected void readHighscoreFromFile(String fileName) {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            String playerName;
            int playerScore;

            while((line = br.readLine()) != null) {
               playerName = line.split(", ")[0];
               playerScore = Integer.parseInt(line.split(", ")[1]);
               scores.add(new Score(playerName, playerScore));
            }

        } catch(IOException e ) {
            System.out.println("File not found");
        }
    }
}
