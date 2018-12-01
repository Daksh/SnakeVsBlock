/**
 * Inherits from Token class. Initializes a shield image inside image view.
 *
 * @author Daksh Shah and Arsh Verma
 */

public class Shield extends Tokens {
    protected  String path = "./../Images/icons8-shield-96.png";
    @Override
    protected String getPath() {
        return this.path;
    }
}
