
public class SudokuSolver {
    
    public static void main(String[] args) {


        // Check Input
        if (args.length == 0)
            throw new Error("Need Sudoku Input");

        String sudokuInput = args[0];
        if (sudokuInput.length() != 81)
            throw new Error("Sudoku Input is not 81 characters");
        try {
            for (int i = 0; i < 81; ++i) {
                int x = Integer.valueOf(sudokuInput.substring(i, i + 1)); // cant do valueOf(sudokuInput) because its 81 digits
            }
        } catch (NumberFormatException e) {
            throw new Error("Sudoku Input needs to be all numbers");
        }
        

        long startTime = System.nanoTime(); // Start Timer
        SudokuBoard board = new SudokuBoard(sudokuInput);
        long elapsedTime = System.nanoTime() - startTime; // Start Timer
        System.out.println("Sudoku Solver: " + elapsedTime / 1000000.0 + "ms");

        if (!board.isSolved()) {
            //board = JFL.solveBoard(0, board);
        }

        
        if (board != null) {
            System.out.println(board.isInvalid());
            // Print after value insertions
            board.printInFormat();
            //board.printAllPossible(rows)
            board.printRows();
            board.printColumns();
            board.printSections();
        } else {
            System.out.println("NULL");
        }

    }
}
