package folder;

import java.awt.*;

public interface Menu {

    public void initialize();

    public void clear(Graphics2D g2d);

    public void drawText(Graphics2D g2d);

    public void drawButton(Graphics2D d2s);

    public void drawBrick(Brick brick,Graphics2D g2d);

    public void drawBall(Ball ball,Graphics2D g2d);

    public void drawPlayer(Player p,Graphics2D g2d);



}
