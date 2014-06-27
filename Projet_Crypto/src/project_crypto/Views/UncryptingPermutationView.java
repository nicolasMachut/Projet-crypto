package project_crypto.Views;

import project_crypto.Models.Permutation;

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

public class UncryptingPermutationView extends UncryptingView
{
    private AlphabetJTable alphaTable;
    private Permutation m_permutation;

    public UncryptingPermutationView()
    {
        super();

        alphaTable = new AlphabetJTable();
        JScrollPane scrollPaneTable = new JScrollPane(alphaTable);
        Dimension dimension = alphaTable.getPreferredSize();
        scrollPaneTable.setPreferredSize(new Dimension(dimension.width, alphaTable.getRowHeight() * 3 + 1));
        Border noBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        scrollPaneTable.setBorder(noBorder);
        scrollPaneTable.setViewportBorder(null);
        this.add(scrollPaneTable);

        m_permutation = new Permutation();

        m_cryptButton.addActionListener(new UncryptActions());

        showComponents();
    }

    private void updatePermutation(String p_textToUncrypt, List<String> alphabeTryUser)
    {
        m_permutation.Uncrypting(p_textToUncrypt, alphabeTryUser);
    }

    /* ===============================================================================================================
    * ActionListeners
    * ============================================================================================================ */
    class UncryptActions implements ActionListener
    {
        public void actionPerformed(ActionEvent p_actionEvent)
        {
            //Handle open button action.
            if (p_actionEvent.getSource() == m_cryptButton)
            {
                List<String> alphabeTryUser = alphaTable.getAlphabetTryUser();

                updatePermutation(m_cryptedTextArea.getText(), alphabeTryUser);

                setUncryptedTextArea(m_permutation.GetReadableString());
            }
        }
    }
}






/*
public class UncryptingPermutationView extends JPanel
{
    private AlphabetJTable alphaTable;
    private JButton m_cryptButton;
    private JTextArea m_uncryptedTextArea;
    private JTextArea m_cryptedTextArea;
    private JButton m_exportButton;

    private Permutation m_permutation;


    public UncryptingPermutationView()
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
        m_cryptButton.addActionListener(new UncryptActions());
        this.add(m_cryptButton);

        m_cryptedTextArea = new JTextArea(Global.m_heightScrollArea, Global.m_widthScrollArea );
        m_cryptedTextArea.setEditable(false);
        JScrollPane scrollPaneCryptedTextArea = new JScrollPane(m_cryptedTextArea);
        this.add(scrollPaneCryptedTextArea);

        m_uncryptedTextArea = new JTextArea(Global.m_heightScrollArea, Global.m_widthScrollArea );
        m_uncryptedTextArea.setEditable(false);
        JScrollPane scrollPaneUncryptedTextArea = new JScrollPane(m_uncryptedTextArea);
        this.add(scrollPaneUncryptedTextArea);

        m_exportButton = new JButton("EXPORT");
        this.add(m_exportButton);

        m_permutation = new Permutation();

        this.setVisible(true);
    }

    public AlphabetJTable getAlphaTable()
    {
        return alphaTable;
    }

    public void setCryptedTextArea(String p_text)
    {
        m_cryptedTextArea.setText(p_text);
    }

    private void updatePermutation(String p_textToUncrypt, List<String> alphabeTryUser)
    {
        m_permutation.Uncrypting(p_textToUncrypt, alphabeTryUser);
    }

    private void setUncryptedTextArea(String p_uncryptedText)
    {
        m_uncryptedTextArea.setText(p_uncryptedText);
    }

    public String GetUncryptedText()
    {
        return m_uncryptedTextArea.getText();
    }
    */

/* ===============================================================================================================
* ActionListeners
* ============================================================================================================ */
/*
    class UncryptActions implements ActionListener
    {
        public void actionPerformed(ActionEvent p_actionEvent)
        {
            //Handle open button action.
            if (p_actionEvent.getSource() == m_cryptButton)
            {
                List<String> alphabeTryUser = alphaTable.getAlphabetTryUser();

                updatePermutation(m_cryptedTextArea.getText(), alphabeTryUser);

                setUncryptedTextArea(m_permutation.GetReadableString());
            }
        }
    }

    public void AddExportButtonListener(ActionListener p_actionListener)
    {
        m_exportButton.addActionListener(p_actionListener);
    }
}
*/