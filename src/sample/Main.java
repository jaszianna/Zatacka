package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) { launch(args ); }

    private Game game;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext graphicsContext;


    @Override
    public void start(Stage primaryStage)
    {
        canvas = new Canvas();
        canvas.setWidth(1000);
        canvas.setHeight(1000);

        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Button newGame = new Button("New Game");
        newGame.setOnAction(e->NewGameEvent());
        newGame.setLayoutY(60);
        newGame.setLayoutX(1010);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        anchorPane.getChildren().addAll(canvas, newGame);

        scene = new Scene(anchorPane);

        primaryStage.setTitle("Zatacka");
        primaryStage.setHeight(1000);
        primaryStage.setWidth(1300);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();

        game = new Game(1, graphicsContext);
    }

    public void NewGameEvent()
    {
        NewGameWindow window = new NewGameWindow(game, scene);
    }
}