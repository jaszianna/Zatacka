package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.AddPlayer.AddPlayerEvent;

public class NewGameWindow {

    private ObservableList<Player> PlayersList;
    private ListView<Player> PlayersListView;


    public NewGameWindow()
    {
        PlayersList= FXCollections.observableArrayList();
        PlayersListView=new ListView<>(PlayersList);
        PlayersListView.setCellFactory(param -> new ListCell<Player>() {
            @Override
            protected void updateItem(Player item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                    setTextFill(item.getColor());
                }
            }
        });
    }

    public void InitialiseWindow()
    {

        Button AddPlayer = new Button("Add Player");
        AddPlayer.setOnAction(new AddPlayerEvent(PlayersList));

        VBox Box=new VBox();
        Pane p=new Pane();

        Box.getChildren().addAll(PlayersListView,AddPlayer);
        //StackPane secondaryLayout = new StackPane();
        //secondaryLayout.getChildren().add(AddPlayer);

        Scene secondScene = new Scene(Box, 230, 100);

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
