import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScore implements Comparable<HighScore> {
    private String name;
    private int score;
    
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() { return name; }
    public int getScore() { return score; }
    
    public static List<HighScore> readHighScores(String filename) throws IOException {
        LineNumberReader reader = new LineNumberReader(new FileReader(filename));
        List<HighScore> highScores = new ArrayList<HighScore>();
        String string;
        while ((string = reader.readLine()) != null) {
            String strings[] = string.split(": ");
            if (strings.length != 2) continue;
            try {
                highScores.add(new HighScore(strings[0], Integer.parseInt(strings[1])));
            } catch (NumberFormatException e) {
                continue;
            }
        }
        Collections.sort(highScores);
        Collections.reverse(highScores);
        return highScores;
    }
    
    public void save(String filename) throws IOException {
        Writer writer = new FileWriter(filename, true);
        writer.write(getName() + ": " + getScore() + "\n");
        writer.close();
    }
    
    public int compareTo(HighScore other) {
        return score - other.score;
    }
}
