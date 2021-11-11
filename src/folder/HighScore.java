package folder;

import java.io.*;
import java.util.Scanner;

import static java.lang.Character.isDigit;


/**
 * class for HighScore
 */
public class HighScore {

    private static int score1 = 0;
    private String filename;

    /**
     * class constructor for HighScore which is used to create a file to store the high score
     * @param filename name of the file to be created
     * @exception IOException if stream to file cannot be written to or closed
     */
    public HighScore(String filename) {
        this.filename=filename;
        try {
            File myObj = new File("C:\\Users\\User\\Desktop\\hi\\"+filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                System.out.println("Absolute path: " + myObj.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * method to determine whether the player has achieved the best record
     * @param time time taken to complete the stage
     * @return return true if it is a new record
     */
    boolean newHighScore(int time) {
        if(time<score1 || score1==0) {
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * method to write the player name and time taken when high score is achieved
     * @param name name of the player
     * @param time time taken to complete the stage
     * @exception IOException if stream to file cannot be written to or closed
     */
    void writeHighScore(String name,int time){
        try{
            FileWriter myWriter = new FileWriter(filename);
            String timeUsed = Integer.toString(time);
            myWriter.write(timeUsed);
            myWriter.write(name);
            myWriter.close();
        }
        catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * method to read the high score file and return the string,
     * the best time record is stored
     * @return return the string from the file
     * @exception FileNotFoundException if file does not exist
     */
    String readHighScore(){
        try{
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            if (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String tmp = data;
                for(int i=0;i<data.length();i++){
                    if(!isDigit(data.charAt(i))){
                        data = data.substring(0,i);
                        break;
                    }
                }
                int highScore1 = Integer.parseInt(data);
                score1 = highScore1;
                return tmp;
            }
            else{
                score1 = 0;
                return null;
            }
        }
        catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }



}
