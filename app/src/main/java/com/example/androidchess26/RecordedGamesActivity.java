package com.example.androidchess26;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import model_2.Game;

class GameInfo implements Comparable
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

    public int compareTo(GameInfo other)
    {
        int ret = name.compareTo(other.name);
        return (ret != 0) ? ret : date.compareTo(other.date);
    }

    @Override
    public int compareTo(Object o)
    {
        GameInfo other = (GameInfo) o;
        int ret = name.compareTo(other.name);
        return (ret != 0) ? ret : date.compareTo(other.date);
    }

    public static Comparator<GameInfo> dateComparator = new Comparator<GameInfo>()
    {
        public int compare(GameInfo gi1, GameInfo gi2)
        {
            String date1 = gi1.date;
            String date2 = gi2.date;
            return date1.compareTo(date2);
        }};
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
        btnsort.setOnClickListener(arg0 ->
        {
            sort();
        });
    }

    public void sort()
    {
        if (sort.equals("name"))
        {
            Collections.sort(games, GameInfo.dateComparator);
            listView.setAdapter(
                    new ArrayAdapter<GameInfo>(this, R.layout.game, games));
            sort = "date";
            Toast.makeText(this, "Sorted By Date", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Collections.sort(games);
            listView.setAdapter(
                    new ArrayAdapter<GameInfo>(this, R.layout.game, games));
            sort = "name";
            Toast.makeText(this, "Sorted By Name", Toast.LENGTH_SHORT).show();
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
