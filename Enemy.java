//imports
import java.awt.Graphics;
import java.awt.Color;


public class Enemy {


    private int x, y, width = 100, height = 30;
    private int speed = 15;

    public Enemy(int startX, int startY) {
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
       // GamePanel.bullets.add(bullet); // wahrscheinlich eigene liste für enemy bullets

    }


    public boolean isCollidingWithBullet(Bullet b){

    int bulletX = b.getX();
    int bulletY = b.getY();
    int bulletW = b.getWidth();
    int bulletH = b.getHeight();

    // wenn eine kannte sich iwi berührt bzw überschneidet dann true ( AABB-Kollision (Axis-Aligned Bounding Box))
    return bulletX < x + width && bulletX + bulletW > x && bulletY < y + height && bulletY + bulletH > y;

    }






    //spieler malen
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(x, y, width, height);
    }

    //get mothoden
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
