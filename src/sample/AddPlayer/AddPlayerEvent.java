package sample.AddPlayer;

        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;

public class AddPlayerEvent implements EventHandler<ActionEvent>
{
    @Override
    public void handle(ActionEvent event)
    {
        AddPlayerWindow window=new AddPlayerWindow();
    }
}
