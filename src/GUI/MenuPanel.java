package GUI;
//phần này ko cần ghép thêm gì
//models.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPanel extends JPanel implements MouseListener {
    private GUIManager guiManager;
    private JLabel lbPlay;
    private JLabel lbOption;
    private JLabel lbHighScore;
    private JLabel lbExit;

    public MenuPanel(GUIManager guiManager) {
        this.guiManager = guiManager;
        setLayout(null);
        addComp();
    }

    private void addComp() {
        int x = 363;
        int y = 250;

        lbPlay = setLabel(x, y, "/Images/Play.png");
        add(lbPlay);
        lbPlay.addMouseListener(this);
        y += 70 + 27;

        lbOption = setLabel(x, y, "/Images/Option.png");
        add(lbOption);
        lbOption.addMouseListener(this);
        y += 70 + 27;

        lbHighScore = setLabel(x, y, "/Images/HightScore.png");
        add(lbHighScore);
        lbHighScore.addMouseListener(this);
        y += 70 + 27;

        lbExit = setLabel(x, y, "/Images/Exit.png");
        add(lbExit);
        lbExit.addMouseListener(this);
    }

    private JLabel setLabel(int x, int y, String url) {
        JLabel jLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));
        jLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLabel.setLocation(x, y);
        jLabel.setIcon(imageIcon);
        return jLabel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Image image = new ImageIcon(getClass().getResource("/Images/background_Menu.png")).getImage();
        g2d.drawImage(image, -15, 0, GUIManager.W_FRAME + 15, GUIManager.H_FRAME, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(lbPlay)) {
            guiManager.getBoomPanel().setIS_PAUSE(false);
            guiManager.showChooseActor();
        } else if (e.getSource().equals(lbOption)) {
            guiManager.showOption();
        } else if (e.getSource().equals(lbHighScore)) {
            guiManager.showHighScore();
        } else if (e.getSource().equals(lbExit)) {
            guiManager.exit();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(lbPlay)) {
            lbPlay.setIcon(new ImageIcon(getClass().getResource("/Images/Play2.png")));
        } else if (e.getSource().equals(lbOption)) {
            lbOption.setIcon(new ImageIcon(getClass().getResource("/Images/Option2.png")));
        } else if (e.getSource().equals(lbHighScore)) {
            lbHighScore.setIcon(new ImageIcon(getClass().getResource("/Images/HightScore2.png")));
        } else if (e.getSource().equals(lbExit)) {
            lbExit.setIcon(new ImageIcon(getClass().getResource("/Images/Exit2.png")));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(lbPlay)) {
            lbPlay.setIcon(new ImageIcon(getClass().getResource("/Images/Play.png")));
        } else if (e.getSource().equals(lbOption)) {
            lbOption.setIcon(new ImageIcon(getClass().getResource("/Images/Option.png")));
        } else if (e.getSource().equals(lbHighScore)) {
            lbHighScore.setIcon(new ImageIcon(getClass().getResource("/Images/HightScore.png")));
        } else if (e.getSource().equals(lbExit)) {
            lbExit.setIcon(new ImageIcon(getClass().getResource("/Images/Exit.png")));
        }
    }
}
