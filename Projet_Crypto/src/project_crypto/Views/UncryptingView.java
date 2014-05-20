package project_crypto.Views;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by kimsavinfo on 17/05/14.
 */
public class UncryptingView extends JPanel
{
    private AlphabetJTable alphaTable;


    public UncryptingView()
    {
        alphaTable = new AlphabetJTable();
        this.add(new JScrollPane(alphaTable));

        this.setVisible(true);
    }

    public AlphabetJTable getAlphaTable()
    {
        return alphaTable;
    }
}
