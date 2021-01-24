package solitaire.viev;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FoundationPanel extends JPanel {

    private final int w = 80, h = 120;
    private static BufferedImage image;

    static {
        try {
            image = ImageIO.read(CardPanel.class.getResource("/foundation.png"));
        } catch (IOException e) {
            System.out.println("Cant load image");
            e.printStackTrace();
        }
    }

    public FoundationPanel() {
        setOpaque(false);
        setSize(w,h);
    }

    public Dimension getPreferredSize() {
        return new Dimension(w,h);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}
