package com.himanshu.chessSolver;

public interface ChessPiece {

	public int getRow();
	public int getCol();
	public boolean isThePlaceSafe(int row, int col);
	public void setRow(int row);
	public void setCol(int col);
	public char returnSymbol();
}
