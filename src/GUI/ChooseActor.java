package GUI;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//phần này ko cần ghép thêm gì
public class ChooseActor extends JPanel implements MouseListener {
	private GUIManager guiManager;
    private JLabel lbBack;
    private JLabel lbKhoKho;
	private JLabel lbBeBong;
	private JLabel lbTiaChop;
	private JLabel lbTiBanh;
	private JLabel lbOptchoose;
    public ChooseActor(GUIManager guiManager) {
        this.guiManager = guiManager;
        setLayout(null);
        addComp();
    }
    private void addComp() {
        int x = 829;
        int y = 564;
        lbBack = setLabel(x, y, "/Images/cancel1.png");
        lbKhoKho = setLabel(126, 122, "/Images/khoKho1.png");
		lbBeBong = setLabel(110, 370, "/Images/beBong1.png");
		lbTiBanh = setLabel(354, 370, "/Images/tiBanh1.png");
		lbTiaChop = setLabel(598, 370, "/Images/tiaChop1.png");

		lbOptchoose = setLabel(407, 123, "/Images/opkhokho.png");
        add(lbKhoKho);
        add(lbBeBong);
        add(lbTiBanh);
        add(lbTiaChop);
        add(lbBack);
        add(lbOptchoose);
        lbKhoKho.addMouseListener(this);
        lbBeBong.addMouseListener(this);
        lbTiBanh.addMouseListener(this);
        lbTiaChop.addMouseListener(this);
        lbOptchoose.addMouseListener(this);
        lbBack.addMouseListener(this);
    }
    private JLabel setLabel(int x, int y, String URL) {
        JLabel jLabel = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource(URL));
        jLabel.setIcon(icon);
        jLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
        jLabel.setLocation(x, y);
        return jLabel;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Image bgimage = new ImageIcon(getClass().getResource("/Images/background_Menu2.png")).getImage();
        g2d.drawImage(bgimage, 0, 0, null);
        Image backgrlb = new ImageIcon(getClass().getResource("/Images/background_Actor.png")).getImage();
        g2d.drawImage(backgrlb, 55, 40, null);

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
    	 if (e.getSource().equals(lbKhoKho)) {
             guiManager.getBoomPanel().setIS_PAUSE(false);
             guiManager.showPlayGame();
         }
    	 else if (e.getSource().equals(lbBeBong)) {
             guiManager.getBoomPanel().setIS_PAUSE(false);
             guiManager.showPlayGame();
         }
    	 else if (e.getSource().equals(lbTiBanh)) {
             guiManager.getBoomPanel().setIS_PAUSE(false);
             guiManager.showPlayGame();
         }
    	 else if (e.getSource().equals(lbTiaChop)) {
             guiManager.getBoomPanel().setIS_PAUSE(false);
             guiManager.showPlayGame();
         }
    	 else if (e.getSource().equals(lbBack)) {
            if (BoomPanel.HIT_PAUSE) {
               
                guiManager.showMenu();
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(lbBack)) {
            lbBack.setIcon(new ImageIcon(getClass().getResource("/Images/cancel2.png")));
        }
        else if (e.getSource().equals(lbKhoKho)) {
            lbKhoKho.setIcon(new ImageIcon(getClass().getResource("/Images/khoKho2.png")));
            lbOptchoose.setIcon(new ImageIcon(getClass().getResource("/Images/opkhokho.png")));
        }
        else if (e.getSource().equals(lbBeBong)) {
            lbBeBong.setIcon(new ImageIcon(getClass().getResource("/Images/beBong2.png")));
            lbOptchoose.setIcon(new ImageIcon(getClass().getResource("/Images/opbebong.png")));
        }
        else if (e.getSource().equals(lbTiBanh)) {
            lbTiBanh.setIcon(new ImageIcon(getClass().getResource("/Images/tiBanh2.png")));
            lbOptchoose.setIcon(new ImageIcon(getClass().getResource("/Images/optibanh.png")));
        }
        else if (e.getSource().equals(lbTiaChop)) {
            lbTiaChop.setIcon(new ImageIcon(getClass().getResource("/Images/tiaChop2.png")));
            lbOptchoose.setIcon(new ImageIcon(getClass().getResource("/Images/optiachop.png")));
        }
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(lbBack)) {
            lbBack.setIcon(new ImageIcon(getClass().getResource("/Images/cancel1.png")));
        }
        else if (e.getSource().equals(lbKhoKho)) {
            lbKhoKho.setIcon(new ImageIcon(getClass().getResource("/Images/khoKho1.png")));
        
        }
        else if (e.getSource().equals(lbBeBong)) {
            lbBeBong.setIcon(new ImageIcon(getClass().getResource("/Images/beBong1.png")));
            
        }
        else if (e.getSource().equals(lbTiBanh)) {
            lbTiBanh.setIcon(new ImageIcon(getClass().getResource("/Images/tiBanh1.png")));
            
        }
        else if (e.getSource().equals(lbTiaChop)) {
            lbTiaChop.setIcon(new ImageIcon(getClass().getResource("/Images/tiaChop1.png")));
            
        }
    }
    
}
