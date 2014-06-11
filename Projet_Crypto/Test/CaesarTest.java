import org.junit.Before;
import org.junit.Test;
import project_crypto.Models.Caesar;

import static org.junit.Assert.*;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 *
 * Date: 26/03/14.
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
        String textToDecrypt = "EGUVWPVGUVGGG";
        String decryptedText = "CESTUNTESTEEE";
        this.caesar.Uncrypting(textToDecrypt);
        assertEquals(decryptedText, this.caesar.GetUncryptedString());
    }

    @Test
    public void TestGetCryptingKey()
    {
        assertEquals(2, this.caesar.GetCryptingKey("EGUVWPVGUV"));
    }

    @Test
    public void TestUncryptingCaesarManual()
    {
        int cryptingKey = 2;
        String textToDecrypt = "EGUVWPVGUVGGG";
        String decryptedText = "CESTUNTESTEEE";
        this.caesar.Uncrypting(textToDecrypt, cryptingKey);
        assertEquals(decryptedText, this.caesar.GetUncryptedString());
    }
}
