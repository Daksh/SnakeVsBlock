/**
 * Inherits from Token class. Initializes a shield image inside image view.
 *
 * @author Daksh Shah and Arsh Verma
 */

public class Shield extends Tokens {
    protected  String path = "./../Images/icons8-shield-96.png";

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
            Snake.setShieldStatus(true);

            Thread t = new Thread()
            {
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Snake.setShieldStatus(false);
                }
            };
            t.start();

        }
    }
}
