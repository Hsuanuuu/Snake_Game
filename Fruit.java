package Snake_Game_Java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Fruit {
    private int x;
    private int y;
    private ImageIcon img;

    public Fruit(){
        img=new ImageIcon("fruit.png");
        //img=new ImageIcon(getClass().getResource("fruit.png"));
        this.x=(int)Math.floor(Math.random()*Main.colum)*Main.CELL_SIZE;//設定fruit隨機的位置
        this.y=(int)Math.floor(Math.random()*Main.row)*Main.CELL_SIZE;
    }

    public int getX(){
      return  this.x=x;
    }

    public int getY(){
        return this.y;
    }
    public void drawFruit(Graphics g){
        //g.setColor(Color.green);
        //g.fillOval(this.x,this.y,Main.CELL_SIZE,Main.CELL_SIZE );
        img.paintIcon(null,g,this.x,this.y);
    }

    public void setNewLocation(Snake s){

        int new_x;
        int new_y;
        boolean overlapping;
        do{
            new_x=(int)Math.floor(Math.random()*Main.colum)*Main.CELL_SIZE;
            new_y=(int)Math.floor(Math.random()*Main.row)*Main.CELL_SIZE;
            overlapping=check_overlap(new_x,new_y,s);
        }while (overlapping);//選的位置跟蛇還是重疊的話，就要再找一個新的位置

        this.x=new_x;
        this.y=new_y;
    }
    private boolean check_overlap(int x,int y,Snake s){
        ArrayList<Node> snake_body=s.getSnakeBody();
        for(int j=0;j<snake_body.size();j++){
            if(x==snake_body.get(j).x && y==snake_body .get(j).y){
                return true;//確實有overlap
            }
        }
        return false;
    }
}
