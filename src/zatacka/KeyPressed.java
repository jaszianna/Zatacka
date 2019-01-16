package zatacka;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.List;

public class KeyPressed implements EventHandler<KeyEvent>
{
    List<Player> players;

    KeyPressed(List<Player> players)
    {
        this.players = players;
    }

    @Override
    public void handle(KeyEvent event) {
        for(Player p:players)
        {
            if(p.getClass().getName() == "zatacka.HumanPlayer")
            {
                if (event.getCode() == ((HumanPlayer) p).getLeft()) {
                    ((HumanPlayer)(p)).setTurningLeft(true);
                }
                if (event.getCode() == ((HumanPlayer) p).getRight()) {
                    ((HumanPlayer)(p)).setTurningRight(true);
                }
            }
        }
    }
}


