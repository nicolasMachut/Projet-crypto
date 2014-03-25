package Library;


import javax.swing.JOptionPane;
import java.io.FileReader ;
import java.io.BufferedReader ;
import java.io.BufferedWriter ;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 *
 * Date : 01/03/14
 *
 * Reads and writes text file.
 * Calculate the char repartition/frequency of the text.
 *
 * It gives you more details about the text too if you want (debug help) :
 * - text length
 * - number of lines
 * - number of words (line.separator needed of course)
 */
public class TextFileManager
{
    private String m_text;

    public TextFileManager()
    {
        m_text = null;
    }

     /* ===============================================================================================================
     * Functions and Methods
     * ============================================================================================================ */

    /**
     * Assign a text directly, not by loading a file
     * Usefull for the output text
     * @param p_text : String
     */
    public void SetText(String p_text)
    {
        // Normalize and then save the text
        m_text = p_text;
    }
    public String getText()
    {
        return m_text;
    }


    /**
     * Load text from a file if it exists
     * @param p_filePath : input file path
     */
    public void LoadFile(String p_filePath)
    {
        // StringBuffer that will stock every lines
        StringBuffer fileContents = new StringBuffer();

        try
        {
            // Store the file in the buffer
            FileReader fileReader = new FileReader(p_filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            // Read the text line by line
            while ( (line = bufferedReader.readLine()) != null )
            {
                // Save the line in the StringBuffer
                fileContents.append(line).append(System.getProperty("line.separator"));
            }
        }
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "File not found");
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "IOException : "+e.getMessage());
        }

        m_text = fileContents.toString();
    }

    /**
     * will override any existing files
     */
    public void WriteFile(String p_path)
    {
        BufferedWriter writer = null;
        try
        {
            // Create temporary file
            File logFile = new File(p_path);

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(this.m_text);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                // Always close the writer
                writer.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
