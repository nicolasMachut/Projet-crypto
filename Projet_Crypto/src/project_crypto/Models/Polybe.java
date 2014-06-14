package project_crypto.Models;

import Library.WordToNormalize;

import java.util.HashMap;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 * Date : 12/03/14.
 *
 * This class crypt and decrypt whith Polybe Crypting as it's name suggests !
 */

public class Polybe extends Crypting
{
    //Variable :
    /**
     * HashMap of String keys and String values containing the association.
     */
    private HashMap<String, String> association = new HashMap<String, String>();

    //End variables

    //Constructor
    public Polybe()
    {
        super();
        this.association.put("A", "11");
        this.association.put("B", "12");
        this.association.put("C", "13");
        this.association.put("D", "14");
        this.association.put("E", "15");
        this.association.put("F", "21");
        this.association.put("G", "22");
        this.association.put("H", "23");
        this.association.put("I", "24");
        this.association.put("J", "25");
        this.association.put("K", "31");
        this.association.put("L", "32");
        this.association.put("M", "33");
        this.association.put("N", "34");
        this.association.put("O", "35");
        this.association.put("P", "41");
        this.association.put("Q", "42");
        this.association.put("R", "43");
        this.association.put("S", "44");
        this.association.put("T", "45");
        this.association.put("U", "51");
        this.association.put("V", "52");
        this.association.put("X", "53");
        this.association.put("Y", "54");
        this.association.put("Z", "55");
    }

    /**
     *  Crypting :
        This method run trough the string to crypt and associate each caracter to a number.
        The private hashMap variable containing association is declared in the constructor : it's this.association.
        The result is saved in the private String this.m_cryptedString.
     */
    public void Crypting(String p_textToCrypt)
    {
        this.m_readableString = new WordToNormalize().normalize(p_textToCrypt);

        for(int i = 0; i < this.m_readableString.length(); i++)
        {
            this.m_cryptedString += this.association.get(String.valueOf(this.m_readableString.charAt(i)));
        }
    }

    /**
     * Uncrypting : Run trough the String table returned by PutEachNumbersInArrayStrings and compare with the HashMap association
     * The Uncryted String is saved in the private String this.m_readableString
     */
    public void Uncrypting(String p_textToUncrypt)
    {
        this.m_cryptedString = new WordToNormalize().normalizeNumber(p_textToUncrypt);

        String[] coupleNumbers = this.PutEachNumbersInArrayStrings();

        for (String coupleNumber : coupleNumbers)
        {
            for (String key : this.association.keySet())
            {
                if (this.association.get(key).equals(coupleNumber))
                {
                    this.m_readableString += key;
                }
            }
        }
    }


    /**
     * PutEachNumbersInArrayStrings
     * @return : table of the crypted String. Each case contains a value recuperable in the HashMap association
     */
    public String[] PutEachNumbersInArrayStrings()
    {
        int j = 0;
        String[] tabEncryptedString = new String[CalculTableLenght()];

        for(int i = 0; i < this.m_cryptedString.length(); i += 2)
        {
            tabEncryptedString[j] = String.valueOf(this.m_cryptedString.charAt(i));

            if(i < this.m_cryptedString.length())
            {
                tabEncryptedString[j] += String.valueOf(this.m_cryptedString.charAt(i + 1));
            }

            j++;
        }

        return tabEncryptedString;
    }

    private int CalculTableLenght()
    {
        int tabLength = this.m_cryptedString.length()/2;

        if( ModuloPositive(this.m_cryptedString.length()/2, 2) == 1 )
        {
            tabLength++;
        }

        return tabLength;
    }

}
