package model;

public class Game
{
    int turn;
    Board first;

    public Game()
    {
        turn = 0;
        first = new Board();
        first.initialize_board();
    }
}
