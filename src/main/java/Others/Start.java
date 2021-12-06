package Others;

import Menu.GameFrame;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Start class
 */
public class Start {
    private Stage stage;
    private Scene scene;
    private GridPane gridPane;
    private BorderPane borderPane;
    private GameFrame owner;

    /**
     * Constructor class to create the display for start menu.
     * JavaFX is used to create the start menu window
     * @param stage javafx stage
     * @param owner frame for start menu
     * @throws Exception
     */
    public Start(Stage stage, GameFrame owner) throws Exception {
        this.owner = owner;
        this.stage = stage;
        borderPane =new BorderPane();
        gridPane = new GridPane();
        Image img = new Image("background_image.jpg",600,450,false,false);
        BackgroundImage bImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);

        borderPane.setBackground(bGround);

        scene = new Scene(borderPane, 600, 450);




        //create buttons
        Font font1 = Font.font("Brush Script MT", FontWeight.BOLD, 25);
        Button startButton= new Button("START  GAME");
        startButton.setMinSize(250,100);
        startButton.setFont(font1);
        Button exitButton= new Button("EXIT");
        exitButton.setMinSize(250,100);
        exitButton.setFont(font1);
        startButton.setOnAction(click ->{
            owner.changeScreen(owner.getMainMenu());
            stage.close();
        });
        exitButton.setOnAction(click ->{
            stage.close();
        });


        //display words
        Font font2 = Font.font("Brush Script MT", FontWeight.BOLD, 50);
        Text title = new Text("BRICK  DESTROYER");
        title.setFont(font2);
        title.setFill(Color.WHITE);
        title.setStroke(Color.BLACK);
        title.setX(100);
        title.setY(60);
        borderPane.getChildren().addAll(gridPane,title);
        gridPane.setVgap(15);
        gridPane.setLayoutX(300);
        gridPane.setLayoutY(250);

        //grid pane settings
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(startButton, 0,1);
        gridPane.add(exitButton, 0,2);

        //stage settings
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Brick Destroyer");
        stage.setX(460);
        stage.setY(190);
        stage.hide();
        stage.show();

    }
}

