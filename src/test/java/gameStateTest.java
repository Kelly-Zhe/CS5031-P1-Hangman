import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class gameStateTest {

    @Test
    public void wordShowTest() {
        GameState gameState = new GameState("England", 10, 2);
        gameState.guessedLetter.add('E');
        gameState.guessedLetter.add('a');
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        gameState.showWord("England");
        assertThat(out.toString(), containsString("E---a--"));
    }

    @Test
    public void noHintsLeftTest() {
        GameState gameState = new GameState("England", 10, 0);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        gameState.hint();
        assertThat(out.toString(), containsString("No more hints allowed"));
    }

    @Test
    public void gameWonTest() {
        GameState gameState = new GameState("England", 10, 0);
        gameState.notGuessedLetter.clear();
        assertTrue(gameState.won());
    }

    @Test
    public void gameLostTest() {
        GameState gameState = new GameState("England", 10, 0);
        gameState.guessesWrong = 0;
        assertTrue(gameState.lost());
    }
}