# Brick_Destroy

Name : Chua Zhen Hern

OWA : efyzc4

## How To Run
- Open up the project on IntelliJ IDEA after extracting the zip file.

- Download the JavaFX library Version 17.0.1. &nbsp;  [(Click Here to Download)](https://gluonhq.com/products/javafx/)
- Inside target folder, right click on Software-Maintenance-CW-main-1.0-SNAPSHOT.jar -> Modify Run Configuration   <br/> 

- Add the following line into the VM options and then click apply
```
--module-path "($PATH_to_FX)\javafx-sdk-17.0.1\lib" --add-modules=javafx.controls
```

- You can now start running the game.


## Git Use
- Record of commit history ( Screenshots of commit history )
- Meaningful commit messages are used when naming the commit 
- Branching and merging concept is applied

## Codes Refactoring
- All classes are arranged organizedly by packages.
- Some of the variable are renamed properly.
- Code smells identified and removed.
  - Large classes are broken down to enhance cohesion. ( Crack class is extracted from Brick class )
  - Duplicated codes are removed.
  - Primitive obsession is removed ( Enum type is used for bricktypes )
- All unused resources are deleted.
- A "Screen" interface is created to be implemented by all the menus class
- Some of the classes are arranged in MVC pattern
  - Brick class
  - Ball class
  - Player class
  - Wall class
- Encapsulated Fields are used to retain private variables ( setter and getter methods are introduced for most of the private variables that are used in other classes )
- Added some design patterns
  - Applied Singleton design pattern to "Player" class to restrict the instantiation of player class and ensures that only one instance of player class
  - Applied Factory method design pattern on brick class to allow subclass which is GetBrickFactory to choose the type of objects to be created ( GetBrickFactory class is introduced )
- Meaningful JUnit test added
  - Brick Test
  - Player Test
  - Wall Test
  - Ball Test
  - Portal Test 

## Additions
- Implement JavaFX for Start Menu <br/> 
![image](https://user-images.githubusercontent.com/93503454/144977353-e1eeef8c-0125-4f7a-bc41-a8e9b056ab29.png) <br/>
- Added a Main Menu screen ( "MainMenu" class )<br/> <br/> 
![image](https://user-images.githubusercontent.com/93503454/144978210-37446ffe-bfd8-4996-a6bc-937856ca1ccd.png) <br/>
- Added a Instruction screen ( "InfoMenu" class )<br/> <br/> 
![image](https://user-images.githubusercontent.com/93503454/144977479-f9c27653-4f95-4c91-9cb3-40527c31c6e9.png) <br/>
- Added a Leaderboard screen which stores permanent high score ( "Leaderboard" class )<br/> <br/> 
![image](https://user-images.githubusercontent.com/93503454/144977531-0bea16eb-729d-4ab4-b579-adf13f4ef737.png) <br/>
- Added a "Select Stage" menu ( "StageMenu" class )<br/> <br/> 
![image](https://user-images.githubusercontent.com/93503454/144977859-c2f4d71b-8754-4b69-86bc-3e62b7659e92.png) <br/>
- Added a special level ( "Portal" class ) <br/> <br/> 
![image](https://user-images.githubusercontent.com/93503454/144978365-e2e0e4b6-6e21-41d6-b883-61eadd0d0826.png) <br/>
- Modify Pause Menu ( Able to change player colour and return to Main Menu ) <br/> <br/>
![image](https://user-images.githubusercontent.com/93503454/144979539-d97fb0bd-c34b-4fc1-bec3-1dd6669d41c2.png) <br/>
- A pop-up dialog will appear to take name input when best record is achieved ( Inside gameboard )<br/> <br/>
![image](https://user-images.githubusercontent.com/93503454/144980851-b0e56456-4a03-46e2-98ad-37c219642e13.png) <br/>

## Javadocs Documentation <br/>
Javadocs comments are added to most of the classes and methods to explain what they do. 




