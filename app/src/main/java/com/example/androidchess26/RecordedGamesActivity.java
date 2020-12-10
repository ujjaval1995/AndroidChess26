package com.example.androidchess26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model_2.Game;

class GameInfo
{
    String name;
    String date;

    public GameInfo(String name, String date)
    {
        this.name = name;
        this.date = date;
    }

    public String toString()
    {
        return name + "\n(" + date + ")";
    }
}

public class RecordedGamesActivity extends AppCompatActivity
{
    ListView listView;
    Button btnsort;

    String name;
    String sort;

    ArrayList<GameInfo> games;

    File path;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        path = getFilesDir();

        games = new ArrayList<GameInfo>();

        try
        {
            HashMap<String, String> map = Game.readList(path);
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                String name = entry.getKey();
                String date = entry.getValue();
                GameInfo info = new GameInfo(name, date);
                games.add(info);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        name = null;
        sort = "name";

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(
                new ArrayAdapter<GameInfo>(this, R.layout.game, games));

        listView.setOnItemClickListener((p, V, pos, id) ->
                toPlayback(pos));

        Button btnsort = (Button) findViewById(R.id.btnsort);

    }

    public void sort()
    {
        if (sort.equals("name"))
        {

        }
        else
        {

        }
    }

    public void toPlayback(int pos)
    {
        Bundle bundle = new Bundle();
        GameInfo info = games.get(pos);
        name = info.name;
        bundle.putString("name", name);
        Intent intent = new Intent(this, PlaybackActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
