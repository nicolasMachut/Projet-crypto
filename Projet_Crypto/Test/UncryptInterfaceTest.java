import Library.Alphabet;
import project_crypto.Models.Caesar;
import project_crypto.Views.UncryptingView;
import project_crypto.Views.Window;

import javax.swing.*;

/**
 * Created by kimsavinfo on 17/05/14.
 */
public class UncryptInterfaceTest
{
    // Subject :
    // Show two alphabets : 1 uncrypted and 1 crypted
    // click on one = highligth
    // click second = higlight unless it is the first one then un-highligth
    // Button switch = refresh

    private static Alphabet normalAlphabet;
    private static Caesar caesar;
    private static UncryptingView jpanel;

    public static void main(String[] args)
    {
        // create two alphabets for test
        normalAlphabet = new Alphabet();
        caesar = new Caesar();
        caesar.Crypting("abcdefghijklmnopqrstuvwxyz", 5);

        jpanel = new UncryptingView();
        jpanel.addRow(normalAlphabet.GetLatin().toString());
        jpanel.addRow(caesar.GetEncryptedString());

        Window m_window = new Window(jpanel);
        m_window.setVisible(true);
    }
}
