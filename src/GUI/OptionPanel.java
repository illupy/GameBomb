package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//phần này ko cần ghép thêm gì nx
public class OptionPanel extends JPanel implements MouseListener {
    private GUIManager guiManager;
    private JLabel lbBack;

    public OptionPanel(GUIManager guiManager) {
        this.guiManager = guiManager;
        setLayout(null);
        addComp();
    }

    private void addComp() {
        int x = 829;
        int y = 564;
        lbBack = setLabel(x, y, "/Images/cancel1.png");
        add(lbBack);
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
        Image bgImage = new ImageIcon(getClass().getResource("/Images/background_Menu2.png")).getImage();
        g2d.drawImage(bgImage, 0, 0, null);
        Image backgrLb = new ImageIcon(getClass().getResource("/Images/background_option.png")).getImage();
        g2d.drawImage(backgrLb, 55, 40, null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(lbBack)) {
            
                guiManager.showMenu();
             
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        lbBack.setIcon(new ImageIcon(getClass().getResource("/Images/cancel2.png")));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        lbBack.setIcon(new ImageIcon(getClass().getResource("/Images/cancel1.png")));
    }
}
