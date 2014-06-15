package project_crypto.Models;

import Library.Alphabet;
import Library.WordToNormalize;

import java.util.HashMap;
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
    private HashMap<String, String> association = new HashMap<String, String>();
    private Map<String,Double> m_alphabet;
    //End variable

    public Permutation()
    {
        super();
        SetLang("fr");
    }


    public void SetLang(String p_lang)
    {
        if (p_lang.equals("fr"))
        {
            m_alphabet = new Alphabet().GetFrSortedDesc();
        }
        else if (p_lang.equals("en"))
        {
            m_alphabet = new Alphabet().GetEnSortedDesc();
        }
    }

    //This method crypt a string using permutation crypting
    public void Crypting(String p_textToCrypt)
    {
        this.m_readableString = new WordToNormalize().normalize(p_textToCrypt);
        String permutString;

        for(int i = 0; i < this.m_readableString.length(); i++)
        {
            if(!association.containsKey(String.valueOf(this.m_readableString.charAt(i))))
            {
                permutString = this.GetVacantRandomLetter();

                this.association.put(String.valueOf(this.m_readableString.charAt(i)), permutString);
            }

            this.m_cryptedString += this.association.get(String.valueOf(this.m_readableString.charAt(i)));
        }
    }


    //This method return a letter in alphabet's tab which is not in this.association
    public String GetVacantRandomLetter()
    {
        String letter = "";
        Boolean find = false;
        int random = GetRandom();

        String[] latinLetters = m_alphabet.keySet().toArray(new String[m_alphabet.keySet().size()]);

        while(!find)
        {
            if(this.association.containsValue(latinLetters[random]))
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
        return this.association;
    }

    public String lol() {
        String uncryptedString = this.m_cryptedString;
        String tried = uncryptedString.replace("D","e");
        tried = tried.replace("E","a");


        return tried.toUpperCase();
    }

    /**
     * NON AUTO
     * @param p_textToUncrypt
     * @param p_alphaUncrypt
     */
    public void Uncrypting(String p_textToUncrypt, HashMap<String, String> p_alphaUncrypt)
    {
        this.m_cryptedString = new WordToNormalize().normalizeNumber(p_textToUncrypt);
        this.m_readableString = "";
        String letter;

        for(int itext = 0; itext <this.m_cryptedString.length(); itext++)
        {
            letter = Character.toString( this.m_cryptedString.charAt(itext) );

            if(p_alphaUncrypt.containsKey(letter))
            {

            }
            else
            {

            }

            //this.m_readableString += ;
        }
    }

    /**
     * AUTOMATIQUE
     * @param p_textToUncrypt
     */
    public void Uncrypting(String p_textToUncrypt)
    {
        this.m_cryptedString = new WordToNormalize().normalizeNumber(p_textToUncrypt);
        this.m_readableString = "";
        String letter;


    }
}
