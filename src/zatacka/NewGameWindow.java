package zatacka;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewGameWindow
{
    private ObservableList<Player> items;
    private ListView<Player> listView;
    private Stage primaryStage;
    private Game game;
    private TextField roundsNumber;
    private Slider velocity;
    private Scene scene;

    public NewGameWindow(Game g, Scene s)
    {
        this.game = g;
        scene = s;
        items = FXCollections.observableArrayList();
        items.add(new HumanPlayer("Tomek", 1000, 1000, Color.GRAY, KeyCode.LEFT, KeyCode.RIGHT));
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
        addPlayerButton.setOnAction(event -> new AddPlayerWindow(items));

        Button removePlayerButton = new Button();
        removePlayerButton.setText("Remove");
        removePlayerButton.setPrefSize(130, 30);
        removePlayerButton.setLayoutX(235);
        removePlayerButton.setLayoutY(550);
        removePlayerButton.disableProperty().bind(Bindings.isNull(listView.getSelectionModel().selectedItemProperty()));
        removePlayerButton.setOnAction(event -> items.remove(listView.getSelectionModel().getSelectedItem()));

        Button addBotButton = new Button();
        addBotButton.setText("Add Bot");
        addBotButton.setPrefSize(130, 30);
        addBotButton.setLayoutX(420);
        addBotButton.setLayoutY(550);
        addBotButton.setOnAction(event -> {items.add(new ComputerPlayer("Computer " + Player.maxID, 1000, 1000, ComputerPlayer.RandomColor()));});

        Label RoundsNumberLab = new Label("Round Count:");
        RoundsNumberLab.setLayoutX(50);
        RoundsNumberLab.setLayoutY(600);

        roundsNumber = new TextField();
        roundsNumber.setLayoutX(50);
        roundsNumber.setLayoutY(620);
        roundsNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                roundsNumber.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        Label velocityLabel = new Label("Veloctiy:");
        velocityLabel.setLayoutX(50);
        velocityLabel.setLayoutY(660);

        velocity = new Slider();
        velocity.setPrefSize(500,50);
        velocity.setLayoutX(50);
        velocity.setLayoutY(680);
        velocity.setMax(50);
        velocity.setMin(10);
        velocity.setSnapToTicks(true);
        velocity.setBlockIncrement(10);
        velocity.setShowTickLabels(true);
        velocity.setShowTickMarks(true);
        velocity.setMajorTickUnit(10);
        velocity.setMinorTickCount(0);

        Button createGameButton = new Button("Create Game");
        createGameButton.setLayoutX(50);
        createGameButton.setLayoutY(800);
        createGameButton.setPrefSize(500,40);
        createGameButton.setOnAction(e -> CreateGameEventHandler());
        createGameButton.disableProperty().bind(new BooleanBinding() {
            {
                super.bind(roundsNumber.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return roundsNumber.getText().isEmpty();
            }
        });

        Button cancelButton = new Button("Close");
        cancelButton.setLayoutY(840);
        cancelButton.setLayoutX(50);
        cancelButton.setPrefSize(500,40);
        cancelButton.setOnAction(event -> primaryStage.close());

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(addPlayerButton, listView, createGameButton,
                RoundsNumberLab, roundsNumber, velocityLabel,
                removePlayerButton, addBotButton,
                velocity ,cancelButton);

        primaryStage.setTitle("New Game");
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();
    }
    private void CreateGameEventHandler()
    {
        game.getHighscore().clear();
        primaryStage.close();
        game.ClearProperties();
        game.setMaxStageCount(Integer.parseInt(roundsNumber.getText()));
        for(int i = 0; i < items.size(); i++)
        {
            items.get(i).setVelocity(velocity.getValue());
            game.AddPlayers(items.get(i));
        }
        scene.setOnKeyReleased(new KeyReleased(game.getPlayers()));
        scene.setOnKeyPressed(new KeyPressed(game.getPlayers()));
        Thread thread = new Thread(game);
        thread.start();
    }
}
