**RUNNING THE PROGRAM**
`java SudokuSolver 080000243000079100040000090693502070014907360070301429060000050002690000459000030 -t`

`-t ` is an optional 2nd parameter which will print out the time taken for the program to solve.

Your program should solve any valid Sudoku problem. When completed you should have a program that can solve even the most difficult Sudoku puzzles. The initial problem should be specified as a String on the command line.  Three examples include the following:

080000243000079100040000090693502070014907360070301429060000050002690000459000030
907156000325400086106823507000040801200080005508060000701234908830005714000718602
000092500000060010574001000000000021708000406640000000000200694050030000009810000

 Where input proceeds left to right, and then onto the next row.  A zero indicates the square is unknown and a number indicates a known value.  See the solution below that corresponds to the first input.


 You program should solve the Sudoku and output the solution as formatted text.  For example, given the first input the output should look like to the following (remember proper formatting is important for the grading process):

|987|156|243|
|325|479|186|
|146|823|597|
------------
|693|542|871|
|214|987|365|
|578|361|429|
------------
|761|234|958|
|832|695|714|
|459|718|632|



Include trace files for two problems that work with your program.

