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
    ListView gamelist;

    TableLayout table;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordedgames);
        table = (TableLayout) findViewById(R.id.gametable);

        insertHeaderRow();
        //insertRows();
    }


    private void insertHeaderRow() {
        TableRow trow = new TableRow(this);
        //
        {   TextView hrow = new TextView(this);
            hrow.setText("Title");
            hrow.setTextColor(Color.WHITE);
            hrow.setTextSize(15);
            hrow.setOnClickListener(RecordedGamesActivity.this);
            trow.addView(hrow);
        }

        {   TextView hrow = new TextView(this);
            hrow.setText("Date");
            hrow.setTextColor(Color.WHITE);
            hrow.setTextSize(15);
            hrow.setOnClickListener(RecordedGamesActivity.this);
            trow.addView(hrow);
        }

        table.addView(trow);
    }



    @Override
    public void onClick(View v) {

    }
}
