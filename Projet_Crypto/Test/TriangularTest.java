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
    public void testSortAlphebetaOrder()
    {
        String textToSort = "NICOLAS";
        String sortedText = "ACILNOS";
        assertEquals(sortedText, this.triangular.SortAlphaBetaOrder(textToSort));
    }

    @Test
    public void testDuplicatedLetters()
    {
        String textToTest = "BONJOUR";
        String result = "BONJUR";
        assertEquals(result, this.triangular.RemoveDuplicatedLetters(textToTest));
    }


}
