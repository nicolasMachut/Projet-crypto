import org.junit.Test;
import project_crypto.Models.Crypting;
import static org.junit.Assert.*;

/**
 * Created by nicolas on 27/03/14.
 */
public class CryptingTest {

    @Test
    public void TestModuloPositiveWithNegativeNumber()
    {
        assertEquals(20, Crypting.ModuloPositive(-10, 30));
    }

    @Test
    public void TestModuloPositiveWithPositiveNumber()
    {
        assertEquals(10, Crypting.ModuloPositive(10, 30));
    }
}
