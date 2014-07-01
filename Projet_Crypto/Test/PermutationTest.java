import Library.LanguageDetection;
import org.junit.Before;
import org.junit.Test;
import project_crypto.Models.Permutation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nicolas on 26/03/14.
 */
public class PermutationTest {


    private Permutation permutation;

    @Before
    public void Setup()
    {
        this.permutation = new Permutation("fr");
    }

    @Test
    public void TestGetVacantLetterWithALetterEverUsed()
    {
        this.permutation.GetAssociation().put("A", "B");
        this.permutation.GetAssociation().put("B", "C");
        this.permutation.GetAssociation().put("C", "D");
        this.permutation.GetAssociation().put("D", "E");
        this.permutation.GetAssociation().put("E", "F");
        this.permutation.GetAssociation().put("F", "G");
        this.permutation.GetAssociation().put("G", "H");
        this.permutation.GetAssociation().put("H", "I");
        this.permutation.GetAssociation().put("I", "J");
        this.permutation.GetAssociation().put("J", "K");
        this.permutation.GetAssociation().put("K", "L");
        this.permutation.GetAssociation().put("L", "M");
        this.permutation.GetAssociation().put("M", "N");
        this.permutation.GetAssociation().put("N", "O");
        this.permutation.GetAssociation().put("O", "P");
        this.permutation.GetAssociation().put("P", "Q");
        this.permutation.GetAssociation().put("Q", "R");
        this.permutation.GetAssociation().put("R", "S");
        this.permutation.GetAssociation().put("S", "T");
        this.permutation.GetAssociation().put("T", "U");
        this.permutation.GetAssociation().put("U", "V");
        this.permutation.GetAssociation().put("V", "W");
        this.permutation.GetAssociation().put("W", "X");
        this.permutation.GetAssociation().put("X", "Y");
        this.permutation.GetAssociation().put("Y", "Z");

        assertEquals("A", this.permutation.GetVacantRandomLetter());
    }

    @Test
    public void testPermuter()
    {
        List<String> alphabeTryUser = new ArrayList<String>();
        alphabeTryUser.add("E");
        alphabeTryUser.add("P");
        alphabeTryUser.add("S");
        alphabeTryUser.add("I");


        permutation.Uncrypting("abcd dcba", alphabeTryUser);

        assertEquals("EPSIISPE", permutation.GetReadableString());
    }

    @Test
    public void testPermuterDetectionLangueFr()
    {
        Permutation permut = new Permutation("fr");
        permut.SetLanguageAlphabetUsed("fr");
        permut.Crypting("CETTECREDIBILITESERANECESSAIREPOURLESACTIONSAVENIRCARUNCLIENTNESTPASTOUJOURSFAVORABLEAUCHANGEMENT");
        Map<String, Double> analyseLangue = new LanguageDetection().SumFrequenceMostUsedCharInLanguage(permut.GetEncryptedString());

        boolean languageDectectee = false;
        if( analyseLangue.get("fr") > analyseLangue.get("en") )
        {
            languageDectectee = true;
        }


        assertTrue(languageDectectee);
    }

    @Test
    public void testPermuterDetectionLangueEn()
    {
        Permutation permut = new Permutation("fr");
        permut.SetLanguageAlphabetUsed("en");
        permut.Crypting("THISISATEST");
        Map<String, Double> analyseLangue = new LanguageDetection().SumFrequenceMostUsedCharInLanguage(permut.GetEncryptedString());

        boolean languageDectectee = false;
        if( analyseLangue.get("en") > analyseLangue.get("fr") )
        {
            languageDectectee = true;
        }

        assertTrue(languageDectectee);
    }

    /*
   @Test
    public void FirstTryWthFreqAnaRespected()
    {
       String crypted = "KDFFDKSDVBXBYBFDODSEJDKDOOEBSDZCLSYDOEKFBCJOEWDJBSKESLJKYBDJFJDOFZEOFCLPCLSOUEWCSEXYDELKTEJQDMDJF";
        this.permutation.SetCryptedString(crypted);

       assertEquals("KEFFEKSEVBXBYBFEOESAJEKEOOABSEZCLSYEOAKFBCJOAWEJBSKASLJKYBEJFJEOFZAOFCLPCLSOUAWCSAXYEALKTAJQEMEJF", this.permutation.GetEncryptedString());
    }
    */

}
