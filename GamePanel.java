
import javax.swing.*;
import java.util.Iterator;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private long score;

    //liste für die schüsse
    protected static ArrayList<Bullet> bullets;
    protected ArrayList<Enemy> enemies;

    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;
    private boolean isSpacePressed = false;
    private boolean isQPressed = false;
    SoundPlayer sounds = new SoundPlayer();


    // spieler createn
    protected  static Player player1 = new Player((SpaceInvaders.sizeX/2)- 50, SpaceInvaders.sizeY-150); // Mitte unten

    // test enemey
    //protected Enemy enemy1 = new Enemy((SpaceInvaders.sizeX/2)- 50, SpaceInvaders.sizeY-750 ); // mitte oben



    public GamePanel() {
        requestFocusInWindow();
        setFocusable(true);
        addKeyListener(this);

        //listew für bullets initaliesieren
        bullets = new ArrayList<>();
        // liste für enemys
        enemies = new ArrayList<>();
        // spawn enemies
        spawnEnemies();



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

        // enemies malen
        for(Enemy enemy : enemies){
            if(enemy.getY()<= SpaceInvaders.sizeY){
                //move runter y achse
                enemy.moveDown();
                enemy.moveRandomLR();
            }
            else{
            }
        enemy.draw(g);
        drawScore(g);
        }
        
        // TODO:  und Objekte zeichnen
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Spiel-Logik updaten

        //enemy spawn logik
        if(enemies.isEmpty()){
            //bullets.clear();
            //spawnEnemies();
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
                player1.shootMainWeapon();
            }

            if(isQPressed){
                player1.shootSpecialQWeapon();
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
                        if((System.currentTimeMillis() - b.hitTime) >= 500){
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
                    if (enemy.getY() >= SpaceInvaders.sizeY-25) {
                        itE.remove();
                        // fail screen
                        DeathPanel panel = new DeathPanel(score);
                        SpaceInvaders.DeathPanelActivate(panel);
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
                            updateScore(enemy.getType());
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
                            b.health--;
                            if(b.health == 0){
                                itB.remove();
                            }
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

    //spawn patterns
    private int[][] spawnPattern1 = {
     { 1,0,0}, // linke spalte
     { 1, 1,1 } , // mitte
     { 1,0,0 }// rechte spalte
    };
     private int[][] spawnPattern2 = {
     { 0,1,0}, // linke spalte
     { 1, 0,1 } , // mitte
     { 0,1,0 }// rechte spalte
    };
     private int[][] spawnPattern3 = {
     { 1,1,1}, // linke spalte
     { 1, 1,1 } , // mitte
     { 1,1,1 }// rechte spalte
    };
    protected int[][] spawnPatternArrow = {
    { 0, 0, 1, 0, 0 },
    { 0, 1, 1, 1, 0 },
    { 1, 1, 1, 1, 1 }
};
protected int[][] spawnPatternShield = {
    { 1, 1, 0, 1, 1 },
    { 1, 0, 0, 0, 1 },
    { 1, 1, 1, 1, 1 }
};
protected int[][] spawnPatternStaggered = {
    { 1, 0, 1, 0, 1 },
    { 0, 1, 0, 1, 0 },
    { 1, 0, 1, 0, 1 },
    { 0, 1, 0, 1, 0 }
};
protected int[][] spawnPatternCross = {
    { 0, 0, 1, 0, 0 },
    { 0, 0, 1, 0, 0 },
    { 1, 1, 1, 1, 1 },
    { 0, 0, 1, 0, 0 },
    { 0, 0, 1, 0, 0 }
};
protected int[][] spawnPatternDiamond = {
    { 0, 0, 1, 0, 0 },
    { 0, 1, 0, 1, 0 },
    { 1, 0, 0, 0, 1 },
    { 0, 1, 0, 1, 0 },
    { 0, 0, 1, 0, 0 }
};
protected int[][] spawnPatternChaos = {
    { 1, 0, 1, 1, 0, 1 },
    { 0, 1, 0, 0, 1, 0 },
    { 1, 1, 0, 1, 1, 0 },
    { 0, 0, 1, 0, 0, 1 }
};
protected int[][] spawnPatternDNA = {
    { 1, 0, 0, 0, 1 },
    { 0, 1, 0, 1, 0 },
    { 0, 0, 1, 0, 0 },
    { 0, 1, 0, 1, 0 },
    { 1, 0, 0, 0, 1 }
};




    //methodend
    private void spawnEnemies() {
      
        //spawnpattern choosing
            Random rand = new Random();
            int choice = rand.nextInt(10); // ergibt 0-9
            
            int[][] pattern = null;

            switch (choice) {
                case 0:
                    pattern= spawnPattern1;
                    break;
                case 1:
                    pattern= spawnPattern2;
                    break;
                case 2:
                    pattern= spawnPattern3;
                    break;
                case 3:

                    pattern=spawnPatternArrow ;
                    break;

                case 4:

                    pattern=spawnPatternChaos ;
                    break;

                case 5:

                    pattern=spawnPatternCross;
                    break;

                case 6:
                    
                    pattern=spawnPatternDNA;
                    break;

                case 7:

                    pattern=spawnPatternDiamond;
                    break;

                case 8:

                    pattern= spawnPatternShield;
                    break;

                case 9:

                    pattern=spawnPatternStaggered;
                    break;

                default:
                break;
            }
        
            int mid = pattern.length/ 2;

        // enemys spawnen
        for(int i= 0;i<=pattern.length-1;i++){
            for(int j = 0;j<=pattern.length-1;j++){

                
            // zufällige typen für enemys
            int anzahlTypen= 2;
            int type = (int)(Math.random() * anzahlTypen) + 1;


                if(pattern[i][j] == 1){
                    if(i==mid){
                        Enemy enemy = new Enemy(SpaceInvaders.sizeX/2-12, SpaceInvaders.sizeY-950 +j *100,type);
                        enemies.add(enemy);
                    }
                    else if(i<mid){
                        
                         Enemy enemy = new Enemy((SpaceInvaders.sizeX/2-12)-((1+i)*100), SpaceInvaders.sizeY-950+j*50,type);
                            enemies.add(enemy);
                    }
                    else if(i>mid){
                        Enemy enemy = new Enemy((SpaceInvaders.sizeX/2-12)+((i-1)*100),SpaceInvaders.sizeY-950+j*50,type);
                        enemies.add(enemy);
                    }
                    else{
                        System.out.println("heyho");
                    }
                }
            }
        }


    }




    //Score
    public void updateScore(int score){
        this.score += score*10;
        
    }
    private void drawScore(Graphics g) {
    g.setColor(Color.WHITE); // Farbe z. B. weiß
    g.setFont(new Font("SansSerif", Font.BOLD, 20)); // Schriftart
    g.drawString("Score: " + score, 10, 20); // Links oben bei (10, 20)
}

    // Tasteneingaben
    @Override public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //key logik
        if (key == KeyEvent.VK_LEFT|| key == KeyEvent.VK_A ) isLeftPressed= true;   // mit booleans damit parralele eingaben möglich sind
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) isRightPressed = true;
        if (key == KeyEvent.VK_SPACE ) isSpacePressed= true ;
        if (key == KeyEvent.VK_Q) isQPressed = true;
    }
    @Override public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT|| key == KeyEvent.VK_A ) isLeftPressed= false;   
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) isRightPressed = false;
        if (key == KeyEvent.VK_SPACE ) isSpacePressed= false ;
        if (key == KeyEvent.VK_Q) isQPressed= false;

    }
    @Override public void keyTyped(KeyEvent e) {}
}