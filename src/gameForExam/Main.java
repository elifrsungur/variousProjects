package gameForExam;

import javax.swing.*;

public class Main extends JFrame {
    public Main(){
        initUI();
    }

    private void initUI() {
        Surface surface = new Surface();
        add(surface);
        setTitle("hello");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main (String[] args){
        Main main = new Main();
        main.setVisible(true);
    }
}
