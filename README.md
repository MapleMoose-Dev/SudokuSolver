## Java SudokuSolver

This java program will solve any possible Sudoku Puzzle. 

**RUNNING THE PROGRAM**<br>
`java SudokuSolver 080000243000079100040000090693502070014907360070301429060000050002690000459000030 -t`

The first argument is the board, left to right, top to bottom. A zero represents an unknown number

`-t ` is an optional 2nd parameter which will print out the time taken for the program to solve.

The solution will be printed in the format:
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

which is equivalent to: <br>
`987156243325479186146823597693542871214987365578361429761234958832695714459718632`

