package folder;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Crack class
 * Refactor is done by extracting crack class from brick class
 * remove composition of crack class in brick class, making it
 * less complex and easier to read
 */
public class Crack{

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;



    private GeneralPath crack;

    private int crackDepth;
    private int steps;
    private static Random rnd;


    /**
     * Crack class constructor
     * @param brickFace shape for brick (rectangle)
     * @param crackDepth the depth of the brick crack
     * @param steps a parameter used to limit the crack stays inside the brick
     */
    public Crack(Shape brickFace, int crackDepth, int steps){
        rnd = new Random();
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;

    }


    /**
     * draw crack when brick is being updated
     * @return crack's general path
     */
    public GeneralPath draw(){

        return crack;
    }

    /**
     * method to reset the crack path to empty
     */
    public void reset(){
        crack.reset();
    }

    /**
     * method to create the crack on the exact point where the brick is hit
     * @param brickFace brick shape
     * @param point impact point coordinate
     * @param direction direction of brick that gets hit
     */
    protected void makeCrack(Shape brickFace,Point2D point, int direction){
        Rectangle bounds = brickFace.getBounds();

        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();


        switch(direction){
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);

                break;

        }
    }

    /**
     * method to set the crack starting point and ending point
     * @param start crack starting point
     * @param end crack ending point
     */
    protected void makeCrack(Point start, Point end){

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x,start.y);

        double w = (end.x - start.x) / (double)steps;
        double h = (end.y - start.y) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x,y;

        for(int i = 1; i < steps;i++){

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if(inMiddle(i,CRACK_SECTIONS,steps))
                y += jumps(jump,JUMP_PROBABILITY);

            path.lineTo(x,y);

        }

        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    /**
     * method to generate random crack length that is in bound
     * @param bound crack length in bound
     * @return random crack length
     */
    private int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound;
    }


    private boolean inMiddle(int i,int steps,int divisions){
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    private int jumps(int bound,double probability){

        if(rnd.nextDouble() > probability)
            return randomInBounds(bound);
        return  0;

    }

    private Point makeRandomPoint(Point from,Point to, int direction){

        Point out = new Point();
        int pos;

        switch(direction){
            case HORIZONTAL:
                pos = rnd.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos,to.y);
                break;
            case VERTICAL:
                pos = rnd.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x,pos);
                break;
        }
        return out;
    }

}
