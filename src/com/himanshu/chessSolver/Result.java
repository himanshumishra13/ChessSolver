package com.himanshu.chessSolver;

import java.util.HashSet;
/**
 * This class stores the unique board solutions for the given set of pieces
 * @author Himanshu
 *
 */

public class Result {
	private HashSet<String> result;
	private int M;
	private int N;
	public Result(int M,int N)
	{
		result=new HashSet<String>();
		this.M=M;
		this.N=N;
	}
	
	/**
	 * This function adds the result board solution in a string format to the result set
	 * @param res The String representation of the solution board
	 */
	public void addResult(String res)
	{
		this.result.add(res);
	}
	
	/**
	 * This solution prints the unique boards stored in the result set in a proper format. '0' represents empty spaces on the board.
	 * @param M The width of the board
	 * @param N The length of the board
	 */
	public void printResult(int M,int N)
	{
		 
		for(String sol:result)
		{
			int[][] board=new int[M][N];
			String temp[]=sol.split(";");
			for (int k = 0; k < temp.length; k++) {
				String g = temp[k];
				int row = g.charAt(1) - 48;
				int col = g.charAt(2) - 48;
				board[row][col] = g.charAt(0) - 48;
			}
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(getAlphabet(board[i][j]) + " ");
				}
				System.out.println();
			}
			System.out.println();

		}
	}
	
	/**
	 * Helper function which gives the character representation of the pieces
	 * @param n The numerical representation of the piece
	 * @return The Character representation of the piece
	 */
	private char getAlphabet(int n) {
		switch (n) {
		case 1:
			return 'K';
		case 2:
			return 'R';
		case 3:
			return 'N';
		case 4:
			return 'B';
		case 5:
			return 'Q';
		default:
			return '0';
		}
	}
	
	/**
	 * This function gives the number of unique solutions
	 * @return The size of the result set, i.e the number of unique solutions
	 */
	public int getSetSize()
	{
		return this.result.size();
	}

}
