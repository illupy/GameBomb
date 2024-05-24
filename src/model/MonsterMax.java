package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MonsterMax extends Monster {

	public MonsterMax(int x, int y, int type, int orient, int speed, int heart, String images) {
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
		if (o instanceof MonsterMax) {
			MonsterMax monsterMax = (MonsterMax) o;
			g2d.drawImage(monsterMax.img, monsterMax.x, monsterMax.y - 4, null);
		}
}

	//@Override
	public void changeOrient(int orient) {
		//super.changeOrient(orient);
		changeOrient(this, orient);
	}
	public void changeOrient(Object o, int orient)
	{
		if (o instanceof MonsterMax) {
			MonsterMax monsterMax = (MonsterMax) o;
			monsterMax.setChange(orient);
			switch (orient) {
			case Actor.LEFT:
				monsterMax.img = new ImageIcon(getClass().getResource("/Images/quaivat 2_left.png")).getImage();
				break;
			case Actor.RIGHT:
				monsterMax.img = new ImageIcon(getClass().getResource("/Images/quaivat 2_right.png")).getImage();
				break;
			case Actor.UP:
				monsterMax.img = new ImageIcon(getClass().getResource("/Images/quaivat 2_up.png")).getImage();
				break;
			case Actor.DOWN:
				monsterMax.img = new ImageIcon(getClass().getResource("/Images/quaivat 2_down.png")).getImage();
				break;
			default:
				break;
			}
		}
	}
}
