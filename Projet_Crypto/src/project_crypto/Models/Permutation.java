package project_crypto.Models;

import Library.Alphabet;
import Library.FrequencyAnalyse;
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
    // m_association : HashMap < letterWeCanSee, letterWeWantToReplaceWith>
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
        super.setLanguage(p_lang);

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

    public HashMap<String, String> GetAssociation()
    {
        return this.m_association;
    }


    // Manually
    public void Uncrypting(String p_textToUncrypt, List<String> p_alphabeTryUser)
    {
        this.m_cryptedString = new WordToNormalize().normalize(p_textToUncrypt);
        this.m_readableString = m_cryptedString;

        SetAssociationUncrypt(p_alphabeTryUser);

        for(String letterKey : m_association.keySet())
        {
            this.m_readableString = this.m_readableString.replace( letterKey, this.m_association.get(letterKey).toLowerCase() );
        }

        this.m_readableString = this.m_readableString.toUpperCase();
    }

    public void SetAssociationEncrypt(List<String> p_alphabeTryUser)
    {
        this.m_association.clear();
        // Only take strings
        String[] latinLetters = new Alphabet().GetLatin();

        // Associate strings
        m_association.clear();

        for(int iLetter = 0; iLetter < p_alphabeTryUser.size(); iLetter++)
        {
            m_association.put(p_alphabeTryUser.get(iLetter), latinLetters[iLetter]);
        }
    }

    // Automatically()
    public void Uncrypting(String p_textToUncrypt)
    {
        this.m_cryptedString = new WordToNormalize().normalize(p_textToUncrypt);
        this.m_readableString = m_cryptedString;

        SetAssociationUncrypt();

        for(String letterKey : m_association.keySet())
        {
            this.m_readableString = this.m_readableString.replace( letterKey, this.m_association.get(letterKey).toLowerCase() );
        }

        this.m_readableString = this.m_readableString.toUpperCase();
    }

    // Associate frequency analysis with most used letters in the chosen language
    private void SetAssociationUncrypt()
    {
        this.m_association.clear();

        String[] latinLetters = m_alphabetInLang.keySet().toArray(new String[m_alphabetInLang.keySet().size()]);

        FrequencyAnalyse frequencyAnalyse = new FrequencyAnalyse(this.m_cryptedString);
        HashMap<String, Double> frequencyScore = frequencyAnalyse.CalculCharFrequencyMono();

        int iLetter = 0;
        for(String letterKey : frequencyScore.keySet())
        {
            this.m_association.put(letterKey , latinLetters[iLetter]);

            iLetter++;
        }
    }

    public void SetAssociationUncrypt(List<String> p_alphabeTryUser)
    {
        this.m_association.clear();
        // Only take strings
        String[] latinLetters = new Alphabet().GetLatin();

        // Associate strings
        m_association.clear();

        for(int iLetter = 0; iLetter < p_alphabeTryUser.size(); iLetter++)
        {
            m_association.put(latinLetters[iLetter], p_alphabeTryUser.get(iLetter));
        }
    }
}
