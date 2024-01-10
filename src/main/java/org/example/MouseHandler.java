package org.example;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
    private final Canvas caneva;
    private int x1, y1, x2, y2;
    private Cercle actualCercle = null;
    private boolean isNewCercle = false;

    public MouseHandler(Canvas canva) {
        super();
        this.caneva = canva;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Effacer cercle si click droite
        if (e.getButton() == MouseEvent.BUTTON3) {
            actualCercle = caneva.getActualCercle(e.getX(), e.getY());
            if(actualCercle != null) {
                caneva.removeCircle(actualCercle);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Créer cercle
        x1 = e.getX();
        y1 = e.getY();

        //Click gauche
        if(e.getButton() == MouseEvent.BUTTON1) {
            if (e.isShiftDown()) {
                actualCercle = caneva.getActualCercle(x1, y1);

            } else {
                actualCercle = new Cercle(x1, y1, 0);
                isNewCercle = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Ajouter cercle à liste
        if(isNewCercle){
            caneva.addCercle(actualCercle);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            actualCercle = caneva.getActualCercle(e.getX(), e.getY());
            if (actualCercle != null) {
                actualCercle.erase(caneva.getPanelGraphic());
            }
        }
        resetAll();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (actualCercle != null) {
            x2 = e.getX();
            y2 = e.getY();
            actualCercle.erase(caneva.getPanelGraphic());
            if (e.isShiftDown()) {
                actualCercle.setX(x2);
                actualCercle.setY(y2);
            } else {
                actualCercle.setRadius(getRadius());
            }
            actualCercle.draw(caneva.getPanelGraphic());
            caneva.drawForm();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private int getRadius() {
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public void resetAll() {
        x1 = x2 = y1 = y2 = 0;
        actualCercle = null;
        isNewCercle = false;
    }
}
