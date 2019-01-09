package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.util.LinkedList;

public class HighscoresWindow {
    public HighscoresWindow(LinkedList<Player> players) {
        Stage stage = new Stage();
        ObservableList<Player> items = FXCollections.observableArrayList();
        for (Player p : players
        ) {
            items.add(p);
        }
        TableView<Player> tableView = new TableView<>();
        tableView.setPrefSize(400, 600);
        tableView.setOnKeyPressed(event -> stage.close());

        TableColumn<Player, String> name = new TableColumn<>("Player");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setPrefWidth(250);

        TableColumn<Player, Integer> points = new TableColumn<>("Points");
        points.setCellValueFactory(new PropertyValueFactory<>("points"));
        points.setPrefWidth(150);

        tableView.getColumns().addAll(name, points);
        tableView.setItems(items);

        Pane pane = new Pane();
        pane.getChildren().add(tableView);

        stage.setTitle("Highscores");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(pane));
        stage.centerOnScreen();
        stage.show();
    }
}
