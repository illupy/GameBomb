package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MonsterMinBoss extends Monster {

	public MonsterMinBoss(int x, int y, int type, int orient, int speed, int heart, String images) {
		super(x, y, type, orient, speed, heart, images);
		height = 45;
		width = 45;
	}
	@Override
	public void drawActor(Graphics2D g2d) {
		//super.drawActor(g2d);
		draw(this,g2d);
	}
	public void draw(Object o, Graphics2D g2d)
	{
		if (o instanceof MonsterMinBoss) {
			MonsterMinBoss minBoss = (MonsterMinBoss) o;
			g2d.drawImage(minBoss.img, minBoss.x, minBoss.y - 13, null);
			g2d.setColor(Color.WHITE);
			g2d.drawRect(minBoss.x + 12, minBoss.y - 23, minBoss.width - 24, 10);
			Image imgHeartBoss = new ImageIcon(getClass().getResource("/Images/heart_boss.png")).getImage();
			int a = 0;
			for (int i = 0; i < (minBoss.heart - 1) / 250 + 1; i++) {
				g2d.drawImage(imgHeartBoss, minBoss.x + 13 + a, minBoss.y - 22, null);
				a = a + 10;
			}
		}
	}

	//@Override
	public void changeOrient(int orient) {
		//super.changeOrient(orient);
		changeOrient(this, orient);
	}
	public void changeOrient(Object o, int orient)
	{
		if (o instanceof MonsterMinBoss) {
			MonsterMinBoss minBoss = (MonsterMinBoss) o;
			minBoss.setChange(orient);
			switch (orient) {
			case Actor.LEFT:
				minBoss.img = new ImageIcon(getClass().getResource("/Images/quaivat 3_left.png")).getImage();
				break;
			case Actor.RIGHT:
				minBoss.img = new ImageIcon(getClass().getResource("/Images/quaivat 3_right.png")).getImage();
				break;
			case Actor.UP:
				minBoss.img = new ImageIcon(getClass().getResource("/Images/quaivat 3_up.png")).getImage();
				break;
			case Actor.DOWN:
				minBoss.img = new ImageIcon(getClass().getResource("/Images/quaivat 3_down.png")).getImage();
				break;
			default:
				break;
			}
		}
	}
}
