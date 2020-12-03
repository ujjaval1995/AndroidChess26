package model;

public class Game
{
    int turn;
    Board first;
    Board curr;

    public Game()
    {
        turn = 0;
        first = new Board();
        curr = first;
        first.initialize_pieces();
    }
}
