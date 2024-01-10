package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class Canvas extends JFrame {
    private LinkedList<Cercle> cerclesListe = new LinkedList<>();

    private JPanel canvasPanel = new JPanel();

    public Canvas(String title){
        super(title);
        start();
    }

    public void start(){
        //Création frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Panel caneva
        canvasPanel.setBackground(Color.white);
        canvasPanel.setPreferredSize(new Dimension(640, 480));
        MouseHandler handler = new MouseHandler(this);
        canvasPanel.addMouseListener(handler);
        canvasPanel.addMouseMotionListener(handler);
        getContentPane().add(canvasPanel, BorderLayout.CENTER);

        //Boutons
        JButton clear = new JButton("Clear");
        JButton quit = new JButton("Quit");
        JPanel inputPanel = new JPanel();
        inputPanel.add(clear);
        inputPanel.add(quit);
        getContentPane().add(inputPanel, BorderLayout.SOUTH);

        //Affiche la fenêtre
        pack();
        setVisible(true);

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerclesListe.clear();
                canvasPanel.repaint();
            }
        });

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void addCercle(Cercle cercle){
        cerclesListe.add(cercle);
        drawForm();
    }

    public void removeCircle(Cercle cercle){
        cerclesListe.remove(cercle);
        drawForm();
    }

    public Cercle getActualCercle(int x, int y){
        ListIterator<Cercle> it = cerclesListe.listIterator(cerclesListe.size());
        while (it.hasPrevious()){
            Cercle cercle = it.previous();
            if (cercle.contains(new Point(x, y))){
                return cercle;
            }
        }
        return null;
    }

    public void drawForm(){
        for (Cercle cercle : cerclesListe){
            cercle.draw(canvasPanel.getGraphics());
        }
    }

    public Graphics getPanelGraphic(){
        return canvasPanel.getGraphics();
    }

}
