import Library.Alphabet;
import project_crypto.Views.UncryptingView;
import project_crypto.Views.Window;

/**
 * Created by kimsavinfo on 17/05/14.
 */
public class UncryptInterfaceTest
{
    // Subject :
    // Show two alphabets : 1 uncrypted and 1 crypted
    // click on one = highligth
    // click second = higlight unless it is the first one then un-highligth
    // Button switch = refresh

    private static Alphabet normalAlphabet;
    private static UncryptingView jpanel;

    public static void main(String[] args)
    {
        // Test datas
        normalAlphabet = new Alphabet();

        jpanel = new UncryptingView();
        jpanel.getAlphaTable().setDataRowAlphaTable("traduction");

        jpanel.setM_uncryptedTextArea("\n" +
                "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed justo nulla, mollis nec pretium nec, convallis et massa. Donec risus est, semper interdum metus nec, imperdiet rhoncus diam. Curabitur nec elit tellus. Nullam elit lacus, eleifend eu eros eget, commodo porttitor libero. Vivamus adipiscing fringilla ante non tempus. Proin risus elit, blandit at ultrices ac, aliquet et libero. Nullam sagittis nisi orci, eu tempus quam scelerisque nec. Integer metus ligula, posuere eu pretium et, hendrerit at lorem. Nunc commodo mi nisi, ornare volutpat nunc congue vitae. Cras dolor quam, aliquam et elementum blandit, luctus sit amet quam.\n" +
                "\n" +
                "Nunc tortor lacus, consectetur non diam nec, mollis feugiat velit. Donec venenatis elit quis ipsum pulvinar, vel malesuada magna porta. Fusce et eros felis. In dui lorem, feugiat sed sapien et, lobortis tincidunt nunc. Duis nisi magna, ultrices at convallis at, vulputate vitae purus. Sed aliquam cursus auctor. Aenean id velit eu metus aliquam sollicitudin porttitor euismod elit. Nulla tempus diam id erat porttitor, ac viverra metus egestas. Mauris iaculis purus justo, a interdum nulla pharetra sed. Nulla rhoncus aliquam purus.\n" +
                "\n" +
                "Ut consequat, ante ac iaculis tempor, justo elit porttitor elit, ac consequat mauris sem in orci. Mauris malesuada vitae felis id ornare. Pellentesque turpis neque, accumsan vitae molestie et, eleifend in urna. Integer placerat libero vel est consequat iaculis in ornare purus. Suspendisse potenti. Fusce sed velit imperdiet, eleifend diam et, consequat tortor. Donec imperdiet, odio id tempor egestas, tortor odio porttitor eros, vel ultricies eros diam a justo. Nullam sodales luctus elit sed rutrum. Duis pellentesque ornare ipsum. In volutpat, ligula ut congue dapibus, orci sem fringilla ante, vitae tempor mi tellus in diam. Curabitur sed massa accumsan, ultricies ante non, auctor augue. Quisque mi magna, dictum et erat commodo, interdum elementum sem. Suspendisse blandit dictum malesuada. Nullam fringilla malesuada libero, cursus porttitor sapien bibendum eget. Mauris at placerat diam. Aenean suscipit fermentum erat et vestibulum.\n" +
                "\n" +
                "Cras ut erat eu libero consectetur placerat at id dolor. Vivamus mauris elit, condimentum sit amet mauris sed, ullamcorper elementum libero. Proin a tempus diam, a posuere nibh. Praesent vel hendrerit magna. Cras ornare ipsum est, id sagittis diam ornare vel. In hac habitasse platea dictumst. Aenean adipiscing quam a odio ornare, nec aliquam velit elementum. Curabitur sollicitudin iaculis magna placerat ultricies. Ut et accumsan nibh. Quisque congue non elit ac auctor. Donec auctor eros non nisl aliquet porta. Nunc vel convallis ipsum. Sed vulputate sed magna quis aliquet. Quisque in eros sed odio pretium accumsan.\n" +
                "\n" +
                "Proin commodo convallis luctus. Duis sit amet velit bibendum, tincidunt risus nec, vulputate enim. Cras in neque molestie, malesuada neque nec, fringilla tellus. Vestibulum eget purus eu nulla ultricies facilisis et quis sem. Suspendisse potenti. Curabitur lobortis sagittis urna, ac porta ipsum varius sed. Sed vel dictum ipsum. Nullam eleifend augue vitae nulla lobortis, eu ultrices sem volutpat. Suspendisse mollis egestas nunc eu gravida. Curabitur hendrerit sagittis fringilla. Vivamus euismod vulputate varius. Donec ornare lobortis dolor id pulvinar. Praesent scelerisque nisl eget tellus mattis, nec condimentum augue vulputate. Aliquam risus felis, elementum et malesuada ut, sollicitudin at turpis. Aliquam sagittis lacus a sem vulputate hendrerit. " +
                "");


        Window m_window = new Window(jpanel);
        m_window.setVisible(true);
    }
}
