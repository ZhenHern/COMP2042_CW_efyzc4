package folder;

import java.awt.*;
import java.awt.Point;


/**
 * Created by filippo on 04/09/16.
 * class for ClayBrick which extends Brick class
 *
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay test.Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     * class constructor for CLayBrick
     * @param point coordinate of clay brick
     * @param size dimension of clay brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /**
     * method to create brick shape
     * @param pos  coordinate of brick
     * @param size size of brick (dimension)
     * @return brick shape
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * method to get brick shape
     * @return brick shape
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
