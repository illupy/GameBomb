package model;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Box {
    IDrawable drawTool;
    protected int x,y, width, height,type;
    protected Image image;
    public static int ALLROW_BANG = 0;
	public static int DISALLROW_BANG = 1;
    public Box(int x, int y, int type,String images){
        this.x=x;
        this.y=y;
        this.type=type;
        this.image = new ImageIcon(getClass().getResource(images)).getImage();
        this.width= image.getWidth(null);
        this.height= image.getHeight(null);
    }
void drawActor(Graphics2D g2d){
    drawTool = new DrawBox();
    drawTool.draw(this,g2d);
}
public int isImpactWithActor(Actor actor){
    Rectangle rec1 = new Rectangle(x,y,width,height);
    Rectangle rec2 = new Rectangle(actor.getX(),actor.getY(),actor.getWidth(),getHeight());
   // Rectangle rec3 = new Rectangle();
    if(rec1.intersects(rec2)){
    }
    return 0;

}
public int getType(){
    return this.type;
}
public int getX(){
    return this.x;
}
public int getY(){
    return this.y;
}
public int getWidth(){
    return this.width;
}
public int getHeight(){
    return this.height;
}
// Chưa sửa
public int isImpactBoxvsActor(Actor actor) {
	Rectangle rec1 = new Rectangle(x, y, width, height);
	Rectangle rec2 = new Rectangle(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
	Rectangle rec3 = new Rectangle();
	if (actor.getType() == Monster.BOSS || actor.getType() == Monster.GHOST) {
		return 0;
	}
	if (rec1.intersects(rec2)) {
		rec1.intersect(rec1, rec2, rec3);
		if (rec3.getHeight() == 1 && (actor.getOrient() == Actor.UP || actor.getOrient() == Actor.DOWN)) {
			if (actor.getX() == rec3.getX()) {
				return (int) rec3.getWidth();
			} else {
				return (int) -rec3.getWidth();
			}
		} else {
			if (actor.getY() == rec3.getY()) {
				return (int) rec3.getHeight();
			} else {
				return (int) -rec3.getHeight();
			}
		}
	}
	return 0;
}
}

