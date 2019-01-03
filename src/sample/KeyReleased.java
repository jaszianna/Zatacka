package sample;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class KeyReleased implements EventHandler<KeyEvent>
{
    List<Player> players;
    KeyReleased(List<Player> players)
    {
        this.players = players;
    }

    @Override
    public void handle(KeyEvent event) {
        for(Player p:players)
        {
            if(event.getCode()== ((HumanPlayer)p).getLeft())
            {
                ((HumanPlayer)p).setTurningLeft(false);
            }
            if(event.getCode()== ((HumanPlayer)p).getRight())
            {
                ((HumanPlayer)p).setTurningRight(false);
            }
        }
    }
}