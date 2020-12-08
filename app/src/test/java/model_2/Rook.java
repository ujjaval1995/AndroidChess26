package model_2;

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
		moved = false;
	}

	Rook(Rook rook)
	{
		super(rook.getColor());
		moved = rook.moved;
	}
	
	public String toString()
	{
		return super.toString() + "R";
	}

	public boolean move(String input, boolean modify)
	{
		int col1 = fileToCol(input.charAt(0));
		int row1 = rankToRow(input.charAt(1));
		int col2 = fileToCol(input.charAt(3));
		int row2 = rankToRow(input.charAt(4));
		boolean ret = move_straight(row1, col1, row2, col2, modify);
		if (ret && modify)
		{
			moved = true;
		}
		return ret;
	}
}
