package Bricks;

import java.awt.*;

/**
 * Refactor: Factory method design pattern is applied here.
 * abstract class Brick is defined for creating the object,
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
    public Brick getBrick(Point point, Dimension size, int brickType){
        if(brickType == 1){
            return new ClayBrick(point,size);
        }
        else if(brickType == 2){
            return new SteelBrick(point,size);
        }
        else if(brickType == 3){
            return new CementBrick(point,size);
        }
        else{
            return null;
        }
    }
}
