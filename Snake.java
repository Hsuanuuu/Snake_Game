package Snake_Game_Java;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private ArrayList<Node> snakeBody; //儲存蛇的身體，每一個身體為一個node
    //constructor

    public Snake() {
        snakeBody = new ArrayList<>();
        snakeBody.add(new Node(80, 0));
        snakeBody.add(new Node(60, 0));
        snakeBody.add(new Node(40, 0));
        snakeBody.add(new Node(20, 0));
    }

    public ArrayList<Node> getSnakeBody(){
        return snakeBody;
    }


    public void drawSnake(Graphics g) {
        g.setColor(Color.orange);
        for (int i=0;i<snakeBody.size();i++) {
            if(i==0){
                g.setColor(Color.blue);
            }
            else {
                g.setColor(Color.orange);
            }

            Node n=snakeBody.get(i);
            if(n.x>=Main.width){
                n.x=0;
            }
            else if(n.x<0){
                n.x=Main.width-Main.CELL_SIZE;
            }
            else  if(n.y>=Main.height){
                n.y=0;
            }
            else if(n.y<0){
                n.y=Main.width-Main.CELL_SIZE;
            }
            g.fillOval(n.x, n.y, Main.CELL_SIZE,Main.CELL_SIZE);
        }

    }
}