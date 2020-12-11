package com.example.androidchess26;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import model_2.*;

public class PlaybackActivity extends AppCompatActivity
{
    TextView txtturn;
    TextView txtwinner;
    Button btnPrev;
    Button btnNext;

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
    }


}


