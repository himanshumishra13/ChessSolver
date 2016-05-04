package com.himanshu.chessSolver;

public class Knight implements ChessPiece {
	int row,col;
	
	public Knight()
	{
		row=-1;
		col=-1;
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
		int rowDiff = Math.abs(this.getRow() - row);
		int colDiff = Math.abs(this.getCol() - col);
		if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))
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
		return 'N';
	}
	
}
