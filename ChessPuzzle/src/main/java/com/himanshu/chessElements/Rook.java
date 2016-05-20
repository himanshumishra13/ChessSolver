package com.himanshu.chessElements;

public class Rook extends AbstractChessPiece {
	int row,col;
	
	public Rook()
	{
		row=-1;
		col=-1;
	}

	
	public boolean isThePlaceSafe(int row, int col) {
		if (this.getRow() == row || this.getCol() == col)
			return true;
		return false;
	}
	
	public char returnSymbol() {
		return 'R';
	}
	
	public void markThreaten(int[][] board,int M,int N)
	{
		for(int y=0;y<M;y++)
		{
			if(y!=this.getRow())
				board[y][this.getCol()]-=1;
			
		}
		for(int z=0;z<N;z++)
		{
			if(z!=this.getCol())
				board[this.getRow()][z]-=1;
		}
	}
	
	public void unmarkThreaten(int[][] board,int M,int N)
	{
		for(int y=0;y<M;y++)
		{
			if(y!=this.getRow())
				board[y][this.getCol()]+=1;
			
		}
		for(int z=0;z<N;z++)
		{
			if(z!=this.getCol())
				board[this.getRow()][z]+=1;
		}
	}

}
