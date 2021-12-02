package Menu;

import Controller.BallController;
import Controller.BrickController;
import Controller.PlayerController;
import Model.Ball.Ball;
import Model.Bricks.Brick;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class InfoMenu extends JComponent implements MouseListener, MouseMotionListener, Screen {

    private static final String INSTRUCTIONS_TEXT = "INSTRUCTIONS";
    private static final String RIGHT_TEXT = "MOVE RIGHT : 'D'";
    private static final String LEFT_TEXT = "MOVE LEFT  : 'A'";
    private static final String BACK_TEXT = "BACK";
    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;
    private static int rightStart = 300;
    private static int leftStart = 450;
    private static int rightStop = 450;
    private static final int TEXT_SIZE = 30;
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private boolean backClicked;
    private Font titleFont;
    private Font descriptionFont;
    private Rectangle player;
    private Rectangle backButton;
    private Timer infoTimer;




    private GameFrame owner;


    /**
     * class constructor for InfoMenu class
     * @param owner GameFrame object
     */
    public InfoMenu (GameFrame owner){
        super();
        this.owner=owner;
        this.initialize();
        titleFont = new Font("Monospaced",Font.BOLD,TEXT_SIZE);
        descriptionFont = new Font("Monospaced",Font.BOLD,20);
        infoTimer = new Timer(10,e -> {
            if(rightStop!=600){
                moveRight();
                moveLeft();
                rightStop+=1;
                repaint();
            }
            else if(rightStop==600){
                rightStop=450;
                rightStart=300;
                leftStart=450;
                infoTimer.stop();
                infoTimer.start();
            }


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
     * method that is called automatically in the background and when components
     * need to be painted
     * @param g object of Graphics
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        clear(g2d);
        drawText(g2d);
        drawPlayer(g2d);
        drawButton(g2d);
    }

    /**
     * method to change the color of background to cyan
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(Color.cyan);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * method to draw the strings according to the font and color
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void drawText(Graphics2D g2d){
        g2d.setFont(titleFont);
        g2d.setColor(Color.BLUE);
        g2d.drawString(INSTRUCTIONS_TEXT,185,50);
        g2d.setFont(descriptionFont);
        g2d.drawString(RIGHT_TEXT,15,185);
        g2d.drawString(LEFT_TEXT,15,340);
    }

    /**
     * method to draw the player rectangle.
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void drawPlayer(Graphics2D g2d){
        Rectangle player1 = makeRectangle(rightStart,180,150,10);
        Rectangle player2 = makeRectangle(leftStart,335,150,10);
        g2d.setColor(INNER_COLOR);
        g2d.fill(player1);
        g2d.fill(player2);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(player1);
        g2d.draw(player2);
    }

    /**
     * method to draw the buttons
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void drawButton(Graphics2D g2d){
        backButton = new Rectangle(0,0,150,30);
        if(backClicked){
            g2d.setColor(Color.blue);
            g2d.fill(backButton);
            g2d.setColor(Color.white);
            g2d.draw(backButton);
            g2d.drawString(BACK_TEXT,50,20);
        }
        else{
            g2d.setColor(Color.WHITE);
            g2d.fill(backButton);
            g2d.setColor(Color.black);
            g2d.draw(backButton);
            g2d.drawString(BACK_TEXT,50,20);
        }

    }

    @Override
    public void drawBrick(BrickController brick, Graphics2D g2d) {

    }

    @Override
    public void drawBall(BallController ball, Graphics2D g2d) {

    }

    @Override
    public void drawPlayer(PlayerController p, Graphics2D g2d) {

    }

    /**
     * method to create a rectangle object with specific dimension and location
     * @param x x coordinate
     * @param y y coordinate
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @return rectangle object that is created
     */
    private Rectangle makeRectangle(int x,int y,int width,int height){
        Point p = new Point((int)(x),y);
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     *method to start the timer
     */
    private void start(){
        infoTimer.start();
    }

    /**
     * method to move the player to right by 1 unit
     */
    private void moveRight(){
        rightStart+=1;
    }

    /**
     * method to move the player to left by 1 unit
     */
    private void moveLeft(){
        leftStart-=1;
    }

    /**
     * method to determine whether the cursor is in the button area when the mouse is being clicked
     * @param e mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if(backButton.contains(p)){
            owner.changeScreen(owner.getMainMenu());
        }
    }

    /**
     * method to repaint the button if the cursor is in the button when the mouse is being pressed
     * @param e moust event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if(backButton.contains(p)){
            backClicked=true;
            repaint(backButton);
        }
    }

    /**
     * method to repaint the button if the mouse is being released.
     * @param e mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(backClicked==true){
            backClicked=false;
            repaint(backButton);
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
