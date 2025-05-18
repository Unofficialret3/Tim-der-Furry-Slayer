//imports
import java.awt.Graphics;
import java.awt.Color;


public class Player {


    private int x, y, width = 100, height = 30;
    private int speed = 15;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }


    public void shoot() {
        // 
        Bullet bullet = new Bullet(x,y);
        GamePanel.bullets.add(bullet);

    }




    //spieler malen
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    
}
