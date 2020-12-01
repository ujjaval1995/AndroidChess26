package chess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents the Chess.
 *
 * @author Jishnu Patel
 * @author Ujjaval Shah
 */

public class Chess
{
	static String turn = "white";
	static int turn_count = 0;
	static boolean draw = false;
	static boolean game_over = false;
	
	static boolean test = false;
	
	static void print_turn()
	{
		if (turn.equals("white"))
		{
			System.out.print("White's Turn: ");
		}
		else
		{
			System.out.print("Black's Turn: ");
		}
	}
	
	static void change_turn()
	{
		if (turn.equals("white"))
		{
			turn = "black";
		}
		else
		{
			turn = "white";
		}
	}
	
	static void increment_turn_count()
	{
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				Piece piece = Board.board[i][j];
				if (piece instanceof Pawn)
				{
					if (turn.equals(piece.getColor()))
					{
						Pawn pawn = (Pawn) piece;
						pawn.enpassant = false;
					}
				}
			}
		}
		turn_count++;
	}
	
	static void end_game()
	{
		if (!draw)
		{
			if (turn.equals("white"))
			{
				System.out.print("White wins");
			}
			else
			{
				System.out.print("Black wins");
			}
		}
	}

	/**
	 * This is the main class.
	 */
	public static void main(String[] args)
	{
		Board.make_board();
		Board.initialize_board();
		Scanner sc = new Scanner(System.in);
		Scanner fsc = null;
		
		if (test)
		{
			try
			{
			      File file = new File("test_cases/test.txt");
			      fsc = new Scanner(file);
			}
			catch (FileNotFoundException e)
			{
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			}
		}
		
		while (!game_over)
		{	
			Board.print_board();
			if (Board.checkmate(turn))
			{
				System.out.println("Checkmate");
				change_turn();
				game_over = true;
				break;
			}
			else if (Board.check(turn))
			{
				System.out.println("Check");
			}
			
			String input = "";
			int row1 = -1, col1 = -1, row2 = -1, col2 = -1;
			
			while (true)
			{
				print_turn();
				
				if (test && fsc.hasNextLine())
				{
					input = fsc.nextLine();
					System.out.println(input);
				}
				else
				{
					input = sc.nextLine();
				}
				if (input.equals("resign"))
				{
					game_over = true;
					break;
				}
				else if (draw && input.equals("draw"))
				{
					game_over = true;
					break;
				}
				else if (input.length() < 5)
				{
					System.out.println("Invalid input, try again");
					continue;
				}
				
				col1 = Board.file_to_col(input.charAt(0));
				row1 = Board.rank_to_row(input.charAt(1));
				col2 = Board.file_to_col(input.charAt(3));
				row2 = Board.rank_to_row(input.charAt(4));
				
				if (row1 == -1 || col1 == -1 || row2 == -1 || col2 == -1)
				{
					System.out.println("Invalid input, try again");
					continue;
				}
				if (Board.board[row1][col1] != null && Board.board[row1][col1].move(input, true))
				{
					draw = false;
					if (input.length() >= 11)
					{
						if (input.substring(input.length()-5).equals("draw?"))
						{
							draw = true;
						}
					}
					break;
				}
				else
				{
					System.out.println("Illegal move, try again");
					continue;
				}
			}
			if (!game_over)
			{
				System.out.println();
			}
			change_turn();
			increment_turn_count();
		}
		if (test)
		{
			fsc.close();
		}
		sc.close();
		end_game();
	}
}
