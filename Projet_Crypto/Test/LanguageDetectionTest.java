import Library.LanguageDetection;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by edouard on 19/04/14.
 */
public class LanguageDetectionTest {

    private LanguageDetection spoken;
    @Before
    public void SetUp()
    {
        this.spoken = new LanguageDetection();
    }

    @Test
    public void SumFrequencyAnalyseChar()
    {
        assertEquals(29.74, this.spoken.SumFrequenceMostUsedCharInEnglish("GTKNIXGIMLCMHTOZRIUQKIGYJMUTG"));
    }


}
