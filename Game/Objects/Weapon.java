package Game.Objects;

import Game.Animation.AnimationManager;
import Game.Animation.AnimationManager;
import Game.Sound.SoundPlayer;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

import Game.Panels.GamePanel;

//Game.Objects.Weapon Types: 0 = Blaster, 1 = Grenade


public class Weapon {
    // variablen für weapon
    int damage;
    int xOffset;
    int xBulletOffset;
    int bulletWidth;
    int bulletHeight;
    int bulletHealth;
    int weaponType;
    double fireRate;//in pro sekunde
    // variable nfür methoden
    long timeOld = 0;
    String soundPath;
    String bulletTexturePath;
    AnimationManager animationManager;

    public Weapon (int xOffset, int xBulltetOffset, int weaponType, int bulletWidth, int bulletHeight, int bulletHealth, int damage, double fireRate, String soundPath, String bulltetTexture, AnimationManager animationManager) {
        this.xOffset = xOffset;
        this.xBulletOffset = xBulltetOffset;
        this.damage = damage;
        this.bulletWidth = bulletWidth;
        this.bulletHeight = bulletHeight;
        this.soundPath = soundPath;
        this.bulletTexturePath = bulltetTexture;
        this.bulletHealth = bulletHealth;
        this.weaponType = weaponType;
        this.fireRate=fireRate;
        this.animationManager = animationManager;
    }
    //copy von waffen
    public Weapon(Weapon original) {
        this(
                original.xOffset,
                original.xBulletOffset,
                original.weaponType,
                original.bulletWidth,
                original.bulletHeight,
                original.bulletHealth,
                original.damage,
                original.fireRate,
                original.soundPath,
                original.bulletTexturePath,
                original.animationManager
        );
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

            if (animationManager != null) {
                animationManager.startAnimation(0, animationManager.textures.length, false);
            }

            timeOld = System.currentTimeMillis();
        }
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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

    public AnimationManager getAnimationManager() {
        return animationManager;
    }

    public int getXOffset() {
        return xOffset;
    }
}


