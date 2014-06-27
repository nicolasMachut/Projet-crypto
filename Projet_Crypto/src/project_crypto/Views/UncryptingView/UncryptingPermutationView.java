package project_crypto.Views.UncryptingView;

import project_crypto.Models.Permutation;
import project_crypto.Views.AlphabetJTable;

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
    private AlphabetJTable m_alphaTable;
    private Permutation m_permutation;

    public UncryptingPermutationView()
    {
        super();

        m_alphaTable = new AlphabetJTable();
        JScrollPane scrollPaneTable = new JScrollPane(m_alphaTable);
        Dimension dimension = m_alphaTable.getPreferredSize();
        scrollPaneTable.setPreferredSize(new Dimension(dimension.width, m_alphaTable.getRowHeight() * 3 + 1));
        Border noBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        scrollPaneTable.setBorder(noBorder);
        scrollPaneTable.setViewportBorder(null);
        this.add(scrollPaneTable);

        m_permutation = new Permutation();

        m_tryUncriptButton.addActionListener(new UncryptActions());

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
            if (p_actionEvent.getSource() == m_tryUncriptButton)
            {
                // TODO : adapter avec getLangUserChoose()

                List<String> alphabeTryUser = m_alphaTable.getAlphabetTryUser();

                updatePermutation(m_cryptedTextArea.getText(), alphabeTryUser);

                setUncryptedTextArea(m_permutation.GetReadableString());
            }
        }
    }
}