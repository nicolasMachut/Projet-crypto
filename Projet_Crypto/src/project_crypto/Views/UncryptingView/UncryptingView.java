package project_crypto.Views.UncryptingView;

import project_crypto.Views.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Autheur : kimsavinfo
 * Date : 08:18 27 juin 2014
 */
public class UncryptingView extends JPanel
{
    protected final ButtonGroup m_languageRadio;
    protected final JRadioButton m_radioFr;
    protected final JRadioButton m_radioEn;
    protected final JButton m_tryUncriptButton;
    protected JTextArea m_uncryptedTextArea;
    protected JTextArea m_cryptedTextArea;
    protected final JButton m_exportButton;


    public UncryptingView()
    {
        m_languageRadio = new ButtonGroup();
        m_radioFr = new JRadioButton("(60%) fr");
        m_languageRadio.add(m_radioFr);
        m_radioEn = new JRadioButton("en (40%)");
        m_languageRadio.add(m_radioEn);

        m_tryUncriptButton = new JButton("TRY");
        m_tryUncriptButton.setPreferredSize(new Dimension(Global.m_widthWindow - 40, 30));

        m_cryptedTextArea = new JTextArea(Global.m_heightScrollArea, Global.m_widthScrollArea );
        m_cryptedTextArea.setEditable(false);

        m_uncryptedTextArea = new JTextArea(Global.m_heightScrollArea, Global.m_widthScrollArea );
        m_uncryptedTextArea.setEditable(false);

        m_exportButton = new JButton("EXPORT");
    }

    protected void showComponents()
    {
        this.add(m_radioFr);
        this.add(m_radioEn);

        this.add(m_tryUncriptButton);
        JScrollPane scrollPaneCryptedTextArea = new JScrollPane(m_cryptedTextArea);
        this.add(scrollPaneCryptedTextArea);
        JScrollPane scrollPaneUncryptedTextArea = new JScrollPane(m_uncryptedTextArea);
        this.add(scrollPaneUncryptedTextArea);

        this.add(m_exportButton);

        this.setVisible(true);
    }


    public void setCryptedTextArea(String p_text)
    {
        m_cryptedTextArea.setText(p_text);
    }

    protected void setUncryptedTextArea(String p_uncryptedText)
    {
        m_uncryptedTextArea.setText(p_uncryptedText);
    }

    public String GetUncryptedText()
    {
        return m_uncryptedTextArea.getText();
    }

    public void AddExportButtonListener(ActionListener p_actionListener)
    {
        m_exportButton.addActionListener(p_actionListener);
    }

    protected String getLangUserChoose()
    {
        String lang = "fr";

        if(m_radioFr.isSelected())
        {
            lang = "fr";
        } else if(m_radioEn.isSelected())
        {
            lang = "en";
        }

        return lang;
    }
}
