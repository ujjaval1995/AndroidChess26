package com.example.androidchess26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class GameActivity extends AppCompatActivity
{
    Button btnAI;
    Button btnRollback;
    Button btnDraw;
    Button btnResign;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        btnAI = findViewById(R.id.btnAI);
        btnRollback = findViewById(R.id.btnRollback);
        btnDraw = findViewById(R.id.btnDraw);
        btnResign = findViewById(R.id.btnResign);

        btnAI.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
               // Intent intent = new Intent(MainActivity.this, GameActivity.class);
               // startActivity(intent);
            }
        });

        btnRollback.setOnClickListener(new View.OnClickListener()
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

        btnResign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Intent intent = new Intent(MainActivity.this, GameActivity.class);
                // startActivity(intent);
            }
        });
    }
}