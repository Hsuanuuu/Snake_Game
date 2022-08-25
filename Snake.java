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

    public void drawSnake(Graphics g) {
        g.setColor(Color.orange);
        for (Node n : snakeBody) {
            g.fillOval(n.x, n.y, Main.CELL_SIZE,Main.CELL_SIZE);
        }

    }
}