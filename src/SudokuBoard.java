

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
        int squareNumber = 0;
        int row = 0;

        for (int i = 0; i < 81; ++i) { // loop through input string
            int squareValue = Integer.valueOf(input.substring(i, i + 1));
            rows[row][squareNumber].setSquareValue(squareValue);

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


    final static private int possibleValues[] = {1,2,3,4,5,6,7,8,9};
    private boolean checkArray(SudokuSquare[] array) {
        IntTable tempNumbers = new IntTable(possibleValues);

        for (int i = 0; i < array.length; ++i) {
            int squareValue = array[i].getSquareValue();

            if (squareValue == 0) continue;

            if (tempNumbers.find(squareValue) != -1) { // Number exists in the array
                tempNumbers.remove(tempNumbers.find(squareValue));
            } else { // Number was already found
                return false;
            }
        }
        return true; // Valid row/section/column
    }


    public boolean isInvalid() {
        for (int row = 0; row < rows.length; ++row) {
            for (int squareNumber = 0; squareNumber < rows[row].length; ++squareNumber)
                if (rows[row][squareNumber].getSquareValue() == 0 && rows[row][squareNumber].getPossibleValues().size() == 0) {
                  //  System.out.println("Invalid - No possible Moves for square");
                    return true;
                }
        }

        // Check rows
        for (int row = 0; row < rows.length; ++row) {
            if (!checkArray(rows[row])) {
               // System.out.println("Invalid Row: " + row);
                return true;
            } // Invalid Row
        }

        // Check Columns
        for (int column = 0; column < columns.length; ++column) {
            if (!checkArray(columns[column])) {
               // System.out.println("Invalid Column: " + column);
                return true;
            } // Invalid Column
        }

        // Check Sections
        for (int sec = 0; sec < sections.length; ++sec) {
            if (!checkArray(sections[sec])) {
               // System.out.println("Invalid Section: " + sec);
                return true;
            }  // Invalid section
        }


        return false;
    }

    public SudokuSquare[][] getRows() {
        return rows;
    }
}
