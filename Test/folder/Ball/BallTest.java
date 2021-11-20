package folder.Ball;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {
    Point p = new Point(10,20);
    RubberBall b = new RubberBall(p);



    @Test
    void move() {
        b.setSpeed(10,10);
        b.move();
        assertNotEquals(new Point(10,20),b.getPosition());
    }

    @Test
    void reverseX() {
        b.setSpeed(10,20);
        b.reverseX();
        assertEquals(-10,b.getSpeedX());
    }

    @Test
    void reverseY() {
        b.setSpeed(10,20);
        b.reverseY();
        assertEquals(-20,b.getSpeedY());
    }

    @Test
    void moveTo() {
        b.moveTo(new Point(50,50));
        assertEquals(new Point(50,50),b.getPosition());
    }
}