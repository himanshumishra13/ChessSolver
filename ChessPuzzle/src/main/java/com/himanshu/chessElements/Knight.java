package com.himanshu.chessElements;

public class Knight implements ChessPiece {
	int row,col;
	
	public Knight()
	{
		row=-1;
		col=-1;
	}

	
	public int getRow() {
		return this.row;
	}

	
	public int getCol() {
		return this.col;
	}

	
	public boolean isThePlaceSafe(int row, int col) {
		int rowDiff = Math.abs(this.getRow() - row);
		int colDiff = Math.abs(this.getCol() - col);
		if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))
			return true;
		return false;
	}

	
	public void setRow(int row) {
		this.row=row;
		
	}

	
	public void setCol(int col) {
		this.col=col;
	}

	
	public char returnSymbol() {
		return 'N';
	}
	
}
