package com.example.androidchess26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import model.*;


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



        btnAI.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
               // Intent intent = new Intent(MainActivity.this, GameActivity.class);
               // startActivity(intent);
            }
        });

        btnUndo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                // Intent intent = new Intent(MainActivity.this, GameActivity.class);
                // startActivity(intent);
            }
        });

        btnDraw.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                // Intent intent = new Intent(MainActivity.this, GameActivity.class);
                // startActivity(intent);
            }
        });

        btnResign.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0) {
                // Intent intent = new Intent(MainActivity.this, GameActivity.class);
                // startActivity(intent);
            }
        });
    }
}