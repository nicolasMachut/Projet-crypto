package Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 *
 * Date :  27/03/14.
 */
public class LanguageDetection
{
    Alphabet alpha = new Alphabet();
    Map<String,Double> myAlphaEn = alpha.GetEnSortedDesc();
    Map<String,Double> myAlphaFr = alpha.GetFrSortedDesc();


    public Map<String, Double> SumFrequenceMostUsedCharInLanguage(String p_Text)
    {
        Map<String, Double> analyseLangue = new HashMap<String, Double>();
        Double totalEn = 0.00d,
               totalFr = 0.00d;

        HashMap<String,Double> nbAppearanceCharFr;
        HashMap<String,Double> nbAppearanceCharEn;
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

        totalEn = (totalEn*100)/100;
        totalFr = (totalFr*100)/100;

        analyseLangue.put("fr", totalFr);
        analyseLangue.put("en", totalEn);

        //retourne la valeur au centième
        return analyseLangue;
    }

    public String GetLanguage(String p_text)
    {
        Map<String, Double> analyseLangue = SumFrequenceMostUsedCharInLanguage(p_text);

        if(analyseLangue.get("fr") > analyseLangue.get("en"))
        {
            return "fr";
        }
        else
        {
            return "en";
        }
    }
}
