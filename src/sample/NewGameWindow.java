package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.AddPlayer.AddPlayerEvent;

import java.util.LinkedList;

public class NewGameWindow {

    private LinkedList<Player> PlayersList;

    public NewGameWindow()
    {
        PlayersList=new LinkedList<>();
    }

    public void InitialiseWindow()
    {
        Button AddPlayer = new Button("Add Player");
        AddPlayer.setOnAction(new AddPlayerEvent(PlayersList));

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(AddPlayer);

        Scene secondScene = new Scene(secondaryLayout, 230, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("New Game");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(200);
        newWindow.setY(100);

        newWindow.show();
    }
}
