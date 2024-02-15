
public class SudokuBoard {

    private SudokuSquare rows[][]; // 2D array of SudokuSquares
    private SudokuSquare columns[][]; // 2D array of SudokuSquares
    private SudokuSquare sections[][]; // 2D array of SudokuSquares

    private void initializeArrays() {

        // The arrays hold the same 81 squares, it points to different squares just in a different format
        rows = new SudokuSquare[9][9];
        columns = new SudokuSquare[9][9];
        sections = new SudokuSquare[9][9]; // left to right, top to bottom

        // Rows
        for (int row = 0; row < rows.length; ++row) {
            for (int squareNumber = 0; squareNumber < rows[row].length; ++squareNumber)
                rows[row][squareNumber] = new SudokuSquare();
        }

        // Columns
        for (int column = 0; column < columns.length; ++column) {
            for (int squareNumber = 0; squareNumber < columns[column].length; ++squareNumber) {
                columns[column][squareNumber] = rows[squareNumber][column]; // Same position but flipped
            }
        }

        // Sections
        int verticalOffset = 0;
        int horizontalOffset = 0;
        for (int section = 0; section < sections.length; ++section) {

            // Manually add each 3x3 grid
            sections[section][0] = rows[verticalOffset][horizontalOffset];
            sections[section][1] = rows[verticalOffset][1 + horizontalOffset];
            sections[section][2] = rows[verticalOffset][2 + horizontalOffset];
            sections[section][3] = rows[1 + verticalOffset][horizontalOffset];
            sections[section][4] = rows[1 + verticalOffset][1 + horizontalOffset];
            sections[section][5] = rows[1 + verticalOffset][2 + horizontalOffset];
            sections[section][6] = rows[2 + verticalOffset][horizontalOffset];
            sections[section][7] = rows[2 + verticalOffset][1 + horizontalOffset];
            sections[section][8] = rows[2 + verticalOffset][2 + horizontalOffset];

            horizontalOffset += 3;

            if (horizontalOffset == 9) {
                verticalOffset += 3;
                horizontalOffset = 0;
            }
        }

        // Set Square Row, Column and Section
        for (int row = 0; row < rows.length; ++row) {
            for (int squareNumber = 0; squareNumber < rows[row].length; ++squareNumber)
                rows[row][squareNumber].setRow(rows[row]);
        }
        for (int col = 0; col < columns.length; ++col) {
            for (int squareNumber = 0; squareNumber < columns[col].length; ++squareNumber)
                columns[col][squareNumber].setColumn(columns[col]);
        }
        for (int section = 0; section < sections.length; ++section) {
            for (int squareNumber = 0; squareNumber < sections[section].length; ++squareNumber)
                sections[section][squareNumber].setSection(sections[section]);
        }

    }

    // Set the value of each square from input string
    public void insertDataIntoRows(String input) {
        printInFormat();
        int squareNumber = 0;
        int row = 0;

        for (int i = 0; i < 81; ++i) { // loop through input string
            int squareValue = Integer.valueOf(input.substring(i, i + 1));
            try{
            rows[row][squareNumber].setSquareValue(squareValue);
            }
            catch (Exception e) {
                printInFormat();
                throw e;
            }

            ++squareNumber; // keep track of which row/square number its at
            if (squareNumber == 9) { // hit the end of the row, move down a row
                ++row;
                squareNumber = 0;
            }
        }
    }

    // Set the value of each square from input string
    public void insertDataIntoRows(SudokuSquare[][] rowToCopyFrom) {
        int squareNumber = 0;
        int row = 0;

        for (int i = 0; i < 81; ++i) { // loop through input string
            rows[row][squareNumber].setSquareValue(rowToCopyFrom[row][squareNumber].getSquareValue());

            ++squareNumber; // keep track of which row/square number its at
            if (squareNumber == 9) { // hit the end of the row, move down a row
                ++row;
                squareNumber = 0;
            }
        }
    }

    public SudokuBoard(String input) {
        initializeArrays();
        insertDataIntoRows(input);
    }
    
    private SudokuBoard() {
        initializeArrays();
    }

    // Print all the rows
    public void printRows() {
        System.out.println("Row Print: ");
        for (int row = 0; row < rows.length; ++row) {
            for (int squareNumber = 0; squareNumber < rows[row].length; ++squareNumber)
                System.out.print(rows[row][squareNumber].getSquareValue() + " ");
            System.out.println();
        }
    }

    // Print all the columns
    public void printColumns() {
        System.out.println("Column Print: ");
        for (int column = 0; column < columns.length; ++column) {
            for (int squareNumber = 0; squareNumber < columns[column].length; ++squareNumber)
                System.out.print(columns[column][squareNumber].getSquareValue() + " ");
            System.out.println();
        }
    }

    // Print all the sections
    public void printSections() {
        System.out.println("Section Print: ");
        for (int section = 0; section < sections.length; ++section) {
            for (int squareNumber = 0; squareNumber < sections[section].length; ++squareNumber)
                System.out.print(sections[section][squareNumber].getSquareValue() + " ");
            System.out.println();
        }
    }

    // print all possible moves for each square
    public void printAllPossible() {
        System.out.println("All Possible Moves: ");
        for (int row = 0; row < rows.length; ++row) {
            for (int squareNumber = 0; squareNumber < rows[row].length; ++squareNumber) {
                System.out.print("[" + row + "][" + squareNumber + "] = ");
                rows[row][squareNumber].printPossibleValues();
            }
        }
    }

    public void printInFormat() {

        for (int row = 0; row < rows.length; ++row) {
            System.out.print("|");
            for (int squareNumber = 0; squareNumber < rows[row].length; ++squareNumber) {
                System.out.print(rows[row][squareNumber].getSquareValue());
                if (squareNumber == 2 || squareNumber == 5)
                    System.out.print("|");
            }
            System.out.print("|\n");
            if (row == 2 || row == 5)
                System.out.println("------------");
        }
    }

    public SudokuBoard cloneBoard() {
        SudokuBoard newBoard = new SudokuBoard();
        newBoard.insertDataIntoRows(this.rows);

        return newBoard;
     }

    public boolean isSolved() {
        for (int row = 0; row < rows.length; ++row) {
            for (int squareNumber = 0; squareNumber < rows[row].length; ++squareNumber)
                if (rows[row][squareNumber].getSquareValue() == 0)
                    return false;
        }
        return true;
    }


    public boolean isInvalid() {
        for (int row = 0; row < rows.length; ++row) {
            for (int squareNumber = 0; squareNumber < rows[row].length; ++squareNumber)
                if (rows[row][squareNumber].getSquareValue() == 0 && rows[row][squareNumber].getPossibleValues().size() == 0)
                    return true;
        }
        return false;
    }

    public SudokuSquare[][] getRows() {
        return rows;
    }
}
