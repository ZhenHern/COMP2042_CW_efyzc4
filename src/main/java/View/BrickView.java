package View;

import Controller.BrickController;

import java.awt.*;

public class BrickView {

    public BrickView(){

    }

    /**
     * method to draw the bricks
     * @param brick bricks that form the wall
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void drawBrick(BrickController brick, Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }
}
