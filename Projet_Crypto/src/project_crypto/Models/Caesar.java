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
        this.m_cryptedString =  new WordToNormalize().normalize(p_textToUncrypt);
        FrequencyAnalyse fileGivenFreq = new FrequencyAnalyse(p_textToUncrypt);
        HashMap<String, Double> frequency = fileGivenFreq.CalculCharFrequencyMono();

        int key = 0;

        String letterMostUsedLanguage = (String) this.m_alphabet.GetFrequencySortedDesc().keySet().toArray()[0];
        String letterMostUsedInText = (String) frequency.keySet().toArray()[0];

        int iLetterMostUsedLanguage = this.m_alphabet.GetIndexLetter(letterMostUsedLanguage);
        int iLetterMostUsedInText = this.m_alphabet.GetIndexLetter(letterMostUsedInText);

        if(iLetterMostUsedInText > iLetterMostUsedLanguage)
        {
            key = Math.abs(iLetterMostUsedLanguage - iLetterMostUsedInText);
        }
        else
        {
            key = (  this.m_alphabet.GetLatin().length + (iLetterMostUsedInText - iLetterMostUsedLanguage));
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
