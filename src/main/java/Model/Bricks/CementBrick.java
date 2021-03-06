package Model.Bricks;

import Controller.BrickController;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * CementBrick class which inherits from BrickModel class
 */
public class CementBrick extends BrickController {


    private static final String NAME = "Cement BrickModel";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;


    /**
     * class constructor for CementBrick
     * @param point coordinate of cement brick
     * @param size dimension of cement brick
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(super.getBrickFace(),BrickController.DEF_CRACK_DEPTH, BrickController.DEF_STEPS);
        brickFace = super.getBrickFace();
    }

    /**
     * method to create brick shape (rectangle)
     * @param pos  coordinate of brick
     * @param size size of brick (dimension)
     * @return brick shape (rectangle)
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * method to call impact() method
     * @param point coordinate of the impact
     * @param dir   direction of the impact
     * @return false if brick is broken
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(brickFace,point,dir);
            updateBrick();
            return false;
        }
        return true;
    }


    /**
     * method to get the brick shape
     * @return brick shape
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * method to update the brick appearance
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.getBrickFace(),false);
            brickFace = gp;
        }
    }

    /**
     * method to repair the brick back to original form
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
    }
}
