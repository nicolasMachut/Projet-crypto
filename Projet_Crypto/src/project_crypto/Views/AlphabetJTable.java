package project_crypto.Views;

import Library.WordToNormalize;

import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kimsavinfo on 20/05/14.
 *
 *  - highlight the selected row
 *  - highlight the selected column
 *  - change user input text so that we only save
 *      the first letter in upper case
 *
 */
public class AlphabetJTable extends JTable
{
    static final int ROWS = 1;
    static final int COLUMNS = 26;
    private Object[][] data;
    private Object[] header;
    private WordToNormalize wordToNormalizer = new WordToNormalize();

    public AlphabetJTable()
    {
        super(new DefaultTableModel(ROWS, COLUMNS));
        tableSetup();
        setVisible(true);
    }

    private void tableSetup()
    {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        setDefaultRenderer(String.class, cellRenderer);

        header = getRowFromEachStringLetters("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        data =
            new Object[][]{
                    {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}
            };

        TableColumn column = null;
        for (int iRow = 0; iRow < data.length; iRow++)
        {
            for (int iColumn = 0; iColumn < COLUMNS; iColumn++)
            {
                column = getColumnModel().getColumn(iColumn);
                column.setPreferredWidth(5);

                if(iRow == 0)
                {
                    this.setValueAt(header[iColumn], iRow, iColumn);
                }
                else
                {
                    this.setValueAt(data[iRow][iColumn], iRow, iColumn);
                }
            }
        }
    }

    /* ===============================================================================================================
    * Highlight
    * ============================================================================================================ */

    @Override
    public boolean isCellSelected(int row, int column)
    {
        // Only column :  return isColumnSelected(column) ;
        // Only row : return isRowSelected(column) ;
        // Both :
        return isColumnSelected(column) || isRowSelected(row);
    }

    @Override
    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
    {
        super.changeSelection(rowIndex, columnIndex, false, false);
        repaint(getVisibleRect());
    }


    /* ===============================================================================================================
    * Deal with the user input text
    * ============================================================================================================ */

    @Override
    public TableCellRenderer getCellRenderer(int row, int column)
    {
        // Update data so you can do whatever we want after
        // So you don't have to do this
        Object value = this.getValueAt(row, column);
        data[row][column] = dealValueFirstLetterUpperCase(value);

        // Cell style
        return new MyRenderer();
    }


    @SuppressWarnings("serial")
    private class MyRenderer extends DefaultTableCellRenderer
    {
        public MyRenderer() {	}

        public void setValue(Object value)
        {
            if (value != null)
            {
                if (value instanceof String)
                {
                    // Mettre en maj et ne garder que la 1er lettre
                    setText( dealValueFirstLetterUpperCase(value) );
                }
            }
        }
    }

    private String dealValueFirstLetterUpperCase(Object p_value)
    {
        String userString = ((String)p_value).toUpperCase();
        userString = String.valueOf(userString.charAt(0));
        userString = wordToNormalizer.normalize(userString);

        return userString;
    }

    /* ===============================================================================================================
    * Custom
    * ============================================================================================================ */

    public void setHeaderAlphaTable(String p_headerString)
    {
        clearAlphaTable("header");

        header = getRowFromEachStringLetters(p_headerString);

        JTableHeader header = getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();
        TableColumn tc;

        for(int iColumn = 0; iColumn < p_headerString.length(); iColumn++)
        {
            tc = columnModel.getColumn(iColumn);

            tc.setHeaderValue( String.valueOf(wordToNormalizer.normalize(p_headerString).charAt(iColumn)) );
        }
    }

    public void setDataRowAlphaTable(String p_dataString)
    {
        clearAlphaTable("row");

        data[0][0] = getRowFromEachStringLetters(p_dataString);

        for(int iColumn = 0; iColumn < p_dataString.length(); iColumn++)
        {
            if(iColumn < COLUMNS)
            {
                setValueAt(String.valueOf(wordToNormalizer.normalize(p_dataString).charAt(iColumn)), 0, iColumn);
            }
        }
    }

    private void clearAlphaTable(String p_part)
    {
        if(p_part.equals("header"))
        {
            JTableHeader header = getTableHeader();
            TableColumnModel columnModel = header.getColumnModel();
            TableColumn tc;

            for(int iColumn = 0; iColumn < COLUMNS; iColumn++)
            {
                tc = columnModel.getColumn(iColumn);
                tc.setHeaderValue(" ");
            }
        }
        else if(p_part.equals("row"))
        {

            for(int iColumn = 0; iColumn < COLUMNS; iColumn++)
            {
                if(iColumn < COLUMNS)
                {
                    setValueAt((" "), 0, iColumn);
                }
            }
        }
    }

    private Object[] getRowFromEachStringLetters(String p_string)
    {
        List<String> dataRow = new ArrayList<String>();
        for(int iChar = 0; iChar < p_string.length(); iChar++)
        {
            dataRow.add(String.valueOf(p_string.charAt(iChar)));
        }

        return dataRow.toArray();
    }

     /* ===============================================================================================================
    * For the UncryptingView
    * ============================================================================================================ */

    public List<String> getAlphabetTryUser()
    {
        List<String> alphaTryUser = new ArrayList<String>();

        for(int iColumn = 0; iColumn < COLUMNS; iColumn++)
        {
            if(iColumn < COLUMNS)
            {
                alphaTryUser.add( getValueAt(0, iColumn).toString() );
            }
        }

        return alphaTryUser;
    }
}
