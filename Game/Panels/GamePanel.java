package Game.Panels;

import Game.Enemys.WaveManager;
import Game.Objects.Bullet;
import Game.Enemys.Enemy;
import Game.Objects.Player;
import Game.Shop.ShopManager;
import Game.Sound.SoundPlayer;
import Game.Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20;

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
    private long score;

    private Player player1;
    //liste für die schüsse
    public  ArrayList<Bullet> bullets;
    protected ArrayList<Enemy> enemies;

    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private boolean isSpacePressed = false;
    private boolean isQPressed = false;
    private boolean isESCPressed = false;
    private boolean paused = false;
    SoundPlayer sounds = new SoundPlayer();
    protected  ShopManager shopManager;
    private WaveManager waveManager;


    // test enemey
    //protected Game.Enemys.Enemy enemy1 = new Game.Enemys.Enemy((Game.SpaceInvaders.sizeX/2)- 50, Game.SpaceInvaders.sizeY-750 ); // mitte oben



    public GamePanel(Player player) {
        this.player1=player;
        requestFocusInWindow();
        setFocusable(true);
        addKeyListener(this);
        shopManager =new ShopManager(player);
        waveManager= new WaveManager();

        //listew für bullets initaliesieren
        bullets = new ArrayList<>();
        // liste für enemys
        enemies = new ArrayList<>();
        // spawn enemies

        WaveManager.sendWave(enemies);



        //tick system für action listner
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
        player1.getMainWeapon().getAnimationManager().update();
        if(!player1.getSpecialQAbilitie().isReady()){
            player1.getSpecialQAbilitie().getLoadBarAnimation().update();
        }

        // enemies malen
        for(Enemy enemy : enemies){
            if(enemy.getY()<= Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY){
                //move runter y achse
                enemy.moveDown();
                enemy.moveRandomLR();
            }

        enemy.draw(g);
        drawScore(g);
        drawMoney(g);
        }
        
        // TODO:  und Objekte zeichnen
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Spiel-Logik updaten
        if(isESCPressed) {
            //sonst stuck
            isESCPressed=false;
            if (!paused) {
                paused = true;
                try {
                    player1.player.loadSound("ressources/sounds/pauseSound.wav");
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
                player1.player.play();
                Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.PausePanelActivate(new PausePanel(player1, this));


            }
        }
        //enemy spawn logik
        if(enemies.isEmpty()){
            bullets.clear();
            WaveManager.sendWave(enemies);
        }

        //bewegungen
            if (isLeftPressed) {
                player1.moveLeft();
            }
            if (isRightPressed) {
                player1.moveRight();
            }

            // Schießen 
            if (isSpacePressed) {
                player1.shootMainWeapon(this);
            }

            if(isQPressed){
                player1.shootSpecialQWeapon(this);
            }

            // schüsse updaten
               Iterator<Bullet> itB = bullets.iterator();

                while (itB.hasNext()) {


                    Bullet b = itB.next();
                    if(!b.hit){
                        switch (b.weaponType){
                            case 0: b.setY(b.getY() - 10);
                                break;
                            case 1: b.setY(b.getY() - 20);
                                break;

                        }
                    } else {
                        if((System.currentTimeMillis() - b.hitTime) >= 300){
                            itB.remove();
                        }
                    }



                    if (b.getY() <= 0) {
                        itB.remove();
                    }
                }

                // Enemies löschen wenn außerhalb
                Iterator<Enemy> itE = enemies.iterator();
                while (itE.hasNext()) {
                    Enemy enemy = itE.next();
                    if (enemy.getY() >= Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY-25) {
                        itE.remove();
                        player1.player.stop();
                        // fail screen
                        DeathPanel panel = new DeathPanel(score);
                        Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.DeathPanelActivate(panel);
                        panel.setBackground(Color.BLACK);
                    }

                }

                // Kollisionen prüfen 
                itB = bullets.iterator();// nochmal weil der von oben schon leer ist
                while (itB.hasNext()) {
                    Bullet b = itB.next();

                    itE = enemies.iterator();
                    while (itE.hasNext()) {
                        Enemy enemy = itE.next();
                            //bullet und enemy coliding
                        if (enemy.isCollidingWithBullet(b)) {


                            b.hit = true;
                            b.hitTime = System.currentTimeMillis();
                            switch (b.weaponType){
                                case 1: b.setWidth(400);
                                        b.setHeight(400);
                                        if(!b.changedX || !b.changedY){
                                            b.setX(b.getX() - b.getWidth() /4);
                                            b.setY(b.getY() - b.getHeight()/2);
                                            b.changedX = true;
                                            b.changedY = true;
                                        }
                            }
                            // enemy hp updaten
                            int weaponType = b.weaponType;
                            switch (weaponType) {
                                case 0: //main Weapon
                                    enemy.setHP(enemy.getHP()-player1.getMainWeapon().getDamage());
                                    break;
                                case 1: // Grenade / Q
                                    enemy.setHP(enemy.getHP()-player1.getSpecialQAbilitie().getDamage());
                                    break;
                                default:
                                    // idk gar nichts i supose
                            }


                            // bullet updaten
                            b.health--;
                            if(b.health == 0){
                                itB.remove();
                            }
                            // chekcen ob enemy weg muss
                            if(enemy.getHP()<=0){
                                updateScore(enemy.getType());
                                itE.remove();
                                player1.addMoney(1);
                                sounds.playEnemyDeath();
                                break;
                            }

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

    //methodend





    //Score
    public void updateScore(int score){
        this.score += score* 10L;
        
    }
    private void drawScore(Graphics g) {
    g.setColor(Color.WHITE); // Farbe z. B. weiß
    g.setFont(new Font("SansSerif", Font.BOLD, 20)); // Schriftart
    g.drawString("Score: " + score, 10, 20); // Links oben bei (10, 20)
    }

    private void drawMoney(Graphics g) {
        g.setColor(Color.WHITE); //
        g.setFont(new Font("SansSerif", Font.BOLD, 20)); // Schriftart
        g.drawString("Money: " + player1.getMoney(), Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeX-150, 20); // rechts oben
    }

    // Tasteneingaben
    @Override public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //key logik
        if (key == KeyEvent.VK_LEFT|| key == KeyEvent.VK_A ) isLeftPressed= true;   // mit booleans damit parralele eingaben möglich sind
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) isRightPressed = true;
        if (key == KeyEvent.VK_SPACE ) isSpacePressed= true ;
        if (key == KeyEvent.VK_Q) isQPressed = true;
        if (key == KeyEvent.VK_ESCAPE) isESCPressed = true;
    }
    @Override public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT|| key == KeyEvent.VK_A ) isLeftPressed= false;   
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) isRightPressed = false;
        if (key == KeyEvent.VK_SPACE ) isSpacePressed= false ;
        if (key == KeyEvent.VK_Q) isQPressed= false;
        //if (key == KeyEvent.VK_ESCAPE) isESCPressed = false;

    }
    @Override public void keyTyped(KeyEvent e) {
    }

    public void setPause(boolean b) {
        this.paused=b;
    }

    public Player getPlayer() {return player1;}
}