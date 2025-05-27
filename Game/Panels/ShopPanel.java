package Game.Panels;

import Game.Objects.Player;
import Game.Shop.ShopManager;
import Game.Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    private final Image background;
    private Player player;

    private final CustomButton UpgradeMainWeaponButtonFireRate;
    private final CustomButton UpgradeMainWeaponButtonBulletHealth;
    private final CustomButton UpgradeMainWeaponButtonDamage;
    private ShopManager shopManager;
    private final CustomButton BackButton;

    public ShopPanel(ShopManager shopManager, Player player , PausePanel pausePanel){
        this.player=player;
        this.setFocusable(true);
        this.shopManager = shopManager;
        setLayout(null);
      //  background = new ImageIcon("");
        background= null;
        //buttons
        int btnWidth = 450;
        int btnHeight = 300;
        int centerX = (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeX - btnWidth) / 2;


        //upgrade button main
        if(shopManager.items[0].getFirerateLvl()<=8) {
            UpgradeMainWeaponButtonFireRate = new CustomButton("Upgrade  Firerate:" + shopManager.items[0].getPrice());
        }
        else {
            UpgradeMainWeaponButtonFireRate = new CustomButton("Max LEVEL");
        }
        if(shopManager.items[1].getDamageLvl()<=5) {
            UpgradeMainWeaponButtonDamage = new CustomButton("Upgrade  Damage: " + shopManager.items[1].getPrice());
        }
        else {
            UpgradeMainWeaponButtonDamage = new CustomButton("Max LEVEL");
        }
        if(shopManager.items[2].getBulletHealthLvl()<=5) {
            UpgradeMainWeaponButtonBulletHealth = new CustomButton("Upgrade  BulletHealth:" + shopManager.items[2].getPrice());
        }
        else {
            UpgradeMainWeaponButtonBulletHealth = new CustomButton("Max LEVEL");
        }


        UpgradeMainWeaponButtonFireRate.setBounds(centerX, Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY- (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY/2+150), btnWidth, btnHeight);
        UpgradeMainWeaponButtonDamage.setBounds(centerX-(btnWidth+10), Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY- (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY/2+150), btnWidth, btnHeight);
        UpgradeMainWeaponButtonBulletHealth.setBounds(centerX+(btnWidth+10), Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY- (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY/2+150), btnWidth, btnHeight);

        BackButton = new CustomButton("BACK");
        BackButton.setBounds(centerX, Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY- (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY/4), btnWidth, btnHeight-150);



        BackButton.setOnClick(() -> {
            System.out.println("firerate: "+ pausePanel.gamePanel.getPlayer().getMainWeapon().getFireRate());
            Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.PausePanelActivate(pausePanel);
            repaint();
        });
        if(PlayerHasEnoughMoney(0)) {
            UpgradeMainWeaponButtonFireRate.setOnClick(() -> {

                if (shopManager.items[0].getFirerateLvl() <= 8) {
                    System.out.println("Krasse waffe .xml...");
                    shopManager.setUpgradingMainWeapon(true);
                    shopManager.buyItem(0, pausePanel.gamePanel.getPlayer());
                    UpgradeMainWeaponButtonFireRate.setLabel("Upgrade Main weapon for: " + shopManager.items[0].getPrice());
                } else {
                    UpgradeMainWeaponButtonFireRate.setLabel("Max LEVEL");
                }
                repaint();
            });
        }
        if(PlayerHasEnoughMoney(1)){
            UpgradeMainWeaponButtonDamage.setOnClick(() -> {

                    if(shopManager.items[1].getDamageLvl()<=5) {
                        System.out.println("Krasse waffe .xml...");
                        shopManager.setUpgradingMainWeapon(true);
                        shopManager.buyItem(1, pausePanel.gamePanel.getPlayer());
                        UpgradeMainWeaponButtonDamage.setLabel("Upgrade Main weapon for: " + shopManager.items[1].getPrice());
                    }
                    else{
                        UpgradeMainWeaponButtonDamage.setLabel("Max LEVEL");
                    }
                    repaint();

            });
        }

        UpgradeMainWeaponButtonBulletHealth.setOnClick(() -> {

            if(shopManager.items[2].getBulletHealthLvl()<=5&&PlayerHasEnoughMoney(1)) {
                System.out.println("Krasse waffe .xml...");
                shopManager.setUpgradingMainWeapon(true);
                shopManager.buyItem(2, pausePanel.gamePanel.getPlayer());
                UpgradeMainWeaponButtonBulletHealth.setLabel("Upgrade Main weapon for: " + shopManager.items[2].getPrice());
            }
            else if(shopManager.items[2].getBulletHealthLvl() >= 5){
            UpgradeMainWeaponButtonBulletHealth.setLabel("Max LEVEL");
            }
            else {
                UpgradeMainWeaponButtonBulletHealth.setLabel("Upgrade Main weapon for: " + shopManager.items[2].getPrice());
            }
            repaint();

        });





        add(BackButton);
        add(UpgradeMainWeaponButtonFireRate);
        add(UpgradeMainWeaponButtonDamage);
        add(UpgradeMainWeaponButtonBulletHealth);


    }

    private boolean PlayerHasEnoughMoney(int index) {
        return shopManager.items[index].getPrice() <= player.getMoney();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        if(background!= null){
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
        else{
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        drawMoney(g);


    }

    private void drawMoney(Graphics g) {
        g.setColor(Color.WHITE); //
        g.setFont(new Font("SansSerif", Font.BOLD, 20)); // Schriftart
        g.drawString("Money: " + player.getMoney(), (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeX/2)-30, 20); // rechts oben
    }
}
