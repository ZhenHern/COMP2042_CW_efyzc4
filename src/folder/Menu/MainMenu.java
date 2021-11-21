package folder.Menu;

import folder.*;
import folder.Ball.Ball;
import folder.Bricks.Brick;
import folder.Bricks.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;


/**
 * MainMenu class
 */
public class MainMenu extends JComponent implements MouseListener, MouseMotionListener, Screen {

    private static final String PLAY_TEXT = "PLAY GAME";
    private static final String INFO_TEXT = "HOW TO PLAY";
    private static final String HIGH_SCORE = "LEADERBOARD";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause folder.Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;
    private static final Color CLICKED_BUTTON_COLOR = new Color(178, 34, 34).darker();
    private static final Color CLICKED_BUTTON_BORDER = Color.GRAY;
    private static final Color CLICKED_TEXT = Color.WHITE;


    private Timer gameTimer;

    private Wall wall;

    private String message;

    private boolean playClicked;
    private boolean infoClicked;
    private boolean leaderboardClicked;

    private Font menuFont;

    private Rectangle playButton;
    private Rectangle infoButton;
    private Rectangle leaderboardButton;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;


    private Font buttonFont;

    private GameFrame owner;


    public MainMenu(GameFrame owner){
        super();

        this.owner=owner;



        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);

        buttonFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);


        this.initialize();
        message = "";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(330,430));

        //initialize the first level
        wall.nextLevel();

        playButton = new Rectangle(220,75);
        infoButton = new Rectangle(220,75);
        leaderboardButton = new Rectangle(220,75);


        gameTimer = new Timer(10,e ->{
            wall.getPlayer().moveStart();
            wall.move();
            wall.findImpacts();
            if(wall.isBallLost()){
                wall.ballReset();
                gameTimer.stop();
                gameTimer.start();
            }
            else if(wall.isDone()){
                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                    gameTimer.start();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                }
            }


            repaint();




        });

        start();


    }

    /**
     * method to initialize the InfoMenu window with specific settings.
     */
    public void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * method to start the gameTimer
     */
    private void start(){
        gameTimer.start();
    }

    /**
     * method that is called automatically in the background and when components
     * need to be painted
     * @param g object of Graphics
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.RED);
        g2d.drawString(message,250,225);

        drawBall(wall.getBall(),g2d);

        for(Brick b : wall.getBricks())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.getPlayer(),g2d);

        drawButton(g2d);

        Toolkit.getDefaultToolkit().sync();
    }


    /**
     * method to draw the bricks
     * @param brick bricks that form the wall
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }


    /**
     * method to change the color of background to white
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    @Override
    public void drawText(Graphics2D g2d) {
    }


    /**
     * method to draw the ball
     * @param ball ball which is used to break the bricks
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void drawBall(Ball ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }


    /**
     * method to draw the player rectangle.
     * @param p player object
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void drawPlayer(Player p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * method to draw the buttons
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        g2d.setFont(buttonFont);

        playButton.setLocation(190,130);
        infoButton.setLocation(190,220);
        leaderboardButton.setLocation(190,310);

        if(playClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.fillRect(190,130,220,75);
            g2d.setColor(CLICKED_BUTTON_BORDER);
            g2d.draw(playButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(PLAY_TEXT,217,175);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(playButton);
            g2d.drawString(PLAY_TEXT,217,175);
        }

        if(infoClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.fillRect(190,220,220,75);
            g2d.setColor(CLICKED_BUTTON_BORDER);
            g2d.draw(infoButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INFO_TEXT,200,265);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(infoButton);
            g2d.drawString(INFO_TEXT,200,265);
        }

        if(leaderboardClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.fillRect(190,310,220,75);
            g2d.setColor(CLICKED_BUTTON_BORDER);
            g2d.draw(leaderboardButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(HIGH_SCORE,200,355);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(leaderboardButton);
            g2d.drawString(HIGH_SCORE,200,355);
        }









    }


    /**
     * method to determine whether the cursor is in the button area when the mouse is being clicked
     * @param mouseEvent mouse event
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(playButton.contains(p)){
            owner.changeScreen(owner.getStageMenu());
            wall.ballReset();
            wall.wallReset();
        }
        else if(infoButton.contains(p)){
            owner.changeScreen(owner.getInfoMenu());
            wall.ballReset();
            wall.wallReset();
        }
        else if(leaderboardButton.contains(p)){
            owner.changeScreen(owner.getLeaderboard());
            wall.ballReset();
            wall.wallReset();
        }
    }

    /**
     * method to repaint the button if the cursor is in the button when the mouse is being pressed
     * @param mouseEvent mouse event
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(playButton.contains(p)){
            playClicked = true;
            repaint(playButton.x,playButton.y,playButton.width+1,playButton.height+1);
        }
        else if(infoButton.contains(p)){
            infoClicked = true;
            repaint(infoButton.x,infoButton.y,infoButton.width+1,infoButton.height+1);
        }
        else if(leaderboardButton.contains(p)){
            leaderboardClicked = true;
            repaint(leaderboardButton.x,leaderboardButton.y,leaderboardButton.width+1,leaderboardButton.height+1);
        }

    }

    /**
     * method to repaint the button if the mouse is being released.
     * @param e mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(playClicked){
            playClicked = false;
            repaint(playButton.x,playButton.y,playButton.width+1,playButton.height+1);
        }
        else if(infoClicked){
            infoClicked = false;
            repaint(infoButton.x,infoButton.y,infoButton.width+1,infoButton.height+1);
        }
        else if(leaderboardClicked){
            leaderboardClicked = false;
            repaint(leaderboardButton.x,leaderboardButton.y,leaderboardButton.width+1,leaderboardButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
