import Library.FrequencyAnalyse;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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
}
