package project_crypto.Controllers;

import Library.TextFileManager;
import project_crypto.Models.*;
import project_crypto.Views.MainView;
import project_crypto.Views.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

            if(m_mainView.GetEncryptType().equals("Caesar"))
            {
                String key;
                do
                {
                    key = this.GetCryptingKeyChoosenByUserAsString();
                }while(!this.isAGoodCeasarKey(key));
                System.out.println(key);
                Caesar caesar = new Caesar();
                caesar.Crypting(m_textFileManager.getText(),2);
                m_textFileManager.SetText(caesar.GetEncryptedString());
            }

            if(m_mainView.GetEncryptType().equals("Permutation"))
            {
                Permutation permutation = new Permutation();
                permutation.Crypting(m_textFileManager.getText());
                m_textFileManager.SetText(permutation.GetEncryptedString());
            }

            if(m_mainView.GetEncryptType().equals("Polybe's square"))
            {
                Polybe polybe = new Polybe();
                polybe.Crypting(m_textFileManager.getText());
                m_textFileManager.SetText(polybe.GetEncryptedString());
            }

            if(m_mainView.GetEncryptType().equals("Triangular permutation"))
            {
                Triangular triangular = new Triangular();
                triangular.Crypting(m_textFileManager.getText(), "cle");
                m_textFileManager.SetText(triangular.GetEncryptedString());
            }

            m_textFileManager.WriteFile(m_mainView.GetOutputFile());
            JOptionPane.showMessageDialog(null, "A file has just been created in : "+m_mainView.GetOutputFile());
        }

        //Uncrypting text file
        public void UncryptingTextFile()
        {
            Caesar testUncrypting = new Caesar();

            testUncrypting.Uncrypting(m_textFileManager.getText());
            m_textFileManager.SetText(testUncrypting.GetUncryptedString());
        }

        private String GetCryptingKeyChoosenByUserAsString()
        {
            String value = JOptionPane.showInputDialog("Please, choose a key");
            return value;
        }

        private boolean isAGoodCeasarKey(String keyToTest)
        {
            Boolean resultat = false;
            if(!keyToTest.isEmpty())
            {
                try
                {
                    int intValue = Integer.valueOf(keyToTest);
                    if(intValue > 0 && intValue < 26)
                        resultat = true;
                }
                catch(Exception e)
                {

                }
            }
            if(!resultat)
                JOptionPane.showMessageDialog(null, "Please, choose a numeric key between 1 and 26.");

            return resultat;
        }
    }
}
