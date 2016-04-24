package com.himanshu.chessSolver;



public class ChessBoardSolver {
	/**
	 * Returns the character equivalent of the pieces,i.e Q=5,B=4,N=3,R=2,K=1;
	 * 
	 * @param n
	 *            Integer equivalent of the pieces
	 * @return Character Equivalent of pieces
	 */
	private char getAlphabet(int n) {
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
	 * This is the function tries to place the pieces on to the board in a
	 * recursive fashion. The function tries to place the pieces in every cell
	 * on the board. If the piece cannot be attacked by other already placed
	 * piece and nor does it threaten any other piece, the piece is placed for
	 * that value of i and j, i.e the current column. The function then tries to
	 * place the remaining pieces recursively. If in any future calls the piece
	 * cannot be placed, the function backtracks to a previous good known state
	 * and removes the piece from the board. If all pieces are placed on the
	 * board successfully, then that board's layout is stored in the result set,
	 * in a string format and storing the result in a HashSet guarantees that no
	 * duplicate solutions are stored.
	 * 
	 * @param result
	 *            A Result object which stores the result board layouts
	 * @param board
	 *            A 2D array which represents the chess board
	 * @param rest
	 *            A containing the remaining pieces to be placed
	 * @param M
	 *            Length of the chess board
	 * @param N
	 *            Width of the chess board
	 * @param placedPieces
	 *            A string containing the row and column value of the
	 *            successfully placed piece. The format is piece name and its
	 *            cell value and a delimiter, eg. K00;
	 * @return True if the piece is placed, false if the piece is not
	 *         successfully placed for that cell.
	 */
	public boolean solve(Result result, int[][] board, String rest, int M,
			int N, String placedPieces) {

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
			result.addResult(sb1.toString());

			return true;
		}
		char c = rest.charAt(0);
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 0)
					continue;
				boolean isSafe = false;

				isSafe = canPlaceThePiece(i, j, c, placedPieces); // Checking if
																	// the piece
																	// can be
																	// placed
																	// successfully
																	// in the
																	// current
																	// cell
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
					solve(result,
							board,
							sb.toString(),
							M,
							N,
							placedPieces
									+ new StringBuilder().append(c)
											.append(Integer.toString(i))
											.append(Integer.toString(j))
											.append(";").toString()); // Recursive
																		// call
																		// to
																		// place
																		// the
																		// next
																		// piece

					board[i][j] = 0; // backtracks if the piece can't be placed
				}
			}
		}

		return false;
	}

	/**
	 * This function checks if the current piece can be placed at the given cell
	 * and checks whether it is threatened by the previously placed pieces and
	 * does it threaten any previously placed piece.
	 * 
	 * @param row
	 *            The current row value
	 * @param col
	 *            The current column value
	 * @param c
	 *            The piece to be placed (Q-Queen, B-Bishop, R-Rook, N-Knight,
	 *            K-King)
	 * @param placedPieces
	 *            The previously placed pieces and their row and column values,
	 *            eg. K10, where K is the piece and 1 is the row value and 0
	 *            column value
	 * @return true if the piece can be placed else false
	 */
	private boolean canPlaceThePiece(int row, int col, char c,
			String placedPieces) {
		if (placedPieces.length() == 0)
			return true;
		String temp[] = placedPieces.split(";");
		for (int i = 0; i < temp.length; i++) {
			boolean attackFrom = false, attackTo = false;
			char a = temp[i].charAt(0);
			int rowVal = temp[i].charAt(1) - 48;
			int colVal = temp[i].charAt(2) - 48;
			// This is to check if any pre placed piece threaten the current
			// piece at row,col
			switch (a) {
			case 'K':
				attackFrom = canKingAttack(rowVal, colVal, row, col);
				break;
			case 'R':
				attackFrom = canRookAttack(rowVal, colVal, row, col);
				break;
			case 'N':
				attackFrom = canKnightAttack(rowVal, colVal, row, col);
				break;
			case 'B':
				attackFrom = canBishopAttack(rowVal, colVal, row, col);
				break;
			case 'Q':
				attackFrom = canQueenAttack(rowVal, colVal, row, col);
				break;

			}
			if (attackFrom)
				return false;
			// This is to check if the piece can threaten pre placed pieces, if
			// placed on row, col values
			switch (c) {
			case 'K':
				attackTo = canKingAttack(row, col, rowVal, colVal);
				break;
			case 'R':
				attackTo = canRookAttack(row, col, rowVal, colVal);
				break;
			case 'N':
				attackTo = canKnightAttack(row, col, rowVal, colVal);
				break;
			case 'B':
				attackTo = canBishopAttack(row, col, rowVal, colVal);
				break;
			case 'Q':
				attackTo = canQueenAttack(row, col, rowVal, colVal);
				break;
			}
			if (attackTo)
				return false;

		}
		return true;
	}

	/**
	 * This function checks if the King, placed on (rowP, colP), can threaten a
	 * piece on (rowA, colA)
	 * 
	 * @param rowP
	 *            The row value of current position of King
	 * @param colP
	 *            The column value of current position of King
	 * @param rowA
	 *            The row value of the other piece
	 * @param colA
	 *            The column value of the other pieces
	 * @return true if the King can attack else false
	 */
	private boolean canKingAttack(int rowP, int colP, int rowA, int colA) {

		if (Math.abs(rowP - rowA) == 1 && Math.abs(colP - colA) == 1)
			return true;

		if ((rowP == rowA && Math.abs(colP - colA) == 1)
				|| (colP == colA && Math.abs(rowP - rowA) == 1))
			return true;
		return false;
	}

	/**
	 * This function checks if the Rook, placed on (rowP, colP), can threaten a
	 * piece on (rowA, colA)
	 * 
	 * @param rowP
	 *            The row value of current position of Rook
	 * @param colP
	 *            The column value of current position of Rook
	 * @param rowA
	 *            The row value of the other piece
	 * @param colA
	 *            The column value of the other pieces
	 * @return true if the Rook can attack else false
	 */
	private boolean canRookAttack(int rowP, int colP, int rowA, int colA) {
		if (rowP == rowA || colP == colA)
			return true;
		return false;
	}

	/**
	 * This function checks if the Knight, placed on (rowP, colP), can threaten
	 * a piece on (rowA, colA)
	 * 
	 * @param rowP
	 *            The row value of current position of Knight
	 * @param colP
	 *            The column value of current position of Knight
	 * @param rowA
	 *            The row value of the other piece
	 * @param colA
	 *            The column value of the other pieces
	 * @return true if the Knight can attack else false
	 */
	private boolean canKnightAttack(int rowP, int colP, int rowA, int colA) {
		int rowDiff = Math.abs(rowP - rowA);
		int colDiff = Math.abs(colP - colA);
		if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))
			return true;
		return false;
	}

	/**
	 * This function checks if the Bishop, placed on (rowP, colP), can threaten
	 * a piece on (rowA, colA)
	 * 
	 * @param rowP
	 *            The row value of current position of Bishop
	 * @param colP
	 *            The column value of current position of Bishop
	 * @param rowA
	 *            The row value of the other piece
	 * @param colA
	 *            The column value of the other pieces
	 * @return true if the Bishop can attack else false
	 */
	private boolean canBishopAttack(int rowP, int colP, int rowA, int colA) {
		if (Math.abs(rowP - rowA) == Math.abs(colP - colA))
			return true;
		return false;
	}

	/**
	 * This function checks if the Queen, placed on (rowP, colP), can threaten a
	 * piece on (rowA, colA)
	 * 
	 * @param rowP
	 *            The row value of current position of Queen
	 * @param colP
	 *            The column value of current position of Queen
	 * @param rowA
	 *            The row value of the other piece
	 * @param colA
	 *            The column value of the other pieces
	 * @return true if the Queen can attack else false
	 */
	private boolean canQueenAttack(int rowP, int colP, int rowA, int colA) {
		if (Math.abs(rowP - rowA) == Math.abs(colP - colA))
			return true;
		if (rowP == rowA || colP == colA)
			return true;
		return false;
	}
}
