package solitaire.viev;

import solitaire.model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Movement implements MouseListener, MouseMotionListener {

    private int X, Y;
    private int startX, startY;

    public Movement(Component... pns) {
        for(Component panel : pns) {
            if(panel instanceof CardPanel) {
                CardPanel cardpanel = (CardPanel)panel;
                if(cardpanel.card.inStack() && !cardpanel.frame.getBoard().isCardPointed(cardpanel.card))
                    continue;
                panel.addMouseListener(this);
                panel.addMouseMotionListener(this);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        X = mouseEvent.getX();
        Y = mouseEvent.getY();
        startX = mouseEvent.getComponent().getX();
        startY = mouseEvent.getComponent().getY();
        if(mouseEvent.getComponent() instanceof CardPanel) {
            if(!((CardPanel)mouseEvent.getComponent()).card.isVisible())
                return;
            ArrayList<JPanel> list = ((CardPanel)mouseEvent.getComponent()).dependencies;
            JPanel panel = ((CardPanel)mouseEvent.getComponent()).main;
            //Collections.reverse(list);
            for(JPanel x : list) {
                //System.out.println(x.getName());
                panel.setComponentZOrder(x, 0);
            }
            panel.setComponentZOrder(mouseEvent.getComponent(), 0);
            //Collections.reverse(list);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        //mouseEvent.getComponent().setLocation(startX, startY);
        CardPanel panel = (CardPanel)mouseEvent.getComponent();
        ArrayList<Position> list = panel.getPositions();
        //System.out.println("panel: " + panel.getX() + " " + panel.getY());
        int minDist = (panel.getX()-startX)*(panel.getX()-startX) + (panel.getY()-startY)*(panel.getY()-startY);
        Position pos = null;

        for(Position p : list) {
            Coordinates cords = getCords(p);
            //System.out.println(cords.x + " " + cords.y);
            int x = (panel.getX()-cords.x)*(panel.getX()-cords.x) + (panel.getY()-cords.y)*(panel.getY()-cords.y);
            if(x < minDist) {
                minDist = x;
                pos = p;
            }
        }

        if(pos != null) {
            //System.out.println("ala");
            panel.frame.getBoard().performMove(panel.card, pos);

        }
        ((CardPanel)mouseEvent.getComponent()).frame.redraw();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if(mouseEvent.getComponent() instanceof CardPanel) {
            if(!((CardPanel)mouseEvent.getComponent()).card.isVisible())
                return;
            mouseEvent.getComponent().setLocation((mouseEvent.getX() + mouseEvent.getComponent().getX())-X, (mouseEvent.getY() + mouseEvent.getComponent().getY())-Y);
            ArrayList<JPanel> list = ((CardPanel)mouseEvent.getComponent()).dependencies;

            JPanel panel = ((CardPanel)mouseEvent.getComponent()).main;
            Collections.reverse(list);
            for(JPanel x : list) {
                //System.out.println(x.getName());
                panel.setComponentZOrder(x, 0);
                x.setLocation((mouseEvent.getX() + x.getX())-X, (mouseEvent.getY() + x.getY())-Y);
            }
            Collections.reverse(list);
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    private Coordinates getCords(Position p) {
        if(p.isFoundation())
            return new Coordinates(100 + 120*p.getPile(), 100);
        else
            return new Coordinates(100 + 120*p.getPile(), 260 + 30*p.getNumber());
    }
}
