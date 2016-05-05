package com.himanshu.chessElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChessBoard {
	private int M,N;
	private int board[][];
	List<ChessPiece> pieces; 
	/**
	 * Initializes the ChessBoard fields
	 * @param M Row count
	 * @param N Column Count
	 * @param piecesToPlace List of pieces to place on the board
	 */
	public ChessBoard(int M,int N,List<ChessPiece> piecesToPlace)
	{
		this.M=M;
		this.N=N;
		pieces=piecesToPlace;
		board=new int[M][N];
		initializeBoard();
	}
	
	/**
	 * This initializes the board to empty, which is represented as 0
	 */
	private void initializeBoard()
	{
		for(int i=0;i<this.getM();i++)
		{
			for(int j=0;j<this.getN();j++)
			{
				board[i][j]=0;
			}
		}
		
	}
	
	
	public int getN() {
		return N;
	}

	public void setN(int N) {
		this.N = N;
	}

	public int getM() {
		return M;
	}

	public void setM(int M) {
		this.M = M;
	}
	
	/**
	 * This function acts as the driver for solve function.
	 * @return Result object containing the unique solutions
	 */
	public Result getSolutions()
	{
		Result results=new Result(this.getM(),this.getN());
		
		solve(results,this.getM(),this.getN(),this.board,this.pieces,"",0);
		
		return results;
	}
	
	/**
	 * This function recursively checks whether the current piece can be placed on the board safely or not. The function backtracks to a previous valid state 
	 * if the the current piece cannot be placed properly
	 * @param results Result object containing the solutions
	 * @param M Row count of the board
	 * @param N Column count of the board
	 * @param board A two dimensional array which represents a chess board
	 * @param pieces A list of ChessPieces types which is to be placed on the board
	 * @param placedPieces A string containing the pieces and their positions already placed on the board
	 * @param offset A counter which points to the current piece in the pieces list
	 * @return True/False, depending whether the piece can be placed or not.
	 */
	public boolean solve(Result results,int M,int N,int board[][],List<ChessPiece> pieces,String placedPieces,int offset)
	{
		if(offset==pieces.size())
		{
			StringBuilder sb1 = new StringBuilder();
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j]>0) 
					{
							char t=(char)board[i][j];
						sb1.append(t)
								.append(Integer.toString(i))
								.append(Integer.toString(j)).append(";");
					}
				}
			}
			results.addResult(sb1.toString());

			return true;
		}
		
		ChessPiece piece=pieces.get(offset);
		for(int k=0;k<M*N;k++)
		{
				int i=k/M;
				int j=k%N;
			if(board[i][j]!=0) //Escaping the cells which are either occupied or being threatened by already placed piece
				continue;
			piece.setRow(i);
			piece.setCol(j);
			boolean isSafe=false;
			isSafe=canPlaceThePiece(i,j,piece,placedPieces);
			if(isSafe)
			{
				board[i][j]=(int)piece.returnSymbol();
				/*
				 * Marking all the cells which will be threatened by this placing piece
				 */
				markThreaten(board,i,j,piece.returnSymbol());
				solve(results,M,N,board,pieces,placedPieces
									+ new StringBuilder().append(piece.returnSymbol())
											.append(Integer.toString(i))
											.append(Integer.toString(j))
											.append(";").toString(),offset+1);
				/*
				 * The solution backtracks, all the changes done by placing this piece should be reversed
				 */
				unmarkThreaten(board,i,j,piece.returnSymbol());
				board[i][j]=0;
				piece.setRow(-1);
				piece.setCol(-1);
			}
		}
	
		return false;
}
	
	/**
	 * A helper function which checks whether the current piece, that is being placed, is threatened by the previously placed pieces or not and
	 *  whether placing this piece on the current position threatens an already placed piece or not.
	 * @param row Current row value
	 * @param col Current column value
	 * @param piece The Current piece to be placed
	 * @param placedPieces Previously placed pieces and their positions
	 * @return True/False, depending that the current piece can be placed safely or not for the current position
	 */
	private boolean canPlaceThePiece(int row,int col,ChessPiece piece,String placedPieces)
	{
		if(placedPieces.length()==0)
			return true;
		String temp[] = placedPieces.split(";");
		for (int i = 0; i < temp.length; i++) {
			boolean attackFrom = false, attackTo = false;
			ChessPiece placedPiece=null;
			char a = temp[i].charAt(0);
			switch(a){
			case 'K': placedPiece=new King();
			break;
			case 'R': placedPiece=new Rook();
			break;
			case 'N': placedPiece=new Knight();
			break;
			case 'B': placedPiece=new Bishop();
			break;
			case 'Q': placedPiece=new Queen();
			break;
			}
			int rowVal = temp[i].charAt(1) - 48;
			int colVal = temp[i].charAt(2) - 48;
			placedPiece.setRow(rowVal);
			placedPiece.setCol(colVal);
			attackFrom=placedPiece.isThePlaceSafe(row, col);
			if(attackFrom)
				return false;
			attackTo=piece.isThePlaceSafe(rowVal, colVal);
			if(attackTo)
				return false;
		}
		return true;
	}
	
	/**
	 * This function marks the cells on the board which will be threatened by placing the piece. The cell values are incremented by -1, so if a 
	 * cell has value < -1, it is being threatened by more than one piece.
	 * @param board The chess board
	 * @param row The row value of the piece being placed
	 * @param col The column value of the piece being placed
	 * @param piece The Piece's character representation
	 */
	private void markThreaten(int[][] board, int row, int col, char piece)
	{
		/*
		 * Marking cells threatened by placing Queen at (row,col)
		 */
		if(piece=='Q') 
		{
			for(int y=0;y<M;y++)
			{
				if(y!=row)
					board[y][col]-=1;
				
			}
			for(int z=0;z<N;z++)
			{
				if(z!=col)
					board[row][z]-=1;
			}
			for(int y=0;y<M;y++)
			{
				for(int z=0;z<N;z++)
				{
					if(Math.abs(row-y)==Math.abs(col-z) && (y!=row) && (z!=col))
						board[y][z]-=1;
				}
			}
		}
		/*
		 * Marking cells threatened by placing Rook at (row,col)
		 */
		if(piece=='R')
		{
			for(int y=0;y<M;y++)
			{
				if(y!=row)
					board[y][col]-=1;
				
			}
			for(int z=0;z<N;z++)
			{
				if(z!=col)
					board[row][z]-=1;
			}
		}
		
		/*
		 * Marking cells threatened by placing Bishop at (row,col)
		 */
		if(piece=='B')
		{
			for(int y=0;y<M;y++)
			{
				for(int z=0;z<N;z++)
				{
					if(Math.abs(row-y)==Math.abs(col-z) && (y!=row) && (z!=col))
						board[y][z]-=1;
				}
			}
		}
		
		/*
		 * Marking cells threatened by placing King at (row,col)
		 */
		if(piece=='K')
		{
			int flag0=0,flag1=0,flag2=0,flag3=0;	
			if((row-1)>=0)
			{
				board[row-1][col]-=1;
				flag0=1;
			}
			if((col-1)>=0)
			{
				board[row][col-1]-=1;
				flag1=1;
			}
			if((row+1)<N)
			{
				board[row+1][col]-=1;
				flag2=1;
			}
			if((col+1)<N)
			{
				board[row][col+1]-=1;
				flag3=1;
			}
			if(flag0==1 && flag1==1)
				board[row-1][col-1]-=1;
			if(flag2==1 && flag3==1)
				board[row+1][col+1]-=1;
			if(flag0==1 && flag3==1)
				board[row-1][col+1]-=1;
			if(flag1==1 && flag2==1)
				board[row+1][col-1]-=1;
		}
	}
	
	
	/**
	 * This function unmarks the cells of the board as the piece is no more threatening the cell. The value is increased by 1. If the value 
	 * of the cell is still < -1 after this operation, it means that the cell was threatened by more than one placed pieces.
	 * @param board The chess board
	 * @param row The row value of the piece
	 * @param col The column value of the piece
	 * @param piece The character representation of the Piece
	 */
	private void unmarkThreaten(int[][] board,int row, int col, char piece)
	{
		/*
		 * Unmarking the cells which were threatened by placing the Queen at (row,col)
		 */
		if(piece=='Q')
		{
			for(int y=0;y<M;y++)
			{
				if(y!=row && y!=row)
					board[y][col]+=1;
				
			}
			for(int z=0;z<N;z++)
			{
				if(z!=col && z!=col)
					board[row][z]+=1;
			}
			for(int y=0;y<M;y++)
			{
				for(int z=0;z<N;z++)
				{
					if(Math.abs(row-y)==Math.abs(col-z) && (y!=row) && (z!=col))
						board[y][z]+=1;
				}
			}
		}
		
		/*
		 * Unmarking the cells which were threatened by placing the Rook at (row,col)
		 */
		if(piece=='R')
		{
			for(int y=0;y<M;y++)
			{
				if(y!=row)
					board[y][col]+=1;
				
			}
			for(int z=0;z<N;z++)
			{
				if(z!=col)
					board[row][z]+=1;
			}
		}
		
		/*
		 * Unmarking the cells which were threatened by placing the Bishop at (row,col)
		 */
		if(piece=='B')
		{
			for(int y=0;y<M;y++)
			{
				for(int z=0;z<N;z++)
				{
					if(Math.abs(row-y)==Math.abs(col-z) && (y!=row) && (z!=col))
						board[y][z]+=1;
				}
			}
		}
		
		/*
		 * Unmarking the cells which were threatened by placing the King at (row,col)
		 */
		if(piece=='K')
		{
			int flag0=0,flag1=0,flag2=0,flag3=0;	
			if((row-1)>=0)
			{
				board[row-1][col]+=1;
				flag0=1;
			}
			if((col-1)>=0)
			{
				board[row][col-1]+=1;
				flag1=1;
			}
			if((row+1)<N)
			{
				board[row+1][col]+=1;
				flag2=1;
			}
			if((col+1)<N)
			{
				board[row][col+1]+=1;
				flag3=1;
			}
			if(flag0==1 && flag1==1)
				board[row-1][col-1]+=1;
			if(flag2==1 && flag3==1)
				board[row+1][col+1]+=1;
			if(flag0==1 && flag3==1)
				board[row-1][col+1]+=1;
			if(flag1==1 && flag2==1)
				board[row+1][col-1]+=1;
		}
	}
}
