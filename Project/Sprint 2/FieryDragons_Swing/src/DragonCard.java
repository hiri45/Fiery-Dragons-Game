import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DragonCard extends JPanel {
    private final ImageIcon front;
    private final ImageIcon back;
    private boolean isFrontVisible = true;

    public DragonCard(ImageIcon front, ImageIcon back) {
        this.front = front;
        this.back = back;
//        setPreferredSize(new Dimension(100, 150)); // Assuming card size
        setLayout(new BorderLayout());
        updateAppearance();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flip();
            }
        });
    }

    public void flip() {
        isFrontVisible = !isFrontVisible;
        updateAppearance();
    }

    private void updateAppearance() {
        removeAll(); // Clear any existing content
        JLabel label = new JLabel(isFrontVisible ? front : back);
        add(label);
        revalidate();
        repaint();
    }
}
