package com.himanshu.chessElements;

public class Queen implements ChessPiece{
	int row,col;
	
	public Queen()
	{
		this.row=-1;
		this.col=-1;
	}

	
	public int getRow() {
		return this.row;
	}

	
	public int getCol() {
		return this.col;
	}

	
	public boolean isThePlaceSafe(int row, int col) {
		if (Math.abs(this.getRow() - row) == Math.abs(this.getCol() - col))
			return true;
		if (this.getRow() == row || this.getCol() == col)
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
		return 'Q';
	}

}
