# TicTacToe
Tic Tac Toe Kata (https://github.com/stephane-genicot/katas/blob/master/TicTacToe.md) 

## The project
This project is a KATA of the Tic Tac Toe game made for android. The language used for this project is Kotlin. The project is made using TDD. All unit tests were made using JUnit 4. The application is made for Android API 24 (Android 7.0 Nougat).

## Branches
The git consists of 2 branches:
- The main branch which only has tested and working code.
- The development branch which is used to develop new functionality for the app.

## The code
### Unit tests
The unit tests are located in the TicTacToeTest class in the test directory (not androidTest). Every unit test is provided with printed text in the terminal to provide a visual representation of what is happening. All unit tests can be run at once or separate.
### The app
The app consists of 1 activity called MainActivity. The app consists of 3 classes:
- MainActivity.kt (which uses objects of TicTacToe and Player)
- TicTacToe.kt (which implements IGame interface)
- Player.kt
The app could be run on any android phone or android emulator. All code is written using OOP principles to make it easy to expand or change the code in the future. Hardcoded code is kept to a minimum. 

# Versions
- 1.0: The minimum viable product. The game could be played.
- 1.1: Preformed some code cleanup. All the hardcoded strings were moved to the String.xml file.
- 1.2: Improved experience. The game state is saved when the life cycle of the app changes. For example:
  - When the app is no longer in focus
  - When the user rotates his screen
  - ...
- 1.3: Improved UI. A new UI, which represents a scratch sheet/notepad.

## Game play
- X always goes first.
- Players cannot play on a played position.
- Players alternate placing X’s and O’s on the board until either:
  - One player has three in a row, horizontally, vertically or diagonally
  - All nine squares are filled.
- If a player is able to draw three X’s or three O’s in a row, that player wins.
- If all nine squares are filled and neither player has three in a row, the game is a draw.
- To restart the game, press the eraser button at the bottom of the screen.
