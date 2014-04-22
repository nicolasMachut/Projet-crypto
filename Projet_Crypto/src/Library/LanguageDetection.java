package Library;

import java.util.HashMap;
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


    public Double SumFrequenceMostUsedCharInEnglish(String p_Text) {
        Double total = 0.00d;
        Double totalEn = 0.00d,
               totalFr = 0.00d;

        HashMap<String,Double> nbAppearanceChar = new HashMap<String, Double>();
        HashMap<String,String> charCorrespondance = new HashMap<String, String>();;

        FrequencyAnalyse Text = new FrequencyAnalyse(p_Text);
        nbAppearanceChar = Text.CharPresence();/*

        for(String key: charCorrespondance.keySet())
        {
            for(String letter: myAlphaFr.keySet())
            {
                charCorrespondance.put(key,letter);
            }
        }
*/

/*

        for(String key: charCorrespondance.keySet())
        {
            for(String letter: myAlphaFr.keySet())
            {
                charCorrespondance.put(key,letter);
            }
        }
*/

        for(String key: nbAppearanceChar.keySet())
        {
          
        }

        System.out.println(charCorrespondance);

        for(String key: charCorrespondance.keySet())
        {
            totalEn += myAlphaEn.get(charCorrespondance.get(key))*nbAppearanceChar.get(key);
            totalFr += 0;//myAlphaFr.get(charCorrespondance.get(key))*nbAppearanceChar.get(key);
        }

        System.out.println("total En :"+totalEn);

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
