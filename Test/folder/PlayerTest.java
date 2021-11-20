package folder;

import folder.Ball.RubberBall;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player = new Player(new Point(0,0),30,10,new Rectangle(200,10));
    Point p1 = new Point(10,20);
    Point p2 =new Point(0,0);
    RubberBall b1 = new RubberBall(p1);
    RubberBall b2 = new RubberBall(p2);
    @Test
    void impact() {
        assertFalse(player.impact(b1));
        assertTrue(player.impact(b2));
    }


    @Test
    void moveTo() {
        player.moveTo(new Point(20,0));
        assertEquals(new Point(5,0),player.getPlayerFace().getLocation());
    }
}