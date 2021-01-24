package solitaire;

import solitaire.model.Board;
import solitaire.viev.BoardFrame;

import java.awt.*;
import javax.swing.*;


public class SolitaireApp extends JFrame {

    public static void main(String[] args) {
        Board board = new Board();
        board.createNewBoard();
        SwingUtilities.invokeLater(() -> {
            BoardFrame frame = new BoardFrame(board);
            frame.setBackground(new Color(52, 162, 73));
            frame.setVisible(true);
        });
    }

}
