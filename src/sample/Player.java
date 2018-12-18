package sample;

import javafx.scene.paint.Color;

public class Player {
private double x;
private double y;
private double velocity;
private double alpha;
private Color color;
private Boolean Loser=false;

    public Player(double x, double y, double velocity, double alpha, Color color) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
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

    public void setVelocity(double velocity) {
        this.velocity = velocity;
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

    public void setColor(Color color) {
        this.color = color;
    }

    public Boolean IfLose(Boolean[][] Marked, double x, double y, double width, double Alpha) {
        for(double beta=Alpha-Math.PI/2;beta<Alpha+Math.PI/2;beta+=Math.PI/10)
        {
            double x1=x+width*Math.cos(beta);
            double y1=y+width*Math.sin(beta);
            if(Marked[(int)Math.round(x1)][(int)Math.round(y1)])
            {
                Loser=true;
                return true;
            }
        }
        return false;
    }

    public Boolean getLoser() {
        return Loser;
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
