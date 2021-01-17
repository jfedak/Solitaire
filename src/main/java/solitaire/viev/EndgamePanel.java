package solitaire.viev;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EndgamePanel extends JPanel {
    static BufferedImage image;
    int w = 1000, h = 300;
    BoardFrame frame;

    static {
        try {
            image = ImageIO.read(CardPanel.class.getResource("/endgame.png"));
        } catch (IOException e) {
            System.out.println("Could not read in the pic");
            System.exit(0);
        }
    }

    public EndgamePanel(BoardFrame frame) {
        this.frame = frame;
        setOpaque(false);
        setSize(w, h);
    }

    public Dimension getPreferredSize() {
        return new Dimension(w,h);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}
