package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class Game implements Runnable
{
    private LinkedList<Player> players;
    private Round stage;
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
            stage = new Round(players, gc,15);
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
    public Round getStage() {
        return stage;
    }

    public LinkedList<Player> getPlayers() { return players; }

    public void ClearProperties()
    {
        players= new LinkedList<Player>();
        stageCount=0;
        stage=null;
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    public void setMaxStageCount(int maxStageCount) {
        this.maxStageCount = maxStageCount;
    }
}
