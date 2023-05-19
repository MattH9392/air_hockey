This ZIP file contains the following files for the SCC.110 project "Air Hockey" (Project 2 in 2022/23):

Java classes (.java files)

Ball.java
GameArena.java
Line.java
Rectangle.java
Text.java
SoundPlayer.java
Driver.java

Sound effects (.wav files)

applause.wav
bounce.wav
drumroll.wav
fanfare.wav
hit.wav

The folder \docs contains JavaDoc documentation. Click on allclasses-index.html to access the documentation.


***GAME CONTROLS***

To move:
- Player 1: w, a, s, d
- Player 2: i, j, k, l

Players are only able to move within the boundaries of the arena and cannot cross over to the opponent's side.

Sounds can be toggled by pressing 'v'.


The default score limit is set to 6, however in order to play the game the user must enter their own score into the terminal.
Players score a point by hitting the puck into the opponent's goal.
When somebody scores a point, the position of the players are reset and the puck starts on the conceding player's side of the table
If a player reaches the score limit, that player wins the game. The user is then prompted to press either 'space' to restart the game or 'q' to exit.


There are two "cheat codes" currently implemented - both of which are activated by entering a specific score limit:

50: Player 1's mallet size is doubled
60: Player 2's mallet size is doubled
