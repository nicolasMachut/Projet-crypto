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
    private Polybe polybe;

    public UncryptingPolybeView(String p_language)
    {
        super();

        polybe = new Polybe(p_language);
        m_tryUncriptButton.addActionListener(new UncryptActions());

        showComponents();
    }

    private void updatePolybe()
    {
        polybe.Uncrypting(m_cryptedTextArea.getText());

        setUncryptedTextArea(polybe.GetReadableString());
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
                // TODO : adapter avec getLangUserChoose()
                updatePolybe();
            }
        }
    }
}
