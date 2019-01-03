package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements Runnable
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

    public void setStageCount(int stageCount) {
        this.stageCount = stageCount;
    }

    public void run()
    {
        while(stageCount != maxStageCount)
        {
            stage = new Stage(players, gc);
            try
            {
                stage.Play();
            }
            catch (InterruptedException e)
            {
                // TO DO LATER
            }
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
