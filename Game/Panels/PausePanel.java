package Game.Panels;

import Game.Objects.Player;
import Game.Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20;

import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel {



    private final CustomButton BackButton ;
    private  Player player;
    private  GamePanel gamePanel;


    public PausePanel(Player player,GamePanel gamePanel) {

        this.player=player;
        this.gamePanel=gamePanel;

        this.setFocusable(true);

        setLayout(null);

        BackButton = new CustomButton("Back to game");
        int btnWidth = 300;
        int btnHeight = 60;
        int centerX = (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeX - btnWidth) / 2;
        BackButton.setBounds(centerX, Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY- (Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.sizeY/4), btnWidth, btnHeight);

        BackButton.setOnClick(() -> {
            System.out.println("Spiel wird fortgesetzt...");
            gamePanel.setPause(false);
            Tim_der_Furry_Slayer_VERYHD_69FPS_EXTREME_2_OPENALPHA_V4_20.GamePanelContinue(gamePanel,player);
        });
        add(BackButton);

    }








    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintPanel(g);
    }

    public void paintPanel(Graphics g) {
        this.setBackground(Color.MAGENTA);
        String headline = "Pauseeeee  UWU ";
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        g.drawString(headline, this.getWidth()/2 - (headline.length()*10) /2, this.getHeight()/4);

    }
}
