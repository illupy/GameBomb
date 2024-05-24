package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import sound.GameSound;
import timer.Countdown;
import timer.DigitalClock;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;

//import sound.GameSound;

import java.awt.Graphics2D;
import java.awt.Image;

public class Manager {
	private Random random = new Random();
	private Bomber mBomber;
	private ArrayList<BombBang> arrBombBang;
    private ArrayList<Box> boxList;
    private ArrayList<Item> itemList;
    private ArrayList<Monster> monsterList;
    private String backGround;
    private ArrayList<Bomb> arrBomb;
    private ArrayList<HighScore> highscoreList;  
    public int round = 1;
    private int nextRound = 0;
	private int status = 0;
	private int type;
	DigitalClock digitalClock = new DigitalClock();
	private Countdown countdown;
	private int scoretime;
    
    
	public Manager(int type) {
		this.type = type;
		countdown = new Countdown();
		initManager();
	}

	public Manager() {
		countdown = new Countdown();
		initManager();
	}
	
	

 public void initManager() {
		switch (round) {
		case 1:
			countdown.update(2, 0);

			setBomBer();
			init("src/map1/BOX.txt", "src/map1/MONSTER.txt", "src/map1/ITEM.txt");
			nextRound = 0;
			status = 0;
			break;
		case 2:
			countdown.update(2, 0);
			countdown.getTimer().start();
			GameSound.getIstance().stop();
			GameSound.getIstance().getAudio(GameSound.PLAYGAME).loop();
			mBomber.setBounds(540, 495);
			init("src/map2/BOX.txt", "src/map2/MONSTER.txt", "src/map2/ITEM.txt");
			nextRound = 0;
			status = 0;
			break;
		case 3:
			countdown.update(2, 0);
			GameSound.getIstance().stop();
			GameSound.getIstance().getAudio(GameSound.PLAYGAME).loop();
			mBomber.setBounds(540, 495);
			init("src/map3/BOX.txt", "src/map3/MONSTER.txt", "src/map3/ITEM.txt");
			nextRound = 0;
			status = 0;
			break;

		default:
			break;
		}

	} 
	

	public Countdown getCountdown() {
		return countdown;
	}
	
	
	public void setBomBer() {
		if (type == 1) {
			mBomber = new KhoKho(540, 495, Actor.BOMBER, Actor.DOWN, 5, 1, 1);
		} else if (type == 2) {
			mBomber = new BeBong(540, 495, Actor.BOMBER, Actor.DOWN, 5, 2, 1);
		} else if (type == 3) {
			mBomber = new TiaChop(540, 495, Actor.BOMBER, Actor.DOWN, 5, 1, 1);
		} else if (type == 4) {
			mBomber = new TiBanh(540, 495, Actor.BOMBER, Actor.DOWN, 6, 2, 1);
		} else
			mBomber = new KhoKho(540, 495, Actor.BOMBER, Actor.DOWN, 5, 1, 1);
	}
	
    // khởi tạo map
    public void init(String pathToBox, String pathToMonster, String pathToItem){
        boxList = new ArrayList<Box>();
        arrBomb = new ArrayList<Bomb>();
        arrBombBang = new ArrayList<BombBang>();
        itemList = new ArrayList<Item>();
        monsterList = new ArrayList<Monster>();
        highscoreList = new ArrayList<HighScore>();
        initBoxList(pathToBox);
        initMonsterList(pathToMonster);
        innitItemList(pathToItem);      
        inithighscoreList("src/highscore/HighScore.txt");
        
    }
    // khoi tao boxList, ve box, ve background
    public void initBoxList(String pathToBox){
        try {
            FileReader file;
            file = new FileReader(pathToBox);
            BufferedReader input = new BufferedReader(file);
            backGround=input.readLine();
            String line;
            while( (line = input.readLine()) != null){
                String str[]= line.split(":");
                int x= Integer.parseInt(str[0]);
                int y= Integer.parseInt(str[1]);
                int type= Integer.parseInt(str[2]);
                String images = str[3];
                Box box = new Box(x, y, type, images);
                boxList.add(box);

            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
	public void innitBomb() {
		if (mBomber.getStatus() == Bomber.DEAD) {
			return;
		}
		int x = mBomber.getX() + mBomber.getWidth() / 2;
		int y = mBomber.getY() + mBomber.getHeart() / 2 + 20;
		for (int i = 0; i < arrBomb.size(); i++) {
			if (arrBomb.get(i).BombvsnewBomb(x, y)) {
				return;
			}
		}

		if (arrBomb.size() >= mBomber.getQuantityBomb()) {
			return;
		}
		GameSound.getIstance().getAudio(GameSound.BOMB).play();
		Bomb mBomb = new Bomb(x, y, mBomber.getSizeBomb(), 1500);
		arrBomb.add(mBomb);
	}
	
	public void drawDialog(Graphics2D g2d, int type) {
		g2d.setFont(new Font("Georgia", Font.BOLD, 70));
		g2d.setColor(Color.RED);
		if (type == 1) {
			g2d.drawString("Game Over !!! bạn quá gà", 200, 250);
		} else {
			if (type == 2) {
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.4));
				g2d.setColor(Color.BLACK);
				g2d.fillRect(45, 44, 675, 585);
				g2d.setColor(Color.WHITE);
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, (float) 0.4));

				g2d.setFont(new Font("Arial", Font.ITALIC, 55));
				g2d.drawString("Time: " + countdown.getMinute() + ":" + countdown.getSecond(), 230, 250);
				g2d.drawString("Score: " + mBomber.getScore(), 230, 350);
				g2d.drawString("Next Round: " + round, 230, 450);
			} else {
				g2d.drawString("You Win !!!", 220, 250);
			}
		}
	}
    
    
    public void drawAllBox(Graphics2D g2d){
        for (int i=0;i<boxList.size();i++){
            boxList.get(i).drawActor(g2d);
         
        }
    }
    public void draWBackground(Graphics2D g2d) {
		Image imgBackground = new ImageIcon(getClass().getResource(backGround)).getImage();
		g2d.drawImage(imgBackground, 0, 40, null);

	}
    
	public int getSore() {
		return mBomber.getScore();
	}

	
    
    public void innitItemList(String path) {
		try {
			FileReader file = new FileReader(path);
			BufferedReader input = new BufferedReader(file);
			String line;
			while ((line = input.readLine()) != null) {
				String str[] = line.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				int type = Integer.parseInt(str[2]);
				String images = str[3];
				Item item = new Item(x, y, type, images);
				itemList.add(item);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public void drawAllItem(Graphics2D g2d) {
		for (int i = 0; i < itemList.size(); i++) {
			itemList.get(i).drawItem(g2d);
		}
	}

    public void drawAllBomb(Graphics2D g2d) {
		for (int i = 0; i < arrBomb.size(); i++) {
			arrBomb.get(i).drawActor(g2d);
		}
		for (int i = 0; i < arrBombBang.size(); i++) {
			arrBombBang.get(i).drawBongBang(g2d);
		}
	}
// khoi tao MonsterList, ve monster
     public void initMonsterList(String pathtoMonster){
         try {
             FileReader file = new FileReader(pathtoMonster);
             BufferedReader input = new BufferedReader(file);
             String line;
             while ((line =input.readLine()) != null){
                 String str[] = line.split(":");
	 			int x = Integer.parseInt(str[0]);
	 			int y = Integer.parseInt(str[1]);
	 			int type = Integer.parseInt(str[2]);
	 			int orient = Integer.parseInt(str[3]);
	 			int speed = Integer.parseInt(str[4]);
	 			int heart = Integer.parseInt(str[5]);
	 			String images = str[6];
                 //MonsterMin mst = new MonsterMin(x, y, type, orient, speed, heart, images);
               //  monsterList.add(mst);
                 if (type == Monster.MonMAX) {
 					MonsterMax max = new MonsterMax(x, y, type, orient, speed, heart, images);
 					monsterList.add(max);
 				}
 				if (type == Monster.MonMIN) {
 					MonsterMin min = new MonsterMin(x, y, type, orient, speed, heart, images);
 					monsterList.add(min);
 				}
 				if (type == Monster.MinBOSS) {
 					MonsterMinBoss minBoss = new MonsterMinBoss(x, y, type, orient, speed, heart, images);
 					monsterList.add(minBoss);
 				}
 				/*if (type == Monster.GHOST) {
 					MonsterGhost ghost = new MonsterGhost(x, y, type, orient, speed, heart, images);
 					monsterList.add(ghost);
 				}
 				if (type == Monster.BOSS) {
 					MonsterBoss boss = new MonsterBoss(x, y, type, orient, speed, heart, images);
 					monsterList.add(boss);
 				}*/
             }
             input.close();
         } catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
            e.printStackTrace();
         } catch(IOException e){
             e.printStackTrace();
         }
      
     }
     public void drawAllMonster(Graphics2D g2d){
         for (Monster monster : monsterList) {
             monster.drawActor(g2d);
         }
     }
     public void changeOrientAll() {
 		for (int i = 0; i < monsterList.size(); i++) {
 			int orient = random.nextInt(4) + 1;
 			monsterList.get(i).changeOrient(orient);
 		}
 	}

 	public void moveAllMonster(int count) {
 		for (int i = 0; i < monsterList.size(); i++) {
 			if (monsterList.get(i).move(count, arrBomb, boxList) == false) {
 				int orient = random.nextInt(4) + 1;
 				monsterList.get(i).changeOrient(orient);
 				// System.out.println(orient);
 			}
 		}
 	}
 	

 	
 	// chưa sửa
	public void checkWinAndLose() {
		if (mBomber.getHeart() == 0 && nextRound == 0) {
			round = 1;
			status = 1;
			nextRound++;
			countdown.getTimer().stop();
			GameSound.getIstance().stop();
			GameSound.getIstance().getAudio(GameSound.LOSE).play();
			saveScore();
		}
		if (monsterList.size() == 0 && nextRound == 0) {
			if (round == 3) {
				status = 3;
				nextRound++;
				countdown.getTimer().stop();
				GameSound.getIstance().stop();
				GameSound.getIstance().getAudio(GameSound.WIN).play();
				saveScore();
				round = 1;
				return;
			}
			scoretime = (countdown.getMinute() * 60 + countdown.getSecond()) * 50;
			mBomber.setScore(mBomber.getScore() + scoretime);

			round = round + 1;
			nextRound++;
			status = 2;
		}
	}
 	
	
	
	// chưa sửa
	public void saveScore() {
		System.out.println();
		if (highscoreList.size() == 0 || highscoreList.size() < 10) {
			String name = JOptionPane.showInputDialog("Please input Your Name");
			HighScore newScore = new HighScore(name, mBomber.getScore());
			highscoreList.add(newScore);
		} else if (mBomber.getScore() > highscoreList.get(highscoreList.size() - 1).getScore()) {
			String name = JOptionPane.showInputDialog("Please input Your Name");
			HighScore newScore = new HighScore(name, mBomber.getScore());
			highscoreList.add(newScore);
		}
		Collections.sort(highscoreList, new Comparator<HighScore>() {

			@Override
			public int compare(HighScore score1, HighScore score2) {
				if (score1.getScore() < score2.getScore()) {
					return 1;
				} else {
					if (score1.getScore() == score2.getScore()) {
						return 0;
					} else {
						return -1;
					}
				}
			}
		});

		if (highscoreList.size() > 10) {
			highscoreList.remove(highscoreList.size() - 1);
		}

		try {
			FileOutputStream fileOutput = new FileOutputStream("src/hightscore/HightScore.txt");
			for (int i = 0; i < highscoreList.size(); i++) {
				String content = highscoreList.get(i).getName() + ":" + highscoreList.get(i).getScore() + "\n";
				fileOutput.write(content.getBytes());
			}
			fileOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/* */
	
	// chưa sửa
	public void checkDead() {
		if (mBomber.getHeart() < 3 && mBomber.getHeart() > 0) {
			getCountdown().getTimer().start();
		}
		for (int i = 0; i < arrBombBang.size(); i++) {
			if (arrBombBang.get(i).BombBangVsActor(mBomber) && mBomber.getStatus() == Bomber.ALIVE) {
				// countdown.update(2, 0);
				Image icon;
				if (mBomber instanceof BeBong) {
					icon = new ImageIcon(getClass().getResource("/Images/bebong_dead.png")).getImage();
				} else if (mBomber instanceof KhoKho) {
					icon = new ImageIcon(getClass().getResource("/Images/khokho_dead.png")).getImage();
				} else if (mBomber instanceof TiaChop) {
					icon = new ImageIcon(getClass().getResource("/Images/tiachop_dead.png")).getImage();
				} else
					icon = new ImageIcon(getClass().getResource("/Images/tibanh_dead.png")).getImage();

				mBomber.setImg(icon);
				if (mBomber.getStatus() == Bomber.DEAD) {
					return;
				}
				mBomber.setHeart(mBomber.getHeart() - 1);
				mBomber.setStatus(Bomber.DEAD);
				GameSound.instance.getAudio(GameSound.BOMBER_DieDRINK).play();
			}
		}
		for (int i = 0; i < monsterList.size(); i++) {
			if (mBomber.BomberVsActor(monsterList.get(i))) {
				// countdown.update(2, 0);
				Image icon;
				if (mBomber instanceof BeBong || mBomber instanceof TiBanh) {
					icon = new ImageIcon(getClass().getResource("/Images/ghost2.png")).getImage();
				} else
					icon = new ImageIcon(getClass().getResource("/Images/ghost.png")).getImage();
				mBomber.setImg(icon);
				if (mBomber.getStatus() == Bomber.DEAD) {
					return;
				}
				mBomber.setHeart(mBomber.getHeart() - 1);
				mBomber.setStatus(Bomber.DEAD);
				GameSound.instance.getAudio(GameSound.BOMBER_DIE).play();
			}
		}
	}
	
/*	// chưa sửa
	public void deadLineAllBomb() {
	for (int i = 0; i < arrBomb.size(); i++) {
		arrBomb.get(i).deadlineBomb();
		if (arrBomb.get(i).getCountdown() == 250) {
			BombBang bomBang = new BombBang(arrBomb.get(i).getX(), arrBomb.get(i).getY(), arrBomb.get(i).getSize(),
					arrBox);
			GameSound.getIstance().getAudio(GameSound.BONG_BANG).play();
			arrBombBang.add(bomBang);
			arrBomb.remove(i);
		}
	}
	for (int j = 0; j < arrMonster.size(); j++) {
		for (int i = 0; i < arrBomb.size(); i++) {
			if (arrBomb.get(i).isImpactBombvsActor(arrMonster.get(j)) == 2) {
				BombBang bomBang = new BombBang(arrBomb.get(i).getX(), arrBomb.get(i).getY(),
						arrBomb.get(i).getSize(), arrBox);
				GameSound.getIstance().getAudio(GameSound.BONG_BANG).play();
				arrBombBang.add(bomBang);
				arrBomb.remove(i);
			}
		}
	}

	for (int i = 0; i < arrBombBang.size(); i++) {
		for (int j = 0; j < arrBomb.size(); j++) {
			if (arrBombBang.get(i).BombBangvsBomb(arrBomb.get(j))) {
				BombBang bomBang = new BombBang(arrBomb.get(j).getX(), arrBomb.get(j).getY(),
						arrBomb.get(j).getSize(), arrBox);
				arrBombBang.add(bomBang);
				arrBomb.remove(j);
			}
		}
	}
	for (int k = 0; k < arrBombBang.size(); k++) {
		arrBombBang.get(k).deadlineBomb();
		for (int j = 0; j < arrMonster.size(); j++) {
			if (arrBombBang.get(k).isImpactBombBangVsActor(arrMonster.get(j))) {
				if (arrMonster.get(j).getHeart() > 1) {
					arrMonster.get(j).setHeart(arrMonster.get(j).getHeart() - 1);
				} else {
					if (arrMonster.get(j).getType() == Monster.BOSS) {
						mBomber.setScore(mBomber.getScore() + 5000);
					} else if (arrMonster.get(j).getType() == Monster.MinBOSS) {
						mBomber.setScore(mBomber.getScore() + 500);
					} else {
						mBomber.setScore(mBomber.getScore() + 200);
					}
					GameSound.getIstance().getAudio(GameSound.MONSTER_DIE).play();
					arrMonster.remove(j);
				}
			}
		}
		if (arrBombBang.get(k).getTimeLine() == 0) {
			arrBombBang.remove(k);
		}
	}
	for (int i = 0; i < arrBombBang.size(); i++) {
		for (int j = 0; j < arrBox.size(); j++) {
			if (arrBombBang.get(i).isImpactBombBangvsBox(arrBox.get(j))) {
				arrBox.remove(j);
				// arrShawDow.remove(j);
			}
		}
	}
	for (int i = 0; i < arrBombBang.size(); i++) {
		for (int j = 0; j < arrItem.size(); j++) {
			if (arrBombBang.get(i).isImpactBombBangvsItem(arrItem.get(j))) {
				arrItem.remove(j);
			}
		}
	}
}

	*/
	
	
/*	public void drawBoss(Graphics2D g2d) {
		for (int i = 0; i < arrMonster.size(); i++) {
			if (arrMonster.get(i).getType() == Monster.BOSS) {
				arrMonster.get(i).drawActor(g2d);
			}
		}
	}
	
	
	

	
		public void checkImpactItem() {
		for (int i = 0; i < arrItem.size(); i++) {
			if (arrItem.get(i).isImpactItemVsBomber(mBomber)) {
				GameSound.instance.getAudio(GameSound.ITEM).play();
				if (arrItem.get(i).getType() == Item.Item_Bomb) {
					mBomber.setQuantityBomb(mBomber.getQuantityBomb() + 1);
					arrItem.remove(i);
					break;
				}
				if (arrItem.get(i).getType() == Item.Item_BombSize) {
					mBomber.setSizeBomb(mBomber.getSizeBomb() + 1);
					arrItem.remove(i);
					break;
				}
				if (arrItem.get(i).getType() == Item.Item_Shoe) {
					mBomber.setSpeed(mBomber.getSpeed() - 1);
					arrItem.remove(i);
					break;
				}
			}
		}
	}
	


	



*/

	public ArrayList<Box> getArrBox() {
		return boxList;
	}
	public ArrayList<Bomb> getArrBomb() {
		return arrBomb;
	}

	

	public void setRunBomer() {
		if (arrBomb.size() > 0) {
			if (arrBomb.get(arrBomb.size() - 1).BombvsBomber(mBomber) == false) {
				mBomber.setRunBomb(Bomber.RUN);
			}
		}
	}

	public void setNewBomb() {
		switch (round) {
		case 1:
			mBomber.setBounds(540, 495);
			break;
		case 2:
			mBomber.setBounds(540, 495);
			break;
		case 3:
			mBomber.setBounds(540, 495);
			break;

		default:
			break;
		}
	}
 	
 	
	public void inithighscoreList(String path) {
		try {
			FileReader file = new FileReader(path);
			BufferedReader input = new BufferedReader(file);
			String line;
			while ((line = input.readLine()) != null) {
				String str[] = line.split(":");
				String name = str[0];
				int score = Integer.parseInt(str[1]);
				HighScore hightScore = new HighScore(name, score);
				highscoreList.add(hightScore);
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Bomber getBomber() {
		return mBomber;
	}

	public int getStatus() {
		return status;
	}

	public void setRound(int round) {
		this.round = round;
	}
}
