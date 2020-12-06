package model;

public class Game
{
    int turn;
    Board first;
    Board current;

    public Game()
    {
        turn = 0;
        first = new Board();
        first.initialize_board();
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

    public void addBoard() throws CloneNotSupportedException
    {
        Board board = new Board(current);
        current.next = board;
        board.prev = current;
        board.next = null;
        current = board;
    }

}
