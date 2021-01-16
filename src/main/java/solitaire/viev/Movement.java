package solitaire.viev;

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
            panel.addMouseListener(this);
            panel.addMouseMotionListener(this);
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
            Collections.reverse(list);
            for(JPanel x : list) {
                //System.out.println(x.getName());
                panel.setComponentZOrder(x, 0);
            }
            panel.setComponentZOrder(mouseEvent.getComponent(), 0);
            Collections.reverse(list);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        //mouseEvent.getComponent().setLocation(startX, startY);
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
            for(JPanel x : list) {
                //System.out.println(x.getName());
                x.setLocation((mouseEvent.getX() + x.getX())-X, (mouseEvent.getY() + x.getY())-Y);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
