package project_crypto.Views.UncryptingView;

import Library.WordToNormalize;
import project_crypto.Models.Triangular;
import project_crypto.Views.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Autheur : kimsavinfo
 * Date : 25:21 27 juin 2014
 */
public class UncryptingTriangularView extends  UncryptingView
{
    private Triangular m_triangular;
    private WordToNormalize wordToNormalize;
    private JFormattedTextField m_keyField;
    private JTextArea m_triangleArea;

    public UncryptingTriangularView()
    {
        super();

        JLabel keyLabel = new JLabel("Triangular key :");
        this.add(keyLabel);
        m_keyField = new JFormattedTextField("epsi");
        m_keyField.setPreferredSize(new Dimension(200, 30));
        this.add(m_keyField);

        m_triangular = new Triangular();
        wordToNormalize = new WordToNormalize();
        m_tryUncriptButton.addActionListener(new UncryptActions());

        m_triangleArea = new JTextArea(Global.m_heightScrollArea, Global.m_widthScrollArea );
        m_triangleArea.setEditable(false);
        JScrollPane scrollPaneTrangleTextArea = new JScrollPane(m_triangleArea);
        this.add(scrollPaneTrangleTextArea);

        showComponents();
    }

    private void updateTriangular()
    {
        fillTriangleArea();

        m_triangular.Uncrypting(m_cryptedTextArea.getText(), wordToNormalize.normalize(m_keyField.getText()));

        setUncryptedTextArea(m_triangular.GetReadableString());
    }

    private void fillTriangleArea()
    {
        java.util.List<Integer> columnOrder = m_triangular.FindColumnOrder(wordToNormalize.normalize(m_keyField.getText()));

        int m_nbLetters = m_triangular.getNbLetters();
        int m_nbLines = m_triangular.getNbLines();
        int m_nbColumns = m_triangular.getNbColumns();
        int nbLettersDoneBeforeLine = ((m_nbLines - 1) * (m_nbLines - 1) + (m_nbLines - 1)) / 2;
        int lasstLetterCol = 2 * (m_nbLetters - (nbLettersDoneBeforeLine + 1));

        String[][] triangleBuild = m_triangular.BuildTriangleToUncrypt(lasstLetterCol, columnOrder);

        String lines = "";
        for (int iLig = 0; iLig < m_nbLines; iLig++)
        {
            for (int iCol = 0; iCol < m_nbColumns; iCol++)
            {
                if (triangleBuild[iLig][iCol].equals(""))
                {
                    lines += "   -   "; // help us see the rows and lines
                }
                else
                {
                    lines += " "+triangleBuild[iLig][iCol]+" ";
                }
            }
            lines += "\n";
        }

        m_triangleArea.setText(lines);
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
                updateTriangular();
            }
        }
    }
}
