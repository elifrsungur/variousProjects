package ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;

class Surface extends JPanel implements ActionListener {
    private double width;
    private double height;
    private double ballX = 0;
    private double ballY = 0;
    private double degree = 60;
    private double speed = 8;
    private double timeCounter = 0;
    private double speedY;
    private double speedX;
    private double diameter;
    private int round = 3;
    private boolean touch = false;
    private boolean start = false;
    private Timer timer;
    private JTextField degreeTextBox;
    private JTextField speedTextBox;
    private JTextField roundTextBox;
    private JLabel degreeLabel;
    private JLabel speedLabel;
    private JLabel roundLabel;
    private JButton startButton;
    private Shadow shadow;
    private ArrayList<Shadow> shadowList = new ArrayList<Shadow>();

    public Surface() {
        initTimer();
        initAdd();
    }

    private void initAdd() {
        speedTextBox = new JTextField(5);
        degreeTextBox = new JTextField(5);
        roundTextBox = new JTextField(5);
        degreeLabel = new JLabel("Açı");
        roundLabel = new JLabel("Tekrar");
        speedLabel = new JLabel("Hız");
        startButton = new JButton("Başla");
        add(degreeLabel);
        add(degreeTextBox);
        add(speedLabel);
        add(speedTextBox);
        add(roundLabel);
        add(roundTextBox);
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!speedTextBox.getText().isEmpty() && !degreeTextBox.getText().isEmpty() && !roundTextBox.getText().isEmpty()) {
                    degree = Integer.valueOf(degreeTextBox.getText());
                    speed = Integer.valueOf(speedTextBox.getText());
                    round = Integer.valueOf(roundTextBox.getText());
                    if (!start) {
                        start = true;
                        timer.start();
                    }
                }
            }
        });
    }

    private void initTimer() {
        timer = new Timer(30, this);
    }

    private void doDrawing(Graphics g) {
        width = getWidth();
        height = getHeight();
        Random rand = new Random();
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();

        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.drawLine((int) (width * 0.05), (int) (height * 0.05), (int) (width * 0.05), (int) (height * 0.95));
        g2d.drawLine((int) (width * 0.05), (int) (height * 0.95), (int) (width * 0.95), (int) (height * 0.95));
        diameter = height * 0.08;
        if (ballY == 0 && ballX == 0) { //topun ilk konumu
            ballX = width * 0.05;
            ballY = height * 0.95 - diameter;
        }
        speedY = speed * Math.sin(Math.PI / 180 * degree) - (9.81 * timeCounter); //V=V0+gt
        speedX = speed * Math.cos(Math.PI / 180 * degree);

        g2d.setColor(new Color(red, green, blue));
        g2d.fillOval((int) (ballX), (int) (ballY), (int) (diameter), (int) (diameter));
        if (!touch)
            ballY -= speedY;
        ballX += speedX;
        timeCounter += 0.01;
     //  shadow = new Shadow(ballX, ballY, Math.sin(Math.PI / 180 * degree));
     //  shadow.setOpaque(false);
     //  shadow.setBounds(0, 0, getWidth(), getHeight());
     //  add(shadow);
     //  shadowList.add(shadow);
    }

    private void startAgain() {
        ballY = 0;
        ballX = 0;
        timeCounter = 0;

        for (Shadow temp : shadowList) {
            remove(temp);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        if (start) {
            if (ballY + diameter > height * 0.95) {
                if (round == 1) {
                    start = false;
                } else {
                    touch = true;
                    if (ballX + height * 0.1> width) {
                        round--;
                        touch = false;
                        startAgain();
                    }
                }
            }
            super.paintComponent(g);
            doDrawing(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (start) {
            repaint();
        }
    }

    class Shadow extends JPanel {
        private double ballX;
        private double ballY;
        private double hizAci;

        public Shadow(double ballX, double ballY, double hizAci) {
            this.ballX = ballX;
            this.ballY = ballY;
            this.hizAci = hizAci;
        }

        private void doDrawing(Graphics2D g) {
            Random rand = new Random();
            float red = rand.nextFloat();
            float green = rand.nextFloat();
            float blue = rand.nextFloat();
            Graphics2D g2d = g;
            g2d.setColor(new Color(red, green, blue));
            Line2D line2D = new Line2D.Double(ballX + getHeight() * 0.05, ballY + getHeight() * 0.05,
                    ballX + getHeight() * 0.05, ballY + getHeight() * 0.05 - (hizAci % 2));
            g.draw(line2D);
            Ellipse2D ellipse2D = new Ellipse2D.Double(ballX , ballY , diameter, diameter);
            g.draw(ellipse2D);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            doDrawing((Graphics2D) g);
        }
    }

    public static class Ball extends JFrame{
        public Ball() {
            initUI();
        }

        private void initUI() {
            Surface surface = new Surface();
            add(surface);
            setTitle("Hareketli Top");
            setSize(900, 600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public static void main(String[] args) {
            Ball st = new Ball();
            st.setVisible(true);
        }
    }
}