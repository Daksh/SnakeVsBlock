import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class User implements Serializable{
	private String Name;
	private int HighScore;
	private int Previous_Score;
	private int Score;
	private LocalDate date;
	public int coins;
	public static HashMap<String, String> UserPasswords = new HashMap<String, String>();
	public static ArrayList<User> Users = new ArrayList<User>();
	public User (String Name,int CS) {
		this.setName(Name);
		this.setScore(CS);
		this.setDate();
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getHighScore() {
		return HighScore;
	}

	public void setHighScore(int highScore) {
		HighScore = highScore;
	}

	public int getPrevious_Score() {
		return Previous_Score;
	}

	public void setPrevious_Score(int previous_Score) {
		Previous_Score = previous_Score;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int currentScore) {
		Score = currentScore;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate() {
		this.date = java.time.LocalDate.now().minusDays(2);
	}
}
