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
  - Large classes are broken down to enhance cohesion. ( Crack class is extracted from Brick class )
  - Duplicated codes are removed.
  - Primitive obsession is removed ( Enum type is used for brick types )
- All unused resources are deleted.
- A Screen interface is created to be implemented by all the menus class
- Some of the classes are arranged in MVC pattern
  - Brick class
  - Ball class
  - Player class
- Encapsulated Fields are used to retain private variables ( setter and getter methods are introduced )
- Added some design patterns
  - Applied Singleton design pattern to player class
  - Applied Factory method design pattern on brick class ( GetBrickFactory class is introduced )
- Meaningful JUnit test added
  - Brick Test
  - Player Test
  - Wall Test
  - Ball Test
  - Portal Test 

## Additions
- Implement JavaFX for Start Menu <br/>
![image](https://user-images.githubusercontent.com/93503454/144977353-e1eeef8c-0125-4f7a-bc41-a8e9b056ab29.png)
- Added a Main Menu screen <br/>
![image](https://user-images.githubusercontent.com/93503454/144977429-7c1b634c-0258-4ca0-ae78-ddef458616e3.png)
- Added a Instruction screen <br/>
![image](https://user-images.githubusercontent.com/93503454/144977479-f9c27653-4f95-4c91-9cb3-40527c31c6e9.png)
- Added a Leaderboard screen <br/>
![image](https://user-images.githubusercontent.com/93503454/144977531-0bea16eb-729d-4ab4-b579-adf13f4ef737.png)
- Added a "Select Stage" menu

![image](https://user-images.githubusercontent.com/93503454/144977859-c2f4d71b-8754-4b69-86bc-3e62b7659e92.png)




