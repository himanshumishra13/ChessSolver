# ChessSolver
The problem is to find all unique configurations of a set of normal chess pieces on a chess board with dimensions M×N where none of the pieces is in a position to take any of the others. Assume the colour of the piece does not matter, and that there are no pawns among the pieces.

Write a program which takes as input:
●	The dimensions of the board: M, N
●	The number of pieces of each type (King, Queen, Bishop, Rook and Knight) to try and place on the board.

As output, the program should list all the unique configurations to the console for which all of the pieces can be placed on the board without threatening each other.

When returning your solution, please provide with your answer the total number of unique configurations for a 7×7 board with 2 Kings, 2 Queens, 2 Bishops and 1 Knight. Also provide the time it took to get the final score. Needless to say, the lower the time, the better.


To Run:

1. Compile the source files by mvn compile (or mvn package for Test cases run)
2. Use mvn exec:java -Dexec.args="arg1 arg2 arg3 arg4 arg5 arg6 arg7" to run the solution. The detail info about arguments are:-

   arg1 = No. of Rows on the board

   arg2 = No. of Columns on the board
   
   arg3 = No. of Queens to be placed
   
   arg4 = No. of Bishops to be placed
   
   arg5 = No. of Rooks to be placed
   
   arg6 = No. of Knights to be placed
   
   arg7 = No. of Kings to be placed
   
   Eg. If we have to place 2 Kings and a Rook on a 3x3 board, the command line arguments will be : 3 3 0 0 1 0 2
   
   To place 2 Queens, 2 Bishops, 1 Knight, 2 Kings on a 7x7 board the arguments will be : 7 7 2 2 0 1 2
