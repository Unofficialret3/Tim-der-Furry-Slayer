import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;

    // spieler createn
    private Player player = new Player(375, 500); // Mitte unten



    public GamePanel() {
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
        // TODO:  und Objekte zeichnen
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Spiel-Logik updaten
        repaint();
    }




    // Tasteneingaben
    @Override public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            //links recht logik
            if (key == KeyEvent.VK_LEFT|| key == KeyEvent.VK_A ) player.moveLeft();
            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) player.moveRight();
    }
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}