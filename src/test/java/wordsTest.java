import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class wordsTest {

    @Test
    public void randomCategoryTest() {
        Words words = new Words();
        String word = Words.randomWord(Words.getCountries());
        assertTrue(Arrays.asList(words.getCountriesList()).contains(word));
    }

    @Test
    public void randomWordsourceTest() {
        assertEquals(null, Words.randomWord("badword.txt"));
        assertEquals(null, Words.randomWord("badword2.txt"));
    }

}