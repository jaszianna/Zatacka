package sample;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class KeyReleased implements EventHandler<KeyEvent>
{
    List<HumanPlayer> players;
    KeyReleased(List<HumanPlayer> players)
    {
        this.players = players;
    }

    @Override
    public void handle(KeyEvent event) {
        for(HumanPlayer p:players)
        {
            if(event.getCode()== p.getLeft())
            {
                p.setTurningLeft(false);
            }
            if(event.getCode()== p.getRight())
            {
                p.setTurningRight(false);
            }
        }
    }
}