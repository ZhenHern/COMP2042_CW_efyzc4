package folder;

import java.io.*;
import java.util.Scanner;

import static java.lang.Character.isDigit;


public class HighScore {

    private static int score1 = 0;
    private String filename;

    HighScore(String filename) {
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

    boolean newHighScore(int time) {
        if(time<score1 || score1==0) {
            return true;
        }
        else{
            return false;
        }
    }



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
