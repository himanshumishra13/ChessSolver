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
}
