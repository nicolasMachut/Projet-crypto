package project_crypto.Models;

import Library.Alphabet;
import Library.WordToNormalize;

/**
 * Created by nicolas on 10/03/14.
 */
public abstract class Crypting{

    protected String m_cryptedString;
    protected String m_readableString;
    protected Alphabet m_alphabet;

    protected Crypting()
    {
        this.m_cryptedString = "";
        this.m_readableString = "";
        this.m_alphabet = new Alphabet();
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

    public String GetUncryptedString()
    {
        return this.m_readableString;
    }

}
