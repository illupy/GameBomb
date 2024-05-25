package model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;



public class Bomb extends Actor {
    protected int size, countdown;
    
    public void deadlineBomb() {
		 this.countdown --;
	 }
	 
	 public int getCountdown() {
		 return countdown;
	 }
	 
	 public void setTimeline(int countdown) {
		 this.countdown = countdown;
	 }
	 
	 public int getSize() {
		 return size;
	 }
	 
	 public void setSize(int size) {
		 this.size = size;
	 }
    
    
    public Bomb(int x, int y, int size, int countdown) {
   	 this.x = (x/45)*45;
   	 this.y = (y/45)*45;
   	 this.size = size;
   	 this.countdown = countdown;
   	 this.orient = 0;
   	 this.type = Actor.BOMB;
   	 img = new ImageIcon(getClass().getResource("/Images/bomb.gif")).getImage();
		 this.width = img.getWidth(null); // khởi tạo chiều dài và chiều rộng theo hình ảnh
		 this.height = img.getHeight(null);
   	 }
    
    public Bomb(int x, int y, int size, int countdown, int speed, int orient) {
   	 this.x = (x/45)*45;
   	 this.y = (y/45)*45;
   	 this.size = size;
   	 this.speed = 5;
   	 this.orient = orient;
   	 this.countdown = countdown;
   	 img = new ImageIcon(getClass().getResource("/Images/bomb.gif")).getImage();
   	 this.width = img.getWidth(null);
   	 this.height = img.getHeight(null);
    }
    
    @Override
    public void drawActor(Graphics2D g2d) {
   	 super.drawActor(g2d);
   	 g2d.drawImage(img, x, y, null);
	 };
	 
	 
	 public boolean BombvsnewBomb(int xNewBomb, int yNewBomB) {
		 Rectangle bombBounds = new Rectangle(x, y, 45, 45);
		 Rectangle newbombBounds = new Rectangle(xNewBomb, yNewBomB);
		 return bombBounds.intersects(newbombBounds);
	 }
	 
	 public boolean BombvsBomber(Bomber bomber) {
		 Rectangle bombBounds = new Rectangle(x, y, 45, 45);
		 Rectangle bomberBounds = new Rectangle(bomber.getX(), bomber.getY(), bomber.getWidth(), bomber.getHeight() );
		 return bombBounds.intersects(bomberBounds);
	 }
	 
	 
	public int BombvsActor(Actor actor) {
		if (actor.getRunBomb() == Bomber.RUN) {
			return 0;
		}
		Rectangle bombBounds = new Rectangle(x, y, 45, 45);
		Rectangle actorBounds = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
		if (bombBounds.intersects(actorBounds)) {
			if (actor.getType() == Monster.BOSS) {
				return 2;
			}
			return 1;
		}
		;
		return 0;
	}	 
	 
}
