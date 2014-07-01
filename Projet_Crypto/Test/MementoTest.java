import org.junit.Before;
import org.junit.Test;
import project_crypto.Memento.Caretaker;
import project_crypto.Memento.Originator;
import project_crypto.Models.Caesar;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Autheur : kimsavinfo
 * Date : 20:21 01 juillet 2014
 */
public class MementoTest
{
    private Caretaker caretaker;
    private Originator originator;
    private String txt;

    @Before
    public void Setup()
    {
        caretaker = new Caretaker();
        originator = new Originator();
        txt = "Le capitaine vous souhaite un bon vol";
    }

    @Test
    public void testCaesar()
    {
        Caesar caesar = new Caesar("fr");
        List<String> menetosValue = new ArrayList<String>();

        for (int i=0; i < 10 ; i++)
        {
            caesar.SetNextKeyAuto();
            caesar.Uncrypting(txt);
            originator.SetState( Integer.toString(caesar.GetKeyCaesar()));
            caretaker.AddMemento(originator.CreateMemento());

            menetosValue.add(originator.GetState());
        }

        for (int i=0; i < 10 ; i++) {
            assertEquals(menetosValue.get(i), caretaker.GetMemento(i).GetState());
            //System.out.println(menetosValue.get(i)+" : "+caretaker.GetMemento(i).GetState());
        }
    }

}
