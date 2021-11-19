package folder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * class for StageMenu
 */
public class StageMenu extends JComponent implements MouseListener, MouseMotionListener,Menu {
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final String TITLE = "SELECT A STAGE";
    private static String BACK_TEXT = "BACK";
    private Font titleFont;
    private Font descFont;
    private Font buttonFont;
    private Rectangle backButton;
    private Rectangle button1;
    private Rectangle button2;
    private Rectangle button3;
    private Rectangle button4;

    private boolean backClicked;
    private boolean button1Clicked;
    private boolean button2Clicked;
    private boolean button3Clicked;
    private boolean button4Clicked;
    private GameFrame owner;

    /**
     * class constructor for StageMenu
     * @param owner GameFrame object
     */
    public StageMenu(GameFrame owner){
        super();
        this.owner = owner;
        this.initialize();
    }

    /**
     * method to initialize the StageMenu window with specific settings.
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
    public void drawText(Graphics2D g2d) {
        titleFont = new Font("Monospaced",Font.BOLD,30);
        descFont = new Font("Monospaced",Font.BOLD,20);
        g2d.setFont(titleFont);
        g2d.setColor(Color.blue);
        g2d.drawString(TITLE,165,60);
        g2d.setFont(descFont);
    }

    /**
     * method to draw the buttons
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    public void drawButton(Graphics2D g2d){
        backButton = new Rectangle(0,0,150,30);
        button1 = new Rectangle(30,140,110,110);
        button2 = new Rectangle(173,140,110,110);
        button3 = new Rectangle(316,140,110,110);
        button4 = new Rectangle(460,140,110,110);
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
        buttonFont = new Font("Monospaced",Font.PLAIN,80);
        g2d.setFont(buttonFont);
        if(button1Clicked){
            g2d.setColor(Color.blue);
            g2d.fill(button1);
            g2d.setColor(Color.white);
            g2d.draw(button1);
            g2d.drawString("1",63,215);
        }
        else{
            g2d.setColor(Color.WHITE);
            g2d.fill(button1);
            g2d.setColor(Color.black);
            g2d.draw(button1);
            g2d.drawString("1",63,215);
        }
        if(button2Clicked){
            g2d.setColor(Color.blue);
            g2d.fill(button2);
            g2d.setColor(Color.white);
            g2d.draw(button2);
            g2d.drawString("2",206,215);
        }
        else{
            g2d.setColor(Color.WHITE);
            g2d.fill(button2);
            g2d.setColor(Color.black);
            g2d.draw(button2);
            g2d.drawString("2",206,215);
        }
        if(button3Clicked){
            g2d.setColor(Color.blue);
            g2d.fill(button3);
            g2d.setColor(Color.white);
            g2d.draw(button3);
            g2d.drawString("3",349,215);
        }
        else{
            g2d.setColor(Color.WHITE);
            g2d.fill(button3);
            g2d.setColor(Color.black);
            g2d.draw(button3);
            g2d.drawString("3",349,215);
        }
        if(button4Clicked){
            g2d.setColor(Color.blue);
            g2d.fill(button4);
            g2d.setColor(Color.white);
            g2d.draw(button4);
            g2d.drawString("4",492,215);
        }
        else{
            g2d.setColor(Color.WHITE);
            g2d.fill(button4);
            g2d.setColor(Color.black);
            g2d.draw(button4);
            g2d.drawString("4",492,215);
        }
    }

    @Override
    public void drawBrick(Brick brick, Graphics2D g2d) {

    }

    @Override
    public void drawBall(Ball ball, Graphics2D g2d) {

    }

    @Override
    public void drawPlayer(Player p, Graphics2D g2d) {

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
        if(button1.contains(p)){
            owner.enableGameBoard(1);
        }
        if(button2.contains(p)){
            owner.enableGameBoard(2);
        }
        if(button3.contains(p)){
            owner.enableGameBoard(3);
        }
        if(button4.contains(p)){
            owner.enableGameBoard(4);
        }

    }

    /**
     * method to repaint the button if the cursor is in the button when the mouse is being pressed
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if(backButton.contains(p)){
            backClicked=true;
            repaint(backButton);
        }
        if(button1.contains(p)){
            button1Clicked=true;
            repaint(button1);
        }
        if(button2.contains(p)){
            button2Clicked=true;
            repaint(button2);
        }
        if(button3.contains(p)){
            button3Clicked=true;
            repaint(button3);
        }
        if(button4.contains(p)){
            button4Clicked=true;
            repaint(button4);
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
        if(button1Clicked==true){
            button1Clicked=false;
            repaint(button1);
        }
        if(button2Clicked==true){
            button2Clicked=false;
            repaint(button2);
        }
        if(button3Clicked==true){
            button3Clicked=false;
            repaint(button3);
        }
        if(button4Clicked==true){
            button4Clicked=false;
            repaint(button4);
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
