package solitaire.viev;

import solitaire.model.Board;
import solitaire.model.Position;
import solitaire.model.cards.Card;
import solitaire.model.cards.FoundationCard;
import solitaire.model.cards.Suit;
import solitaire.model.cards.TableauCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardFrame extends JFrame {
    private Board board;

    public BoardFrame(Board board) {
        setSize(1500, 1500);
        setLocationRelativeTo(this);

        this.board = board;
        redraw();
        setVisible(true);
    }

    public void redraw() {
        System.out.println(System.currentTimeMillis());
        JPanel back = new JPanel();
        back.setSize(getSize());
        back.setBackground(new Color(52, 162, 73));
        back.setLayout(null);
        back.setLocation(0,0);

        FoundationCard[] foundation = board.getFoundation();
        for(int i = 0; i < board.foundationSize; i++) {
            int size = foundation[i].getSize();
            FoundationPanel fpanel = new FoundationPanel();
            fpanel.setLocation(100 + 150*i, 100);

            if(size >= 1) {
                CardPanel cpanel = new CardPanel(foundation[i].getLast(), new ArrayList<>(), back, this);
                cpanel.setLocation(100 + 150*i, 100);
                back.add(cpanel);
            }
            if(size >= 2) {
                CardPanel cpanel = new CardPanel(foundation[i].getLast().getAbove(), new ArrayList<>(), back, this);
                cpanel.setLocation(100 + 150*i, 100);
                back.add(cpanel);
            }
            back.add(fpanel);
        }

        TableauCard[] tableau = board.getTableau();
        for(int i = 0; i < board.tableauSize; i++) {
            int height = 350 + (tableau[i].getSize()-1)*40;
            Card card = tableau[i].getLast();
            ArrayList<JPanel> list = new ArrayList<>();
            while(!(card instanceof TableauCard)) {
                ArrayList<JPanel> in = new ArrayList<>(list);
                CardPanel cpanel = new CardPanel(card, in, back, this);
                cpanel.setLocation(100 + 150*i, height);
                height -= 40;
                back.add(cpanel);
                card = card.getAbove();
                list.add(cpanel);
            }
        }

        System.out.println(System.currentTimeMillis());
        Movement mv = new Movement(back.getComponents());
        System.out.println(System.currentTimeMillis());
        getContentPane().removeAll();
        add(back);
        repaint();
        System.out.println(System.currentTimeMillis());
        System.out.println("End of repaint");
    }

    private ArrayList<Coordinates> giveCoordinates(ArrayList<Position> pos) {
        ArrayList<Coordinates> list = new ArrayList<>();
        for(Position p : pos) {
            if(p.isFoundation())
                list.add(new Coordinates(100 + 150*p.getPile(), 100));
            else
                list.add(new Coordinates(100 + 150*p.getPile(), 350 + 40*p.getNumber()));
        }

        return list;
    }

}
