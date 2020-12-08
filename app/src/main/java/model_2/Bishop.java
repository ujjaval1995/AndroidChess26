package model_2;

/**
 * This class represents the Bishop.
 *
 * @author Jishnu Patel
 * @author Ujjaval Shah
 */

public class Bishop extends Piece
{
	Bishop(Piece[][] boardIdx, String color)
	{
		super(boardIdx, color);
	}

	Bishop(Piece[][] boardIdx, Bishop bishop)
	{
		super(boardIdx, bishop);
	}
	
	public String toString()
	{
		return super.toString() + "B";
	}

	public boolean move(String input, boolean modify)
	{
		int col1 = Board.fileToCol(input.charAt(0));
		int row1 = Board.rankToRow(input.charAt(1));
		int col2 = Board.fileToCol(input.charAt(3));
		int row2 = Board.rankToRow(input.charAt(4));
		return move_diagonal(row1, col1, row2, col2, modify);
	}
}
