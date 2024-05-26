package GUI;


import javax.swing.*;

import model.Bomber;
import model.Manager;
import sound.GameSound;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;


public class BoomPanel extends JPanel implements Runnable{
    //private GameManager gameManager;
	private Manager manager;
    protected JPanel gamePanel;
    private BitSet bitSet = new BitSet();
    private GUIManager guiManager;
    private int move=0,i=0;
    private int count=0;
    private int timeDead = 0;
    private int timeNextRound = 0;
    private int timeLose = 0;
    private boolean IS_RUNNING = true;
    private boolean IS_PAUSE = true;
    public static boolean HIT_PAUSE = false;
    Thread thread;

    public BoomPanel(GUIManager guiManager) {
    	setLayout(null);
        manager = new Manager(2);
        this.guiManager = guiManager;
        gamePanel = pngame;
        gamePanel.addKeyListener(keyAdapter);
       thread = new Thread(this);
       thread.start();
       setFocusable(true);
       
    }
    public void chooseActor(int type) {
		manager = new Manager(type);
	}

    private JPanel pngame  = new JPanel(){
        @Override
        public void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            manager.draWBackground(g2d);
            manager.drawAllBomb(g2d);
            g2d.setStroke(new java.awt.BasicStroke(2));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(Color.BLACK);
            g2d.fillRect(0, 0, 947, 45);
            g2d.fillRect(0, GUIFrame.GUI_H-72, 947, 72);
            g2d.setFont(new Font("Georgia", Font.BOLD, 20));
            g2d.setPaint(Color.WHITE);
            g2d.drawString("HUST - ONE LOVE ONE FUTURE!", 300, 25);
            
            
            
            manager.drawAllItem(g2d);
            manager.drawAllBox(g2d);
            manager.drawInfo(g2d);
           g2d.drawString("GOOD GAME", 400,GUIFrame.GUI_H-50);
           manager.drawAllMonster(g2d);
           manager.getBomber().drawActor(g2d);
           manager.drawDialog(g2d, manager.getStatus());

        }
     };

    @Override
    public void run() {
        while (IS_RUNNING) {
            // Điều khiển nhan vật
            if (bitSet.get(KeyEvent.VK_A) || bitSet.get(KeyEvent.VK_LEFT)) {
				manager.getBomber().changeOrient(Bomber.LEFT);
				manager.getBomber().move(count, manager.getArrBomb(), manager.getArrBox());
				runActor();
			}
			if (bitSet.get(KeyEvent.VK_D) || bitSet.get(KeyEvent.VK_RIGHT)) {
				manager.getBomber().changeOrient(Bomber.RIGHT);
				manager.getBomber().move(count, manager.getArrBomb(), manager.getArrBox());
				runActor();
			}
			if (bitSet.get(KeyEvent.VK_W) || bitSet.get(KeyEvent.VK_UP)) {
				manager.getBomber().changeOrient(Bomber.UP);
				manager.getBomber().move(count, manager.getArrBomb(), manager.getArrBox());
				runActor();
			}
			if (bitSet.get(KeyEvent.VK_S) || bitSet.get(KeyEvent.VK_DOWN)) {
				manager.getBomber().changeOrient(Bomber.DOWN);
				manager.getBomber().move(count, manager.getArrBomb(), manager.getArrBox());
				runActor();
			}
			if (bitSet.get(KeyEvent.VK_J) || bitSet.get(KeyEvent.VK_SPACE)) {
				manager.innitBomb();
				manager.getBomber().setRunBomb(Bomber.RUN);
				runActor();
			}

            manager.setRunBomer();
            manager.collectItem();
            manager.deadLineAllBomb();
            manager.checkDead();
            manager.checkWinAndLose();
			manager.moveAllMonster(count);
			gamePanel.repaint();

			count++;
            // nếu thua chuyển về menu
            if (manager.getStatus()==1){
                timeLose++;
                if(timeLose==3000){
                    manager.initManager();
                    guiManager.showMenu();
                    timeLose=0;
                }
            }
            // nếu thắng round1,2 chuyển nextround
            if (manager.getStatus()==2){
                timeNextRound++;
                if(timeNextRound==5000){
                    manager.initManager(); 
                    timeNextRound=0;
                }
            }
            // neu win round 3, you win
            if (manager.getStatus()==3){
                timeNextRound++;
                if(timeNextRound==3000){
                    manager.initManager(); 
                    guiManager.showMenu();
                    timeNextRound=0;
                }
            }

			if (count == 1000000) {
				count = 0;
			}
            if (move == 0) {
				manager.changeOrientAll();
				move = 5000;
			}
			if (move > 0) {
				move--;
			}
            if (manager.getBomber().getStatus() == Bomber.DEAD) {
				timeDead++;
				if (timeDead == 1000) {
					manager.setNewBomber();
					timeDead = 0;
				}
			}

            try {
                Thread.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
       }

   }
   	@SuppressWarnings("removal")
    private void runActor() {
        i++;
		if (i == 200) {
			manager.sound.getAudio(GameSound.FOOT).play();
			i = 0;
		}
	}

    KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
		public void keyPressed(KeyEvent e) {
			bitSet.set(e.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent e) {
			bitSet.clear(e.getKeyCode());
		}
    };

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
