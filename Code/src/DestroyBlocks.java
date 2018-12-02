import javafx.scene.Group;

/**
 * Inherits from Token class. Initializes an explosion image inside image view.
 *
 * @author Daksh Shah and Arsh Verma
 */

public class DestroyBlocks extends Tokens {
    protected  String path = "./../Images/icons8-explosion-96.png";
    private boolean coll = false;

    public DestroyBlocks(Group tg){super(tg);}
    public DestroyBlocks(){super();}

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
            System.out.println("DestroyBlocks COLLISION");

            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.coll = false;
            });
            t.start();

            if(!coll){
                _blocks.destroyAllBlocksInScreen();
                _tokenGroup.getChildren().clear();
                coll = true;
            }
        }
    }
}
