package gameBomb;

import javax.swing.*;


public class GameBomb extends JFrame {
    public GameBomb() {

        initUI();
    }

    private void initUI() {
        Surface surface = new Surface();
        add(surface);

        setSize(500, 400);
        setResizable(false);

        setTitle("Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

           GameBomb gameBomb = new GameBomb();
           gameBomb.setVisible(true);
    }
}