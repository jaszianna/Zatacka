package sample;

import javafx.scene.paint.Color;

public class Player
{
private double x;
private double y;
private static double velocity = 4;
private double alpha;
private Color color;
private Boolean hasLost = false;

    public Player(double x, double y, double alpha, Color color)
    {
        this.x = x;
        this.y = y;
        this.alpha = alpha;
        this.color = color;
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

    public Boolean IfLose(Boolean[][] Marked, double x, double y, double width, double Alpha) {
        for(double beta=Alpha-Math.PI/2;beta<Alpha+Math.PI/2;beta+=Math.PI/10)
        {
            double x1=x+width*Math.cos(beta);
            double y1=y+width*Math.sin(beta);
            if(x1<0||y1<0||x1>Marked.length-1||y1>Marked[0].length-1||Marked[(int)Math.round(x1)][(int)Math.round(y1)])
            {
                hasLost =true;
                return true;
            }
        }
        return false;
    }

    public Boolean getHasLost() {
        return hasLost;
    }

    public void MarkOnTab(Boolean[][] Marked, double x, double y, double r) {
        int i0=x-r>0?(int)(x-r):0;
        int j0=y-r>0?(int)(y-r):0;
        int i1=x+r<Marked.length?(int)(x+r):Marked.length;
        int j1=y+r<Marked[0].length?(int)(y+r):Marked[0].length;
        for(int i=i0;i<i1;i++)
        {
            for(int j=j0;j<j1;j++)
            {
                if((i-x)*(i-x)+(j-y)*(j-y)<=r*r)
                    Marked[i][j]=true;
            }
        }
    }
}
