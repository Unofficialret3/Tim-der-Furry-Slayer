//imports
import java.awt.Graphics;
import java.awt.Color;


public class Player {


    private int x, y, width = 100, height = 30;
    private int speed = 15;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void shoot(){
        //TODO: create a new bullet object and add it to the game
        
    }




    //spieler malen
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }
}
