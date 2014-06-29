import org.junit.Before;
import org.junit.Test;
import project_crypto.Models.Polybe;
import static org.junit.Assert.*;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 * Date : 26/03/14.
 */
public class PolybeTest {

    private Polybe polybe;

    @Before
    public void Setup()
    {
        this.polybe = new Polybe("fr");
    }

    @Test
    public void TestPolybeCrypting()
    {
        String textToCrypt = "CESTUNTESTEEE";

        //cryptedText must be changed if hashMap association in Polybe class is changed
        String cryptedText = "13154445513445154445151515";

        this.polybe.Crypting(textToCrypt);
        assertEquals(cryptedText, this.polybe.GetEncryptedString());
    }

    @Test
    public void TestPolybeUncrypting()
    {
        String textToUncrypt = "13154445513445154445151515";

        String uncryptedText = "CESTUNTESTEEE";

        this.polybe.Uncrypting(textToUncrypt);
        assertEquals(uncryptedText, this.polybe.GetReadableString());
    }

    @Test
    public void TestPolybeUncryptingLittle()
    {
        String textToUncrypt = "44113444";

        String uncryptedText = "SANS";

        this.polybe.Uncrypting(textToUncrypt);
        assertEquals(uncryptedText, this.polybe.GetReadableString());
    }

    @Test
    public void TestPutEachNumbersInArrayString()
    {
        this.polybe.Crypting("BCDE");

        String result[] = {"12", "13", "14", "15"};
        for(int i = 0; i < result.length; i++)
        {
            assertEquals(result[i], this.polybe.PutEachNumbersInArrayStrings()[i]);
        }
    }

    @Test
    public void TestNormalizeLettersForCryptingFr()
    {
        String textToCrypt = "Wapiti";

        polybe.setAssociation("fr");
        polybe.Crypting(textToCrypt);
        // NormalizeLettersForCrypting retourne VAPITI

        assertEquals("521141244524", polybe.GetEncryptedString());
    }

    @Test
    public void TestNormalizeLettersForCryptingEn()
    {
        String textToCrypt = "Jelly";

        polybe.setAssociation("en");
        polybe.Crypting(textToCrypt);
        // NormalizeLettersForCrypting retourne IELLY

        assertEquals("2415313154", polybe.GetEncryptedString());
    }
}
