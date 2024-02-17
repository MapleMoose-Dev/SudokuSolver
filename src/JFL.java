
public class JFL { // Jarvis-Function-Library

    public static SudokuBoard solveBoard(int startingSquare, SudokuBoard board) {

        if (startingSquare == 81) // Hit end of sudoku
            return board;

        SudokuSquare[][] rows = board.getRows();
        SudokuSquare square = rows[(startingSquare - (startingSquare % 9)) / 9][startingSquare % 9];

        if (square.getSquareValue() != 0) { // Square already has a found solution
            return solveBoard(startingSquare + 1, board);
        }

        // Square needs to be solved
        IntTable possibleValues = square.getPossibleValues();
        for (int i = 0; i < possibleValues.size(); ++i) {

            SudokuBoard clonedBoard = board.cloneBoard();
            clonedBoard.getRows()[(startingSquare - (startingSquare % 9)) / 9][startingSquare % 9]
                    .setSquareValue(possibleValues.at(i));

            if (!clonedBoard.isInvalid()) { // Not solved but is a valid move
                SudokuBoard result = solveBoard(startingSquare + 1, clonedBoard);
                if (result != null && result.isSolved() && !result.isInvalid())
                    return result;
            }
        }

        return null; // Gone through every possible move in the loop, no solution exist
    }


}