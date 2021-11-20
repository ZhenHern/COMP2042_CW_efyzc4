package folder.Bricks;

import folder.Ball.Ball;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Class Portal to create portals for special stage
 */
public class Portal {
    private Shape portal1;
    private Shape portal2;
    private Shape portal3;
    private Shape portal4;


    /**
     * Class constructor Portal to set the position and radius of the portals
     */
    public Portal(){
        setPortal1(new Ellipse2D.Double(440,225,30,30));
        setPortal2(new Ellipse2D.Double(180,130,30,30));
        setPortal3(new Ellipse2D.Double(70,310,30,30));
        setPortal4(new Ellipse2D.Double(310,180,30,30));

    }

    /**
     * method to find portal impact, and move the balls from one portal to another
     * @param b ball object
     * @return true if ball touch the portal circle, false if it does not touch the portal circle
     */
    public boolean impactPortal1(Ball b){
        if(getPortal1().contains(b.getLeft()) || getPortal1().contains(b.getRight()) || getPortal1().contains(b.getUp()) || getPortal1().contains(b.getDown())){
            b.moveTo(new Point(180,130));
            return true;
        }
        if(getPortal2().contains(b.getLeft()) || getPortal2().contains(b.getRight()) || getPortal2().contains(b.getUp()) || getPortal2().contains(b.getDown())){
            b.moveTo(new Point(440,225));
            return true;
        }
        return false;
    }

    /**
     * method to find portal impact, and move the balls from one portal to another
     * @param b ball object
     * @return true if ball touch the portal circle, false if it does not touch the portal circle
     */
    public boolean impactPortal2(Ball b){
        if(getPortal3().contains(b.getLeft()) || getPortal3().contains(b.getRight()) || getPortal3().contains(b.getUp()) || getPortal3().contains(b.getDown())){
            b.moveTo(new Point(310,180));
            return true;
        }
        if(getPortal4().contains(b.getLeft()) || getPortal4().contains(b.getRight()) || getPortal4().contains(b.getUp()) || getPortal4().contains(b.getDown())){
            b.moveTo(new Point(70,310));
            return true;
        }
        return false;
    }

    /**
     * getter method for portal1
     * @return portal shape
     */
    public Shape getPortal1() {
        return portal1;
    }

    /**
     * setter method for portal1
     * @param portal1 portal shape
     */
    public void setPortal1(Shape portal1) {
        this.portal1 = portal1;
    }

    /**
     * getter method for portal2
     * @return portal shape
     */
    public Shape getPortal2() {
        return portal2;
    }

    /**
     * setter method for portal2
     * @param portal2 portal shape
     */
    public void setPortal2(Shape portal2) {
        this.portal2 = portal2;
    }

    /**
     * getter method for portal3
     * @return portal shape
     */
    public Shape getPortal3() {
        return portal3;
    }

    /**
     * setter method for portal3
     * @param portal3 portal shape
     */
    public void setPortal3(Shape portal3) {
        this.portal3 = portal3;
    }

    /**
     * getter method for portal4
     * @return portal shape
     */
    public Shape getPortal4() {
        return portal4;
    }

    /**
     * setter method for portal4
     * @param portal4 portal shape
     */
    public void setPortal4(Shape portal4) {
        this.portal4 = portal4;
    }
}
