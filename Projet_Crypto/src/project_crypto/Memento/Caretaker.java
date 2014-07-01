package project_crypto.Memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Autheur : kimsavinfo
 * Date : 08:21 30 juin 2014
 */
public class Caretaker
{
    private List<Memento> m_statesList = new ArrayList<Memento>();

    public void AddMemento(Memento m) {
        m_statesList.add(m);
    }

    public Memento GetMemento(int index) {
        return m_statesList.get(index);
    }

    public String GetStatesForExport()
    {
        String toExport = "";

        for (Memento memento : m_statesList)
        {
            toExport += memento.GetState()+"\n";
        }

        return toExport;
    }
}
