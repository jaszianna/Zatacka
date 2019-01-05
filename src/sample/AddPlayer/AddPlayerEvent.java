package sample.AddPlayer;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import sample.Player;

public class AddPlayerEvent implements EventHandler<ActionEvent>
{
    private ObservableList<Player> PlayersList;

    public AddPlayerEvent(ObservableList<Player> playersList) {
        PlayersList = playersList;
    }

    @Override
    public void handle(ActionEvent event)
    {
    AddPlayerWindow window=new AddPlayerWindow(PlayersList);
    }
}
