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

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * class for SteelBrick which extends Brick class
 */
public class SteelBrick extends Brick {

    private static final String NAME = "Steel test.Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    /**
     * class constructor for SteelBrick class
     * @param point coordinate of steel brick
     * @param size dimension of steel brick
     */
    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }


    /**
     * method to create brick shape
     * @param pos  coordinate of brick
     * @param size size of brick (dimension)
     * @return brick shape
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * method to get brick shape
     * @return brick shape
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * method to call impact() method if the brick is not broken yet
     * @param point coordinate of the impact
     * @param dir   direction of the impact
     * @return false if brick is already broken, true if there is impact
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    /**
     * method to set impact on steel brick according to the probability
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

}
