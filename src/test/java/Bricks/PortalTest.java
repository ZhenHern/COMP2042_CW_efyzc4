package Bricks;

import Ball.RubberBall;

import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * JUnit test for Portal class
 */
class PortalTest {
    Portal portal = new Portal();
    RubberBall b1 = new RubberBall(new Point(472,234));
    RubberBall b2 = new RubberBall(new Point(196,130));
    RubberBall b3 = new RubberBall(new Point(78,308));
    RubberBall b4 = new RubberBall(new Point(328,214));
    RubberBall b5 = new RubberBall(new Point(0,0));


    /**
     * Testing red portals that ball will change position when it goes in the portal
     */
    @org.junit.jupiter.api.Test
    void impactPortal1() {
        assertTrue(portal.impactPortal1(b1));
        assertTrue(portal.impactPortal1(b2));
        assertFalse(portal.impactPortal1(b5));
    }

    /**
     * Testing blue portals that ball will change position when it goes in the portal
     */
    @org.junit.jupiter.api.Test
    void impactPortal2() {
        assertTrue(portal.impactPortal2(b3));
        assertTrue(portal.impactPortal2(b4));
        assertFalse(portal.impactPortal2(b5));
    }
}