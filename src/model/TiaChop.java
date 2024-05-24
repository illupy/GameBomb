package model;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class TiaChop extends Bomber {

	private IDrawable drawable;
	private IOrientable orientable;
	
	public class DrawTiaChop implements IDrawable{

		@Override
		public void draw(Object object, Graphics2D g2d) {
			if(object instanceof TiaChop) {
				TiaChop tiaChop = (TiaChop) object;
				g2d.drawImage(tiaChop.img, tiaChop.x, tiaChop.y - 20, null);
			}
		}

	}
	
	public class OrientTiaChop implements IOrientable{

		@Override
		public void changeOrient(Object object, int orient) {
			if (object instanceof TiaChop) {
				TiaChop tiaChop = (TiaChop) object;
				if (tiaChop.status == Actor.DEAD) {
					return;
				}
				tiaChop.setChange_orient(orient);
				switch (orient) {
				case Actor.LEFT:
					tiaChop.img = new ImageIcon(getClass().getResource("/Images/tiachop_left.png")).getImage();
					break;
				case Actor.RIGHT:
					tiaChop.img = new ImageIcon(getClass().getResource("/Images/tiachop_right.png")).getImage();
					break;
				case Actor.UP:
					tiaChop.img = new ImageIcon(getClass().getResource("/Images/tiachop_up.png")).getImage();
					break;
				case Actor.DOWN:
					tiaChop.img = new ImageIcon(getClass().getResource("/Images/tiachop_down.png")).getImage();
					break;
				default:
					break;
				}

			}
		}
	}

	public TiaChop(int x, int y, int type, int orient, int speed, int sizebomb, int quantityBomb) {
		super(x, y, type, orient, speed, sizebomb, quantityBomb);

		this.runBomb = RUN;
		this.heart = 3;
		this.score = 0;
		this.status = Actor.ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/tiachop_down.png")).getImage();
		width = img.getWidth(null);
		height = img.getHeight(null) - 20;
	}

	@Override
	public void setBounds(int x, int y) {
		this.x = x;
		this.y = y;
		this.status = ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/tiachop_down.png")).getImage();
	}

	@Override
	public void drawActor(Graphics2D g2d) {
		super.drawActor(g2d);
		drawable = new DrawTiaChop();
		drawable.draw(this, g2d);
	}

	@Override
	public void changeOrient(int orient) {
		super.changeOrient(orient);
		orientable = new OrientTiaChop();
		orientable.changeOrient(this, orient);
	}

	@Override
	public void setQuantityBomb(int quantityBomb) {
		if (quantityBomb > 10) {
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
		if (speed < 3) {
			return;
		}
		this.speed = speed;
	}
}