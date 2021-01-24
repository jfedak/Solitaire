package solitaire.viev;

import solitaire.model.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StackPanel extends JPanel {
    private static BufferedImage image, empty;
    private Board board;
    private final int w = 80, h = 120;

    static {
        try {
            image = ImageIO.read(CardPanel.class.getResource("/blue_back.png"));
            empty = ImageIO.read(CardPanel.class.getResource("/stack.png"));
        } catch (IOException e) {
            System.out.println("Could not read in the pic");
            System.exit(0);
        }
    }

    public StackPanel(BoardFrame frame) {
        setOpaque(false);
        this.board = frame.getBoard();

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                board.performMove();
                frame.redraw();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) { }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) { }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) { }

            @Override
            public void mouseExited(MouseEvent mouseEvent) { }
        });
        setSize(w, h);
    }

    public Dimension getPreferredSize() {
        return new Dimension(w,h);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(!board.stackEnded())
            g.drawImage(image,0,0,this);
        else
            g.drawImage(empty,0,0,this);
    }
}
