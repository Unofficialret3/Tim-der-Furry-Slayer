import java.awt.Color;
import java.awt.Graphics;

public class Bullet {

    private int width = 5;
    private int height = 20;
    private int x, y;
    //private int speedY;
    //private int damage;

    public Bullet (int x,int y){
        
        this.x = x + GamePanel.player1.getWidth()/2 ; // damit es aus der mitte des players kommt
        this.y = y;
        

    }


    
    //malen
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }


    //set und getter 
   
    
    public void setX(int x) {
        this.x = x;
        }

    public void setY(int y) {
        this.y = y;
        }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}