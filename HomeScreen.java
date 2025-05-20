import java.awt.*;


import javax.swing.JPanel;
//diese klasse ist nicht mkir ich verstehe go nix
public class HomeScreen extends JPanel {


    private CustomButton startButton;
    private CustomButton optionsButton;

     public HomeScreen() {
        setFocusable(true);

        setLayout(null); // manuelle Platzierung

        // Button initialisieren
        startButton = new CustomButton("Spiel starten");
        optionsButton = new CustomButton("Optionen");

        // Größe setzen und zentrieren
        int btnWidth = 300;
        int btnHeight = 60;
        int centerX = (SpaceInvaders.sizeX - btnWidth) / 2;

        startButton.setBounds(centerX, 300, btnWidth, btnHeight);
        optionsButton.setBounds(centerX, 380, btnWidth, btnHeight);

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

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        

    }

    
}
