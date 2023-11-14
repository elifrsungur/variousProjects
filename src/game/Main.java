package game;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        initUI();
    }

    private void initUI() {
        final Surface surface=new Surface();
        setTitle("GAME");
        setSize(500,400);
        add(surface);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Main main=new Main();
        main.setVisible(true);
    }
}
