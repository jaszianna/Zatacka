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
        NewGameWindow window=new NewGameWindow();
        window.InitialiseWindow();
        //Thread thread = new Thread(game);
        //thread.start();
    }
}
