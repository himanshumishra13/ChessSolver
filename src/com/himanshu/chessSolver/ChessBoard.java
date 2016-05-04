package com.himanshu.chessSolver;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
	private int M,N;
	private int board[][];
	List<ChessPiece> pieces; 

	public ChessBoard(int M,int N,List<ChessPiece> piecesToPlace)
	{
		this.M=M;
		this.N=N;
		pieces=piecesToPlace;
		board=new int[M][N];
		initializeBoard();
	}
	
	private void initializeBoard()
	{
		for(int i=0;i<this.getM();i++)
		{
			for(int j=0;j<this.getN();j++)
				board[i][j]=0;
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
	
	public Result getSolutions()
	{
		Result results=new Result(this.getM(),this.getN());
		
		solve(results,this.getM(),this.getN(),this.board,this.pieces,"",0);
		
		return results;
	}
	
	public boolean solve(Result results,int M,int N,int board[][],List<ChessPiece> pieces,String placedPieces,int offset)
	{
		if(offset==pieces.size())
		{
			StringBuilder sb1 = new StringBuilder();
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] != 0) {
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
			if(board[i][j]!=0)
				continue;
			piece.setRow(i);
			piece.setCol(j);
			boolean isSafe=false;
			isSafe=canPlaceThePiece(i,j,piece,placedPieces);
			if(isSafe)
			{
				board[i][j]=(int)piece.returnSymbol();
				//pieces.remove(0);
				solve(results,M,N,board,pieces,placedPieces
									+ new StringBuilder().append(piece.returnSymbol())
											.append(Integer.toString(i))
											.append(Integer.toString(j))
											.append(";").toString(),offset+1);
				board[i][j]=0;
				piece.setRow(-1);
				piece.setCol(-1);
			}
		}
		return false;
	}
	
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
}
