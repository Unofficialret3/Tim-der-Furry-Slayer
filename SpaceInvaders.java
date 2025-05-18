
import java.awt.Color;

import javax.swing.*;


public class SpaceInvaders {

    protected static int sizeX = 1000;
    protected static int sizeY = 800;

    public static void main(String[] args) {

        //jframe
        JFrame frame = new JFrame("Space Invaders Very HD 60FPS!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizeX, sizeY);
        frame.setResizable(false);

        // Panel
        GamePanel panel = new GamePanel(); 
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.setVisible(true);
    }

}