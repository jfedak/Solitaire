package solitaire;

import solitaire.model.Board;
import solitaire.viev.BoardFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;


public class SolitaireApp extends JFrame {
    /*
    public SolitaireApp() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500,1500);
        setLocationRelativeTo(this);
        setTitle("Solitaire");

        Board board = new Board();
        board.createNewBoard();
        BoardPanel panel = new BoardPanel(board);

        JPanel jpanel = new JPanel();
        jpanel.setBackground(new Color(200, 200, 200));
        jpanel.setSize(400,400);
        add(jpanel);

        add(panel);
        setVisible(true);


    }

     */

    public static void main(String[] args) {
        Board board = new Board();
        board.createNewBoard();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BoardFrame frame = new BoardFrame(board);
                frame.setBackground(new Color(52, 162, 73));
                frame.setVisible(true);
            }
        });
    }

}
