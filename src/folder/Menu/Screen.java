package folder.Menu;

import folder.Ball.Ball;
import folder.Bricks.Brick;
import folder.Player;

import java.awt.*;


/**
 * Refactor: Interface "folder.Menu" is created
 * for every menu class to implement.
 */
public interface Screen {

    public void initialize();

    public void clear(Graphics2D g2d);

    public void drawText(Graphics2D g2d);

    public void drawButton(Graphics2D d2s);

    public void drawBrick(Brick brick, Graphics2D g2d);

    public void drawBall(Ball ball, Graphics2D g2d);

    public void drawPlayer(Player p, Graphics2D g2d);



}
