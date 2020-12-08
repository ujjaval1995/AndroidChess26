package model_2;

/**
 * This class represents the piece Queen.
 *
 * @author Jishnu Patel
 * @author Ujjaval Shah
 */

public class Queen extends Piece
{
	Queen(Piece[][] boardIdx, String color)
	{
		super(boardIdx, color);
	}

	Queen(Piece[][] boardIdx, Queen queen)
	{
		super(boardIdx, queen);
	}
	
	public String toString()
	{
		return super.toString() + "Q";
	}

	public boolean move(String input, boolean modify)
	{
		int col1 = Board.fileToCol(input.charAt(0));
		int row1 = Board.rankToRow(input.charAt(1));
		int col2 = Board.fileToCol(input.charAt(3));
		int row2 = Board.rankToRow(input.charAt(4));
		return move_straight(row1, col1, row2, col2, modify) || move_diagonal(row1, col1, row2, col2, modify);
	}
}
