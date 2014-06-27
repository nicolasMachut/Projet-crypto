package project_crypto.Models;

import Library.Alphabet;
import Library.WordToNormalize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nicolas on 10/03/14.
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 * machut.nicolas@gmail.com
 *
 * * This class crypt and decrypt whith Permutation Crypting as it's name suggests !
 */
public class Permutation extends Crypting{

    //Variable
    // <letterWantedForUncrypting, letterReadableFromAlphabet>
    private HashMap<String, String> m_association = new HashMap<String, String>();
    private Map<String,Double> m_alphabetInLang;
    //End variable

    public Permutation(String p_language)
    {
        super();
        setLanguage(p_language);
        SetLanguageAlphabetUsed(p_language);
    }


    public void SetLanguageAlphabetUsed(String p_lang)
    {
        if (p_lang.equals("fr"))
        {
            m_alphabetInLang = new Alphabet().GetFrSortedDesc();
        }
        else if (p_lang.equals("en"))
        {
            m_alphabetInLang = new Alphabet().GetEnSortedDesc();
        }
    }

    //This method crypt a string using permutation crypting
    public void Crypting(String p_textToCrypt)
    {
        this.m_readableString = new WordToNormalize().normalize(p_textToCrypt);
        String permutString;

        for(int i = 0; i < this.m_readableString.length(); i++)
        {
            if(!m_association.containsKey(String.valueOf(this.m_readableString.charAt(i))))
            {
                permutString = this.GetVacantRandomLetter();

                this.m_association.put(String.valueOf(this.m_readableString.charAt(i)), permutString);
            }

            this.m_cryptedString += this.m_association.get(String.valueOf(this.m_readableString.charAt(i)));
        }
    }


    //This method return a letter in alphabet's tab which is not in this.m_association
    public String GetVacantRandomLetter()
    {
        String letter = "";
        Boolean find = false;
        int random = GetRandom();

        String[] latinLetters = m_alphabetInLang.keySet().toArray(new String[m_alphabetInLang.keySet().size()]);

        while(!find)
        {
            if(this.m_association.containsValue(latinLetters[random]))
            {
                random = GetRandom();
            }
            else
            {
                letter = latinLetters[random];
                find = true;
            }
        }
        return letter;
    }

    public HashMap<String, String> getAssociation()
    {
        return this.m_association;
    }


    public void Uncrypting(String p_textToUncrypt, List<String> p_alphabeTryUser)
    {
        this.m_cryptedString = new WordToNormalize().normalize(p_textToUncrypt);
        this.m_readableString = m_cryptedString;

        setAssociation(p_alphabeTryUser);

        for(String letterKey : m_association.keySet())
        {
            this.m_readableString = this.m_readableString.replace( letterKey, this.m_association.get(letterKey).toLowerCase() );
        }

        this.m_readableString = this.m_readableString.toUpperCase();
    }

    public void setAssociation(List<String> p_alphabeTryUser)
    {
        // Only take strings
        String[] latinLetters = new Alphabet().GetLatin();

        // Associate strings
        m_association.clear();

        for(int iLetter = 0; iLetter < p_alphabeTryUser.size(); iLetter++)
        {
            m_association.put(p_alphabeTryUser.get(iLetter), latinLetters[iLetter]);
        }
    }

    /*

    public void setAssociationFrequency(List<String> p_alphabeTryUser)
    {
        // Only take strings
        String[] latinLetters = m_alphabetInLang.keySet().toArray(new String[m_alphabetInLang.keySet().size()]);

        // Associate strings
        // <letterWantedForUncrypting, letterReadableFromAlphabet>
        m_association.clear();

        for(int iLetter = 0; iLetter < p_alphabeTryUser.size(); iLetter++)
        {
            m_association.put(p_alphabeTryUser.get(iLetter), latinLetters[iLetter]);
        }
    }


    public void setAssociation(HashMap<String, Double> p_letters)
    {
        // Order
        // latinLetters already in order frequency DESC
        HashMap<String, Double> lettersOrder = (HashMap<String, Double>) MapManager.sortByComparator(p_letters, MapManager.DESC);

        // Only take strings
        String[] latinLetters = m_alphabetInLang.keySet().toArray(new String[m_alphabetInLang.keySet().size()]);
        String[] lettersTry = lettersOrder.keySet().toArray(new String[lettersOrder.keySet().size()]);

        // Associate strings
        // <letterWantedForUncrypting, letterReadableFromAlphabet>
        m_association.clear();


        for(int iLetter = 0; iLetter < lettersTry.length; iLetter++)
        {
            m_association.put( lettersTry[iLetter], latinLetters[iLetter] );
        }
    }
    */
}
