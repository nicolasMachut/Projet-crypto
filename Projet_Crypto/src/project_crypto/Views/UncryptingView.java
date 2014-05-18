package project_crypto.Views;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimsavinfo on 17/05/14.
 */
public class UncryptingView extends JPanel
{
    private JTable alphaTable;

    public UncryptingView()
    {
        Object[][] dataDefault = {
                {"A", "L", "P", "H", "A"}
        };
        String headerDefault = "STRNG";
        alphaTable = new JTable(dataDefault, getRowFromEachStringLetters(headerDefault));

        // Table style
        setAlphaTableStyle();

        this.setVisible(true);
    }

    private void setAlphaTableStyle()
    {
        this.add(alphaTable.getTableHeader(), BorderLayout.NORTH);
        this.add(alphaTable, BorderLayout.CENTER);

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
        TableColumn cellCol = alphaTable.getColumnModel().getColumn(0);

        int nb_column = alphaTable.getColumnCount();
        for(int iColumn = 0; iColumn < nb_column; iColumn++)
        {
            cellCol = alphaTable.getColumnModel().getColumn(iColumn);
            cellCol.setCellRenderer(dtcr);
        }
    }

    public void setHeaderAlphaTable(String p_headerString)
    {
        JTableHeader header = alphaTable.getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();
        TableColumn tc;

        for(int iColumn = 0; iColumn < p_headerString.length(); iColumn++)
        {
            tc = columnModel.getColumn(iColumn);
            tc.setHeaderValue( String.valueOf(p_headerString.charAt(iColumn)) );
        }

        header.repaint();
    }

    public void setDataRowAlphaTable(String p_dataString)
    {
        int nb_column = alphaTable.getColumnCount();
        for(int iColumn = 0; iColumn < p_dataString.length(); iColumn++)
        {
            if(iColumn < nb_column)
            {
                alphaTable.setValueAt( String.valueOf(p_dataString.charAt(iColumn)), 0, iColumn);
            }
        }
    }

    public Object[] getRowFromEachStringLetters(String p_string)
    {
        List<String> dataRow = new ArrayList<String>();
        for(int iChar = 0; iChar < p_string.length(); iChar++)
        {
            dataRow.add(String.valueOf(p_string.charAt(iChar)));
        }

        return dataRow.toArray();
    }

}
