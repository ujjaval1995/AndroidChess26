package com.example.androidchess26;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.*;

import java.util.List;

public class RecordedGamesActivity extends AppCompatActivity implements View.OnClickListener
{

    TableLayout table;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        table = (TableLayout) findViewById(R.id.gametable);

        insertHeaderRow();
        insertTableRow();
    }


    private void insertHeaderRow() {
        TableRow trow = new TableRow(this);
        //
        {   TextView hrow = new TextView(this);
            hrow.setText("Title");
            hrow.setTextColor(Color.WHITE);
            hrow.setBackgroundColor(Color.GRAY);
            hrow.setTextSize(15);
            hrow.setWidth(250);
            hrow.setHeight(40);
            hrow.setOnClickListener(RecordedGamesActivity.this);
            trow.addView(hrow);
        }

        {   TextView hrow = new TextView(this);
            hrow.setText("Date");
            hrow.setTextColor(Color.WHITE);
            hrow.setBackgroundColor(Color.GRAY);
            hrow.setWidth(250);
            hrow.setHeight(40);
            hrow.setTextSize(15);
            hrow.setOnClickListener(RecordedGamesActivity.this);
            trow.addView(hrow);
        }

        {   TextView hrow = new TextView(this);
            hrow.setText("");
            hrow.setTextColor(Color.WHITE);
            hrow.setBackgroundColor(Color.GRAY);
            hrow.setWidth(250);
            hrow.setHeight(40);
            hrow.setTextSize(15);
            hrow.setOnClickListener(RecordedGamesActivity.this);
            trow.addView(hrow);
        }

        table.addView(trow);
    }

    private void insertTableRow() {
        TableRow trow = new TableRow(this);
        //
        {   TextView hrow = new TextView(this);
            hrow.setText("Title");
            hrow.setTextColor(Color.WHITE);
            hrow.setBackgroundColor(Color.GRAY);
            hrow.setTextSize(15);
            hrow.setWidth(250);
            hrow.setHeight(40);
            hrow.setOnClickListener(RecordedGamesActivity.this);
            trow.addView(hrow);
        }

        {   TextView hrow = new TextView(this);
            hrow.setText("Date");
            hrow.setTextColor(Color.WHITE);
            hrow.setBackgroundColor(Color.GRAY);
            hrow.setWidth(250);
            hrow.setHeight(40);
            hrow.setTextSize(15);
            hrow.setOnClickListener(RecordedGamesActivity.this);
            trow.addView(hrow);
        }

        {   Button btPlayback = new Button(this);
            btPlayback.setText("");
            btPlayback.setTextColor(Color.WHITE);
            btPlayback.setBackgroundColor(Color.GRAY);
            btPlayback.setWidth(200);
            btPlayback.setHeight(40);
            btPlayback.setTextSize(15);
            btPlayback.setOnClickListener(RecordedGamesActivity.this);
            trow.addView(btPlayback);
        }

        table.addView(trow);
    }



    @Override
    public void onClick(View v) {

    }
}
