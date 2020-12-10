package model_2;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Game
{
    Board first;
    Board current;
    String winner;
    String name;
    String date;

    public Game()
    {
        first = new Board();
        first.initializeBoard();
        current = first;
        winner = null;
        name = null;
        date = null;
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

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void writeList(File path) throws IOException, JSONException
    {
        File file = new File(path, "list.json");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(name, date);

        FileWriter writer = new FileWriter(file);
        writer.write(jsonObject.toString());
        writer.close();
    }

    public void writeData(File path) throws IOException, JSONException
    {
        writeList(path);

        File file = new File(path, name + ".txt");
        FileWriter writer = new FileWriter(file);

        writer.write(winner + "\n\n");
        writer.flush();

        for (Board board = first; board != null; board = board.next)
        {
            String boardStr = "";
            for (int i = 0; i < 8; i++)
            {
                String rowString = "";
                for (int j = 0; j < 8; j++)
                {
                    String pieceStr = "";
                    Piece piece = board.getPiece(i, j);
                    if (piece != null)
                    {
                        pieceStr = piece.toString() + " ";
                    }
                    else
                    {
                        pieceStr = "-- ";
                    }
                    rowString += pieceStr;
                }
                boardStr += rowString + "\n";
            }
            writer.write(boardStr + "\n");
            writer.flush();
        }
    }

    public static void readData()
    {

    }

}
