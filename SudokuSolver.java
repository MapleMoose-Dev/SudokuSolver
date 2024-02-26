
import Table.IntTable;


/**
 * Main Class for solving Sudokus
 */
public class SudokuSolver {
   
    
    /*
        Back tracking function
     */
    private static SudokuBoard solveBoard(int startingSquare, SudokuBoard board) {

        if (startingSquare == 81) // Hit end of sudoku
            return board;
        
        // Get the square to check
        SudokuSquare square = board.getRows()[(startingSquare - (startingSquare % 9)) / 9][startingSquare % 9];


        if (square.getSquareValue() != 0) { // Square already has a found solution
            return solveBoard(startingSquare + 1, board); // Search the next square
        }

        // Square needs to be solved
        IntTable possibleValues = square.getPossibleValues();
        for (int i = 0; i < possibleValues.size(); ++i) { // Loop through all the possible values

            // For each possible option, 
            SudokuBoard clonedBoard = board.cloneBoard();  // Clone the board
            clonedBoard.getRows()[(startingSquare - (startingSquare % 9)) / 9][startingSquare % 9].setSquareValue(possibleValues.at(i)); // Set the square's value option

            if (!clonedBoard.isInvalid()) { // Possible option was a valid option, option never violated any other domains.
                SudokuBoard result = solveBoard(startingSquare + 1, clonedBoard); // Recurse to the next square
                if (result != null && result.isSolved() && !result.isInvalid()) // Returned solution is solved and is not invalid
                    return result; // Return solution
            }
        }

        return null; // Gone through every possible move in the loop, no solution exist, recurse back up
    }

    public static void main(String[] args) {


        //--------------------------------------
        //------------ Start Up --------------
        //--------------------------------------

        // Check for any input
        if (args.length == 0)
            throw new Error("Need Sudoku Input");

        // Input must be 81 chars
        String sudokuInput = args[0];
        if (sudokuInput.length() != 81)
            throw new Error("Sudoku Input is not 81 characters");

        // Input must be all numbers
        try {
            for (int i = 0; i < 81; ++i) 
                Integer.valueOf(sudokuInput.substring(i, i + 1)); // cant do valueOf(sudokuInput) because its 81 digits    
        } catch (NumberFormatException e) {
            throw new Error("Sudoku Input needs to be all numbers");
        }
        
      

        //--------------------------------------
        //-------- Check Board ------------
        //--------------------------------------

        long startTime = System.nanoTime(); // Start Timer
        SudokuBoard board = new SudokuBoard(sudokuInput);

        if (!board.isSolved())  // If the board was not solely solved by constraint propagation
            board = solveBoard(0, board); // Backtrack search, starting at square 0
        
         //--------------------------------------
        //------ Completed Search ------
        //--------------------------------------
        
        long elapsedTime = System.nanoTime() - startTime; // Start Timer

        if (args.length >= 2 && args[1].equals("-t")) // Print out time taken only with -t as a second argument
            System.out.println("Sudoku Solver: " + elapsedTime / 1000000.0 + "ms");

        if (board != null && !board.isInvalid() ) {  // Found a solution to the board
            
            board.printInFormat();
        //board.printAllPossible(rows)
         //   board.printRows();
        //    board.printColumns();
         //   board.printSections();
        } else {
            System.out.println("No Solution to the board exists");
        }

    }
}
