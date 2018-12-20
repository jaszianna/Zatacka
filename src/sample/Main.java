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

    public Boolean Marked[][];

    public Canvas canvas;

    public GraphicsContext gc;

    public List<HumanPlayer> PlayerList;

    public KeyPressed KeyPress;

    public void MakeMarkedTab(int width, int height) {
        Marked = new Boolean[width][];
        for (int i = 0; i < width; i++) {
            Marked[i] = new Boolean[height];
            for (int j = 0; j < height; j++) {
                Marked[i][j] = false;
            }
        }
    }

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
        pane2.getChildren().addAll(l1,l2,NewGameBtn);

        pane.getItems().addAll(pane1, pane2);



        root.getChildren().add(pane);


    }

    public void InitialiseTwoDefaultPlayers() {
        PlayerList = new LinkedList<HumanPlayer>();
        PlayerList.add(new HumanPlayer(400, 400, 0, Color.GREEN, KeyCode.LEFT, KeyCode.RIGHT));
        PlayerList.add(new HumanPlayer(100, 100, 0, Color.BLUE, KeyCode.A, KeyCode.D));
    }

    TimerTask task = new TimerTask() {
        public void run() {
            if(!(KeyPress==null) && !KeyPress.getPaused())
            {
                for (HumanPlayer hp : PlayerList) {
                    if (!hp.getHasLost()) {
                        hp.setX(hp.getX() + hp.getVelocity() * Math.cos(hp.getAlpha()));
                        hp.setY(hp.getY() + hp.getVelocity() * Math.sin(hp.getAlpha()));
                        gc.setFill(hp.getColor());
                        gc.fillRoundRect(hp.getX() + 5, hp.getY() + 5, 10, 10, 10, 10);
                        if (!hp.IfLose(Marked, hp.getX() + 5, hp.getY() + 5, 5, hp.getAlpha())) {
                            hp.MarkOnTab(Marked, hp.getX() + 5, hp.getY() + 5, 5);
                        }
                    }
                }
            }
        }
    };
    TimerTask makingATurn = new TimerTask()
    {
        public void run()
        {
            if(!(KeyPress==null) && !KeyPress.getPaused())
            for (HumanPlayer hp : PlayerList)
            {
                if(hp.getTurningLeft() == true)
                    hp.setAlpha(hp.getAlpha() - Math.PI / 15);
                if(hp.getTurningRight() == true)
                    hp.setAlpha(hp.getAlpha() + Math.PI / 15);
            }
        }
    };


    @Override
    public void start(Stage primaryStage) {
        MakeMarkedTab(1000, 1000);

        InitialiseTwoDefaultPlayers();

        primaryStage.setTitle("Our Zatacka!!!");
        Group root = new Group();
        MakeCanvasAndGraphicsContext(1000, 1000, root);

        Timer timer = new Timer();
        Timer makingATurnTimer = new Timer();
        makingATurnTimer.schedule(makingATurn, 10, 50);
        timer.schedule(task, 10, 20);
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();
        sc.setOnKeyReleased(new KeyReleased(PlayerList));
        KeyPress=new KeyPressed(PlayerList);
        sc.setOnKeyPressed(KeyPress);
    }
}