package com.himanshu.chessSolver;

/**
 * This class is the driver class for the ChessBoard solver.
 * @author Himanshu
 *
 */
public class ChessDriver {

	public static void main(String[] args) {
		
		int M=7,N=7;
		Result result=new Result(M,N);
		ChessBoardSolver sol=new ChessBoardSolver();
		long startTime = System.nanoTime();
		sol.solve(result, new int[M][N], "QQBBKKN", M, N, "");
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		double timeElapsed = duration / (1000000);
		//result.printResult(M, N); //To print the solutions
		System.out.println("The Elapsed time in milliseconds is:- " + timeElapsed);
		System.out.println("The total no. of unique solutions are "+result.getSetSize());
		
	}

}
