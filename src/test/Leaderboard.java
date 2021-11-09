package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static java.lang.Character.isDigit;


public class Leaderboard extends JComponent implements MouseListener, MouseMotionListener {

    private static String LEADERBOARD = "LEADERBOARD";
    private static String STAGE = "STAGE";
    private static String USERNAME = "NAME";
    private static String BEST_RECORD = "RECORD";
    private static String BACK_TEXT = "BACK";
    private static String string1;
    private static String string2;
    private static String string3;
    private static String string4;
    private static String score1;
    private static String score2;
    private static String score3;
    private static String score4;
    private static String name1;
    private static String name2;
    private static String name3;
    private static String name4;
    private static String tmp;
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private Font titleFont;
    private Font descFont;
    private boolean backClicked;
    private Rectangle backButton;
    private GameFrame owner;

    public Leaderboard(GameFrame owner){
        super();
        this.owner = owner;
        this.initialize();
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
        drawButton(g2d);

    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(Color.cyan);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawText(Graphics2D g2d){

        g2d.drawRect(60,75,480,350);
        g2d.drawLine(60,165,540,165);
        g2d.drawLine(60,230,540,230);
        g2d.drawLine(60,295,540,295);
        g2d.drawLine(60,360,540,360);
        g2d.drawLine(155,75,155,425);
        g2d.drawLine(430,75,430,425);
        titleFont = new Font("Monospaced",Font.BOLD,30);
        descFont = new Font("Monospaced",Font.BOLD,20);
        g2d.setFont(titleFont);
        g2d.setColor(Color.blue);
        g2d.drawString(LEADERBOARD,200,50);
        g2d.setFont(descFont);
        g2d.drawString(STAGE,77,125);
        g2d.drawString(USERNAME,265,125);
        g2d.drawString(BEST_RECORD,448,125);
        g2d.drawString("1",103,200);
        g2d.drawString("2",103,265);
        g2d.drawString("3",103,330);
        g2d.drawString("4",103,395);


        HighScore highScore1 = new HighScore("HighScore1.txt");
        HighScore highScore2 = new HighScore("HighScore2.txt");
        HighScore highScore3 = new HighScore("HighScore3.txt");
        HighScore highScore4 = new HighScore("HighScore4.txt");
        string1 = highScore1.readHighScore();
        string2 = highScore2.readHighScore();
        string3 = highScore3.readHighScore();
        string4 = highScore4.readHighScore();
        if(string1!=null){
            separateString(string1);
            g2d.drawString(name1,175,200);
            createTime(score1);
            g2d.drawString(tmp,440,200);
        }
        else{
            g2d.drawString("N/A",175,200);
            g2d.drawString("-",478,200);
        }
        if(string2!=null){
            separateString(string2);
            g2d.drawString(name2,175,260);
            createTime(score2);
            g2d.drawString(tmp,440,260);
        }
        else{
            g2d.drawString("N/A",175,260);
            g2d.drawString("-",478,260);
        }
        if(string3!=null){
            separateString(string3);
            g2d.drawString(name3,175,330);
            createTime(score3);
            g2d.drawString(tmp,440,330);
        }
        else{
            g2d.drawString("N/A",175,330);
            g2d.drawString("-",478,330);
        }
        if(string4!=null){
            separateString(string4);
            g2d.drawString(name4,175,395);
            createTime(score4);
            g2d.drawString(tmp,440,395);
        }
        else{
            g2d.drawString("N/A",175,395);
            g2d.drawString("-",478,395);
        }
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

    private void separateString(String string){
        for(int i=0;i<string.length();i++){
            if(!isDigit(string.charAt(i))){
                if(string==string1){
                    score1 = string.substring(0,i);
                    name1 = string.substring(i);
                    break;
                }
                else if(string==string2){
                    score2 = string.substring(0,i);
                    name2 = string.substring(i);
                    break;
                }
                else if(string==string3){
                    score3 = string.substring(0,i);
                    name3 = string.substring(i);
                    break;
                }
                else if(string==string4){
                    score4 = string.substring(0,i);
                    name4 = string.substring(i);
                    break;
                }
            }
        }
    }

    private void createTime(String string){
        int length = string.length();
        String front;
        String end;
        front = string.substring(0,length-2);
        end = string.substring(length-2,length);
        string = front+"."+end+"s";
        this.tmp = string;
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
