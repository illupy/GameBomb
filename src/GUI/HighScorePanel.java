package GUI;

import model.HighScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class HighScorePanel extends JPanel implements MouseListener {
   private ArrayList<HighScore> highScores;
    private GUIManager guiManager;
    private JLabel lb_back;

    public HighScorePanel(GUIManager guiManager) {
        this.guiManager = guiManager;
        setLayout(null);
        addComp();
        initHighScore("src/highScore/highscore.txt");
    }

    private void addComp() {//thiết lập vị trí, chèn ảnh cancel1, thêm tín hiệu chuột
        int x = 829;
        int y = 564;
        lb_back = setLabel(x, y, "/Images/cancel1.png");
        lb_back.addMouseListener(this);
        add(lb_back);
    }
    //cấu hình vị trí, kích thước nhãn cancel
    private JLabel setLabel(int x, int y, String url) {
        JLabel jLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(url));
        jLabel.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        jLabel.setIcon(imageIcon);
        jLabel.setLocation(x, y);
        return jLabel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
      //vẽ hình nền dưới và hình nền điểm cao
        // Draw background;
        Image background = new ImageIcon(getClass().getResource("/Images/background_Menu2.png")).getImage();
        g2d.drawImage(background, 0, 0,  null);
        Image backgrLb = new ImageIcon(getClass().getResource("/Images/background_highscore.png")).getImage();
        g2d.drawImage(backgrLb, 55, 40, null);
 //   ẩn tạm cái in bảng điểm 	
        // Draw HighScore
        int y = 170;
        int x = 200;
       for (int i = 0; i < highScores.size(); i++) {
            if (i==9) break;
            g2d.setColor(Color.PINK);
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            g2d.drawString(Integer.toString(i + 1), x, y);
            g2d.drawString(highScores.get(i).getName(), x + 100, y);
            g2d.drawString(Integer.toString(highScores.get(i).getScore()), x + 400, y);
            y += 50;
        } 
           
    }

    public void initHighScore(String highScorePath) {
       highScores = new ArrayList<>();
        try {
            String line = "";
            FileReader fileReader = new FileReader(highScorePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String name;
                int score;
                String[] arr = line.split(":");
                name = arr[0];
                score = Integer.parseInt(arr[1]);
               HighScore highScore = new HighScore(name, score);
               highScores.add(highScore);
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {//phương thức ấn chuột thì trở lại menu
        if (e.getSource().equals(lb_back)) {
              guiManager.showMenu();
                    }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource().equals(lb_back)) {
            lb_back.setIcon(new ImageIcon(getClass().getResource("/Images/cancel2.png")));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(lb_back)) {
            lb_back.setIcon(new ImageIcon(getClass().getResource("/Images/cancel1.png")));
        }
    }
}
