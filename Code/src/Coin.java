import javafx.scene.Group;

/**
 * Inherits from Token class. Initializes a coin image inside image view.
 *
 * @author Daksh Shah and Arsh Verma
 */

public class Coin extends Tokens {
    protected  String path = "./../Images/icons8-cheap-2-96.png";

    public Coin(Group tg){
        super(tg);
    }
    public Coin(){super();}

	/**
	 * @return Path of the token image in directory
	 */
    @Override
    protected String getPath() {
        return this.path;
    }

    @Override
    public void collides(){
        if(_tokenGroup!=null && _snake!=null) {
            System.out.println("Coin COLLISION");
            _tokenGroup.getChildren().clear();
        }
    }
}
