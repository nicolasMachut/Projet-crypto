import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import project_crypto.Models.Caesar;

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
    public void Test()
    {
        Assert.assertTrue(true);
    }

}
