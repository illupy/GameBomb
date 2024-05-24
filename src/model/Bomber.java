package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;


public abstract class Bomber extends Actor {
    public static int RUN = 0;
    public static int NO_RUN = 1;
    protected int sizeBomb, quantityBomb, status, score, heart;  

    

   	public abstract void setBounds(int x, int y);// hồi sinh

   	public int getScore() {
   		return score;
   	}

   	public void setScore(int score) {
   		this.score = score;
   	}

   	public void setImg(Image img) {
   		this.img = img;
   	}

   	public int getStatus() {
   		return status;
   	}

   	public void setStatus(int status) {
   		this.status = status;
   	}

   	public int getQuantityBomb() {
   		return quantityBomb;
   	}

   	public abstract void setQuantityBomb(int quantityBomb);

   	public abstract void setSizeBomb(int sizeBomb);

   	public int getSizeBomb() {
   		return sizeBomb;
   	}

   	public int getType() {
   		return type;
   	}

   	public int getHeart() {
   		return heart;
   	}

   	public void setHeart(int heart) {
   		this.heart = heart;
   	}

   	public void setChange_orient(int orient) {
    		super.changeOrient(orient);
    	}
    
    
	// Khai báo nhân vật đặt bomb
	public Bomber(int x, int y, int type, int orient, int speed, int sizebomb, int quantityBomb) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.orient = orient;
		this.speed = speed;
		this.sizeBomb = sizebomb;
		this.quantityBomb = quantityBomb;
	}
    
	
	// Ghi đè phương thức move của lớp cha
	@Override
	public boolean move(int count, ArrayList<Bomb> arrBomb, ArrayList<Box> arrBox) {
		if (status == DEAD) {
			return false;  // nếu chết k di chuyển 
		}
		return super.move(count, arrBomb, arrBox);  //gọi phương thức move để di chuyển nhân vật nếu không chết
	}

	public boolean BomberVsActor(Actor actor) {
		if (status == DEAD) {
			return false;
		}
		Rectangle bomberBounds = new Rectangle(x, y, width, height);  // tạo một hình chữ nhật cho boomber
		Rectangle actorBounds = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
		return bomberBounds.intersects(actorBounds);  // kiểm tra xem hai hình chữ nhật có giao nhau không
	}


	
}