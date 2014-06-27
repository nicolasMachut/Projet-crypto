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
    public Polybe(String p_language)
    {
        super();

        setAssociation(p_language);
    }

    private void setAssociation(String p_language)
    {
        super.setLanguage(p_language);

        if (p_language.equals("fr"))
        {
            association = setAssociationFr();
        }
        else if (p_language.equals("en"))
        {
            association = setAssociationEn();
        }
    }

    private HashMap<String, String> setAssociationFr()
    {
        HashMap<String, String> associationFr = new HashMap<String, String>();

        associationFr.put("A", "11");
        associationFr.put("B", "12");
        associationFr.put("C", "13");
        associationFr.put("D", "14");
        associationFr.put("E", "15");
        associationFr.put("F", "21");
        associationFr.put("G", "22");
        associationFr.put("H", "23");
        associationFr.put("I", "24");
        associationFr.put("J", "25");
        associationFr.put("K", "31");
        associationFr.put("L", "32");
        associationFr.put("M", "33");
        associationFr.put("N", "34");
        associationFr.put("O", "35");
        associationFr.put("P", "41");
        associationFr.put("Q", "42");
        associationFr.put("R", "43");
        associationFr.put("S", "44");
        associationFr.put("T", "45");
        associationFr.put("U", "51");
        associationFr.put("V", "52");
        associationFr.put("X", "53");
        associationFr.put("Y", "54");
        associationFr.put("Z", "55");

        return associationFr;
    }

    private HashMap<String, String> setAssociationEn()
    {
        HashMap<String, String> associationEn = new HashMap<String, String>();

        associationEn.put("A", "11");
        associationEn.put("B", "12");
        associationEn.put("C", "13");
        associationEn.put("D", "14");
        associationEn.put("E", "15");
        associationEn.put("F", "21");
        associationEn.put("G", "22");
        associationEn.put("H", "23");
        associationEn.put("I", "24");
        associationEn.put("K", "25");
        associationEn.put("L", "31");
        associationEn.put("M", "32");
        associationEn.put("N", "33");
        associationEn.put("O", "34");
        associationEn.put("P", "35");
        associationEn.put("Q", "41");
        associationEn.put("R", "42");
        associationEn.put("S", "43");
        associationEn.put("T", "44");
        associationEn.put("U", "45");
        associationEn.put("V", "51");
        associationEn.put("W", "52");
        associationEn.put("X", "53");
        associationEn.put("Y", "54");
        associationEn.put("Z", "55");

        return associationEn;
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

        m_readableString = "";
        String[] coupleNumbers = this.PutEachNumbersInArrayStrings();

        for (String coupleNumber : coupleNumbers)
        {
            for (String key : this.association.keySet())
            {
                if (this.association.get(key).equals(coupleNumber))
                {
                    System.out.println(key+" ("+this.association.get(key)+") = "+coupleNumber);

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
