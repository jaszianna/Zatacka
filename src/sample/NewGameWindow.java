package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.AddPlayer.AddPlayerEvent;

import java.util.LinkedList;

public class NewGameWindow
{

    private ObservableList<Player> items;
    private ListView<Player> listView;


    public NewGameWindow()
    {
        items = FXCollections.observableArrayList();
        Stage primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.setWidth(600);
        primaryStage.setHeight(900);

        listView = new ListView<>();
        listView.setLayoutX(50);
        listView.setLayoutY(30);
        listView.setPrefSize(500,500);
        listView.setCellFactory(param -> new ListCell<Player>()
        {
            @Override
            protected void updateItem(Player item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null)
                {
                    setText(null);
                } else {
                    setText(item.getName());
                    setTextFill(item.getColor());
                }
            }
        });
        listView.setItems(items);

        Button addPlayerButton = new Button();
        addPlayerButton.setText("Add Player");
        addPlayerButton.setLayoutY(560);
        addPlayerButton.setLayoutX(200);
        addPlayerButton.setOnAction(new AddPlayerEvent(items));

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(addPlayerButton, listView);

        primaryStage.setTitle("New Game");
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
