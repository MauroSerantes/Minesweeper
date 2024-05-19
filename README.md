# Minesweeper Game

_This is the well know game minesweeper for android. This is a personal version of the game._

  
### About the game 📋

_The main objective of the game is to clear the board without detonating any mines, with help from clues about the number of neighboring mines in each field._
_The game count with three different levels of difficulties:_

```
BEGGINER (64 tiles and 10 mines)
```
```
MEDIUM (256 tiles and 40 mines)
```
```
ADVANCED (480 tiles and 99 mines)
```
#### Here some screeshots of the main presentation of the game
![Screenshot_20240519_121926](https://github.com/MauroSerantes/Minesweeper/assets/146656323/70c58d88-7038-415d-899b-d3d4daa6ef06) ![Screenshot_20240519_122002](https://github.com/MauroSerantes/Minesweeper/assets/146656323/6eea5966-9edf-463d-89a9-66aef9a5e78b)



### Instructions for play 

_The main game includes three command buttons: Reset Button, Dig Mode Button (Shovel), Flag Mode Button (Flag)._

_When Dig Mode is activate you can reveal any cell exept the ones that are marked by a flag._

_When Flag Mode is activate you can mark or dismark any cell with a flag (The number of flags is equal to the total number of mines)_

_The reset button if for reset the game_

#### Here some screeshots of the main game

![Screenshot_20240519_122219](https://github.com/MauroSerantes/Minesweeper/assets/146656323/e9860cb3-1f27-4d50-bf10-0e0d6e0fe3d6) ![Screenshot_20240519_122351](https://github.com/MauroSerantes/Minesweeper/assets/146656323/edf65657-2a68-4465-89e6-b97b3e04205c) ![Screenshot_20240519_122250](https://github.com/MauroSerantes/Minesweeper/assets/146656323/06df6df6-84d4-47d3-a95b-5756ba14a155)






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



