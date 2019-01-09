package sample;

import javafx.scene.paint.Color;

public class ComputerPlayer extends Player {

    private int LeftTurnStack;
    private int RightTurnStack;
    private static Boolean[][] MarkedTab;

    public ComputerPlayer(String n, int width, int height, Color color) {
        super(n, width, height, color);
        LeftTurnStack = 0;
        RightTurnStack = 0;
        MarkedTab = Round.Marked;
    }

    public void Move()
    {
        if(LeftTurnStack == 0 && RightTurnStack == 0)
        {
            double Angle = alpha-(Math.PI/30*25);
            int direction =- 25;
            int finishdirection = 0;
            double MaxDist = Double.MIN_VALUE;
            while (direction < 26)
            {
                double dist = ComputeMaximumLength(x,1000-y,Angle,0);
                if(dist > MaxDist)
                {
                    MaxDist = dist;
                    finishdirection = direction;
                }
                direction ++;
                Angle += Math.PI/30;
            }
            if(finishdirection < 0)RightTurnStack =- finishdirection;
            if(finishdirection > 0)LeftTurnStack = finishdirection;
        }
    }

    public double ComputeMaximumLength(double xPos, double yPos, double angle,int width)
    {
        angle =- angle;
        MarkedTab = Round.Marked;
        double yxRatio = Math.tan(angle);
        double Xjump = 0;
        if(Math.cos(angle) >= 0)
        {
            Xjump = 0.3;
        }
        else
        {
            Xjump =- 0.3;
        }


        double actxPos = xPos;
        double actyPos = yPos;

        double SumXJumps = 0;
        double SumYJumps = 0;
        while(Math.sqrt((SumXJumps) * (SumXJumps) + (SumYJumps) * (SumYJumps)) < 15)
        {
            actxPos += Xjump;
            actyPos += Xjump * yxRatio;
            SumXJumps += Xjump;
            SumYJumps += Xjump * yxRatio;
        }

        while(true)
        {
            actxPos += Xjump;
            actyPos += Xjump*yxRatio;
            int actxPosOnTab = (int)actxPos;
            int actyPosOnTab = 1000 - (int)actyPos;
            if(actxPosOnTab < 0 || actyPosOnTab < 0 || actxPosOnTab >= MarkedTab.length || actyPosOnTab >= MarkedTab[0].length)break;
            if(MarkedTab[actxPosOnTab][actyPosOnTab])break;
        }
        return Math.sqrt((actxPos - xPos) * (actxPos - xPos) + (actyPos - yPos) * (actyPos - yPos));
    }

    @Override
    public Boolean getTurningRight() {
        if(LeftTurnStack > 0)
        {
            LeftTurnStack --;
            return true;
        }
        return false;
    }

    @Override
    public void setTurningRight(Boolean turningRight) {

    }

    @Override
    public Boolean getTurningLeft() {
        if(RightTurnStack > 0)
        {
            RightTurnStack --;
            return true;
        }
        return false;
    }

    @Override
    public void setTurningLeft(Boolean turningLeft) {

    }
}
