/**
 * Inherits from Token class. Initializes a magnet image inside image view.
 *
 * @author Daksh Shah and Arsh Verma
 */
public class Magnet extends Tokens{
    protected String path = "./../Images/icons8-magnet-96.png";

	/**
	 * @return Path of the token image in directory
	 */
	@Override
    protected String getPath() {
        return this.path;
    }
}
