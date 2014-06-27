import Library.WordToNormalize;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 *
 * Date: 07/04/14.
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

    @Test
    public void TestNumber()
    {
        assertEquals("1234567890", this.normalize.normalizeNumber(" 1cdsppokdsc sh2 sld3 lfooaz 4pvpoozev 5pze:!;az 6 =+ 7° &8 <9 >0"));
    }
}