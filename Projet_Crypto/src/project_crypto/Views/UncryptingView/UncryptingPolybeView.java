package project_crypto.Views.UncryptingView;

import project_crypto.Models.Polybe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Autheur : kimsavinfo
 * Date : 52:19 27 juin 2014
 */
public class UncryptingPolybeView extends UncryptingView
{
    private Polybe m_polybe;

    public UncryptingPolybeView(String p_language)
    {
        super(p_language);

        m_polybe = new Polybe(p_language);
        m_tryUncriptButton.addActionListener(new UncryptActions());

        ShowComponents();
    }

    @Override
    public String GetLogsToExport()
    {
        return m_polybe.ExportKeyLog();
    }

    private void UpdatePolybe()
    {
        m_polybe.Uncrypting(m_cryptedTextArea.getText());

        SetUncryptedTextArea(m_polybe.GetReadableString());
    }

    public Polybe GetPolybe()
    {
        return  m_polybe;
    }

    /* ===============================================================================================================
    * ActionListeners
    * ============================================================================================================ */
    class UncryptActions implements ActionListener
    {
        public void actionPerformed(ActionEvent p_actionEvent)
        {
            //Handle open button action.
            if (p_actionEvent.getSource() == m_tryUncriptButton)
            {
                m_polybe.SetAssociation(getLangUserChoose());

                UpdatePolybe();
            }
        }
    }
}
