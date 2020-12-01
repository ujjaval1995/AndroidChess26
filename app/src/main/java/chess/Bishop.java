package chess;
/**
 * This class represents the Bishop.
 *
 * @author Jishnu Patel
 * @author Ujjaval Shah
 */
public class Bishop extends Piece
{
	Bishop(String color)
	{
		super(color);
	}
	
	public String toString()
	{
		return super.toString() + "B";
	}

	public boolean move(String input, boolean modify)
	{
		int col1 = Board.file_to_col(input.charAt(0));
		int row1 = Board.rank_to_row(input.charAt(1));
		int col2 = Board.file_to_col(input.charAt(3));
		int row2 = Board.rank_to_row(input.charAt(4));
		return move_diagonal(row1, col1, row2, col2, modify);
	}
}
