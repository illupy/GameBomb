package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MonsterBoss extends Monster implements IDrawable,IOrientable{

	public MonsterBoss(int x, int y, int type, int orient, int speed, int heart, String images) {
		super(x, y, type, orient, speed, heart, images);
		height = img.getHeight(null) - 38;
	}

	@Override
	public void drawActor(Graphics2D g2d) {
		//super.drawActor(g2d);
		draw(this,g2d);
	}
	public void draw(Object object, Graphics2D g2d)
	{
		if (object instanceof MonsterBoss) {
			MonsterBoss boss = (MonsterBoss) object;
			g2d.drawImage(boss.img, boss.x, boss.y - 38, null);
			g2d.setColor(Color.WHITE);
			g2d.drawRect(boss.x + 13, boss.y - 54, boss.width - 26, 12);
			Image imgHeartBoss = new ImageIcon(getClass().getResource("/Images/heart_boss.png")).getImage();
			int a = 0;
			for (int i = 0; i < (boss.heart - 1) / 250 + 1; i++) {
				g2d.drawImage(imgHeartBoss, boss.x + 18 + a, boss.y - 52, null);
				a = a + 10;
			}
		}
	}
	public void changeOrient(int orient) {
		//super.changeOrient(orient);
		changeOrient(this, orient);
	}
	public void changeOrient(Object object, int orient)
	{
		if (object instanceof MonsterBoss) {
			MonsterBoss boss = (MonsterBoss) object;
			boss.setChange(orient);
			switch (orient) {
			case Actor.LEFT:
				boss.img = new ImageIcon(getClass().getResource("/Images/boss_left.png")).getImage();
				break;
			case Actor.RIGHT:
				boss.img = new ImageIcon(getClass().getResource("/Images/boss_right.png")).getImage();
				break;
			case Actor.UP:
				boss.img = new ImageIcon(getClass().getResource("/Images/boss_up.png")).getImage();
				break;
			case Actor.DOWN:
				boss.img = new ImageIcon(getClass().getResource("/Images/boss_down.png")).getImage();
				break;
			default:
				break;
			}
		}
	}
}
