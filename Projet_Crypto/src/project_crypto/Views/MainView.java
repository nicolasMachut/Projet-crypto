package project_crypto.Views;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;

/**
 * Created by kimsavinfo on 11/03/14.
 */
public class MainView extends JPanel
{
    // Upper part : choose input and output file
    private JButton m_openButton;
    private JFileChooser m_fileChooser;
    private FileNameExtensionFilter m_filterFile;
    private JFormattedTextField m_inputFile;
    private JLabel m_outputPath;
    private JFormattedTextField m_outputFile;

    // Middle part : choose code or decode action
    private JRadioButton m_encryptButton;
    private JRadioButton m_uncryptButton;
    private ButtonGroup m_cryptGroup;
    private JComboBox m_encryptType = new JComboBox();


    // Bottom part : send choice button
    private JButton m_launchButton;

    public MainView()
    {
        // Create a file chooser

        m_fileChooser = new JFileChooser();

        // Only .txt files allowed
        m_filterFile = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        m_fileChooser.setFileFilter(m_filterFile);

        // -- UPPER PART --
        //Create the open button.
        m_openButton = new JButton("Open a file");
        m_openButton.setPreferredSize(new Dimension(Global.m_widthWindow - 40, 30));
        m_openButton.addActionListener(new FileChooserActions());

        // For layout purposes, put the button in a separate panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(m_openButton);

        // Create textfield for input and output files
        m_inputFile = new JFormattedTextField();
        m_inputFile.setPreferredSize(new Dimension(Global.m_widthWindow - 40, 30));

        // For layout purposes, put the output file options in a separate panel
        JPanel outputPanel = new JPanel();
        m_outputPath = new JLabel("Input file's path (select a file please) ");
        outputPanel.add(m_outputPath);
        m_outputFile = new JFormattedTextField("Export");
        m_outputFile.setPreferredSize(new Dimension(200, 30));
        outputPanel.add(m_outputFile);
        outputPanel.add(new JLabel(".txt"));

        // -- MIDDLE PART --
        // Create buttons and the group
        m_encryptButton = new JRadioButton(Ressources.Lang.Lang_en.encrypt , true);
        m_uncryptButton = new JRadioButton(Ressources.Lang.Lang_en.uncrypt , false);
        m_cryptGroup = new ButtonGroup();
        m_cryptGroup.add(m_encryptButton);
        m_cryptGroup.add(m_uncryptButton);

        // Select
        m_encryptType = new JComboBox();
        m_encryptType.addItem("Caesar");
        m_encryptType.addItem("Permutation");
        m_encryptType.addItem("Polybe's square");
        m_encryptType.addItem("Triangular permutation");

        // Add buttons to a new panel
        JPanel cryptChoicePanel = new JPanel();
        cryptChoicePanel.setPreferredSize(new Dimension(Global.m_widthWindow - 40, 60));
        cryptChoicePanel.setLayout(new GridLayout(2, 2));
        cryptChoicePanel.add(m_encryptButton);
        cryptChoicePanel.add(m_encryptType);
        cryptChoicePanel.add(m_uncryptButton);

        // -- BOTTOM PART --
        m_launchButton = new JButton("GO");


        // Add the buttons, text zones and panels
        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.add(m_inputFile, BorderLayout.CENTER);
        this.add(outputPanel, BorderLayout.CENTER);
        this.add(cryptChoicePanel);
        this.add(m_launchButton, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public String GetInputFile()
    {
        return m_inputFile.getText();
    }
    public String GetOutputFile()
    {
        String outputFile = "Export";
        String outputPath = "Projet_Crypto/src/Test_TextFiles/";
        if (! m_outputFile.getText().equals(""))
        {
            outputFile = m_outputFile.getText();
        }

        if(! m_outputPath.getText().equals("Input file's path (select a file please) "))
        {
            outputPath = m_outputPath.getText();
        }


        return  outputPath+outputFile+".txt";
    }
    public String GetMode()
    {
        String mode = m_encryptButton.getText();
        for (Enumeration<AbstractButton> buttons = m_cryptGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                mode = button.getText();
            }
        }
        return mode;
    }


    public String GetEncryptType()
    {
        return m_encryptType.getSelectedItem().toString();
    }

    /* ===============================================================================================================
    * ActionListeners
    * ============================================================================================================ */

    class FileChooserActions implements ActionListener
    {
        public void actionPerformed(ActionEvent p_actionEvent)
        {
            //Handle open button action.
            if (p_actionEvent.getSource() == m_openButton)
            {
                int returnVal = m_fileChooser.showOpenDialog(MainView.this);

                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    // Get the file
                    File file = m_fileChooser.getSelectedFile();
                    // File path + file name
                    String filePath = file.getPath();
                    // File path only
                    String absolutePath = filePath.substring(0, filePath.lastIndexOf(File.separator)) + "/";

                    m_inputFile.setText(filePath);
                    m_outputPath.setText(absolutePath);
                }
            }
        }
    }


    public void AddLaunchButtonListener(ActionListener p_actionListener)
    {
        m_launchButton.addActionListener(p_actionListener);
    }


}
