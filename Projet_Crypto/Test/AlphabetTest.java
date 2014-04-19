import Library.Alphabet;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by nicolas on 19/04/14.
 */
public class AlphabetTest {

    private Alphabet alphabet;
    @Before
    public void Setup()
    {
        this.alphabet = new Alphabet();
    }

    @Test
    public void TestGetIndexOfLetter()
    {
        this.alphabet.SetLanguage("fr");
        assertEquals(4, this.alphabet.GetIndexOfALetterInAlphabet("E"));
        assertEquals(0, this.alphabet.GetIndexOfALetterInAlphabet("A"));
    }
}
