package model_2;

/**
 * This class represents the piece Knight.
 *
 * @author Jishnu Patel
 * @author Ujjaval Shah
 */

public class Knight extends Piece
{
	Knight(Piece[][] boardIdx, String color)
	{
		super(boardIdx, color);
	}

	Knight(Piece[][] boardIdx, Knight knight)
	{
		super(boardIdx, knight);
	}
	
	public String toString()
	{
		return super.toString() + "N";
	}

	public boolean move(String input, boolean modify)
	{
		int col1 = Board.fileToCol(input.charAt(0));
		int row1 = Board.rankToRow(input.charAt(1));
		int col2 = Board.fileToCol(input.charAt(3));
		int row2 = Board.rankToRow(input.charAt(4));
		return move_L(row1, col1, row2, col2, modify);
	}
	
	public boolean move_L(int row1, int col1, int row2, int col2, boolean modify)
	{

		if ((Math.abs(row1-row2) == 2 && Math.abs(col1-col2) == 1) || (Math.abs(row1-row2) == 1 && Math.abs(col1-col2) == 2)) // L
		{
			return (regular_move(row1, col1, row2, col2, modify) || capture_move(row1, col1, row2, col2, modify));
		}
		else // not L
		{
			return false;
		}
	}
}
