package sample.AddPlayer;

        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import sample.Player;

        import java.util.LinkedList;

public class AddPlayerEvent implements EventHandler<ActionEvent>
{
    LinkedList<Player> PlayersList;

    public AddPlayerEvent(LinkedList<Player> playersList) {
        PlayersList = playersList;
    }

    @Override
    public void handle(ActionEvent event)
    {
    AddPlayerWindow window=new AddPlayerWindow(PlayersList);
}
}
