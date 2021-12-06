package Model.Ball;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by filippo on 04/09/16.
 * class folder.Model.Ball.Model.Ball
 *
 */
public class Ball {

    private Point2D center;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private int speedX;
    private int speedY;

    private Color border;
    private Color inner;

    /**
     * class constructor for folder.Model.Ball.Model.Ball
     * @param center center of the ball
     * @param radius radius of ball
     */
    public Ball(Point2D center,int radius, Color inner, Color border){
        this.center = center;

        setUp(new Point2D.Double());
        setDown(new Point2D.Double());
        setLeft(new Point2D.Double());
        setRight(new Point2D.Double());

        getUp().setLocation(center.getX(),center.getY()-(radius / 2));
        getDown().setLocation(center.getX(),center.getY()+(radius / 2));

        getLeft().setLocation(center.getX()-(radius /2),center.getY());
        getRight().setLocation(center.getX()+(radius /2),center.getY());

        speedX = 0;
        speedY = 0;

        this.setBorder(border);
        this.setInner(inner);
    }


    /**
     * method to set the speeds of the ball
     * @param x horizontal speed of ball
     * @param y vertical speed of ball
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * method to set the horizontal speed of baLL
     * @param s horizontal speed of ball
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * method to set the vertical speed of ball
     * @param s vertical speed of ball
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    public Point2D getPosition(){
        return center;
    }
    
    /**
     * method to set points for the ball's rectangle which
     * are used when having collision with wall or border of the windoW
     * @param width width of the ball's rectangle
     * @param height height of the ball's rectangle
     */
    private void setPoints(double width,double height){
        getUp().setLocation(center.getX(),center.getY()-(height / 2));
        getDown().setLocation(center.getX(),center.getY()+(height / 2));

        getLeft().setLocation(center.getX()-(width / 2),center.getY());
        getRight().setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * method to get the horizontal speed of the ball
     * @return horizontal speed of the ball
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * method to get the vertical speed of the ball
     * @return vertical speed of the ball
     */
    public int getSpeedY(){
        return speedY;
    }


    public Point2D getUp() {
        return up;
    }

    public void setUp(Point2D up) {
        this.up = up;
    }

    public Point2D getDown() {
        return down;
    }

    public void setDown(Point2D down) {
        this.down = down;
    }

    public Point2D getLeft() {
        return left;
    }

    public void setLeft(Point2D left) {
        this.left = left;
    }

    public Point2D getRight() {
        return right;
    }

    public void setRight(Point2D right) {
        this.right = right;
    }

    public Color getBorder() {
        return border;
    }

    public void setBorder(Color border) {
        this.border = border;
    }

    public Color getInner() {
        return inner;
    }

    public void setInner(Color inner) {
        this.inner = inner;
    }
}
