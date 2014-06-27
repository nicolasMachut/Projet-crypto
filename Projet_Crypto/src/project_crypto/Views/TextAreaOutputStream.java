package project_crypto.Views;

/**
 * Autheur : kimsavinfo
 * Date : 09:22 27 juin 2014
 *
 * @(#) TextAreaOutputStream.java
 *  TextArea onsole like
 *
 *
 *
 * USAGE :
 *
 * Create an instance of javax.swing.JTextArea control
 * JTextArea txtConsole = new JTextArea();
 *
 * Create a new TextAreaOutputStream to write to our JTextArea control and wrap a
 * PrintStream around it to support the println/printf methods.
 * PrintStream out = new PrintStream( new TextAreaOutputStream( txtConsole ) );
 *
 * Redirect standard output stream to the TextAreaOutputStream
 * System.setOut( out );
 *
 * Redirect standard error stream to the TextAreaOutputStream
 * System.setErr( out );
 *
 * Test the mechanism ;)
 * System.out.println( "Hello World" );
 */

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * An output stream that writes its output to a javax.swing.JTextArea
 * control.
 *
 * @author  Ranganath Kini
 * @see      javax.swing.JTextArea
 */
public class TextAreaOutputStream extends OutputStream
{
    private JTextArea textControl;

    /**
     * Creates a new instance of TextAreaOutputStream which writes
     * to the specified instance of javax.swing.JTextArea control.
     *
     * @param control   A reference to the javax.swing.JTextArea
     *                  control to which the output must be redirected
     *                  to.
     */
    public TextAreaOutputStream( JTextArea control )
    {
        textControl = control;
    }

    /**
     * Writes the specified byte as a character to the
     * javax.swing.JTextArea.
     *
     * @param   b   The byte to be written as character to the
     *              JTextArea.
     */
    public void write( int b ) throws IOException
    {
        // append the data as characters to the JTextArea control
        textControl.append( String.valueOf( ( char )b ) );
    }
}