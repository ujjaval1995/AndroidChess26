package model_2;

public class Board
{
	Piece[][] boardIdx;
	Board next;
	Board prev;

	public Board()
	{
		boardIdx = new Piece[8][8];
		next = null;
		prev = null;
	}

	public Board(Board board)
	{
		boardIdx = new Piece[8][8];
		next = null;
		prev = null;

		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Piece piece = board.boardIdx[i][j];
				Piece newPiece = null;
				if (piece != null)
				{
					if (piece instanceof Pawn)
					{
						newPiece = new Pawn(boardIdx, (Pawn) piece);
					}
					else if (piece instanceof King)
					{
						newPiece = new King(boardIdx, (King) piece);
					}
					else if (piece instanceof Queen)
					{
						newPiece = new Queen(boardIdx, (Queen) piece);
					}
					else if (piece instanceof Rook)
					{
						newPiece = new Rook(boardIdx, (Rook) piece);
					}
					else if (piece instanceof Knight)
					{
						newPiece = new Knight(boardIdx, (Knight) piece);
					}
					else if (piece instanceof Bishop)
					{
						newPiece = new Bishop(boardIdx, (Bishop) piece);
					}
					boardIdx[i][j] = newPiece;
				}
				else
				{
					boardIdx[i][j] = null;
				}
			}
		}
	}
	
	public void initializeBoard()
	{
		for (int j=0; j<8; j++)
		{
			boardIdx[1][j] = new Pawn(boardIdx, "black");
			boardIdx[6][j] = new Pawn(boardIdx, "white");
		}
		for (int j=0; j<8; j+=7)
		{
			boardIdx[0][j] = new Rook(boardIdx, "black");
			boardIdx[7][j] = new Rook(boardIdx, "white");
		}
		for (int j=1; j<8; j+=5)
		{
			boardIdx[0][j] = new Knight(boardIdx, "black");
			boardIdx[7][j] = new Knight(boardIdx, "white");
		}
		for (int j=2; j<8; j+=3)
		{
			boardIdx[0][j] = new Bishop(boardIdx, "black");
			boardIdx[7][j] = new Bishop(boardIdx, "white");
		}
		boardIdx[0][3] = new Queen(boardIdx, "black");
		boardIdx[7][3] = new Queen(boardIdx, "white");
		boardIdx[0][4] = new King(boardIdx, "black");
		boardIdx[7][4] = new King(boardIdx, "white");
	}

	public void printBoard()
	{
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				Piece piece = boardIdx[i][j];
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

	public Piece getPiece(String pos)
	{
		int col = fileToCol(pos.charAt(0));
		int row = rankToRow(pos.charAt(1));
		return boardIdx[row][col];
	}

	public Piece getPiece(int row, int col)
	{
		return boardIdx[row][col];
	}

	public static int fileToCol(char file)
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
	
	public static int rankToRow(char rank)
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
	
	public static char colToFile(int col)
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
	
	public static char rowToRank(int row)
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
	
	public static boolean check(Piece[][] boardIdx, String color)
	{
		Piece king = boardIdx[0][0];
		int x=0, y=0;
		boolean flag = false;
		for (x=0; x<8; x++)
		{
			for (y=0; y<8; y++)
			{
				king = boardIdx[x][y];
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
				attacker = boardIdx[i][j];
				if (attacker != null && !attacker.hasColor(color))
				{
					if (attacker.move(colToFile(j) + "" + rowToRank(i) + " " + colToFile(y) + "" + rowToRank(x), false))
					{
						// System.out.println(color + " in check by " + colToFile(j) + rowToRank(i));
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean checkmate(Piece[][] boardIdx, String color)
	{
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				Piece piece = boardIdx[i][j];
				if (piece != null && piece.hasColor(color))
				{
					for (int x=0; x<8; x++)
					{
						for (int y=0; y<8; y++)
						{
							if (piece.move(colToFile(j) + "" + rowToRank(i) + " " + colToFile(y) + "" + rowToRank(x), false))
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
