import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

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

        // Größe setzen
        startButton.setBounds(400, 300, 200, 50);
        optionsButton.setBounds(400, 370, 200, 50);

        // Aktionen bei Klick
        startButton.setOnClick(() -> {
            System.out.println("Spiel wird gestartet...");
            // TODO: GamePanel starten
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
