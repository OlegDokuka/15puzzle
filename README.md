# 15puzzle

## How To Build

*This Project is based on Gradle Wrapper Build tool.*

Open your terminal in the project folder and run following command:

``` shell
./gradlew clean jar
```

## How To Run

To Run compiled game execute the following command:

``` shell
java -jar ./build/libs/15puzzle*
```

## How To Play

After the game has been run, you will see similar to the following output
<pre>
---------------------------------
|       |       |       |       |
|   6   |  10   |   9   |   7   |
|       |       |       |       |
---------------------------------
|       |       |       |       |
|   2   |  12   |  15   |   8   |
|       |       |       |       |
---------------------------------
|       |       |       |       |
|  14   |  11   |   4   |   5   |
|       |       |       |       |
---------------------------------
|       |       |       |       |
|   1   |   3   |  13   |       |
|       |       |       |       |
---------------------------------
Please, use following game-play commands :
  * Use 1 to move element from left to right empty space
  * Use 2 to move element from right to left empty space
  * Use 3 to move element from top to bottom empty space
  * Use 4 to move element from bottom to top empty space
  * Use r to restart the game
  * Use q to quit the game
  * Use h to show help menu
</pre>

The first part of the content is **GameBoard**.
The second part of the output is helping window with available terminal commands.

> *Note*: All commands will be executed only after you have pressed ***enter*** on the keyboard

> *Important*: All movement (from left to right, etc) is relative to the puzzle without number, so it means that by entering **1** you will swap ***13*** with ***empty*** puzzle.

<pre>
---------------------------------
|       |       |       |       |
|   6   |  10   |   9   |   7   |
|       |       |       |       |
---------------------------------
|       |       |       |       |
|   2   |  12   |  15   |   8   |
|       |       |       |       |
---------------------------------
|       |       |       |       |
|  14   |  11   |   4   |   5   |
|       |       |       |       |
---------------------------------
|       |       |       |       |
|   1   |   3   |       |  13   |
|       |       |       |       |
---------------------------------
</pre>
