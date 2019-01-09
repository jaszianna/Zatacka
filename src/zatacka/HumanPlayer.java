package zatacka;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class HumanPlayer extends Player
{
    private KeyCode Left;
    private KeyCode Right;
    private Boolean turningRight;
    private Boolean turningLeft;


    public HumanPlayer(String Name, int x, int y, Color color, KeyCode left, KeyCode right) {
        super(Name, x, y, color);
        Left=left;
        Right=right;
        turningRight = false;
        turningLeft = false;
    }

    public KeyCode getLeft() {
        return Left;
    }

    public KeyCode getRight() {
        return Right;
    }

    public Boolean getTurningRight()
    {
        return turningRight;
    }

    public void setTurningRight(Boolean turningRight)
    {
        this.turningRight = turningRight;
    }

    public Boolean getTurningLeft()
    {
        return turningLeft;
    }

    public void setTurningLeft(Boolean turningLeft)
    {
        this.turningLeft = turningLeft;
    }
}
