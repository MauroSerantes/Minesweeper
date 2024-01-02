# Minesweeper Game

_This is a the well know game minesweeper for android. This is a personal version of the game._

  
### About the game 📋

_The main objective of the game is to clear the board without detonating any mines, with help from clues about the number of neighboring mines in each field._
_The game count with three different levels of difficulties:_

```
BEGGINER (96 tiles and 20 mines)
```
```
MEDIUM (120 tiles and 30 mines)
```
```
ADVANCED (160 tiles and 40 mines)
```
#### Here some screeshots of the main presentation of the game
![Screenshot_20240102-112912_Minesweeper](https://github.com/MauroSerantes/Minesweeper/assets/146656323/299ab8a4-8f48-4585-ad2e-2b6e83050209)
![Screenshot_20240102-112919_Minesweeper](https://github.com/MauroSerantes/Minesweeper/assets/146656323/7cf8288c-a2df-47ed-a339-0e3e61fcf594)


### Instructions for play 

_The main game includes three command buttons: Reset Button, Dig Mode Button (Shovel), Flag Mode Button (Flag)._

_When Dig Mode is activate you can reveal any cell exept the ones that are marked by a flag._

_When Flag Mode is activate you can mark or dismark any cell with a flag (The number of flags is equal to the total number of mines)_

_The reset button if for reset the game_

#### Here some screeshots of the main game

![Screenshot_20240102-112942_Minesweeper](https://github.com/MauroSerantes/Minesweeper/assets/146656323/1dc9d177-4ffd-4b13-b1d8-8708346e6293)
![Screenshot_20240102-113049_Minesweeper](https://github.com/MauroSerantes/Minesweeper/assets/146656323/79ec230b-7d16-43d8-83c2-cf3e5ce4e48e)



## Tech Stack Used And Architectural pattern ⚙️

* Xml (eXtensible Markup Language) - For the views
* Navigation Component - For simple navigation between fragments
* MVP(Model-View-Presenter) - Main Architecture pattern
* Kotlin - The main language

### Personal Commentaries
_I use MVP architecture with the finality of separate the logic of the game from the view._
_With the use of MVP I create a main game presenter wich controlls all the logic of the game when_
_the main view (Game Fragment) just show all user interactions._

_In the domain file there are some auxiliar classes and structures for made the game more_
_simple to implement. These code is one of the multiple ways of implement the game._

## Authors ✒️

* **Mauro Serantes** - [Mauro Serantes](https://github.com/MauroSerantes)

## For The Future

* Add a timer
* Add an online mode
* Improve the UI



