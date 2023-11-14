package circles;

import javax.swing.*;
import java.awt.event.*;


public class Circles extends JFrame {

    public Circles() {
        initUI();
    }

    public void initUI() {
        Surface surface = new Surface();
        add(surface);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
            }
        });
        setTitle("Circle");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        Circles circle = new Circles();
        circle.setVisible(true);
    }
}

