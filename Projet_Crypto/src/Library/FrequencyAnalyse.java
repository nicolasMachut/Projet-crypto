package Library;

import java.util.HashMap;

/**
 * Created by edouard on 11/03/14.
 */
public class FrequencyAnalyse {

    private String m_text;
    private HashMap<String,HashMap<String,Double>> bigrammeTable = new HashMap<String, HashMap<String, Double>>();

    /**
    * FrequencyAnalyse's Constructor
    */

     public FrequencyAnalyse(String p_file){this.m_text = p_file;}

    /**
     * How many times each char is present in the text ?
     * @return HashMap<String, nbTimes>
     */
    public HashMap<String, Double>  CharPresence()
    {
        HashMap<String, Double> presence = new HashMap<String, Double>();
        String[] alphabet = new Alphabet().GetLatin();

        // Initialize the dico frequency
        int alphabetLenght = alphabet.length;
        for(int iChar = 0; iChar < alphabetLenght; iChar++)
        {
            presence.put(alphabet[iChar], (double)0);
        }

        String textUpperCase = m_text.toUpperCase();
        String key;
        Double value;

        int textLenght = m_text.length();
        for(int iText = 0; iText < textLenght; iText++)
        {
            key = Character.toString( textUpperCase.charAt(iText) );

            if( presence.containsKey(key) )
            {
                value = presence.get(key);
                value++;
                presence.put(key, value );
            }
        }

        return presence;
    }

    public String getMostUsedChar(HashMap<String, Double> p_frequency)
    {
        String mostUsedChar = "";
        Double higherFrequency = (double)0;
        for(String key : p_frequency.keySet())
        {
            if(higherFrequency < p_frequency.get(key))
            {
                higherFrequency = p_frequency.get(key);
                mostUsedChar = key;
            }

        }
        return mostUsedChar;
    }


    /**
     * Number of characters [A..Z] in the text
     * Punctuation and empty space are not counted
     * @return total of characters
     */
    public int CountCharacters()
    {
        return this.m_text.length();
    }

    /**
     * Calculate the frequency (%) of each alphabet's character in the text
     * Map <string, frequency>
     * @return chars with their frequency in the text
     */
    public HashMap<String, Double>  CalculCharFrequency()
    {
        HashMap<String, Double> frequency = this.CharPresence();
        int totalChars = CountCharacters();

        // Test = MUST BE DELETED/ERASE
        //System.out.println(" CalculCharFrequency Total : "+totalChars);

        for (String key : frequency.keySet())
        {
            //System.out.println("key: " + key + " value: " + frequency.get(key) +" times so : "+frequency.get(key)/totalChars+" % ");
            frequency.put(key, frequency.get(key)/totalChars*100);

        }
        return frequency;
    }

    /**
     *Set the HashMap bigramme for a language
     * Keep the first two letters of the language given in parameters
     * and in capital letters
     *@set bigrammeTable HashMap
     */
    public void SetBigrammeTable(String p_language, HashMap<String,Double> p_bigrammeTable)
    {
        StringBuffer languageGiven = new StringBuffer(p_language);
        String keyBigramme = "";

        if(languageGiven.length()>=2)
        {
            for(int i=0 ;i<2;i++)
            {
                keyBigramme += languageGiven.charAt(i);
            }
        }else
        {
            System.out.println("The length of the language given in parameter is too short.");
        }


        this.bigrammeTable.put(keyBigramme.toUpperCase(),p_bigrammeTable);
        //this.bigrammeTable.put("FR", new HashMap<String, Double>("er",1.48 ));
    }

    /**
     *
     * @param p_keyLanguage
     * @return HashMap of bigramme choosen
     */
    public HashMap<String,Double> GetBigramme(String p_keyLanguage)
    {
        return this.bigrammeTable.get(p_keyLanguage);
    }

    /**
     *
     * @return boolean if there is 3 same letters aside in the text, return true
     */
    public boolean CheckIfThereIsThreeSameLetterAside()
    {
        int countSameLettersAside = 0;
        for(int i = 0; i < this.m_text.length(); i++)
        {
            if(i > 0)
            {
                if(String.valueOf(this.m_text.charAt(i-1)).equals(String.valueOf(this.m_text.charAt(i))))
                {
                    countSameLettersAside += 1;
                }
                else
                {
                    countSameLettersAside = 0;
                }
                if(countSameLettersAside == 2)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public HashMap<String,Double> GetBigrammeScore(String p_UncryptedText)
    {
        HashMap score = new HashMap<String,Double>();
        // TODO Déclaration d'un Alpĥabet pour obtenir le bigramme


        for(int i =0; i <p_UncryptedText.length(); i++ )
        {
            if(i > 0)
            {
                String myBigramme = String.valueOf(p_UncryptedText.charAt(i-1)+p_UncryptedText.charAt(i));
                Double occurence = 0.00;
                score.put(myBigramme,occurence);
            }
        }


        return score;
    }


    private void showCharFrequency(HashMap<String, Double> p_lettersFrequencies)
    {
        System.out.println(" CalculCharFrequency, char total : "+ m_text.length());

        int totalChars = m_text.length();

        for (String key : p_lettersFrequencies.keySet())
        {
            System.out.println("key: " + key + " = " + p_lettersFrequencies.get(key) + " %");
        }
    }
}
