package project_crypto.Memento;

/**
 * Autheur : kimsavinfo
 * Date : 08:21 30 juin 2014
 *
 */
public class Originator
{
    private String m_state;

    public void SetState(String state) {
        this.m_state = state;
    }

    public String GetState() {
        return m_state;
    }

    public Memento CreateMemento() {
        return new Memento(m_state);
    }

    /* Serait utilisé via IA et arbre de décisions
    Si demande à user, est ce qu'on se rapproche : oui/non revenir sur ses décisions
    public void SetMemento(Memento memento) {

        m_state = memento.GetState();
    }
    */
}
