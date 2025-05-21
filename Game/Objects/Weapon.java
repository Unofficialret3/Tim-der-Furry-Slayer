package Game.Objects;

import Game.Sound.SoundPlayer;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.awt.Graphics;
import java.awt.Color;
import Game.Panels.GamePanel;

//Game.Objects.Weapon Types: 0 = Blaster, 1 = Grenade


public class Weapon {
    // variablen für weapon
    int damage;
    int width;
    int height;
    int xOffset;
    int xBulletOffset;
    int bulletWidth;
    int bulletHeight;
    int bulletHealth;
    int weaponType;
    double fireRate;//in pro sekunde
    // variable nfür methoden
    long timeOld = 0;
    BufferedImage texture;
    String texturePath;
    String soundPath;
    String bulletTexturePath;

    public Weapon (int width, int height, int xOffset, int xBulltetOffset, int weaponType, int bulletWidth, int bulletHeight, int bulletHealth, int damage, double fireRate, String texturePath, String soundPath, String bulltetTexture) {
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
        this.weaponType = weaponType;
        this.fireRate=fireRate;

        try {

            texture = ImageIO.read(Objects.requireNonNull(getClass().getResource(texturePath)));
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

        if(System.currentTimeMillis()>=timeOld + 1000/fireRate ){

            Bullet bullet = new Bullet(x+xBulletOffset, y,bulletWidth, bulletHeight, bulletTexturePath, bulletHealth, weaponType);
            GamePanel.bullets.add(bullet);
            try {
                sounds.loadSound(soundPath);
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
            sounds.play();
            timeOld = System.currentTimeMillis();
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBulletHealth() {
        return bulletHealth;
    }

    public void setBulletHealth(int bulletHealth) {
        this.bulletHealth = bulletHealth;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }
}


