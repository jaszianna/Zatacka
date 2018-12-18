package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
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

    public void MakeMarkedTab(int width, int height) {
        Marked = new Boolean[width][];
        for (int i = 0; i < width; i++) {
            Marked[i] = new Boolean[height];
            for (int j = 0; j < height; j++) {
                Marked[i][j] = false;
            }
        }
    }

    public void MarkOnTab(double x, double y, double r) {
        int i0 = x - r > 0 ? (int) (x - r) : 0;
        int j0 = y - r > 0 ? (int) (y - r) : 0;
        int i1 = x + r < Marked.length ? (int) (x + r) : Marked.length;
        int j1 = y + r < Marked[0].length ? (int) (y + r) : Marked[0].length;
        for (int i = i0; i < i1; i++) {
            for (int j = j0; j < j1; j++) {
                if ((i - x) * (i - x) + (j - y) * (j - y) <= r * r)
                    Marked[i][j] = true;
            }
        }
    }

    public Boolean IfLose(double x, double y, double width, double Alpha) {
        for (double beta = Alpha - Math.PI / 2; beta < Alpha + Math.PI / 2; beta += Math.PI / 10) {
            double x1 = x + width * Math.cos(beta);
            double y1 = y + width * Math.sin(beta);
            if (Marked[(int) Math.round(x1)][(int) Math.round(y1)]) {
                return true;
            }
        }
        return false;
    }

    public void MakeCanvasAndGraphicsContext(int width, int height, Group root) {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        root.getChildren().add(canvas);
    }

    public List<HumanPlayer> PlayerList;

    public void InitialiseTwoDefaultPlayers() {
        PlayerList = new LinkedList<HumanPlayer>();
        PlayerList.add(new HumanPlayer(10, 10, 4, 0, Color.GREEN, KeyCode.LEFT, KeyCode.RIGHT));
        PlayerList.add(new HumanPlayer(100, 100, 4, 2, Color.BLUE, KeyCode.A, KeyCode.D));
    }

    TimerTask task = new TimerTask() {
        public void run() {
            for (HumanPlayer hp : PlayerList) {
                if (!hp.getLoser()) {
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
    };

    @Override
    public void start(Stage primaryStage) {
        MakeMarkedTab(1000, 1000);

        InitialiseTwoDefaultPlayers();

        primaryStage.setTitle("Uwaga Kurwa!!!");
        Group root = new Group();
        MakeCanvasAndGraphicsContext(1000, 1000, root);
        Timer timer = new Timer();
        timer.schedule(task, 10, 50);
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();
        sc.setOnKeyPressed(new KeyPressed(PlayerList));
    }
}