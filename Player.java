//imports
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.IOException;
import java.util.Objects;


public class Player {
    SoundPlayer player = new SoundPlayer();
    long oldMillis= 0;
    protected int x, y, width = 100, height = 100;
    protected int speed = 15;
    private BufferedImage image;  // Bild für den Spieler

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        try {
            // Bild aus Ressourcen laden
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/Player.png"))); // <-- Stelle sicher, dass das Bild im Klassenpfad liegt
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Konnte Bild nicht laden: " + e.getMessage());
            image = null;
        }
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void shoot() {


        long newMilis = System.currentTimeMillis();
                
                if((newMilis-oldMillis)>500){
                    
                    Bullet bullet = new Bullet(x, y);
                    GamePanel.bullets.add(bullet);
                    player.playLaser();
                    oldMillis = newMilis;
                }


                
   }



    // Spieler malen
    public void draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        } else {
            // Fallback: grünes Rechteck
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
        }
    }

    // Getter
    public int getWidth() {
        return width;
    }
}
