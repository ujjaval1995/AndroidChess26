package chess;

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
		this.moved = false;
		this.enpassant = false;
	}
	
	public String toString()
	{
		return super.toString() + "p";
	}

	public boolean move(String input, boolean modify)
	{
		int col1 = Board.file_to_col(input.charAt(0));
		int row1 = Board.rank_to_row(input.charAt(1));
		int col2 = Board.file_to_col(input.charAt(3));
		int row2 = Board.rank_to_row(input.charAt(4));
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
				if (Board.board[(row1+row2)/2][col1] != null) // clear path
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
		if (Board.board[row2][col2] == null)
		{
			Piece piece = Board.board[row1][col2];
			if (piece instanceof Pawn && !piece.hasColor(this.getColor()))
			{
				Pawn pawn = (Pawn) piece;
				if (pawn.enpassant)
				{
					Board.board[row1][col1] = null;
					Board.board[row1][col2] = null;
					Board.board[row2][col2] = this;
					if (Board.check(this.getColor()))
					{
						Board.board[row1][col1] = this;
						Board.board[row1][col2] = piece;
						Board.board[row2][col2] = null;
						return false;
					}
					else
					{
						if (!modify)
						{
							Board.board[row1][col1] = this;
							Board.board[row1][col2] = piece;
							Board.board[row2][col2] = null;
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
					Board.board[row][col] = new Rook(this.getColor());
					break;
				case 'N':
					Board.board[row][col] = new Knight(this.getColor());
					break;
				case 'B':
					Board.board[row][col] = new Bishop(this.getColor());
					break;
				default:
					Board.board[row][col] = new Queen(this.getColor());
			}
		}
	}
}
