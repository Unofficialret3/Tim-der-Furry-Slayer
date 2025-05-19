import javax.swing.*;
import java.util.Iterator;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    long oldMillis= 0;

    //liste für die schüsse
    protected static ArrayList<Bullet> bullets;
    protected ArrayList<Enemy> enemies;

    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private boolean isSpacePressed = false;
    


    // spieler createn
    protected  static Player player1 = new Player((SpaceInvaders.sizeX/2)- 50, SpaceInvaders.sizeY-150); // Mitte unten

    // test enemey
    protected Enemy enemy1 = new Enemy((SpaceInvaders.sizeX/2)- 50, SpaceInvaders.sizeY-750 ); // mitte oben



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


            if(enemy.getY()<= SpaceInvaders.sizeY){
                //move runter y achse
                enemy.moveDown();

                long newMilis = System.currentTimeMillis();
                

                if((newMilis-oldMillis)>500){
                enemy.moveRandomLR();
                oldMillis = newMilis;
                }
            }
            else{
                
            }


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
               Iterator<Bullet> itB = bullets.iterator();

                while (itB.hasNext()) {

                    Bullet b = itB.next();
                    b.setY(b.getY() - 10);

                    if (b.getY() <= 0) {
                        itB.remove();
                    }
                }

                // Enemies löschen wenn außerhalb
                Iterator<Enemy> itE = enemies.iterator();
                while (itE.hasNext()) {

                    Enemy enemy = itE.next();

                    if (enemy.getY() >= SpaceInvaders.sizeY) {
                        itE.remove();
                    }

                }

                // Kollisionen prüfen 
                itB = bullets.iterator();// nochmal weil der von oben schon leer ist
                while (itB.hasNext()) {

                    Bullet b = itB.next();
                    itE = enemies.iterator();

                    while (itE.hasNext()) {

                        Enemy enemy = itE.next();

                        if (enemy.isCollidingWithBullet(b)) {
                            itB.remove();
                            itE.remove();
                            break; 
                            }
                    }
                }
        // tests
   /*  try{
    System.out.println(enemies.get(0).getY());
    }
    catch (Exception e2){
        System.out.println("keine enemies");
        }
        */
        repaint();
    }



    //methoden
    private void spawnEnemies() {

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