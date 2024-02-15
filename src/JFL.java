

public class JFL { // Jarvis-Function-Library
 
    /*
    public static SudokuBoard attemptPossibleMovesFromSquare() {
        for (int possibleMoveNumber = 0; possibleMoveNumber < )
    }


    public static SudokuBoard doAIMagic(SudokuBoard boardToSolve) {
    
        SudokuBoard tempBoard = boardToSolve.cloneBoard();

        for (int row = 0; row < rows.length; ++row) {
            for (int squareNumber = 0; squareNumber < rows[row].length; ++squareNumber) {
                
                
                
                System.out.print("[" + row + "][" + squareNumber + "] = ");
                rows[row][squareNumber].printPossibleValues();
            }
        }


    }
*/

    public static SudokuBoard solveBoard(int startingSquare, SudokuBoard board) {
        SudokuSquare[][] rows = board.getRows();
        //System.out.println(startingSquare);

        for (int squareN = startingSquare; squareN < 81; ++squareN) { // loop through all squares starting from specific square
            SudokuSquare square = rows[(squareN - (squareN % 9)) / 9][squareN % 9];
            
            if (square.getSquareValue() != 0) continue; // Square already has a found solution
            
            IntTable possibleValues = square.getPossibleValues();
            for (int i = 0; i < possibleValues.size(); ++i) {
                SudokuBoard clonedBoard = board.cloneBoard();
                clonedBoard.getRows()[(squareN - (squareN % 9)) / 9][squareN % 9].setSquareValue(possibleValues.at(i));

                if (clonedBoard.isSolved())
                    return clonedBoard;
                else if (clonedBoard.isInvalid())
                    continue;
                else { // Not solved but is a valid move
                    SudokuBoard result = solveBoard(squareN + 1, clonedBoard);
                    if (result != null && result.isSolved())
                        return result;
                }
            }
            
            return null;
        }
        return board;
    }



/*
local function check(startingMove, board) {
   
    for (int i = startingMove, i < 81) { // loop through all squares starting from specific square
        
        if squareValue ~= 0 then // square already has a value
            continue
        end 

        local moves = getPossibleMoves()

        for each move {
            local cloneBoard = board.clone()
            cloneBoard.makeMove(i)
            if board.Solved()
                return cloneBoard
            elseif Invalid // created a board with no solution
                // do nothing, try next move
            else // Not solved but was a valid move
                local result = check(i + 1, cloneBoard) // try moves at next square
                if result isA solution
                    return cloneBoard
            end
        }

        return null; // All the moves tried failed, back track

    }

}


check(0, board)
 */

}