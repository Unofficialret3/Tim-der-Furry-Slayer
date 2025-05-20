
import java.awt.*;

import javax.swing.*;


public class SpaceInvaders {

    protected static int sizeX = 1400;
    protected static int sizeY = 1000;
    protected static JFrame frame = new JFrame();

    public static void main(String[] args) {
        //jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizeX, sizeY);
        frame.setResizable(false);
        frame.setTitle("TIM DER FURRY SLAYER very HD 69FPS!!! EXTREME OPEN ALPHA V 4.20");
        //homescreen
       //HomeScreenActivate();
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
        frame.repaint();
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }

    protected static void DeathPanelActivate(DeathPanel panel){
        frame.getContentPane().removeAll(); 
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    protected static void PausePanelActivate(){
        frame.getContentPane().removeAll();
        PausePanel panel = new PausePanel();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

}