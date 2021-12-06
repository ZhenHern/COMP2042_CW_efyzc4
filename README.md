# Brick_Destroy

Name : Chua Zhen Hern

OWA : efyzc4

## How To Run
- Open up the project on IntelliJ IDEA after extracting the zip file.

- Download the JavaFX library Version 17.0.1. &nbsp;  [(Click Here to Download)](https://gluonhq.com/products/javafx/)
- Add JavaFX library ( File -> Project Structure -> Libraries )
- Click on the '+' button, Select Java, then direct to the lib folder in your javafx-sdk-17.0.1.
- Click on Edit Configurations ( Modify options -> Add VM options )  <br/> ![image](https://user-images.githubusercontent.com/93503454/144802698-41ef598c-a7c1-425c-84f7-1e88b0f6c2b5.png)

- Add the following line into the VM options and then click apply
```
--module-path (JavaFX lib Folder Path) --add-modules=javafx.controls
```
- In my case, the path looks like this
```
--module-path "C:\Users\User\Desktop\javafx-sdk-17.0.1\lib" --add-modules=javafx.controls
```
- You can now start running the game.

## Codes Refactoring
- All classes are arranged organizedly by packages.
- Some of the variable are renamed properly.
- Code smells identified and removed.
  - Large classes are broken down to enhance cohesion.
  - Duplicated codes are removed.
  - Primitive obsession is removed ( Enum type is used for brick types )




