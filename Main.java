package Snake_Game_Java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends JPanel implements KeyListener {

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
    private boolean allowKeyPress;
    private int score;
    private int highest_score;//如果從來沒有玩過遊戲的話不存在
    public  Main(){
        read_highest_score();
        reset();
        addKeyListener(this);
    }

    private void reset(){
        score=0;
        if(snake!=null){
            snake.getSnakeBody().clear();
        }
        allowKeyPress=true;
        direction="Right";
        snake=new Snake();
        fruit=new Fruit();
        setTimer();
    }

    private void setTimer(){
        t=new Timer();
        t.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                repaint();//去執行paintComponent
            }
        },0,speed);
    }

    @Override
    public  void paintComponent(Graphics g){
        //看蛇是不是有咬到自己
        ArrayList<Node> snake_body=snake.getSnakeBody();
        Node head=snake_body.get(0);
        for(int i=1;i<snake_body.size();i++){
            if(snake_body.get(i).x==head.x && snake_body.get(i).y==head.y){
                allowKeyPress=false;
                t.cancel();
                t.purge();
                int response =JOptionPane.showOptionDialog(this,"遊戲結束,你的分數是"+score+"遊戲最高分是"+highest_score+"你要重新開始嗎?","Game Over",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,JOptionPane.YES_OPTION);
                write_a_file(score);
                switch (response){
                    case JOptionPane.CANCEL_OPTION:
                        System.exit(0);
                        break;
                        case JOptionPane.NO_OPTION:
                            System.exit(0);
                            break;
                    case JOptionPane.YES_OPTION:
                        reset();
                        return;//下面都不會執行了


                }
            }
        }


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
        //確定蛇是不是有吃到水果
        if(snake.getSnakeBody().get(0).x==fruit.getX() && snake.getSnakeBody().get(0).y==fruit.getY()){
            fruit.setNewLocation(snake);
            fruit.drawFruit(g);
            score++;
        }else {
        snake.getSnakeBody().remove(snake.getSnakeBody().size()-1);

    }
        snake.getSnakeBody().add(0,newHead);//加上new head
        allowKeyPress=true;
        requestFocusInWindow();
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(allowKeyPress){
            if (e.getKeyCode()==37 && !direction.equals("Right")){
                direction="Left";
            }else if(e.getKeyCode()==38 && !direction.equals("Down")){
                direction="Up";
            }else if(e.getKeyCode()==39 && !direction.equals("Left")){
                direction="Right";
            }else if(e.getKeyCode()==40 && !direction.equals("Up")){
                direction="Down";
            }
            allowKeyPress=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void read_highest_score(){
        try{
            File myObj =new File("score_file.txt");
            Scanner myReader=new Scanner(myObj);
            highest_score=myReader.nextInt();
            myReader.close();
        }catch (FileNotFoundException e){
            highest_score=0;//從來都沒玩過遊戲
            try {
                File myObj=new File("score_file.txt");//創建file
                if(myObj.createNewFile()){
                    System.out.println("檔案創建:"+myObj.getName());
                }
                FileWriter myWriter = new FileWriter(myObj.getName());//寫出file,FileWriter需要有例外路徑IOException
                myWriter.write(0);
            }catch (IOException err){
                System.out.println("發生錯誤");
                err.printStackTrace();
            }
        }
    }

    public void write_a_file(int score){
        try{
            FileWriter myWriter =new FileWriter("score_file.txt");
            if(score > highest_score){
                myWriter.write(""+score);//加上""讓type為String
                highest_score=score;
            }else
                myWriter.write(""+highest_score);
            myWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
