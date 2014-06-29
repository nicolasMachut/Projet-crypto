package project_crypto.Views.UncryptingView;

import project_crypto.Models.Caesar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Autheur : kimsavinfo
 * Date : 05:19 27 juin 2014
 */
public class UncryptingCaesarView extends UncryptingView
{
    private Caesar m_caesar;
    private JFormattedTextField m_keyField;

    public UncryptingCaesarView(String p_language)
    {
        super(p_language);

        JLabel keyLabel = new JLabel("Caesar key :");
        this.add(keyLabel);
        m_keyField = new JFormattedTextField("0");
        m_keyField.setPreferredSize(new Dimension(200, 30));
        this.add(m_keyField);

        m_cryptedTextArea.getText();

        m_tryUncriptButton.addActionListener(new UncryptActions());

        m_caesar = new Caesar(p_language);

        ShowComponents();
    }

    public void SetCaesarKeyField(int p_key)
    {
        m_keyField.setText( Integer.toString(p_key) );
    }

    public Caesar GetCaesar()
    {
        return m_caesar;
    }

    private void updateCaesar()
    {
        SetUncryptedTextArea(m_caesar.GetReadableString());
    }

    private boolean isAGoodCeasarKey(String keyToTest)
    {
        Boolean resultat = false;
        if(!keyToTest.isEmpty())
        {
            try
            {
                int intValue = Integer.valueOf(keyToTest);
                if(intValue > 0 && intValue < 26)
                    resultat = true;
            }
            catch(Exception e)
            {
                e.getCause();
            }
        }
        if(!resultat)
            JOptionPane.showMessageDialog(null, "Please, choose a numeric key between 1 and 26.");

        return resultat;
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
                m_caesar.SetLanguage(getLangUserChoose());

                if( isAGoodCeasarKey(m_keyField.getText()) )
                {
                    updateCaesar();

                    m_caesar.Uncrypting(m_cryptedTextArea.getText(), Integer.parseInt(m_keyField.getText()));
                }
            }
        }
    }
}
