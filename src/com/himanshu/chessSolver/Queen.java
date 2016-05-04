package com.himanshu.chessSolver;

public class Queen implements ChessPiece{
	int row,col;
	
	public Queen()
	{
		this.row=-1;
		this.col=-1;
	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public int getCol() {
		return this.col;
	}

	@Override
	public boolean isThePlaceSafe(int row, int col) {
		if (Math.abs(this.getRow() - row) == Math.abs(this.getCol() - col))
			return true;
		if (this.getRow() == row || this.getCol() == col)
			return true;
		return false;
	}

	@Override
	public void setRow(int row) {
		this.row=row;
	}

	@Override
	public void setCol(int col) {
		this.col=col;
	}

	@Override
	public char returnSymbol() {
		return 'Q';
	}

}
