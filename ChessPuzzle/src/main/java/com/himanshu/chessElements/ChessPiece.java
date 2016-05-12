package com.himanshu.chessElements;
/**
 * This interface is implemented by all the piece types
 * @author Himanshu
 *
 */
public interface ChessPiece {

	/**
	 * This returns the row value of the piece
	 * @return row value
	 */
	public int getRow();
	
	/**
	 * This returns the column value
	 * @return column value
	 */
	public int getCol();
	
	/**
	 * This contains the implementation of each pieces ability to attack and checks whether the given row and column value is attackable or not
	 * @param row row value of the target
	 * @param col column value of the target
	 * @return true/false, whether it can attack the target position or not
	 */
	public boolean isThePlaceSafe(int row, int col);
	
	/**
	 * Setter for row field
	 * @param row row value
	 */
	public void setRow(int row);
	
	/**
	 * Setter for column field
	 * @param col column value
	 */
	public void setCol(int col);
	
	/**
	 * This function returns the Character representation of the piece
	 * @return Character representation of Piece (Q-Queen, B-Bishop, R-Rook, K-King, N-Knight)
	 */
	public char returnSymbol();
	
	/**
	 * This function marks the cells on the board which will be threatened by placing the piece. The cell values are incremented by -1, so if a 
	 * cell has value < -1, it is being threatened by more than one piece.
	 * @param board The chess board
	 * @param M Row count of the board
	 * @param N Column count of the board
	 */
	public void markThreaten(int[][] board,int M,int N);
	
	/**
	 * This function unmarks the cells of the board as the piece is no more threatening the cell. The value is increased by 1. If the value 
	 * of the cell is still < -1 after this operation, it means that the cell was threatened by more than one placed pieces.
	 * @param board The chess board
	 * @param M Row count of the board
	 * @param N Column count of the board
	 */
	public void unmarkThreaten(int[][] board,int M,int N);
}
