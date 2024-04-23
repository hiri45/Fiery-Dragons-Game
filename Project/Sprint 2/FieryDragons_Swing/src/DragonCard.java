import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DragonCard extends JPanel {
    private final ImageIcon front = new ImageIcon("src/Images/dragon card resize.png");
    private final String back;
    private boolean isFrontVisible = true;
    private int amount;

    public DragonCard(String back, int amount) {
//        this.front = new ImageIcon("src/Images/FD logo.png");
        this.back = back;
        this.amount = amount;
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
        JLabel label;
        if(isFrontVisible)
        {
            label = new JLabel(front);
        }
        else
        {
            label = new JLabel(back);
        }
//        JLabel label = new JLabel(isFrontVisible ? front : back);
        add(label);
        revalidate();
        repaint();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isFrontVisible() {
        return isFrontVisible;
    }

    public void setFrontVisible(boolean frontVisible) {
        isFrontVisible = frontVisible;
    }

    public String getBack() {
        return back;
    }

    public ImageIcon getFront() {
        return front;
    }
}
