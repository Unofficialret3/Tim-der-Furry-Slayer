package Game;

import Game.Panels.DeathPanel;
import Game.Panels.GamePanel;
import Game.Panels.HomeScreen;
import Game.Panels.PausePanel;

import java.awt.*;

import javax.swing.*;


public class Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20 {

    public static int sizeX = 1400;
    public static int sizeY = 1000;
    protected static JFrame frame = new JFrame();

    public static void main(String[] args) {
        //jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizeX, sizeY);
        frame.setResizable(false);
        frame.setTitle("TIM DER FURRY SLAYER very HD 69FPS!!! EXTREME 2 OPEN ALPHA V 4.20");
        //homescreen
       HomeScreenActivate();
       //GamePanelActivate();
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

    public static void GamePanelActivate(){
        GamePanel panel = new GamePanel();
        frame.getContentPane().removeAll();
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }

    public static void DeathPanelActivate(DeathPanel panel){
        frame.getContentPane().removeAll(); 
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    public static void PausePanelActivate(PausePanel panel){
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

}