package com.example.androidchess26;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment
{
    private TextView txtwinner;
    private EditText gamename;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        TextView txtwinner = (TextView) view.findViewById(R.id.txtwinner);
        EditText gamename = (EditText) view.findViewById(R.id.gamename);

        Bundle bundle = getArguments();
        String winner = bundle.getString("winner","");
        txtwinner.setText(winner);

        builder.setView(view)
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {

                }
            })
            .setPositiveButton("Save Game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    String name = gamename.getText().toString().trim();

                    boolean valid = false;
                    if (!valid)
                    {
                        Toast.makeText(getActivity().getBaseContext(), "Invalid Name", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        return builder.create();
    }
}
