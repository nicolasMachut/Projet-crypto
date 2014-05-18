package project_crypto.Views;

import sun.org.mozilla.javascript.internal.ast.ArrayComprehensionLoop;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
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
        Object[][] donnees = {
                {"Johnathan", "Sykes", Color.red, true, Sport.TENNIS},
                {"Nicolas", "Van de Kampf", Color.black, true, Sport.FOOTBALL},
                {"Damien", "Cuthbert", Color.cyan, true, Sport.RIEN},
                {"Corinne", "Valance", Color.blue, false, Sport.NATATION},
                {"Emilie", "Schrödinger", Color.magenta, false, Sport.FOOTBALL},
                {"Delphine", "Duke", Color.yellow, false, Sport.TENNIS},
                {"Eric", "Trump", Color.pink, true, Sport.FOOTBALL},
        };

        String data = "abcde";

        alphaTable = new JTable(donnees,getRowFromEachStringLetters(data));


        this.add(alphaTable.getTableHeader(), BorderLayout.NORTH);
        this.add(alphaTable, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public enum Sport {
        TENNIS,
        FOOTBALL,
        NATATION,
        RIEN;
    }

    public Object[] getRowFromEachStringLetters(String p_string)
    {
        List<String> dataRow = new ArrayList<String>();
        for(int i = 0; i < p_string.length(); i ++)
        {
            dataRow.add(String.valueOf(p_string.charAt(i)));


        }

        return dataRow.toArray();
    }

}
