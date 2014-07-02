package project_crypto.Controllers;

import Library.LanguageDetection;
import Library.TextFileManager;
import Library.WordToNormalize;
import project_crypto.Models.Caesar;
import project_crypto.Models.Permutation;
import project_crypto.Models.Polybe;
import project_crypto.Models.Triangular;
import project_crypto.Ressources.Lang.Lang_en;
import project_crypto.Views.Global;
import project_crypto.Views.MainView;
import project_crypto.Views.UncryptingView.*;
import project_crypto.Views.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
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
    private static String m_uncryptingViewType;
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
        m_outputFilePath = "";

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
            m_uncryptingViewType = m_mainView.GetEncryptType();

            if (mode.equals(Lang_en.encrypt))
            {
                String langue;
                do {
                    langue = this.GetCryptingKeyChoosenByUserAsString(Global.m_msgAskLanguage);
                } while(!this.isAGoodPolybeKey(langue));

                if(m_uncryptingViewType.equals(Lang_en.caesar))
                {
                    String key;
                    do
                    {
                        key = this.GetCryptingKeyChoosenByUserAsString(Global.m_msgAskKeyCaesar);
                    }while(!this.isAGoodCeasarKey(key));

                    Caesar caesar = new Caesar(langue);
                    caesar.Crypting(m_textFileManager.GetText(), Integer.valueOf(key));
                    m_textFileManager.SetText(caesar.GetEncryptedString());
                }
                else if(m_uncryptingViewType.equals(Lang_en.permutation))
                {
                    Permutation permutation = new Permutation(langue);
                    permutation.Crypting(m_textFileManager.GetText());
                    m_textFileManager.SetText(permutation.GetEncryptedString());
                }
                else if(m_uncryptingViewType.equals(Lang_en.polybe_square))
                {
                    Polybe polybe = new Polybe(langue);
                    polybe.Crypting(m_textFileManager.GetText());
                    m_textFileManager.SetText(polybe.GetEncryptedString());
                }
                else if(m_uncryptingViewType.equals(Lang_en.triangle_permutation))
                {
                    String key;
                    do {
                        key = this.GetCryptingKeyChoosenByUserAsString(Global.m_msgAskKeyWord);
                    } while(!this.isAGoodTriangularKey(key));

                    Triangular triangular = new Triangular();
                    triangular.Crypting(m_textFileManager.GetText(), key);
                    m_textFileManager.SetText(triangular.GetEncryptedString());
                }

            }
            else if (mode.equals(Lang_en.uncrypt))
            {
                 // Save the output file path
                m_outputFilePath = m_mainView.GetOutputFile();
                String textToUncrypt = m_textFileManager.GetText();

                // Which language do you speak ?
                LanguageDetection languageDetection = new LanguageDetection();
                String language = languageDetection.GetLanguage(textToUncrypt);


                if(m_uncryptingViewType.equals(Lang_en.caesar))
                {
                    UncryptingCaesarView uncryptingCaesarView = new UncryptingCaesarView(language);
                    uncryptingCaesarView.SetCryptedTextArea(textToUncrypt);

                    uncryptingCaesarView.GetCaesar().Uncrypting(textToUncrypt);
                    uncryptingCaesarView.SetUncryptedTextArea(uncryptingCaesarView.GetCaesar().GetReadableString());
                    uncryptingCaesarView.SetCaesarKeyField(uncryptingCaesarView.GetCaesar().FindCryptingKey(textToUncrypt));

                    m_uncryptingView = uncryptingCaesarView;
                }
                else if(m_uncryptingViewType.equals(Lang_en.permutation))
                {
                    UncryptingPermutationView uncryptingPermutationView = new UncryptingPermutationView(language);
                    uncryptingPermutationView.SetCryptedTextArea(textToUncrypt);

                    uncryptingPermutationView.GetPermutation().Uncrypting(textToUncrypt);
                    uncryptingPermutationView.SetDataRowAlphaTable( uncryptingPermutationView.GetPermutation().GetAssociation() );
                    uncryptingPermutationView.SetUncryptedTextArea(uncryptingPermutationView.GetPermutation().GetReadableString());

                    m_uncryptingView = uncryptingPermutationView;
                }
                else if(m_uncryptingViewType.equals(Lang_en.polybe_square))
                {
                    UncryptingPolybeView uncryptingPolybeView = new UncryptingPolybeView(language);
                    uncryptingPolybeView.SetCryptedTextArea(textToUncrypt);

                    if(uncryptingPolybeView.GetPolybe().IsTextUncryptable(textToUncrypt))
                    {
                        uncryptingPolybeView.GetPolybe().Uncrypting(textToUncrypt);
                        uncryptingPolybeView.SetUncryptedTextArea(uncryptingPolybeView.GetPolybe().GetReadableString());
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Incorrect text lenght (odd number)");
                    }

                    m_uncryptingView = uncryptingPolybeView;
                }
                else if(m_uncryptingViewType.equals(Lang_en.triangle_permutation))
                {
                    UncryptingTriangularView uncryptingTriangularView = new UncryptingTriangularView(language);
                    uncryptingTriangularView.SetCryptedTextArea(textToUncrypt);
                    m_uncryptingView = uncryptingTriangularView;
                }

                Map<String, Double> languageInfos = languageDetection.SumFrequenceMostUsedCharInLanguage(textToUncrypt);
                m_uncryptingView.SetTextLanguageRadioFr("fr (" + languageInfos.get("fr") + " )");
                m_uncryptingView.SetTextLanguageRadioEn("en (" + languageInfos.get("en") + " )");

                // show interface
                m_window.SetView(m_uncryptingView);
                m_uncryptingView.AddExportButtonListener(new UncryptingExportActions());
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please, choose a mode (encrypt/uncrypt).");
            }

            if( mode.equals(Lang_en.encrypt) )
            {
                m_textFileManager.WriteFile(m_mainView.GetOutputFile());
                JOptionPane.showMessageDialog(null, "A file has just been created in : " + m_mainView.GetOutputFile());
            }
        }

        private String GetCryptingKeyChoosenByUserAsString(String p_message)
        {
            String value = JOptionPane.showInputDialog(p_message);

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
                JOptionPane.showMessageDialog(null, "Please, choose a numeric key between 1 and 26.");

            return resultat;
        }

        private boolean isAGoodPolybeKey(String keyToTest)
        {
            boolean resultat = false;

            if(keyToTest.equals("fr") || keyToTest.equals("en"))
            {
                resultat = true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please, choose 'fr' or 'en'.");
            }

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
            // Log
            m_textFileManager.SetText(m_uncryptingViewType+" trials :\n"+m_uncryptingView.GetLogsToExport());
            String logExport = m_mainView.GetOutputLogs(new WordToNormalize().Normalize(m_uncryptingViewType));
            m_textFileManager.WriteFile(logExport);


            // Text
            m_textFileManager.SetText(m_uncryptingView.GetUncryptedText());
            m_textFileManager.WriteFile(m_outputFilePath);
            JOptionPane.showMessageDialog(null, "Two files have just been created : " + m_outputFilePath+" and "+logExport);
        }
    }
}
