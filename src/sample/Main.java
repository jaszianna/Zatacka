package sample;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private Game game;

    public Canvas canvas;

    public GraphicsContext gc;

    public KeyPressed KeyPress;

    public void MakeCanvasAndGraphicsContext(int width, int height, Group root) {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        SplitPane pane = new SplitPane();
        pane.setOrientation(Orientation.HORIZONTAL);

        AnchorPane pane1 = new AnchorPane();
        pane1.getChildren().add(canvas);

        VBox pane2 = new VBox();

        Label l1=new Label("Mikołaj - 0");
        Label l2=new Label("Tomek - 0");

        Button NewGameBtn= new Button("New Game");
        NewGameBtn.setOnAction(new NewGameEvent(game));
        pane2.getChildren().addAll(l1,l2,NewGameBtn);

        pane.getItems().addAll(pane1, pane2);



        root.getChildren().add(pane);


    }

    public void InitialiseTwoDefaultPlayers()
    {
        game = new Game(1, gc);
        game.AddPlayers(new HumanPlayer(400, 400, 0, Color.GREEN, KeyCode.LEFT, KeyCode.RIGHT),
                new HumanPlayer(100, 100, 0, Color.BLUE, KeyCode.A, KeyCode.D));
    }


    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Our Zatacka!!!");
        Group root = new Group();
        MakeCanvasAndGraphicsContext(1000, 1000, root);

        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();
        InitialiseTwoDefaultPlayers();
        sc.setOnKeyReleased(new KeyReleased(game.getPlayers()));
        sc.setOnKeyPressed(new KeyPressed(game.getPlayers()));
        SplitPane pane = new SplitPane();
        pane.setOrientation(Orientation.HORIZONTAL);

        AnchorPane pane1 = new AnchorPane();
        pane1.getChildren().add(canvas);

        VBox pane2 = new VBox();

        Label l1=new Label("Mikołaj - 0");
        Label l2=new Label("Tomek - 0");

        Button NewGameBtn= new Button("New Game");
        NewGameBtn.setOnAction(new NewGameEvent(game));
        pane2.getChildren().addAll(l1,l2,NewGameBtn);

        pane.getItems().addAll(pane1, pane2);



        root.getChildren().add(pane);

    }
}