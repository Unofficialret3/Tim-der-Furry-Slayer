import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;
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
    SoundPlayer sounds = new SoundPlayer();


    // spieler createn
    protected  static Player player1 = new Player((SpaceInvaders.sizeX/2)- 50, SpaceInvaders.sizeY-150); // Mitte unten

    // test enemey
    //protected Enemy enemy1 = new Enemy((SpaceInvaders.sizeX/2)- 50, SpaceInvaders.sizeY-750 ); // mitte oben



    public GamePanel() {

        setFocusable(true);
        addKeyListener(this);

        //listew für bullets initaliesieren
        bullets = new ArrayList<>();
        // liste für enemys
        enemies = new ArrayList<>();

        spawnEnemies(spawnPattern1);



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
                            sounds.playEnemyDeath();
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

    protected int[][] spawnPattern1 = {
     { 1,0,0}, // linke spalte
     { 1, 1,1 } , // mitte
     { 1,0,0 }// rechte spalte
    };
     protected int[][] spawnPattern2 = {
     { 0,1,0}, // linke spalte
     { 1, 0,1 } , // mitte
     { 0,1,0 }// rechte spalte
    };
     protected int[][] spawnPattern3 = {
     { 1,1,1}, // linke spalte
     { 1, 1,1 } , // mitte
     { 1,1,1 }// rechte spalte
    };

    //methodend
    private void spawnEnemies(int[][] pattern) {
        
        
        int mid = pattern.length/ 2;


        // enemys spawnen
        for(int i= 0;i<=pattern.length-1;i++){
            for(int j = 0;j<=pattern.length-1;j++){

                if(pattern[i][j] == 1){
                    if(i==mid){
                        Enemy enemy = new Enemy(SpaceInvaders.sizeX/2-25, SpaceInvaders.sizeY-750 +j *100);
                        enemies.add(enemy);
                    }
                    else if(i<mid){
                        
                         Enemy enemy = new Enemy((SpaceInvaders.sizeX/2-25)-((1+i)*100), SpaceInvaders.sizeY-750+j*100);
                            enemies.add(enemy);
                    }
                    else if(i>mid){
                        Enemy enemy = new Enemy((SpaceInvaders.sizeX/2-25)+((i-1)*100),SpaceInvaders.sizeY-750+j*100);
                        enemies.add(enemy);
                    }
                    else{
                        System.out.println("heyho");
                    }
                }
            }
        }


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