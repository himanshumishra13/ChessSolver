package com.himanshu.chessElements;

public class Knight extends AbstractChessPiece {
	int row,col;
	
	public Knight()
	{
		row=-1;
		col=-1;
	}

	
	
	
	public boolean isThePlaceSafe(int row, int col) {
		int rowDiff = Math.abs(this.getRow() - row);
		int colDiff = Math.abs(this.getCol() - col);
		if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))
			return true;
		return false;
	}

	
	
	public char returnSymbol() {
		return 'N';
	}
	
	public void markThreaten(int[][] board,int M,int N)
	{
	
	}
	
	public void unmarkThreaten(int[][] board,int M,int N)
	{
		
	}
	
}
