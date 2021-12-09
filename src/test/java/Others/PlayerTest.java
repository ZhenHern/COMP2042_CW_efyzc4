package Others;

import Controller.PlayerController;
import Model.Ball.RubberBall;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for PlayerModel class
 */
class PlayerTest {

    PlayerController player = PlayerController.getPlayer();
    Point p1 = new Point(10,20);
    Point p2 =new Point(0,0);
    RubberBall b1 = new RubberBall(p1);
    RubberBall b2 = new RubberBall(p2);

    /**
     * test whether the method impact() return the correct value when it
     * has an impact with ball or not
     */
    @Test
    void impact() {
        assertFalse(player.impact(b1));
        assertTrue(player.impact(b2));
    }


    /**
     * test whether the player can be moved to a certain point after calling the
     * method moveTo()
     */
    @Test
    void moveTo() {
        player.moveTo(new Point(20,0));
        assertEquals(new Point(5,0),player.getPlayerFace().getLocation());
    }
}