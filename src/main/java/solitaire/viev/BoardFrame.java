package solitaire.viev;

import solitaire.model.Board;
import solitaire.model.cards.Card;
import solitaire.model.cards.FoundationCard;
import solitaire.model.cards.TableauCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardFrame extends JFrame {
    private Board board;
    private int height = 1000, width = 1000;

    public BoardFrame(Board board) {
        setSize(width, height);
        setLocationRelativeTo(this);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.board = board;
        redraw();
    }

    public Board getBoard() {
        return this.board;
    }

    public void redraw() {
        JPanel back = new JPanel();
        back.setSize(getSize());
        back.setBackground(new Color(52, 162, 73));
        back.setLayout(null);
        back.setLocation(0,0);

        GameButton button = new GameButton(this);
        button.setLocation(100,30);
        back.add(button);

        FoundationCard[] foundation = board.getFoundation();
        for(int i = 0; i < board.foundationSize; i++) {
            int size = foundation[i].getSize();
            FoundationPanel fpanel = new FoundationPanel();
            fpanel.setLocation(100 + 120*i, 100);

            if(size >= 1) {
                CardPanel cpanel = new CardPanel(foundation[i].getLast(), new ArrayList<>(), back, this);
                cpanel.setLocation(100 + 120*i, 100);
                back.add(cpanel);
            }
            if(size >= 2) {
                CardPanel cpanel = new CardPanel(foundation[i].getLast().getAbove(), new ArrayList<>(), back, this);
                cpanel.setLocation(100 + 120*i, 100);
                back.add(cpanel);
            }
            back.add(fpanel);
        }

        ArrayList<Card> stack = board.getStack();
        int stackPtr = board.getStackPtr();
        for(int i = 0; i <= 2; i++) {
            if(stackPtr >= i) {
                CardPanel cpanel = new CardPanel(stack.get(stackPtr-i), new ArrayList<>(), back, this);
                cpanel.setLocation(700-20*i, 100);
                back.add(cpanel);
            }
        }

        StackPanel spanel = new StackPanel(this);
        spanel.setLocation(820,100);
        back.add(spanel);

        if(board.checkEndgame()) {
            printEndgame(back);
            getContentPane().removeAll();
            add(back);
            repaint();
            return;
        }

        TableauCard[] tableau = board.getTableau();
        for(int i = 0; i < board.tableauSize; i++) {
            int height = 260 + (tableau[i].getSize()-1)*30;
            Card card = tableau[i].getLast();
            ArrayList<JPanel> list = new ArrayList<>();
            while(!(card instanceof TableauCard)) {
                ArrayList<JPanel> in = new ArrayList<>(list);
                CardPanel cpanel = new CardPanel(card, in, back, this);
                cpanel.setLocation(100 + 120*i, height);
                height -= 30;
                back.add(cpanel);
                card = card.getAbove();
                list.add(cpanel);
            }
        }

        Movement mv = new Movement(back.getComponents());
        getContentPane().removeAll();
        add(back);
        repaint();
    }

    private void printEndgame(JPanel back) {
        System.out.println("YOU WON!");
        JPanel text = new EndgamePanel(this);
        text.setLocation(0,400);
        back.add(text);
    }

}
