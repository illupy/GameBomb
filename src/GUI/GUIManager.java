package GUI;

//import models.HighScore;
//import sounds.GameSound;

import javax.swing.*;
import java.awt.*;

public class GUIManager extends JPanel {
    public static final int PLAY_W = 947 ;
    public static final int PLAY_H = 675 ;
    public static final int W_FRAME = 947 + 6;
    public static final int H_FRAME = 675 + 29;
    private ChooseActor chooseActor;
    private BoomPanel boomPanel;
    private HighScorePanel highScorePanel;
    private OptionPanel optionPanel;
    private MenuPanel menuPanel;
    private CardLayout cardLayout; // Card layout se giup luu nhieu panel tai cung 1 vi tri va hien thi cai can
                                   // hien thi khi can
 

    private static String MENU_TAG = "menu";
    private static String OPTION_TAG = "option";
    private static String HIGHSCORE_TAG = "highscore";
    private static String PLAYGAME_TAG = "playgame";
    private static String CHOOSEACTOR_TAG = "chooseactor";

    public GUIManager() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        menuPanel = new MenuPanel(this);
        add(menuPanel, MENU_TAG);
        boomPanel = new BoomPanel(this);
        add(boomPanel.gamePanel, PLAYGAME_TAG);
        highScorePanel = new HighScorePanel(this);
        add(highScorePanel, HIGHSCORE_TAG);
        optionPanel = new OptionPanel(this);
        add(optionPanel, OPTION_TAG);
        
        chooseActor = new ChooseActor(this);
        add(chooseActor, CHOOSEACTOR_TAG);

        showMenu();
    }

    public void showMenu() {
        cardLayout.show(this, MENU_TAG);
        menuPanel.requestFocus();
    //    GameSound.getIstance().stop();
   //     GameSound.getIstance().getAudio(GameSound.MENU).loop();
    }

   
    public void showOption() {
        cardLayout.show(this, OPTION_TAG);
        optionPanel.requestFocus();
    }

    public void showHighScore() {
    //    highScorePanel.initHighScore("src/highScores/highscore.txt");
        cardLayout.show(this, HIGHSCORE_TAG);
        highScorePanel.requestFocus();
        
    }
    public void showChooseActor() {
    	cardLayout.show(this, CHOOSEACTOR_TAG);
       chooseActor.requestFocus();
    }
    public void showPlayGame(int type) {
        boomPanel.chooseActor(type);
        cardLayout.show(this, PLAYGAME_TAG);
        boomPanel.gamePanel.requestFocus();
 //      GameSound.getIstance().stop();
 //      GameSound.getIstance().getAudio(GameSound.PLAYGAME).loop();
    }
    public void exit() {
        boomPanel.setIS_RUNNING(false);
        System.exit(0);
    }

    public BoomPanel getBoomPanel() {
        return boomPanel;
    }

    public void setBoomPanel(BoomPanel boomPanel) {
        this.boomPanel = boomPanel;
    }
    
}
