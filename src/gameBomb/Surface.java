package gameBomb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Random;

public class Surface extends JPanel implements ActionListener {

    private int width;
    private int height;
    private int bombX;
    private int rectX;
    private int rectY;
    private int alienX;
    private int alienFirstY;
    private int degree = 90 ;
    private int timeCounter = 0;
    private Timer timer;
    private int DELAY=20;
    private Graphics2D g2d;
    private Rectangle2D rect;
    private boolean isAlien=false;
    private ArrayList<Shot> shots = new ArrayList<>();
    private ArrayList<Area> aliens;
    private ArrayList<Double> xList;

    public Surface(){
        initStart();
        initTimer();

    }
    private void initStart(){
        addKeyListener(new TAdapter());
        setFocusable(true);
    }

    private void initTimer(){
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void doDrawing(){
        height = getHeight();
        width = getWidth();

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        bomb();
        fireGun();
    }

    private void bomb(){

        bombX = (int) (width * 0.2);
        g2d.fill(new Ellipse2D.Double(width/2 - bombX /2, height- bombX /2 , bombX, bombX));
    }

    private void fireGun(){
        rectX = (int)(width * 0.03);
        rectY = (int)(width * 0.12);
        rect = new Rectangle2D.Double(0, 0, rectX,rectY);
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(degree),width/2 ,height-20);
        tx.translate(width/2 -rectX/2  , height - 100);
        GeneralPath path = new GeneralPath();
        path.append(tx.createTransformedShape(rect), false);
        g2d.fill(path);
    }

    private void gun(){

    }

    private void alien(){
        double number = 0;
        aliens = new ArrayList<>();
        alienX = (int)(width*0.03);
        alienFirstY = 0;

      //  while (xList.size() != 10) {
            g2d.fill(new Ellipse2D.Double(getRandom(), alienFirstY, alienX, alienX));
           // alienFirstY++;
      //      }

    }

    private double getRandom() {
        double randomNum;
        randomNum = new Random().nextInt() % width;
        return randomNum;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.g2d = (Graphics2D) g;
        doDrawing();

        for (Shot shot : shots){
            if( shot.getShotY()<0 || shot.getShotX()>width || shot.getShotX()<0 )
                shots.remove(shot);
        }

        for(Shot shot : shots){
            g2d.setColor(Color.red);
            g2d.fillOval(shot.getShotX(), shot.getShotY(),10,10);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

       for (Shot shot : shots ){
           shot.setShotY(shot.getShotY()-(int) (10 * Math.sin(Math.PI / 180 * degree) - 9.81 * (timeCounter += 0.01)));
           shot.setShotX(shot.getShotX()+(int)(Math.cos(Math.PI / 180 * degree) * 10));
       }
        repaint();
    }

    class Shot{
        private int shotX;
        private int shotY;

        public Shot(){
            shotX = (int)(width / 2 - 5 + 40 * Math.cos(Math.PI / 180 * degree));
            shotY = (int)(height - 75 - 40 * Math.sin(Math.PI / 180 * degree));
        }
        public int getShotX(){
            return shotX;
        }
        public void setShotX(int shotX){
            this.shotX = shotX;
        }
        public int getShotY(){
            return shotY;
        }
        public void setShotY(int shotY){
            this.shotY = shotY;
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(degree > -90)
                    degree-=5; break;
                case KeyEvent.VK_RIGHT:
                    if(degree < 90)
                    degree+=5; break;
                case KeyEvent.VK_UP:
                    shots.add(new Shot());
            }
        }
    }
}

