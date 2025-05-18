import javax.swing.*;
import java.util.Iterator;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;

    //liste für die schüsse
    protected static ArrayList<Bullet> bullets;
    protected ArrayList<Enemy> enemies;

    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private boolean isSpacePressed = false;
    


    // spieler createn
    protected  static Player player1 = new Player((SpaceInvaders.sizeX/2)- 50, SpaceInvaders.sizeY-150); // Mitte unten

    // test enemey
    protected Enemy enemy1 = new Enemy((SpaceInvaders.sizeX/2)- 50, SpaceInvaders.sizeY-650 ); // mitte oben



    public GamePanel() {
        
        setFocusable(true);
        addKeyListener(this);

        //listew für bullets initaliesieren
        bullets = new ArrayList<>();
        // liste für enemys
        enemies = new ArrayList<>();

        enemies.add(enemy1);



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
        // enemies malen
        for(Enemy enemy : enemies){
            enemy.draw(g);
        }
        
        // TODO:  und Objekte zeichnen
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Spiel-Logik updaten



        //bewegungen
            if (isLeftPressed) {
                player1.moveLeft();
            }
            if (isRightPressed) {
                player1.moveRight();
            }

            // Schießen 
            if (isSpacePressed) {
                player1.shoot();
            }


        // schüsse updaten
        if (!bullets.isEmpty()) {
            // iteraor  nicht schleife damit  sachen  sicher gelöscht werden
            Iterator<Bullet> itB = bullets.iterator();
            while (itB.hasNext()) {
                Bullet b = itB.next();
                // Position aktualisieren
                b.setY(b.getY() - 10); // 10 nach oben
                // Wenn Bullet aus dem Panel raus ist dann löschem
                if (b.getY() <= 0) { 
                    itB.remove(); // korrekt: entfernt über den Iterator
                }

                // iterator durch jden enemy
                Iterator<Enemy> itE = enemies.iterator();
                while(itE.hasNext()){
                    Enemy enemy = itE.next();
                    if(enemy.isCollidingWithBullet(b)){
                        itB.remove();
                        itE.remove();
                    }

                }
                

            }
}



        repaint();
    }




    // Tasteneingaben
    @Override public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            //key logik
            if (key == KeyEvent.VK_LEFT|| key == KeyEvent.VK_A ) isLeftPressed= true;   // mit booleans damit parralele eingaben möglich sind
            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) isRightPressed = true;
            if (key == KeyEvent.VK_SPACE ) isSpacePressed= true ;
    }
    @Override public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT|| key == KeyEvent.VK_A ) isLeftPressed= false;   
            if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) isRightPressed = false;
            if (key == KeyEvent.VK_SPACE ) isSpacePressed= false ;

    }
    @Override public void keyTyped(KeyEvent e) {}
}