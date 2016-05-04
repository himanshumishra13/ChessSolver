package com.himanshu.chessSolver;

public class Rook implements ChessPiece {
	int row,col;
	
	public Rook()
	{
		row=-1;
		col=-1;
	}

	
	public int getRow() {
		return this.row;
	}

	@Override
	public int getCol() {
		return this.col;
	}

	@Override
	public boolean isThePlaceSafe(int row, int col) {
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
		return 'R';
	}
	
	

}
