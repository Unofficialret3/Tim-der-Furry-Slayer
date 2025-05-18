
import java.awt.Color;

import javax.swing.*;


public class SpaceInvaders {


    public static void main(String[] args) {

        //jframe
        JFrame frame = new JFrame("Space Invaders Very HD 60FPS!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);

        // Panel
        GamePanel panel = new GamePanel(); 
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.setVisible(true);
    }

}