package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class MonsterGhost extends Monster implements IDrawable,IOrientable{
	public MonsterGhost(int x, int y, int type, int orient, int speed, int heart, String images) {
		super(x, y, type, orient, speed, heart, images);
		height = 45;
		width = 45;
	}

	@Override
	public void drawActor(Graphics2D g2d) {
		//super.drawActor(g2d);
		draw(this,g2d);
	}
	public void draw(Object object, Graphics2D g2d)
	{
		if (object instanceof MonsterGhost) {
			MonsterGhost monsterGhost = (MonsterGhost) object;
			g2d.drawImage(monsterGhost.img, monsterGhost.x, monsterGhost.y, null);
		}
	}

	//@Override
		public void changeOrient(int orient) {
			//super.changeOrient(orient);
			changeOrient(this, orient);
		}
		public void changeOrient(Object object, int orient)
		{
			if (object instanceof MonsterGhost) {
				MonsterGhost monsterGhost = (MonsterGhost) object;
				monsterGhost.setChange(orient);
				switch (orient) {
				case Actor.LEFT:
					monsterGhost.img = new ImageIcon(getClass().getResource("/Images/ghost3.png")).getImage();
					break;
				case Actor.RIGHT:
					monsterGhost.img = new ImageIcon(getClass().getResource("/Images/ghost3.png")).getImage();
					break;
				case Actor.UP:
					monsterGhost.img = new ImageIcon(getClass().getResource("/Images/ghost3.png")).getImage();
					break;
				case Actor.DOWN:
					monsterGhost.img = new ImageIcon(getClass().getResource("/Images/ghost3.png")).getImage();
					break;
				default:
					break;
				}
			}
		}
}
