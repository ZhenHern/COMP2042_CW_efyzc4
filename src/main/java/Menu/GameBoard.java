/*
 *   test.BrickModel Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Menu;


import Controller.BrickController;
import Controller.PlayerController;
import Controller.WallController;
import Others.*;
import Model.Bricks.Portal;
import View.BallView;
import View.BrickView;
import View.PlayerView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;


/**
 * class for GameBoard
 */
public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener, Screen {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String MAIN_MENU = "Main Menu";
    private static final String PAUSE = "Pause Menu";
    private static String name;
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;
    private int recordedTime=0;
    private int time1=0;
    private int time2=0;
    private int stage=0;
    private int colorChange = 1;
    private String highScore;
    private WallController wall;
    private Portal portal;

    private String message;

    private boolean showPauseMenu;

    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle menuButtonRect;
    private Rectangle restartButtonRect;
    private Polygon leftButton;
    private Polygon rightButton;
    private Color c = Color.GREEN;
    private int strLen;

    private DebugConsole debugConsole;
    private GameFrame owner;
    private HighScore highscore;




    /**
     * class constructor of GameBoard class with two parameters.
     * Timer is started in this class constructor where player can
     * start playing the game.
     * @param owner GameFrame object
     */
    public GameBoard(GameFrame owner, int stage){

        super();
        this.stage=stage;
        this.owner=owner;
        PlayerController.resetPlayer();
        strLen = 0;
        showPauseMenu = false;





        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);


        this.initialize();
        message = "";

        wall = new WallController(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));
        portal = new Portal();
        debugConsole = new DebugConsole(owner,wall,this);
        //initialize the first level
        if(stage ==1){
            wall.nextLevel();
        }
        else if(stage ==2){
            wall.nextLevel();
            wall.nextLevel();
        }
        else if(stage ==3){
            wall.nextLevel();
            wall.nextLevel();
            wall.nextLevel();
        }
        else if(stage ==4){
            wall.nextLevel();
            wall.nextLevel();
            wall.nextLevel();
            wall.nextLevel();
        }
        else if(stage==5){
            wall.nextLevel();
            wall.nextLevel();
            wall.nextLevel();
            wall.nextLevel();
            wall.nextLevel();
        }


        gameTimer = new Timer(10,e ->{

            recordedTime+=1;
            wall.move();
            wall.findImpacts();
            if (stage==5){
                if(time1 == 0) {
                    if (portal.impactPortal1(wall.getBall())) {
                        time1 = 8;
                    }
                }
                else{
                    time1-=1;
                }

                if(time2 == 0) {
                    if (portal.impactPortal2(wall.getBall())) {
                        time2 = 8;
                    }
                }
                else{
                    time2-=1;
                }
            }



            message = String.format("Bricks: %d Balls %d",wall.getBrickCount(),wall.getBallCount());
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    message = "Game over";
                }
                wall.ballReset();
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(stage!=5) {
                    if(highscore == null){
                        highscore = new HighScore("HighScore"+stage+".txt");
                    }
                    this.highscore.readHighScore();
                    if (highscore.newHighScore(recordedTime)) {
                        highScore = Integer.toString(recordedTime);
                        nameInput();
                        highscore.writeHighScore(name, recordedTime);
                    }
                }

                if(wall.hasLevel()){
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    wall.nextLevel();
                }
                else{
                    message = "ALL WALLS DESTROYED";
                    gameTimer.stop();
                }
                recordedTime = 0;

            }

            repaint();
        });

    }


    /**
     * method to initialize the GameBoard window with specific settings
     */
    public void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
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

        g2d.setColor(Color.RED);
        g2d.drawString(message,250,225);

        if (stage==5){
            drawPortal1(g2d,portal.getPortal1());
            drawPortal1(g2d,portal.getPortal2());
            drawPortal2(g2d,portal.getPortal3());
            drawPortal2(g2d,portal.getPortal4());
        }



        wall.getBall().updateView(wall.getBall(),g2d);

        for(BrickController b : wall.getBricks())
            if(!b.isBroken())
                b.updateView(b,g2d);

        wall.getPlayer().updateView(wall.getPlayer(),g2d,c);
        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
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

    @Override
    public void drawButton(Graphics2D d2s) {

    }


    private void drawPortal1(Graphics2D g2d,Shape portal){
        g2d.draw(portal);
        Toolkit t=Toolkit.getDefaultToolkit();
        Image i=t.getImage("p3.gif");
    }

    private void drawPortal2(Graphics2D g2d,Shape portal){
        g2d.setColor(Color.blue);
        g2d.draw(portal);
    }


    /**
     * method to draw the pause folder.Menu
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * method to change the background to a darker color
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * method to draw the buttons and stings for pause menu
     * @param g2d object of Graphics2D which is taken from the "paint" method
     */
    private void drawPauseMenu(Graphics2D g2d){

        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(menuButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            menuButtonRect = menuFont.getStringBounds(MAIN_MENU,frc).getBounds();
            menuButtonRect.setLocation(x,y-menuButtonRect.height);
        }

        g2d.drawString(MAIN_MENU,x,y);
        Polygon leftButton = new Polygon();
        leftButton.xpoints = new int[] {320, 340, 340};
        leftButton.ypoints = new int[] {330, 310, 350};
        leftButton.npoints = 3;
        this.leftButton = leftButton;
        g2d.drawPolygon(this.leftButton);
        g2d.fill(this.leftButton);

        Polygon rightButton = new Polygon();
        rightButton.xpoints = new int[] {560, 540, 540};
        rightButton.ypoints = new int[] {330, 310, 350};
        rightButton.npoints = 3;
        this.rightButton = rightButton;
        g2d.drawPolygon(this.rightButton);
        g2d.fill(this.rightButton);

        Rectangle color = new Rectangle(365,323,150,10);
        g2d.setColor(Color.black);
        g2d.draw(color);
        g2d.setColor(c);
        g2d.fill(color);



    }


    /**
     * method to get the name input from player after achieving high score,
     * A pop-up dialog will appear
     */
    public void nameInput() {
        String name = JOptionPane.showInputDialog(this, "NEW HIGH SCORE!!\n"+highScore.substring(0,highScore.length()-2)+"."+highScore.substring(highScore.length()-2)+"secs"+"\nEnter your name:");
        this.name=name;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * method to move the player position using keyboard,
     * this method is also used to detect the key pressed
     * by player to do certain actions like pausing the game,
     * showing the pause menu and console.
     * @param keyEvent keyboard event
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                wall.getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                wall.getPlayer().moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning()) {
                        gameTimer.stop();
                    }
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                wall.getPlayer().stop();
        }
    }

    /**
     * method to stop the player movement when the key is released
     * @param keyEvent keyboard event
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.getPlayer().stop();
    }

    /**
     *method to add action to the buttons when they are being clicked
     * @param mouseEvent mouse event
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            repaint();
        }
        else if(menuButtonRect.contains(p)){
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            repaint();
            owner.changeScreen(owner.getMainMenu());
        }
        else if(leftButton.contains(p)){
            if(colorChange == 1){
                c = Color.CYAN;
                colorChange = 4;
            }
            else if(colorChange == 2){
                c = Color.GREEN;
                colorChange = 1;

            }
            else if(colorChange == 3){
                c = Color.ORANGE;
                colorChange = 2;

            }
            else if(colorChange == 4){
                c = Color.MAGENTA;
                colorChange = 3;
            }

            repaint();
        }
        else if(rightButton.contains(p)){
            if(colorChange == 1){
                c = Color.ORANGE;
                colorChange = 2;
            }
            else if(colorChange == 2){
                c = Color.magenta;
                colorChange = 3;
            }
            else if(colorChange == 3){
                c = Color.CYAN;
                colorChange = 4;
            }
            else if(colorChange == 4){
                c = Color.GREEN;
                colorChange = 1;
            }
            repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * change the cursor look when it is in the button and also when it is outside of the button
     * @param mouseEvent mouse event
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(menuButtonRect != null && showPauseMenu) {
            if (menuButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p) || leftButton.contains(p) || rightButton.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * method to pause the game when focus is not given to the game window
     */
    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }


}
