package com.himanshu.chessElements;

public class Bishop implements ChessPiece{
	int row,col;
	
	public Bishop()
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
		return false;
	}

	
	public void setRow(int row) {
		this.row=row;
	}

	
	public void setCol(int col) {
		this.col=col;
	}

	
	public char returnSymbol() {
		return 'B';
	}
	
	public void markThreaten(int[][] board,int M,int N)
	{
		for(int y=0;y<M;y++)
		{
			for(int z=0;z<N;z++)
			{
				if(Math.abs(this.getRow()-y)==Math.abs(this.getCol()-z) && (y!=this.getRow()) && (z!=this.getCol()))
					board[y][z]-=1;
			}
		}
	}
	
	public void unmarkThreaten(int[][] board,int M,int N)
	{
		for(int y=0;y<M;y++)
		{
			for(int z=0;z<N;z++)
			{
				if(Math.abs(this.getRow()-y)==Math.abs(this.getCol()-z) && (y!=this.getRow()) && (z!=this.getCol()))
					board[y][z]+=1;
			}
		}
	}
}
