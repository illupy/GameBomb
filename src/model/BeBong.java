package model;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;


public class BeBong extends Bomber  {

	private IDrawable drawable;  // Dùng để khai báo biến drawable dùng để vẽ biến hình ảnh
	private IOrientable orientable;  // Dùng để khai báo biến orientable đổi hướng bé bóng

	public class DrawBeBong implements IDrawable {
		@Override
		public void draw(Object object, Graphics2D g2d) {
			if (object instanceof BeBong) {
				BeBong beBong = (BeBong) object;
				g2d.drawImage(beBong.img, beBong.x, beBong.y - 11, null);
			}
		}
	}

	private class OrientBeBong implements IOrientable {
		@Override
		public void changeOrient(Object object, int orient) {
			if (object instanceof BeBong) {
				BeBong beBong = (BeBong) object;
				if (beBong.status == Actor.DEAD) {
					return;
				}
				beBong.setChange_orient(orient);
				switch (orient) {
					case Actor.LEFT:
						beBong.img = new ImageIcon(getClass().getResource("/Images/bebong_left.png")).getImage();
						break;
					case Actor.RIGHT:
						beBong.img = new ImageIcon(getClass().getResource("/Images/bebong_right.png")).getImage();
						break;
					case Actor.UP:
						beBong.img = new ImageIcon(getClass().getResource("/Images/bebong_up.png")).getImage();
						break;
					case Actor.DOWN:
						beBong.img = new ImageIcon(getClass().getResource("/Images/bebong_down.png")).getImage();
						break;
					default:
						break;
				}
			}
		}
	}
	
	
	public BeBong(int x, int y, int type, int orient, int speed, int sizebomb, int quantityBomb) {
		super(x, y, type, orient, speed, sizebomb, quantityBomb);

		this.runBomb = RUN;
		this.heart = 3;
		this.score = 0;
		this.status = Actor.ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/bebong_down.png")).getImage();
		width = img.getWidth(null);
		height = img.getHeight(null) - 11;
	}
    
	// ghi đè phương thức setNew của lớp cha để thiết lập vị trí nhân vật
	@Override
	public void setBounds(int x, int y) {
		this.x = x;
		this.y = y;
		this.status = ALIVE;
		this.img = new ImageIcon(getClass().getResource("/Images/bebong_down.png")).getImage();
	}

	@Override
	public void drawActor(Graphics2D g2d) {
		super.drawActor(g2d);
		drawable = new DrawBeBong();
		drawable.draw(this, g2d);
	}

	@Override
	public void changeOrient(int orient) {
		super.changeOrient(orient);
		orientable = new OrientBeBong();
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
		if (sizeBomb > 10) {
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
