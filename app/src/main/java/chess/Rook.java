package chess;

/**
 * This class represents the piece Rook.
 *
 * @author Jishnu Patel
 * @author Ujjaval Shah
 */
public class Rook extends Piece
{
	boolean moved = false;
	
	Rook(String color)
	{
		super(color);
		this.moved = false;
	}
	
	public String toString()
	{
		return super.toString() + "R";
	}

	public boolean move(String input, boolean modify)
	{
		int col1 = Board.file_to_col(input.charAt(0));
		int row1 = Board.rank_to_row(input.charAt(1));
		int col2 = Board.file_to_col(input.charAt(3));
		int row2 = Board.rank_to_row(input.charAt(4));
		boolean ret = move_straight(row1, col1, row2, col2, modify);
		if (ret && modify)
		{
			moved = true;
		}
		return ret;
	}
}
