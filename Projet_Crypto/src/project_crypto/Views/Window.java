package project_crypto.Views;

import javax.swing.*;
import java.awt.Color;

/**
 * Created by kimsavinfo on 11/03/14.
 *
 * JFrame : window that can be reduced, closed...
 * JPanels are in other class
 */
public class Window extends JFrame
{
    private static final String m_title = "CRYPTOGRAPHY";
    private JPanel m_panelActu; // will change thanks to the controller

    public Window(JPanel p_panel)
    {
        // Set windows properties
        this.setTitle(m_title);
        this.setSize(Global.m_widthWindow, Global.m_heightWindow);
        this.setBackground(Color.GRAY);
        // Allow the user to close the windows
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Scrollbar
        JScrollPane scrollPaneUncryptedTextArea = new JScrollPane();
        this.add(scrollPaneUncryptedTextArea);

        SetView(p_panel);
    }

    public void SetView(JPanel p_panel)
    {
        this.getContentPane().removeAll();
        // Link with a JPanel
        m_panelActu = p_panel;
        this.getContentPane().add(m_panelActu);
        // Refresh the view
        this.validate();
        this.repaint();
    }

    public JPanel GetView()
    {
        return m_panelActu;
    }


}
