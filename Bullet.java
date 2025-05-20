import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Bullet {

    private int width = 20;
    private int height = 20;
    private int x, y;
    //private int speedY;
    //private int damage;
    private BufferedImage image;
    public Bullet (int x,int y){
        
        this.x = x + GamePanel.player1.getWidth()/2 ; // damit es aus der mitte des players kommt
        this.y = y;

        try {
            // Bild aus Ressourcen laden
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("textures/StonePebble.png"))); // <-- Stelle sicher, dass das Bild im Klassenpfad liegt
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