package Game.Objects;

import Game.Animation.AnimationManager;
import Game.Panels.GamePanel;
import Game.Sound.SoundPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Ability extends Weapon {


    private double cooldown;
    private long lastUse;
    private String idleTexture;
    private AnimationManager loadBarAnimation;

    public Ability(int xOffset, int xBulletOffset, int weaponType, int bulletWidth, int bulletHeight, int bulletHealth, int damage, double fireRate, String soundPath, String bulletTexturePath, AnimationManager animationManager,String[] abilitieLoadBar) {

       // parent
        super(xOffset, xBulletOffset, weaponType, bulletWidth, bulletHeight, bulletHealth, damage, fireRate, soundPath, bulletTexturePath, animationManager);
        this.lastUse=0;
        this.cooldown = 1000 / fireRate;
        this.idleTexture=abilitieLoadBar[4];
        this.loadBarAnimation = new AnimationManager(75,20,5,(int) Math.round(cooldown/5)+50,abilitieLoadBar,idleTexture);
    }
        @Override
        public void shootWeapon(int x, int y, SoundPlayer sounds) {


            if(isReady()){

                Bullet bullet = new Bullet(x+xBulletOffset, y,bulletWidth, bulletHeight, bulletTexturePath, bulletHealth, weaponType);
                GamePanel.bullets.add(bullet);
                try {
                    sounds.loadSound(soundPath);
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
                sounds.play();
                int p=1;
                if(loadBarAnimation!=null){
                    loadBarAnimation.startAnimation(0,5,false);
                }

                if (animationManager != null) {
                    animationManager.startAnimation(0, animationManager.textures.length, false);
                }

                timeOld = System.currentTimeMillis();
            }
        }


    public boolean isReady(){
        return System.currentTimeMillis()>=timeOld + cooldown;
    }

    public AnimationManager getLoadBarAnimation() {
        return loadBarAnimation;
    }

    public void setLoadBarAnimation(AnimationManager loadBarAnimation) {
        this.loadBarAnimation = loadBarAnimation;
    }
}
