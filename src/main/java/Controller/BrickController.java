package Controller;

import Model.Bricks.BrickModel;
import View.BrickView;


import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

/**
 * Created by filippo on 04/09/16.
 * abstract class for BrickModel
 */
abstract public class BrickController  {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    private Shape brickFace;

    private int fullStrength;
    private int strength;

    private boolean broken;

    private BrickModel brModel;
    private BrickView brView;



    /**
     * class constructor for BrickModel class
     * @param name name of brick
     * @param pos coordinate of the brick
     * @param size size of the brick (dimension)
     * @param border border color of brick
     * @param inner inner color of brick
     * @param strength hit point of brick
     */
    public BrickController(String name, Point pos,Dimension size,Color border,Color inner,int strength){

        broken = false;
        brModel = new BrickModel(name, border,inner);
        brView = new BrickView();
        setBrickFace(makeBrickFace(pos,size));

        this.fullStrength = this.strength = strength;


    }

    /**
     * method to update the view of brick
     * @param brick BrickController object
     * @param g2d Graphics2D object
     */
    public void updateView(BrickController brick, Graphics2D g2d){
        this.brView.drawBrick(brick,g2d);
    }


    /**
     * abstract method to create a brick object
     * @param pos coordinate of brick
     * @param size size of brick (dimension)
     * @return return the brick object created
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * method to call impact() method
     * @param point coordinate of the impact
     * @param dir direction of the impact
     * @return return false if brick broken to make sure there is no impact when brick is already broken,
     * return  broken after impact
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * method to get brick shape
     * @return brick shape
     */
    public abstract Shape getBrick();


    /**
     * method to get brick border color
     * @return brick border color
     */
    public Color getBorderColor(){
        return  brModel.getBorderColor();
    }

    /**
     * method to get brick inner color
     * @return brick inner color
     */
    public Color getInnerColor(){
        return brModel.getInnerColor();
    }


    /**
     * method to find the direction of the impact between brick and ball
     * @param b ball object
     * @return impact integers to differentiate the impact direction
     */
    public final int findImpact(BallController b){
        if(broken)
            return 0;
        int out  = 0;
        if(getBrickFace().contains(b.getRight())){
            out = LEFT_IMPACT;
        }
        else if(getBrickFace().contains(b.getLeft())){
            out = RIGHT_IMPACT;
        }
        else if(getBrickFace().contains(b.getUp())){
            out = DOWN_IMPACT;
        }
        else if(getBrickFace().contains(b.getDown())){
            out = UP_IMPACT;
        }

        return out;
    }

    /**
     * method to check whether the brick is broken
     * @return broken boolean
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * method to replace the broken bricks
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * method to destroy brick or reduce hit point of brick after impact
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }


    public Shape getBrickFace() {
        return brickFace;
    }

    public void setBrickFace(Shape brickFace) {
        this.brickFace = brickFace;
    }
}





