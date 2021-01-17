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
    static BufferedImage image;
    private Board board;
    private BoardFrame frame;
    private final int w = 80, h = 120;

    static {
        try {
            image = ImageIO.read(CardPanel.class.getResource("/blue_back.png"));
        } catch (IOException e) {
            System.out.println("Could not read in the pic");
            System.exit(0);
        }
    }

    public StackPanel(BoardFrame frame) {
        //System.out.println("creating stack!");
        //setOpaque(false);
        this.frame = frame;
        this.board = frame.getBoard();

        if(board.stackEnded())
            setBackground(new Color(211,211,211));

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
        //System.out.println("printing stack!");
        super.paintComponent(g);
        if(!board.stackEnded())
            g.drawImage(image,0,0,this);
    }
}
