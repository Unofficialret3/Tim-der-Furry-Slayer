package Game.Objects;//imports
import Game.Animation.AnimationManager;
import Game.Sound.SoundPlayer;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.IOException;
import java.util.Objects;


public class Player {
    public SoundPlayer player = new SoundPlayer();

    public int money = 0;

    protected int x, y, width = 100, height = 100;
    protected int speed = 15;

    private BufferedImage texture;
    Weapon mainWeapon;
    String mainWeaponIdleTexturePath = "/textures/weapons/gun_un_fired.png";
    Weapon specialQWeapon;
    String[] mainWeaponTexturePaths = new String[] {"/textures/weapons/gun_fired_1.png", "/textures/weapons/gun_fired_2.png", "/textures/weapons/gun_fired_3.png"};
    String[] specialQWeaponTexturePaths = new String[] {"/textures/weapons/Slingshot.png"};

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        try {
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResource("/textures/Player.png")));

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Konnte Bild nicht laden: " + e.getMessage());
            texture = null;
        }
         mainWeapon = new Weapon(70,  85,0, 10, 10, 1, 1, 8,"ressources/sounds/throw.wav", "/textures/StonePebble.png", new AnimationManager(50, 100, 3, 10, mainWeaponTexturePaths, mainWeaponIdleTexturePath));
         mainWeapon.getAnimationManager().startAnimation(1, 1, true); // Startanimation beim Spielstart

        specialQWeapon = new Weapon(200,  0,1, 200, 200, 10, 10,0.5,"ressources/sounds/throw.wav", "/textures/StonePebble.png", new AnimationManager(200, 200, 1, 1, specialQWeaponTexturePaths, specialQWeaponTexturePaths[0]));
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

    public void shootSpecialQWeapon(){
        specialQWeapon.shootWeapon(x, y, player);

    }

    public void draw(Graphics g) {
        if (texture != null) {
            g.drawImage(texture, x, y, width, height, null);
            //mainWeapon.drawWeapon(x, y, g);
            mainWeapon.getAnimationManager().draw(g, x + mainWeapon.getXOffset(), y);
        } else {
            // Fallback: grünes Rechteck
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
        }
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public int getMoney() {return money;}

    public Weapon getMainWeapon() {return mainWeapon;}

    public void setMainWeapon( Weapon mainWeapon) {
        this.mainWeapon=mainWeapon;
    }
}
