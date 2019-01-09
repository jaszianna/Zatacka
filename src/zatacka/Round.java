package zatacka;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

public class Round {
    private LinkedList<Player> activePlayers;
    public static Boolean Marked[][];
    private GraphicsContext gc;
    private LinkedBlockingQueue<Integer> queue;
    private int MoveTimerPeriod;
    private Random rand;

    public Round(LinkedList<Player> activePlayers, GraphicsContext graphicsContext, int period) {

        MoveTimerPeriod = period;
        this.activePlayers = (LinkedList<Player>) activePlayers.clone();
        gc = graphicsContext;
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        MakeMarkedTab((int) gc.getCanvas().getWidth(), (int) gc.getCanvas().getHeight());
        queue = new LinkedBlockingQueue<Integer>();
        for (Player p : activePlayers) {
            p.AddMessageQueue(queue);
            p.SetRandomPosition((int) gc.getCanvas().getWidth(), (int) gc.getCanvas().getHeight());
            p.setHasLost(false);
        }
        rand = new Random();
    }

    public LinkedList<Player> getActivePlayers() {
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
        makingATurnTimer.schedule(makingATurn, 0, MoveTimerPeriod);
        makeAMoveTimer.schedule(makingAMove, 0, MoveTimerPeriod);
        while (activePlayers.size() > 1) {
            int msg = queue.take();
            for (int i = 0; i < activePlayers.size(); i++) {
                if (activePlayers.get(i).getMyID() == msg) {
                    activePlayers.remove(i);
                    break;
                }
            }
            for (int i = 0; i < activePlayers.size(); i++) {
                activePlayers.get(i).AddPoint();
            }
        }
        makeAMoveTimer.cancel();
        makingATurnTimer.cancel();
        activePlayers.get(0).RemoveLastPlayer();
        Thread.sleep(2000);
    }

    TimerTask makingAMove = new TimerTask() {
        public void run() {


            double r = rand.nextDouble();
            int Pickedindex = -1;
            if (r < 0.01) {
                Pickedindex = rand.nextInt(activePlayers.size());
            }

            for (int i = 0; i < activePlayers.size(); i++) {
                Player player = activePlayers.get(i);

                if (i == Pickedindex) {
                    player.setPickedStack(10 + rand.nextInt(5) * 3);
                }
                if (player.getClass().getName() == "zatacka.ComputerPlayer") {
                    ComputerPlayer p = (ComputerPlayer) player;
                    p.Move();
                }
                if (!player.getHasLost()) {
                    if (player.getPickedStack() > 0) {
                        gc.setFill(Color.BLACK);
                        gc.fillRoundRect(player.getX() + 5, player.getY() + 5, 10, 10, 10, 10);
                    }
                    player.setX(player.getX() + player.getVelocity() / 10 * Math.cos(player.getAlpha()));
                    player.setY(player.getY() + player.getVelocity() / 10 * Math.sin(player.getAlpha()));
                    gc.setFill(player.getColor());
                    gc.fillRoundRect(player.getX() + 5, player.getY() + 5, 10, 10, 10, 10);
                    try {
                        if (!player.IfLose(Marked, player.getX() + 5, player.getY() + 5, 5, player.getAlpha())) {
                            if (player.getPickedStack() <= 0) {
                                player.MarkOnTab(Marked, player.getX() + 5, player.getY() + 5, 5);
                            }

                        }
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    };
    TimerTask makingATurn = new TimerTask() {
        public void run() {
            for (Player player : activePlayers) {
                if (player.getTurningLeft() == true)
                    player.setAlpha(player.getAlpha() - Math.PI / 30);
                if (player.getTurningRight() == true)
                    player.setAlpha(player.getAlpha() + Math.PI / 30);
            }
        }
    };
}
