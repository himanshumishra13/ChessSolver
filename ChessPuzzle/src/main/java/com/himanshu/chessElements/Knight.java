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
		for(int i=0;i<M && i<=this.row+2;i++)
		{
			for(int j=0;j<N && j<=this.col+2;j++)
			{
				int rowDiff = Math.abs(this.getRow() - i);
				int colDiff = Math.abs(this.getCol() - j);
				if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))
					board[i][j]-=1;
			}
		}
	}
	
	public void unmarkThreaten(int[][] board,int M,int N)
	{
		for(int i=0;i<M && i<=this.row+2;i++)
		{
			for(int j=0;j<N && j<=this.col+2;j++)
			{
				int rowDiff = Math.abs(this.getRow() - i);
				int colDiff = Math.abs(this.getCol() - j);
				if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))
					board[i][j]+=1;
			}
		}
	}
	
}
