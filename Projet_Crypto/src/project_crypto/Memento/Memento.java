package project_crypto.Memento;

/**
 * Autheur : kimsavinfo
 * Date : 15:21 30 juin 2014
 */
public class Memento
{
    private String m_state;

    public Memento(String m_state){
        this.m_state = m_state;
    }

    public String GetState() {
        return m_state;
    }
}
