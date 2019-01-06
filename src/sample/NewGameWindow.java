package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.AddPlayer.AddPlayerWindow;

public class NewGameWindow
{
    private ObservableList<Player> items;
    private ListView<Player> listView;
    private Stage primaryStage;
    private Game game;
    private TextField RoundsNumber;
    private TextField Velocity;
    private Scene sc;


    public NewGameWindow(Game g,Scene s)
    {
        this.game=g;
        sc=s;
        items = FXCollections.observableArrayList();
        primaryStage = new Stage();
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
        addPlayerButton.setOnAction(e->AddPlayerEventHandler());

        Button CreateGameButton=new Button("Create Game");
        CreateGameButton.setLayoutX(20);
        CreateGameButton.setLayoutY(800);
        CreateGameButton.setOnAction(e->CreateGameEventHandler());

        Label RoundsNumberLab = new Label("Round Count:");
        RoundsNumberLab.setLayoutX(50);
        RoundsNumberLab.setLayoutY(600);

        RoundsNumber=new TextField();
        RoundsNumber.setLayoutX(50);
        RoundsNumber.setLayoutY(620);

        RoundsNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    RoundsNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Label VelocityLab = new Label("Veloctiy:");
        VelocityLab.setLayoutX(50);
        VelocityLab.setLayoutY(660);

        Velocity=new TextField();
        Velocity.setLayoutX(50);
        Velocity.setLayoutY(680);

        Velocity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    Velocity.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(addPlayerButton, listView, CreateGameButton,RoundsNumberLab,RoundsNumber, VelocityLab, Velocity);

        primaryStage.setTitle("New Game");
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
    private void CreateGameEventHandler()
    {
        primaryStage.close();
        game.ClearProperties();
        game.setStageCount(Integer.parseInt(RoundsNumber.getText()));
        for(int i=0;i<items.size();i++)
        {
            items.get(i).setVelocity(Integer.parseInt(Velocity.getText()));
            game.AddPlayers(items.get(i));
        }
        sc.setOnKeyReleased(new KeyReleased(game.getPlayers()));
        sc.setOnKeyPressed(new KeyPressed(game.getPlayers()));
        Thread thread = new Thread(game);
        thread.start();
    }
    private void AddPlayerEventHandler()
    {
        AddPlayerWindow window=new AddPlayerWindow(items);
    }

}
