import Library.LanguageDetection;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
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
        Map<String, Double> analyseLangue = this.spoken.SumFrequenceMostUsedCharInLanguage("GTKNIXGIMLCMHTOZRIUQKIGYJMUTG");

        assertEquals(88.11999999999998, analyseLangue.get("fr"));
        assertEquals(99.66, analyseLangue.get("en"));
    }


    @Test
    public void SumFrequencyAnalyseCharFr()
    {
        Map<String, Double> analyseLangue = this.spoken.SumFrequenceMostUsedCharInLanguage("EALCIAPTNVIEUSOSUAOHTUIEBNNOOXVL");

        assertEquals(131.09, analyseLangue.get("en"));
        assertEquals(139.76, analyseLangue.get("fr"));
    }
}
