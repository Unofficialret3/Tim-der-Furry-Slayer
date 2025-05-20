
import java.awt.Color;

import javax.swing.*;


public class SpaceInvaders {

    protected static int sizeX = 1400;
    protected static int sizeY = 1000;
    protected static JFrame frame = new JFrame("TIM THE FURY SLAYER Very HD 69FPS!!! EXTREME 2 Open BETA ALPHA V4.20");


    
    public static void main(String[] args) {

        //jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizeX, sizeY);
        frame.setResizable(false);

        //homescreen HomeScreenActivate();
        
       GamePanelActivate();
    }

    protected static void HomeScreenActivate(){
        HomeScreen panel = new HomeScreen();
        if(frame.getContentPane()!=null){
            frame.getContentPane().removeAll();
        }
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.revalidate();
        frame.setVisible(true);
        

    }

    protected static void GamePanelActivate(){
         GamePanel panel = new GamePanel(); 

        frame.getContentPane().removeAll();
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.revalidate();
        frame.setVisible(true);
        
    }

    protected static void DeathPanelActivate(DeathPanel panel){
       
       
        frame.getContentPane().removeAll(); 
        frame.add(panel);
        frame.revalidate();
        frame.repaint();;
    }

}