package sample;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import java.util.List;

public class KeyPressed implements EventHandler<KeyEvent>
{
    List<Player> players;

    public Boolean getPaused() {
        return Paused;
    }
    Boolean Paused=true;
    KeyPressed(List<Player> players)
    {
        this.players = players;
    }

    @Override
    public void handle(KeyEvent event) {
        //TODO - pause
//        if(event.getCode() == KeyCode.ENTER)
//        {
//            Paused = Paused == true?false:true;
//        }
        for(Player p:players)
        {
            if(p.getClass().getName() == "sample.HumanPlayer")
            {
                if (event.getCode() == ((HumanPlayer) p).getLeft()) {
                    p.setTurningLeft(true);
                }
                if (event.getCode() == ((HumanPlayer) p).getRight()) {
                    p.setTurningRight(true);
                }
            }
        }
    }
}


