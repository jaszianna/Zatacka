package sample;

import javafx.scene.paint.Color;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Player {
    static int maxID = 0;
    protected int myID;
    protected double x;
    protected double y;
    protected double velocity = 3;
    protected double alpha;
    protected Color color;
    protected Boolean hasLost = false;
    protected int points;
    protected LinkedBlockingQueue<Integer> messageQueue;
    protected String name;

    public Player(String n, int width, int height, Color color) {
        name = n;
        myID = maxID;
        maxID += 1;
        SetRandomPosition(width, height);
        this.color = color;
        points = 0;
        messageQueue = null;
    }

    public String getName() {
        return name;
    }

    public int getMyID() {
        return myID;
    }

    public void setHasLost(Boolean hasLost) {
        this.hasLost = hasLost;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public Color getColor() {
        return color;
    }

    public void AddMessageQueue(LinkedBlockingQueue queue) {
        messageQueue = queue;
    }

    public Boolean IfLose(Boolean[][] Marked, double x, double y, double width, double Alpha) throws InterruptedException {
        for (double beta = Alpha - Math.PI / 4; beta < Alpha + Math.PI / 4; beta += Math.PI / 10) {
            double x1 = x + width * Math.cos(beta);
            double y1 = y + width * Math.sin(beta);
            if (x1 <= 0 || y1 <= 0 || x1 >= Marked.length - 1 || y1 >= Marked[0].length - 1 || Marked[(int) Math.round(x1)][(int) Math.round(y1)]) {

                hasLost = true;
                messageQueue.put(myID);
                return true;
            }
        }
        return false;
    }

    public Boolean getHasLost() {
        return hasLost;
    }

    public void MarkOnTab(Boolean[][] Marked, double x, double y, double r) {
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

    public void AddPoint() {
        points += 1;
    }

    public void ResetPoints() {
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void SetRandomPosition(int width, int height) {
        Random r = new Random();
        x = r.nextInt(width - 200) + 100;
        y = r.nextInt(height - 200) + 100;
        alpha = r.nextDouble() * Math.PI * 2;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public abstract Boolean getTurningRight();

    public abstract void setTurningRight(Boolean turningRight);

    public abstract Boolean getTurningLeft();

    public abstract void setTurningLeft(Boolean turningLeft);
}
