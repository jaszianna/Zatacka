package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.AddPlayer.AddPlayerWindow;

public class NewGameWindow
{
    private ObservableList<Player> items;
    private ListView<Player> listView;
    private Stage primaryStage;
    private Game game;
    private TextField roundsNumber;
    private TextField velocity;
    private Scene scene;

    public NewGameWindow(Game g,Scene s)
    {
        this.game = g;
        scene = s;
        items = FXCollections.observableArrayList();
        items.add(new HumanPlayer("Tomek", 1000, 1000, Color.ALICEBLUE, KeyCode.LEFT, KeyCode.RIGHT));
        items.add(new HumanPlayer("Mikołaj", 1000, 1000, Color.CRIMSON, KeyCode.A, KeyCode.D));

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
        addPlayerButton.setText("Add");
        addPlayerButton.setPrefSize(130,30);
        addPlayerButton.setLayoutY(550);
        addPlayerButton.setLayoutX(50);
        addPlayerButton.setOnAction(e->AddPlayerEventHandler());

        Button removePlayerButton = new Button();
        removePlayerButton.setText("Remove");
        removePlayerButton.setPrefSize(130, 30);
        removePlayerButton.setLayoutX(235);
        removePlayerButton.setLayoutY(550);
        removePlayerButton.disableProperty().bind(Bindings.isNull(listView.getSelectionModel().selectedItemProperty()));

        Button button = new Button();
        button.setText("Add Bot");
        button.setPrefSize(130, 30);
        button.setLayoutX(420);
        button.setLayoutY(550);

        Label RoundsNumberLab = new Label("Round Count:");
        RoundsNumberLab.setLayoutX(50);
        RoundsNumberLab.setLayoutY(600);

        roundsNumber = new TextField();
        roundsNumber.setLayoutX(50);
        roundsNumber.setLayoutY(620);
        roundsNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    roundsNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Label velocityLabel = new Label("Veloctiy:");
        velocityLabel.setLayoutX(50);
        velocityLabel.setLayoutY(660);

        velocity = new TextField();
        velocity.setLayoutX(50);
        velocity.setLayoutY(680);
        velocity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    velocity.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Button createGameButton = new Button("Create Game");
        createGameButton.setLayoutX(50);
        createGameButton.setLayoutY(800);
        createGameButton.setPrefSize(500,40);
        createGameButton.setOnAction(e -> CreateGameEventHandler());
        createGameButton.disableProperty().bind(new BooleanBinding() {
            {
                super.bind(roundsNumber.textProperty(),
                        velocity.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return roundsNumber.getText().isEmpty() || velocity.getText().isEmpty();
            }
        });

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(addPlayerButton, listView, createGameButton,
                RoundsNumberLab, roundsNumber, velocityLabel,
                velocity, removePlayerButton, button);

        primaryStage.setTitle("New Game");
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
    private void CreateGameEventHandler()
    {
        primaryStage.close();
        game.ClearProperties();
        game.setStageCount(Integer.parseInt(roundsNumber.getText()));
        for(int i = 0; i < items.size(); i++)
        {
            items.get(i).setVelocity(Integer.parseInt(velocity.getText()));
            game.AddPlayers(items.get(i));
        }
        scene.setOnKeyReleased(new KeyReleased(game.getPlayers()));
        scene.setOnKeyPressed(new KeyPressed(game.getPlayers()));
        Thread thread = new Thread(game);
        thread.start();
    }
    private void AddPlayerEventHandler()
    {
        AddPlayerWindow window=new AddPlayerWindow(items);
    }

}
