package Snake_Game_Java;

import javax.swing.*;
import java.awt.*;

public class Main extends JPanel {

    public static final int CELL_SIZE=20;
    public static int width=400;
    public static int height=400;

    public static int row=height/CELL_SIZE;//寬度
    public static int colum=width/CELL_SIZE;//高度
    private Snake snake;
    public  Main(){
        snake=new Snake();//建立出Snake
    }

    @Override
    public  void paintComponent(Graphics g){
        //黑色背景
        g.fillRect(0,0,width,height);//背景為黑色
        snake.drawSnake(g);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(width,height);//設定寬度和高度
    }

    public static void main(String[] args) {
        JFrame window=new JFrame("Snake Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new Main());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
    }

}
