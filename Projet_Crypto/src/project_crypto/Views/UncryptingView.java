package project_crypto.Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by kimsavinfo on 17/05/14.
 */
public class UncryptingView extends JPanel
{
    private AlphabetJTable alphaTable;
    private JButton m_cryptButton;
    private JTextArea m_uncryptedTextArea;


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

        JButton saveInFileButton = new JButton("SAVE");
        saveInFileButton.addActionListener(new SaveCryiptingTextInFile());
        this.add(saveInFileButton);


        this.setVisible(true);
    }

    public AlphabetJTable getAlphaTable()
    {
        return alphaTable;
    }

    public void setM_uncryptedTextArea(String p_text)
    {
        m_uncryptedTextArea.setText(p_text);
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

                // return this to try to uncryot with user choice
                System.out.println(alphabeTryUser);
            }
        }
    }

    class SaveCryiptingTextInFile implements ActionListener {
        public void actionPerformed(ActionEvent p_actionEvent) {
            System.out.println("Sauvegarde du texte : " + m_uncryptedTextArea.getText());
        }
    }
}
