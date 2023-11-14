package game;

import java.awt.event.KeyEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class FireGun {
    private int score = 150;
    private Boolean gameStart = true;
    private List<Fires> fireList;
    private double width;
    private double height;
    private double degree;

    public FireGun(double width, double height, double degree) {
        this.width = width;
        this.height = height;
        this.degree = degree;
        fireGunDesign();
        fireList = new ArrayList<>();
    }

    public GeneralPath fireGunDesign() {
        Area fireGun = new Area(new Rectangle2D.Double(0, 0, 70, 20));
        AffineTransform tx = new AffineTransform();
        tx.rotate(-Math.toRadians(degree), width / 2, height - 68);
        tx.translate(width / 2 - 15, height - 78);
        GeneralPath path = new GeneralPath();
        path.append(tx.createTransformedShape(fireGun), false);
        return path;
    }

    protected double getDegree() {
        return degree;
    }

    private void fire() {
        if (gameStart && score > 0) {
            fireList.add(new Fires(width, height, degree));
            score -= 15;
        } else
            gameStart = false;
    }

    protected int getScore() {
        return score;
    }

    protected void setScore(int score){
        this.score=score;
    }

    protected void incrementScore(int increment){
        this.score+=increment;
    }

    protected boolean gameStart() {
        return gameStart;
    }

    public List<Fires> getFireList() {
        return fireList;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                if (degree < 180);
                degree+=5; break;
            case KeyEvent.VK_RIGHT:
                if (degree > 0);
                degree -= 5; break;
            case  KeyEvent.VK_UP :
                fire(); break;
        }

    }

}
