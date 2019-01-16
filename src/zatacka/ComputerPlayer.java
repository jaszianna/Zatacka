package zatacka;

import javafx.scene.paint.Color;

import java.util.Random;

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

    public static Color RandomColor()
    {
        Random random = new Random();
        int R = random.nextInt(255);
        int G = random.nextInt(255);
        int B = random.nextInt(255);
        return Color.rgb(R,G,B);
    }

    public void Move() {
        if (LeftTurnStack == 0 && RightTurnStack == 0) {
            double Angle = alpha - (Math.PI / 30 * 25);
            int direction = -25;
            int finishdirection = 0;
            double MaxDist = Double.MIN_VALUE;
            while (direction < 26) {
                double dist = ComputeMaximumLength(x, 1000 - y, Angle, 0);
                if (dist > MaxDist) {
                    MaxDist = dist;
                    finishdirection = direction;
                }
                direction++;
                Angle += Math.PI / 30;
            }
            if (finishdirection < 0) RightTurnStack = -finishdirection;
            if (finishdirection > 0) LeftTurnStack = finishdirection;
        }
    }

    public double ComputeMaximumLength(double xPos, double yPos, double angle, int width) {

        angle = -angle;
        MarkedTab = Round.Marked;
        double yxRatio = Math.tan(angle);
        double Xjump = 0;
        if (Math.cos(angle) >= 0) {
            Xjump = 0.3;
        } else {
            Xjump = -0.3;
        }
        double actxPos = xPos;
        double actyPos = yPos;

        double SumXJumps = 0;
        double SumYJumps = 0;

        while (Math.sqrt((SumXJumps) * (SumXJumps) + (SumYJumps) * (SumYJumps)) < 8) {
            actxPos += Xjump;
            actyPos += Xjump * yxRatio;
            SumXJumps += Xjump;
            SumYJumps += Xjump * yxRatio;
        }

        SumXJumps = 0;
        SumYJumps = 0;

        double actxLeftPos = actxPos;
        double actxRightPos = actxPos;
        double actyLeftPos = actyPos;
        double actyRightPos = actyPos;

        Xjump = -Xjump;

        while (Math.sqrt((SumXJumps) * (SumXJumps) + (SumYJumps) * (SumYJumps)) < 6) {
            actxLeftPos += Xjump;
            actxRightPos -= Xjump;
            actyLeftPos += Xjump * yxRatio;
            actyRightPos -= Xjump * yxRatio;
            SumXJumps += Xjump;
            SumYJumps += Xjump * yxRatio;
        }

        Xjump = -Xjump;

        while (true) {

            actxPos += Xjump;
            actyPos += Xjump * yxRatio;
            actxLeftPos += Xjump;
            actxRightPos += Xjump;
            actyLeftPos += Xjump * yxRatio;
            actyRightPos += Xjump * yxRatio;

            int actxLeftPosOnTab = (int) actxLeftPos;
            int actxRightPosOnTab = (int) actxRightPos;
            int actyLeftPosOnTab = 1000 - (int) actyLeftPos;
            int actyRightPosOnTab = 1000 - (int) actyRightPos;
            if (actxLeftPosOnTab < 0 || actyLeftPosOnTab < 0 || actxRightPosOnTab < 0 || actyRightPosOnTab < 0 ||
                    actxLeftPosOnTab >= MarkedTab.length || actxRightPosOnTab >= MarkedTab[0].length ||
                    actyRightPosOnTab >= MarkedTab.length || actyRightPosOnTab >= MarkedTab[0].length)
                break;
            if (MarkedTab[actxLeftPosOnTab][actyLeftPosOnTab] || MarkedTab[actxRightPosOnTab][actyRightPosOnTab])
                break;
        }
        return Math.sqrt((actxPos - xPos) * (actxPos - xPos) + (actyPos - yPos) * (actyPos - yPos));

    }

    @Override
    public Boolean getTurningRight() {
        if (LeftTurnStack > 0) {
            LeftTurnStack--;
            return true;
        }
        return false;
    }

    @Override
    public Boolean getTurningLeft() {
        if (RightTurnStack > 0) {
            RightTurnStack--;
            return true;
        }
        return false;
    }

}
