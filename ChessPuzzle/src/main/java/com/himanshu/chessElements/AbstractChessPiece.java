package com.himanshu.chessElements;

public abstract class AbstractChessPiece implements ChessPiece{
	int row,col;
	
	public AbstractChessPiece(){
		
	}
	
	public abstract char returnSymbol();
	
	public int getRow() {
		return this.row;
	}

	
	public int getCol() {
		return this.col;
	}
	
	public void setRow(int row) {
		this.row=row;
	}

	
	public void setCol(int col) {
		this.col=col;
	}
	
	
}
