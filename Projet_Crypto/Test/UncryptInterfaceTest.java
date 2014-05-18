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
    private static UncryptingView jpanel;

    public static void main(String[] args)
    {
        // Test datas
        normalAlphabet = new Alphabet();

        jpanel = new UncryptingView();

        Window m_window = new Window(jpanel);
        m_window.setVisible(true);
    }
}
