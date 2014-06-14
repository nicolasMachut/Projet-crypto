package project_crypto.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 *
 * Date :17/05/14.
 */
public class UncryptingView extends JPanel
{
    private AlphabetJTable alphaTable;
    private JButton m_cryptButton;
    private JTextArea m_uncryptedTextArea;
    private JButton m_exportButton;


    public UncryptingView()
    {
        alphaTable = new AlphabetJTable();
        JScrollPane scrollPaneTable = new JScrollPane(alphaTable);
        Dimension d = alphaTable.getPreferredSize();
        scrollPaneTable.setPreferredSize(new Dimension(d.width, alphaTable.getRowHeight() * 3 + 1));
        Border noBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        scrollPaneTable.setBorder(noBorder);
        scrollPaneTable.setViewportBorder(null);
        this.add(scrollPaneTable);

        m_cryptButton = new JButton("TRY");
        m_cryptButton.setPreferredSize(new Dimension(Global.m_widthWindow - 40, 30));
        m_cryptButton.addActionListener(new CryptActions());
        this.add(m_cryptButton);

        m_uncryptedTextArea = new JTextArea(20 , 60);
        JScrollPane scrollPaneTextArea = new JScrollPane(m_uncryptedTextArea);
        m_uncryptedTextArea.setEditable(false);
        this.add(scrollPaneTextArea);

        m_exportButton = new JButton("EXPORT");
        this.add(m_exportButton);


        this.setVisible(true);
    }

    public AlphabetJTable getAlphaTable()
    {
        return alphaTable;
    }

    public void setUncryptedTextArea(String p_text)
    {
        m_uncryptedTextArea.setText(p_text);
    }

    public String GetUncryptedText()
    {
        return m_uncryptedTextArea.getText();
    }

    /* ===============================================================================================================
* ActionListeners
* ============================================================================================================ */

    class CryptActions implements ActionListener
    {
        public void actionPerformed(ActionEvent p_actionEvent)
        {
            //Handle open button action.
            if (p_actionEvent.getSource() == m_cryptButton)
            {
                List<String> alphabeTryUser = alphaTable.getAlphabetTryUser();

                System.out.println(alphabeTryUser);
            }
        }
    }

    public void AddExportButtonListener(ActionListener p_actionListener)
    {
        m_exportButton.addActionListener(p_actionListener);
    }
}