package newBall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.Random;

class Surface extends JPanel implements ActionListener {
    private final double DEGREE = 45;
    private final double GRAVITY = 9.81;
    private final int DELAY = 20;
    private double width;
    private double height;
    private double xBall = 0;
    private double yBall = 0;
    private double distance = 0;
    private double speed;
    private double lastSpeed = 0;
    private double timeCounter = 0;
    private float alpha_rectangle;
    private boolean intersects = false;
    private Rectangle2D rect;
    private Image catapult;
    private Image bucket;
    private Image image;
    private Timer timer;
    private Graphics2D g2d;

    public Surface() {
        alpha_rectangle = 1f;
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(DELAY, this);
        timer.start();
        try {
            image = new ImageIcon("background.jpeg").getImage();
            bucket = new ImageIcon("bucketIcon2.png").getImage();
            catapult = new ImageIcon("catapultIcon.png").getImage();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void doDrawing() {

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        width = getWidth();
        height = getHeight();

        g2d.drawImage(image,0,0,(int) width,(int) height,this);

        if (distance == 0)
            distance = getRandom() - height * 0.15;
        if (xBall <= 0) {
            xBall = height * 0.055;
            yBall = height * 0.85;
        }
        speed = Math.sqrt((distance - height * 0.115) * GRAVITY / (2 * Math.cos(Math.PI / 180 * DEGREE) * Math.sin(Math.PI / 180 * DEGREE))) / 10;
        lastSpeed = speed * Math.sin(Math.PI / 180 * DEGREE) - GRAVITY * timeCounter;

        dimensionAndInfo();
        rect = new Rectangle2D.Double(distance, height * 0.85, height * 0.15, height * 0.1);
        g2d.drawImage(catapult, (int) (height * 0.05), (int) (height * 0.727), 280, 200, this);
        g2d.setPaint(Color.red);
        g2d.fill(new Ellipse2D.Double(xBall, yBall, height * 0.025, height * 0.025));
        xBall += Math.cos(Math.PI / 180 * DEGREE) * speed;
        yBall -= lastSpeed;
        timeCounter += 0.01;
        g2d.setPaint(Color.black);
        catapultDesign();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha_rectangle));
        g2d.fill(rect);
        g2d.dispose();
    }

    private void dimensionAndInfo() {
        g2d.drawString("      Speed: " + speed + " m/s", 20, 20);
        g2d.draw(new Line2D.Double(height * 0.05, height * 0.05, height * 0.05, height * 0.95));
        g2d.draw(new Line2D.Double(height * 0.05, height * 0.95, width * 0.95, height * 0.95));
    }

    public void catapultDesign() {
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(-10), height * 0.02 + 180, height * 0.73 + 120);
        tx.translate(height * 0.02, height * 0.73);
        g2d.drawImage(bucket, tx, this);
    }

    private double getRandom() {
        double randomNum;
        while ((randomNum = new Random().nextInt() % width) <= width * 0.35) ;
        return randomNum;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        doDrawing();
        if (yBall >= height * 0.925)
            restore();
        if (rect.intersects(xBall, yBall, height * 0.01, height * 0.01))
            intersects = true;
        if (intersects) {
            alpha_rectangle += -0.04f;
            if (alpha_rectangle < 0)
                alpha_rectangle = 0;
        }
    }

    public void restore() {
        xBall = 0;
        yBall = 0;
        distance = 0;
        lastSpeed = 0;
        timeCounter = 0;
        alpha_rectangle = 1f;
        intersects = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}

public class NewBall extends JFrame {
    public NewBall() {
        initUI();
    }

    private void initUI() {
        Surface surface = new Surface();
        add(surface);
        setTitle("Hedef Top");
        setSize(1020, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                NewBall newBall= new NewBall();
                newBall.setVisible(true);
            }
        });
    }

}