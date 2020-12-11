package com.example.androidchess26;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import model_2.*;

class StrGame
{
    StrBoard first;
    StrBoard current;

    public StrGame()
    {
        first = null;
        current = null;
    }

    public void addStrBoard(StrBoard strBoard)
    {
        if (first == null)
        {
            first = strBoard;
            current = first;
        }
        else
        {
            current.next = strBoard;
            strBoard.prev = current;
            current = current.next;
        }
    }

    public StrBoard goToPrevBoard()
    {
        current = current.prev;
        return current;
    }

    public StrBoard goToNextBoard()
    {
        current = current.next;
        return current;
    }

    public boolean hasPrev()
    {
        return (current.prev != null) ? true : false;
    }

    public boolean hasNext()
    {
        return (current.next != null) ? true : false;
    }

}

class StrBoard
{
    String[][] strBoardIdx;
    StrBoard prev;
    StrBoard next;

    public StrBoard()
    {
        strBoardIdx = new String[8][8];
        next = null;
        prev = null;
    }

    public void printBoard()
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                System.out.print(strBoardIdx[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

public class PlaybackActivity extends AppCompatActivity
{
    ImageView imgplayer;
    TextView txtturn;
    TextView txtwinner;
    Button btnPrev;
    Button btnNext;

    int turn;
    StrGame strGame;
    String currentPlayer;
    String name;
    String winner;

    File path;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);

        imgplayer = (ImageView) findViewById(R.id.imgplayer);
        txtturn = (TextView) findViewById(R.id.txtturn);
        txtwinner = (TextView) findViewById(R.id.txtwinner);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");

        strGame = new StrGame();

        path = getFilesDir();

        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        txtwinner.setText(winner);

        currentPlayer = "white";
        turn = 1;

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(arg0 ->
        {
            if (strGame.hasPrev())
            {
                strGame.goToPrevBoard();
                decrementTurn();
                changePlayer();
                refreshBoard();
            }
            else
            {
                Toast.makeText(this, "Cannot Go Further", Toast.LENGTH_SHORT).show();
            }
        });

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(arg0 ->
        {
            if (strGame.hasNext())
            {
                strGame.goToNextBoard();
                incrementTurn();
                changePlayer();
                refreshBoard();
            }
            else
            {
                Toast.makeText(this, "Cannot Go Further", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void readData() throws IOException
    {
        File file = new File(path, name + ".txt");

        if (file.exists())
        {
            FileReader reader = new FileReader(file);

            String line = "";
            BufferedReader br = new BufferedReader(new FileReader(file));

            winner = br.readLine();

            boolean flag = false;
            while ((line = br.readLine()) != null)
            {
                StrBoard strBoard = new StrBoard();
                for (int i = 0; i < 8; i++)
                {
                    line = br.readLine();
                    if (line == null)
                    {
                        flag = true;
                        break;
                    }
                    String[] arr = line.split(" ", 0);

                    for (int j = 0; j < 8; j++)
                    {
                        strBoard.strBoardIdx[i][j] = arr[j];
                    }
                }
                if (flag)
                {
                    break;
                }
                strGame.addStrBoard(strBoard);
            }
            strGame.current = strGame.first;
            br.close();
        }
    }

    public void refreshBoard()
    {
        Resources res = getResources();
        String[][] strBoardIdx = strGame.current.strBoardIdx;

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                int id = res.getIdentifier(Character.toString(Board.colToFile(j)) + Character.toString(Board.rowToRank(i)), "id", this.getPackageName());
                ImageView img = findViewById(id);

                String strPiece = strBoardIdx[i][j];

                switch (strPiece)
                {
                    case "wp":
                        img.setImageResource(R.drawable.whitepawn);
                        break;
                    case "wK":
                        img.setImageResource(R.drawable.whiteking);
                        break;
                    case "wQ":
                        img.setImageResource(R.drawable.whitequeen);
                        break;
                    case "wN":
                        img.setImageResource(R.drawable.whiteknight);
                        break;
                    case "wR":
                        img.setImageResource(R.drawable.whiterook);
                        break;
                    case "wB":
                        img.setImageResource(R.drawable.whitebishop);
                        break;
                    case "bp":
                        img.setImageResource(R.drawable.blackpawn);
                        break;
                    case "bK":
                        img.setImageResource(R.drawable.blackking);
                        break;
                    case "bQ":
                        img.setImageResource(R.drawable.blackqueen);
                        break;
                    case "bN":
                        img.setImageResource(R.drawable.blackknight);
                        break;
                    case "bR":
                        img.setImageResource(R.drawable.blackrook);
                        break;
                    case "bB":
                        img.setImageResource(R.drawable.blackbishop);
                        break;
                    default:
                        img.setImageResource(R.drawable.transparent);
                }
            }
        }
    }

    public void changePlayer()
    {
        if (currentPlayer.equals("white"))
        {
            imgplayer.setImageResource(R.drawable.blackking);
            currentPlayer = "black";
        }
        else
        {
            imgplayer.setImageResource(R.drawable.whiteking);
            currentPlayer = "white";
        }
    }

    public void incrementTurn()
    {
        turn++;
        txtturn.setText("Turn " + turn + "");
        changePlayer();
    }

    public void decrementTurn()
    {
        turn--;
        txtturn.setText("Turn " + turn + "");
        changePlayer();
    }


}


