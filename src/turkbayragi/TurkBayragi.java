package turkbayragi;

import javax.swing.*;
import java.awt.*;

class Surface extends JPanel{
    private double height;
    private double width;
    private double uckurlukGenisligi;
    private Color kirmizi = new Color(227, 10, 23);
    private Color beyaz = new Color(225,225,225);

    private void doDrawing(Graphics g) {
        height = getHeight();
        width = getWidth();
        uckurlukGenisligi = (width * 5) / 100;


        Graphics2D g2d = (Graphics2D) g;

        setBackground(kirmizi);

        g2d.setPaint(beyaz); // uçkurluk kısmı
        g2d.fillRect(0, 0, (int) uckurlukGenisligi, (int) height);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);


        ay(g2d);
        yildiz(g);

    }

    private void yildiz(Graphics g){
        g.setColor(beyaz);

        int i, faz, aci, icfaz, xm, ym, dr, ir;
        int koordx[] = new int[10];			        		//Yildizin x koordinati
        int koordy[] = new int[10]; 						//Yildizin y koordinati
        faz = 36; 											//Yildizin donus acisi
        aci = (360 / 5);									//Yildizin uclari arasindaki aci
        icfaz = (aci / 2);									//Yildizin uclari arasindaki aci
        xm = (int)(height * 0.87); 							//Yildiz icin cizilen dairelerden buyuk olanin merkez noktasinin x degeri
        ym = (int)(height * 0.5); 							//Yildiz icin cizilen dairelerden buyuk olanin merkez noktasinin y degeri
        dr = (int)height * 1/8; 							//Yildiz icin cizilen dairelerden buyuk olanin yari capi
        ir = (int)(height * 1/20); 						    //Yildiz icin cizilen dairelerden Kucuk olanin yari capi
        for (i = 0; i < koordx.length; i += 2) {
            koordx[i] = xm + (int) (dr * Math.cos(Math.PI * faz / 180));
            koordx[i + 1] = xm + (int) (ir * Math.cos(Math.PI * (icfaz + faz) / 180));
            koordy[i] = ym + (int) (dr * Math.sin(Math.PI * faz / 180));
            koordy[i + 1] = ym + (int) (ir * Math.sin(Math.PI * (icfaz + faz) / 180));
            faz += aci;
        }
        g.fillPolygon(koordx, koordy, koordx.length);

    }

    private void ay(Graphics2D g2d) {
        g2d.setPaint(beyaz); // ayın dış dairesi
        g2d.fillOval((int) (height * 0.30), (int) (height * 0.25), (int) (height * 0.50), (int) (height * 0.50));


        g2d.setPaint(kirmizi); // ayın iç dairesi
        g2d.fillOval((int) ((height * 0.55) - (height * 0.20 - height * 1 / 16)), (int) (height * 0.30), (int) (height * 0.40), (int) (height * 0.40));


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);

    }

}

public class TurkBayragi extends JFrame {

    public TurkBayragi() {

        add(new Surface());
        double height = 600;
        double width = 1.5 * height;

        setTitle("TÜRK BAYRAĞI");
        setSize((int) width, (int) height);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
       TurkBayragi ex = new TurkBayragi();
       ex.setVisible(true);


    }

}