package project_crypto.Models;

import Library.WordToNormalize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 *
 * Date : 16/03/14.
 *
 * * This class crypt and decrypt whith triangular transposition
 * * Unforunatly it doesn't find the key word... Working on it...
 */
public class Triangular extends Crypting
{
    private int m_nbLines;
    private int m_nbColumns;
    private int m_nbLetters;

    public Triangular()
    {
        super();
    }

    @Override
    public void SetNextKeyAuto()
    {

    }

    @Override
    public void SaveKeyInMemento() {

    }

     /* ===============================================================================================================
     * Public functions and Methods
     * ============================================================================================================ */

    /**
     * Crypting :
     * This code will create the triangle
     * Then, it will handle the key word so we obtain the column's order
     * Finally, it will run throught the columns in the order found previously
     * and vertically from the top to the bottom
     * @param p_key : key word chosen by the user
     */
    public void Crypting(String p_textToCrypt, String p_key)
    {
        this.m_readableString = new WordToNormalize().Normalize(p_textToCrypt);

        // Need nb letters lines and columns
        FindInfo(this.m_readableString);

        // Fill the triangle
        String[][] triangle = BuildTriangleToEncrypt();

        // Find the columns order
        List<Integer> columnOrder = FindColumnOrder(p_key);

        // Encrypt thanks to the column order
        for (Integer aColumnOrder : columnOrder) {
            for (int iLig = 0; iLig < m_nbLines; iLig++) {
                this.m_cryptedString += triangle[iLig][aColumnOrder];
            }
        }

        //System.out.println("crypter : "+m_readableString);
        //ShowTriangle(triangle);
    }

    // Getters for the view
    public int GetNbLines()
    {
        return m_nbLines;
    }
    public int GetNbColumns()
    {
        return m_nbColumns;
    }
    public int GetNbLetters()
    {
        return m_nbLetters;
    }

    /**
     * Uncrypting :
     * This code will find the column's order thanks to the key word and the encrypting text length
     * In the same time, it will deduce the last letter's column's index
     * Then it will build the triangle
     * Finally, it will run throught the columns in the order found previously
     * and vertically from the top to the bottom
     * @param p_key : key word chosen by the user
     */
    public void Uncrypting(String p_textToUncrypt, String p_key)
    {
        this.m_cryptedString = new WordToNormalize().Normalize(p_textToUncrypt);

        // Need nb letters lines and columns
        FindInfo(this.m_cryptedString);

        // Find the columns order
        List<Integer> columnOrder = FindColumnOrder(p_key);

        // Find the last letter's column's index of the encrypted string
        int nbLettersDoneBeforeLine = ((m_nbLines - 1) * (m_nbLines - 1) + (m_nbLines - 1)) / 2; // (square are + diago) / 2
        int lasstLetterCol = 2 * (m_nbLetters - (nbLettersDoneBeforeLine + 1));

        // Fill the triangle
        String triangle[][] = BuildTriangleToUncrypt(lasstLetterCol, columnOrder);
        //System.out.println("Décryptage : "+m_cryptedString);
        //ShowTriangle(triangle);

        // We uncrypt thanks to the column order
        for (int iLig = 0; iLig < m_nbLines; iLig++)
        {
            for (int iCol = 0; iCol < m_nbColumns; iCol++)
            {
                m_readableString = m_readableString + triangle[iLig][iCol];
            }
        }
    }

    /* ===============================================================================================================
     * Private functions and Methods
     * ============================================================================================================ */

    /**
     * Create the encrypting triangle
     * @return an array (so a rectangle or square) with the triangle in it
     */
    private String[][] BuildTriangleToEncrypt()
    {
        String[][] triangle = new String[m_nbLines][m_nbColumns];

        // Initialize the triangle
        for (int iLig = 0; iLig < m_nbLines; iLig++)
        {
            for (int iCol = 0; iCol < m_nbColumns; iCol++)
            {
                triangle[iLig][iCol] = "";
            }
        }

        // Fill the triangle with the string to Crypt
        int base = m_nbLines - 1; // column of the higher/first letter of the pyramid
        int iLig = 0; // first line's index
        int iCol = base; // begin with the base's column obviously

        for (int iLetter = 0; iLetter < m_nbLetters; iLetter++)
        {
            triangle[iLig][iCol] = triangle[iLig][iCol] = String.valueOf(this.m_readableString.charAt(iLetter));
            // Do we have to change the line ?
            // nb letters max at the end of this line = (iLig + 1) * (iLig + 1) + (iLig + 1)) / 2
            if (iLetter + 1 == ((iLig + 1) * (iLig + 1) + (iLig + 1)) / 2)
            {
                iLig++; // Add a line
                iCol = base - iLig; // the column of the new line's first letter
            }
            else
            {
                iCol += 2; // same line, shift two rows after
            }

            // Complete the string with random letters
            // if the last line is not full
            if( iLetter == (m_nbLetters -1) && ( iLig < m_nbLines )  )
            {
                int iFillUp = iCol;

                while ( iFillUp  <= m_nbColumns )
                {
                    if(iFillUp < m_nbColumns)
                    {
                        triangle[iLig][iFillUp] = GetRandomLetter();
                    }
                    else
                    {
                        triangle[iLig][m_nbColumns] = GetRandomLetter();
                    }
                    iFillUp += 2;
                }
            }
        }

        return triangle;
    }

    /**
     * Create the uncrypting triangle
     * @param p_lastLetterCol : last letter's column
     * @param p_columnOrder : list of the columns in the key word's order
     * @return an array (so a rectangle or square) with the triangle in it
     *
     * NOTE : public for the view
     */
    public String[][] BuildTriangleToUncrypt(int p_lastLetterCol, List<Integer> p_columnOrder)
    {
        // Initialize the triangle
        String triangle[][] = new String[m_nbLines][m_nbColumns];
        for (int iLig = 0; iLig < m_nbLines; iLig++)
        {
            for (int iCol = 0; iCol < m_nbColumns; iCol++)
            {
                triangle[iLig][iCol] = "";
            }
        }

        // Find the position of the higher letter = the base's column
        int base = m_nbLines - 1;

        // Index for the encrypt string
        int iCol; // Initialize column's index
        int iEncrypt = 0; // Encrypted string's index

        // Deal with columns in the key word's order
        for (Integer aP_columnOrder : p_columnOrder) {
            // Get column the column's index
            iCol = aP_columnOrder;

            // Run through lines, beginning at the interesting index (in short, not null)
            for (int iLig = abs(base - iCol); iLig < m_nbLines; iLig += 2) {
                // Careful : on the last line, there is no letter after the last letter's column
                if ((iLig < m_nbLines - 1) || ((iLig == m_nbLines - 1) && (iCol <= p_lastLetterCol))) {
                    // Put the letter in the triangle
                    triangle[iLig][iCol] = String.valueOf(m_cryptedString.charAt(iEncrypt));
                    // Next letter please !
                    iEncrypt++;
                }
            }
        }

        return triangle;
    }

    /**
     * Find the column's order we have to take in order to (en/un)crypt the text
     * First, take the key word
     * Then, remove duplicated letters
     * Now, put them in the alphabetic order
     * Finally, save the indexes in the List
     * @param p_key : key word chosen by the user
     * @return column's order
     *
     * NOTE : public for the view
     */
    public List<Integer> FindColumnOrder(String p_key)
    {
        // Create the encrypting key
        p_key = CreateKey(p_key);

        // Key without duplicated letters and in alphabetic order
        String sortedKey = CreateOrderedKey(p_key);

        // Store the column order for the encrypting text
        List<Integer> columnOrder = new ArrayList<Integer>();
        //int index = 0;
        for (int iKey = 0; iKey < sortedKey.length(); iKey++)
        {
            for (int k = 0; k < m_nbColumns; k++)
            {
                if (sortedKey.charAt(iKey) == p_key.charAt(k))
                {
                    columnOrder.add(k);
                }
            }
        }

        return columnOrder;
    }

    /**
     * Create the key that fills the column thanks to a word
     * Repeat the key while not every column is linked with a letter of the key word
     * @param p_key : word chosen by the user
     * @return key for the triangle
     */
    private String CreateKey(String p_key)
    {
        String key = "";
        int keyLenght = p_key.length();

        for (int iCol = 0; iCol < m_nbColumns; iCol++)
        {
            key += String.valueOf(p_key.charAt(iCol % keyLenght));
        }

        return key;
    }

    /**
     * Create a key without duplicated letters and in alphabetic order
     * Help = RemoveDuplicatedLetters + SortAlphaBetaOrder
     * @param p_text : text to deal with
     * @return cleaned key in order to find the column's order
     */
    private String CreateOrderedKey(String p_text)
    {
        String cleanKey = p_text;
        cleanKey = RemoveDuplicatedLetters(cleanKey);
        cleanKey = SortAlphaBetaOrder(cleanKey);
        return cleanKey;
    }

    /**
     * Remove duplicated letters
     * @param p_text : text we want to deal with
     * @return text without duplicated letters
     */
    public String RemoveDuplicatedLetters(String p_text) {
        String data = "";
        for (int iText = 0; iText < p_text.length(); iText++) {
            if (!data.contains(String.valueOf(p_text.charAt(iText)))) {
                data += String.valueOf(p_text.charAt(iText));
            }
        }
        return data;
    }

    /**
     * Sort letters in the alphabetic order
     * @param p_text : text we want to deal with
     * @return array of letters in the alphabetic order
     */
    public String SortAlphaBetaOrder(String p_text)
    {
        char[] chars = p_text.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * Return a random letter from the alphabet
     */
    private String GetRandomLetter()
    {
        int random = GetRandom();
        return this.m_alphabet.GetLatin()[random];
    }

     /* ===============================================================================================================
     * Calculate
     * ============================================================================================================ */

    /**
     * How many letters are there in the text ?
     * How many lines do we need  ?
     * How many columns do we need ?
     * @param p_text : what do we have to use ?
     */
    private void FindInfo(String p_text)
    {
        m_nbLetters = p_text.length();
        m_nbLines = CalculateNbLines(m_nbLetters);
        m_nbColumns = CalculateNbColumns(m_nbLines);
    }

    /**
     * How many lines do we need  ?
     * While we don't arrive at the end of the text, we go on
     * Add one line when she is full of course
     * @param p_nbLetters : How many letters are there in the text ?
     * @return number of lines needed for the triangle
     */
    public int CalculateNbLines(int p_nbLetters)
    {
        int nbLine = 1;

        // nb letters max at the end of this line = ( nbLine * nbLine + nbLine ) / 2
        while ((nbLine * nbLine + nbLine) / 2 < p_nbLetters)
        {
            nbLine++;
        }

        return nbLine;
    }

    /**
     * How many columns do we need  ?
     * @param p_nbLines : How many lines are there in the triangle ?
     * @return number of rows needed for the triangle
     */
    public int CalculateNbColumns(int p_nbLines)
    {
        if(p_nbLines == 1)
        {
            return p_nbLines;
        }
        else
        {
            if (ModuloPositive((p_nbLines / 2), 2) == 0)
            {
                return 2 * p_nbLines;
            }
            else
            {
                return (2 * p_nbLines) - 1;
            }
        }
    }

    /* ===============================================================================================================
    * Vision for developpers : too afraid to erase
    * ============================================================================================================ */

    // Put there temporally
     private void ShowTriangle(String[][] p_triangle)
    {
        for (int iLig = 0; iLig < m_nbLines; iLig++) {
            for (int iCol = 0; iCol < m_nbColumns; iCol++)
            {
                if (p_triangle[iLig][iCol].equals(""))
                {
                    System.out.print("-"); // help us see the rows and lines
                }
                else
                {
                    System.out.print(p_triangle[iLig][iCol]);
                }
            }
            System.out.println("");
        }
    }

}
