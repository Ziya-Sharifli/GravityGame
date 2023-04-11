import enigma.console.TextAttributes;
import enigma.core.Enigma;

import java.awt.*;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Initialization {

    public enigma.console.Console cn = Enigma.getConsole("Gravity ALPHA"); // new Enigma console with title
    String[][] square = new String[55][25]; // create hollow square array which is used to keep track of what's inside the square
    int earthCount = 0; // counter for the amount of earths
    public TextAttributes playerColor = new TextAttributes(Color.GREEN);
    public TextAttributes robotColor = new TextAttributes(Color.YELLOW);
    public TextAttributes treasureColor = new TextAttributes(Color.RED);

    Random random = new Random(); // random class usage


    Initialization() {
        walls();
        earth();
        boulders();
        treasure();
        emptySquare();
        robots();
        player();
        stats();
    }


    void walls() { // printing of the walls

        int i, j;
        for (i = 1; i <= 25; i++) { // for loop that iterates over rows and columns and prints #
            for (j = 1; j <= 55; j++) {
                if (i == 1 || i == 25 ||
                        j == 1 || j == 55)
                    System.out.print("#"); // prints the walls if it is on the given boundaries
                else
                    System.out.print(" "); // prints an empty string to create a hollow square
            }
            System.out.println();
        }

        for (int e = 0; e < 50; e++) { // side wall print top
            cn.getTextWindow().setCursorPosition(e, 8);
            cn.getTextWindow().output(cn.getTextWindow().getCursorX(),cn.getTextWindow().getCursorY(), '#');
            square[e][8] = "#"; // updates the square array
        }


        for (int k = 5; k < 54 ; k++) { // side wall print bottom
            cn.getTextWindow().setCursorPosition(k, 16);
            cn.getTextWindow().output(cn.getTextWindow().getCursorX(),cn.getTextWindow().getCursorY(), '#');
            square[k][16] = "#"; // updates the square array
        }


    }

    void earth() {

        for (int i = 1; i < 54; i++) { // for loop that iterates over columns and rows
            for (int j = 1; j < 24; j++) {
                cn.getTextWindow().setCursorPosition(i, j);
                if (square[i][j] != "#")  { //checks if square array contains # , if it does change it to
                                                              // : and prints it to console
                    square[i][j] = ":";
                    earthCount++;

                    cn.getTextWindow().output(i,j, ':');
                }
            }
        }

       // for (String[] row : square)

            // converting each row as string
            // and then printing in a separate line
         //   System.out.println(Arrays.toString(row));


    }

    void boulders() {

        cn.getTextWindow().setCursorPosition(0, 0);
        int loopCounter = 0;
        while (loopCounter < 180) {
            int r1 = random.nextInt(55); // get a random column
            int r2 = random.nextInt(25); // get a random row
            if (square[r1][r2] == ":") { // checks if the random column and row contain earth, if it does, it changes to boulder
                cn.getTextWindow().setCursorPosition(r1, r2);
                square[r1][r2] = "O";
                cn.getTextWindow().output(r1,r2, 'O');;
                loopCounter++;
                earthCount--; // update the earth counter
            }
        }
    }

    void stats() { // earth counter function
        cn.getTextWindow().setCursorPosition(60, 0);
        System.out.println("================");
        cn.getTextWindow().setCursorPosition(60, 1);
        System.out.println("Earth count: "+earthCount);
        cn.getTextWindow().setCursorPosition(60, 2);
        System.out.println("================");

    }

    void treasure() { // treasure function

        int counter = 0;
        while (counter < 30) {
            int randomNum = random.nextInt(3)+1; // get a random number for treasure types
            char treasureNum = ' ';
            switch (randomNum) { // selects a random treasure using switch case, which updates a string
                case 1:
                    treasureNum = '1';
                    break;
                case 2:
                    treasureNum = '2';
                    break;
                case 3:
                    treasureNum = '3';
                    break;
                default:
                    treasure();
            }
            int treasureX = random.nextInt(55); // get random column
            int treasureY = random.nextInt(25); // get random row
            if (square[treasureX][treasureY] == ":") { // check if random column and row contain :
                cn.getTextWindow().setCursorPosition(treasureX, treasureY);
                square[treasureX][treasureY] = String.valueOf(treasureNum); // update the hollow square array
                cn.getTextWindow().output(treasureX,treasureY,treasureNum, treasureColor);; // if random column and row contain :, it changes to the random chosen treasure from the switch case
                counter++;
                earthCount--; // update earth counter
            }
        }

    }

    void emptySquare() { // change earths to empty squares

        int counter = 0;
        while (counter < 200) {
            int randomX = random.nextInt(55); // random row
            int randomY = random.nextInt(25); // random column
            if (square[randomX][randomY] == ":") { // checks if random row and column contain earth
                cn.getTextWindow().setCursorPosition(randomX,randomY);
                square[randomX][randomY] = " "; // update hollow square array
                cn.getTextWindow().output(randomX,randomY, ' '); // print empty earth square on screen
                counter++;
                earthCount--; // update earth counter
            }

        }

    }

    void robots() { // place robots on the game
        int counter = 0;
        while (counter < 7) {
            int randomX = random.nextInt(55); // random columns
            int randomY = random.nextInt(25); // random row
            if (square[randomX][randomY] == ":") { // check if random row and column contain earth
                cn.getTextWindow().setCursorPosition(randomX, randomY);
                square[randomX][randomY] = "X"; // if it does, change : to X (robot)
                cn.getTextWindow().output(randomX,randomY, 'X', robotColor);
                counter++;
                earthCount--;
            }
        }
    }

    void player() { // player placement

        int counter = 0;
        while (counter < 1) { // while loop has to complete until player is placed
            int randomX = random.nextInt(55); // random column
            int randomY = random.nextInt(25); // random row
            if (square[randomX][randomY] == ":") { // check if random row and column contain earth
                cn.getTextWindow().setCursorPosition(randomX, randomY);
                square[randomX][randomY] = "P";
                cn.getTextWindow().output(randomX,randomY,'P', playerColor);; // if it does, change : to P (player)
                earthCount--;
                counter++;
            }
        }


    }



}
