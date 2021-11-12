package folder;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 * class Ball
 *
 */
abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * class constructor for Ball
     * @param center center of the ball
     * @param radiusA radius of ball
     * @param radiusB radius of ball
     * @param inner inner color of ball
     * @param border border color of ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * method to create the shape of the ball
     * @param center coordinate of the ball center
     * @param radiusA radius of ball
     * @param radiusB radius of ball
     * @return shape of the ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * method to change the ball position according to the movements
     */
    public void move(){
        RectangularShape tmp =  (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w/2 )),(center.getY() - (h /2 )),w,h);
        setPoints(w,h);


        ballFace = tmp;
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

    /**
     * method to reverse the horizontal speed of the ball
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * method to reverse the vertical speed of the ball
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * method to get ball's border color
     * @return border color of ball
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * method to get the inner color of the ball
     * @return inner color of ball
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * method to get the coordinate of the ball center
     * @return coordinate of the ball center
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * method to get the ball shape
     * @return ball shape
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * method to move the ball to a certain position
     * @param p position for the ball to be moved to
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * method to set points for the ball's rectangle which
     * are used when having collision with wall or border of the windoW
     * @param width width of the ball's rectangle
     * @param height height of the ball's rectangle
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
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


}
