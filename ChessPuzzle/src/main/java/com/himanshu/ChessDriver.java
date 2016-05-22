package com.himanshu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.himanshu.chessElements.Bishop;
import com.himanshu.chessElements.ChessBoard;
import com.himanshu.chessElements.ChessPiece;
import com.himanshu.chessElements.King;
import com.himanshu.chessElements.Knight;
import com.himanshu.chessElements.Queen;
import com.himanshu.chessElements.Result;
import com.himanshu.chessElements.Rook;

/**
 * This class is the driver class for the ChessBoard solver. This class takes in dimension of the board and pieces to be placed
 * as command line arguments. The detailed description is:-
 * arg1 = No. of Rows on the board
 * arg2 = No. of Columns on the board
 * arg3 = No. of Queens to be placed
 * arg4 = No. of Bishops to be placed
 * arg5 = No. of Rooks to be placed
 * arg6 = No. of Knights to be placed
 * arg7 = No. of Kings to be placed
 * Eg. If we have to place 2 Kings and a Rook on a 3x3 board, the command line arguments will be:-
 * 3 3 0 0 1 0 2.
 * @author Himanshu
 *
 */
public class ChessDriver {

	public static void main(String[] args) {
		
		int M,N;
		M=Integer.parseInt(args[0]);
		N=Integer.parseInt(args[1]);
		List<ChessPiece> pieceList=new ArrayList<ChessPiece>();
		int noOfQueen, noOfBishop, noOfRook, noOfKing, noOfKnight;
		noOfQueen=Integer.parseInt(args[2]);
		noOfBishop=Integer.parseInt(args[3]);
		noOfRook=Integer.parseInt(args[4]);
		noOfKnight=Integer.parseInt(args[5]);
		noOfKing=Integer.parseInt(args[6]);
		for(int i=0;i<noOfQueen;i++)
		{
			Queen queen=new Queen();
			pieceList.add(queen);
		}
		for(int i=0;i<noOfBishop;i++)
		{
			Bishop bishop=new Bishop();
			pieceList.add(bishop);
		}
		for(int i=0;i<noOfRook;i++)
		{
			Rook rook=new Rook();
			pieceList.add(rook);
		}
		for(int i=0;i<noOfKnight;i++)
		{
			Knight knight=new Knight();
			pieceList.add(knight);
		}
		for(int i=0;i<noOfKing;i++)
		{
			King king=new King();
			pieceList.add(king);
		}
		Result result;
		ChessBoard chess=new ChessBoard(M,N,pieceList);
		long startTime = System.nanoTime();
		result=chess.getSolutions();
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		double timeElapsed = duration / (1000000);
		//result.printResult(M, N); //To print the solutions
		System.out.println("The Elapsed time in milliseconds is:- " + timeElapsed);
		System.out.println("The total no. of unique solutions are "+result.getSetSize());
		
	}

}
