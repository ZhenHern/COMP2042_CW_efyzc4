package folder.Bricks;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BrickTest {

    ClayBrick brick = new ClayBrick(new Point(0,0),new Dimension(30,10));
    @Test
    void setImpact() {
        assertTrue(brick.setImpact(new Point(0,0),1));
        assertFalse(brick.setImpact(new Point(0,0),1));
    }


    @Test
    void isBroken() {
        assertFalse(brick.isBroken());
        brick.setImpact(new Point(0,0),1);
        assertTrue(brick.isBroken());

    }

    @Test
    void repair() {
        brick.setImpact(new Point(0,0),1);
        brick.repair();
        assertFalse(brick.isBroken());
    }

    @Test
    void impact() {
        brick.impact();
        assertTrue(brick.isBroken());
    }
}