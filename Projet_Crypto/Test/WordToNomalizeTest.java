import Library.WordToNormalize;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by nicolas on 07/04/14.
 */
public class WordToNomalizeTest {

    private WordToNormalize normalize;

    @Before
    public void Setup()
    {
        this.normalize = new WordToNormalize();
    }


    @Test
    public void TestEraseSpecialChar()
    {
        assertEquals("CESTNICOLASQUIVOUSAPARLE", this.normalize.normalize("c'est nicolas qui vous à parl_é"));
    }
}