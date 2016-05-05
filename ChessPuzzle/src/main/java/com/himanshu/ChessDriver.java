package com.himanshu;

import java.util.Arrays;

import com.himanshu.chessElements.Bishop;
import com.himanshu.chessElements.ChessBoard;
import com.himanshu.chessElements.ChessPiece;
import com.himanshu.chessElements.King;
import com.himanshu.chessElements.Knight;
import com.himanshu.chessElements.Queen;
import com.himanshu.chessElements.Result;
import com.himanshu.chessElements.Rook;

/**
 * This class is the driver class for the ChessBoard solver.
 * @author Himanshu
 *
 */
public class ChessDriver {

	public static void main(String[] args) {
		
		int M=7,N=7;
		//int M=4,N=4;
		//int M=3,N=3;
		Result result;
		ChessPiece[] piece={new Queen(),new Queen(),new Bishop(),new Bishop(),new King(), new King(),new Knight()};
		//ChessPiece[] piece={new Rook(),new Rook(),new Knight(),new Knight(),new Knight(), new Knight()};
		//ChessPiece[] piece={new King(), new King(),new Rook()};
		ChessBoard chess=new ChessBoard(M,N,Arrays.asList(piece));
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
