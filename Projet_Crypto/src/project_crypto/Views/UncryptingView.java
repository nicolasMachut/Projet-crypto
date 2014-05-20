package project_crypto.Views;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by kimsavinfo on 17/05/14.
 */
public class UncryptingView extends JPanel
{
    private JTable alphaTable;
    private Color highlightColor;

    public UncryptingView()
    {
        Object[][] dataDefault = {
                {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T" ,"U", "V", "W", "X", "Y", "Z"}
        };
        String headerDefault = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        initAlphaTable(dataDefault, headerDefault);

        highlightColor = new Color(103, 242, 255);

        this.setVisible(true);
    }

    public void setHeaderAlphaTable(String p_headerString)
    {
        clearAlphaTable("header");

        JTableHeader header = alphaTable.getTableHeader();
        TableColumnModel columnModel = header.getColumnModel();
        TableColumn tc;

        for(int iColumn = 0; iColumn < p_headerString.length(); iColumn++)
        {
            tc = columnModel.getColumn(iColumn);
            tc.setHeaderValue( String.valueOf(p_headerString.charAt(iColumn)) );
        }
    }

    public void setDataRowAlphaTable(String p_dataString)
    {
        clearAlphaTable("row");

        int nb_column = alphaTable.getColumnCount();
        for(int iColumn = 0; iColumn < p_dataString.length(); iColumn++)
        {
            if(iColumn < nb_column)
            {
                alphaTable.setValueAt( String.valueOf(p_dataString.charAt(iColumn)), 0, iColumn);
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

    private void clearAlphaTable(String p_part)
    {
        int nb_column = alphaTable.getColumnCount();

        if(p_part.equals("header"))
        {
            JTableHeader header = alphaTable.getTableHeader();
            TableColumnModel columnModel = header.getColumnModel();
            TableColumn tc;

            for(int iColumn = 0; iColumn < nb_column; iColumn++)
            {
                tc = columnModel.getColumn(iColumn);
                tc.setHeaderValue(" ");
            }
        }
        else if(p_part.equals("row"))
        {

            for(int iColumn = 0; iColumn < nb_column; iColumn++)
            {
                if(iColumn < nb_column)
                {
                    alphaTable.setValueAt((" "), 0, iColumn);
                }
            }
        }
    }

     /* ===============================================================================================================
    * JTable
    * ============================================================================================================ */
    private void initAlphaTable(Object[][] p_data, String p_header)
    {
        alphaTable = new JTable(p_data, getRowFromEachStringLetters(p_header));
        alphaTable.setCellSelectionEnabled(true);

        // Disable editabled
        alphaTable.setEnabled(false);

        // Style
        this.add(alphaTable.getTableHeader(), BorderLayout.NORTH);
        this.add(alphaTable, BorderLayout.CENTER);
        TableColumn column = null;
        for (int iColumn = 0; iColumn < alphaTable.getColumnCount(); iColumn++)
        {
            column = alphaTable.getColumnModel().getColumn(iColumn);
            column.setPreferredWidth(5);
        }

        // Mouse action
        // DOING, DO NOT ERASE PLEASE !!!
        alphaTable.addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                //int row = //alphaTable.rowAtPoint(evt.getPoint());
                int col = alphaTable.columnAtPoint(evt.getPoint());

                DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer()
                {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
                    {
                        if( getBackground() == highlightColor)
                        {
                            setBackground(Color.WHITE);
                        }
                        else
                        {
                            setBackground(highlightColor);
                            setText(value.toString());
                        }

                        return this;
                    }
                };
                alphaTable.getColumnModel().getColumn(col).setCellRenderer( cellRenderer );

                alphaTable.repaint();
            }
        });
    }

}
