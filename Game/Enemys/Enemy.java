package Game.Enemys;//imports
import Game.Objects.Bullet;


import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Enemy {
    private int x;
    private int y;
    private final int width = 100;
    private final int height = 100;
    private final int type ;
    private final int speed ;
    private  int hp;
    BufferedImage texture;
    long oldMillis= 0;


    public Enemy(int startX, int startY, int type, int waveCount, BufferedImage texture) {
        this.x = startX;
        this.y = startY;
        //später brauchbar
        this.type=type;
        this.speed= this.getSpeed(waveCount);
        this.hp = this.getHp(waveCount);
        this.texture = texture;
    }



    public void moveLeft(int s) {
        x -= s;
    }

    public void moveRight(int s) {
        x += s;
    }
    public void moveDown() {
        y += speed;
    }
    public void moveUp() {
        y -= speed;
    }
    public void moveRandomLR(){
        long newMilis = System.currentTimeMillis();
                //wenn nicht bewegt seit x milis
                if((newMilis-oldMillis)>250){
                    if(Math.random()<0.5){
                            moveLeft(speed*2);
                        }
                        else{
                            moveRight(speed*2);
                        }
                oldMillis = newMilis;
                }
    }

    //public void shoot() {
        //TODO: für gegener machen und dann auch kollison mit spieler 
       // Game.Objects.Bullet bullet = new Game.Objects.Bullet(x,y);
       //Game.Panels.GamePanel.bullets.add(bullet); // wahrscheinlich eigene liste für enemy bullets

    //}

    public boolean isCollidingWithBullet(Bullet b){

    int bulletX = b.getX();
    int bulletY = b.getY();
    int bulletW = b.getWidth();
    int bulletH = b.getHeight();

    // wenn eine kannte sich iwi berührt bzw überschneidet dann true ( AABB-Kollision (Axis-Aligned Bounding Box))
    return bulletX < x + width && bulletX + bulletW > x && bulletY < y + height && bulletY + bulletH > y;
    }

    //enemy  malen
    public void draw(Graphics g) {
        if (texture != null) {
            g.drawImage(texture, x, y, width, height, null);
        } else {
            g.setColor(Color.PINK);
            g.fillRect(x, y, width, height);
        }
    }

    private int getSpeed(int waveCount) {

        return switch (this.getType()) {
            //normaler enemy
            case 1 -> (int) (1 * (Math.pow(1.05, waveCount)));
            //schneller enemy
            case 2 -> (int) (2 * (Math.pow(1.05, waveCount)));
            default -> 0;
        };
       
    }
    private int getHp(int waveCount) {

        return switch (this.getType()) {
            //normaler enemy
            case 1 -> (int) (1 * (Math.pow(1.25, waveCount)));
            //schneller enemy
            case 2 -> (int) (1 * (Math.pow(1.09, waveCount)));
            default -> 0;
        };
    }
    public int getType() { return type; }
    public int getY() { return y; }

    public int getHP() {return hp;}

    public void setHP(int hp) { this.hp=hp;}
}
