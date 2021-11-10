package folder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class InfoMenu extends JComponent implements MouseListener, MouseMotionListener{

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






    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        clear(g2d);
        drawText(g2d);
        drawPlayer(g2d);
        drawButton(g2d);
    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(Color.cyan);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawText(Graphics2D g2d){
        g2d.setFont(titleFont);
        g2d.setColor(Color.BLUE);
        g2d.drawString(INSTRUCTIONS_TEXT,185,50);
        g2d.setFont(descriptionFont);
        g2d.drawString(RIGHT_TEXT,15,185);
        g2d.drawString(LEFT_TEXT,15,340);
    }

    private void drawPlayer(Graphics2D g2d){
        Rectangle player1 = makeRectangle(rightStart,180,150,10);
        Rectangle player2 = makeRectangle(leftStart,335,150,10);
        g2d.setColor(INNER_COLOR);
        g2d.fill(player1);
        g2d.fill(player2);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(player1);
        g2d.draw(player2);
    }

    private void drawButton(Graphics2D g2d){
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

    private Rectangle makeRectangle(int x,int y,int width,int height){
        Point p = new Point((int)(x),y);
        return  new Rectangle(p,new Dimension(width,height));
    }

    private void start(){
        infoTimer.start();
    }

    private void moveRight(){
        rightStart+=1;
    }

    private void moveLeft(){
        leftStart-=1;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        if(backButton.contains(p)){
            owner.enableMainMenu();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if(backButton.contains(p)){
            backClicked=true;
            repaint(backButton);
        }
    }

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
