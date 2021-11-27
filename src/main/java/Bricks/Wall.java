/*
 *  test.Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Bricks;

import Ball.Ball;
import Ball.RubberBall;
import Others.Player;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * class Wall
 */
public class Wall {

    private static final int LEVELS_COUNT = 5;
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    public static int CURRENT_LEVEL=0;

    private Random rnd;
    private Rectangle area;

    private Brick[] bricks;
    private Ball ball;
    private Player player;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * class constructor for Wall
     * @param drawArea area of the whole window
     * @param brickCount total number of bricks
     * @param lineCount total number of brick lines
     * @param brickDimensionRatio brick dimension ratio (width : height)
     * @param ballPos ball starting position
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 10;
        setBallLost(false);

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;
        do{
            speedX = 4;
        }while(speedX == 0);
        do{
            speedY = -8;
        }while(speedY == 0);

        getBall().setSpeed(speedX,speedY);

        setPlayer(Player.getPlayer());

        area = drawArea;


    }

    /**
     * method to create wall with single type of bricks
     * @param drawArea area of the whole window
     * @param brickCnt total number of bricks
     * @param lineCnt total number of brick lines
     * @param brickSizeRatio brick dimension ratio (width : height)
     * @param type brick type
     * @return return an array of brick objects
     */
    public Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }

    /**
     * method to create wall with two types of bricks
     * @param drawArea area of the whole window
     * @param brickCnt total number of bricks
     * @param lineCnt total number of brick lines
     * @param brickSizeRatio brick dimension ratio (width : height)
     * @param typeA first brick type
     * @param typeB second brick type
     * @return return an array of brick objects
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * method to create rubber ball object
     * @param ballPos ball position (coordinate)
     */
    private void makeBall(Point2D ballPos){
        setBall(new RubberBall(ballPos));
    }

    /**
     * method to make levels by using the correct bricks
     * @param drawArea area of the whole window
     * @param brickCount total number of bricks
     * @param lineCount total number of brick lines
     * @param brickDimensionRatio brick dimension ratio (width : height)
     * @return return an array of game level, which is used to determine what bricks are used in the levels
     */
    private Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        return tmp;
    }

    /**
     * method to allow the player and ball to move
     */
    public void move(){
        getPlayer().move();
        getBall().move();
    }

    /**
     * method to change ball direction, reduce total brick number
     * when there is impact, and to determine if the ball is lost
     */
    public void findImpacts(){
        if(getPlayer().impact(getBall())){
            getBall().reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;

        }
        else if(impactBorder()) {
            getBall().reverseX();
        }
        else if(getBall().getPosition().getY() < area.getY()){
            getBall().reverseY();
        }
        else if(getBall().getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            setBallLost(true);
        }
    }

    /**
     * method to change the ball direction upon hitting brick,
     * and give crack appearance to brick according to direction of the impact
     * @return boolean which determine whether the brick is impacted
     */
    private boolean impactWall(){
        for(Brick b : getBricks()){
            switch(b.findImpact(getBall())) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getDown(), Crack.UP);
                case Brick.DOWN_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getUp(), Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getRight(), Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getLeft(), Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * method to change the direction of ball when it hits the border of the window
     * @return return true if ball hits the border of the window
     */
    private boolean impactBorder(){
        Point2D p = getBall().getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * method to get the total number of bricks
     * @return total number of bricks
     */
    public int getBrickCount(){
        return brickCount;
    }

    public void setBrickCount(int brickCount){
        this.brickCount=brickCount;
    }

    /**
     * method to get the number of balls left
     * @return number of balls left
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * method to determine whether the ball is lost
     * @return return true if the ball is lost
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * method to reset the ball position back to original spot
     */
    public void ballReset(){
        getPlayer().moveTo(startPoint);
        getBall().moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = 4;
        }while(speedX == 0);
        do{
            speedY = -4;
        }while(speedY == 0);

        getBall().setSpeed(speedX,speedY);
        setBallLost(false);
    }

    /**
     * method to reset the whole wall of bricks
     */
    public void wallReset(){
        for(Brick b : getBricks())
            b.repair();
        brickCount = getBricks().length;
        ballCount = 10;
    }

    /**
     * method to determine if number of ball has been used up
     * @return true if ball has been used up
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * method to determine if all bricks have been destroyed
     * @return true if all bricks have been destroyed
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * method to proceed to next level
     * it is used in the stage menu to choose level
     */
    public void nextLevel(){
        setBricks(levels[level++]);
        this.brickCount = getBricks().length;
        CURRENT_LEVEL+=1;
    }


    /**
     * method to determine whether there is more level after the current one
     * @return true if there are more levels available
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * method to set ball x speed
     * @param s speed
     */
    public void setBallXSpeed(int s){
        getBall().setXSpeed(s);
    }

    /**
     * method to set ball y speed
     * @param s speed
     */
    public void setBallYSpeed(int s){
        getBall().setYSpeed(s);
    }

    /**
     * method to reset the ball count back to 3
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * Refactor : makeBrick method is changed according to
     * the factory method design pattern. Brick type to be
     * instatiated is done in GetBrickFactory class's getBrick method
     * method to create brick of different types
     * @param point coordinate of brick
     * @param size dimension of brick
     * @param type type of brick
     * @return brick object
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        GetBrickFactory factory = new GetBrickFactory();
        return factory.getBrick(point,size,type);
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Brick[] getBricks() {
        return bricks;
    }

    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    public void setBallLost(boolean ballLost) {
        this.ballLost = ballLost;
    }
}
