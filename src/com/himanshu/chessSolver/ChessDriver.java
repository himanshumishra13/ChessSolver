package com.himanshu.chessSolver;

import java.util.Arrays;

/**
 * This class is the driver class for the ChessBoard solver.
 * @author Himanshu
 *
 */
public class ChessDriver {

	public static void main(String[] args) {
		
		int M=7,N=7;
		Result result;
		ChessPiece[] piece={new Queen(),new Queen(),new Bishop(),new Bishop(),new King(),new King(),new Knight()};
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
