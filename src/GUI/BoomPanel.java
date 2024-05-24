package GUI;

//import models.Bomber;
//import models.GameManager;
//import sounds.GameSound;

import javax.swing.*;

import model.Manager;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.BitSet;
import GUI.GUIFrame;

public class BoomPanel extends JPanel implements KeyListener, Runnable, MouseListener {
    //private GameManager gameManager;
	private Manager manager;
    protected JPanel gamePanel;
    private JLabel lb_back;
    private BitSet bitSet = new BitSet();
    private GUIManager guiManager;
    private int move=0;
    private int count=0;
    private int timeDead = 0;
    private int timeNextRound = 0;
    private int timeWin = 0;
    private int timeLose = 0;
    private boolean IS_RUNNING = true;
    private boolean IS_PAUSE = true;
    public static boolean HIT_PAUSE = false;
    Thread thread;

    public BoomPanel(GUIManager guiManager) {
    	setLayout(null);
        manager = new Manager();
        this.guiManager = guiManager;
        gamePanel = pngame;

        //addLabel();
        addKeyListener(this);
       thread = new Thread(this);
       thread.start();
       setFocusable(true);
       
    }

    private void addLabel() {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Images/button_back.png"));
        lb_back = new JLabel();
        lb_back.setIcon(imageIcon);
        lb_back.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        lb_back.setLocation(729, 564+10);
        lb_back.addMouseListener(this);
        add(lb_back);
    }

    private JPanel pngame  = new JPanel(){
        @Override
        public void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new java.awt.BasicStroke(2));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            System.out.println("paint component");
            g2d.setPaint(Color.BLACK);
            g2d.fillRect(0, 0, 947, 45);
            g2d.fillRect(0, GUIFrame.GUI_H-150, 947, 150);
            g2d.setFont(new Font("Georgia", Font.BOLD, 20));
            g2d.setPaint(Color.WHITE);
            g2d.drawString("HUST - ONE LOVE ONE FUTURE!", 300, 25);
            


            
           manager.draWBackground(g2d);
           manager.drawAllItem(g2d);
           manager.drawAllBox(g2d);
           manager.drawAllBomb(g2d);
           g2d.drawString("GOOD GAME", 400,GUIFrame.GUI_H-50);
            manager.drawAllMonster(g2d);

        }
     };

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        bitSet.set(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        bitSet.clear(e.getKeyCode());
    }

    @Override
    public void run() {
        while (IS_RUNNING) {
            while (IS_PAUSE) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
          
			manager.moveAllMonster(count);
			gamePanel.repaint();
			count++;
			if (count == 1000000) {
				count = 0;
			}

            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
       }

   }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(lb_back)) {
            setIS_PAUSE(true);
            HIT_PAUSE = true;
            guiManager.showMenu();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(lb_back)) {
            lb_back.setIcon(new ImageIcon(getClass().getResource("/Images/button_back(1).png")));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(lb_back)) {
            lb_back.setIcon(new ImageIcon(getClass().getResource("/Images/button_back.png")));
        }
    }

    public boolean isIS_RUNNING() {
        return IS_RUNNING;
    }

    public void setIS_RUNNING(boolean IS_RUNNING) {
        this.IS_RUNNING = IS_RUNNING;
    }

    public boolean isIS_PAUSE() {
        return IS_PAUSE;
    }

    public void setIS_PAUSE(boolean IS_PAUSE) {
        this.IS_PAUSE = IS_PAUSE;
    }
    
}
