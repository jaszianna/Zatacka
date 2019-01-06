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
    private Scene sc;
    private Canvas canvas;
    private GraphicsContext gc;


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
        NewGameBtn.setOnAction(e->NewGameEv());
        pane2.getChildren().addAll(l1,l2,NewGameBtn);

        pane.getItems().addAll(pane1, pane2);
        root.getChildren().add(pane);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Our Zatacka!!!");
        Group root = new Group();
        MakeCanvasAndGraphicsContext(1000, 1000, root);

        sc = new Scene(root);

        primaryStage.setScene(sc);
        primaryStage.show();
        game = new Game(1, gc);
        SplitPane pane = new SplitPane();
        pane.setOrientation(Orientation.HORIZONTAL);

        AnchorPane pane1 = new AnchorPane();
        pane1.getChildren().add(canvas);

        VBox pane2 = new VBox();

        Label l1=new Label("Mikołaj - 0");
        Label l2=new Label("Tomek - 0");

        Button NewGameBtn= new Button("New Game");
        NewGameBtn.setOnAction(e->NewGameEv());
        pane2.getChildren().addAll(l1,l2,NewGameBtn);

        pane.getItems().addAll(pane1, pane2);

        root.getChildren().add(pane);
    }

    public void NewGameEv()
    {
        NewGameWindow window = new NewGameWindow(game,sc);
    }
}