package Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nicolas on 27/03/14.
 */
public class LanguageDetection {

    /*
        A faire :
        Passer en parametre au constructeur d'alphabet (fr ou en) pour qu'il initialise les tablaeaux brigrammes et monogrammes dans la bonne langue
        Ensuite on fait Alphabet alphabet = new Alphabet("fr") et on a tout en francais


        Decryptage permutation :
        - Cas perticulier : 3 lettres d'affilé en francais, si ca existe alors la lettre en question est un e !
        - IMPORTANT : voir les trigrammes qui existent

        Phrases de test : Le capitaine vous souhaite un bon vol
                        : The captain wish you a great flighth

     */


    Alphabet alpha = new Alphabet();
    Map<String,Double> myAlphaEn = alpha.GetEnSortedDesc();
    Map<String,Double> myAlphaFr = alpha.GetFrSortedDesc();


    public Double SumFrequenceMostUsedCharInEnglish(String p_Text)
    {
        Double total = 0.00d;
        Double totalEn = 0.00d,
               totalFr = 0.00d;

        HashMap<String,Double> nbAppearanceCharFr = new HashMap<String, Double>();
        HashMap<String,Double> nbAppearanceCharEn = new HashMap<String, Double>();
        HashMap<String,String> charCorrespondance = new HashMap<String, String>();;

        FrequencyAnalyse Text = new FrequencyAnalyse(p_Text);
        nbAppearanceCharFr = Text.CharPresence();
        nbAppearanceCharEn = Text.CharPresence();

        // Analyse EN
        List keysAlphaEn = new ArrayList(myAlphaEn.keySet());
        int iAppearanceEn = 0;
        for(String key: nbAppearanceCharEn.keySet())
        {
            charCorrespondance.put(key, keysAlphaEn.get(iAppearanceEn).toString() );
            iAppearanceEn++;
        }

        // Analyse FR
        List keysAlphaFr = new ArrayList(myAlphaEn.keySet());
        int iAppearanceFr = 0;
        for(String key: nbAppearanceCharFr.keySet())
        {
            charCorrespondance.put(key, keysAlphaFr.get(iAppearanceFr).toString() );
            iAppearanceFr++;
        }

        for(String key: charCorrespondance.keySet())
        {
            totalEn += myAlphaEn.get(charCorrespondance.get(key))*nbAppearanceCharEn.get(key);
            totalFr += myAlphaFr.get(charCorrespondance.get(key))*nbAppearanceCharFr.get(key);
        }

        if(totalEn > totalFr)
        {
            total = totalEn;
        }else
        {
            total = totalFr;
        }

        //retourne la valeur au centième
        return (total*100)/100;
    }
}
