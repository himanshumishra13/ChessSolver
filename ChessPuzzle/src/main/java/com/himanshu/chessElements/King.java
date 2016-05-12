package com.himanshu.chessElements;

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

	
	public void markThreaten(int[][] board,int M,int N)
	{
		int flag0=0,flag1=0,flag2=0,flag3=0;	
		if((this.getRow()-1)>=0)
		{
			board[this.getRow()-1][this.getCol()]-=1;
			flag0=1;
		}
		if((this.getCol()-1)>=0)
		{
			board[this.getRow()][this.getCol()-1]-=1;
			flag1=1;
		}
		if((this.getRow()+1)<N)
		{
			board[this.getRow()+1][this.getCol()]-=1;
			flag2=1;
		}
		if((this.getCol()+1)<N)
		{
			board[this.getRow()][this.getCol()+1]-=1;
			flag3=1;
		}
		if(flag0==1 && flag1==1)
			board[this.getRow()-1][this.getCol()-1]-=1;
		if(flag2==1 && flag3==1)
			board[this.getRow()+1][this.getCol()+1]-=1;
		if(flag0==1 && flag3==1)
			board[this.getRow()-1][this.getCol()+1]-=1;
		if(flag1==1 && flag2==1)
			board[this.getRow()+1][this.getCol()-1]-=1;
	}
	
	public void unmarkThreaten(int[][] board,int M,int N)
	{
		int flag0=0,flag1=0,flag2=0,flag3=0;	
		if((this.getRow()-1)>=0)
		{
			board[this.getRow()-1][this.getCol()]+=1;
			flag0=1;
		}
		if((this.getCol()-1)>=0)
		{
			board[this.getRow()][this.getCol()-1]+=1;
			flag1=1;
		}
		if((this.getRow()+1)<N)
		{
			board[this.getRow()+1][this.getCol()]+=1;
			flag2=1;
		}
		if((this.getCol()+1)<N)
		{
			board[this.getRow()][this.getCol()+1]+=1;
			flag3=1;
		}
		if(flag0==1 && flag1==1)
			board[this.getRow()-1][this.getCol()-1]+=1;
		if(flag2==1 && flag3==1)
			board[this.getRow()+1][this.getCol()+1]+=1;
		if(flag0==1 && flag3==1)
			board[this.getRow()-1][this.getCol()+1]+=1;
		if(flag1==1 && flag2==1)
			board[this.getRow()+1][this.getCol()-1]+=1;
	}
}
