package folder.Bricks;

import folder.Ball.Ball;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by filippo on 04/09/16.
 * abstract class for Brick
 */
abstract public class Brick  {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;





    private static Random rnd;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * class constructor for Brick class
     * @param name name of brick
     * @param pos coordinate of the brick
     * @param size size of the brick (dimension)
     * @param border border color of brick
     * @param inner inner color of brick
     * @param strength hit point of brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;


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
        return  border;
    }

    /**
     * method to get brick inner color
     * @return brick inner color
     */
    public Color getInnerColor(){
        return inner;
    }


    /**
     * method to find the direction of the impact between brick and ball
     * @param b ball object
     * @return impact integers to differentiate the impact direction
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;
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



}





