package com.example.androidchess26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import model.Game;


public class GameActivity extends AppCompatActivity
{
    Button btnAI;
    Button btnUndo;
    Button btnDraw;
    Button btnResign;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnAI = findViewById(R.id.btnAI);
        btnUndo = findViewById(R.id.btnUndo);
        btnDraw = findViewById(R.id.btnDraw);
        btnResign = findViewById(R.id.btnResign);

        Game game = new Game();



        btnAI.setOnClickListener(arg0 ->
        {
            Log.i("btn", "AI");
        });

        btnUndo.setOnClickListener(arg0 ->
        {
            Log.i("btn", "Undo");
        });

        btnDraw.setOnClickListener(arg0 ->
        {
            Log.i("btn", "Draw");
        });

        btnResign.setOnClickListener(arg0 ->
        {
            Log.i("btn", "Resign");
        });
    }

}