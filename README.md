# Brick_Destroy

Name : Chua Zhen Hern

OWA : efyzc4

## How To Run
- Open up the project on IntelliJ IDEA after extracting the zip file.

- Download the JavaFX library Version 17.0.1. &nbsp;  [(Click Here to Download)](https://gluonhq.com/products/javafx/)
- Add JavaFX library ( File -> Project Structure -> Libraries )
- Click on the '+' button, Select Java, then direct to the lib folder in your javafx-sdk-17.0.1.
- Click on Edit Configurations ( Modify options -> Add VM options )
- ![image](https://user-images.githubusercontent.com/93503454/144802698-41ef598c-a7c1-425c-84f7-1e88b0f6c2b5.png)

- Add the following line into the VM options
```
--module-path (JavaFX lib Folder Path) --add-modules=javafx.controls
```


## Old descriptions
This is a simple arcace video game.
Player's goal is to destroy a wall with a small ball.
The game has  very simple commmand:
SPACE start/pause the game
A move left the player
D move rigth the player
ESC enter/exit pause menu
ALT+SHITF+F1 open console
the game automatically pause if the frame loses focus


