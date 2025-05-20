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

        // Hintergrund
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // EXTREME Überschrift
        String title = "TIM THE FURY SLAYER Very HD 69FPS!!! EXTREME 2 Open BETA ALPHA V4.20";
        g.setFont(new Font("Impact", Font.BOLD, 32));
        g.setColor(Color.RED);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(title);
        g.drawString(title, (getWidth() - textWidth) / 2, 120);
    }

    
}
