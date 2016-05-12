package com.himanshu.chessElements;

import java.util.ArrayList;
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
		List<ChessPiece> placedPiece=new ArrayList<ChessPiece>();
		solve(results,this.getM(),this.getN(),this.board,this.pieces,placedPiece,0);
		
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
	 * @param placedPieces A list of ChessPieces types which are already placed on the board
	 * @param offset A counter which points to the current piece in the pieces list
	 * @return True/False, depending whether the piece can be placed or not.
	 */
	public boolean solve(Result results,int M,int N,int board[][],List<ChessPiece> pieces,List<ChessPiece> placedPieces,int offset)
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
		for(int k=0;k<(M*N);k++)
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
				piece.markThreaten(board, M, N);
				placedPieces.add(piece);
				solve(results,M,N,board,pieces,placedPieces,offset+1);
				/*
				 * The solution backtracks, all the changes done by placing this piece should be reversed
				 */
				piece.unmarkThreaten(board, M, N);
				placedPieces.remove(placedPieces.size()-1);
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
	private boolean canPlaceThePiece(int row,int col,ChessPiece piece,List<ChessPiece> placedPieces)
	{
		if(placedPieces.size()==0)
			return true;
		for (int i = 0; i < placedPieces.size(); i++) {
			boolean attackFrom = false, attackTo = false;
			attackFrom=placedPieces.get(i).isThePlaceSafe(row, col);
			if(attackFrom)
				return false;
			attackTo=piece.isThePlaceSafe(placedPieces.get(i).getRow(), placedPieces.get(i).getCol());
			if(attackTo)
				return false;
		}
		return true;
	}
	
	
}
