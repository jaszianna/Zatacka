package zatacka;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game implements Runnable {
    private LinkedList<Player> players;
    private Round round;
    private int maxRoundCount;
    private int roundCount;
    private final AtomicBoolean run = new AtomicBoolean(true);
    private GraphicsContext gc;
    private ObservableList<Player> highscore;


    public Game(int maxRoundCount, GraphicsContext graphicsContext) {
        highscore = FXCollections.observableArrayList();
        gc = graphicsContext;
        players = new LinkedList<Player>();
        this.maxRoundCount = maxRoundCount;
        roundCount = 0;
    }

    public void stop()
    {
        run.set(false);
    }

    public void run() {
        while (roundCount != maxRoundCount && run.get() == true)
        {
            round = new Round(players, gc, 15);
            try {

                round.Play();
            }
            catch (InterruptedException e) {
            }
            roundCount++;
        }
        Platform.runLater
                (
                        () -> {
                            HighscoresWindow highscoresWindow = new HighscoresWindow(players);
                        }
                );
    }

    public void AddPlayers(Player... players) {
        for (Player p : players) {
            this.players.add(p);
            highscore.add(p);
        }
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public void ClearProperties() {
        players = new LinkedList<Player>();
        roundCount = 0;
        round = null;
        run.set(true);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    public void setMaxRoundCount(int maxRoundCount) {
        this.maxRoundCount = maxRoundCount;
    }

    public ObservableList<Player> getHighscore() {
        return highscore;
    }
}
