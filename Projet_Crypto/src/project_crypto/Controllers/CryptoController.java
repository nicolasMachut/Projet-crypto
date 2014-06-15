package project_crypto.Controllers;

import Library.TextFileManager;
import project_crypto.Models.Caesar;
import project_crypto.Models.Permutation;
import project_crypto.Models.Polybe;
import project_crypto.Models.Triangular;
import project_crypto.Views.MainView;
import project_crypto.Views.UncryptingView;
import project_crypto.Views.Window;
import project_crypto.Ressources.Lang.Lang_en;

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
    private static Window m_window;
    private static MainView m_mainView;
    private static TextFileManager m_textFileManager;
    private static UncryptingView m_uncryptingView;
    private static String m_outputFilePath;

    public static void main(String[] args)
    {
        // Main View
        // Create the text file manager
        m_textFileManager = new TextFileManager();
        // Create the first main view
        m_mainView = new MainView();
        // Add listeners
        m_mainView.AddLaunchButtonListener(new CryptingActions());

        // Uncrypting View
        m_uncryptingView = new UncryptingView();
        m_outputFilePath = "";
        // Add listners
         m_uncryptingView.AddExportButtonListener(new UncryptingExportActions());

        // Create a window
        m_window = new Window(m_mainView);
        // Show it to the user
        m_window.setVisible(true);
    }

    /* ===============================================================================================================
    * ActionListeners
    * ============================================================================================================ */

    private static class CryptingActions implements ActionListener
    {
        public void actionPerformed(ActionEvent p_actionEvent)
        {
            m_textFileManager.LoadFile(m_mainView.GetInputFile());

            String mode = m_mainView.GetMode();
            String type = m_mainView.GetEncryptType();

            if (mode.equals(Lang_en.encrypt))
            {
                if(type.equals(Lang_en.caesar))
                {
                    String key;
                    do
                    {
                        key = this.GetCryptingKeyChoosenByUserAsString();
                    }while(! this.isAGoodCeasarKey(key));

                    Caesar caesar = new Caesar();
                    caesar.Crypting(m_textFileManager.getText(), Integer.valueOf(key));
                    m_textFileManager.SetText(caesar.GetEncryptedString());
                }
                else if(type.equals(Lang_en.permutation))
                {
                    Permutation permutation = new Permutation();
                    permutation.Crypting(m_textFileManager.getText());
                    m_textFileManager.SetText(permutation.GetEncryptedString());
                }
                else if(type.equals(Lang_en.polybe_square))
                {
                    Polybe polybe = new Polybe();
                    polybe.Crypting(m_textFileManager.getText());
                    m_textFileManager.SetText(polybe.GetEncryptedString());
                }
                else if(type.equals(Lang_en.triangle_permutation))
                {
                    String key;
                    do {
                        key = this.GetCryptingKeyChoosenByUserAsString();
                    } while(!this.isAGoodTriangularKey(key));

                    Triangular triangular = new Triangular();
                    triangular.Crypting(m_textFileManager.getText(), key);
                    m_textFileManager.SetText(triangular.GetEncryptedString());
                }

            }
            else if (mode.equals(Lang_en.uncrypt))
            {
                if(type.equals(Lang_en.caesar))
                {
                    Boolean isKeyOK = false;
                    String key;
                    do
                    {
                        key = this.GetCryptingKeyChoosenByUserAsString();

                        if(!key.equals(Lang_en.auto))
                        {
                            isKeyOK = this.isAGoodCeasarKey(key);
                        }

                    }while(!isKeyOK && (!key.equals(Lang_en.auto)));

                    Caesar caesar = new Caesar();

                    if(!key.equals(Lang_en.auto))
                    {
                        caesar.Uncrypting(m_textFileManager.getText(), Integer.valueOf(key));
                    }
                    else
                    {
                        caesar.Uncrypting(m_textFileManager.getText());
                    }

                    m_textFileManager.SetText(caesar.GetReadableString());
                }
                else if(type.equals(Lang_en.permutation))
                {
                    // Save the output file path
                    m_outputFilePath = m_mainView.GetOutputFile();

                    // Init text for the user
                    m_uncryptingView.getAlphaTable().setDataRowAlphaTable(""); //"abcdefghijklmnopqrstuvwxyz"
                    m_uncryptingView.setCryptedTextArea(m_textFileManager.getText());

                    // show interface
                    m_window.SetView(m_uncryptingView);
                }
                else if(type.equals(Lang_en.polybe_square))
                {
                    Polybe polybe = new Polybe();
                    polybe.Uncrypting( m_textFileManager.getText() );
                    m_textFileManager.SetText(polybe.GetReadableString());
                }
                else if(type.equals(Lang_en.permutation))
                {
                    String key;
                    do {
                        key =  this.GetCryptingKeyChoosenByUserAsString();
                    } while(!this.isAGoodTriangularKey(key));

                    Triangular triangular = new Triangular();
                    triangular.Uncrypting( m_textFileManager.getText(), key );
                    m_textFileManager.SetText(triangular.GetReadableString());
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please, choose a mode (encrypt/uncrypt).");
            }

            if( !( mode.equals(Lang_en.uncrypt) && type.equals(Lang_en.permutation) ) )
            {
                m_textFileManager.WriteFile(m_mainView.GetOutputFile());
                JOptionPane.showMessageDialog(null, "A file has just been created in : " + m_mainView.GetOutputFile());
            }
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

    private static class UncryptingExportActions implements ActionListener
    {
        public void actionPerformed(ActionEvent p_actionEvent)
        {
            m_textFileManager.SetText(m_uncryptingView.GetUncryptedText());

            m_textFileManager.WriteFile(m_outputFilePath);
            JOptionPane.showMessageDialog(null, "A file has just been created in : " + m_mainView.GetOutputFile());
        }
    }
}
