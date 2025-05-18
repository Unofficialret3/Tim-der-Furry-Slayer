import javax.swing.*;
import java.util.Iterator;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;

    //liste für die schüsse
    protected static ArrayList<Bullet> bullets;
    


    // spieler createn
    protected  static Player player1 = new Player((SpaceInvaders.sizeX/2)- 50, SpaceInvaders.sizeY-150); // Mitte unten



    public GamePanel() {
        
        
        
        setFocusable(true);
        
        addKeyListener(this);

        //listew für bullets initaliesieren
        bullets = new ArrayList<>();

        //tbh kein plan was der macht
        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        //bullets zeichnen
        for (Bullet bullet : bullets) {
            bullet.draw(g);
            }
        
        // spieler zeichnen
        player1.draw(g);

        
        // TODO:  und Objekte zeichnen
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Spiel-Logik updaten


        // schüsse updaten
        if (!bullets.isEmpty()) {
            // iteraor  nicht schleife damit  sachen  sicher gelöscht werden
            Iterator<Bullet> it = bullets.iterator();
            while (it.hasNext()) {
                Bullet b = it.next();

                // Position aktualisieren
                b.setY(b.getY() - 10); // 10 nach oben

                // Wenn Bullet aus dem Panel raus ist dann löschem
                if (b.getY() <= 0) {
                    it.remove(); // korrekt: entfernt über den Iterator
                }
    }
}



        repaint();
    }




    // Tasteneingaben
    @Override public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            //links recht logik
            if (key == KeyEvent.VK_LEFT|| key == KeyEvent.VK_A ) player1.moveLeft();
            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) player1.moveRight();
            if (key == KeyEvent.VK_SPACE ) player1.shoot();
    }
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}