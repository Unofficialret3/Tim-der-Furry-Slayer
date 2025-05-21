package Game.Panels;

import Game.Shop.ShopManager;
import Game.Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20;

import javax.swing.*;
import java.awt.*;

public class ShopPanel extends JPanel {
    private final Image background;

    private final CustomButton UpgradeMainWeaponButton;
    private final CustomButton BackButton;

    public ShopPanel(ShopManager shopManager,PausePanel pausePanel){

        this.setFocusable(true);

        setLayout(null);
      //  background = new ImageIcon("");
        background= null;
        //buttons
        int btnWidth = 450;
        int btnHeight = 300;
        int centerX = (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeX - btnWidth) / 2;

        if(shopManager.items[0].getLvl()<=8) {
            UpgradeMainWeaponButton = new CustomButton("Upgrade Main weapon for :" + shopManager.items[0].getPrice());
        }
        else {
            UpgradeMainWeaponButton = new CustomButton("Max LEVEL");
        }
        UpgradeMainWeaponButton.setBounds(centerX-450, Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY- (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY/2+150), btnWidth, btnHeight);

        BackButton = new CustomButton("BACK");
        BackButton.setBounds(centerX, Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY- (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY/4), btnWidth, btnHeight-150);



        BackButton.setOnClick(() -> {
            System.out.println("firerate: "+ pausePanel.gamePanel.getPlayer().getMainWeapon().getFireRate());
            Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.PausePanelActivate(pausePanel);

        });
        UpgradeMainWeaponButton.setOnClick(() -> {

            if(shopManager.items[0].getLvl()<=8) {
                System.out.println("Krasse waffe .xml...");
                shopManager.setUpgradingMainWeapon(true);
                shopManager.buyItem(0, pausePanel.gamePanel.getPlayer());
                UpgradeMainWeaponButton.setLabel("Upgrade Main weapon for: " + shopManager.items[0].getPrice());
            }
            else{
                UpgradeMainWeaponButton.setLabel("Max LEVEL");
            }
        });
        add(BackButton);
        add(UpgradeMainWeaponButton);

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

    }
}
