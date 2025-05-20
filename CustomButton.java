import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



class CustomButton extends JComponent {
        private String label;
        private boolean hovered = false;
        private Runnable onClick = null;

        public CustomButton(String label) {
            this.label = label;
            setPreferredSize(new Dimension(200, 50));

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

            g.setColor(hovered ? new Color(100, 100, 255) : new Color(70, 70, 200));
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);

            g.setColor(Color.WHITE);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(label);
            int textAscent = fm.getAscent();

            g.drawString(label, (getWidth() - textWidth) / 2, (getHeight() + textAscent) / 2 - 5);
        }
    }
