package Library;

import Library.Alphabet;

import java.text.Normalizer;
import java.util.Vector;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 *
 * Date : 24/02/2014
 *
 * Class that normalize the text for futur handling :
 * no accent, no space, all in upper case
 */

public class WordToNormalize
{
    /* ===============================================================================================================
    * Functions and Methods
    * ============================================================================================================ */

    /*
    *
    * New method to erase accent and/or special chars
    * */
    private static String eraseSpecialChars(String strToNormalize)
    {
        String strNormalized =strToNormalize.toUpperCase();
        strNormalized = Normalizer.normalize(strNormalized,Normalizer.Form.NFD).replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}+","");
        strNormalized = strNormalized.replaceAll("\\W","");
        strNormalized = strNormalized.replaceAll("\\d","");
        strNormalized = strNormalized.replaceAll("_","");

        return strNormalized;
    }

     public String normalize(String p_text)
    {
        // no accent, no space, all in upper case
        String text = eraseSpecialChars(p_text);
        return text;
    }
}
