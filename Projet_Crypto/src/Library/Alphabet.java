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
    private static HashMap<String, Double> m_frenchFrequency;
    private static HashMap<String, Double> m_englishFrequency;
    private static int[] frenchMostUsedLetterIndex = {5, 19, 1, 9};
    private static int[] englishMostUsedLetterIndex = {};

    public Alphabet()
    {
        m_latin = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T" ,"U", "V", "W", "X", "Y", "Z"};
        m_frenchFrequency = InitFrequencyFr();
        m_englishFrequency = InitFrequencyEn();
    }

    /* ===============================================================================================================
     * Functions and Methods
     * ============================================================================================================ */

    public String[] GetLatin()
    {
        return m_latin;
    }

    public int[] getFrenchMostUsedLetter()
    {
        return frenchMostUsedLetterIndex;
    }

    /**
     *  Associate a letter with its apparition's frequency in percentage
     *  French version
     *  Source : http://www.apprendre-en-ligne.net
     */
    private HashMap<String, Double> InitFrequencyFr()
    {
        m_frenchFrequency = new HashMap<String, Double>();

        m_frenchFrequency.put("A", 8.40);
        m_frenchFrequency.put("B", 1.06);
        m_frenchFrequency.put("C", 3.03);
        m_frenchFrequency.put("D", 4.18);
        m_frenchFrequency.put("E", 17.26);
        m_frenchFrequency.put("F", 1.12);
        m_frenchFrequency.put("G", 1.27);
        m_frenchFrequency.put("H", 0.92);
        m_frenchFrequency.put("I", 7.34);
        m_frenchFrequency.put("J", 0.31);
        m_frenchFrequency.put("K", 0.05);
        m_frenchFrequency.put("L", 6.01);
        m_frenchFrequency.put("M", 2.96);
        m_frenchFrequency.put("N", 7.13);
        m_frenchFrequency.put("O", 5.26);
        m_frenchFrequency.put("P", 3.01);
        m_frenchFrequency.put("Q", 0.99);
        m_frenchFrequency.put("R", 6.55);
        m_frenchFrequency.put("S", 8.08);
        m_frenchFrequency.put("T", 7.07);
        m_frenchFrequency.put("U", 5.74);
        m_frenchFrequency.put("V", 1.32);
        m_frenchFrequency.put("W", 0.04);
        m_frenchFrequency.put("X", 0.45);
        m_frenchFrequency.put("Y", 0.30);
        m_frenchFrequency.put("Z", 0.12);

        return m_frenchFrequency;
    }

    /**
     *  Associate a letter with its apparition's frequency in percentage
     *  English version
     *  Source : http://www.apprendre-en-ligne.net
     */
    private HashMap<String, Double> InitFrequencyEn()
    {
        m_englishFrequency = new HashMap<String, Double>();

        m_englishFrequency.put("A", 8.08);
        m_englishFrequency.put("B", 1.67);
        m_englishFrequency.put("C", 3.18);
        m_englishFrequency.put("D", 3.99);
        m_englishFrequency.put("E", 12.56);
        m_englishFrequency.put("F", 2.17);
        m_englishFrequency.put("G", 1.80);
        m_englishFrequency.put("H", 5.27);
        m_englishFrequency.put("I", 7.24);
        m_englishFrequency.put("J", 0.14);
        m_englishFrequency.put("K", 0.63);
        m_englishFrequency.put("L", 4.04);
        m_englishFrequency.put("M", 2.60);
        m_englishFrequency.put("N", 7.38);
        m_englishFrequency.put("O", 7.47);
        m_englishFrequency.put("P", 1.91);
        m_englishFrequency.put("Q", 0.09);
        m_englishFrequency.put("R", 6.42);
        m_englishFrequency.put("S", 6.59);
        m_englishFrequency.put("T", 9.15);
        m_englishFrequency.put("U", 2.79);
        m_englishFrequency.put("V", 1.00);
        m_englishFrequency.put("W", 1.89);
        m_englishFrequency.put("X", 0.21);
        m_englishFrequency.put("Y", 1.65);
        m_englishFrequency.put("Z", 0.07);

        return m_englishFrequency;
    }

    public Map<String, Double> GetFrSortedDesc()
    {
        return MapManager.sortByComparator(m_frenchFrequency, MapManager.DESC);
    }

    public Map<String, Double> GetEnSortedDesc()
    {
        return MapManager.sortByComparator(m_englishFrequency, MapManager.DESC);
    }
}
