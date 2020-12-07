package model;

/**
 * This class represents the piece King.
 *
 * @author Jishnu Patel
 * @author Ujjaval Shah
 */
public class King extends Piece
{
	boolean moved;
	
	King(String color)
	{
		super(color);
		moved = false;
	}
	
	public String toString()
	{
		return super.toString() + "K";
	}

	public boolean move(String input, boolean modify)
	{
		int col1 = fileToCol(input.charAt(0));
		int row1 = rankToRow(input.charAt(1));
		int col2 = fileToCol(input.charAt(3));
		int row2 = rankToRow(input.charAt(4));
		boolean ret = move_1(row1, col1, row2, col2, modify) || castle(row1, col1, row2, col2);
		if (ret && modify)
		{
			moved = true;
		}
		return ret;
	}
	
	public boolean move_1(int row1, int col1, int row2, int col2, boolean modify)
	{
		
		if (row1 == row2 && col1 == col2) // same
		{
			return false;
		}
		else if (Math.abs(row1-row2) <= 1 && Math.abs(col1-col2) <= 1) // 1
		{
			return regular_move(row1, col1, row2, col2, modify) || capture_move(row1, col1, row2, col2, modify);
		}
		else // not same, 1
		{
			return false;
		}
	}
	
	public boolean castle(int row1, int col1, int row2, int col2)
	{
		if (moved || row1 != row2 || check(this.getColor())) // moved, different row, check
		{
			return false;
		}
		else if (col1+2 == col2) // right 2
		{
			if (regular_move(row1, col1, row1, col1+1, false) && regular_move(row1, col1, row1, col1+2, false)) // move and clear path
			{
				Piece piece = boardIdx[row1][7];
				if (piece instanceof Rook && piece.hasColor(this.getColor()))
				{
					Rook rook = (Rook) piece;
					if (rook.moved)
					{
						return false;
					}
					else
					{
						boardIdx[row1][col1] = null;
						boardIdx[row1][col1+1] = piece;
						boardIdx[row1][col1+2] = this;
						boardIdx[row1][7] = null;
						if (check(this.getColor()))
						{
							boardIdx[row1][col1] = this;
							boardIdx[row1][col1+1] = null;
							boardIdx[row1][col1+2] = null;
							boardIdx[row1][7] = piece;
							return false;
						}
						else
						{
							return true;
						}
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else if (col1 == col2+2) // left 2
		{
			if (regular_move(row1, col1, row1, col1-1, false) && regular_move(row1, col1, row1, col1-2, false)) // move and clear path
			{
				Piece piece = boardIdx[row1][0];
				if (piece instanceof Rook && piece.hasColor(this.getColor()))
				{
					Rook rook = (Rook) piece;
					if (rook.moved)
					{
						return false;
					}
					else
					{
						boardIdx[row1][col1] = null;
						boardIdx[row1][col1-1] = piece;
						boardIdx[row1][col1-2] = this;
						boardIdx[row1][0] = null;
						if (check(this.getColor()))
						{
							boardIdx[row1][col1] = this;
							boardIdx[row1][col1-1] = null;
							boardIdx[row1][col1-2] = null;
							boardIdx[row1][0] = piece;
							return false;
						}
						else
						{
							return true;
						}
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else // not right 2, left 2
		{
			return false;
		}
	}
}
