package project_crypto.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Autheur : kimsavinfo
 * Date : 08:18 27 juin 2014
 */
public class UncryptingView extends JPanel
{
    protected JButton m_cryptButton;
    protected JTextArea m_uncryptedTextArea;
    protected JTextArea m_cryptedTextArea;
    protected JButton m_exportButton;


    public UncryptingView()
    {
        m_cryptButton = new JButton("TRY");
        m_cryptButton.setPreferredSize(new Dimension(Global.m_widthWindow - 40, 30));

        m_cryptedTextArea = new JTextArea(Global.m_heightScrollArea, Global.m_widthScrollArea );
        m_cryptedTextArea.setEditable(false);

        m_uncryptedTextArea = new JTextArea(Global.m_heightScrollArea, Global.m_widthScrollArea );
        m_uncryptedTextArea.setEditable(false);

        m_exportButton = new JButton("EXPORT");
    }

    protected void showComponents()
    {
        this.add(m_cryptButton);
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
}
