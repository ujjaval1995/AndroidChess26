package com.example.androidchess26;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

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
}

public class PlaybackActivity extends AppCompatActivity
{
    TextView txtturn;
    TextView txtwinner;
    Button btnPrev;
    Button btnNext;

    StrGame strGame;
    String name;
    String winner;

    File path;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);

        txtturn = (TextView) findViewById(R.id.txtturn);
        txtwinner = (TextView) findViewById(R.id.txtwinner);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(arg0 -> {

        });

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(arg0 -> {

        });

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");

        strGame = new StrGame();
        name = null;
        winner = null;

        path = getFilesDir();

        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        txtwinner.setText(winner);


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

            while ((line = br.readLine()) != null)
            {
                StrBoard strBoard = new StrBoard();
                for (int i = 0; i < 8; i++)
                {
                    line = br.readLine();
                    String[] arr = line.split(" ", 8);
                    for (int j = 0; j < 8; j++)
                    {
                        strBoard.strBoardIdx[i][j] = arr[j];
                    }
                }
                strGame.addStrBoard(strBoard);
            }
            strGame.current = strGame.first;
            br.close();
        }
    }


}


