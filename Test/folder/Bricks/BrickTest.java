package folder.Bricks;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for BrickModel class
 */
class BrickTest {

    ClayBrick brick = new ClayBrick(new Point(0,0),new Dimension(30,10));

    /**
     * if brick is not broken, there will be an impact.
     * if the brick is already broken, there will be no impact.
     */
    @Test
    void setImpact() {
        assertTrue(brick.setImpact(new Point(0,0),1));
        assertFalse(brick.setImpact(new Point(0,0),1));
    }


    /**
     * Test for isBroken() method, false if its is not broken,
     * after an impact , the brick is broken as it is a clay brick
     */
    @Test
    void isBroken() {
        assertFalse(brick.isBroken());
        brick.setImpact(new Point(0,0),1);
        assertTrue(brick.isBroken());

    }

    /**
     * test whether the brick is repaired to original state,
     * it is not broken after repairing
     */
    @Test
    void repair() {
        brick.setImpact(new Point(0,0),1);
        brick.repair();
        assertFalse(brick.isBroken());
    }

    /**
     * test whether the impact() method is working, brick will
     * be broken when impact() is called.
     */
    @Test
    void impact() {
        brick.impact();
        assertTrue(brick.isBroken());
    }
}