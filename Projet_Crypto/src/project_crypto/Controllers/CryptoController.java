package project_crypto.Controllers;

import Library.TextFileManager;
import project_crypto.Models.Caesar;
import project_crypto.Models.Permutation;
import project_crypto.Models.Polybe;
import project_crypto.Models.Triangular;
import project_crypto.Views.MainView;
import project_crypto.Views.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 *
 * Date : 24/02/2014.
 */

public class CryptoController
{
    private static MainView m_mainView;
    private static TextFileManager m_textFileManager;

    public static void main(String[] args)
    {
        Window m_window;
        // Create the text file manager
        m_textFileManager = new TextFileManager();
        // Create the first main view
        m_mainView = new MainView();
        // Add listeners
        m_mainView.AddLaunchButtonListener(new CryptingActions());

        // Create a window
        m_window = new Window(m_mainView);
        // Show it to the user
        m_window.setVisible(true);
    }

    /* ===============================================================================================================
    * ActionListeners
    * ============================================================================================================ */

    static class CryptingActions implements ActionListener
    {
        public void actionPerformed(ActionEvent p_actionEvent)
        {
            m_textFileManager.LoadFile(m_mainView.GetInputFile());

            String mode = m_mainView.GetMode();
            String type = m_mainView.GetEncryptType();
            if (mode.equals("Encrypt"))
            {
                if(type.equals("Caesar"))
                {
                    String key = "";
                    do
                    {
                        key = this.GetCryptingKeyChoosenByUserAsString();
                    }while(! this.isAGoodCeasarKey(key));

                    Caesar caesar = new Caesar();
                    caesar.Crypting(m_textFileManager.getText(), Integer.valueOf(key));
                    m_textFileManager.SetText(caesar.GetEncryptedString());
                }
                else if(type.equals("Permutation"))
                {
                    Permutation permutation = new Permutation();
                    permutation.Crypting(m_textFileManager.getText());
                    m_textFileManager.SetText(permutation.GetEncryptedString());
                }
                else if(type.equals("Polybe's square"))
                {
                    Polybe polybe = new Polybe();
                    polybe.Crypting(m_textFileManager.getText());
                    m_textFileManager.SetText(polybe.GetEncryptedString());
                }
                else if(type.equals("Triangular permutation"))
                {
                    String key;
                    do {
                        key = this.GetCryptingKeyChoosenByUserAsString();
                    } while(!this.isAGoodTriangularKey(key));
                    Triangular triangular = new Triangular();
                    triangular.Crypting(m_textFileManager.getText(), "cle");
                    m_textFileManager.SetText(triangular.GetEncryptedString());
                }

            }
            else if (mode.equals("Uncrypt"))
            {
                String autoFlag = "auto";

                if(type.equals("Caesar"))
                {
                    Boolean isKeyOK = false;
                    String key = "";
                    do
                    {
                        key = this.GetCryptingKeyChoosenByUserAsString();

                        System.out.println(key);

                        if(!key.equals(autoFlag))
                        {
                            isKeyOK = this.isAGoodCeasarKey(key);
                        }

                    }while(!isKeyOK && (!key.equals(autoFlag)));

                    Caesar caesar = new Caesar();

                    if(!key.equals(autoFlag))
                    {
                        caesar.Uncrypting(m_textFileManager.getText(), Integer.valueOf(key));
                    }
                    else
                    {
                        caesar.Uncrypting(m_textFileManager.getText());
                    }

                    m_textFileManager.SetText(caesar.GetUncryptedString());
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please, choose a mode (encrypt/uncrypt).");
            }

            m_textFileManager.WriteFile(m_mainView.GetOutputFile());
            JOptionPane.showMessageDialog(null, "A file has just been created in : " + m_mainView.GetOutputFile());
        }

        private String GetCryptingKeyChoosenByUserAsString()
        {
            String value = JOptionPane.showInputDialog("Please, choose a key");

            if(value == null)
            {
                value = "-1";
            }

            return value;
        }

        private boolean isAGoodCeasarKey(String keyToTest)
        {
            Boolean resultat = false;
            if(!keyToTest.isEmpty() && keyToTest != null)
            {
                try
                {
                    int intValue = Integer.valueOf(keyToTest);
                    if(intValue > 0 && intValue < 26)
                        resultat = true;
                }
                catch(Exception e)
                {
                    e.getCause();
                }
            }
            if(!resultat)
                JOptionPane.showMessageDialog(null, "Please, choose a numeric key between 1 and 26 or 'auto' for uncrypting mode.");

            return resultat;
        }

        private boolean isAGoodTriangularKey(String keyToTest)
        {
            Pattern pattern = Pattern.compile("[a-zA-Z]");
            Matcher matcher = pattern.matcher(keyToTest);
            Boolean resultat = false;
            if(! keyToTest.isEmpty())
            {
                if(matcher.find())
                {
                    resultat = true;
                }
            }
            if(!resultat)
                JOptionPane.showMessageDialog(null, "Please, choose a key with only chars.");

            return resultat;
        }
    }
}
