package Game.Panels;

import Game.Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
//diese klasse ist nicht mkir ich verstehe go nix
public class HomeScreen extends JPanel {

    private final Image background;
    private final CustomButton startButton;
    private final CustomButton optionsButton;

     public HomeScreen() {
        setFocusable(true);

        setLayout(null); // manuelle Platzierung

        // Button initialisieren
        startButton = new CustomButton("Spiel starten");
        optionsButton = new CustomButton("Optionen");
        background = new ImageIcon("ressources/textures/TitleScreen.png").getImage();

        // Größe setzen und zentrieren
        int btnWidth = 300;
        int btnHeight = 60;
        int centerX = (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeX - btnWidth) / 2;

        startButton.setBounds(centerX, Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY- Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY/4, btnWidth, btnHeight);
        optionsButton.setBounds(centerX, Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY- Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY/4 + 80, btnWidth, btnHeight);

        // Aktionen bei Klick
        startButton.setOnClick(() -> {
            System.out.println("Spiel wird gestartet...");
            
            Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.GamePanelActivate();
        });

        optionsButton.setOnClick(() -> {
            System.out.println("Optionen geöffnet...");
            // TODO: Optionsmenü anzeigen
        });

        // Buttons hinzufügen
        add(startButton);
        add(optionsButton);
    }


    @Override
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
