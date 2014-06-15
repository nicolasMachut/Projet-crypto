package project_crypto.Models;

import Library.FrequencyAnalyse;
import Library.WordToNormalize;

import java.util.HashMap;


/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 *
 * Date : 24/02/2014.
 *
 * * This class crypt and decrypt whith Caesar Crypting as it's name suggests !
 */

public class Caesar extends Crypting
{

    /* ===============================================================================================================
    * Functions and Methods
    * ============================================================================================================ */
    public Caesar()
    {
        super();
    }


    public void Crypting(String p_textToCrypt, int p_crytingKey)
    {
        this.m_readableString = new WordToNormalize().normalize(p_textToCrypt);
        // Encrypt text letter by letter
        for(int iText = 0; iText < this.m_readableString.length(); iText++)
        {
            for(int iAlphabet = 0; iAlphabet < m_alphabet.GetLatin().length; iAlphabet++)
            {
                if( m_alphabet.GetLatin()[iAlphabet].equals( Character.toString(this.m_readableString.charAt(iText)) ) )
                {
                    this.m_cryptedString += m_alphabet.GetLatin()[(iAlphabet+p_crytingKey)% m_alphabet.GetLatin().length];
                }
            }
        }
    }

     public int GetCryptingKey(String p_textToUncrypt)
    {
        FrequencyAnalyse fileGiven = new FrequencyAnalyse(p_textToUncrypt);
        this.m_cryptedString =  new WordToNormalize().normalize(p_textToUncrypt);
        HashMap<String, Double> frequency = fileGiven.CalculCharFrequencyMono();

        int key = 0;
        for(String KeyH : this.m_alphabet.GetFrequencySortedDesc().keySet())
        {
            for(int i = 0; i < m_alphabet.GetLatin().length; i++)
            {
                if(m_alphabet.GetLatin()[i].equals(fileGiven.getMostUsedChar(frequency)))
                {
                    // Compare with most used letter in the alphabet, not in the text
                    if((i+1) >  this.m_alphabet.GetIndexOfALetterInAlphabet(KeyH))
                    {
                        key = (i+1) - this.m_alphabet.GetIndexOfALetterInAlphabet(KeyH);
                    }
                    else
                    {
                        key = this.m_alphabet.GetIndexOfALetterInAlphabet(KeyH) - (i+1);
                    }
                }
            }
        }

        return key;
    }


    public void Uncrypting(String p_textToUncrypt)
    {
        //Find Key
        int uncryptedKey = this.GetCryptingKey(p_textToUncrypt);

        UncryptLetters(uncryptedKey);
    }


    public void Uncrypting(String p_textToUncrypt, int uncryptedKey)
    {
        this.m_cryptedString =  new WordToNormalize().normalize(p_textToUncrypt);

        UncryptLetters(uncryptedKey);
    }


    private void UncryptLetters(int uncryptedKey)
    {
        // Uncrypt text letter by letter
        for(int itext = 0; itext <this.m_cryptedString.length(); itext++)
        {
            for(int iAlphabet = 0; iAlphabet < m_alphabet.GetLatin().length; iAlphabet++)
            {
                if( m_alphabet.GetLatin()[iAlphabet].equals( Character.toString(this.m_cryptedString.charAt(itext)) ) )
                {
                    this.m_readableString += m_alphabet.GetLatin()[ModuloPositive(iAlphabet - uncryptedKey, m_alphabet.GetLatin().length)];
                }
            }
        }
    }
}
