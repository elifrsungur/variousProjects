package gameForExam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Surface extends JPanel implements ActionListener {
    private int width;
    private int height;
    private int degree = 0;
    private int tableX;
    private final int DELAY = 50;
    private Color color;
    private Random random;

    public Surface() {
        initTimer();
        initKey();
    }

    public void initKey() {
        addKeyListener(new TAdapter());
        setFocusable(true);
    }

    public void initTimer() {
        Timer timer = new Timer(DELAY, this);
        timer.start();
        random = new Random();
        setColor();

    }

    public void doDrawing(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        width = getWidth();
        height = getHeight();
        g2D.setColor(Color.black);
        if(tableX==0)
            tableX=width/2;
        Rectangle2D table = new Rectangle2D.Double(tableX, height - 10, 60, 10);
        g2D.fill(table);

        Rectangle2D rect = new Rectangle2D.Double(0, 0, 150, 10);
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(degree), width / 2, height / 2);
        tx.translate(width / 2 - 75, height / 2 - 5);
        GeneralPath gP = new GeneralPath();
        gP.append(tx.createTransformedShape(rect), false);
        g2D.setColor(color);
        g2D.fill(gP);

        Rectangle2D rect2 = new Rectangle2D.Double(0, 0, 80, 10);
        AffineTransform tx2 = new AffineTransform();
        tx2.rotate(Math.toRadians(-degree), width / 3, height / 2);
        tx2.translate(width / 3 - 40, height / 2 - 5);
        GeneralPath gP2 = new GeneralPath();
        gP2.append(tx2.createTransformedShape(rect2), false);
        g2D.fill(gP2);

        AffineTransform tx3 = new AffineTransform();
        tx3.rotate(Math.toRadians(degree), width - width / 3, height / 2);
        tx3.translate(width - width / 3 - 40, height / 2 - 5);
        GeneralPath gP3 = new GeneralPath();
        gP3.append(tx3.createTransformedShape(rect2), false);
        g2D.fill(gP3);





    }

    private void setColor() {
        color = new Color(Math.abs(random.nextInt()) % 256, Math.abs(random.nextInt()) % 256, Math.abs(random.nextInt()) % 256);
    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        if (degree == 360) {
            degree = 0;
            setColor();
        }
        degree += 5;

        Random rand = new Random();
        g2D.setColor(new Color(Math.abs(rand.nextInt() % 255), Math.abs(rand.nextInt() % 255), Math.abs(rand.nextInt() % 255)));
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    tableX-=5;
                    break;
                case KeyEvent.VK_RIGHT:
                    tableX+=5;
                    break;
            }
        }
    }

}



