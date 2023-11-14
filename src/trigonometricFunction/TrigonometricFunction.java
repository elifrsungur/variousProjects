package trigonometricFunction;

import javax.swing.*;
import java.awt.*;

class Surface extends JPanel {
    private double width;
    private double height;
    private double heightOfFrequency = 100;
    private int cycles = 3;
    private int point;
    private double[] points;
    private int[] YCoordinatePoints;


    public void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        width = getWidth();
        height = getHeight();

        g2d.setPaint(Color.BLACK);
        g2d.drawLine((int) (width / 2), (int) (height * 0.1), (int) (width / 2), (int) (height * 0.9)); //düşey koordinant
        g2d.drawLine((int) (width / 2), (int) (height * 0.1), (int) (width / 2 - 10), (int) (height * 0.1 + 10)); // +y ok
        g2d.drawLine((int) (width / 2), (int) (height * 0.1), (int) (width / 2 + 10), (int) (height * 0.1 + 10));
        g2d.drawLine((int) (width / 2), (int) (height * 0.9), (int) (width / 2 - 10), (int) (height * 0.9 - 10)); // -y ok
        g2d.drawLine((int) (width / 2), (int) (height * 0.9), (int) (width / 2 + 10), (int) (height * 0.9 - 10));
        g2d.drawLine((int) (width * 0.1), (int) (height / 2), (int) (width * 0.9), (int) (height / 2)); //yatay koordinant
        g2d.drawLine((int) (width * 0.9), (int) (height / 2), (int) (width * 0.9 - 10), (int) (height / 2 + 10)); // +x ok
        g2d.drawLine((int) (width * 0.9), (int) (height / 2), (int) (width * 0.9 - 10), (int) (height / 2 - 10));
        g2d.drawLine((int) (width * 0.1), (int) (height / 2), (int) (width * 0.1 + 10), (int) (height / 2 - 10)); //-x
        g2d.drawLine((int) (width * 0.1), (int) (height / 2), (int) (width * 0.1 + 10), (int) (height / 2 + 10));

        point = 180 * (cycles) * 2; //kaç tane noktadan oluşacak çizgiler
        points = new double[point];

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        tan();
        drawingWave(g2d, Color.blue);
        cot();
        drawingWave(g2d, Color.green);
        sin();
        drawingWave(g2d, Color.PINK);
        cos();
        drawingWave(g2d, Color.RED);

        g2d.setColor(Color.black);
        float[] dash = {2f, 2f, 2f};
        BasicStroke bs = new BasicStroke(5, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 1.0f, dash, 0f);
        g2d.setStroke(bs);
        g2d.drawLine((int) (width / 2), (int) (height * 0.1), (int) (width / 2), (int) (height * 0.9));
        g2d.drawLine((int) (width * 0.1), (int) (height / 2), (int) (width * 0.9), (int) (height / 2));

    }

    private void sin() {
        for (int i = 0; i < point; i++) {
            points[i] = Math.sin((Math.PI / 180) * i);
        }
    }

    private void cos() {
        for (int i = 0; i < point; i++) {
            points[i] = Math.cos((Math.PI / 180) * i);
        }
    }

    private void tan() {
        for (int i = 0; i < point; i++) {
            points[i] = Math.tan((Math.PI / 180) * i);
        }
    }

    private void cot() {
        for (int i = 0; i < point; i++) {
            points[i] = 1 / Math.tan((Math.PI / 180) * i);
        }
    }

    private void drawingWave(Graphics2D g2d, Color color) {
        g2d.setPaint(color);
        YCoordinatePoints = new int[point];
//        Y koordinat sisteminin yuksekligini
        for (int i = 0; i < point; i++)
            YCoordinatePoints[i] = (int) (points[i] * heightOfFrequency + height / 2);
//        cizgi olusturumak
        for (int i = 1; i < point; i++)
            g2d.drawLine((int) ((i - 1) * width / point), YCoordinatePoints[i - 1], (int) (i * width / point), YCoordinatePoints[i]);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}

public class TrigonometricFunction extends JFrame {
    public TrigonometricFunction() {
        initUI();
    }

    private void initUI() {
        Surface surface = new Surface();
        add(surface);
        setTitle("Trigonometrik Fonksiyon Grafikleri");
        setSize(900, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        TrigonometricFunction trigonometricFunction = new TrigonometricFunction();
        trigonometricFunction.setVisible(true);
    }


}
