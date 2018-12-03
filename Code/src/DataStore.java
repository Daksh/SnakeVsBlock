import javax.xml.crypto.Data;
import java.io.*;

public class DataStore implements Serializable {
    public int _snakeLength, _score, _sceneCol, _snakeColor;
    public double _speed,_life;

    public DataStore(int sl, int sc, double speed, double life, int sceneCol, int snakeCol){
        _snakeLength = sl;
        _score = sc;
        _speed = speed;
        _life = life;
        _sceneCol = sceneCol;
        _snakeColor = snakeCol;
    }

    public static void serialize(){
        try {
            serialize(new DataStore(Main._gamePlay.getSnake().get_length(), Main._gamePlay.getScore(), Main.SPEED, Main._gamePlay.getLife(), Main._gamePlay.sceneCol, Main._gamePlay.getSnake().colour));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serialize(DataStore ds) throws IOException{
        ObjectOutputStream out = null;
        try{
            out = new ObjectOutputStream(new FileOutputStream(Main.fPathDS));
            out.writeObject(ds);
        } finally {
            out.close();
        }
    }

    public static DataStore deserialize() throws  IOException, ClassNotFoundException{
        ObjectInputStream in = null;
        DataStore ds = null;
        try{
            in = new ObjectInputStream(new FileInputStream(Main.fPathDS));
            ds = (DataStore) in.readObject();
        } finally {
            in.close();
        }
        return ds;
    }

}
