package com.example.androidchess26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import model.*;


public class GameActivity extends AppCompatActivity implements View.OnClickListener
{
    final String pink = "#FF6666";
    final String colored = "#996633";
    final String uncolored = "#FFFFCC";

    ImageView player;
    Button btnAI;
    Button btnUndo;
    Button btnDraw;
    Button btnResign;

    ImageView[][] views;
    String current_player;
    String selected_square;
    Piece selected_piece;

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ImageView player = (ImageView) findViewById(R.id.player);
        btnAI = (Button) findViewById(R.id.btnAI);
        btnUndo = (Button) findViewById(R.id.btnUndo);
        btnDraw = (Button) findViewById(R.id.btnDraw);
        btnResign = (Button) findViewById(R.id.btnResign);

        btnAI.setOnClickListener(this);
        btnUndo.setOnClickListener(this);
        btnDraw.setOnClickListener(this);
        btnResign.setOnClickListener(this);

        views = new ImageView[8][8];
        current_player = "white";
        selected_square = null;
        selected_piece = null;
        init_board();

        game = new Game();
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
           select(img);
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

    public void color_board()
    {
        for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if ((i + j) % 2 == 1)
				{
					views[i][j].setBackgroundColor(Color.parseColor(colored));
				}
				else
				{
                    views[i][j].setBackgroundColor(Color.parseColor(uncolored));
				}
			}
		}
    }

    public void change_turn()
    {
        if (current_player.equals("white"))
        {
            player.setImageResource(R.drawable.blackking);
            current_player = "black";
        }
        else
        {
            player.setImageResource(R.drawable.whiteking);
            current_player = "white";
        }
    }

    public void select(ImageView img)
    {
        Resources res = getResources();
        String pos = res.getResourceEntryName(img.getId());

        if (selected_square == null)
        {
            Piece piece = game.getCurrent().getPiece(pos);
            if (piece != null && piece.hasColor(current_player))
            {
                img.setBackgroundColor(Color.parseColor(pink));
                selected_square = pos;
                selected_piece = piece;
            }
        }
        else if (selected_square.equals(pos))
        {
            color_board();
            selected_square = null;
            selected_piece = null;
        }
        else
        {
            // move piece
        }
    }

}