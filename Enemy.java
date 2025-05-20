//imports
import java.awt.Graphics;
import java.awt.Color;


public class Enemy {


    private int x, y, width = 25, height = 25, type ;
    private int speed ;

    long oldMillis= 0;


    public Enemy(int startX, int startY,int type) {
        this.x = startX;
        this.y = startY;
        //später brauchbar
        this.type=type;
        this.speed= this.getSpeed();
        
    }

    

    public void moveLeft(int s) {
        x -= s;
    }

    public void moveRight(int s) {
        x += s;
    }
    public void moveDown() {
        y += speed;
    }
    public void moveUp() {
        y -= speed;
    }
    public void moveRandomLR(){

        long newMilis = System.currentTimeMillis();
                
                //wenn nicht bewegt seit x milis
                if((newMilis-oldMillis)>250){
                    
                    if(Math.random()<0.5){
                            moveLeft(5);
                        }
                        else{
                            moveRight(5);
                        }


                oldMillis = newMilis;
                }

        
    }

    public void shoot() {
        //TODO: für gegener machen und dann auch kollison mit spieler 
       // Bullet bullet = new Bullet(x,y);
       //GamePanel.bullets.add(bullet); // wahrscheinlich eigene liste für enemy bullets

    }


















    public boolean isCollidingWithBullet(Bullet b){

    int bulletX = b.getX();
    int bulletY = b.getY();
    int bulletW = b.getWidth();
    int bulletH = b.getHeight();

    // wenn eine kannte sich iwi berührt bzw überschneidet dann true ( AABB-Kollision (Axis-Aligned Bounding Box))
    return bulletX < x + width && bulletX + bulletW > x && bulletY < y + height && bulletY + bulletH > y;

    }


    //enemy  malen
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(x, y, width, height);
    }

    //get mothoden
    private int getSpeed() {
       
        switch (this.getType()) {
            //nromaler enemy
            case 1:
                return 1;
            //schneller enemy 
            case 2 : 
                return 4;
           
            default:
            
                return 0;
                
        }
       
    }



    public int getType() { return type; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
