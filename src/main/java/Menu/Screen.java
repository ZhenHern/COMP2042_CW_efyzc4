package Menu;

import Ball.Ball;
import Bricks.Brick;
import Others.Player;

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

    void drawBrick(Brick brick, Graphics2D g2d);

    void drawBall(Ball ball, Graphics2D g2d);

    void drawPlayer(Player p, Graphics2D g2d);



}
