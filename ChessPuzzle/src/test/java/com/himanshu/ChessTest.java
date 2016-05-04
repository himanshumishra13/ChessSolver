package com.himanshu;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.himanshu.chessElements.Bishop;
import com.himanshu.chessElements.ChessBoard;
import com.himanshu.chessElements.ChessPiece;
import com.himanshu.chessElements.King;
import com.himanshu.chessElements.Knight;
import com.himanshu.chessElements.Queen;
import com.himanshu.chessElements.Result;
import com.himanshu.chessElements.Rook;


/**
 * Unit test for ChessPuzzle.
 */

public class ChessTest {

	
   @Test
   public void testForTwoKingsOneRook()
   {
	   ChessPiece[] piece={new Rook(),new King(),new King()};
	   ChessBoard obj=new ChessBoard(3,3,Arrays.asList(piece));
	   Result result=obj.getSolutions();
	   
	   Assert.assertTrue((result.getSet().contains("K00;K02;R21;")));
   }
   
   @Test
   public void testFor2Rooks4Knights()
   {
	   ChessPiece[] piece={new Rook(),new Rook(),new Knight(),new Knight(),new Knight(),new Knight()};
	   ChessBoard obj=new ChessBoard(4,4,Arrays.asList(piece));
	   Result result=obj.getSolutions();
	   Assert.assertTrue(result.getSetSize()==8);
   }
   
   @Test
   public void testFor4Bishops()
   {
	   ChessPiece[] piece={new Bishop(),new Bishop(),new Bishop(),new Bishop()};
	   ChessBoard obj=new ChessBoard(4,4,Arrays.asList(piece));
	   Result result=obj.getSolutions();
	   Assert.assertTrue(result.getSet().contains("B00;B10;B20;B30;"));
   }
   
   @Test
   public void testFor2Queens2Bishops2Kings1Knight()
   {
	   ChessPiece[] piece={new Queen(),new Queen(),new Bishop(),new Bishop(),new King(),new King(),new Knight()};
	   ChessBoard obj=new ChessBoard(7,7,Arrays.asList(piece));
	   Result result=obj.getSolutions();
	   Assert.assertTrue(result.getSetSize()==3063828);
   }
}

