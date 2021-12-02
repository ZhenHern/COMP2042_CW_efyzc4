package folder.Ball;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for Model.Ball class
 */
class BallTest {
    Point p = new Point(10,20);
    RubberBall b = new RubberBall(p);


    /**
     * ball position will be different after move() is called
     */
    @Test
    void move() {
        b.setSpeed(10,10);
        b.move();
        assertNotEquals(new Point(10,10),b.getPosition());
    }

    /**
     * ball speed x will be reversed after reverseX() is called
     */
    @Test
    void reverseX() {
        b.setSpeed(10,20);
        b.reverseX();
        assertEquals(-10,b.getSpeedX());
    }

    /**
     * ball speed y will be reversed after reverseY() is called
     */
    @Test
    void reverseY() {
        b.setSpeed(10,20);
        b.reverseY();
        assertEquals(-20,b.getSpeedY());
    }

    /**
     * ball will be moved to ta certain position after the method moveTo() is called
     */
    @Test
    void moveTo() {
        b.moveTo(new Point(50,50));
        assertEquals(new Point(50,50),b.getPosition());
    }
}