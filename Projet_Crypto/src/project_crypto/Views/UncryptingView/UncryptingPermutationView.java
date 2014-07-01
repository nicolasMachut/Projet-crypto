package project_crypto.Views.UncryptingView;

import project_crypto.Models.Permutation;
import project_crypto.Views.AlphabetJTable;
import project_crypto.Views.Global;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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
    private JButton m_tryAuto;

    public UncryptingPermutationView(String p_language)
    {
        super(p_language);

        m_tryAuto = new JButton(Global.m_tryUncryptingAuto);
        m_tryAuto.setPreferredSize(new Dimension(Global.m_widthWindow - 40, 30));
        m_tryAuto.addActionListener(new UncryptAutoActions());
        this.add(m_tryAuto);

        m_alphaTable = new AlphabetJTable();
        JScrollPane scrollPaneTable = new JScrollPane(m_alphaTable);
        Dimension dimension = m_alphaTable.getPreferredSize();
        scrollPaneTable.setPreferredSize(new Dimension(dimension.width, m_alphaTable.getRowHeight() * 3 + 1));
        Border noBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        scrollPaneTable.setBorder(noBorder);
        scrollPaneTable.setViewportBorder(null);
        this.add(scrollPaneTable);

        m_permutation = new Permutation(p_language);

        m_tryUncriptButton.addActionListener(new UncryptActions());

        ShowComponents();
    }

    @Override
    public String GetLogsToExport() {
        return m_permutation.ExportKeyLog();
    }

    private void UpdatePermutation(String p_textToUncrypt, List<String> alphabeTryUser)
    {
        m_permutation.SetLanguageAlphabetUsed(getLangUserChoose());
        m_permutation.Uncrypting(p_textToUncrypt, alphabeTryUser);

        SetUncryptedTextArea(m_permutation.GetReadableString());

        m_permutation.SetAssociationUncrypt(alphabeTryUser);
        SetDataRowAlphaTable(m_permutation.GetAssociation());
    }

    public Permutation GetPermutation()
    {
        return m_permutation;
    }

    public void SetDataRowAlphaTable(HashMap<String, String> p_association)
    {
        String associationString = "";

        String[] alphaOrder = m_permutation.GetAlphabet().GetLatin();
        for (int iLetter =0; iLetter < alphaOrder.length ; iLetter++)
        {
            for(String letterKey : p_association.keySet())
            {
                if(letterKey.equals(alphaOrder[iLetter]))
                {
                   associationString += p_association.get(letterKey);
                }
            }
        }

        m_alphaTable.SetDataRowAlphaTable(associationString);
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
                List<String> alphabeTryUser = m_alphaTable.getAlphabetTryUser();

                UpdatePermutation(m_cryptedTextArea.getText(), alphabeTryUser);
            }
        }
    }

    class UncryptAutoActions implements ActionListener
    {
        public void actionPerformed(ActionEvent p_actionEvent)
        {
            //Handle open button action.
            if (p_actionEvent.getSource() == m_tryAuto)
            {
                if(!m_permutation.GetLanguage().equals(getLangUserChoose()))
                {
                    m_permutation.SetLanguage(getLangUserChoose());
                }

                m_permutation.SetNextKeyAuto();

                UpdatePermutation(m_cryptedTextArea.getText(), m_permutation.GetNextKeyAuto());
            }
        }
    }
}