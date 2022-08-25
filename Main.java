package Snake_Game_Java;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends JPanel {

    public static final int CELL_SIZE=20;
    public static int width=400;
    public static int height=400;

    public static int row=height/CELL_SIZE;//寬度
    public static int colum=width/CELL_SIZE;//高度
    private Snake snake;
    private Fruit fruit;
    private Timer t;
    private int speed=100;
    private static String direction;
    public  Main(){
        snake=new Snake();//建立出Snake
        fruit=new Fruit();
        t=new Timer();
        t.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                repaint();//去執行paintComponent
            }
        },0,speed);
        direction="Right";
    }

    @Override
    public  void paintComponent(Graphics g){
        //黑色背景
        g.fillRect(0,0,width,height);//背景為黑色
        snake.drawSnake(g);
        fruit.drawFruit(g);

        //切下snake的尾端，放回頭的位置
        int snakeX=snake.getSnakeBody().get(0).x;
        int snakeY=snake.getSnakeBody().get(0).y;
        if (direction.equals("Left")) {
            snakeX-=CELL_SIZE;
        }
        else if (direction.equals("Up")){
            snakeY-=CELL_SIZE;
        }
        else if (direction.equals("Right")){
            snakeX+=CELL_SIZE;
        }
        else if(direction.equals("Down")){
            snakeY+=CELL_SIZE;
        }
        Node newHead=new Node(snakeX,snakeY);//new head
        snake.getSnakeBody().remove(snake.getSnakeBody().size()-1);
        snake.getSnakeBody().add(0,newHead);//加上new head

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
