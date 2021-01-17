package solitaire.viev;

import solitaire.model.cards.Card;

import javax.swing.*;
import java.awt.*;

public class FoundationPanel extends JPanel {

    private final int w = 80, h = 120;

    public FoundationPanel() {
        setSize(w,h);
        setBackground(new Color(211,211,211));
    }
}
