package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cercle {
    private static int counter = 0;
    private static final List<Color> rainbow = new ArrayList<>(){ // list of all the colors of the rainbow
        {
            add(Color.RED);
            add(Color.ORANGE);
            add(Color.YELLOW);
            add(Color.GREEN);
            add(Color.BLUE);
            add(new Color(75, 0, 130));
            add(new Color(238, 130, 238));
        }};

    private Point pnt;
    private Color color;
    private int radius;

    public Cercle(int x, int y, int radius){
        this.pnt = new Point(x, y);
        this.radius = radius;
        this.color = rainbow.get(counter % rainbow.size());
        counter++;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(pnt.x - radius, pnt.y - radius, 2*radius, 2*radius);
    }

    //regarde si le point ce trouve dans le cercle
    public boolean contains(Point p){
        return Math.sqrt(Math.pow(pnt.x - p.x, 2) + Math.pow(pnt.y - p.y, 2)) <= radius;
    }

    public void setRadius(int newRadius){
        radius = newRadius;
    }

    public void setX(int x){
        pnt.x = x;
    }

    public void setY(int y){
        pnt.y = y;
    }

    public void erase(Graphics g){
        Color temp = color;
        color = Color.WHITE;
        draw(g);
        color = temp;
    }
}
