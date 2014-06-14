import Library.TextFileManager;
import Library.WordToNormalize;
import org.junit.Before;
import org.junit.Test;
import project_crypto.Models.Triangular;
import static org.junit.Assert.assertEquals;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 * Date : 26/03/14.
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
    public void TestCryptingLigneComplete()
    {
        String textToCrypt = "JAVAEE";
        String cryptedText = "AJEAVE";
        this.triangular.Crypting(textToCrypt, "test");
        assertEquals(cryptedText, this.triangular.GetEncryptedString());
    }

    @Test
    public void TestCryptingLigneIncomplete()
    {
        String textToCrypt = "JAVA";
        this.triangular.Crypting(textToCrypt, "test");
        assertEquals(6, this.triangular.GetEncryptedString().length());
    }

    @Test
    public void TestUncrypting()
    {
        String textToUncrypt = "LECODAGEPARTRANSPOSITIONTRIANGULAIRE";
        this.triangular.Crypting(textToUncrypt, "codage");

        String uncryptedText = "LECODAGEPARTRANSPOSITIONTRIANGULAIRE";
        assertEquals(uncryptedText, this.triangular.GetReadableString());
    }

    @Test
    public void TestCryptingLongTxt()
    {
        TextFileManager m_textFileManager = new TextFileManager();
        m_textFileManager.LoadFile("/home/kimsavinfo/IdeaProjects/Projet-crypto/Projet_Crypto/src/Test_TextFiles/NotreDameParis_clair.txt");
        String uncryptedText =  new WordToNormalize().normalize( m_textFileManager.getText() );
        String key = "epsi";

        Triangular triangular = new Triangular();
        triangular.Crypting(m_textFileManager.getText(), key);

        this.triangular.Uncrypting(triangular.GetEncryptedString(), key);

        assertEquals(uncryptedText, this.triangular.GetReadableString().substring(0, uncryptedText.length()));
    }

}
