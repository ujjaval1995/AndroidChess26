package chess;

/**
 * This class represents the piece Knight.
 *
 * @author Jishnu Patel
 * @author Ujjaval Shah
 */
public class Knight extends Piece
{
	Knight(String color)
	{
		super(color);
	}
	
	public String toString()
	{
		return super.toString() + "N";
	}

	public boolean move(String input, boolean modify)
	{
		int col1 = Board.file_to_col(input.charAt(0));
		int row1 = Board.rank_to_row(input.charAt(1));
		int col2 = Board.file_to_col(input.charAt(3));
		int row2 = Board.rank_to_row(input.charAt(4));
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
