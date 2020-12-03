package com.example.androidchess26;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class RecordedGamesActivity extends AppCompatActivity
{
    ListView gamelist;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordedgames);

        gamelist = findViewById(R.id.gamelist);

    }
}
