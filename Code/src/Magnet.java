import javafx.scene.Group;

/**
 * Inherits from Token class. Initializes a magnet image inside image view.
 *
 * @author Daksh Shah and Arsh Verma
 */
public class Magnet extends Tokens{
    protected String path = "./../Images/icons8-magnet-96.png";

	/**
	 * at return Path of the token image in directory
	 */
	public Magnet(Group t){
	    super(t);
    }
    public Magnet(){super();};

	@Override
    protected String getPath() {
        return this.path;
    }

    @Override
    public void collides(){
        if(_tokenGroup!=null && _snake!=null) {
            System.out.println("Magnet COLLISION");
            _tokenGroup.getChildren().clear();
            Tokens.setMagnetOnFor(5000);
        }
    }
}
