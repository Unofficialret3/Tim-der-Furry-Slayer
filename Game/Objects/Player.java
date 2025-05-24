package Game.Objects;//imports
import Game.Animation.AnimationManager;
import Game.Sound.SoundPlayer;
import Game.Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player {
    public SoundPlayer player = new SoundPlayer();

    public int money = 10000;

    protected int x, y, width = 100, height = 100;
    protected int speed = 15;

    private BufferedImage texture;
    private BufferedImage qAbilityTexture;
    Weapon mainWeapon;
    String mainWeaponIdleTexturePath = "/textures/weapons/gun_un_fired.png";
    Ability specialQAbilitie;

    String[] mainWeaponTexturePaths = new String[] {"/textures/weapons/gun_fired_1.png", "/textures/weapons/gun_fired_2.png", "/textures/weapons/gun_fired_3.png"};
    String[] specialQWeaponTexturePaths = new String[] {"/textures/weapons/Slingshot.png"};
    String getSpecialQAbilityUI = "/textures/Abilities/SpecialQWeaponAbility.png";
    String[] getSpecialQAbilitieLoadBarTexturePaths = new String[]  {"/textures/Abilities/qBar1.png", "/textures/Abilities/qBar2.png", "/textures/Abilities/qBar3.png","/textures/Abilities/qBar4.png","/textures/Abilities/qBar5.png"};
    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        try {
            texture = ImageIO.read(Objects.requireNonNull(getClass().getResource("/textures/Player.png")));
            qAbilityTexture= ImageIO.read(Objects.requireNonNull(getClass().getResource(getSpecialQAbilityUI)));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Konnte Bild nicht laden: " + e.getMessage());
            texture = null;
            qAbilityTexture=null;
        }
         mainWeapon = new Weapon(70,  85,0, 10, 10, 1, 1, 4,"ressources/sounds/throw.wav", "/textures/stonePebble.png", new AnimationManager(50, 100, 3, 10, mainWeaponTexturePaths, mainWeaponIdleTexturePath));
         // Startanimation beim Spielstart

        specialQAbilitie = new Ability(200,  0,1, 200, 200, 10, 10,0.5,"ressources/sounds/throw.wav", "/textures/StonePebble.png", new AnimationManager(200, 200, 1, 1, specialQWeaponTexturePaths, specialQWeaponTexturePaths[0]),getSpecialQAbilitieLoadBarTexturePaths);

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

    public void shootSpecialQWeapon(){specialQAbilitie.shootWeapon(x, y, player);}

    public void draw(Graphics g) {
        if (texture != null) {
            g.drawImage(texture, x, y, width, height, null);
            //mainWeapon.drawWeapon(x, y, g);
            mainWeapon.getAnimationManager().draw(g, x + mainWeapon.getXOffset(), y);

            if(qAbilityTexture!=null){
                // drawAbilityQ

                drawAbilityIcon(g,qAbilityTexture, specialQAbilitie.isReady(),"Q","LOAD",0,Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY-120,75);
                specialQAbilitie.getLoadBarAnimation().draw(g,0,Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY-140);
            }

        } else {
            // Fallback: grünes Rechteck
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
        }
    }

    private void drawAbilityIcon(Graphics g, Image icon, boolean isReady, String buttonLabel, String cooldownLabel, int x, int y, int size) {

        g.drawImage(icon, x, y, size, size, null);
        String label = isReady ? buttonLabel : cooldownLabel; // je nach boolean
        Graphics2D g2d = (Graphics2D) g.create();
        if (isReady) {
            g2d.setColor(Color.WHITE);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // volle Deckkraft
        } else {
            g2d.setColor(Color.GRAY);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // halbtransparent
        }

        g2d.setFont(new Font("Arial Black", Font.BOLD, 18));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(label);
        int textHeight = fm.getAscent();

        int textX = x + (size - textWidth) / 2;
        int textY = y + (size + textHeight) / 2 - 5;
        g2d.drawString(label, textX, textY);
        g2d.dispose(); // angeblich wichtig aber kein plan why tbh
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public int getMoney() {return money;}

    public Weapon getMainWeapon() {return mainWeapon;}

    public void setMainWeapon( Weapon mainWeapon) {
        this.mainWeapon=mainWeapon;
    }

    public Ability getSpecialQAbilitie() {return specialQAbilitie;}
}
