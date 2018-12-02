import javafx.scene.Group;

/**
 * Inherits from Token class. Initializes a shield image inside image view.
 *
 * @author Daksh Shah and Arsh Verma
 */

public class Shield extends Tokens {
    protected  String path = "./../Images/icons8-shield-96.png";

    public Shield(Group tg){
        super(tg);
    }

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
            System.out.println("Shield COLLISION");
            _tokenGroup.getChildren().clear();
            Snake.setShieldOnFor(5000);
        }
    }
}
