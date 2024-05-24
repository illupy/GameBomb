package model;

import java.awt.Graphics2D;

public class DrawBox implements IDrawable {

    @Override
    public void draw(Object o, Graphics2D g2d) {
      if(o instanceof Box){
        Box box = (Box) o;
        g2d.drawImage(box.image, box.x,box.y,null);
      }
        
    }
    
    
}
