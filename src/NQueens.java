
public class NQueens {

	static int N=8;
	//static 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int board[][]=new int[N][N];
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				board[i][j]=0;
		if(solve(board,0))
		{
			for(int i=0;i<N;i++)
				{
					for(int j=0;j<N;j++)
						System.out.print(board[i][j]+ " ");
					System.out.println();
				}
		}
		

	}
	
	public static boolean solve(int[][] board, int col)
	{
		if(col>=N)
		{
			for(int i=0;i<N;i++)
			{
				for(int j=0;j<N;j++)
					System.out.print(board[i][j]+" ");
				System.out.println();
			}
			System.out.println();
			return true;
		}
		for(int i=0;i<N;i++)
		{
			if(isSafe(board,i,col))
			{
				board[i][col]=1;
			
				solve(board,col+1);
						
				board[i][col]=0;
			}
		}
		return false;
	}
	
	public static boolean isSafe(int[][]board,int row, int col)
	{
		for(int i=0;i<col;i++)
			if(board[row][i]==1)
				return false;
		
		for(int i=row,j=col;i>=0 && j>=0;i--,j--)
		{
			if(board[i][j]==1)
				return false;
		}
		
		for(int i=row,j=col;i<N && j<N; i++,j++)
			if(board[i][j]==1)
				return false;
		for(int i=row,j=col; i<N && j>=0;i++,j--)
			if(board[i][j]==1)
				return false;
		for(int i=row,j=col; i>=0 && j<N;i--,j++)
			if(board[i][j]==1)
				return false;
		return true;
		}
	

}
