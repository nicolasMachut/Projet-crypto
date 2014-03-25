package project_crypto.Models;

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
        System.out.println("String to crypt : "+this.m_readableString);
        this.m_readableString = p_textToCrypt;
        String permutString;
        for(int i = 0; i < this.m_readableString.length(); i++)
        {
            if(!association.containsKey(String.valueOf(this.m_readableString.charAt(i))))
            {
                permutString = this.GetVacantRandomLetter();
                //System.out.println("On ajoute la lettre : "+this.m_stringToCrypt.charAt(i)+" permutée avec : "+permutString);
                this.association.put(String.valueOf(this.m_readableString.charAt(i)), permutString);
            }

            this.m_cryptedString += this.association.get(String.valueOf(this.m_readableString.charAt(i)));
        }
        System.out.println("Crypted String : "+this.m_cryptedString);
    }


    //This method return a letter in alphabet's tab which is not in this.association
    private String GetVacantRandomLetter()
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



}
