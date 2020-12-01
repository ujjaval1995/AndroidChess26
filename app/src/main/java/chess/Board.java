package chess;
/**
 * This class represents the ChessBoard.
 *
 * @author Jishnu Patel
 * @author Ujjaval Shah
 */
public class Board
{
	static Piece[][] board;
	
	static void make_board()
	{
		board = new Piece[8][8];
	}
	
	static void initialize_board()
	{
		for (int j=0; j<8; j++)
		{
			board[1][j] = new Pawn("black");
			board[6][j] = new Pawn("white");
		}
		for (int j=0; j<8; j+=7)
		{
			board[0][j] = new Rook("black");
			board[7][j] = new Rook("white");
		}
		for (int j=1; j<8; j+=5)
		{
			board[0][j] = new Knight("black");
			board[7][j] = new Knight("white");
		}
		for (int j=2; j<8; j+=3)
		{
			board[0][j] = new Bishop("black");
			board[7][j] = new Bishop("white");
		}
		board[0][3] = new Queen("black");
		board[7][3] = new Queen("white");
		board[0][4] = new King("black");
		board[7][4] = new King("white");
	}
	
	static void print_board()
	{
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				Piece piece = board[i][j];
				if (piece != null)
				{
					System.out.print(piece + " ");
				}
				else if ((i+j)%2 == 1)
				{
					System.out.print("## ");
				}
				else
				{
					System.out.print("   ");
				}
			}
			System.out.println(8-i);
		}
		System.out.println(" a  b  c  d  e  f  g  h\n");
	}
	
	static int file_to_col(char file)
	{
		switch (file)
		{
			case 'a': return 0;
			case 'b': return 1;
			case 'c': return 2;
			case 'd': return 3;
			case 'e': return 4;
			case 'f': return 5;
			case 'g': return 6;
			case 'h': return 7;
		}
		return -1;
	}
	
	static int rank_to_row(char rank)
	{
		switch(rank)
		{
			case '8': return 0;
			case '7': return 1;
			case '6': return 2;
			case '5': return 3;
			case '4': return 4;
			case '3': return 5;
			case '2': return 6;
			case '1': return 7;
		}
		return -1;
	}
	
	static char col_to_file(int col)
	{
		switch (col)
		{
			case 0: return 'a';
			case 1: return 'b';
			case 2: return 'c';
			case 3: return 'd';
			case 4: return 'e';
			case 5: return 'f';
			case 6: return 'g';
			case 7: return 'h';
		}
		return ' ';
	}
	
	static char row_to_rank(int row)
	{
		switch(row)
		{
			case 0: return '8';
			case 1: return '7';
			case 2: return '6';
			case 3: return '5';
			case 4: return '4';
			case 5: return '3';
			case 6: return '2';
			case 7: return '1';
		}
		return ' ';
	}
	
	static boolean check(String color)
	{
		Piece king = Board.board[0][0];
		int x=0, y=0;
		boolean flag = false;
		for (x=0; x<8; x++)
		{
			for (y=0; y<8; y++)
			{
				king = Board.board[x][y];
				if (king != null && king instanceof King && king.hasColor(color))
				{
					flag = true;
					break;
				}
			}
			if (flag)
			{
				break;
			}
		}
		Piece attacker = null;
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				attacker = Board.board[i][j];
				if (attacker != null && !attacker.hasColor(color))
				{
					if (attacker.move(col_to_file(j) + "" + row_to_rank(i) + " " + col_to_file(y) + "" + row_to_rank(x), false))
					{
						// System.out.println(color + " in check by " + col_to_file(j) + row_to_rank(i));
						return true;
					}
				}
			}
		}
		return false;
	}
	
	static boolean checkmate(String color)
	{
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				Piece piece = Board.board[i][j];
				if (piece != null && piece.hasColor(color))
				{
					for (int x=0; x<8; x++)
					{
						for (int y=0; y<8; y++)
						{
							if (piece.move(col_to_file(j) + "" + row_to_rank(i) + " " + col_to_file(y) + "" + row_to_rank(x), false))
							{
								
								return false;
							}
						}
					}
					
				}
			}
		}
		return true;
	}
}
