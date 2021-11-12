/*
 *  test.Brick Destroy - A simple Arcade video game
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
package folder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


/**
 * GameFrame class
 * Refactor : A JComponent named "component" is introduced to store the
 * current JComponent when switching between screens. All the changing screen method
 * can use the "component" variable to do action. For example,this.remove(component) is
 * used instead of this.remove(HomeMenu). In order to change screen, the previous JComponent must
 * be removed. With this variable, the component can be easily removed, without having to call
 * this.remove() method multiple times as some of the screen can be accessed from different screen.
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";


    /**
     * Refactor: encapsulation has been improved on certain
     * variable. Set and get methods are introduced for public
     * access.
     */
    private GameBoard gameBoard;
    private HomeMenu homeMenu;
    private MainMenu mainMenu;
    private InfoMenu infoMenu;
    private Leaderboard leaderboard;
    private StageMenu stageMenu;
    private JComponent component;

    private boolean gaming;

    /**
     * class constructor for GameFrame
     */
    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        setMainMenu(new MainMenu(this));

        setInfoMenu(new InfoMenu(this));

        setLeaderboard(new Leaderboard(this));

        setStageMenu(new StageMenu(this));

        homeMenu = new HomeMenu(this,new Dimension(450,300));

        enableHomeMenu();


    }

    /**
     *method to give a fixed setting to the newly created window
     *
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * method to open the game board.
     * @param stage To determine which stage is being chosen
     *
     */
    public void enableGameBoard(int stage){
        this.dispose();
        this.remove(component);
        gameBoard = new GameBoard(this,stage);
        this.component = gameBoard;
        this.add(component,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /**
     * method to start the home menu
     */
    public void enableHomeMenu(){
        this.component = homeMenu;
        this.add(component,BorderLayout.CENTER);
        this.setUndecorated(true);
    }


    /**
     * method to change screen
     * Refactor : all the enable method have been changed
     * to this single method. (Ex : enableGameBoard() )
     * @param component new component that is going to appear
     */
    public void changeScreen(JComponent component){
        this.dispose();
        this.remove(this.component);
        this.component = component;
        this.add(this.component,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);
    }




    /**
     * method to locate the window at the center of the screen
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    /**
     * method to determine whether the window is getting the focus or not
     * @param windowEvent An event that happen to the window
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * method to determine whether the focus for the window is lost or not
     * @param windowEvent An event that happen to the window
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming&& gameBoard !=null)
            gameBoard.onLostFocus();
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public InfoMenu getInfoMenu() {
        return infoMenu;
    }

    public void setInfoMenu(InfoMenu infoMenu) {
        this.infoMenu = infoMenu;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    public StageMenu getStageMenu() {
        return stageMenu;
    }

    public void setStageMenu(StageMenu stageMenu) {
        this.stageMenu = stageMenu;
    }

}
