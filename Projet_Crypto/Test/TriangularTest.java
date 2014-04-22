import org.junit.Before;
import org.junit.Test;
import project_crypto.Models.Triangular;
import static org.junit.Assert.*;

/**
 * Created by nicolas on 26/03/14.
 */
public class TriangularTest {

    private Triangular triangular;

    @Before
    public void Setup()
    {
        this.triangular = new Triangular();
    }

    @Test
    public void TestSortAlphebetaOrder()
    {
        String textToSort = "NICOLAS";
        String sortedText = "ACILNOS";
        assertEquals(sortedText, this.triangular.SortAlphaBetaOrder(textToSort));
    }

    @Test
    public void TestDuplicatedLetters()
    {
        String textToTest = "BONJOUR";
        String result = "BONJUR";
        assertEquals(result, this.triangular.RemoveDuplicatedLetters(textToTest));
    }


    @Test
    public void TestCalculateNbLine()
    {
        int nbLetters = 10;
        int result = 4;
        assertEquals(result, this.triangular.CalculateNbLines(nbLetters));
    }


    @Test
    public void TestCalculateNbColumns()
    {
        int nbLines = 10;
        int nbLetters = 30;
        int result = 18;
        assertEquals(result, this.triangular.CalculateNbColumns(nbLines, nbLetters));
    }

    @Test
    public void TestCrypting()
    {
        String textToCrypt = "MARTIN";
        String cryptedText = "AMITRN";
        this.triangular.Crypting(textToCrypt, "test");
        assertEquals(cryptedText, this.triangular.GetEncryptedString());
    }

    @Test
    public void TestUncrypting()
    {
        String textToUncrypt = "JUORBON";
        String uncryptedText = "BONJOUR";
        this.triangular.Uncrypting(textToUncrypt, "test");
        assertEquals(uncryptedText, this.triangular.GetUncryptedString());
    }

}
