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
    public void SumFrequencyAnalyseCharEn()
    {
        assertEquals(99.66, this.spoken.SumFrequenceMostUsedCharInEnglish("GTKNIXGIMLCMHTOZRIUQKIGYJMUTG"));
    }


    @Test
    public void SumFrequencyAnalyseCharFr()
    {
        assertEquals(139.76, this.spoken.SumFrequenceMostUsedCharInEnglish("EALCIAPTNVIEUSOSUAOHTUIEBNNOOXVL"));
    }
}
