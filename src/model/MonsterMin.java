package model;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class MonsterMin extends Monster implements IDrawable,IOrientable{
	public MonsterMin(int x, int y, int type, int orient, int speed, int heart, String images) {
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
		if (o instanceof MonsterMin) {
			MonsterMin monsterMin = (MonsterMin) o;
			g2d.drawImage(monsterMin.img, monsterMin.x + 3, monsterMin.y + 1, null);
		}
	}
	//@Override
	public void changeOrient(int orient) {
		//super.changeOrient(orient);
		changeOrient(this, orient);
	}
	public void changeOrient(Object o, int orient)
	{
		if (o instanceof MonsterMin) {
			MonsterMin monsterMin = (MonsterMin) o;
			monsterMin.orient=orient;
		switch (orient) {
			case Actor.LEFT:
				monsterMin.img = new ImageIcon(getClass().getResource("/Images/quaivat 1_left.png")).getImage();
				break;
			case Actor.RIGHT:
				monsterMin.img = new ImageIcon(getClass().getResource("/Images/quaivat 1_right.png")).getImage();
				break;
			case Actor.UP:
				monsterMin.img = new ImageIcon(getClass().getResource("/Images/quaivat 1_up.png")).getImage();
				break;
			case Actor.DOWN:
				monsterMin.img = new ImageIcon(getClass().getResource("/Images/quaivat 1_down.png")).getImage();
				break;
			default:
				break;
			}
		}
	}
}
