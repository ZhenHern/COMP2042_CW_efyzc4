package View;

import Controller.BallController;

import java.awt.*;

public class BallView {

    public BallView(){

    }

    /**
     * method to draw the ball
     * @param ball ball which is used to break the bricks
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void drawBall(BallController ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }
}
