import org.junit.Before;
import org.junit.Test;
import project_crypto.Models.Caesar;

import static org.junit.Assert.*;

/**
 * Created by nicolas on 26/03/14.
 */
public class CaesarTest {

    private Caesar caesar;

    @Before
    public void Setup()
    {
        this.caesar = new Caesar();
    }

    @Test
    public void TestCryptingCaesar()
    {
        int cryptingKey = 2;
        String textToCrypt = "CESTUNTEST";
        String CryptedString = "EGUVWPVGUV";
        this.caesar.Crypting(textToCrypt, cryptingKey);
        assertEquals(CryptedString, this.caesar.GetEncryptedString());
    }

    @Test
    public void TestUncryptingCaesarWhenEIsTheMostUsedLetter()
    {
        int cryptingKey = 2;
        String textToDecrypt = "EGUVWPVGUVGGG";
        String decryptedText = "CESTUNTESTEEE";
        this.caesar.Uncrypting(textToDecrypt);
        assertEquals(decryptedText, this.caesar.GetUncryptedString());
    }

    @Test
    public void TestGetCryptingKey()
    {
        assertEquals(0, this.caesar.GetCryptingKey("EGUVWPVGUV"));
    }
}
