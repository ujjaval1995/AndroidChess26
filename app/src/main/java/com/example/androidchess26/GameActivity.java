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

    ImageView imgplayer;
    Button btnAI;
    Button btnUndo;
    Button btnDraw;
    Button btnResign;

    ImageView[][] views;
    String currentPlayer;
    String selectedSquare;
    Piece selectedPiece;

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ImageView imgplayer = (ImageView) findViewById(R.id.imgplayer);
        btnAI = (Button) findViewById(R.id.btnAI);
        btnUndo = (Button) findViewById(R.id.btnUndo);
        btnDraw = (Button) findViewById(R.id.btnDraw);
        btnResign = (Button) findViewById(R.id.btnResign);

        btnAI.setOnClickListener(this);
        btnUndo.setOnClickListener(this);
        btnDraw.setOnClickListener(this);
        btnResign.setOnClickListener(this);

        views = new ImageView[8][8];
        currentPlayer = "white";
        selectedSquare = null;
        selectedPiece = null;
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
            try {
                select(img);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

    }

    public void init_board()
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                Resources res = getResources();
                int id = res.getIdentifier(Character.toString(Board.colToFile(j)) + Character.toString(Board.rowToRank(i)), "id", this.getPackageName());
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

    public void select(ImageView img) throws CloneNotSupportedException
    {
        Resources res = getResources();
        String pos = res.getResourceEntryName(img.getId());

        if (selectedSquare == null)
        {
            Piece piece = game.getCurrent().getPiece(pos);
            if (piece != null && piece.hasColor(currentPlayer))
            {
                img.setBackgroundColor(Color.parseColor(pink));
                selectedSquare = pos;
                selectedPiece = piece;
            }
        }
        else if (selectedSquare.equals(pos))
        {
            color_board();
            selectedSquare = null;
            selectedPiece = null;
        }
        else
        {
            Board board = game.getCurrent();
            Board newBoard = new Board(board);
            int row = Board.rankToRow(pos.charAt(1));
            int col = Board.fileToCol(pos.charAt(0));
            Piece piece = newBoard.getBoardIdx()[row][col];
            if (piece != null)
            {
                boolean moved = newBoard.getBoardIdx()[row][col].move(selectedSquare + " " + pos + "", true);
                if (moved)
                {
                    update_board();
                }
                else
                {

                }
            }
        }
    }

    public void update_board()
    {

    }

}