import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class wordsTest {

    @Test
    public void randomCategoryTest() {
        Words words = new Words();
        String word1 = Words.randomWord(Words.getCountries());
        assertTrue(Arrays.asList(words.getCountriesList()).contains(word1));
        String word2 = Words.randomWord(Words.getCounties());
        assertTrue(Arrays.asList(words.getCountiesList()).contains(word2));
        String word3 = Words.randomWord(Words.getCities());
        assertTrue(Arrays.asList(words.getCitiesList()).contains(word3));
    }


    @Test
    public void randomWordsourceTest() {
        assertEquals(null, Words.randomWord("badword.txt"));
        assertEquals(null, Words.randomWord("badword2.txt"));
    }

}