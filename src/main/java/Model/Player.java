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
package Model;

import Controller.BallController;
import Model.Ball.Ball;

import java.awt.*;


/**
 * class Player
 */
public class Player {

    private static Player player = null;
    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;
    private static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle playerFace;
    private Rectangle container;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;
    private int changeDir=0;


    /**
     * class constructor for player
     * Refactor : Singleton design pattern is used here.
     * Only one instance of player class is created. A get
     * instance method is use to get the player object.
     */
    private Player() {
        container = new Rectangle(0,0,600,450);
        ballPoint = new Point(300,430);
        int width = 150;
        int height = 10;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * Create a player object if there is none.
     * Method to obtain the player object
     * @return player object
     */
    public static Player getPlayer(){
        if(player == null){
            player = new Player();
        }
        return player;
    }

    /**
     * remove the current player object
     */
    public static void resetPlayer(){
        player = null;
    }

    /**
     * method to create the player shape
     * @param width width of the player
     * @param height height of the player
     * @return shape of the player
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * method to determine if there is an impact between player and ball
     * @param b ball object
     * @return true if ball contacts with player
     */
    public boolean impact(BallController b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.getDown()) ;
    }

    /**
     * method to move the player position
     * player can only be moved horizontally, it cannot
     * move beyond the border of window
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }


    /**
     * method to move the player left
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * method to move the player right
     */
    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * method to move the player in the main menu
     */
    public void moveStart() {
        if (changeDir==0){
            moveAmount =-2;
        }
        else if(changeDir==1){
            moveAmount =2;
        }
        double a = ballPoint.getX() + moveAmount;

        if (a < min ) {
            changeDir=1;
        }
        else if(a > max){
            changeDir=0;
        }
    }


    /**
     * method to stop the movement of player
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * method to get the player shape
     * @return player shape
     */
    public Rectangle getPlayerFace(){
        return  playerFace;
    }

    /**
     * method to move the player to certain position
     * @param p player coordinate
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}
