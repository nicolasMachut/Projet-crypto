import Library.FrequencyAnalyse;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by nicolas on 28/03/14.
 */
public class FrequencyAnalyseTest {

    private FrequencyAnalyse analyse;

    @Before
    public void Setup()
    {
        this.analyse = new FrequencyAnalyse("LABALEINEESTECHOUEEESTMORTESURLAPLAGE");
    }

    @Test
    public void TestIfThereIsThreeSameLetterAside()
    {
        Assert.assertTrue(this.analyse.CheckIfThereIsThreeSameLetterAside());
    }

    @Test
    public void TestCharPresence()
    {
        assertEquals(9.0, this.analyse.CharPresence().get("E"));
    }

    @Test
    public void TestGetMostUsedChar()
    {
        assertEquals("E", this.analyse.getMostUsedChar(this.analyse.CalculCharFrequency()));
    }

    @Test
    public void TestCountCharacters()
    {
        assertEquals(37, this.analyse.CountCharacters());
    }

    @Test
    public void TestCalculCharFrequency()
    {
        assertEquals(10.810810810810811, this.analyse.CalculCharFrequency().get("L"));
    }

}
