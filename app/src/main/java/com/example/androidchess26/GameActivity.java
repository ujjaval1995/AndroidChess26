package com.example.androidchess26;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import model_2.*;


public class GameActivity extends AppCompatActivity implements View.OnClickListener
{
    final String pink = "#FF6666";
    final String colored = "#996633";
    final String uncolored = "#FFFFCC";

    File path;

    ImageView imgplayer;
    TextView txtturn;

    Button btnAI;
    Button btnUndo;
    Button btnDraw;
    Button btnResign;

    ImageView[][] views;

    int turn;
    boolean canUndo;
    boolean drawPressed;
    boolean drawAsked;
    String currentPlayer;
    String selectedSquare;
    String winner;

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        path = getFilesDir();

        imgplayer = (ImageView) findViewById(R.id.imgplayer);
        txtturn = (TextView) findViewById(R.id.txtturn);

        btnAI = (Button) findViewById(R.id.btnAI);
        btnUndo = (Button) findViewById(R.id.btnUndo);
        btnDraw = (Button) findViewById(R.id.btnDraw);
        btnResign = (Button) findViewById(R.id.btnResign);

        btnAI.setOnClickListener(this);
        btnUndo.setOnClickListener(this);
        btnDraw.setOnClickListener(this);
        btnResign.setOnClickListener(this);

        views = new ImageView[8][8];

        turn = 1;
        canUndo = false;
        drawPressed = false;
        drawAsked = false;
        currentPlayer = "white";
        selectedSquare = null;
        winner = null;
        initBoard();

        game = new Game();
    }

    @Override
    public void onClick(View view)
    {
        if (winner != null) return;

        if (view instanceof Button)
        {
            Button btn = (Button) view;
            switch (btn.getId())
            {
                case R.id.btnAI:
                    ai();
                    break;
                case R.id.btnUndo:
                    undo();
                    break;
                case R.id.btnDraw:
                    draw();
                    break;
                case R.id.btnResign:
                    try {
                        resign();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        else if (view instanceof ImageView)
        {
           ImageView img = (ImageView) view;
           select(img);
        }

    }
    public void select(ImageView img)
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
            }
        }
        else if (selectedSquare.equals(pos))
        {
            colorBoard();
            selectedSquare = null;
        }
        else
        {
            Board board = game.getCurrent();
            Board newBoard = new Board(board);

            int row = Board.rankToRow(pos.charAt(1));
            int col = Board.fileToCol(pos.charAt(0));

            Piece piece = newBoard.getPiece(selectedSquare);
            boolean moved = piece.move(selectedSquare + " " + pos + "", true);
            if (moved)
            {
                game.addBoard(newBoard);
                refreshBoard(newBoard);
                incrementTurn();
                resetEnpassant();

                if (newBoard.checkmate(currentPlayer))
                {
                    winner = otherPlayer() + " wins";
                    game.setWinner(winner);
                    openDialog();
                }
                else if (newBoard.check(currentPlayer))
                {
                    Toast.makeText(this, "Check", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void initBoard()
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

    public void colorBoard()
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

    public void refreshBoard(Board board)
    {
        colorBoard();
        selectedSquare = null;
        if (drawPressed)
        {
            drawAsked = true;
        }
        else
        {
            drawAsked = false;
        }
        drawPressed = false;
        Resources res = getResources();
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                int id = res.getIdentifier(Character.toString(Board.colToFile(j)) + Character.toString(Board.rowToRank(i)), "id", this.getPackageName());
                ImageView img = findViewById(id);

                Piece piece = game.getCurrent().getPiece(i, j);
                if (piece != null)
                {
                    switch (piece.toString())
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
                    }
                }
                else
                {
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

    public String otherPlayer()
    {
        if (currentPlayer.equals("white"))
        {
            return "black";
        }
        else
        {
            return "white";
        }
    }

    public void resetEnpassant()
    {
        Board board = game.getCurrent();
        for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				Piece piece = board.getPiece(i, j);
				if (piece instanceof Pawn)
				{
					if (currentPlayer.equals(piece.getColor()))
					{
						Pawn pawn = (Pawn) piece;
						pawn.setEnpassant(false);
					}
				}
			}
		}
    }

    public void incrementTurn()
	{
		turn++;
        txtturn.setText("Turn " + turn + "");
        changePlayer();
        canUndo = true;
	}

    public void decrementTurn()
    {
        turn--;
        txtturn.setText("Turn " + turn + "");
        changePlayer();
        canUndo = false;
    }

    public void openDialog()
    {
        Dialog dialog = new Dialog();
        Bundle bundle = new Bundle();
        bundle.putString("winner", winner);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "dialog");
    }

    public void ai()
    {
        Board board = game.getCurrent();
        ArrayList<String> list = board.getLegalMoves(currentPlayer);
        int size = list.size();

        Random rand = new Random();
        int n = rand.nextInt(size);
        String str = list.get(n);
        char rank = str.charAt(1);
        char file = str.charAt(0);
        int row = Board.rankToRow(rank);
        int col = Board.fileToCol(file);

        Board newBoard = new Board(board);
        Piece piece = newBoard.getPiece(row, col);
        piece.move(str, true);

        game.addBoard(newBoard);
        refreshBoard(newBoard);
        selectedSquare = null;
        incrementTurn();
        resetEnpassant();

        if (newBoard.checkmate(currentPlayer))
        {
            openDialog();
        }
        else if (newBoard.check(currentPlayer))
        {
            Toast.makeText(this, "Check", Toast.LENGTH_SHORT).show();
        }
    }

    public void undo()
    {
        if (canUndo)
        {
            Board board = game.goToPrevBoard();
            refreshBoard(board);
            decrementTurn();
        }
        else
        {
            Toast.makeText(this, "Cannot Undo", Toast.LENGTH_SHORT).show();
        }
    }

    public void draw()
    {
        if (drawAsked)
        {
            winner = "draw";
            game.setWinner(winner);
            openDialog();
        }
        else if (drawPressed)
        {
            drawPressed = false;
            Toast.makeText(this, "Draw Deactivated", Toast.LENGTH_SHORT).show();
        }
        else
        {
            drawPressed = true;
            Toast.makeText(this, "Draw Activated. Press Draw On Next Turn to Accept", Toast.LENGTH_SHORT).show();
        }
    }

    public void resign() throws IOException {
        winner = otherPlayer() + " wins";
        game.setWinner(winner);
        game.writeData(path);
        openDialog();
    }
}