package View;

import Controller.PlayerController;

import java.awt.*;

public class PlayerView {

    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    public PlayerView(){

    }

    /**
     * method to draw the player rectangle.
     * @param p player object which can move left and right
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void drawPlayer(PlayerController p, Graphics2D g2d, Color c){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(c);
        g2d.fill(s);

        g2d.setColor(Color.black);
        g2d.draw(s);

        g2d.setColor(tmp);
    }
}
