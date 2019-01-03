package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

public class Stage
{
    private LinkedList<Player> activePlayers;
    public Boolean Marked[][];
    private GraphicsContext gc;
    private LinkedBlockingQueue<Integer> queue;

    public Stage(LinkedList<Player> activePlayers, GraphicsContext graphicsContext)
    {
        this.activePlayers = (LinkedList<Player>) activePlayers.clone();
        gc = graphicsContext;
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        MakeMarkedTab((int)gc.getCanvas().getWidth(), (int)gc.getCanvas().getHeight());
        queue = new LinkedBlockingQueue<Integer>();
        for (Player p:activePlayers
             ) {
            p.AddMessageQueue(queue);
            p.SetRandomPosition((int)gc.getCanvas().getWidth(), (int)gc.getCanvas().getHeight());
            p.setHasLost(false);
        }
    }
    public LinkedList<Player> getActivePlayers()
    {
        return activePlayers;
    }
    public void MakeMarkedTab(int width, int height) {
        Marked = new Boolean[width][];
        for (int i = 0; i < width; i++) {
            Marked[i] = new Boolean[height];
            for (int j = 0; j < height; j++) {
                Marked[i][j] = false;
            }
        }
    }
    public void Play() throws InterruptedException {
        Timer makeAMoveTimer = new Timer();
        Timer makingATurnTimer = new Timer();
        makingATurnTimer.schedule(makingATurn, 0, 50);
        makeAMoveTimer.schedule(makingAMove, 0, 20);
        while(activePlayers.size() > 0)
        {
            int msg = queue.take();
            for (int i = 0; i < activePlayers.size(); i++)
            {
                if (activePlayers.get(i).getMyID() == msg)
                {
                    activePlayers.remove(i);
                    break;
                }
            }
            for (int i = 0; i < activePlayers.size(); i++)
            {
                activePlayers.get(i).AddPoint();
            }
        }
    }

    TimerTask makingAMove = new TimerTask() {
        public void run() {
            for (Player player : activePlayers) {
                if (!player.getHasLost()) {
                    player.setX(player.getX() + player.getVelocity() * Math.cos(player.getAlpha()));
                    player.setY(player.getY() + player.getVelocity() * Math.sin(player.getAlpha()));
                    gc.setFill(player.getColor());
                    gc.fillRoundRect(player.getX() + 5, player.getY() + 5, 10, 10, 10, 10);
                    try {
                        if (!player.IfLose(Marked, player.getX() + 5, player.getY() + 5, 5, player.getAlpha())) {
                            player.MarkOnTab(Marked, player.getX() + 5, player.getY() + 5, 5);
                        }
                    } catch (InterruptedException e) {
                        // TO DO LATER
                    }
                }
            }
        }
    };
    TimerTask makingATurn = new TimerTask()
    {
        public void run()
        {
                for (Player player : activePlayers)
                {
                    if(((HumanPlayer)player).getTurningLeft() == true)
                        player.setAlpha(player.getAlpha() - Math.PI / 15);
                    if(((HumanPlayer)player).getTurningRight() == true)
                        player.setAlpha(player.getAlpha() + Math.PI / 15);
                }
        }
    };
}
