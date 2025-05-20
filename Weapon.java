import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.awt.Graphics;
import java.awt.Color;

public class Weapon {
    int damage;
    int width;
    int height;
    int xOffset;
    int xBulletOffset;
    int bulletWidth;
    int bulletHeight;
    int bulletHealth;

    BufferedImage texture;
    String texturePath;
    String soundPath;
    String bulletTexturePath;

    public Weapon (int width, int height, int xOffset, int xBulltetOffset, int bulletWidth, int bulletHeight, int bulletHealth, int damage, String texturePath, String soundPath, String bulltetTexture) {
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.xBulletOffset = xBulltetOffset;
        this.damage = damage;
        this.bulletWidth = bulletWidth;
        this.bulletHeight = bulletHeight;
        this.texturePath = texturePath;
        this.soundPath = soundPath;
        this.bulletTexturePath = bulltetTexture;
        this.bulletHealth = bulletHealth;

        try {
            // Bild aus Ressourcen laden
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResource(texturePath))); // <-- Stelle sicher, dass das Bild im Klassenpfad liegt
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Konnte Bild nicht laden: " + e.getMessage());
        }
    }

    public void drawWeapon(Graphics g, int x, int y) {
        if (texture != null) {
            g.drawImage(texture, x+xOffset, y, width, height, null);
        } else {
            // Fallback: grünes Rechteck
            g.setColor(Color.PINK);
            g.fillRect(x, y, width, height);
        }
    }

    public void shootWeapon(int x, int y, SoundPlayer sounds) {
        Bullet bullet = new Bullet(x+xBulletOffset, y,bulletWidth, bulletHeight, bulletTexturePath, bulletHealth);
        GamePanel.bullets.add(bullet);
        try {
            sounds.loadSound(soundPath);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        sounds.play();
    }

}
