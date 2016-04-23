import java.util.HashSet;

/**
 * This class takes the input parameters and try to place the pieces such that
 * the pieces do not threaten each other.
 * 
 * @author Himanshu
 * 
 */
public class ChessPiece {

	static HashSet<String> set;

	public static void main(String[] args) {
		int M = 4, N = 4;
		int board[][] = new int[M][N];
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++) {
				board[i][j] = 0;
			}

		set = new HashSet<String>();
		long startTime = System.nanoTime();
		solve(board, "RRNNN", M, N);
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		double timeElapsed = duration / (1000000);
		System.out.println("The Elapsed time is:- " + timeElapsed);

	}

	/**
	 * Returns the character equivalent of the pieces,i.e Q=5,B=4,N=3,R=2,K=1;
	 * 
	 * @param n
	 *            Integer equivalent of the pieces
	 * @return Character Equivalent of pieces
	 */
	public static char getAlphabet(int n) {
		switch (n) {
		case 1:
			return 'K';
		case 2:
			return 'R';
		case 3:
			return 'N';
		case 4:
			return 'B';
		case 5:
			return 'Q';
		default:
			return '0';
		}
	}

	/**
	 * This method prints the board for the given solution in string format. The
	 * string contains the pieces and their coordinates on board.
	 * 
	 * @param s
	 *            Solution board in string format
	 * @param M
	 *            No. of Rows of the board
	 * @param N
	 *            No. of Columns of the board
	 */
	public static void printSolution(String s, int M, int N) {
		int[][] board = new int[M][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				board[i][j] = 0;
		String temp[] = s.split(";");
		for (int k = 0; k < temp.length; k++) {
			String g = temp[k];
			int row = g.charAt(1) - 48;
			int col = g.charAt(2) - 48;
			board[row][col] = g.charAt(0) - 48;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(getAlphabet(board[i][j]) + " ");
			}
			System.out.println();
		}
		System.out.println();

	}

	/**
	 * This is function which solves the position of the pieces on the board.
	 * The function takes the first character in the piece sequence and tries to
	 * place it in every possible cell on the board. If the piece is kept
	 * properly, i.e. it doesn't threaten any other, then the first letter of
	 * the piece sequence is taken out and this function is called recursively
	 * again to check for other pieces. If the current piece doesn't fit in any
	 * cell, the function is backtracked to previous good state. If the piece
	 * sequence becomes of 0 length the solution is stored as a string in a set
	 * to avoid duplication (The whole process can be optimized more)
	 * 
	 * @param board
	 *            Board containing the positions of the pieces
	 * @param rest
	 *            The piece sequence to be positioned on the board
	 * @param M
	 *            No. of rows in the board
	 * @param N
	 *            No. of columns in the board
	 * @return True if the piece are positioned properly or false on otherwise
	 */
	public static boolean solve(int[][] board, String rest, int M, int N) {

		if (rest.length() == 0) {
			StringBuilder sb1 = new StringBuilder();
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (getAlphabet(board[i][j]) != '0') {
						sb1.append(Integer.toString(board[i][j]))
								.append(Integer.toString(i))
								.append(Integer.toString(j)).append(";");
					}
				}
			}
			if (!set.contains(sb1.toString())) {
				set.add(sb1.toString());
				printSolution(sb1.toString(), M, N);
			}

			return true;
		}
		char c = rest.charAt(0);
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				boolean isSafe = false;

				switch (c) {
				case 'K':
					isSafe = isSafeKing(board, i, j, M, N);
					break;
				case 'R':
					isSafe = isSafeRook(board, i, j, M, N);
					break;
				case 'N':
					isSafe = isSafeKnight(board, i, j, M, N);
					break;
				case 'B':
					isSafe = isSafeBishop(board, i, j, M, N);
					break;
				case 'Q':
					isSafe = isSafeBishop(board, i, j, M, N);
				}
				if (isSafe) {

					switch (c) {
					case 'K':
						board[i][j] = 1;
						break;
					case 'R':
						board[i][j] = 2;
						break;
					case 'N':
						board[i][j] = 3;
						break;
					case 'B':
						board[i][j] = 4;
						break;
					case 'Q':
						board[i][j] = 5;
					}

					StringBuffer sb = new StringBuffer(rest);
					sb.deleteCharAt(0);
					solve(board, sb.toString(), M, N);

					board[i][j] = 0;
				}
			}
		}

		return false;
	}

	/**
	 * This function checks if the king can be placed in the given cell, does it
	 * threaten anyone or is threatened by any other
	 * 
	 * @param board
	 *            The current board
	 * @param row
	 *            Row position of the current placement
	 * @param col
	 *            Column position of the current placement
	 * @param M
	 *            No. of Rows on the board
	 * @param N
	 *            No. of columns on the board
	 * @return true if the King can be placed on the given position, i.e row,col
	 *         or else false.
	 */
	public static boolean isSafeKing(int[][] board, int row, int col, int M,
			int N) {
		if (board[row][col] != 0)
			return false;
		/*
		 * Checking if any piece is on same row as the King
		 */

		for (int i = 0; i < N; i++) {
			if (board[row][i] == 1 && Math.abs(col - i) >= 2)
				break;

			if (board[row][i] != 0) {
				return false;
			}
		}
		/*
		 * Checking if any piece is on same column as the King
		 */
		for (int i = 0; i < M; i++) {
			if (board[i][col] == 1 && Math.abs(row - i) >= 2)
				break;

			if (board[i][col] != 0) {
				return false;
			}
		}

		/**
		 * Checking if any piece is on the upper left diagonal of the king
		 */
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1 && (row - i) >= 2 && (col - j) >= 2)
				break;
			if (board[i][j] == 2 && (row - i) >= 2 && (col - j) >= 2)
				break;

			if (board[i][j] != 0)
				return false;
		}

		/**
		 * Checking if any pieces lie on the upper right diagonal
		 */
		for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
			if (board[i][j] == 1 && (row - i) >= 2 && Math.abs((col - j)) >= 2)
				break;
			if (board[i][j] == 2 && (row - i) >= 2 && Math.abs((col - j)) >= 2)
				break;
			;
			if (board[i][j] != 0)
				return false;
		}

		/**
		 * Checking if any piece lie on the lower left diagonal
		 */
		for (int i = row, j = col; i < M && j >= 0; i++, j--) {
			if (board[i][j] == 1 && Math.abs((row - i)) >= 2 && (col - j) >= 2)
				break;
			if (board[i][j] == 2 && Math.abs((row - i)) >= 2 && (col - j) >= 2)
				break;

			if (board[i][j] != 0)
				return false;
		}
		/**
		 * Checking if any piece lie on the lower right diagonal
		 */
		for (int i = row, j = col; i < M && j < N; i++, j++) {
			if (board[i][j] == 1 && Math.abs((row - i)) >= 2
					&& Math.abs((col - j)) >= 2)
				break;
			if (board[i][j] == 2 && Math.abs((row - i)) >= 2
					&& Math.abs((col - j)) >= 2)
				break;

			if (board[i][j] != 0)
				return false;
		}

		return true;
	}

	/**
	 * This function checks if the rook can be placed in the given cell, does it
	 * threaten anyone or is threatened by any other
	 * 
	 * @param board
	 *            The current board
	 * @param row
	 *            Row position of the current placement
	 * @param col
	 *            Column position of the current placement
	 * @param M
	 *            No. of Rows on the board
	 * @param N
	 *            No. of columns on the board
	 * @return true if the Rook can be placed on the given position, i.e row,col
	 *         or else false.
	 */
	public static boolean isSafeRook(int[][] board, int row, int col, int M,
			int N) {
		if (board[row][col] != 0)
			return false;
		/**
		 * Checking if any piece lie in the same row
		 */
		for (int i = 0; i < N; i++) {
			if (board[row][i] != 0)
				return false;
		}
		for (int i = 0; i < M; i++) {
			if (board[i][col] != 0)
				return false;
		}

		/**
		 * Checking if any piece lie on the upper left diagonal
		 */
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1 && (row - i) >= 2 && (col - j) >= 2)
				break;
			if (board[i][j] == 2)
				break;
			if (board[i][j] == 3)
				break;
			if (board[i][j] != 0)
				return false;
		}

		/**
		 * Checking if any pieces lie on the upper right diagonal
		 */
		for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
			if (board[i][j] == 1 && (row - i) >= 2 && Math.abs((col - j)) >= 2)
				break;
			if (board[i][j] == 2)
				break;
			if (board[i][j] == 3)
				break;
			if (board[i][j] != 0)
				return false;
		}

		/**
		 * Checking if any piece lie on the lower left diagonal
		 */
		for (int i = row, j = col; i < M && j >= 0; i++, j--) {
			if (board[i][j] == 1 && Math.abs((row - i)) >= 2 && (col - j) >= 2)
				break;
			if (board[i][j] == 2)
				break;
			if (board[i][j] == 3)
				break;
			if (board[i][j] != 0)
				return false;
		}
		/**
		 * Checking if any piece lie on the lower right diagonal
		 */
		for (int i = row, j = col; i < M && j < N; i++, j++) {
			if (board[i][j] == 1 && Math.abs((row - i)) >= 2
					&& Math.abs((col - j)) >= 2)
				break;
			if (board[i][j] == 2)
				break;
			if (board[i][j] == 3)
				break;
			if (board[i][j] != 0)
				return false;
		}

		return true;
	}

	/**
	 * This function checks if the Knight can be placed in the given cell, does
	 * it threaten anyone or is threatened by any other
	 * 
	 * @param board
	 *            The current board
	 * @param row
	 *            Row position of the current placement
	 * @param col
	 *            Column position of the current placement
	 * @param M
	 *            No. of Rows on the board
	 * @param N
	 *            No. of columns on the board
	 * @return true if the Knight can be placed on the given position, i.e
	 *         row,col or else false.
	 */
	public static boolean isSafeKnight(int[][] board, int row, int col, int M,
			int N) {

		/**
		 * Checking in row for any threatening pieces
		 */
		if (board[row][col] != 0)
			return false;
		for (int i = 0; i < N; i++) {
			if (board[row][i] == 1 && Math.abs(col - i) >= 2)
				break;
			if (board[row][i] == 3)
				break;
			if (board[row][i] == 4)
				break;
			if (board[row][i] != 0)
				return false;
		}

		/**
		 * Checking in column for any threatening piece
		 */
		for (int i = 0; i < M; i++) {
			if (board[i][col] == 1 && Math.abs(row - i) >= 2)
				break;
			if (board[i][col] == 3)
				break;
			if (board[i][col] == 4)
				break;
			if (board[i][col] != 0)
				return false;
		}
		/**
		 * Checking in Upper left diagonal
		 */
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1 && Math.abs(row - i) >= 2
					&& Math.abs(col - j) >= 2)
				break;
			if (board[i][j] == 2)
				break;
			if (board[i][j] == 3)
				break;
			if (board[i][j] != 0)
				return false;
		}
		/**
		 * Checking in upper right diagonal
		 */
		for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
			if (board[i][j] == 1 && Math.abs(row - i) >= 2
					&& Math.abs(col - j) >= 2)
				break;
			if (board[i][j] == 2)
				break;
			if (board[i][j] == 3)
				break;
			if (board[i][j] != 0)
				return false;
		}

		/**
		 * Checking in lower left diagonal
		 */
		for (int i = row, j = col; i < M && j >= 0; i++, j--) {
			if (board[i][j] == 1 && Math.abs(row - i) >= 2
					&& Math.abs(col - j) >= 2)
				break;
			if (board[i][j] == 2)
				break;
			if (board[i][j] == 3)
				break;
			if (board[i][j] != 0)
				return false;
		}

		/**
		 * Checking lower right diagonal
		 */
		for (int i = row, j = col; i < M && j < N; i++, j++) {
			if (board[i][j] == 1 && Math.abs(row - i) >= 2
					&& Math.abs(col - j) >= 2)
				break;
			if (board[i][j] == 2)
				break;
			if (board[i][j] == 3)
				break;
			if (board[i][j] != 0)
				return false;
		}

		/**
		 * Checking if Knight threaten any piece (2L1U)
		 */
		if (row - 1 >= 0 && col - 2 >= 0) {
			if ((board[row - 1][col - 2] != 0))
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (1L2U)
		 */
		if (row - 2 >= 0 && col - 1 >= 0) {
			if (board[row - 2][col - 1] != 0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (1R2U)
		 */
		if (row - 2 >= 0 && col + 1 < N) {
			if (board[row - 2][col + 1] != 0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (2R1U)
		 */
		if (row - 1 >= 0 && col + 2 < N) {
			if (board[row - 1][col + 2] != 0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (2R1D)
		 */
		if (row + 1 < M && col + 2 < N) {
			if (board[row + 1][col + 2] != 0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (1R2D)
		 * 
		 */
		if (row + 2 < M && col + 1 < N) {
			if (board[row + 2][col + 1] != 0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (1L2D)
		 */
		if (row + 2 < M && col - 1 >= 0) {
			if (board[row + 2][col - 1] != 0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (2L1D)
		 */
		if (row + 1 < M && col - 2 >= 0) {
			if (board[row + 1][col - 2] != 0)
				return false;
		}
		return true;
	}

	/**
	 * This function checks if the Bishop can be placed in the given cell, does
	 * it threaten anyone or is threatened by any other
	 * 
	 * @param board
	 *            The current board
	 * @param row
	 *            Row position of the current placement
	 * @param col
	 *            Column position of the current placement
	 * @param M
	 *            No. of Rows on the board
	 * @param N
	 *            No. of columns on the board
	 * @return true if the Bishop can be placed on the given position, i.e
	 *         row,col or else false.
	 */
	public static boolean isSafeBishop(int[][] board, int row, int col, int M,
			int N) {
		if (board[row][col] != 0)
			return false;
		/**
		 * Checking if any piece lie in the same row
		 */
		for (int i = 0; i < N; i++) {
			if (board[row][i] == 1 && Math.abs(col - i) >= 2)
				break;
			if (board[row][i] == 3)
				break;
			if (board[row][i] == 4)
				break;
			if (board[row][i] != 0) {
				return false;
			}
		}
		for (int i = 0; i < M; i++) {
			if (board[i][col] == 1 && Math.abs(row - i) >= 2)
				break;
			if (board[i][col] == 3)
				break;
			if (board[i][col] == 4)
				break;
			if (board[i][col] != 0) {
				return false;
			}
		}
		/**
		 * Checking if any piece lie on the upper left diagonal
		 */
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] != 0)
				return false;
		}

		/**
		 * Checking if any pieces lie on the upper right diagonal
		 */
		for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
			if (board[i][j] != 0)
				return false;
		}

		/**
		 * Checking if any piece lie on the lower left diagonal
		 */
		for (int i = row, j = col; i < M && j >= 0; i++, j--) {
			if (board[i][j] != 0)
				return false;
		}
		/**
		 * Checking if any piece lie on the lower right diagonal
		 */
		for (int i = row, j = col; i < M && j < N; i++, j++) {
			if (board[i][j] != 0)
				return false;
		}

		return true;
	}

	/**
	 * This function checks if the Queen can be placed in the given cell, does
	 * it threaten anyone or is threatened by any other
	 * 
	 * @param board
	 *            The current board
	 * @param row
	 *            Row position of the current placement
	 * @param col
	 *            Column position of the current placement
	 * @param M
	 *            No. of Rows on the board
	 * @param N
	 *            No. of columns on the board
	 * @return true if the Queen can be placed on the given position, i.e
	 *         row,col or else false.
	 */
	public static boolean isSafeQueen(int[][] board, int row, int col, int M,
			int N) {
		if (board[row][col] != 0)
			return false;
		/**
		 * Checking if any piece lie in the same row
		 */
		for (int i = 0; i < N; i++) {
			if (board[row][i] != 0)
				return false;
		}
		for (int i = 0; i < M; i++) {
			if (board[i][col] != 0)
				return false;
		}
		/**
		 * Checking if any piece lie on the upper left diagonal
		 */
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] != 0)
				return false;
		}

		/**
		 * Checking if any pieces lie on the upper right diagonal
		 */
		for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
			if (board[i][j] != 0)
				return false;
		}

		/**
		 * Checking if any piece lie on the lower left diagonal
		 */
		for (int i = row, j = col; i < M && j >= 0; i++, j--) {
			if (board[i][j] != 0)
				return false;
		}
		/**
		 * Checking if any piece lie on the lower right diagonal
		 */
		for (int i = row, j = col; i < M && j < N; i++, j++) {
			if (board[i][j] != 0)
				return false;
		}

		return true;

	}
}
