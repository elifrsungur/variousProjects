package circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.Random;

class Surface extends JPanel implements ActionListener {
    private LinkedList<Area> list = new LinkedList<>();
    public int value = 10;
    public Timer timer;
    public final int DELAY = 150;

    public Surface() {
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public Timer getTimer() {
        return timer;
    }

    public void doDrawing(Graphics2D pencil) {
        Random rand = new Random();
        pencil.setColor(new Color(Math.abs(rand.nextInt() % 255), Math.abs(rand.nextInt() % 255), Math.abs(rand.nextInt() % 255)));
        Area daire = new Area(new Ellipse2D.Double(getWidth() / 2 - value, getHeight() / 2  - value, value + value, value + value));
        for (Area alan : list)
            daire.subtract(alan);
        pencil.fill(daire);
        list.add(daire);
        value += 10;
    }

    @Override
    protected void paintComponent(Graphics g) {

        doDrawing((Graphics2D) g);

        if (getHeight() > getWidth()) {
            if (value >= getWidth() / 2) {
                value = 10;
                super.paintComponent(g);
                list.removeAll(list);
            }
        } else {
            if (value >= getHeight() / 2) {
                value = 10;
                super.paintComponent(g);
                list.removeAll(list);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
