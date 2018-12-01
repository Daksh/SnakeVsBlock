/**
 * Inherits from Token class. Initializes a coin image inside image view.
 *
 * @author Daksh Shah and Arsh Verma
 */

public class Coin extends Tokens {
    protected  String path = "./../Images/icons8-cheap-2-96.png";

	/**
	 * @return Path of the token image in directory
	 */
    @Override
    protected String getPath() {
        return this.path;
    }
}
