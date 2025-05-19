//imports
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;


public class Player {
    SoundPlayer player = new SoundPlayer();

    protected int x, y, width = 100, height = 30;
    protected int speed = 15;

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


    public void shoot(){
        // 
        Bullet bullet = new Bullet(x,y);
        GamePanel.bullets.add(bullet);
        player.playLaser();
    }

    //spieler malen
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    //get mothoden
    public int getWidth() {
        return width;
        }
}
