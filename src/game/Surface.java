package game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Surface extends JPanel implements ActionListener {
    private double width = 500;
    private double height = 400;
    private Timer timer;
    private Graphics2D g2d;
    private final int DELAY = 50;
    private int degree = 90;
    private FireGun fireGun;
    private List<Alien> aliens;
    private boolean startGame = true;
    private int lifeCounter = 1;
    private ArrayList<Double> xList;
    private ArrayList<Double> yList;
    private Random random;


    public Surface() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        this.timer = new Timer(DELAY, this);
        this.timer.start();
        this.fireGun = new FireGun(500, 400, degree);
        initAliens();
    }

    private void initAliens() {
        aliens = new ArrayList<>();
        random = new Random();
        double number;
        xList = new ArrayList();
        yList = new ArrayList();
        while (xList.size() != 10) {
            while (xList.contains(number = Math.abs(random.nextInt() % 450))) ;
            xList.add(number);
        }
        while (yList.size() != 10) {
            while (yList.contains(number = -(Math.abs(random.nextInt() % 400)))) ;
            yList.add(number);
        }
        for (int i = 0; i < xList.size(); i++) {
            int speed;
            Alien alien = new Alien(400, 500, xList.get(i), yList.get(i));
            alien.setSpeed((speed = Math.abs(random.nextInt()) % 5) == 0 ? 1 : speed);
            alien.setRGB(Math.abs(random.nextInt()) % 256, Math.abs(random.nextInt()) % 256, Math.abs(random.nextInt()) % 256);
            aliens.add(alien);
        }
    }

    private void doDrawing() {
        width = getWidth();
        height = getHeight();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.drawString("Derece: " + fireGun.getDegree() + "˚", 380, 20);
        g2d.drawString("Skor: " + fireGun.getScore() , 380, 40);
        g2d.fill(fireGun.fireGunDesign());
        g2d.fill(new Ellipse2D.Double(width / 2 - 55, height - 50, 110, 100)); //fireGun table
        g2d.setPaint(Color.red);
        if (!fireGun.gameStart())
            drawGameOver();

        //        fires the ball
        List<Fires> firesList = fireGun.getFireList();
        for (Fires fire : firesList)
            if (fire.isVisible())
                this.g2d.fill(new Ellipse2D.Double(fire.getXBall(), fire.getYBall(), 10, 10));
        for (Alien alien : aliens) {
            if (alien.isVisible())
                this.g2d.setPaint(new Color(alien.getRed()[0], alien.getRed()[1], alien.getRed()[2]));
            this.g2d.fill(new Ellipse2D.Double(alien.getXPoint(), alien.getYPoint(), 20, 20));
        }
    }

    private void drawGameOver() {
        String msg;
        if (aliens.isEmpty())
            msg = "KAZANDIN! PUANIN: " + fireGun.getScore() ;
        else
            msg = "KAYBETTİN!";
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics fm = getFontMetrics(small);
        this.g2d.setColor(Color.red);
        this.g2d.setFont(small);
        this.g2d.drawString(msg, (int) (width / 2 - fm.stringWidth(msg) / 2), (int) (height / 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g2d = (Graphics2D) g;
        if (startGame) {
            doDrawing();
        } else {
                drawGameOver();
        }
        Toolkit.getDefaultToolkit().sync();
        if (fireGun.getScore() < 15 && lifeCounter <= 3) {
            lifeCounter++;
            fireGun.setScore(150);
            initAliens();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!startGame)
            this.timer.stop();

        List<Fires> fires = this.fireGun.getFireList();
        for (int i = 0; i < fires.size(); i++) {
            Fires m = fires.get(i);
            if (m.isVisible())
                m.move();
            else
                fires.remove(i);
        }

        if (this.aliens.isEmpty()) {
            this.startGame = false;
            return;
        }
        for (int i = 0; i < this.aliens.size(); i++) {
            Alien a = this.aliens.get(i);
            if (a.isVisible())
                a.move();
            else
                aliens.remove(i);
        }

        for (Fires fire : fires) {
            Rectangle2D r1 = fire.getBound();
            for (Alien alien : aliens) {
                Rectangle2D r2 = alien.getBound();
                if (r1.intersects(r2)) {
                    fireGun.incrementScore(10);
                    fire.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            fireGun.keyPressed(e);
        }
    }
}
