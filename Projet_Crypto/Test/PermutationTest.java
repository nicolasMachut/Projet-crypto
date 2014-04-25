import org.junit.Before;
import org.junit.Test;
import project_crypto.Models.Permutation;

import static org.junit.Assert.assertEquals;

/**
 * Created by nicolas on 26/03/14.
 */
public class PermutationTest {


    private Permutation permutation;

    @Before
    public void Setup()
    {
        this.permutation = new Permutation();
    }

    @Test
    public void TestGetVacantLetterWithALetterEverUsed()
    {
        this.permutation.getAssociation().put("A", "B");
        this.permutation.getAssociation().put("B", "C");
        this.permutation.getAssociation().put("C", "D");
        this.permutation.getAssociation().put("D", "E");
        this.permutation.getAssociation().put("E", "F");
        this.permutation.getAssociation().put("F", "G");
        this.permutation.getAssociation().put("G", "H");
        this.permutation.getAssociation().put("H", "I");
        this.permutation.getAssociation().put("I", "J");
        this.permutation.getAssociation().put("J", "K");
        this.permutation.getAssociation().put("K", "L");
        this.permutation.getAssociation().put("L", "M");
        this.permutation.getAssociation().put("M", "N");
        this.permutation.getAssociation().put("N", "O");
        this.permutation.getAssociation().put("O", "P");
        this.permutation.getAssociation().put("P", "Q");
        this.permutation.getAssociation().put("Q", "R");
        this.permutation.getAssociation().put("R", "S");
        this.permutation.getAssociation().put("S", "T");
        this.permutation.getAssociation().put("T", "U");
        this.permutation.getAssociation().put("U", "V");
        this.permutation.getAssociation().put("V", "W");
        this.permutation.getAssociation().put("W", "X");
        this.permutation.getAssociation().put("X", "Y");
        this.permutation.getAssociation().put("Y", "Z");

        assertEquals("A", this.permutation.GetVacantRandomLetter());
    }

    @Test
    public void testPermuter()
    {
        Permutation permut = new Permutation();
        permut.Crypting("CETTECREDIBILITESERANECESSAIREPOURLESACTIONSAVENIRCARUNCLIENTNESTPASTOUJOURSFAVORABLEAUCHANGEMENT");
        System.out.println(permut.GetEncryptedString());
    }

   @Test
    public void FirstTryWthFreqAnaRespected()
    {
       String crypted = "KDFFDKSDVBXBYBFDODSEJDKDOOEBSDZCLSYDOEKFBCJOEWDJBSKESLJKYBDJFJDOFZEOFCLPCLSOUEWCSEXYDELKTEJQDMDJF";
        this.permutation.setCryptedString(crypted);

       assertEquals("KEFFEKSEVBXBYBFEOESAJEKEOOABSEZCLSYEOAKFBCJOAWEJBSKASLJKYBEJFJEOFZAOFCLPCLSOUAWCSAXYEALKTAJQEMEJF", this.permutation.lol());
    }

}
