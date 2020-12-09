package model_2;

public class Game
{
    Board first;
    Board current;
    String winner;

    public Game()
    {
        first = new Board();
        first.initializeBoard();
        current = first;
        winner = null;
    }

    public Board getFirst()
    {
        return first;
    }

    public Board getCurrent()
    {
        return current;
    }

    public Board goToPrevBoard()
    {
        current = current.prev;
        return current;
    }

    public void addBoard(Board board)
    {
        current.next = board;
        board.prev = current;
        board.next = null;
        current = board;
    }

    public void setWinner(String winner)
    {
        this.winner = winner;
    }

    public void writeData()
    {
        for (Board board = first; board != null; board = board.next)
        {
            String boardStr = "";
            for (int i = 0; i < 8; i++)
            {
                String rowString = "";
                for (int j = 0; j < 8; j++)
                {
                    Piece piece = board.getPiece(i, j);
                    if (piece != null)
                    {
                        String pieceStr = piece.toString();
                    }
                }
            }
        }
    }

    public static void readData()
    {

    }

}
