/*
 *  test.BrickModel Destroy - A simple Arcade video game
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
package folder.Ball;

import folder.Ball.Ball;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * class RubberBall which extends folder.Model.BallModel.Model.BallModel class
 */
public class RubberBall extends Ball {


    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = new Color(255, 219, 88);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();


    /**
     * class constructor for RubberBall
     * @param center coordinate of the rubber ball
     */
    public RubberBall(Point2D center){
        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }


    /**
     * method to create the ball shape
     * @param center  coordinate of the ball center
     * @param radiusA radius of ball
     * @param radiusB radius of ball
     * @return shape of ball
     */
    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {

        double x = center.getX() - (radiusA /2);
        double y = center.getY() - (radiusB / 2);

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}
