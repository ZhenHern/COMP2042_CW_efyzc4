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
package Model.Bricks;

import Controller.BallController;
import Controller.BrickController;
import Controller.PlayerController;


import java.awt.*;
import java.util.Random;


/**
 * class Wall
 */
public class Wall {

    public static final int LEVELS_COUNT = 5;
    public static int CURRENT_LEVEL=0;

    private Random rnd;
    private Rectangle drawArea;
    private BrickController[] bricks;
    private BallController ball;
    private PlayerController player;
    private int level;
    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * class constructor for Wall
     * @param drawArea area of the whole window
     * @param ballPos ball starting position
     */
    public Wall(Rectangle drawArea, Point ballPos){

        this.setStartPoint(new Point(ballPos));


        setLevel(0);

        ballCount = 10;
        setBallLost(false);


        this.setDrawArea(drawArea);



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

    public void setBallCount(int ballCount){
        this.ballCount=ballCount;
    }

    /**
     * method to determine whether the ball is lost
     * @return return true if the ball is lost
     */
    public boolean isBallLost(){
        return ballLost;
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
        return bricks;
    }

    public void setBricks(BrickController[] bricks) {
        this.bricks = bricks;
    }

    public void setBallLost(boolean ballLost) {
        this.ballLost = ballLost;
    }


    public Rectangle getDrawArea() {
        return drawArea;
    }

    public void setDrawArea(Rectangle drawArea) {
        this.drawArea = drawArea;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
}
