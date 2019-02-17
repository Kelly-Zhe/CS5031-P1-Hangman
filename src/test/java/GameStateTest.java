import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class GameStateTest {

//    String dundee = "Dundee";
//    String edinburgh = "Edinburgh";
//    String perthAndKinrosse = "Prth and Kinross";
//    String theUK = "the uk";
//    GameState game,game2;
//    ArrayList<Character> GuessedLetter;

    @Test
    public void wordShowTest() {
//        CommandOpts opts = new CommandOpts(new String[]{});
//        Words words = new Words();
        GameState gameState = new GameState("England", 10, 2);
        gameState.guessedLetter.add('E');
        gameState.guessedLetter.add('a');
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        gameState.showWord("England");
        assertThat(out.toString(),containsString("E---a--"));
    }

    @Test
    public void noHintsLeftTest() {
//        CommandOpts opts = new CommandOpts(new String[]{});
//        Words words = new Words();
        GameState gameState = new GameState("England", 10, 0);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        gameState.hint();
        assertThat(out.toString(),containsString("No more hints allowed"));
    }

    @Test
    public void gameWonTest() {
//        CommandOpts opts = new CommandOpts(new String[]{});
//        Words words = new Words();
        GameState gameState = new GameState("England", 10, 0);
        gameState.notGuessedLetter.clear();
        assertTrue(gameState.won());
    }

    @Test
    public void gameLostTest() {
//        CommandOpts opts = new CommandOpts(new String[]{});
//        Words words = new Words();
        GameState gameState = new GameState("England", 10, 0);
        gameState.GuessesWrong = 0;
        assertTrue(gameState.lost());
    }
}