package Game.Panels;

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
        int centerX = (SpaceInvaders.sizeX - btnWidth) / 2;

        startButton.setBounds(centerX, SpaceInvaders.sizeY- SpaceInvaders.sizeY/4, btnWidth, btnHeight);
        optionsButton.setBounds(centerX, SpaceInvaders.sizeY- SpaceInvaders.sizeY/4 + 80, btnWidth, btnHeight);

        // Aktionen bei Klick
        startButton.setOnClick(() -> {
            System.out.println("Spiel wird gestartet...");
            
            SpaceInvaders.GamePanelActivate();
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
