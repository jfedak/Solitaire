package solitaire.viev;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameButton extends JPanel {
    static BufferedImage normal, pressed;
    int w = 100, h = 30;
    boolean isPressed = false;
    BoardFrame frame;

    static {
        try {
            normal = ImageIO.read(CardPanel.class.getResource("/button1.png"));
            pressed = ImageIO.read(CardPanel.class.getResource("/button2.png"));
        } catch (IOException e) {
            System.out.println("Could not read in the pic");
            System.exit(0);
        }
    }

    public GameButton(BoardFrame frame) {
        this.frame = frame;
        setSize(w, h);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) { }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                isPressed = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                isPressed = false;
                frame.getBoard().createNewBoard();
                frame.redraw();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) { }

            @Override
            public void mouseExited(MouseEvent mouseEvent) { }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(w,h);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!isPressed)
            g.drawImage(normal,0,0,this);
        else
            g.drawImage(pressed,0,0,this);
    }
}
