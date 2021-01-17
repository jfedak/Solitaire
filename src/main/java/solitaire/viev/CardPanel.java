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
    BufferedImage front;
    private final int w = 80, h = 120;
    static BufferedImage[] cardImages;
    static BufferedImage back;

    static {
        cardImages = new BufferedImage[52];
        for(int i = 0; i < 52; i++) {
            String name = "/" + Card.getName(i) + ".png";
            try {
                //System.out.println(i +  ": " + name);
                cardImages[i] = ImageIO.read(CardPanel.class.getResource(name));
            } catch (IOException e) {
                System.out.println("Could not read in the pic");
                System.exit(0);
            }
        }

        try {
            back = ImageIO.read(CardPanel.class.getResource("/blue_back.png"));
        } catch (IOException e) {
            System.out.println("Could not read in the pic");
            System.exit(0);
        }

    }

    public CardPanel(Card c, ArrayList<JPanel> list, JPanel main, BoardFrame frame) {
        setOpaque(false);
        this.card = c;
        this.frame = frame;
        this.dependencies = list;
        this.main = main;

        int number = c.getNumber();
        front = cardImages[number];
        setSize(w, h);
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

    public ArrayList<Position> getPositions() {
        return frame.getBoard().getPositions(card);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(card.isVisible())
            g.drawImage(front,0,0,this);
        else
            g.drawImage(back,0,0,this);
    }
}
