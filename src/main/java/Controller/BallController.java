package Controller;

import Model.Ball.BallModel;
import View.BallView;


import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 * class folder.Model.BallModel.Model.BallModel
 *
 */
abstract public class BallController {

    private Shape ballFace;
    private BallModel bModel;
    private BallView bView;


    /**
     * class constructor for folder.Model.BallModel.Model.BallModel
     * @param center center of the ball
     * @param radius radius of ball
     * @param inner inner color of ball
     * @param border border color of ball
     */
    public BallController(Point2D center,int radius,Color inner,Color border){


        bModel = new BallModel(center, radius, inner, border);
        bView =  new BallView();
        ballFace = makeBall(center,radius);

    }

    /**
     * method to create the shape of the ball
     * @param center coordinate of the ball center
     * @param radius radius of ball
     * @return shape of the ball
     */
    protected abstract Shape makeBall(Point2D center,int radius);

    /**
     * method to change the ball position according to the movements
     */
    public void move(){
        RectangularShape tmp =  (RectangularShape) ballFace;
        getPosition().setLocation((getPosition().getX() + getSpeedX()),(getPosition().getY() + getSpeedY()));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((getPosition().getX() -(w/2 )),(getPosition().getY() - (h /2 )),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * method to update the view of ball
     * @param ball BallController object
     * @param g2d Graphics2D object
     */
    public void updateView(BallController ball, Graphics2D g2d){
        this.bView.drawBall(ball,g2d);
    }

    /**
     * method to set the speeds of the ball
     * @param x horizontal speed of ball
     * @param y vertical speed of ball
     */
    public void setSpeed(int x,int y){
        bModel.setSpeed(x,y);
    }

    /**
     * method to set the horizontal speed of baLL
     * @param s horizontal speed of ball
     */
    public void setXSpeed(int s){
        bModel.setXSpeed(s);
    }

    /**
     * method to set the vertical speed of ball
     * @param s vertical speed of ball
     */
    public void setYSpeed(int s){
        bModel.setYSpeed(s);
    }

    /**
     * method to reverse the horizontal speed of the ball
     */
    public void reverseX(){
        int revX = getSpeedX() * -1;
        bModel.setXSpeed(revX);
    }

    /**
     * method to reverse the vertical speed of the ball
     */
    public void reverseY(){
        int revY = getSpeedY() * -1;
        bModel.setYSpeed(revY);
    }

    /**
     * method to get ball's border color
     * @return border color of ball
     */
    public Color getBorderColor(){
        return bModel.getBorder();
    }

    /**
     * method to get the inner color of the ball
     * @return inner color of ball
     */
    public Color getInnerColor(){
        return bModel.getInner();
    }

    /**
     * method to get the coordinate of the ball center
     * @return coordinate of the ball center
     */
    public Point2D getPosition(){
        return bModel.getPosition();
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
        getPosition().setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((getPosition().getX() -(w / 2)),(getPosition().getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * method to set points for the ball's rectangle which
     * are used when having collision with wall or border of the windoW
     * @param width width of the ball's rectangle
     * @param height height of the ball's rectangle
     */
    private void setPoints(double width,double height){
        getUp().setLocation(getPosition().getX(),getPosition().getY()-(height / 2));
        getDown().setLocation(getPosition().getX(),getPosition().getY()+(height / 2));

        getLeft().setLocation(getPosition().getX()-(width / 2),getPosition().getY());
        getRight().setLocation(getPosition().getX()+(width / 2),getPosition().getY());
    }

    /**
     * method to get the horizontal speed of the ball
     * @return horizontal speed of the ball
     */
    public int getSpeedX(){
        return bModel.getSpeedX();
    }

    /**
     * method to get the vertical speed of the ball
     * @return vertical speed of the ball
     */
    public int getSpeedY(){
        return bModel.getSpeedY();
    }


    public Point2D getUp() {
        return bModel.getUp();
    }



    public Point2D getDown() {
        return bModel.getDown();
    }



    public Point2D getLeft() {
        return bModel.getLeft();
    }



    public Point2D getRight() {
        return bModel.getRight();
    }


}
