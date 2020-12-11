package model_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    public void writeList(File path) throws IOException
    {
        HashMap<String, String> map = readList(path);
        map.put(name, date);

        File file = new File(path, "list.csv");
        FileWriter writer = new FileWriter(file);

        for (Map.Entry<String, String> entry : map.entrySet())
        {
            String name = entry.getKey();
            String date = entry.getValue();

            writer.append(name);
            writer.append(",");
            writer.append(date);
            writer.append("\n");
        }
        writer.close();
    }

    public static HashMap<String, String> readList(File path) throws IOException
    {
        File file = new File(path, "list.csv");

        if (file.exists())
        {
            HashMap<String, String> map = new HashMap<String, String>();

            FileReader reader = new FileReader(file);
            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null)
            {
                String[] arr = line.split(",", -1);

                String name = arr[0];
                String date = arr[1];

                map.put(name, date);
            }
            br.close();
            return map;
        }
        else
        {
            return new HashMap<String, String>();
        }
    }

    public void writeData(File path) throws IOException
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
}
