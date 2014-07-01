package project_crypto.Models;

import Library.Alphabet;
import project_crypto.Memento.Caretaker;
import project_crypto.Memento.Originator;

/**
 * @author Edouard SOUAN-MARCELON
 * @author Nicolas MACHUT
 * @author Kim SAVAROCHE
 * Date : 10/03/14.
 */
public abstract class Crypting{

    protected String m_cryptedString;
    protected String m_readableString;
    protected Alphabet m_alphabet;
    protected String m_language;
    protected Object m_iNextUKeyAuto; // key type depends on Models

    // Memento for the Log
    protected Caretaker caretaker;
    protected Originator originator;

    protected Crypting()
    {
        this.m_cryptedString = "";
        this.m_readableString = "";
        this.m_alphabet = new Alphabet();
        SetLanguage("fr");

        caretaker = new Caretaker();
        originator = new Originator();
    }


    /**
     * This function prevents the return of a negative modulo from the classic modulo function
     * @param p_number : how much ?
     * @param p_modulo : divisor
     * @return positive congruence
     */
    public static int ModuloPositive(int p_number, int p_modulo)
    {
        int result = p_number%p_modulo;
        if(result < 0)
        {
            result += p_modulo;
        }

        return result;
    }

    //Return random int beetwen 0 to alphabet's length
    protected int GetRandom()
    {
        int maxValue = this.m_alphabet.GetLatin().length;
        return (int)(Math.random() * (maxValue));
    }

    public String GetEncryptedString()
    {
        return this.m_cryptedString;
    }

    public String GetReadableString()
    {
        return this.m_readableString;
    }

    public void SetCryptedString(String crypted) {
        this.m_cryptedString = crypted;
    }

    public void SetLanguage(String p_language)
    {
        if(p_language.equals("fr") || p_language.equals("en")) {
            m_language = p_language;
        }
        else
        {
            m_language = "fr";
        }

        // Reset iUncryptedLettert
        m_iNextUKeyAuto = 0;
    }

    public String GetLanguage()
    {
        return m_language;
    }

    public Alphabet GetAlphabet()
    {
        return m_alphabet;
    }

    public Object GetNextKeyAuto()
    {
        return m_iNextUKeyAuto;
    }

    public String ExportKeyLog()
    {
        return caretaker.GetStatesForExport();
    }

    public abstract void SetNextKeyAuto();

    public abstract void SaveKeyInMemento();

}
