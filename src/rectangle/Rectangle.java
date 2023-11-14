package rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Surface extends JPanel{
    private double width;
    private double height;
    private double sqx;
    private double sqy;
    private double sqx2;
    private double sqy2;

    private void doDrawing(Graphics pencil){
        width=getWidth();
        height=getHeight();
        sqx = 0;
        sqy = 0;
        sqx2 += width / 10;
        sqy2 += height / 8;

        Graphics2D pen = (Graphics2D) pencil.create();

        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        pen.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);


        float[] dash= {4f, 0f, 2f};

        BasicStroke bs2 = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND,
                1.0f, dash, 2f);
        pen.setStroke(bs2);

        Random rand = new Random();


        for(sqy = 0; sqy <= height ; sqy+=height/8){
            for (sqx = 0 ; sqx <= width ; sqx+= width/10){

                float red = rand.nextFloat();   //Math.abs/random.nextInt()%255
                float green = rand.nextFloat();
                float blue = rand.nextFloat();

                pen.setColor(Color.black);
                pen.drawLine(0, (int) sqy, (int) width, (int) sqy); //row
                pen.drawLine((int) sqx, 0, (int) sqx, (int) height); //column

                pen.setColor(new Color(red, green, blue));
                pen.fillRect((int)sqx,(int)sqy,(int) sqx2 ,(int) sqy2);


               //  pen.drawRect((int)sqx, (int)sqy ,(int) sqx2,(int) sqy2);


            }
        }

    }

    public void paintComponent(Graphics pencil){
        doDrawing(pencil);
    }

}

 public class Rectangle extends JFrame {

    public Rectangle(){
        initUI();
    }

    private void initUI(){

        add(new Surface());
        setTitle("COLORED RECTANGLE");
        setSize(500,450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setVisible(true);
    }

}