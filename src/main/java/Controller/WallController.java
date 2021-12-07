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
package Controller;

import Model.Ball.RubberBall;
import Model.Bricks.Crack;
import Model.Bricks.GetBrickFactory;
import Model.Bricks.Wall;
import Model.Bricks.brickTypes;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * class Wall
 */
public class WallController {



    private Random rnd;
//    private Rectangle drawArea;
    private BrickController[] bricks;
    private BallController ball;
    private PlayerController player;
    private BrickController[][] levels;


    private Wall wModel;

    /**
     * class constructor for Wall
     * @param drawArea area of the whole window
     * @param brickCount total number of bricks
     * @param lineCount total number of brick lines
     * @param brickDimensionRatio brick dimension ratio (width : height)
     * @param ballPos ball starting position
     */
    public WallController(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){



        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);

        wModel = new Wall(drawArea, ballPos);

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

        setPlayer(PlayerController.getPlayer());




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
    public BrickController[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, brickTypes type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickController[] tmp  = new BrickController[brickCnt];

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
            tmp[i] = makeBrick(p,brickSize,type);
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
    private BrickController[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, brickTypes typeA, brickTypes typeB){
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

        BrickController[] tmp  = new BrickController[brickCnt];

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
    private BrickController[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio){
        BrickController[][] tmp = new BrickController[wModel.LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,brickTypes.CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,brickTypes.CLAY,brickTypes.CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,brickTypes.CLAY,brickTypes.CLAY);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,brickTypes.STEEL,brickTypes.CEMENT);
        tmp[4] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,brickTypes.CLAY);
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
            wModel.setBrickCount(getBrickCount()-1);

        }
        else if(impactBorder()) {
            getBall().reverseX();
        }
        else if(getBall().getPosition().getY() < wModel.getDrawArea().getY()){
            getBall().reverseY();
        }
        else if(getBall().getPosition().getY() > wModel.getDrawArea().getY() + wModel.getDrawArea().getHeight()){
            wModel.setBallCount(getBallCount()-1);
            wModel.setBallLost(true);
        }
    }

    /**
     * method to change the ball direction upon hitting brick,
     * and give crack appearance to brick according to direction of the impact
     * @return boolean which determine whether the brick is impacted
     */
    private boolean impactWall(){
        for(BrickController b : getBricks()){
            switch(b.findImpact(getBall())) {
                //Vertical Impact
                case BrickController.UP_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getDown(), Crack.UP);
                case BrickController.DOWN_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getUp(), Crack.DOWN);

                //Horizontal Impact
                case BrickController.LEFT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getRight(), Crack.RIGHT);
                case BrickController.RIGHT_IMPACT:
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
        return ((p.getX() < wModel.getDrawArea().getX()) ||(p.getX() > (wModel.getDrawArea().getX() + wModel.getDrawArea().getWidth())));
    }

    /**
     * method to get the total number of bricks
     * @return total number of bricks
     */
    public int getBrickCount(){
        return wModel.getBrickCount();
    }



    /**
     * method to get the number of balls left
     * @return number of balls left
     */
    public int getBallCount(){
        return wModel.getBallCount();
    }

    /**
     * method to determine whether the ball is lost
     * @return return true if the ball is lost
     */
    public boolean isBallLost(){
        return wModel.isBallLost();
    }

    /**
     * method to reset the ball position back to original spot
     */
    public void ballReset(){
        getPlayer().moveTo(wModel.getStartPoint());
        getBall().moveTo(wModel.getStartPoint());
        int speedX,speedY;
        do{
            speedX = 4;
        }while(speedX == 0);
        do{
            speedY = -4;
        }while(speedY == 0);

        getBall().setSpeed(speedX,speedY);
        wModel.setBallLost(false);
    }

    /**
     * method to reset the whole wall of bricks
     */
    public void wallReset(){
        for(BrickController b : getBricks())
            b.repair();
        wModel.setBrickCount(getBricks().length);
        wModel.setBallCount(10);
    }

    /**
     * method to determine if number of ball has been used up
     * @return true if ball has been used up
     */
    public boolean ballEnd(){
        return getBallCount() == 0;
    }

    /**
     * method to determine if all bricks have been destroyed
     * @return true if all bricks have been destroyed
     */
    public boolean isDone(){
        return getBrickCount()== 0;
    }

    /**
     * method to proceed to next level
     * it is used in the stage menu to choose level
     */
    public void nextLevel(){
        wModel.setBricks(levels[wModel.getLevel()]);
        wModel.setLevel(wModel.getLevel() +1);

        wModel.setBrickCount(getBricks().length);
        wModel.CURRENT_LEVEL+=1;
    }


    /**
     * method to determine whether there is more level after the current one
     * @return true if there are more levels available
     */
    public boolean hasLevel(){
        return wModel.getLevel()< levels.length;
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
        wModel.setBallCount(3);
    }

    /**
     * Refactor : makeBrick method is changed according to
     * the factory method design pattern. Brick type to be
     * instantiated is done in GetBrickFactory class's getBrick method
     * method to create brick of different types
     * @param point coordinate of brick
     * @param size dimension of brick
     * @param type type of brick
     * @return brick object
     */
    private BrickController makeBrick(Point point, Dimension size, brickTypes type){
        GetBrickFactory factory = new GetBrickFactory();
        return factory.getBrick(point,size,type);
    }

    public BallController getBall() {
        return ball;
    }

    public void setBall(BallController ball) {
        this.ball = ball;
    }

    public PlayerController getPlayer() {
        return player;
    }

    public void setPlayer(PlayerController player) {
        this.player = player;
    }

    public BrickController[] getBricks() {
        return wModel.getBricks();
    }


    public void setBricks(BrickController[] bricks) {
        wModel.setBricks(bricks);
    }

}
