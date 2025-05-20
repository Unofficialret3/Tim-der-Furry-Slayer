import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel {
    public PausePanel() {
        this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintPanel(g);
    }

    public void paintPanel(Graphics g) {
        this.setBackground(Color.MAGENTA);
        String headline = "Pauseeeee";
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        g.drawString(headline, this.getWidth()/2, this.getHeight()/4);
    }
}
