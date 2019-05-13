
import java.awt.Font;

import java.io.File;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JFrame;

public class SU21589828 {

    // Board size

    static int gridWidth = 6;
    static int gridHeight = 7;
    // The 6-by-7 grid that represents the gameboard, it is initially empty
    public static int[][] grid = new int[gridWidth][gridHeight];
    static boolean player1 = true;

    static boolean gui = false;

    public static int playerValue = 1;
    static int input;
    public static int column;
    public static int rowNum; // row number of last token placed
    public static int colNum; // column number of last token placed
    public static int[] p1powers = { 2, 2, 2 };
    public static int[] p2powers = { 2, 2, 2 };
    static int[] curppowers = new int[3];
    public static int telDiscValue;
    public static int columnValue;
    public static Font font = new Font("fira mono", Font.CENTER_BASELINE, 26);
    public static Font font2 = new Font("fira mono", Font.CENTER_BASELINE, 16);
    public static String playturntext;
    public static int state = 0;
    public static boolean firstPlayerRed = true;

    // This variable can be used to turn your debugging output on and off.
    // Preferably use
    static boolean DEBUG = true;

    // Method to print game logo
    public static void drawLogo() {
        StdOut.println("****************************************************************************");
        StdOut.println("*  _______  _______  __    _  __    _  _______  _______  _______  _   ___  *"
                + "\n* |       ||       ||  |  | ||  |  | ||       ||       ||       || | |   | *"
                + "\n* |       ||   _   ||   |_| ||   |_| ||    ___||       ||_     _|| |_|   | *"
                + "\n* |       ||  | |  ||       ||       ||   |___ |       |  |   |  |       | *"
                + "\n* |      _||  |_|  ||  _    ||  _    ||    ___||      _|  |   |  |___    | *"
                + "\n* |     |_ |       || | |   || | |   ||   |___ |     |_   |   |      |   | *"
                + "\n* |_______||_______||_|  |__||_|  |__||_______||_______|  |___|      |___| *");
        StdOut.println("*                                                                          *");
        StdOut.println("****************************************************************************");

    }

    public static void playerTurnDisplay() {
        if (gui == false) {
            if (player1) {
                StdOut.println("Player 1's turn (You are Red):");
                curppowers = p1powers;

            } else {
                StdOut.println("Player 2's turn (You are Yellow):");
                curppowers = p2powers;
            }
        } else {

            /*
             * if (player1) { StdDraw.setPenColor(214, 48, 49); playturntext = "Red";
             * StdDraw.text(0.2, 0.05, playturntext);
             * 
             * } else { StdDraw.setPenColor(253, 203, 110); playturntext = "Yellow";
             * StdDraw.text(0.2, 0.05, playturntext); }
             */

        }

    }

    // methgod to print move menu
    public static void displayMenu() {
        StdOut.println("Choose your move: \n 1. Play Normal \n 2. Play Bomb (" + curppowers[0]
                + " left) \n 3. Play Teleportation (" + curppowers[1] + " left) \n 4. Play Colour Changer ("
                + curppowers[2] + " left)\n 5. Display Gameboard \n 6. Load Test File \n 0. Exit");
    }

    public static void boardSet() {
        // this method is used to reset the grid to its origional "empty state".

        try {
            for (int i = 0; i <= gridHeight; i++) {
                for (int j = 0; j <= gridWidth; j++) {
                    grid[i][j] = 0;

                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    // This method displays the current grid state
    public static void display(int[][] grid) {

        StdOut.println("");
        System.out.print(" ");
        for (int i = 0; i <= gridWidth; i++)
            System.out.print(" " + i);
        System.out.println("  ");

        // System.out.print(" ");

        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    System.out.print("* ");
                } else if (grid[i][j] == 1) {
                    System.out.print("R ");
                } else if (grid[i][j] == 2) {
                    System.out.print("Y ");
                }

            }
            System.out.println(" ");
        }
        StdOut.println("");

    }

    public static boolean checkWin(int playerValue) {

        try {
            // horizontal check
            for (int i = 0; i < gridHeight; i++) {
                for (int j = 0; j < gridWidth; j++) {
                    if (grid[i][j] == playerValue && grid[i][j + 1] == playerValue && grid[i][j + 2] == playerValue
                            && grid[i][j + 3] == playerValue) {
                        return true;
                    }
                }
            }

        } catch (Exception e) {

        }

        // verticalCheck

        try {
            for (int i = 0; i < gridWidth; i++) {
                for (int j = 0; j < gridHeight; j++) {
                    if (grid[i][j] == playerValue && grid[i + 1][j] == playerValue && grid[i + 2][j] == playerValue
                            && grid[i + 3][j] == playerValue) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            
        }

        try {
            // ascendingDiagonalCheck
            for (int i = 3; i < gridWidth; i++) {
                for (int j = 0; j < gridHeight; j++) {
                    if (grid[i][j] == playerValue && grid[i - 1][j + 1] == playerValue
                            && grid[i - 2][j + 2] == playerValue && grid[i - 3][j + 3] == playerValue)
                        return true;
                }
            }
        } catch (Exception e) {
            
        }

        try {
            // descendingDiagonalCheck
            for (int i = 3; i < gridWidth; i++) {
                for (int j = 3; j < gridHeight; j++) {
                    if (grid[i][j] == playerValue && grid[i - 1][j - 1] == playerValue
                            && grid[i - 2][j - 2] == playerValue && grid[i - 3][j - 3] == playerValue)
                        return true;
                }
            }
        } catch (Exception e) {
            
        }

        return false;
    }

    // CHECK DRAW

    public static boolean checkDraw(int[][] grid) {
        try {
            for (int h = 0; h <= gridWidth; h++) {
                if (grid[0][h] == 0) {
                    return false;
                }
            }

        } catch (Exception e) {
            
        }
        return true;
    }

    public static void play(int column, int playerNum) {
        for (int i = 5; i >= 0; i--) {
            if (grid[i][column] == 0) {
                grid[i][column] = playerNum;

                break;
            }

        }

    }

    public static boolean isPlayable(int column) {
        return grid[0][column] == 0;
    }

    // method to play the bomb disk
    public static void playBomb(int column, int playerNum) {

        // First find the first available point in the column
        // change all values directly around the point to 0
        try {
            for (int i = 5; i >= 0; i--) {
                if (grid[i][column] == 0) {
                    // grid point to the left
                    grid[i][column - 1] = 0;
                    // grid point to the right
                    grid[i][column + 1] = 0;
                    // grid point below

                    grid[i - 1][column] = 0;
                    // grid ppint below left
                    grid[i - 1][column - 1] = 0;
                    // grid poiint below right
                    grid[i - 1][column + 1] = 0;
                    // grid point above left

                    if (i != 5) {
                        grid[i + 1][column - 1] = 0;
                        // grid point above right
                        grid[i + 1][column + 1] = 0;
                        grid[i + 1][column] = 0;

                    }

                    // break the for-loop
                    break;
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // TODO: handle exception
        }

        // now drop all other player tokens to the nearest point (Gravity method by
        // taking coulmn played in as the paramenter)

        // play again as to "Drop to the next available point"

    }

    public static void gravity(int column) {
        // check from the bottom of the grid up (oppisity to play method check) for
        // value 0

        // COLUMN LEFT
        try {
            // find 1st point in the grid that is 0
            for (int i = 5; i >= 0; i--) {
                if (grid[i][column - 1] == 0) {
                    // you have your first empty slot point
                    // now checking the ppoints above that empty point for discs
                    for (int j = i - 1; j >= 0; j--) {
                        // now checking for the first point that isnt 0
                        if (grid[j][column - 1] == 1 || grid[j][column - 1] == 2) {
                            // make the first empty position the value of the recently checked one
                            grid[i][column - 1] = grid[j][column - 1];
                            // make the recently checked column = 0
                            grid[j][column - 1] = 0;
                            // break out of the inner loop as to change the "first empty slot"
                            break;
                        }

                    }

                }

            }

        } catch (ArrayIndexOutOfBoundsException e) {
            // TODO: handle exception
        }

        // COLUMN RIGHT
        try {
            // find 1st point in the grid that is 0
            for (int i = 5; i >= 0; i--) {
                if (grid[i][column + 1] == 0) {
                    // you have your first empty slot point
                    // now check for above values
                    for (int j = i - 1; j >= 0; j--) {
                        // now checking for the first point that isnt 0
                        if (grid[j][column + 1] == 1 || grid[j][column + 1] == 2) {
                            // make the first empty position the value of the recently checked one
                            grid[i][column + 1] = grid[j][column + 1];
                            // make the recently checked column = 0
                            grid[j][column + 1] = 0;
                            // break out of the inner loop as to change the "first empty slot"
                            break;
                        }

                    }

                }

            }

        } catch (ArrayIndexOutOfBoundsException e) {

        }

    }

    public static void teleport(int column, int playerValue) {

        // find the first grid point with a value that is no 0
        try {

            for (int i = 0; i <= 5; i++) {
                if (grid[i][column] == 1 || grid[i][column] == 2) {

                    if (grid[i][column] == 1) {
                        telDiscValue = 1;

                    } else {

                        telDiscValue = 2;
                    }

                    grid[i][column] = playerValue;
                    break;
                }

            }
            columnValue = column + 3;
            if (columnValue > 6) {
                columnValue = columnValue - 7;

            }
            if (isPlayable(columnValue)) {
                play(columnValue, telDiscValue);

            } else {

                StdOut.println("Column is full.");

            }

        } catch (Exception e) {
            // TODO: handle exception

        }

    }

    // COLOUR CHANGE DISC

    public static void colourChange(int column) {

        try {
            for (int i = 0; i <= 5; i++) {
                if (grid[i][column] == 1 || grid[i][column] == 2) {
                    if (grid[i][column] == 1) {
                        grid[i][column] = 2;
                    } else if (grid[i][column] == 2) {
                        grid[i][column] = 1;
                    }
                    break;
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void playerSwitch() {
        if (player1) {
            player1 = false;
            playerValue = 2;
            curppowers = p2powers;
        } else {
            player1 = true;
            playerValue = 1;
            curppowers = p1powers;
        }
    }

    // Fuction to change the starting player of every replayed game
    public static void replayFirstPlayer() {
        // change the firstplayer value
        if (firstPlayerRed == true) {

            firstPlayerRed = false;
            player1 = false;
            playerValue = 2;

        } else if (firstPlayerRed == false) {

            firstPlayerRed = true;
            player1 = true;
            playerValue = 1;

        }

    }

    public static void reset() {
        p1powers[0] = 2;
        p1powers[1] = 2;
        p1powers[2] = 2;

        p2powers[0] = 2;
        p2powers[1] = 2;
        p2powers[2] = 2;
    }

    /*
     * public static void drawframe() { StdDraw.setPenColor(45, 52, 54);
     * StdDraw.filledRectangle(0.5, 0.5, 0.5, 0.5); StdDraw.setPenColor(9, 132,
     * 227); StdDraw.setFont(font); StdDraw.filledRectangle(0.5, 0.5, 0.4, 0.35);
     * StdDraw.text(0.5, 0.95, "Connect 4");
     * 
     * StdDraw.setFont(font2); StdDraw.text(0.07, 0.05, "Player:");
     * 
     * }
     * 
     * public static double[] gridX = { 0.1625, 0.275, 0.3875, 0.5, 0.6125, 0.725,
     * 0.8375 }; public static double[] gridY = { 0.7875, 0.675, 0.5625, 0.45,
     * 0.3375, 0.225, 0.1125 };
     * 
     * // This method displays the current grid state public static void
     * displayGui(int[][] grid) { for (int i = 0; i < grid.length; i++) {
     * 
     * for (int j = 0; j < grid[0].length; j++) { if (grid[i][j] == 0) {
     * StdDraw.setPenColor(223, 230, 233); StdDraw.filledCircle(gridX[j], gridY[i],
     * 0.05); } else if (grid[i][j] == 1) { StdDraw.setPenColor(214, 48, 49);
     * StdDraw.filledCircle(gridX[j], gridY[i], 0.05);
     * 
     * } else if (grid[i][j] == 2) { StdDraw.setPenColor(253, 203, 110);
     * 
     * StdDraw.filledCircle(gridX[j], gridY[i], 0.05); }
     * 
     * }
     * 
     * }
     * 
     * }
     * 
     * public static void wins(int state) { if (state == 0) { StdDraw.clear();
     * StdDraw.setPenColor(45, 52, 54); StdDraw.filledRectangle(0.5, 0.5, 0.5, 0.5);
     * StdDraw.setPenColor(9, 132, 227); StdDraw.setFont(font);
     * 
     * StdDraw.text(0.5, 0.95, "Connect 4"); StdDraw.text(0.5, 0.5, "Draw!");
     * 
     * } else if (state == 1) { StdDraw.clear(); StdDraw.clear();
     * StdDraw.setPenColor(45, 52, 54); StdDraw.filledRectangle(0.5, 0.5, 0.5, 0.5);
     * StdDraw.setPenColor(9, 132, 227); StdDraw.setFont(font);
     * 
     * StdDraw.text(0.5, 0.95, "Connect 4"); StdDraw.text(0.5, 0.5,
     * "Player 1 wins!"); } else if (state == 2) { StdDraw.clear(); StdDraw.clear();
     * StdDraw.setPenColor(45, 52, 54); StdDraw.filledRectangle(0.5, 0.5, 0.5, 0.5);
     * StdDraw.setPenColor(9, 132, 227); StdDraw.setFont(font);
     * 
     * StdDraw.text(0.5, 0.95, "Connect 4"); StdDraw.text(0.5, 0.5,
     * "Player 2 wins!");
     * 
     * }
     * 
     * }
     * 
     * public static int getColumn(Double mouseHorizontal) { Double X =
     * mouseHorizontal; int columnIndex = 0; if (0.1 < X && X < 0.21432) {
     * columnIndex = 0; } if (0.21432 < X && X < 0.3286) { columnIndex = 1; } if
     * (0.3286 < X && X < 0.44288) { columnIndex = 2; } if (0.44288 < X && X <
     * 0.55716) { columnIndex = 3; } if (0.55716 < X && X < 0.67144) { columnIndex =
     * 4; } if (0.67144 < X && X < 0.78572) { columnIndex = 5; } if (0.78572 < X &&
     * X < 0.9) { columnIndex = 6; } return columnIndex; }
     */

    public static void main(String[] args) {

        try {
            if (args[0].equals("T")) {
                gui = false;
            } else if (args[0].equals("G")) {
                gui = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set the baord to normal
        boardSet();

        // check what game mode
        if (gui == false) { // START OF TERMINAL MODE.
            if (player1) {
                curppowers = p1powers;
            } else {
                curppowers = p2powers;
            }
            // PRINT LOGO WHEN PROGRAM STARTS
            drawLogo();

            while (true) {

                playerTurnDisplay();
                // display the menu options
                displayMenu();

                // read the players menu choice

                boolean valid = true;
                // According to the players menu choice give the

                do {
                    while (true) {
                        try {
                            input = StdIn.readInt();
                            break;
                        } catch (InputMismatchException e) {

                            StdOut.println("Please choose a valid option");
                            display(grid);
                            playerTurnDisplay();
                            displayMenu();

                        }

                    }

                    switch (input) {

                    case 0:
                        Exit();
                        break;

                    case 1:
                        StdOut.println("Choose a column to play in:");

                        boolean tryAgain = true;

                        while (true) {
                            try {
                                column = StdIn.readInt();

                                if (column < 0 || column > 6) {
                                    throw new NumberFormatException();
                                } else {
                                    tryAgain = false;
                                }
                                break;
                            } catch (NumberFormatException e1) {

                                System.out.println("Illegal move, please input legal move:");

                            } catch (InputMismatchException e2) {
                                System.out.println("Illegal move, please input legal move:");

                            }

                        }
                        try {
                            if (isPlayable(column) == true) {
                                play(column, playerValue);

                            } else {
                                StdOut.println("Column is full.");

                            }

                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                        valid = true;
                        break;

                    case 2:

                        // TODO: Read in chosen column
                        // TODO: Check that value is within the given bounds, check that the data is
                        // numeric

                        if (curppowers[0] == 2 || curppowers[0] == 1) {

                            StdOut.println("Choose a column to play in:");
                            tryAgain = true;

                            while (true) {
                                try {
                                    column = StdIn.readInt();

                                    if (column < 0 || column > 6) {
                                        throw new ArrayIndexOutOfBoundsException();
                                    } else {

                                        tryAgain = false;
                                    }
                                    break;
                                } catch (ArrayIndexOutOfBoundsException e1) {
                                    // TODO: handle exception
                                    System.out.println("Illegal move, please input legal move:");

                                } catch (InputMismatchException e2) {
                                    System.out.println("Illegal move, please input legal move:");

                                }

                            }

                            // TODO: Play bomb disc in chosen column and reduce the number of bombs left

                            try {
                                if (isPlayable(column) == true) {
                                    playBomb(column, playerValue);
                                    gravity(column);
                                    play(column, playerValue);
                                    // take away a bomb from that players power array
                                    curppowers[0] = curppowers[0] - 1;
                                } else {
                                    curppowers[0] = curppowers[0] - 1;
                                    StdOut.println("Column is full.");

                                }
                            } catch (ArrayIndexOutOfBoundsException e) {

                            }

                            valid = true;

                        } else {

                            StdOut.println("You have no bomb power discs left");
                            display(grid);
                            playerTurnDisplay();
                            displayMenu();
                            valid = false;

                        }

                        break;

                    case 3:

                        if (curppowers[1] == 2 || curppowers[1] == 1) {

                            StdOut.println("Choose a column to play in:");
                            tryAgain = true;

                            while (true) {

                                try {
                                    column = StdIn.readInt();

                                    if (column < 0 || column > 6) {
                                        throw new NumberFormatException();
                                    } else {

                                        tryAgain = false;
                                    }
                                    break;
                                } catch (NumberFormatException e1) {
                                    // TODO: handle exception
                                    System.out.println("Illegal move, please input legal move:");

                                } catch (InputMismatchException e2) {
                                    System.out.println("Illegal move, please input legal move:");

                                }

                            }

                            try {

                                curppowers[1] = curppowers[1] - 1;
                                teleport(column, playerValue);

                            } catch (Exception e) {

                            }

                            valid = true;

                        } else {

                            StdOut.println("You have no teleporter power discs left");
                            display(grid);
                            playerTurnDisplay();
                            displayMenu();
                            valid = false;

                        }
                        break;

                    case 4:
                        if (curppowers[2] == 2 || curppowers[2] == 1) {

                            StdOut.println("Choose a column to play in:");
                            tryAgain = true;

                            while (true) {

                                try {
                                    column = StdIn.readInt();

                                    if (column < 0 || column > 6) {
                                        throw new NumberFormatException();
                                    } else {

                                        tryAgain = false;
                                    }
                                    break;
                                } catch (NumberFormatException e1) {

                                    System.out.println("Illegal move, please input legal move:");

                                } catch (InputMismatchException e2) {
                                    System.out.println("Illegal move, please input legal move:");

                                }

                            }

                            try {

                                curppowers[2] = curppowers[2] - 1;
                                if (grid[5][column] == 0) {
                                    grid[5][column] = playerValue;
                                } else {
                                    colourChange(column);

                                }

                            } catch (Exception e) {

                            }

                            valid = true;

                        } else {
                            // NO POWER DISCS
                            StdOut.println("You have no colour changer power discs left");
                            display(grid);
                            playerTurnDisplay();
                            displayMenu();
                            valid = false;

                        }

                        break;

                    // This part will be used during testing, please DO NOT change it. This will
                    // result in loss of marks
                    case 5:
                        display(grid);
                        // Displays the current gameboard again, doesn't count as a move, so the player
                        // stays the same
                        player1 = !player1;
                        break;

                    // This part will be used during testing, please DO NOT change it. This will
                    // result in loss of marks
                    case 6:
                        grid = Test(StdIn.readString());
                        // Reads in a test file and loads the gameboard from it.
                        player1 = !player1;
                        break;
                    // This part will be used during testing, please DO NOT change it. This will
                    // result in loss of marks
                    case 7:
                        Save(StdIn.readString(), grid);
                        player1 = !player1;
                        break;

                    default: // TODO: Invalid choice was made, print out an error message but do not switch
                        // player turns
                        StdOut.println("Please choose a valid option");
                        display(grid);
                        playerTurnDisplay();
                        displayMenu();
                        valid = false;
                        break;
                    }

                } while (!valid);

                // update and draw the current grid state
                display(grid);

                // Check if someone has won.
                boolean checker = checkWin(playerValue);

                boolean validNew = true;

                if (checkDraw(grid) || (checkWin(1) == true && checkWin(2) == true)) {
                    StdOut.println("Draw!");
                    StdOut.println("Do you want to play again? \n 1. Yes \n 2. No");

                    do {
                        int newGame;

                        while (true) {
                            try {
                                newGame = StdIn.readInt();
                                break;
                            } catch (InputMismatchException e) {
                                StdOut.println("Choose either 1 for Yes or 2 for No:");
                            }

                        }

                        switch (newGame) {
                        case 1:

                            replayFirstPlayer();
                            boardSet();
                            reset();

                            validNew = true;

                            break;

                        case 2:

                            validNew = true;

                            Exit();

                        default:
                            StdOut.println("Choose either 1 for Yes or 2 for No:");
                            validNew = false;
                        }

                    } while (!validNew);

                } else if (checker == true) {
                    if (player1 == true) {
                        StdOut.println("Player 1 wins!");
                        StdOut.println("Do you want to play again? \n 1. Yes \n 2. No");

                        do {
                            int newGame;
                            while (true) {
                                try {
                                    newGame = StdIn.readInt();
                                    break;
                                } catch (InputMismatchException e) {
                                    StdOut.println("Choose either 1 for Yes or 2 for No:");
                                }

                            }

                            switch (newGame) {
                            case 1:
                                replayFirstPlayer();
                                boardSet();
                                reset();

                                validNew = true;
                                break;

                            case 2:
                                Exit();

                                break;

                            default:
                                StdOut.println("Choose either 1 for Yes or 2 for No:");
                                validNew = false;
                            }

                        } while (!validNew);

                    } else if (player1 == false) {
                        StdOut.println("Player 2 wins!");
                        StdOut.println("Do you want to play again? \n 1. Yes \n 2. No");
                        do {
                            int newGame;
                            while (true) {
                                try {
                                    newGame = StdIn.readInt();
                                    break;
                                } catch (InputMismatchException e) {
                                    StdOut.println("Choose either 1 for Yes or 2 for No:");
                                }

                            }

                            switch (newGame) {
                            case 1:
                                replayFirstPlayer();
                                boardSet();
                                reset();
                                validNew = true;

                                break;

                            case 2:

                                validNew = true;

                                Exit();

                            default:
                                StdOut.println("Choose either 1 for Yes or 2 for No:");
                                validNew = false;
                            }

                        } while (!validNew);
                    }

                } else if (checker == false) {

                    // Switch player turns
                    playerSwitch();

                }

            }

        } else if (gui = true) {
            /*
             * drawframe();
             * 
             * while (true) { // Graphics mode
             * 
             * playerTurnDisplay(); displayGui(grid);
             * 
             * while (StdDraw.mousePressed() == false) { if (StdDraw.mousePressed() == true)
             * { if (StdDraw.mouseX() < 0.9 && StdDraw.mouseX() > 0.1) { if
             * (StdDraw.mouseY() > 0.15 && StdDraw.mouseY() < 0.9) {
             * play(getColumn(StdDraw.mouseX()), playerValue); break; }
             * 
             * }
             * 
             * }
             * 
             * }
             * 
             * if (checkWin(playerValue) || checkDraw(grid)) { if (checkDraw(grid) ||
             * checkWin(1) == true && checkWin(2) == true) { wins(0);
             * 
             * } else if (checkWin(1) == true) {
             * 
             * wins(0); } else if (checkWin(2) == true) { wins(2);
             * 
             * }
             * 
             * } else { playerSwitch(); }
             * 
             * }
             */

        }

        // END MAIN METHOD
    }

    private static void Exit() {
        System.exit(1);
    }

    /**
     * Reads in a board from a text file.
     *
     * @param name The name of the given file
     * @return
     */
    // Reads in a game state from a text file, assumes the file is a txt file
    public static int[][] Test(String name) {
        int[][] grid = new int[6][7];
        try {
            File file = new File(name + ".txt");
            Scanner sc = new Scanner(file);

            for (int i = 0; i < gridWidth; i++) {
                for (int j = 0; j < gridHeight; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return grid;
    }

    /**
     * Saves the current game board to a text file.
     *
     * @param name The name of the given file
     * @param grid The current game board
     * @return
     */
    // Used for testing
    public static int[][] Save(String name, int[][] grid) {
        try {
            FileWriter fileWriter = new FileWriter(name + ".txt");
            for (int i = 0; i < gridWidth; i++) {
                for (int j = 0; j < gridHeight; j++) {
                    fileWriter.write(Integer.toString(grid[i][j]) + " ");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return grid;
    }

    /**
     * Debugging tool, preferably use this since we can then turn off your debugging
     * output if you forgot to remove it. Only prints out if the DEBUG variable is
     * set to true.
     *
     * @param line The String you would like to print out.
     */
    public static void Debug(String line) {
        if (DEBUG)
            System.out.println(line);
    }

    // END CLASS
}
