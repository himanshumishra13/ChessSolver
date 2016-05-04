package com.himanshu.chessSolver;

public class King implements ChessPiece {
	
	int row,col;
	
	public King()
	{
		row=-1;
		col=-1;
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getCol()
	{
		return this.col;
	}

	
	public boolean isThePlaceSafe(int row, int col) {
	
		if (Math.abs(this.getRow() - row) == 1 && Math.abs(this.getCol() - col) == 1)
			return true;

		if ((this.getRow() == row && Math.abs(this.getCol() - col) == 1)
				|| (this.getCol() == col && Math.abs(this.getRow() - row) == 1))
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
		return 'K';
	}

}
