package model_2;

public class Game
{
    int turn;
    Board first;
    Board current;
    String winner = null;

    public Game()
    {
        turn = 0;
        first = new Board();
        first.initializeBoard();
        current = first;
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
}
