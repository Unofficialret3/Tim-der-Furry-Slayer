
import java.awt.Color;

import javax.swing.*;


public class SpaceInvaders {

    protected static int sizeX = 1400;
    protected static int sizeY = 1000;
    protected static JFrame frame = new JFrame("Space Invaders Very HD 60FPS!!!");

    public static void main(String[] args) {

        //jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizeX, sizeY);
        frame.setResizable(false);

        // Panel
        GamePanel panel = new GamePanel(); 
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.setVisible(true);
    }

    protected static void DeatPanelActivate( DeathPanel panel){

        frame.getContentPane().removeAll(); 
        frame.add(panel);
        frame.revalidate();
        frame.repaint();;
    }

}