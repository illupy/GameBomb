package model;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class KhoKho extends Bomber {

	private IDrawable drawable;
	private IOrientable orientable;
	
	public class DrawKhoKho implements IDrawable{

		@Override
		public void draw(Object object, Graphics2D g2d) {
			if(object instanceof KhoKho) {
				KhoKho khoKho = (KhoKho) object;
				g2d.drawImage(khoKho.img, khoKho.x, khoKho.y - 20, null);
			}
		}

	}
	
	public class OrientKhoKho implements IOrientable {

		@Override
		public void changeOrient(Object object, int orient) {
			if (object instanceof KhoKho) {
				KhoKho khoKho = (KhoKho) object;
				if (khoKho.status == Actor.DEAD) {
					return;
				}
				khoKho.setChange_orient(orient);
				switch (orient) {
				case Actor.LEFT:
					khoKho.img = new ImageIcon(getClass().getResource("/Images/khokho_left.png")).getImage();
					break;
				case Actor.RIGHT:
					khoKho.img = new ImageIcon(getClass().getResource("/Images/khokho_right.png")).getImage();
					break;
				case Actor.UP:
					khoKho.img = new ImageIcon(getClass().getResource("/Images/khokho_up.png")).getImage();
					break;
				case Actor.DOWN:
					khoKho.img = new ImageIcon(getClass().getResource("/Images/khokho_down.png")).getImage();
					break;
				default:
					break;
				}

			}
		}
	}


	public KhoKho(int x, int y, int type, int orient, int speed, int sizebomb, int quantityBomb) {
		super(x, y, type, orient, speed, sizebomb, quantityBomb);

		this.runBomb = RUN;
		this.heart = 3;
		this.score = 0;
		this.status = Actor.ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/khokho_down.png")).getImage();
		width = img.getWidth(null);
		height = img.getHeight(null) - 20;
	}

	@Override
	public void setBounds(int x, int y) {
		this.x = x;
		this.y = y;
		this.status = ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/khokho_down.png")).getImage();
	}

	@Override
	public void drawActor(Graphics2D g2d) {
		super.drawActor(g2d);
		drawable = new DrawKhoKho();
		drawable.draw(this, g2d);
	}

	@Override
	public void changeOrient(int orient) {
		super.changeOrient(orient);
		orientable = new OrientKhoKho();
		orientable.changeOrient(this, orient);
	}

	
	@Override
	public void setQuantityBomb(int quantityBomb) {
		if (quantityBomb > 6) {
			return;
		}
		this.quantityBomb = quantityBomb;
	}

	@Override
	public void setSizeBomb(int sizeBomb) {
		if (sizeBomb > 7) {
			return;
		}
		this.sizeBomb = sizeBomb;
	}

	@Override
	public void setSpeed(int speed) {
		if (speed < 1) {
			return;
		}
		this.speed = speed;
	}
}