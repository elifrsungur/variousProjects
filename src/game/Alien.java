package game;

import java.awt.geom.Rectangle2D;

public class Alien {
    private double xPoint = 0;
    private double yPoint = 0;
    private double width;
    private double height;
    private boolean visible;
    private boolean touch = false;
    private int red;
    private int green;
    private int blue;
    private int speed=1;

    public Alien(double height, double width, double xPoint, double yPoint) {
        this.width = width;
        this.height = height;
        this.xPoint = xPoint;
        this.yPoint = yPoint;
        visible = true;
    }

    public void move() {
        if (yPoint >= height - 48)
            touch = true;
        if (yPoint <= 0)
            touch = false;
        if (!touch)
            yPoint+=speed;
        else
            yPoint-=speed;
    }
    public void setSpeed(int speed){
        this.speed=speed;
    }

    public void setRGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public int[] getRed(){
        int[] rgb= new int[]{red,green,blue};
        return rgb;
    }

    public boolean isVisible() {
        return visible;
    }

    public double getXPoint() {
        return xPoint;
    }

    public double getYPoint() {
        return yPoint;
    }

    protected void setVisible(Boolean visible){
        this.visible=visible;
    }

    public Rectangle2D.Double getBound(){
        return new Rectangle2D.Double(xPoint,yPoint,20,20);
    }
}