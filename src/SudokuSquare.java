
/*
    class represents each square on a sudoku
 */
public class SudokuSquare {

    private static int startingValues[] =  {1,2,3,4,5,6,7,8,9}; // Possible values the square could be, used to create IntTable

    private IntTable possibleValues;  // int array of all possible values
    private int squareValue = 0; // the value of the square, (once found / solved)
    private SudokuSquare row[]; // The array row the square is in
    private SudokuSquare column[]; // The array column the square is in
    private SudokuSquare section[]; // The array section (3x3 grid) the square is in

    SudokuSquare() {
        possibleValues = new IntTable(startingValues); // Initialize the possible values table
    }

    public void setSection(SudokuSquare section[]) { // Sets which section the square is in
        this.section = section;
    }

    public IntTable getPossibleValues() {
        return possibleValues.clone();
    }

    public void setRow(SudokuSquare row[]) { // Sets which row the square is in
        this.row = row;
    }

    public void setColumn(SudokuSquare column[]) { // Sets which column the square is in
        this.column = column;
    }

    public void printPossibleValues() { 
        if (possibleValues.size() > 0) // If there are multiple possible values
            possibleValues.print(); // print the possible values
        else
            System.out.println(squareValue); // Print the value of the square as a "possible move"
    }

    public int getSquareValue() { 
        return squareValue;
    }

    public void removePossibleValue(int eliminatedSquareValue) { // Remove the value as a possible option for the square
        possibleValues.remove(possibleValues.find(eliminatedSquareValue)); // if it exists, remove it, otherwise ignore

        if (possibleValues.size() == 1 && squareValue == 0) { // last possible value for the square
           setSquareValue(possibleValues.at(0)); 
        }  
    }

   
    public void setSquareValue(int newSquareValue) {
        if (newSquareValue == 0) return; // redundant to set its value to zero

        if (squareValue == 0 && possibleValues.size() == 0) {
            return;
        }

        squareValue = newSquareValue; // Set the value of the square
        possibleValues.clear(); // clear the possible moves, no other possible moves 

        // Remove value from its row
        for (int squareNumber = 0; squareNumber < row.length; ++squareNumber) {
            row[squareNumber].removePossibleValue(newSquareValue);
        }

        // Remove value from its column
        for (int squareNumber = 0; squareNumber < column.length; ++squareNumber) {
            column[squareNumber].removePossibleValue(newSquareValue);
        }

        // Remove value from its section
        for (int squareNumber = 0; squareNumber < section.length; ++squareNumber) {
            section[squareNumber].removePossibleValue(newSquareValue);
        }

    }
    
}