package project_crypto.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * Created by kimsavinfo on 17/05/14.
 */
public class UncryptingView extends JPanel
{
    private JTable tableau;

    public UncryptingView()
    {
        JTable tableau = new JTable();

        this.add(tableau);

        this.setVisible(true);
    }

    public void addRow(String p_string)
    {
        DefaultTableModel model = (DefaultTableModel) tableau.getModel();

        Vector row = new Vector();
        char[] charArray = p_string.toCharArray();
        row.add(charArray);

        model.addRow(row);
        tableau.setModel(model);
    }
}
