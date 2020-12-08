package model_2;

/**
 * This class represents the general Piece.
 *
 * @author Jishnu Patel
 * @author Ujjaval Shah
 */

public abstract class Piece
{
	protected Piece[][] boardIdx;
	private String color;
	
	Piece(Piece[][] boardIdx, String color)
	{
		this.boardIdx = boardIdx;
		if (color.equals("white"))
		{
			this.color = color;
		}
		else
		{
			this.color = "black";
		}
	}

	Piece(Piece[][] boardIdx, Piece piece)
	{
		this.boardIdx = boardIdx;
		color = piece.color;
	}
	
	public String toString()
	{
		if (this.color.equals("white"))
		{
			return "w";
		}
		else
		{
			return "b";
		}
	}
	
	public String getColor()
	{
		if (this.color.equals("white"))
		{
			return "white";
		}
		else
		{
			return "black";
		}
	}
	
	public boolean hasColor(String color)
	{
		if (this.color.equals(color))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public abstract boolean move(String input, boolean modify);

	public boolean regular_move(int row1, int col1, int row2, int col2, boolean modify)
	{
		if (boardIdx[row2][col2] == null) // move
		{
			boardIdx[row1][col1] = null;
			boardIdx[row2][col2] = this;
			if (Board.check(boardIdx, color))
			{
				boardIdx[row1][col1] = this;
				boardIdx[row2][col2] = null;
				return false;
			}
			else
			{
				if (!modify)
				{
					boardIdx[row1][col1] = this;
					boardIdx[row2][col2] = null;
				}
				return true;
			}
		}
		else
		{
			return false;
		}
	}
	
	public boolean capture_move(int row1, int col1, int row2, int col2, boolean modify)
	{		
		if (boardIdx[row2][col2] != null && !boardIdx[row2][col2].hasColor(color)) // capture
		{
			Piece piece = boardIdx[row2][col2];
			boardIdx[row1][col1] = null;
			boardIdx[row2][col2] = this;
			if (Board.check(boardIdx, color))
			{
				boardIdx[row1][col1] = this;
				boardIdx[row2][col2] = piece;
				return false;
			}
			else
			{
				if (!modify)
				{
					boardIdx[row1][col1] = this;
					boardIdx[row2][col2] = piece;
				}
				return true;
			}
		}
		else
		{
			return false;
		}
	}

	public boolean move_straight(int row1, int col1, int row2, int col2, boolean modify)
	{		
		if (row1 == row2 && col1 == col2) // same
		{
			return false;
		}
		else if (row1 == row2 && col1 != col2) // horizontal
		{
			int min = Math.min(col1, col2);
			int max = Math.max(col1, col2);
			for (int j=min+1; j<max; j++) // clear path
			{
				if (boardIdx[row1][j] != null)
				{
					return false;
				}
			}
		}
		else if (row1 != row2 && col1 == col2) // vertical
		{
			int min = Math.min(row1, row2);
			int max = Math.max(row1, row2);
			for (int i=min+1; i<max; i++) // clear path
			{
				if (boardIdx[i][col1] != null)
				{
					return false;
				}
			}
		}
		else // not same, horizontal, vertical
		{
			return false;
		}
		return (regular_move(row1, col1, row2, col2, modify) || capture_move(row1, col1, row2, col2, modify));
	}
	
	public boolean move_diagonal(int row1, int col1, int row2, int col2, boolean modify)
	{	
		if (row1 == row2 || col1 == col2) // same, horizontal, vertical
		{
			return false;
		}
		else if (row1 + col1 == row2 + col2) // diagonal /
		{
			int minrow = Math.min(row1, row2);
			if (row1 == minrow) // down-left
			{
				for (int i=row1+1, j=col1-1; i<row2; i++, j--) // clear path
				{
					if (boardIdx[i][j] != null)
					{
						return false;
					}
				}
			}
			else // up-right
			{
				for (int i=row2+1, j=col2-1; i<row1; i++, j--) // clear path
				{
					if (boardIdx[i][j] != null)
					{
						return false;
					}
				}
			}
		}
		else if (row1 + (7-col1) == row2 + (7-col2)) // diagonal \
		{
			int minrow = Math.min(row1, row2);
			if (row1 == minrow) // down-right
			{
				for (int i=row1+1, j=col1+1; i<row2; i++, j++) // clear path
				{
					if (boardIdx[i][j] != null)
					{
						return false;
					}
				}
			}
			else // up-left
			{
				for (int i=row2+1, j=col2+1; i<row1; i++, j++) // clear path
				{
					if (boardIdx[i][j] != null)
					{
						return false;
					}
				}
			}
		}
		else // not same, horizontal, vertical, diagonal
		{
			return false;
		}
		return (regular_move(row1, col1, row2, col2, modify) || capture_move(row1, col1, row2, col2, modify));
	}
}
