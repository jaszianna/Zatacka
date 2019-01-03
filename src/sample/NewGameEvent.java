package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class NewGameEvent implements EventHandler<ActionEvent>
{
    Game game;
    NewGameEvent(Game game){this.game = game;}

    @Override
    public void handle(ActionEvent event)
    {
        try {
            game.Run();
        } catch (InterruptedException e) {

        }
    }
}
