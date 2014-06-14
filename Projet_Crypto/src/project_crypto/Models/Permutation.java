package project_crypto.Models;

import Library.WordToNormalize;

import java.util.HashMap;

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
    //End variable

    public Permutation()
    {
        super();
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

        while(!find)
        {
            if(this.association.containsValue(this.m_alphabet.GetLatin()[random]))
            {
                random = GetRandom();
            }
            else
            {
                letter = this.m_alphabet.GetLatin()[random];
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

  /*  public HashMap<String,String> tryToUncrypt(String p_letterToUncrypt)
    {
        this.association.put(p_letterToUncrypt,GetVacantRandomLetter(p_letterToUncrypt))
    }*/
}
