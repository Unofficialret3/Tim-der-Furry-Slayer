import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Weapon {
    int damage;
    int range;
    int speed;
    int width;
    int height;

    BufferedImage texture;
    String texturePath;
    String soundPath;

    public Weapon (int width, int height, int damage, int range, int speed, String texturePath, String soundPath) {
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.range = range;
        this.speed = speed;
        this.texturePath = texturePath;
        this.soundPath = soundPath;

        try {
            // Bild aus Ressourcen laden
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResource(texturePath))); // <-- Stelle sicher, dass das Bild im Klassenpfad liegt
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Konnte Bild nicht laden: " + e.getMessage());
        }
    }

    public void drawWeapon(Graphics g, int x, int y) {
        if (texture != null) {
            g.drawImage(texture, x+70, y, width, height, null);
        } else {
            // Fallback: grünes Rechteck
            g.setColor(Color.PINK);
            g.fillRect(x, y, width, height);
        }
    }


}
