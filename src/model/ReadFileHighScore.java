package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFileHighScore {
	private ArrayList<HighScore> arrHightScore;

	public ReadFileHighScore() {
		arrHightScore = new ArrayList<HighScore>();
		try {
			FileReader file = new FileReader("src/highscore/HighScore.txt");
			BufferedReader input = new BufferedReader(file);
			String line;
			while ((line = input.readLine()) != null) {
				String str[] = line.split(":");
				String name = str[0];
				int score = Integer.parseInt(str[1]);
				HighScore hightScore = new HighScore(name, score);
				arrHightScore.add(hightScore);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<HighScore> getArrHightScore() {
		return arrHightScore;
	}
}
