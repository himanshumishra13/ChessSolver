package com.himanshu.chessElements;

public class King extends AbstractChessPiece {
	
	int row,col;
	
	public King()
	{
		row=-1;
		col=-1;
	}
	

	
	public boolean isThePlaceSafe(int row, int col) {
	
		if (Math.abs(this.getRow() - row) == 1 && Math.abs(this.getCol() - col) == 1)
			return true;

		if ((this.getRow() == row && Math.abs(this.getCol() - col) == 1)
				|| (this.getCol() == col && Math.abs(this.getRow() - row) == 1))
			return true;
		return false;
	}

	
	public char returnSymbol() {
		return 'K';
	}

	
	public void markThreaten(int[][] board,int M,int N)
	{
		for(int i=0;i<M && i<=this.row+1;i++)
		{
			for(int j=0;j<N && j<=this.col+1;j++)
			{
				if (Math.abs(this.getRow() - i) == 1 && Math.abs(this.getCol() - j) == 1)
					board[i][j]-=1;
				if ((this.getRow() == i && Math.abs(this.getCol() - j) == 1)
						|| (this.getCol() == j && Math.abs(this.getRow() - i) == 1))
					board[i][j]-=1;
			}
		}
		
	}
	
	public void unmarkThreaten(int[][] board,int M,int N)
	{
		
		for(int i=0;i<M && i<=this.row+1;i++)
		{
			for(int j=0;j<N && j<=this.col+1;j++)
			{
				if (Math.abs(this.getRow() - i) == 1 && Math.abs(this.getCol() - j) == 1)
					board[i][j]+=1;
				if ((this.getRow() == i && Math.abs(this.getCol() - j) == 1)
						|| (this.getCol() == j && Math.abs(this.getRow() - i) == 1))
					board[i][j]+=1;
			}
		}
		
	}
}
