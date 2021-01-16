package solitaire.viev;

import solitaire.model.Position;
import solitaire.model.cards.Card;
import solitaire.model.cards.Suit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class CardPanel extends JPanel {
    ArrayList<JPanel> dependencies;
    ArrayList<Position> positions;
    JPanel main;
    BoardFrame frame;
    Card card;
    BufferedImage back, front;
    private final int w = 100, h = 150;

    public CardPanel(Card c, ArrayList<JPanel> list, JPanel main, BoardFrame frame) {
        setOpaque(false);
        this.card = c;
        this.frame = frame;
        this.dependencies = list;
        this.main = main;
        try {
            String name = "/";
            switch(c.getRank()) {
                case 1:
                    name += "A";
                    break;
                case 11:
                    name += "J";
                    break;
                case 12:
                    name += "Q";
                    break;
                case 13:
                    name += "K";
                    break;
                default:
                    name += (c.getRank());
            }

            switch(c.getSuit()) {
                case DIAMONDS:
                    name += "D";
                    break;
                case HEARTS:
                    name += "H";
                    break;
                case CLUBS:
                    name += "C";
                    break;
                case SPADES:
                    name += "S";
                    break;
            }

            name += ".png";

            //System.out.println(name);
            back = ImageIO.read(getClass().getResource("/blue_back.png"));
            front = ImageIO.read(getClass().getResource(name));

            setSize(w, h);

        } catch (IOException ioe) {
            System.out.println("Could not read in the pic");
            System.exit(0);
        }
    }

    public void setDependencies(ArrayList<JPanel> list) {
        dependencies = list;
    }

    public void setCard(Card c) {
        if(this.card.getRank() == c.getRank() && this.card.getSuit() == c.getSuit())
            this.card = c;
        else
            System.out.println("Wrong card!!!");
    }

    public void setMain(JPanel panel) {
        this.main = panel;
    }

    public Dimension getPreferredSize() {
        return new Dimension(w,h);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(card.isVisible())
            g.drawImage(front,0,0,this);
        else
            g.drawImage(back,0,0,this);
    }
}
