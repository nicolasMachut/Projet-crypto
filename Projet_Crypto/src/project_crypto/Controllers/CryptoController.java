package project_crypto.Controllers;

import Library.TextFileManager;
import project_crypto.Models.Caesar;
import project_crypto.Models.Permutation;
import project_crypto.Models.Polybe;
import project_crypto.Models.Triangular;
import project_crypto.Views.MainView;
import project_crypto.Views.UncryptingView;
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
        m_outputFilePath = new String();
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

            if(m_mainView.GetMode().equals(Ressources.Lang.Lang_en.encrypt))
            {
                m_textFileManager.LoadFile(m_mainView.GetInputFile());

                if (m_mainView.GetEncryptType().equals("Caesar")) {
                    String key;
                    do {
                        key = this.GetCryptingKeyChoosenByUserAsString();
                    } while (!this.isAGoodCeasarKey(key));
                    System.out.println(key);
                    Caesar caesar = new Caesar();
                    caesar.Crypting(m_textFileManager.getText(), 2);
                    m_textFileManager.SetText(caesar.GetEncryptedString());
                }

                if (m_mainView.GetEncryptType().equals("Permutation")) {
                    Permutation permutation = new Permutation();
                    permutation.Crypting(m_textFileManager.getText());
                    m_textFileManager.SetText(permutation.GetEncryptedString());
                }

                if (m_mainView.GetEncryptType().equals("Polybe's square")) {
                    Polybe polybe = new Polybe();
                    polybe.Crypting(m_textFileManager.getText());
                    m_textFileManager.SetText(polybe.GetEncryptedString());
                }

                if (m_mainView.GetEncryptType().equals("Triangular permutation")) {
                    String key;
                    do {
                        key = this.GetCryptingKeyChoosenByUserAsString();
                    } while (!this.isAGoodTriangularKey(key));
                    Triangular triangular = new Triangular();
                    triangular.Crypting(m_textFileManager.getText(), "cle");
                    m_textFileManager.SetText(triangular.GetEncryptedString());
                }

                m_textFileManager.WriteFile(m_mainView.GetOutputFile());
                JOptionPane.showMessageDialog(null, "A file has just been created in : " + m_mainView.GetOutputFile());
            }
            else if( m_mainView.GetMode().equals(Ressources.Lang.Lang_en.uncrypt) )
            {
                m_outputFilePath = m_mainView.GetOutputFile();

                System.out.println("Chemin d'export : "+m_outputFilePath);

                // Init texts
                m_uncryptingView.getAlphaTable().setDataRowAlphaTable("traduction");

                m_uncryptingView.setUncryptedTextArea("\n" +
                        "\n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed justo nulla, mollis nec pretium nec, convallis et massa. Donec risus est, semper interdum metus nec, imperdiet rhoncus diam. Curabitur nec elit tellus. Nullam elit lacus, eleifend eu eros eget, commodo porttitor libero. Vivamus adipiscing fringilla ante non tempus. Proin risus elit, blandit at ultrices ac, aliquet et libero. Nullam sagittis nisi orci, eu tempus quam scelerisque nec. Integer metus ligula, posuere eu pretium et, hendrerit at lorem. Nunc commodo mi nisi, ornare volutpat nunc congue vitae. Cras dolor quam, aliquam et elementum blandit, luctus sit amet quam.\n" +
                        "\n" +
                        "Nunc tortor lacus, consectetur non diam nec, mollis feugiat velit. Donec venenatis elit quis ipsum pulvinar, vel malesuada magna porta. Fusce et eros felis. In dui lorem, feugiat sed sapien et, lobortis tincidunt nunc. Duis nisi magna, ultrices at convallis at, vulputate vitae purus. Sed aliquam cursus auctor. Aenean id velit eu metus aliquam sollicitudin porttitor euismod elit. Nulla tempus diam id erat porttitor, ac viverra metus egestas. Mauris iaculis purus justo, a interdum nulla pharetra sed. Nulla rhoncus aliquam purus.\n" +
                        "\n" +
                        "Ut consequat, ante ac iaculis tempor, justo elit porttitor elit, ac consequat mauris sem in orci. Mauris malesuada vitae felis id ornare. Pellentesque turpis neque, accumsan vitae molestie et, eleifend in urna. Integer placerat libero vel est consequat iaculis in ornare purus. Suspendisse potenti. Fusce sed velit imperdiet, eleifend diam et, consequat tortor. Donec imperdiet, odio id tempor egestas, tortor odio porttitor eros, vel ultricies eros diam a justo. Nullam sodales luctus elit sed rutrum. Duis pellentesque ornare ipsum. In volutpat, ligula ut congue dapibus, orci sem fringilla ante, vitae tempor mi tellus in diam. Curabitur sed massa accumsan, ultricies ante non, auctor augue. Quisque mi magna, dictum et erat commodo, interdum elementum sem. Suspendisse blandit dictum malesuada. Nullam fringilla malesuada libero, cursus porttitor sapien bibendum eget. Mauris at placerat diam. Aenean suscipit fermentum erat et vestibulum.\n" +
                        "\n" +
                        "Cras ut erat eu libero consectetur placerat at id dolor. Vivamus mauris elit, condimentum sit amet mauris sed, ullamcorper elementum libero. Proin a tempus diam, a posuere nibh. Praesent vel hendrerit magna. Cras ornare ipsum est, id sagittis diam ornare vel. In hac habitasse platea dictumst. Aenean adipiscing quam a odio ornare, nec aliquam velit elementum. Curabitur sollicitudin iaculis magna placerat ultricies. Ut et accumsan nibh. Quisque congue non elit ac auctor. Donec auctor eros non nisl aliquet porta. Nunc vel convallis ipsum. Sed vulputate sed magna quis aliquet. Quisque in eros sed odio pretium accumsan.\n" +
                        "\n" +
                        "Proin commodo convallis luctus. Duis sit amet velit bibendum, tincidunt risus nec, vulputate enim. Cras in neque molestie, malesuada neque nec, fringilla tellus. Vestibulum eget purus eu nulla ultricies facilisis et quis sem. Suspendisse potenti. Curabitur lobortis sagittis urna, ac porta ipsum varius sed. Sed vel dictum ipsum. Nullam eleifend augue vitae nulla lobortis, eu ultrices sem volutpat. Suspendisse mollis egestas nunc eu gravida. Curabitur hendrerit sagittis fringilla. Vivamus euismod vulputate varius. Donec ornare lobortis dolor id pulvinar. Praesent scelerisque nisl eget tellus mattis, nec condimentum augue vulputate. Aliquam risus felis, elementum et malesuada ut, sollicitudin at turpis. Aliquam sagittis lacus a sem vulputate hendrerit. " +
                        "");

                m_window.SetView(m_uncryptingView);
            }
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
