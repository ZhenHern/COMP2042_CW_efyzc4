package Bricks;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    Wall wall = new Wall(new Rectangle(0,0,600, 450),30,3,6/2,new Point(300,430));



    @Test
    void findImpacts() {
        int ballY1 = wall.getBall().getSpeedY();
        int ballY2;
        int ballX = wall.getBall().getSpeedX();
        wall.setBricks(wall.makeSingleTypeLevel(new Rectangle(0,0,600,450),30,3,6/2,1));
        /**
         *Testing if ball y speed reverses if it impact with player
         */
        wall.findImpacts();
        assertEquals(-ballY1,wall.getBall().getSpeedY());
        ballY2 = wall.getBall().getSpeedY();



        /**
         *Testing if ball x speed reverses if it impact with border
         */
        wall.getBall().moveTo(new Point(602,238));
        wall.findImpacts();
        assertEquals(-ballX,wall.getBall().getSpeedX());
//        assertEquals(30,wall.getBricks().length);


        /**
         *Testing if ball y speed reverses if it impact with top borderline
         */
        wall.getBall().moveTo(new Point(442,-2));
        wall.findImpacts();
        assertEquals(-ballY2,wall.getBall().getSpeedY());

        wall.getBall().moveTo(new Point(314,454));
        wall.findImpacts();
        assertEquals(9,wall.getBallCount());

    }


}