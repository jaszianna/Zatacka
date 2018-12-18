package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class HumanPlayer extends Player{

    private KeyCode Left;
    private KeyCode Right;
    public HumanPlayer(double x, double y, double r, double alpha, Color color, KeyCode left, KeyCode right) {
        super(x, y, r, alpha, color);
        Left=left;
        Right=right;
    }

    public KeyCode getLeft() {
        return Left;
    }

    public void setLeft(KeyCode left) {
        Left = left;
    }

    public KeyCode getRight() {
        return Right;
    }

    public void setRight(KeyCode right) {
        Right = right;
    }
}
