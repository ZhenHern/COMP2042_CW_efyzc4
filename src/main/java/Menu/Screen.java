package Menu;

import Controller.BallController;
import Controller.BrickController;
import Model.Ball.Ball;
import Model.Bricks.Brick;
import Model.Player;

import java.awt.*;


/**
 * Refactor: Interface "folder.Menu" is created
 * for every menu class to implement. Open-closed principle
 * is used in this interface for design principle. "Screen" interface
 * is open for other class to implement while it is also closed for
 * modification
 */
public interface Screen {

    void initialize();

    void clear(Graphics2D g2d);

    void drawText(Graphics2D g2d);

    void drawButton(Graphics2D d2s);

    void drawBrick(BrickController brick, Graphics2D g2d);

    void drawBall(BallController ball, Graphics2D g2d);

    void drawPlayer(Player p, Graphics2D g2d);



}
