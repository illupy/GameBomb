package GUI;

import javax.swing.*;
// import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Toolkit;


public class GUIFrame extends JFrame {
    protected static int GUI_W = 947 + 6 ;
    protected static int GUI_H = 675 + 29;
    
    public GUIFrame() {
        setSize(GUI_W, GUI_H);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new GUIManager());
        setLocationRelativeTo(null);
        Image image = Toolkit.getDefaultToolkit().getImage("src/Images/dau.png");
		setIconImage(image);
		setTitle("BOOM!!!");
        setVisible(true);

    }
    

}
