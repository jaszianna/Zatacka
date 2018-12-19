package sample;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class KeyPressed implements EventHandler<KeyEvent>
{
    List<HumanPlayer> players;
    KeyPressed(List<HumanPlayer> players)
    {
        this.players = players;
    }

    @Override
    public void handle(KeyEvent event) {
        for(HumanPlayer p:players)
        {
            if(event.getCode() == p.getLeft())
            {
                p.setTurningLeft(true);
            }
            if(event.getCode()== p.getRight())
            {
                p.setTurningRight(true);
            }
        }
    }
}


