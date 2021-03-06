package zatacka;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow extends Application {

    public static void main(String[] args) { launch(args ); }

    private Game game;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Thread thread;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage)
    {
        canvas = new Canvas();
        canvas.setWidth(1000);
        canvas.setHeight(1000);

        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> NewGameEvent());
        newGameButton.setLayoutY(900);
        newGameButton.setLayoutX(1010);
        newGameButton.setPrefSize(280, 40);

        Button button = new Button();
        button.setLayoutY(940);
        button.setLayoutX(1010);
        button.setPrefSize(280,40);
        button.setText("Exit");
        button.setOnAction(event -> primaryStage.close());

        ListView listView = new ListView();
        listView.setLayoutY(50);
        listView.setLayoutX(1010);
        listView.setPrefSize(280,500);
        listView.setCellFactory(param -> new ListCell<Player>()
        {
            @Override
            protected void updateItem(Player item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null)
                {
                    setText(null);
                } else {
                    setTextFill(item.getColor());
                    setText(item.getName());
                }
            }
        });

        Label players = new Label();
        players.setText("Players:");
        players.prefWidth(100);
        players.setFont(Font.font(18));
        players.setLayoutX(1010);
        players.setLayoutY(20);

        Button button1 = new Button("Stop");
        button1.setLayoutY(700);
        button1.setLayoutX(1100);
        button1.setPrefSize(100,100);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                game.stop();
            }
        });

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        anchorPane.getChildren().addAll(canvas, newGameButton, listView, button, players, button1);

        scene = new Scene(anchorPane);
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setTitle("Zatacka");
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1300);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();

        game = new Game(1, graphicsContext);
        listView.setItems(game.getHighscore());
    }

    public void NewGameEvent()
    {
        NewGameWindow window = new NewGameWindow(game, scene);
    }
}