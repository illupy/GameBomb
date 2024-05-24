package model;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class TiBanh extends Bomber {

	private IDrawable drawable;
	private IOrientable orientable;
	
	public class DrawTiBanh implements IDrawable{

		@Override
		public void draw(Object object, Graphics2D g2d) {
			if(object instanceof TiBanh) {
				TiBanh tiBanh = (TiBanh) object;
				g2d.drawImage(tiBanh.img, tiBanh.x, tiBanh.y - 15, null);
			}
		}
	}
	
	public class OrientTiBanh implements IOrientable {

		@Override
		public void changeOrient(Object object, int orient) {
			if (object instanceof TiBanh) {
				TiBanh tiBanh = (TiBanh) object;
				if (tiBanh.status == Actor.DEAD) {
					return;
				}
				tiBanh.setChange_orient(orient);
				switch (orient) {
				case Actor.LEFT:
					tiBanh.img = new ImageIcon(getClass().getResource("/Images/tibanh_left.png")).getImage();
					break;
				case Actor.RIGHT:
					tiBanh.img = new ImageIcon(getClass().getResource("/Images/tibanh_right.png")).getImage();
					break;
				case Actor.UP:
					tiBanh.img = new ImageIcon(getClass().getResource("/Images/tibanh_up.png")).getImage();
					break;
				case Actor.DOWN:
					tiBanh.img = new ImageIcon(getClass().getResource("/Images/tibanh_down.png")).getImage();
					break;
				default:
					break;
				}
			}
		}
	}


	public TiBanh(int x, int y, int type, int orient, int speed, int sizebomb, int quantityBomb) {
		super(x, y, type, orient, speed, sizebomb, quantityBomb);

		this.runBomb = RUN;
		this.heart = 3;
		this.score = 0;
		this.status = Actor.ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/tibanh_down.png")).getImage();
		width = img.getWidth(null);
		height = img.getHeight(null) - 15;
	}

	@Override
	public void setBounds(int x, int y) {
		this.x = x;
		this.y = y;
		this.status = ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/tibanh_down.png")).getImage();
	}

	@Override
	public void drawActor(Graphics2D g2d) {
		super.drawActor(g2d);
		drawable = new DrawTiBanh();
		drawable.draw(this, g2d);
	}

	@Override
	public void changeOrient(int orient) {
		super.changeOrient(orient);
		orientable = new OrientTiBanh();
		orientable.changeOrient(this, orient);
	}

	@Override
	public void setQuantityBomb(int quantityBomb) {
		if (quantityBomb > 9) {
			return;
		}
		this.quantityBomb = quantityBomb;
	}

	@Override
	public void setSizeBomb(int sizeBomb) {
		if (sizeBomb > 8) {
			return;
		}
		this.sizeBomb = sizeBomb;
	}

	@Override
	public void setSpeed(int speed) {
		if (speed < 2) {
			return;
		}
		this.speed = speed;
	}
}