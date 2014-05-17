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
    private static HashMap<String, HashMap<String, Double>> m_monogramme;
    private static HashMap<String, HashMap<String, Double>> m_bigramme;
    private static HashMap<String, HashMap<String, Double>> m_trigramme;


    // Tempo
    HashMap<String, Double> monogrammeTempo = new HashMap<String, Double>();



    public Alphabet()
    {
        m_latin = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T" ,"U", "V", "W", "X", "Y", "Z"};

        m_monogramme = new HashMap<String, HashMap<String, Double>>();
        m_bigramme = new HashMap<String, HashMap<String, Double>>();
        m_trigramme = new HashMap<String, HashMap<String, Double>>();

        HashMap<String, Double> monogrammeFr = new HashMap<String, Double>();
        HashMap<String, Double> monogrammeEn = new HashMap<String, Double>();

        HashMap<String, Double> bigrammeFr = new HashMap<String, Double>();
        HashMap<String, Double> bigrammeEn = new HashMap<String, Double>();

        HashMap<String, Double> trigrammeFr = new HashMap<String, Double>();
        HashMap<String, Double> trigrammeEn = new HashMap<String, Double>();

        List<String> impossibleBigramme;

        monogrammeFr.put("A", 8.40);
        monogrammeFr.put("B", 1.06);
        monogrammeFr.put("C", 3.03);
        monogrammeFr.put("D", 4.18);
        monogrammeFr.put("E", 17.26);
        monogrammeFr.put("F", 1.12);
        monogrammeFr.put("G", 1.27);
        monogrammeFr.put("H", 0.92);
        monogrammeFr.put("I", 7.34);
        monogrammeFr.put("J", 0.31);
        monogrammeFr.put("K", 0.05);
        monogrammeFr.put("L", 6.01);
        monogrammeFr.put("M", 2.96);
        monogrammeFr.put("N", 7.13);
        monogrammeFr.put("O", 5.26);
        monogrammeFr.put("P", 3.01);
        monogrammeFr.put("Q", 0.99);
        monogrammeFr.put("R", 6.55);
        monogrammeFr.put("S", 8.08);
        monogrammeFr.put("T", 7.07);
        monogrammeFr.put("U", 5.74);
        monogrammeFr.put("V", 1.32);
        monogrammeFr.put("W", 0.04);
        monogrammeFr.put("X", 0.45);
        monogrammeFr.put("Y", 0.30);
        monogrammeFr.put("Z", 0.12);

        monogrammeEn.put("A", 8.08);
        monogrammeEn.put("B", 1.67);
        monogrammeEn.put("C", 3.18);
        monogrammeEn.put("D", 3.99);
        monogrammeEn.put("E", 12.56);
        monogrammeEn.put("F", 2.17);
        monogrammeEn.put("G", 1.80);
        monogrammeEn.put("H", 5.27);
        monogrammeEn.put("I", 7.24);
        monogrammeEn.put("J", 0.14);
        monogrammeEn.put("K", 0.63);
        monogrammeEn.put("L", 4.04);
        monogrammeEn.put("M", 2.60);
        monogrammeEn.put("N", 7.38);
        monogrammeEn.put("O", 7.47);
        monogrammeEn.put("P", 1.91);
        monogrammeEn.put("Q", 0.09);
        monogrammeEn.put("R", 6.42);
        monogrammeEn.put("S", 6.59);
        monogrammeEn.put("T", 9.15);
        monogrammeEn.put("U", 2.79);
        monogrammeEn.put("V", 1.00);
        monogrammeEn.put("W", 1.89);
        monogrammeEn.put("X", 0.21);
        monogrammeEn.put("Y", 1.65);
        monogrammeEn.put("Z", 0.07);

        bigrammeFr.put("ES", 3.318);
        bigrammeFr.put("DE", 2.409);
        bigrammeFr.put("LE", 2.366);
        bigrammeFr.put("EN", 2.121);
        bigrammeFr.put("RE", 1.885);
        bigrammeFr.put("NT", 1.694);
        bigrammeFr.put("ON", 1.646);
        bigrammeFr.put("ER", 1.514);
        bigrammeFr.put("TE", 1.484);
        bigrammeFr.put("EL", 1.382);
        bigrammeFr.put("AN", 1.378);
        bigrammeFr.put("SE", 1.377);
        bigrammeFr.put("ET", 1.307);
        bigrammeFr.put("LA", 1.270);
        bigrammeFr.put("AI", 1.255);
        bigrammeFr.put("IT", 1.243);
        bigrammeFr.put("ME", 1.099);
        bigrammeFr.put("OU", 1.086);
        bigrammeFr.put("EM", 1.056);
        bigrammeFr.put("IE", 1.030);

        bigrammeEn.put("TH", 3.020);
        bigrammeEn.put("HE", 2.496);
        bigrammeEn.put("IN", 2.078);
        bigrammeEn.put("ER", 1.821);
        bigrammeEn.put("AN", 1.676);
        bigrammeEn.put("RE", 1.467);
        bigrammeEn.put("ES", 1.345);
        bigrammeEn.put("ON", 1.318);
        bigrammeEn.put("ST", 1.290);
        bigrammeEn.put("NT", 1.267);
        bigrammeEn.put("EN", 1.243);
        bigrammeEn.put("ED", 1.187);
        bigrammeEn.put("ND", 1.160);
        bigrammeEn.put("AT", 1.131);
        bigrammeEn.put("TI", 1.115);
        bigrammeEn.put("TE", 1.062);
        bigrammeEn.put("OR", 1.023);
        bigrammeEn.put("AR", 9.48);
        bigrammeEn.put("HA", 9.48);
        bigrammeEn.put("OF", 9.45);

        trigrammeFr.put("ENT", 0.900);
        trigrammeFr.put("LES", 0.801);
        trigrammeFr.put("EDE", 0.630);
        trigrammeFr.put("DES", 0.609);
        trigrammeFr.put("QUE", 0.607);
        trigrammeFr.put("AIT", 0.542);
        trigrammeFr.put("LLE", 0.509);
        trigrammeFr.put("SDE", 0.508);
        trigrammeFr.put("ION", 0.477);
        trigrammeFr.put("EME", 0.472);
        trigrammeFr.put("ELA", 0.437);
        trigrammeFr.put("RES", 0.432);
        trigrammeFr.put("MEN", 0.425);
        trigrammeFr.put("ESE", 0.416);
        trigrammeFr.put("DEL", 0.404);
        trigrammeFr.put("ANT", 0.397);
        trigrammeFr.put("TIO", 0.383);
        trigrammeFr.put("PAR", 0.360);
        trigrammeFr.put("ESD", 0.351);
        trigrammeFr.put("TDE", 0.350);

        trigrammeEn.put("THE", 2.069);
        trigrammeEn.put("AND", 0.819);
        trigrammeEn.put("ING", 0.607);
        trigrammeEn.put("ENT", 0.487);
        trigrammeEn.put("ION", 0.428);
        trigrammeEn.put("NTH", 0.381);
        trigrammeEn.put("TER", 0.367);
        trigrammeEn.put("INT", 0.357);
        trigrammeEn.put("OFT", 0.357);
        trigrammeEn.put("THA", 0.355);
        trigrammeEn.put("ERE", 0.355);
        trigrammeEn.put("TIO", 0.335);
        trigrammeEn.put("HER", 0.327);
        trigrammeEn.put("FTH", 0.321);
        trigrammeEn.put("ETH", 0.315);
        trigrammeEn.put("ATI", 0.307);
        trigrammeEn.put("HAT", 0.295);
        trigrammeEn.put("ATE", 0.286);
        trigrammeEn.put("STH", 0.281);
        trigrammeEn.put("EST", 0.277);


        m_monogramme.put("fr", monogrammeFr);
        m_monogramme.put("en", monogrammeEn);

        m_bigramme.put("fr", bigrammeFr);
        m_bigramme.put("en", bigrammeEn);

        m_trigramme.put("fr", trigrammeFr);
        m_trigramme.put("en", trigrammeEn);
    }

    /* ===============================================================================================================
     * Functions and Methods
     * ============================================================================================================ */

    public String[] GetLatin()
    {
        return m_latin;
    }

    public Map<String, Double> GetFrequencySortedDesc()
    {
        // return MapManager.sortByComparator(m_frequency, MapManager.DESC);
        // TEMPO
        return MapManager.sortByComparator(monogrammeTempo, MapManager.DESC);
    }

    public Map<String, Double> GetFrSortedDesc()
    {
        // Début tempo : test pour analyse freq
        // A discuter avec Nicolas
        Alphabet v_alpha = new Alphabet();
        v_alpha.InitFr();
        HashMap<String, Double> m_frenchFrequency = v_alpha.GetFrequency();
        // Fin tempo

        return MapManager.sortByComparator(m_frenchFrequency, MapManager.DESC);
    }

    public Map<String, Double> GetEnSortedDesc()
    {
        // Début tempo : test pour analyse freq
        // A discuter avec Nicolas
        Alphabet v_alpha = new Alphabet();
        v_alpha.InitEn();
        HashMap<String, Double> m_englishFrequency = v_alpha.GetFrequency();
        // Fin tempo

        return MapManager.sortByComparator(m_englishFrequency, MapManager.DESC);
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

    // TEMPO
    private void InitFr()
    {

    }

    private void InitEn()
    {

    }

    private HashMap<String,Double> GetFrequency()
    {
        return new HashMap<String,Double>();
    }
}
