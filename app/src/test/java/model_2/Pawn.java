package model_2;

/**
 * This class represents the piece Pawn.
 *
 * @author Jishnu Patel
 * @author Ujjaval Shah
 */
public class Pawn extends Piece
{
	boolean moved;
	boolean enpassant;
	
	Pawn(String color)
	{
		super(color);
		moved = false;
		enpassant = false;
	}

	Pawn(Pawn pawn)
	{
		super(pawn.getColor());
		moved = pawn.moved;
		enpassant = pawn.enpassant;
	}
	
	public String toString()
	{
		return super.toString() + "p";
	}

	public boolean move(String input, boolean modify)
	{
		int col1 = fileToCol(input.charAt(0));
		int row1 = rankToRow(input.charAt(1));
		int col2 = fileToCol(input.charAt(3));
		int row2 = rankToRow(input.charAt(4));
		return move_forward(row1, col1, row2, col2, modify, (input.length() >= 7) ? input.charAt(6) : 'Q');
	}
	
	public boolean move_forward(int row1, int col1, int row2, int col2, boolean modify, char c)
	{
		if (col1 == col2) // straight
		{
			if ((this.hasColor("white") && row1-1 == row2) || (this.hasColor("black") && row1+1 == row2)) // 1
			{
				boolean ret = regular_move(row1, col1, row2, col2, modify);
				if (ret && modify)
				{
					moved = true;
					promote(row2, col2, c);
				}
				return ret;
			}
			else if ((this.hasColor("white") && row1-2 == row2) || (this.hasColor("black") && row1+2 == row2) && !moved) // 2
			{
				if (boardIdx[(row1+row2)/2][col1] != null) // clear path
				{
					return false;
				}
				else
				{
					boolean ret = regular_move(row1, col1, row2, col2, modify);
					if (ret && modify)
					{
						moved = true;
						enpassant = true;
					}
					return ret;
				}
			}
			else
			{
				return false;
			}
		}
		else if (Math.abs(col1-col2) == 1) // diagonal 1
		{
			if ((this.hasColor("white") && row1-1 == row2) || (this.hasColor("black") && row1+1 == row2))
			{
				if (capture_move(row1, col1, row2, col2, modify))
				{
					if (modify)
					{
						moved = true;
						promote(row2, col2, c);
					}
					return true;
				}
				else
				{
					return enpassant(row1, col1, row2, col2, modify);
				}
			}
			else
			{
				return false;
			}
		}
		else // not straight, diagonal 1
		{
			return false;
		}
	}
	
	public boolean enpassant(int row1, int col1, int row2, int col2, boolean modify)
	{
		if (boardIdx[row2][col2] == null)
		{
			Piece piece = boardIdx[row1][col2];
			if (piece instanceof Pawn && !piece.hasColor(this.getColor()))
			{
				Pawn pawn = (Pawn) piece;
				if (pawn.enpassant)
				{
					boardIdx[row1][col1] = null;
					boardIdx[row1][col2] = null;
					boardIdx[row2][col2] = this;
					if (check(this.getColor()))
					{
						boardIdx[row1][col1] = this;
						boardIdx[row1][col2] = piece;
						boardIdx[row2][col2] = null;
						return false;
					}
					else
					{
						if (!modify)
						{
							boardIdx[row1][col1] = this;
							boardIdx[row1][col2] = piece;
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
	
	public void promote(int row, int col, char c)
	{
		if ((this.hasColor("white") && row == 0) || (this.hasColor("black") && row == 7))
		{
			switch (c)
			{
				case 'R':
					boardIdx[row][col] = new Rook(this.getColor());
					break;
				case 'N':
					boardIdx[row][col] = new Knight(this.getColor());
					break;
				case 'B':
					boardIdx[row][col] = new Bishop(this.getColor());
					break;
				default:
					boardIdx[row][col] = new Queen(this.getColor());
			}
		}
	}
}
