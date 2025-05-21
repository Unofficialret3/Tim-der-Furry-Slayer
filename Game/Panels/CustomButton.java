package Game.Panels;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class CustomButton extends JComponent {
        private  String label;
        private boolean hovered = false;
        private Runnable onClick = null;

        public CustomButton(String label) {
            this.label = label;
            setPreferredSize(new Dimension(300, 60));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hovered = false;
                    repaint();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (onClick != null) {
                        onClick.run();
                    }
                }
            });
        }

        public void setOnClick(Runnable r) {
            this.onClick = r;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            GradientPaint gradient = new GradientPaint(0, 0, hovered ? new Color(255, 50, 50) : new Color(200, 0, 0),
                                                       getWidth(), getHeight(), hovered ? new Color(255, 150, 0) : new Color(100, 0, 0));
            g2.setPaint(gradient);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial Black", Font.BOLD, 20));
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(label);
            int textAscent = fm.getAscent();

            g.drawString(label, (getWidth() - textWidth) / 2, (getHeight() + textAscent) / 2 - 5);
    }

    public void setLabel(String newText) {
        this.label = newText;
        repaint();
    }
}