package com.example.androidchess26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import model.*;


public class GameActivity extends AppCompatActivity implements View.OnClickListener
{
    Button btnAI;
    Button btnUndo;
    Button btnDraw;
    Button btnResign;

    ImageView[][] views = new ImageView[8][8];
    String selected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnAI = (Button) findViewById(R.id.btnAI);
        btnUndo = (Button) findViewById(R.id.btnUndo);
        btnDraw = (Button) findViewById(R.id.btnDraw);
        btnResign = (Button) findViewById(R.id.btnResign);

        btnAI.setOnClickListener(this);
        btnUndo.setOnClickListener(this);
        btnDraw.setOnClickListener(this);
        btnResign.setOnClickListener(this);

        init_board();

        Game game = new Game();


    }

    @Override
    public void onClick(View view)
    {
        if (view instanceof Button)
        {
            Button btn = (Button) view;
            Log.i("Button", view.getResources().getResourceName(view.getId()));
            switch (btn.getId())
            {
                case R.id.btnAI:
                    // do something
                    break;
                case R.id.btnUndo:
                    // do something
                    break;
                case R.id.btnDraw:
                    // do something
                    break;
                case R.id.btnResign:
                    // do something
                    break;
            }
        }
        else if (view instanceof ImageView)
        {
           ImageView img = (ImageView) view;
           Log.i("ImageView", view.getResources().getResourceName(view.getId()));
        }

    }

    public void init_board()
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                Resources res = getResources();
                int id = res.getIdentifier(Character.toString(Board.col_to_file(j)) + Character.toString(Board.row_to_rank(i)), "id", this.getPackageName());
                views[i][j] = (ImageView) findViewById(id);
                views[i][j].setOnClickListener(this);
            }
        }
    }

}