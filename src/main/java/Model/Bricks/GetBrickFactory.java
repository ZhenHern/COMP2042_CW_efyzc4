package Model.Bricks;

import Controller.BrickController;

import java.awt.*;

/**
 * Refactor: Factory method design pattern is applied here.
 * abstract class BrickModel is defined for creating the object,
 * but the factory decides which class to instantiate
 */
public class GetBrickFactory {

    /**
     * brick is instantiated using this method according to the brick type
     * chosen
     * @param point coordinate of brick
     * @param size dimension of brick
     * @param brickType type of brick
     * @return brick object to be instantiated
     */
    public BrickController getBrick(Point point, Dimension size, brickTypes brickType) {
        switch (brickType) {
            case CLAY:
                return new ClayBrick(point, size);
            case STEEL:
                return new SteelBrick(point, size);
            case CEMENT:
                return new CementBrick(point, size);
            default:
                return null;
        }
    }
}
