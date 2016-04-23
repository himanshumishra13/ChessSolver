
public class ChessPiece {
	static int N=4;
	
	public static void main(String[] args) {
		
		int board[][]=new int[N][N];
		
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				{board[i][j]=0;}
		
		
		solve(board,"RRCCCC");
		


	}
	
	public static char getAlphabet(int n){
		switch(n){
		case 1: return 'K';
		case 2: return 'R';
		case 3: return 'N';
		case 4: return 'B';
		case 5: return 'Q';
		default: return '0';
		}
	}
	

	public static boolean solve(int[][] board,String rest)
	{
		
		if(rest.length()==0)
		{	
			
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
					{
						System.out.print(getAlphabet(board[i][j])+" ");
					}
				System.out.println();
			}
			System.out.println();
			
			return true;
		}
		char c=rest.charAt(0);
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				boolean isSafe=false;

				switch(c)
				{
				case 'K': isSafe=isSafeKing(board,i,j);
				break;
				case 'R': isSafe=isSafeRook(board,i,j);
				break;
				case 'C': isSafe=isSafeKnight(board,i,j);
				break;
				case 'B': isSafe=isSafeBishop(board,i,j);
				break;
				case 'Q': isSafe=isSafeBishop(board,i,j);
				}
				if(isSafe)
				{
					

					switch(c){
					case 'K': board[i][j]=1;
					break;
					case 'R': board[i][j]=2;
					break;
					case 'C': board[i][j]=3;
					break;
					case 'B': board[i][j]=4;
					break;
					case 'Q': board[i][j]=5;
					}
					
					StringBuffer sb=new StringBuffer(rest);
					sb.deleteCharAt(0);
					solve(board,sb.toString());
					
						

					
					board[i][j]=0;
				}
			}
		}

		return false;
	}



	public static boolean isSafeKing(int[][]board,int row, int col)
	{
		if(board[row][col]!=0)
			return false;
		/**
		 * Checking if any piece is on the left side of the king
		 */
		
		for(int i=0;i<N;i++)
		{
			if(board[row][i]==1 && Math.abs(col-i)>=2)
				break;
			if(board[row][i]!=0)
			{						
				return false;
			}
		}
		for(int i=0;i<N;i++)
		{
			if(board[i][col]==1 && Math.abs(row-i)>=2)
				break;
			if(board[i][col]!=0)
			{						
				return false;
			}
		}

		/**
		 *  Checking if any piece is on the upper left diagonal of the king
		 */
		for(int i=row,j=col;i>=0 && j>=0; i--, j--)
		{
			if(board[i][j]==1 && (row-i)>=2 && (col-j)>=2)
				break;
			if(board[i][j]==2 && (row-i)>=2 && (col-j)>=2)
				break;
			if(board[i][j]!=0)
				return false;
		}

		/**
		 * Checking if any pieces lie on the upper right diagonal
		 */
		for(int i=row,j=col;i>=0 && j<N;i--,j++)
		{
			if(board[i][j]==1 && (row-i)>=2 && Math.abs((col-j))>=2)
				break;
			if(board[i][j]==2 && (row-i)>=2 && Math.abs((col-j))>=2)
				break;
			if(board[i][j]!=0)
				return false;
		}

		/**
		 * Checking if any piece lie on the lower left diagonal
		 */
		for(int i=row,j=col;i<N && j>=0;i++,j--)
		{
			if(board[i][j]==1 && Math.abs((row-i))>=2 && (col-j)>=2)
				break;
			if(board[i][j]==2 && Math.abs((row-i))>=2 && (col-j)>=2)
				break;
			if(board[i][j]!=0)
				return false;
		}
		/**
		 * Checking if any piece lie on the lower right diagonal
		 */
		for(int i=row,j=col;i<N && j<N;i++,j++)
		{
			if(board[i][j]==1 && Math.abs((row-i))>=2 && Math.abs((col-j))>=2)
				break;
			if(board[i][j]==2 && Math.abs((row-i))>=2 && Math.abs((col-j))>=2)
				break;
			if(board[i][j]!=0)
				return false;
		}

		return true;
	}

	public static boolean isSafeRook(int[][]board, int row, int col)
	{
		if(board[row][col]!=0)
			return false;
		/**
		 * Checking if any piece lie in the same row
		 */
		for(int i=0;i<N;i++)
		{
			if(board[row][i]!=0)
				return false;
			if(board[i][col]!=0)
				return false;
		}
		/**
		 * Checking if any piece lie on the upper left diagonal
		 */
		for(int i=row,j=col;i>=0 && j>=0; i--, j--)
		{
			if(board[i][j]==1 && (row-i)>=2 && (col-j)>=2)
				break;
			if(board[i][j]==2)
				break;
			if(board[i][j]==3)
				break;
			if(board[i][j]!=0)
				return false;
		}

		/**
		 * Checking if any pieces lie on the upper right diagonal
		 */
		for(int i=row,j=col;i>=0 && j<N;i--,j++)
		{
			if(board[i][j]==1 && (row-i)>=2 && Math.abs((col-j))>=2)
				break;
			if(board[i][j]==2)
				break;
			if(board[i][j]==3)
				break;
			if(board[i][j]!=0)
				return false;
		}

		/**
		 * Checking if any piece lie on the lower left diagonal
		 */
		for(int i=row,j=col;i<N && j>=0;i++,j--)
		{
			if(board[i][j]==1 && Math.abs((row-i))>=2 && (col-j)>=2)
				break;
			if(board[i][j]==2)
				break;
			if(board[i][j]==3)
				break;
			if(board[i][j]!=0)
				return false;
		}
		/**
		 * Checking if any piece lie on the lower right diagonal
		 */
		for(int i=row,j=col;i<N && j<N;i++,j++)
		{
			if(board[i][j]==1 && Math.abs((row-i))>=2 && Math.abs((col-j))>=2)
				break;
			if(board[i][j]==2)
				break;
			if(board[i][j]==3)
				break;
			if(board[i][j]!=0)
				return false;
		}


		return true;
	}

	public static boolean isSafeKnight(int[][]board, int row,int col){
		
		/**
		 * Checking in row for any threatening pieces
		 */
		if(board[row][col]!=0)
			return false;
		for(int i=0;i<N;i++)
		{
			if(board[row][i]==1 && Math.abs(col-i)>=2)
				break;
			if(board[row][i]==3)
				break;
			if(board[row][i]==4)
				break;
			if(board[row][i]!=0)
				return false;
		}
		
		/**
		 * Checking in column for any threatening piece
		 */
		for(int i=0;i<N;i++)
		{
			if(board[i][col]==1 && Math.abs(row-i)>=2)
				break;
			if(board[i][col]==3)
				break;
			if(board[i][col]==4)
				break;
			if(board[i][col]!=0)
				return false;
		}
		/**
		 * Checking in Upper left diagonal
		 */
		for(int i=row,j=col;i>=0 && j>=0;i--,j--)
		{
			if(board[i][j]==1 && Math.abs(row-i)>=2 && Math.abs(col-j)>=2)
				break;
			if(board[i][j]==2)
				break;
			if(board[i][j]==3)
				break;
			if(board[i][j]!=0)
				return false;
		}
		/**
		 * Checking in upper right diagonal
		 */
		for(int i=row,j=col;i>=0 && j<N;i--,j++)
		{
			if(board[i][j]==1 && Math.abs(row-i)>=2 && Math.abs(col-j)>=2)
				break;
			if(board[i][j]==2)
				break;
			if(board[i][j]==3)
				break;
			if(board[i][j]!=0)
				return false;
		}
		
		/**
		 * Checking in lower left diagonal
		 */
		for(int i=row,j=col;i<N && j>=0;i++,j--)
		{
			if(board[i][j]==1 && Math.abs(row-i)>=2 && Math.abs(col-j)>=2)
				break;
			if(board[i][j]==2)
				break;
			if(board[i][j]==3)
				break;
			if(board[i][j]!=0)
				return false;
		}
		
		/**
		 * Checking lower right diagonal
		 */
		for(int i=row,j=col;i<N && j<N;i++,j++)
		{
			if(board[i][j]==1 && Math.abs(row-i)>=2 && Math.abs(col-j)>=2)
				break;
			if(board[i][j]==2)
				break;
			if(board[i][j]==3)
				break;
			if(board[i][j]!=0)
				return false;
		}
		
		/**
		 * Checking if Knight threaten any piece (2L1U)
		 */
		if(row-1>=0 && col-2>=0)
		{
			if((board[row-1][col-2]!=0))
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (1L2U) 
		 */
		if(row-2>=0 && col-1>=0)
		{
			if(board[row-2][col-1]!=0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (1R2U)
		 */
		if(row-2>=0 && col+1<N)
		{
			if(board[row-2][col+1]!=0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (2R1U)
		 */
		if(row-1>=0 && col+2<N)
		{
			if(board[row-1][col+2]!=0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (2R1D)
		 */
		if(row+1<N && col+2<N)
		{
			if(board[row+1][col+2]!=0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (1R2D)
		 *
		 */
		if(row+2<N && col+1<N)
		{
			if(board[row+2][col+1]!=0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (1L2D)
		 */
		if(row+2<N && col-1>=0)
		{
			if(board[row+2][col-1]!=0)
				return false;
		}
		/**
		 * Checking if Knight threaten any piece (2L1D)
		 */
		if(row+1<N && col-2>=0)
		{
			if(board[row+1][col-2]!=0)
				return false;
		}
	return true;	
	}
	public static boolean isSafeBishop(int[][]board, int row, int col)
	{
		if(board[row][col]!=0)
			return false;
		/**
		 * Checking if any piece lie in the same row
		 */
		for(int i=0;i<N;i++)
		{
			if(board[row][i]==1 && Math.abs(col-i)>=2)
				break;
			if(board[row][i]==3)
				break;
			if(board[row][i]!=0)
			{						
				return false;
			}
		}
		for(int i=0;i<N;i++)
		{
			if(board[i][col]==1 && Math.abs(row-i)>=2)
				break;
			if(board[i][col]==3)
				break;
			if(board[i][col]!=0)
			{						
				return false;
			}
		}
		/**
		 * Checking if any piece lie on the upper left diagonal
		 */
		for(int i=row,j=col;i>=0 && j>=0; i--, j--)
		{
			if(board[i][j]!=0)
				return false;
		}

		/**
		 * Checking if any pieces lie on the upper right diagonal
		 */
		for(int i=row,j=col;i>=0 && j<N;i--,j++)
		{
			if(board[i][j]!=0)
				return false;
		}

		/**
		 * Checking if any piece lie on the lower left diagonal
		 */
		for(int i=row,j=col;i<N && j>=0;i++,j--)
		{
			if(board[i][j]!=0)
				return false;
		}
		/**
		 * Checking if any piece lie on the lower right diagonal
		 */
		for(int i=row,j=col;i<N && j<N;i++,j++)
		{
			if(board[i][j]!=0)
				return false;
		}


		return true;
	}
	public static boolean isSafeQueen(int[][]board, int row, int col)
	{
		if(board[row][col]!=0)
			return false;
		/**
		 * Checking if any piece lie in the same row
		 */
		for(int i=0;i<N;i++)
		{
			if(board[row][i]!=0)
				return false;
			if(board[i][col]!=0)
				return false;
		}
		/**
		 * Checking if any piece lie on the upper left diagonal
		 */
		for(int i=row,j=col;i>=0 && j>=0; i--, j--)
		{
			if(board[i][j]!=0)
				return false;
		}

		/**
		 * Checking if any pieces lie on the upper right diagonal
		 */
		for(int i=row,j=col;i>=0 && j<N;i--,j++)
		{
			if(board[i][j]!=0)
				return false;
		}

		/**
		 * Checking if any piece lie on the lower left diagonal
		 */
		for(int i=row,j=col;i<N && j>=0;i++,j--)
		{
			if(board[i][j]!=0)
				return false;
		}
		/**
		 * Checking if any piece lie on the lower right diagonal
		 */
		for(int i=row,j=col;i<N && j<N;i++,j++)
		{
			if(board[i][j]!=0)
				return false;
		}


		return true;
		
	}
}



