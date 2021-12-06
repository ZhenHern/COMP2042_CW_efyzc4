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

import java.awt.*;


/**
 * class Player
 */
public class Player {


    private Point ballPoint;
    private int min;
    private int max;


    /**
     * class constructor for player
     * Refactor : Singleton design pattern is used here.
     * Only one instance of player class is created. A get
     * instance method is use to get the player object.
     */
    public Player(Point ballPoint, int width, Rectangle container) {
        this.setBallPoint(ballPoint);
        setMin(container.x + (width / 2));
        setMax(getMin() + container.width - width);

    }

    public Point getBallPoint() {
        return ballPoint;
    }

    public void setBallPoint(Point ballPoint) {
        this.ballPoint = ballPoint;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
