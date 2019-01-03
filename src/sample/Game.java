package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Game
{
    private LinkedList<Player> players;
    private Stage stage;
    private int maxStageCount;
    private int stageCount;
    private GraphicsContext gc;

    public Game(int maxStageCount, GraphicsContext graphicsContext)
    {
        gc = graphicsContext;
        players = new LinkedList<Player>();
        this.maxStageCount = maxStageCount;
        stageCount = 0;
    }
    public void Run() throws InterruptedException {
        stage = new Stage(players, gc);
        while(stageCount != maxStageCount)
        {
            stage = new Stage(players, gc);
            stage.Play();
            stageCount++;
        }
    }
    public void AddPlayers(Player... players)
    {
        for (Player p: players)
        {
            this.players.add(p);
        }
    }
    public Stage getStage() {
        return stage;
    }
    public LinkedList<Player> getPlayers() { return players; }




}
