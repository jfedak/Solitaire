package solitaire.viev;

import solitaire.model.Position;
import solitaire.model.cards.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class CardPanel extends JPanel {
    private ArrayList<JPanel> dependencies;
    private JPanel main;
    private BoardFrame frame;
    private Card card;
    private BufferedImage front;
    private final int w = 80, h = 120;
    static BufferedImage[] cardImages;
    static BufferedImage back;

    static {
        cardImages = new BufferedImage[52];
        for(int i = 0; i < 52; i++) {
            String name = "/" + Card.getName(i) + ".png";
            try {
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

    public ArrayList<JPanel> getDependencies() {
        return this.dependencies;
    }

    public JPanel getMain() {
        return this.main;
    }

    public BoardFrame getFrame() {
        return this.frame;
    }

    public Card getCard() {
        return this.card;
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
