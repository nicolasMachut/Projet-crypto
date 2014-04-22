package Library;

import java.util.*;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 *
 * Date : 01/03/14.
 *
 * Alphabet : latin exclusively because the subject only deals with french and english text
 * We have chosen to create an object for an organisation question and because we had to save the frequency of each letters
 * Moreover if a language must be add or if the letter's frequency change, it would be easier to deal with it
 */

public class Alphabet
{
    private static String[] m_latin;
    private static HashMap<String, Double> m_frequency;

    public Alphabet()
    {
        m_latin = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T" ,"U", "V", "W", "X", "Y", "Z"};
    }

    /* ===============================================================================================================
     * Functions and Methods
     * ============================================================================================================ */

    public void SetLanguage(String language)
    {
        if(language == "fr")
        {
            this.InitFr();
        }
        if(language == "en")
        {
            this.InitEn();
        }
    }

    private void InitFr() {

        m_frequency = new HashMap<String, Double>();

        m_frequency.put("A", 8.40);
        m_frequency.put("B", 1.06);
        m_frequency.put("C", 3.03);
        m_frequency.put("D", 4.18);
        m_frequency.put("E", 17.26);
        m_frequency.put("F", 1.12);
        m_frequency.put("G", 1.27);
        m_frequency.put("H", 0.92);
        m_frequency.put("I", 7.34);
        m_frequency.put("J", 0.31);
        m_frequency.put("K", 0.05);
        m_frequency.put("L", 6.01);
        m_frequency.put("M", 2.96);
        m_frequency.put("N", 7.13);
        m_frequency.put("O", 5.26);
        m_frequency.put("P", 3.01);
        m_frequency.put("Q", 0.99);
        m_frequency.put("R", 6.55);
        m_frequency.put("S", 8.08);
        m_frequency.put("T", 7.07);
        m_frequency.put("U", 5.74);
        m_frequency.put("V", 1.32);
        m_frequency.put("W", 0.04);
        m_frequency.put("X", 0.45);
        m_frequency.put("Y", 0.30);
        m_frequency.put("Z", 0.12);



    }

    private void InitEn() {
        m_frequency = new HashMap<String, Double>();

        m_frequency.put("A", 8.08);
        m_frequency.put("B", 1.67);
        m_frequency.put("C", 3.18);
        m_frequency.put("D", 3.99);
        m_frequency.put("E", 12.56);
        m_frequency.put("F", 2.17);
        m_frequency.put("G", 1.80);
        m_frequency.put("H", 5.27);
        m_frequency.put("I", 7.24);
        m_frequency.put("J", 0.14);
        m_frequency.put("K", 0.63);
        m_frequency.put("L", 4.04);
        m_frequency.put("M", 2.60);
        m_frequency.put("N", 7.38);
        m_frequency.put("O", 7.47);
        m_frequency.put("P", 1.91);
        m_frequency.put("Q", 0.09);
        m_frequency.put("R", 6.42);
        m_frequency.put("S", 6.59);
        m_frequency.put("T", 9.15);
        m_frequency.put("U", 2.79);
        m_frequency.put("V", 1.00);
        m_frequency.put("W", 1.89);
        m_frequency.put("X", 0.21);
        m_frequency.put("Y", 1.65);
        m_frequency.put("Z", 0.07);

    }
    public String[] GetLatin()
    {
        return m_latin;
    }

    public HashMap<String, Double> GetFrequency()
    {
        return m_frequency;
    }

    public Map<String, Double> GetFrequencySortedDesc()
    {
        return MapManager.sortByComparator(m_frequency, MapManager.DESC);
    }

    public int GetIndexOfALetterInAlphabet(String letter)
    {
        int result = 0;
        for(int i = 0; i < m_latin.length; i++)
        {
            if(letter == m_latin[i])
            {
                result = i;
            }
        }
        return result;
    }
}
