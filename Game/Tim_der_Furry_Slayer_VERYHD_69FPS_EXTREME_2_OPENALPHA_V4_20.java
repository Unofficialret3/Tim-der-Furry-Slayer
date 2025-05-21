package Game;

import Game.Objects.Player;
import Game.Panels.*;

import java.awt.*;

import javax.swing.*;


public class Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20 {

    public static int sizeX = 1400;
    public static int sizeY = 1000;
    protected static JFrame frame = new JFrame();
    // spieler createn
    protected  static Player player1 = new Player((Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeX/2)- 50, Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY-150); // Mitte unten

    public static void main(String[] args) {
        //jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(sizeX, sizeY);
        frame.setResizable(false);
        frame.setTitle("TIM DER FURRY SLAYER very HD 69FPS!!! EXTREME 2 OPEN ALPHA V 4.20");
        //homescreen
      // HomeScreenActivate();
       GamePanelActivate();
    }

    protected static void HomeScreenActivate(){
        HomePanel panel = new HomePanel();
        if(frame.getContentPane()!=null){
            frame.getContentPane().removeAll();
        }
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.revalidate();
        frame.setVisible(true);
    }

    public static void GamePanelActivate(){
        GamePanel panel = new GamePanel(player1);
        frame.getContentPane().removeAll();
        panel.setBackground(Color.BLACK);
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }
    public static void GamePanelContinue(GamePanel panel,Player player) {
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
        panel.requestFocusInWindow();
    }

    public static  void ShopPanelActivate(ShopPanel panel){
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        panel.requestFocusInWindow();
    }

}