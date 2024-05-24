package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class BombBang {
	IDrawable drawable; // khai báo biến drawable để vẽ đối tượng
	protected int x, y, size, countDown;
	protected Image img_left, img_right, img_up, img_down;

	public void deadlineBomb() {
		if (countDown > 0) {
			countDown--;
		}
	}

	public int getTimeLine() {
		return countDown;
	}
	
	public void setImage(int orient, int size) {
		switch (orient) {
		case Bomber.LEFT:

			img_left = new ImageIcon(getClass().getResource("/Images/bombbang_left" + size + ".png")).getImage();

			break;
		case Bomber.RIGHT:

			img_right = new ImageIcon(getClass().getResource("/Images/bombbang_right" + size + ".png")).getImage();

			break;
		case Bomber.UP:

			img_up = new ImageIcon(getClass().getResource("/Images/bombbang_up" + size + ".png")).getImage();

			break;
		case Bomber.DOWN:

			img_down = new ImageIcon(getClass().getResource("/Images/bombbang_down" + size + ".png")).getImage();

			break;

		default:
			break;
		}
	}


	
	public BombBang(int x, int y, int size, ArrayList<Box> arrBox) {
	    this.x = x;
	    this.y = y;
	    this.size = size;
	    this.countDown = 250;
	    loadBombImages(); // Tải hình ảnh của bom
	    detect(arrBox); // Phát hiện va chạm và thiết lập hình ảnh của bom
	}

	private void loadBombImages() {
	    img_left = new ImageIcon(getClass().getResource("/Images/bombbang_left1.png")).getImage();
	    img_right = new ImageIcon(getClass().getResource("/Images/bombbang_right1.png")).getImage();
	    img_up = new ImageIcon(getClass().getResource("/Images/bombbang_up1.png")).getImage();
	    img_down = new ImageIcon(getClass().getResource("/Images/bombbang_down1.png")).getImage();
	}

	private void detect(ArrayList<Box> arrBox) {
		for (int i = 1; i < size; i++) {
			int tmp_left = 0, tmp_right = 0, tmp_up = 0, tmp_dow = 0;
			for (int j = 0; j < arrBox.size(); j++) {
				if (isImpactBox(x - (i) * 45, y, (i + 1) * 45, 45, arrBox.get(j))) {
					tmp_left = 1;
				}
				if (isImpactBox(x, y, (i + 1) * 45, 45, arrBox.get(j))) {
					tmp_right = 1;
				}
				if (isImpactBox(x, y - (i * 45), 45, (i + 1) * 45, arrBox.get(j))) {
					tmp_up = 1;
				}
				if (isImpactBox(x, y, 45, (i + 1) * 45, arrBox.get(j))) {
					tmp_dow = 1;
				}
			}
			if (tmp_left == 0) {
				setImage(Bomber.LEFT, i + 1);
			}
			if (tmp_right == 0) {
				setImage(Bomber.RIGHT, i + 1);
			}
			if (tmp_up == 0) {
				setImage(Bomber.UP, i + 1);
			}
			if (tmp_dow == 0) {
				setImage(Bomber.DOWN, i + 1);
			}
		}
	}
    
	

	public void drawBongBang(Graphics2D g2d) {
		drawable = new DrawBombBangImpl();
		drawable.draw(this, g2d);
	}
    
	private boolean isImpactBox(int x, int y, int width, int height, Box box) {
		Rectangle rec1 = new Rectangle(x, y, width, height);
		Rectangle rec2 = new Rectangle(box.getX(), box.getY(), box.getWidth(), box.getHeight());
		return rec1.intersects(rec2);
	}
	
	public boolean BombBangvsItem(Item item) {
		Rectangle bombBounds_left = new Rectangle(x + 45 - img_left.getWidth(null), y, img_left.getWidth(null),
				img_left.getHeight(null));
		Rectangle bombBounds_right = new Rectangle(x, y, img_right.getWidth(null), img_right.getHeight(null));
		Rectangle bombBounds_up = new Rectangle(x, y + 45 - img_up.getHeight(null), img_up.getWidth(null),
				img_up.getHeight(null));
		Rectangle bombBounds_down = new Rectangle(x, y, img_down.getWidth(null), img_down.getHeight(null));
		Rectangle itemBounds = new Rectangle(item.getX(), item.getY(), item.getWidth(), item.getHeight());
		if (bombBounds_left.intersects(itemBounds) || bombBounds_right.intersects(itemBounds) || bombBounds_up.intersects(itemBounds) || bombBounds_down.intersects(itemBounds)) {
			if (item.getTimeLine() > 0) {
				item.setTimeLine(item.getTimeLine() - 1);
				return false;
			} else {
				return true;
			}
		}
		return false;
	}
	
	public boolean BombBangvsBomb(Bomb bomb) {
		Rectangle bombBounds_left = new Rectangle(x + 45 - img_left.getWidth(null), y, img_left.getWidth(null),
				img_left.getHeight(null));
		Rectangle bombBounds_right = new Rectangle(x, y, img_right.getWidth(null), img_right.getHeight(null));
		Rectangle bombBounds_up = new Rectangle(x, y + 45 - img_up.getHeight(null), img_up.getWidth(null),
				img_up.getHeight(null));
		Rectangle bombBounds_down = new Rectangle(x, y, img_down.getWidth(null), img_down.getHeight(null));
		Rectangle bombBounds_center = new Rectangle(bomb.getX(), bomb.getY(), bomb.getWidth(), bomb.getHeight());
		if (bombBounds_left.intersects(bombBounds_center) || bombBounds_right.intersects(bombBounds_center) || bombBounds_up.intersects(bombBounds_center) || bombBounds_down.intersects(bombBounds_center)) {
			return true;
		}
		return false;
	}
	

	public boolean BombBangvsBox(Box box) {
		if (box.getType() == Box.DISALLROW_BANG) {
			return false;
		}
		Rectangle bombBounds_left = new Rectangle(x + 45 - img_left.getWidth(null), y, img_left.getWidth(null),
				img_left.getHeight(null));
		Rectangle bombBounds_right = new Rectangle(x, y, img_right.getWidth(null), img_right.getHeight(null));
		Rectangle bombBounds_up = new Rectangle(x, y + 45 - img_up.getHeight(null), img_up.getWidth(null),
				img_up.getHeight(null));
		Rectangle bombBounds_down = new Rectangle(x, y, img_down.getWidth(null), img_down.getHeight(null));
		Rectangle boxBounds = new Rectangle(box.getX(), box.getY(), box.getWidth(), box.getHeight());
		if (bombBounds_left.intersects(boxBounds) || bombBounds_right.intersects(boxBounds) || bombBounds_up.intersects(boxBounds) || bombBounds_down.intersects(boxBounds)) {
			return true;
		}
		return false;
	}
	
	public boolean BombBangVsActor(Actor actor) {
		Rectangle bombBounds_left = new Rectangle(x + 45 - img_left.getWidth(null) + 5, y + 5, img_left.getWidth(null) - 5,
				img_left.getHeight(null) - 10);
		Rectangle bombBounds_right = new Rectangle(x, y + 5, img_right.getWidth(null) - 5, img_right.getHeight(null) - 10);
		Rectangle bombBounds_up = new Rectangle(x + 5, y + 45 - img_up.getHeight(null) + 5, img_up.getWidth(null) - 5,
				img_up.getHeight(null) - 10);
		Rectangle bombBounds_down = new Rectangle(x + 5, y, img_down.getWidth(null) - 10, img_down.getHeight(null) - 5);
		Rectangle actorBounds = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
		if (bombBounds_left.intersects(actorBounds) || bombBounds_right.intersects(actorBounds) || bombBounds_up.intersects(actorBounds) || bombBounds_down.intersects(actorBounds)) {
			return true;
		}
		return false;
	}

	

	

	

}
