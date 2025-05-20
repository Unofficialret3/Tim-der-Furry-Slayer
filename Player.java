//imports
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.IOException;
import java.util.Objects;


public class Player {
    SoundPlayer player = new SoundPlayer();

    protected int x, y, width = 100, height = 100;
    protected int speed = 15;

    private BufferedImage texture;  // Bild für den Spieler
    Weapon mainWeapon;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        try {
            // Bild aus Ressourcen laden
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResource("textures/Player.png"))); // <-- Stelle sicher, dass das Bild im Klassenpfad liegt
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Konnte Bild nicht laden: " + e.getMessage());
            texture = null;
        }
         mainWeapon = new Weapon(50, 50, 70, 85, 10, 10, 10,"textures/Slingshot.png", "sounds/throw.wav");
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void shootMainWeapon() {
        mainWeapon.shootWeapon(x, y, player);
    }

    // Spieler malen
    public void draw(Graphics g) {
        if (texture != null) {
            g.drawImage(texture, x, y, width, height, null);
            mainWeapon.drawWeapon(g, x, y);
        } else {
            // Fallback: grünes Rechteck
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
        }
    }
}
