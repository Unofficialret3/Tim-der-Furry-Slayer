package Game.Objects;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bullet {

    private int width;
    private int height;
    private int x, y;
    private BufferedImage image;
    public int health;
    public int weaponType;
    public boolean changedX = false;
    public boolean changedY = false;
    public boolean hit = false;
    public long hitTime;
    public Bullet (int x,int y, int width, int height, String texture, int health, int weaponType){
        this.width = width;
        this.height = height;
        this.health = health;
        this.x = x;
        this.y = y;
        this.weaponType = weaponType;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource(texture)));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Konnte Bild nicht laden: " + e.getMessage());
            image = null;
        }
    }

    //malen
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }
    }

    //set und getter
    public void setY(int y) {
        this.y = y;
        }
        public void setX(int x) {this.x = x;}
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeaponType( ) {return this.weaponType;}
}